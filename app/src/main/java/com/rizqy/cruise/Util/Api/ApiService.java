package com.rizqy.cruise.Util.Api;

import com.rizqy.cruise.Model.DestinationDetailResponse;
import com.rizqy.cruise.Model.DestinationResponse;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ApiService {
//    @GET("destination")
//    Call<DestinationResponse> getDestination();

    @GET("destination/search/{ship}")
    Call<DestinationResponse> getDestination(@Path("ship") String ship);

    @GET("destination/{id)")
    Call<DestinationDetailResponse> getDestinationDetail(@Path( "id" )String id);

    @FormUrlEncoded
    @POST("destination")
    Call<ResponseBody> postDestination(@Field("photo") String photo,
                                       @Field("price") String price,
                                       @Field("ship") String ship,
                                       @Field("title") String title,
                                       @Field("date") String date,
                                       @Field("visiting") String visiting);

    @FormUrlEncoded
    @PUT("destination/{id}")
    Call<ResponseBody> putDestination(@Path( "id" )String id,
                                      @Field("photo") String photo,
                                      @Field("price") String price,
                                      @Field("ship") String ship,
                                      @Field("title") String title,
                                      @Field("date") String date,
                                      @Field("visiting") String visiting);

    @DELETE("destination/{id}")
    Call<ResponseBody> deleteDestination(@Path("id") String id);
}
