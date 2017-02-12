package michael.com.meettheteam.ui.presenter;

import michael.com.meettheteam.model.Response;
import michael.com.meettheteam.network.Service;
import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by Mikhail on 2/11/17.
 */

public class TeamPresenter implements TeamContract.UserActionListener {

    private Service mService;
    private TeamContract.View mView;
    private CompositeSubscription mSubscription;

    TeamPresenter(Service service, TeamContract.View view) {
        this.mService = service;
        this.mView = view;
        mSubscription = new CompositeSubscription();
    }


    public void getTeamContacts(Boolean isConnected) {
        mView.showLoading();

        Subscription subscription = mService.getTeamContactList(new Service.GetContactsCallBack() {

            @Override
            public void onSuccess(Response teamContactResponse) {
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



    @Override
    public void onClickContact(int position) {

    }
}
