package com.alisir.base.net;

import android.support.annotation.MainThread;
import android.util.Log;

import com.alisir.base.BaseContent;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Scheduler;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by ALiSir on 17/5/16.
 */

public class NetUtil {
    String TAG = "网络请求：";
    static NetUtil netUtil;
    Retrofit retrofit;

    public static NetUtil init(){
        if (netUtil == null){
            netUtil = new NetUtil();
        }
        return netUtil;
    }

    private NetUtil(){
        retrofit = new Retrofit.Builder()
                .baseUrl(BaseContent.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
    }
    //     Api/login

    public void postRequest(Map<String,String> map){
        final String type = map.get("type");
        String url = map.get("url");
        NetUtilServer server = retrofit.create(NetUtilServer.class);
        Call<HashMap<String,Object>> request = server.postRequest(map,url);
        request.enqueue(new Callback<HashMap<String, Object>>() {
            @Override
            public void onResponse(Call<HashMap<String, Object>> call, Response<HashMap<String, Object>> response) {
                Log.i(TAG, "onResponse: 登录成功！");
            }

            @Override
            public void onFailure(Call<HashMap<String, Object>> call, Throwable t) {
                Log.i(TAG, "onFailure: 登录失败！");
                t.printStackTrace();
            }
        });
    }

    public void postRequetRx(Map<String,String> map,Subscriber<HashMap<String,Object>> sub){
        final String type = map.get("type");
        String url = map.get("url");
        NetUtilServer server = retrofit.create(NetUtilServer.class);
        server.postRequestRx(map,url)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(sub);
    }

}
