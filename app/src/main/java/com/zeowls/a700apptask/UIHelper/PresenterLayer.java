package com.zeowls.a700apptask.UIHelper;

import android.util.Log;

import com.zeowls.a700apptask.DataModel.RequestModel;
import com.zeowls.a700apptask.DataModel.Result;
import com.zeowls.a700apptask.Fragments.StoreFragment;
import com.zeowls.a700apptask.ServiceHandler.NetworkService;

import java.util.Arrays;

import rx.Observable;
import rx.Observer;
import rx.Subscription;

/**
 * Created by root on 8/2/17.
 */

public class PresenterLayer implements PresenterIntegrator {

    private NetworkService service;
    private Subscription subscription;

    public PresenterLayer(NetworkService service) {
        this.service = service;
    }

    public void loadStoreData(StoreFragment storeFragment) {
        final StoreFragment view = storeFragment;
        view.showRxInProcess();
        Observable<Result> categoryObserver = (Observable<Result>) service.getPreparedObservable(
                service.getAPI().getCategories(new RequestModel()), Result.class, true, true);
        subscription = categoryObserver.subscribe(new Observer<Result>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                Log.d("Error", e.toString());
                view.showRxFailure();
            }

            @Override
            public void onNext(Result response) {
                view.showRxResults(response);
                Log.d("Result", Arrays.toString(response.getItems().toArray()));
            }
        });
    }

    public void rxUnSubscribe() {
        if (subscription != null && !subscription.isUnsubscribed())
            subscription.unsubscribe();
    }
}
