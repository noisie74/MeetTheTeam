package michael.com.meettheteam.ui.presenter;

import java.util.List;

import michael.com.meettheteam.model.Contacts;

/**
 * Contract
 */

public interface TeamContract {

    interface View {
        void showLoading();

        void hideLoading();

        void onLoadingFailed(String error);

        void showContacts(List<Contacts> contactsList);

//        void hideRecyclerView();
    }

}
