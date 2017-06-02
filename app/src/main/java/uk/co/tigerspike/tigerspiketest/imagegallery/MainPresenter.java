package uk.co.tigerspike.tigerspiketest.imagegallery;

import android.support.annotation.NonNull;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import uk.co.tigerspike.tigerspiketest.data.model.Flickr;
import uk.co.tigerspike.tigerspiketest.data.remote.FlickrServiceApi;

/**
 * Created by haitao on 01/06/2017.
 */

public class MainPresenter implements MainContract.Presenter {

    private final MainContract.View mMainView;
    private final Retrofit mRetrofit;

    @Inject
    public MainPresenter(MainContract.View mainView, Retrofit retrofit) {
        this.mMainView = mainView;
        this.mRetrofit = retrofit;
    }

    @Override
    public void loadImageList() {
        Call<Flickr> call = mRetrofit.create(FlickrServiceApi.class).getFlickr();
        call.enqueue(new Callback<Flickr>() {
            @Override
            public void onResponse(@NonNull Call<Flickr> call, @NonNull Response<Flickr> response) {
                mMainView.showImageList(response.body().getImages());
            }

            @Override
            public void onFailure(@NonNull Call<Flickr> call, @NonNull Throwable t) {

            }
        });
    }
}
