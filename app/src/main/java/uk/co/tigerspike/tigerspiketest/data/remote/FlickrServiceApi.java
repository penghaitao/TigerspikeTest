package uk.co.tigerspike.tigerspiketest.data.remote;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import uk.co.tigerspike.tigerspiketest.data.model.Flickr;

/**
 * Created by haitao on 01/06/2017.
 */

public interface FlickrServiceApi {

    @GET("feeds/photos_public.gne?format=json&nojsoncallback=1")
    Call<Flickr> getFlickr();

    @GET("feeds/photos_public.gne?format=json&nojsoncallback=1")
    Call<Flickr> getFlickrByTag(@Query("tags") String tag);
}
