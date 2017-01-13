package fi.solita.helloretrofit.dependency.items;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * Created by eetupa on 01/09/16.
 */
@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface ItemsActivityScope {
}
