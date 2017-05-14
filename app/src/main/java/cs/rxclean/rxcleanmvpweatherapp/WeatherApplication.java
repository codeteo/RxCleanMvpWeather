package cs.rxclean.rxcleanmvpweatherapp;

import android.app.Application;

import cs.rxclean.rxcleanmvpweatherapp.dagger.ApplicationComponent;
import cs.rxclean.rxcleanmvpweatherapp.dagger.ApplicationModule;
import cs.rxclean.rxcleanmvpweatherapp.dagger.DaggerApplicationComponent;
import cs.rxclean.rxcleanmvpweatherapp.dagger.DaggerNetworkComponent;
import cs.rxclean.rxcleanmvpweatherapp.dagger.NetworkComponent;
import cs.rxclean.rxcleanmvpweatherapp.dagger.NetworkModule;

/**
 * Application class builds and provides Dagger Components with Application scope.
 */

public class WeatherApplication extends Application {

    private static ApplicationComponent applicationComponent;
    private static NetworkComponent networkComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        applicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();

        networkComponent = DaggerNetworkComponent.builder()
                .applicationComponent(applicationComponent)
                .networkModule(new NetworkModule())
                .build();

    }

    public static ApplicationComponent getApplicationComponent() {
        return applicationComponent;
    }

    public static NetworkComponent getNetworkComponent() {
        return networkComponent;
    }

}