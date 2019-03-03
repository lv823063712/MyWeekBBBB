package soexample.bigfly.com.lvfei20190302.mvp.mycallback;

public interface MyCallBack<T> {
    void setData(T data);

    void setError(T error);
}
