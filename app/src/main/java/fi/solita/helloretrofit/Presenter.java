package fi.solita.helloretrofit;

/**
 * Created by eetupa on 30/08/16.
 */

/**
 * Every presenter in the app must either implement this interface or extend BasePresenter (BasePresenter not implemented)
 * indicating the MvpView type that wants to be attached with.
 */

public interface Presenter<V extends MVPView> {
    void attachView(V mvpView);
    void detachView();
}
