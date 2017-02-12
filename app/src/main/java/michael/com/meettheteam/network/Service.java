package michael.com.meettheteam.network;

import michael.com.meettheteam.model.Contacts;
import michael.com.meettheteam.model.Response;
import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;
import timber.log.Timber;

/**
 * Created by Mikhail on 2/11/17.
 */

public class Service {

    private final NetworkService mNetworkService;

    public Service(NetworkService networkService) {
        this.mNetworkService = networkService;
    }


    public Subscription getTeamContactList(final GetContactsCallBack callback) {

        return mNetworkService.getContacts()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .onErrorResumeNext(new Func1<Throwable, Observable<? extends Response>>() {
                    @Override
                    public Observable<? extends Response> call(Throwable throwable) {
                        return Observable.error(throwable);
                    }
                })
                .subscribe(new Subscriber<Response>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        callback.onError(e.toString());


                    }

                    @Override
                    public void onNext(Response teamContactList) {
                        callback.onSuccess(teamContactList);

                    }
                });
    }

    public interface GetContactsCallBack {
        void onSuccess(Response teamContactResponse);
        void onError(String networkError);
    }
}
