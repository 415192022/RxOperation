package com.li.utils;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;
import rx.subjects.PublishSubject;
import rx.subjects.SerializedSubject;
import rx.subjects.Subject;

/**
 * RxBus
 */
public class RxBusUtil {
    private static volatile RxBusUtil defaultInstance;

    private Map<Class,List<Subscription>> subscriptionsByEventType = new HashMap<>();


    private Map<Object,List<Class>> eventTypesBySubscriber = new HashMap<>();


    private Map<Class,List<SubscriberMethod>> subscriberMethodByEventType = new HashMap<>();

    private final Subject bus;
    // PublishSubject只会把在订阅发生的时间点之后来自原始Observable的数据发射给观察者
    public RxBusUtil() {
        bus = new SerializedSubject<>(PublishSubject.create());
    }
    // 单例RxBus
    public static RxBusUtil getDefault() {
        RxBusUtil rxBus = defaultInstance;
        if (defaultInstance == null) {
            synchronized (RxBusUtil.class) {
                rxBus = defaultInstance;
                if (defaultInstance == null) {
                    rxBus = new RxBusUtil();
                    defaultInstance = rxBus;
                }
            }
        }
        return rxBus;
    }


    /**
     * 提供了一个新的事件,单一类型
     * @param o 事件数据
     */
    public void post (Object o) {
        bus.onNext(o);
    }

    /**
     * 根据传递的 eventType 类型返回特定类型(eventType)的 被观察者
     * @param eventType 事件类型
     * @param <T>
     * @return
     */
    public <T> Observable<T> toObservable(Class<T> eventType) {
        return bus.ofType(eventType);
    }

    /**
     * 提供了一个新的事件,根据code进行分发
     * @param code 事件code
     * @param o
     */
    public void post(int code, Object o){
        bus.onNext(new Message(code,o));

    }


    /**
     * 根据传递的code和 eventType 类型返回特定类型(eventType)的 被观察者
     * @param code 事件code
     * @param eventType 事件类型
     * @param <T>
     * @return
     */
    public <T> Observable<T> toObservable(final int code, final Class<T> eventType) {
        return bus.ofType(Message.class)
                .filter(new Func1<Message,Boolean>() {
            @Override
            public Boolean call(Message o) {
                //过滤code和eventType都相同的事件
                return o.getCode() == code && eventType.isInstance(o.getObject());
            }
        }).map(new Func1<Message,Object>() {
            @Override
            public Object call(Message o) {
                return o.getObject();
            }
        }).cast(eventType);
    }


    /**
     * 注册
     * @param subscriber 订阅者
     */
    public void register(Object subscriber){
        Class<?> subClass = subscriber.getClass();
        Method[] methods = subClass.getDeclaredMethods();
        for(Method method : methods){
            if(method.isAnnotationPresent(Subscribe.class)){
                //获得参数类型
                Class[] parameterType = method.getParameterTypes();
                //参数不为空 且参数个数为1
                if(parameterType != null && parameterType.length == 1){

                    Class eventType = parameterType[0];

                    addEventTypeToMap(subscriber,eventType);
                    Subscribe sub = method.getAnnotation(Subscribe.class);
                    int code = sub.code();
                    ThreadMode threadMode = sub.threadMode();

                    SubscriberMethod subscriberMethod = new SubscriberMethod(subscriber,method,eventType, code,threadMode);
                    addSubscriberToMap(eventType, subscriberMethod);

                    addSubscriber(subscriberMethod);
                }
            }
        }
    }


    /**
     * 将event的类型以订阅中subscriber为key保存到map里
     * @param subscriber 订阅者
     * @param eventType event类型
     */
    private void addEventTypeToMap(Object subscriber, Class eventType){
        List<Class> eventTypes = eventTypesBySubscriber.get(subscriber);
        if(eventTypes == null){
            eventTypes = new ArrayList<>();
            eventTypesBySubscriber.put(subscriber,eventTypes);
        }

        if(!eventTypes.contains(eventType)){
            eventTypes.add(eventType);
        }
    }

    /**
     * 将注解方法信息以event类型为key保存到map中
     * @param eventType event类型
     * @param subscriberMethod 注解方法信息
     */
    private void addSubscriberToMap(Class eventType, SubscriberMethod subscriberMethod){
        List<SubscriberMethod> subscriberMethods = subscriberMethodByEventType.get(eventType);
        if(subscriberMethods == null){
            subscriberMethods = new ArrayList<>();
            subscriberMethodByEventType.put(eventType,subscriberMethods);
        }

        if(!subscriberMethods.contains(subscriberMethod)){
            subscriberMethods.add(subscriberMethod);
        }
    }


    /**
     *将订阅事件以event类型为key保存到map,用于取消订阅时用
     * @param eventType event类型
     * @param subscription 订阅事件
     */
    private void addSubscriptionToMap(Class eventType, Subscription subscription){
        List<Subscription> subscriptions = subscriptionsByEventType.get(eventType);
        if(subscriptions == null){
            subscriptions = new ArrayList<>();
            subscriptionsByEventType.put(eventType,subscriptions);
        }

        if(!subscriptions.contains(subscription)){
            subscriptions.add(subscription);
        }
    }


    /**
     * 用RxJava添加订阅者
     * @param subscriberMethod
     */
    public  void addSubscriber(final SubscriberMethod subscriberMethod){
        Observable observable ;
        if(subscriberMethod.code == -1){
            observable = toObservable(subscriberMethod.eventType);
        }else{
            observable = toObservable(subscriberMethod.code,subscriberMethod.eventType);
        }

        Subscription subscription = postToObservable(observable,subscriberMethod)
        .subscribe(new Action1<Object>() {
            @Override
            public void call(Object o) {
                callEvent(subscriberMethod.code,o);
            }
        });
        addSubscriptionToMap(subscriberMethod.eventType ,subscription);
    }


    /**
     * 用于处理订阅事件在那个线程中执行
     * @param observable
     * @param subscriberMethod
     * @return
     */
    private Observable postToObservable(Observable observable, SubscriberMethod subscriberMethod) {

        switch (subscriberMethod.threadMode) {
            case MAIN:
                observable.observeOn(AndroidSchedulers.mainThread());
                break;

            case NEW_THREAD:
                observable.observeOn(Schedulers.newThread());
                break;
            case CURRENT_THREAD:
                observable.observeOn(Schedulers.immediate());
                break;
            default:
                throw new IllegalStateException("Unknown thread mode: " + subscriberMethod.threadMode);
        }
        return observable;
    }


    /**
     * 回调到订阅者的方法中
     * @param code code
     * @param object obj
     */
    private void callEvent(int code, Object object){
        Class eventClass = object.getClass();
        List<SubscriberMethod> methods = subscriberMethodByEventType.get(eventClass);
        if(methods != null && methods.size() > 0){
            for(SubscriberMethod subscriberMethod : methods){

                Subscribe sub = subscriberMethod.method.getAnnotation(Subscribe.class);
                int c = sub.code();
                if(c == code){
                    subscriberMethod.invoke(object);
                }

            }
        }
    }


    /**
     * 取消注册
     * @param subscriber
     */
    public void unRegister(Object subscriber){
        List<Class> subscribedTypes = eventTypesBySubscriber.get(subscriber);
        if (subscribedTypes != null) {
            for (Class<?> eventType : subscribedTypes) {
                unSubscribeByEventType(eventType);
                unSubscribeMethodByEventType(subscriber,eventType);
            }
            eventTypesBySubscriber.remove(subscriber);
        }
    }


    /**
     * subscriptions unsubscribe
     * @param eventType
     */
    private void unSubscribeByEventType(Class eventType){
        List<Subscription> subscriptions = subscriptionsByEventType.get(eventType);
        if (subscriptions != null) {
            Iterator<Subscription> iterator = subscriptions.iterator();
            while(iterator.hasNext()){
                Subscription subscription = iterator.next();
                if(subscription !=null && !subscription.isUnsubscribed()){
                    subscription.unsubscribe();
                    iterator.remove();
                }
            }
        }
    }

    /**
     * 移除subscriber对应的subscriberMethods
     * @param subscriber
     * @param eventType
     */
    private void unSubscribeMethodByEventType(Object subscriber, Class eventType){
        List<SubscriberMethod> subscriberMethods = subscriberMethodByEventType.get(eventType);
        if(subscriberMethods != null){
            Iterator<SubscriberMethod> iterator = subscriberMethods.iterator();
            while (iterator.hasNext()){
                SubscriberMethod subscriberMethod = iterator.next();
                if(subscriberMethod.subscriber.equals(subscriber)){
                    iterator.remove();
                }
            }
        }
    }

    public class SubscriberMethod {
        public Method method;
        public ThreadMode threadMode;
        public Class<?> eventType;
        public Object subscriber;
        public int code;

        public SubscriberMethod(Object subscriber, Method method, Class<?> eventType, int code,ThreadMode threadMode) {
            this.method = method;
            this.threadMode = threadMode;
            this.eventType = eventType;
            this.subscriber = subscriber;
            this.code = code;
        }


        /**
         * 调用方法
         * @param o 参数
         */
        public void invoke(Object o){
            try {
                method.invoke(subscriber, o);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }

    }
    public enum  ThreadMode {

        /**
         * current thread
         */
        CURRENT_THREAD,

        /**
         * android main thread
         */
        MAIN,


        /**
         * new thread
         */
        NEW_THREAD

    }


    @Documented
    @Target(ElementType.METHOD)
    @Retention(RetentionPolicy.RUNTIME)
    public @interface Subscribe {
        int code() default -1;
        ThreadMode threadMode() default ThreadMode.CURRENT_THREAD;
    }

    public class Message {
        private int code;
        private Object object;

        public Message() {

        }

        public Message(int code, Object o) {
            this.code = code;
            this.object = o;
        }

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        public Object getObject() {
            return object;
        }

        public void setObject(Object object) {
            this.object = object;
        }
    }
}
