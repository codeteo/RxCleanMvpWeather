package cs.rxclean.rxcleanmvpweatherapp.dagger;

import com.google.gson.Gson;

import java.util.concurrent.TimeUnit;

import cs.rxclean.rxcleanmvpweatherapp.utils.BaseUrlInterceptor;
import cs.rxclean.rxcleanmvpweatherapp.utils.MainActivityScope;
import dagger.Module;
import dagger.Provides;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Dagger Module which provides network dependencies.
 */

@Module
public class NetworkModule {

    private static final int CONNECTION_TIMEOUT = 15;

    public NetworkModule() {
    }

    @Provides
    @MainActivityScope
    Gson providesGson() {
        return new Gson();
    }

    @Provides
    @MainActivityScope
    OkHttpClient providesOkHttpClient(BaseUrlInterceptor baseUrlInterceptor){

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        final OkHttpClient.Builder client = new OkHttpClient.Builder();
        client.retryOnConnectionFailure(true);
        client.addInterceptor(interceptor);
        client.addInterceptor(baseUrlInterceptor);
        client.connectTimeout(CONNECTION_TIMEOUT, TimeUnit.SECONDS);

        return client.build();
    }

    @Provides
    @MainActivityScope
    Retrofit providesRetrofit(HttpUrl baseUrl, OkHttpClient client) {
        return new Retrofit.Builder()
                .client(client)
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
    }

}