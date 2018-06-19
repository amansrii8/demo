package com.example.dell.demoforarrk.request;

import com.example.dell.demoforarrk.model.StarWarDetailResponseModel;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

public class StarApiRequest {

    public interface StarWarApiService {
        @GET("api/people/?")
        Observable<StarWarDetailResponseModel> fetchStarWar(@Query("page") int page);
    }
}
