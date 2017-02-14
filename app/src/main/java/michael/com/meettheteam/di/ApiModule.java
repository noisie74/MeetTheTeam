package michael.com.meettheteam.di;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import michael.com.meettheteam.MeetTheTeam;
import michael.com.meettheteam.network.NetworkService;
import michael.com.meettheteam.network.Service;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import static michael.com.meettheteam.util.Constants.API_URL;


@Module
public class ApiModule {

    MeetTheTeam meetTheTeamApp;

    public ApiModule(MeetTheTeam meetTheTeamApp) {
        this.meetTheTeamApp = meetTheTeamApp;
    }

    @Provides
    MeetTheTeam provideMeetTheTeamApp() {
        return meetTheTeamApp;
    }

    @Provides
    @Singleton
    OkHttpClient okHttpClient() {
        return new OkHttpClient.Builder().build();
    }

    @Provides
    @Singleton
    Retrofit retrofit(OkHttpClient okHttpClient) {
        return new Retrofit.Builder()
                .baseUrl(API_URL)
                .client(okHttpClient)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    @Provides
    @Singleton
    NetworkService provideNetworkCall(Retrofit retrofit) {
        return retrofit.create(NetworkService.class);
    }

    @Provides
    @Singleton
    public Service providesService(
            NetworkService networkService) {
        return new Service(networkService);
    }
}
