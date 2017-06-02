package uk.co.tigerspike.tigerspiketest.imagedetail;

import dagger.Module;
import dagger.Provides;
import uk.co.tigerspike.tigerspiketest.data.model.Image;
import uk.co.tigerspike.tigerspiketest.imagegallery.MainContract;
import uk.co.tigerspike.tigerspiketest.util.CustomScope;

/**
 * Created by haitao on 02/06/2017.
 */

@Module
public class ImageDetailModule {

    private final ImageDetailContract.View mView;
    private final Image mImage;

    public ImageDetailModule(ImageDetailContract.View view, Image image) {
        this.mView = view;
        this.mImage = image;
    }

    @Provides
    public ImageDetailContract.View providesDetailContractView() {
        return mView;
    }

    @Provides
    public Image providesImage() {return mImage;}
}
