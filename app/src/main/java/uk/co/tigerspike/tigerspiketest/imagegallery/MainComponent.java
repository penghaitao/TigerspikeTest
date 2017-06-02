package uk.co.tigerspike.tigerspiketest.imagegallery;

import dagger.Component;
import uk.co.tigerspike.tigerspiketest.data.NetComponent;
import uk.co.tigerspike.tigerspiketest.util.CustomScope;

/**
 * Created by haitao on 01/06/2017.
 */
@CustomScope
@Component(dependencies = NetComponent.class, modules = MainModule.class)
public interface MainComponent {
    void inject(MainActivity activity);

}
