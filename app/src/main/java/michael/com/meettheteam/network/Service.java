package michael.com.meettheteam.network;

import java.util.List;

import michael.com.meettheteam.model.Contacts;
import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * API Service
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
                .onErrorResumeNext(Observable::error)
                .subscribe(new Subscriber<List<Contacts>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        callback.onError(e.toString());


                    }

                    @Override
                    public void onNext(List<Contacts> list) {
                        callback.onSuccess(list);

                    }
                });
    }

    public interface GetContactsCallBack {
        void onSuccess(List<Contacts> teamContactResponse);
        void onError(String networkError);
    }
}
