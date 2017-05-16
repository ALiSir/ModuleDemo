package com.alisir.base.net;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import retrofit2.http.Url;
import rx.Observable;

/**
 * Created by ALiSir on 17/5/16.
 */

public interface NetUtilServer {

    @FormUrlEncoded
    @POST
    Call<HashMap<String,Object>> postRequest(@FieldMap Map<String,String> data, @Url String url);

    @FormUrlEncoded
    @POST
    Observable<HashMap<String,Object>> postRequestRx(@FieldMap Map<String,String> data, @Url String url);
}
