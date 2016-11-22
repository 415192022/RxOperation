package com.li.pro.apiservice.home;

import com.li.pro.bean.home.BeanHomeBase;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by Administrator on 2016/11/15 0015.
 */

public interface IFragmentCExResourceApiService {
    @GET("拓展资源/{number}/{page}")
    Observable<BeanHomeBase> getFragmentCExResource(@Path("number") int count, @Path("page") int page);
}
