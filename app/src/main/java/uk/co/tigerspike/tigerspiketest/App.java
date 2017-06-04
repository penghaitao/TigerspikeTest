package uk.co.tigerspike.tigerspiketest;

import android.app.Application;
import android.content.Context;

import uk.co.tigerspike.tigerspiketest.data.DaggerNetComponent;
import uk.co.tigerspike.tigerspiketest.data.NetComponent;
import uk.co.tigerspike.tigerspiketest.data.NetModule;
import uk.co.tigerspike.tigerspiketest.data.remote.FlickrServiceApiEndpoint;

/**
 * Created by haitao on 01/06/2017.
 */

public class App extends Application {
    private NetComponent mNetComponent;
    public static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
        mNetComponent = DaggerNetComponent.builder()
                .appModule(new AppModule(this))
                .netModule(new NetModule(FlickrServiceApiEndpoint.FLICKR_BASE_URL))
                .build();
    }

    public NetComponent getNetComponent() {
        return mNetComponent;
    }
}
