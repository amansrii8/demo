package com.example.dell.demoforarrk.presenter;

import com.example.dell.demoforarrk.model.StarWarDetailResponseModel;
import com.example.dell.demoforarrk.request.StarApiRequest;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

import static com.example.dell.demoforarrk.constants.Constants.BASEURL;

public class StarApiPresenter {

    public void getStarWarCharacterDetails(final StarWarInterface presenter, int page) {

        Retrofit retrofit = new Retrofit.Builder()
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASEURL)
                .build();

        StarApiRequest.StarWarApiService  apiService = retrofit.create(StarApiRequest.StarWarApiService.class);
        Observable<StarWarDetailResponseModel> apiObservable = apiService.fetchStarWar(page);

        Observer<StarWarDetailResponseModel> apiServiceObserver = new Observer<StarWarDetailResponseModel>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                presenter.onErrorStarWarDetails();
            }

            @Override
            public void onNext(StarWarDetailResponseModel starWarDetailResponseModel) {
                presenter.onSuccessStarWarDetails(starWarDetailResponseModel);
            }
        };

        apiObservable.subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread()).subscribe(apiServiceObserver);
    }

    public interface StarWarInterface {
        void onSuccessStarWarDetails(StarWarDetailResponseModel starWarDetailResponseModel);
        void onErrorStarWarDetails();
    }
}
