package uk.co.tigerspike.tigerspiketest.imagedetail;

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
        String media = mImage.getMedia().getM();
        mView.showImage(media.replace("_m.jpg", "_b.jpg"));
    }

    @Override
    public void showDetail() {

    }
}
