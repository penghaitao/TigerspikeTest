package uk.co.tigerspike.tigerspiketest.imagegallery;

import java.util.List;

import uk.co.tigerspike.tigerspiketest.data.model.Image;

/**
 * Created by haitao on 01/06/2017.
 */

public interface MainContract {

    interface View {

        void setLoadingIndicator(boolean active);

        void showImageList(List<Image> imageList);
    }

    interface Presenter {
        void loadImageList();
    }
}
