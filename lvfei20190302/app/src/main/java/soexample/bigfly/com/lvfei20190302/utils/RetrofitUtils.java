package soexample.bigfly.com.lvfei20190302.utils;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * <p>文件描述：<p>
 * <p>作者：${吕飞}<p>
 * <p>创建时间：2019/3/2   15:45<p>
 * <p>更改时间：2019/3/2   15:45<p>
 * <p>版本号：1<p>
 */

public class RetrofitUtils {

    private final ApiService apiService;

    private RetrofitUtils(){
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .readTimeout(20,TimeUnit.SECONDS)
                .writeTimeout(20,TimeUnit.SECONDS)
                .connectTimeout(20,TimeUnit.SECONDS)
                .retryOnConnectionFailure(true)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(Constans.BASE_URL)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        apiService = retrofit.create(ApiService.class);
    }

    //单例模式
    public static RetrofitUtils getInstance(){
        return ViewHolderr.RETROFIT_UTILS;
    }

    private static class ViewHolderr{
        private static final RetrofitUtils RETROFIT_UTILS = new RetrofitUtils();
    }

    //封装get方法
    public void get(String url, Map<String,Object>headMap, Map<String,Object>map, final setMyHttpListener listener){
        apiService.get(url,headMap,map).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResponseBody>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        if (listener!=null){
                            listener.error(e.getMessage());
                        }
                    }

                    @Override
                    public void onNext(ResponseBody responseBody) {
                        if (listener!=null){
                            try {
                                listener.success(responseBody.string());
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }

                    }
                });
    }




    //接口UI掉
    public interface setMyHttpListener<T>{
        void success(T data);
        void error(T error);
    }

}
