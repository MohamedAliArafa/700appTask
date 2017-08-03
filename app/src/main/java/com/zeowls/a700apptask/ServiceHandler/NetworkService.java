package com.zeowls.a700apptask.ServiceHandler;

import android.support.annotation.NonNull;
import android.support.v4.util.LruCache;

import com.zeowls.a700apptask.DataModel.RequestModel;
import com.zeowls.a700apptask.DataModel.Result;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.POST;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/*
 * Created by root on 8/2/17.
 */

public class NetworkService {

    private static String baseUrl = "http://108.179.204.213:8098/";
    private NetworkAPI networkAPI;
    private LruCache<Class<?>, Observable<?>> apiObservables;

    public NetworkService() {
        this(baseUrl);
    }

    private NetworkService(String baseUrl) {
        apiObservables = new LruCache<>(10);

        OkHttpClient okHttpClient = new OkHttpClient().newBuilder()
                .connectTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS)
                .build();

        OkHttpClient.Builder client = new OkHttpClient.Builder();
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        client.addInterceptor(loggingInterceptor);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();

        networkAPI = retrofit.create(NetworkAPI.class);
    }

    /**
     * Method to return the API interface.
     *
     * @return NetworkAPI
     */
    public NetworkAPI getAPI() {
        return networkAPI;
    }


    /**
     * Method to build and return an OkHttpClient so we can set/get
     * headers quickly and efficiently.
     *
     * @return OkHttpClient
     */
    public OkHttpClient buildClient() {

        OkHttpClient.Builder builder = new OkHttpClient.Builder();

        builder.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(@NonNull Chain chain) throws IOException {
                // Do anything with response here
                //if we ant to grab a specific cookie or something..
                return chain.proceed(chain.request());
            }
        });

        builder.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(@NonNull Chain chain) throws IOException {
                //this is where we will add whatever we want to our request headers.
                Request request = chain.request().newBuilder().addHeader("Accept", "application/json").build();
                return chain.proceed(request);
            }
        });

        return builder.build();
    }

    /**
     * Method to clear the entire cache of observables
     */
    public void clearCache() {
        apiObservables.evictAll();
    }


    /**
     * Method to either return a cached observable or prepare a new one.
     *
     * @param unPreparedObservable
     * @param clazz
     * @param cacheObservable
     * @param useCache
     * @return Observable ready to be subscribed to
     */
    public Observable<?> getPreparedObservable(Observable<?> unPreparedObservable, Class<?> clazz, boolean cacheObservable, boolean useCache) {

        Observable<?> preparedObservable = null;

        if (useCache)//this way we don't reset anything in the cache if this is the only instance of us not wanting to use it.
            preparedObservable = apiObservables.get(clazz);

        if (preparedObservable != null)
            return preparedObservable;


        //we are here because we have never created this observable before or we didn't want to use the cache...

        preparedObservable = unPreparedObservable.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread());

        if (cacheObservable) {
            preparedObservable = preparedObservable.cache();
            apiObservables.put(clazz, preparedObservable);
        }


        return preparedObservable;
    }


    /**
     * all the Service alls to use for the retrofit requests.
     */
    public interface NetworkAPI {

        String GetAllCategory = "api/Category/GetAllCategory";

        @POST(GetAllCategory)
        Observable<Result> getCategories(@Body RequestModel requestModel);
    }

}
