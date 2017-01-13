package fi.solita.helloretrofit.utils;

import java.io.IOException;

import fi.solita.helloretrofit.models.items.ItemHolder;
import okhttp3.MediaType;
import okhttp3.ResponseBody;
import retrofit2.Response;
import retrofit2.adapter.rxjava.HttpException;
import rx.Observable;

/**
 * Created by eetupa on 06/09/16.
 */
public class ExceptionUtils {

    public static Observable<ItemHolder> get400BadRequestError() {
        return Observable.error(new HttpException(
                Response.error(400, ResponseBody.create(MediaType.parse("application/json"), "Bad request"))));

    }

    public static Observable getIOExceptionError() {
        return Observable.error(new IOException());
    }
}
