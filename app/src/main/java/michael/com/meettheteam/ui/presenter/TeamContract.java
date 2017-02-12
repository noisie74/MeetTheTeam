package michael.com.meettheteam.ui.presenter;

import michael.com.meettheteam.model.Response;

/**
 * Created by Mikhail on 2/11/17.
 */

public interface TeamContract {

    interface View{
        void showLoading();
        void hideLoading();
        void onLoadingFailed(String error);
        void showContacts(Response response);
    }

    interface UserActionListener{
        void onClickContact(int position);
    }
}
