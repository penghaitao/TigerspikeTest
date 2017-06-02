package uk.co.tigerspike.tigerspiketest.imagegallery;

import dagger.Module;
import dagger.Provides;
import uk.co.tigerspike.tigerspiketest.util.CustomScope;

/**
 * Created by haitao on 01/06/2017.
 */

@Module
public class MainModule {

    private final MainContract.View mView;

    public MainModule(MainContract.View mView) {
        this.mView = mView;
    }

    @Provides
    public MainContract.View providesMainContractView() {
        return mView;
    }
}
