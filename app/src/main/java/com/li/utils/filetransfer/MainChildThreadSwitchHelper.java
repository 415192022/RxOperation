package com.li.utils.filetransfer;


import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import java.util.concurrent.ExecutorService;

/**
 * Created by Mingwei Li on 2017/1/4 0004.
 */

public class MainChildThreadSwitchHelper {
    private OnSwitchMainChlidThread onSwitchMainChlidThread;

    public static MainChildThreadSwitchHelper newInstance() {
        return new MainChildThreadSwitchHelper();
    }

    public void runOnMainThread(OnSwitchMainChlidThread onSwitchMainChlidThread) {
        this.onSwitchMainChlidThread = onSwitchMainChlidThread;
        CustomBlockingThreadPoolExecutor customBlockingThreadPoolExecutor = new CustomBlockingThreadPoolExecutor();
        customBlockingThreadPoolExecutor.init();
        ExecutorService executorService = customBlockingThreadPoolExecutor.getCustomThreadPoolExecutor();
        executorService.execute(new MainThread());
    }

    public void runOnChildThread(OnSwitchMainChlidThread onSwitchMainChlidThread) {
        this.onSwitchMainChlidThread = onSwitchMainChlidThread;
        CustomBlockingThreadPoolExecutor customBlockingThreadPoolExecutor = new CustomBlockingThreadPoolExecutor();
        customBlockingThreadPoolExecutor.init();
        ExecutorService executorService = customBlockingThreadPoolExecutor.getCustomThreadPoolExecutor();
        executorService.execute(new ChildThread());
    }

    class MainThread implements Runnable {
        @Override
        public void run() {
            Looper looper = Looper.getMainLooper();
            Looper.prepare();
            MainHandler mainHandler = new MainHandler(looper);
            Message message = new Message();
            mainHandler.sendMessage(message);
            Looper.loop();
        }
    }

    class ChildThread implements Runnable {
        @Override
        public void run() {
            if (null != onSwitchMainChlidThread) {
                onSwitchMainChlidThread.runOn();
            }
//            Looper looper = Looper.myLooper();
//            ChildHandler childHandler = new ChildHandler(looper);
//            Message message = new Message();
//            childHandler.sendMessage(message);
//            Looper.loop();
        }
    }

    class MainHandler extends Handler {
        private MainHandler(Looper looper) {
            super(looper);
            if (null != onSwitchMainChlidThread) {
                onSwitchMainChlidThread.runOn();
            }
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (null != onSwitchMainChlidThread) {
                onSwitchMainChlidThread.runOn();
            }
        }
    }

    class ChildHandler extends Handler {
        private ChildHandler(Looper looper) {
            super(looper);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (null != onSwitchMainChlidThread) {
                onSwitchMainChlidThread.runOn();
            }
        }
    }

    public interface OnSwitchMainChlidThread {
        void runOn();
    }
}
