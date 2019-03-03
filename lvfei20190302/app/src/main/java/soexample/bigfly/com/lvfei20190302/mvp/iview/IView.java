package soexample.bigfly.com.lvfei20190302.mvp.iview;

public interface IView<T> {
    void success(T data);

    void error(T error);
}
