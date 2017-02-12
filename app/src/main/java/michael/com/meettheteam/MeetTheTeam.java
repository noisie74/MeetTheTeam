package michael.com.meettheteam;

import android.app.Application;

import michael.com.meettheteam.di.ApiComponent;
import michael.com.meettheteam.di.ApiModule;
import michael.com.meettheteam.di.DaggerApiComponent;
import timber.log.Timber;

/**
 * Created by Mikhail on 2/11/17.
 */

public class MeetTheTeam extends Application {

    ApiComponent component;

    @Override
    public void onCreate() {
        super.onCreate();

        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        }

        component = DaggerApiComponent.builder().apiModule(new ApiModule(this)).build();

    }

    public ApiComponent getComponent(){
        return component;
    }

}
