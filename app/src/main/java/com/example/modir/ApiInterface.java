package com.example.modir;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;


public interface ApiInterface {


    @FormUrlEncoded
    @POST("confirm_rest.php")
    Call<StatusModel> Api_confirm_rest(@Field("action") String action,
                                       @Field("id") String id
    );


    @GET("show_rest.php")
    Call<List<Rest>> Api_get_rests();


    @GET("show_users.php")
    Call<List<User>> Api_get_users();


//    @FormUrlEncoded
//    @POST("get_city")
//    Call<List<Province>> Api_getCity(
//            @Field("mikhay") String mikhay
//    );

}
