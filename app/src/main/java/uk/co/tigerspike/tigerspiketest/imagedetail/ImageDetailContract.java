package uk.co.tigerspike.tigerspiketest.imagedetail;

import uk.co.tigerspike.tigerspiketest.data.model.Image;

/**
 * Created by haitao on 02/06/2017.
 */

public interface ImageDetailContract {

    interface View {
//        void showImage(String imageUrl);
        void showDetail(Image image);
    }

    interface Presenter {
        void showImage();
        void showDetail();
    }
}
