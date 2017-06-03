package uk.co.tigerspike.tigerspiketest.imagegallery;

import java.util.List;

import uk.co.tigerspike.tigerspiketest.data.model.Image;

/**
 * Created by haitao on 01/06/2017.
 */

public interface MainContract {

    interface View {

        void showOrderPopupMenu();

        void setLoadingIndicator(boolean active);

        void showImageList(List<Image> imageList);

        void showError();
    }

    interface Presenter {
        void loadImageList();

        void orderImageList(List<Image> images, String order_type);
    }
}
