package michael.com.meettheteam.ui.presenter;

import java.util.List;

import michael.com.meettheteam.model.Contacts;
import michael.com.meettheteam.network.Service;
import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * Presenter
 */

public class TeamPresenter {

    private Service mService;
    private TeamContract.View mView;
    private CompositeSubscription mSubscription;

    public TeamPresenter(Service service, TeamContract.View view) {
        this.mService = service;
        this.mView = view;
        mSubscription = new CompositeSubscription();
    }


    public void getTeamContacts(Boolean isConnected) {
        mView.showLoading();

        Subscription subscription = mService.getTeamContactList(new Service.GetContactsCallBack() {

            @Override
            public void onSuccess(List<Contacts> teamContactResponse) {
                mView.hideLoading();
                mView.showContacts(teamContactResponse);
            }

            @Override
            public void onError(String networkError) {
                mView.hideLoading();
                mView.onLoadingFailed(networkError);
            }
        });

        mSubscription.add(subscription);

    }

}
