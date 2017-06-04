package uk.co.tigerspike.tigerspiketest.imagedetail;

import dagger.Component;
import uk.co.tigerspike.tigerspiketest.util.CustomScope;

/**
 * Created by haitao on 02/06/2017.
 */

@CustomScope
@Component(modules = ImageDetailModule.class)
public interface ImageDetailComponent {
    void inject(ImageDetailActivity activity);

}
