package uk.co.tigerspike.tigerspiketest.imagedetail;

import android.content.Intent;
import android.net.Uri;

import javax.inject.Inject;

import uk.co.tigerspike.tigerspiketest.data.model.Image;

/**
 * Created by haitao on 02/06/2017.
 */

public class ImageDetailPresenter implements ImageDetailContract.Presenter {

    private final ImageDetailContract.View mView;
    private final Image mImage;

    @Inject
    public ImageDetailPresenter(ImageDetailContract.View mView, Image image) {
        this.mView = mView;
        this.mImage = image;
    }

    @Override
    public void showImage() {
    }

    @Override
    public void showDetail() {

    }

}
