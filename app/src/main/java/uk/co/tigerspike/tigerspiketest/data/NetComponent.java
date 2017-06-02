package uk.co.tigerspike.tigerspiketest.data;

import javax.inject.Singleton;

import dagger.Component;
import retrofit2.Retrofit;
import uk.co.tigerspike.tigerspiketest.AppModule;

/**
 * Created by haitao on 01/06/2017.
 */

@Singleton
@Component(modules = {AppModule.class, NetModule.class})
public interface NetComponent {

    Retrofit retrofit();

}
