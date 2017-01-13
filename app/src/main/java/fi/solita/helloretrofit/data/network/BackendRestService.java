package fi.solita.helloretrofit.data.network;

import fi.solita.helloretrofit.models.items.ItemHolder;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

public interface BackendRestService {
    String BASE_URL = "https://qovf678mx0.execute-api.eu-west-1.amazonaws.com/prod/";

    @GET("items")
    Observable<ItemHolder> getItems(@Query("serial") String serial);
}
