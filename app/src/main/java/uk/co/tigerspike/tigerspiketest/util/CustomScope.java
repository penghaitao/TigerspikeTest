package uk.co.tigerspike.tigerspiketest.util;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * Created by haitao on 01/06/2017.
 */

@Documented
@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface CustomScope {
}
