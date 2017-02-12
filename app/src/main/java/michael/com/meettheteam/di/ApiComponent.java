package michael.com.meettheteam.di;

import javax.inject.Singleton;

import dagger.Component;
import michael.com.meettheteam.ui.MainActivity;

/**
 * Created by Mikhail on 2/11/17.
 */

@Singleton
@Component (modules = ApiModule.class)
public interface ApiComponent {

    void inject(MainActivity mainActivity);
}
