package com.alisir.moduledemo02;

import android.content.Context;
import android.util.Log;

import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.facade.annotation.Interceptor;
import com.alibaba.android.arouter.facade.callback.InterceptorCallback;
import com.alibaba.android.arouter.facade.template.IInterceptor;
import com.alisir.base.BaseContent;

/**
 * Created by ALiSir on 17/5/15.
 */
@Interceptor(priority = 8,name = "登录验证")
public class LoginInterceptor implements IInterceptor {
    String TAG = "拦截器，拦截优先级：8";

    @Override
    public void process(Postcard postcard, InterceptorCallback callback) {
        Log.i(TAG, "process: 拦截一下");

        if("/main/lgsu".equals(postcard.getPath())){
            if(BaseContent.isLogin){
                callback.onContinue(postcard);
            }else{
                callback.onInterrupt(new Throwable("我就是想栏你啊！"));
            }
        }else{
            callback.onContinue(postcard);
        }


    }


    @Override
    public void init(Context context) {

    }
}
