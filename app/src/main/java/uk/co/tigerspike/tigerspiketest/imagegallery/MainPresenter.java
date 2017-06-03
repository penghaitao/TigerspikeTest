package uk.co.tigerspike.tigerspiketest.imagegallery;

import android.support.annotation.NonNull;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import uk.co.tigerspike.tigerspiketest.data.model.Flickr;
import uk.co.tigerspike.tigerspiketest.data.model.Image;
import uk.co.tigerspike.tigerspiketest.data.remote.FlickrServiceApi;
import uk.co.tigerspike.tigerspiketest.util.Constants;

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

    @Override
    public void orderImageList(List<Image> images, String order_type) {
        if (Constants.ORDER_BY_PUBLISH_DATE == order_type) {
            Collections.sort(images, new Comparator<Image>() {
                @Override
                public int compare(Image o1, Image o2) {
                    return o1.getPublished().compareTo(o2.getPublished());
                }
            });
        } else {
            Collections.sort(images, new Comparator<Image>() {
                @Override
                public int compare(Image o1, Image o2) {
                    return o1.getDateTaken().compareTo(o2.getDateTaken());
                }
            });
        }
        mMainView.showImageList(images);
    }
}
