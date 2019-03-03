package soexample.bigfly.com.lvfei20190302.mvp.ipresenter;

import java.util.Map;

import soexample.bigfly.com.lvfei20190302.mvp.iview.IView;
import soexample.bigfly.com.lvfei20190302.mvp.moudle.MyMoudleImpl;
import soexample.bigfly.com.lvfei20190302.mvp.mycallback.MyCallBack;

public class IPresenterImpl implements IPresenter {
    private IView iView;
    private MyMoudleImpl myMoudle;

    public IPresenterImpl(IView iView) {
        this.iView = iView;
        myMoudle = new MyMoudleImpl();
    }

    @Override
    public void startRequest(String url, Map<String, Object> headMap, Map<String, Object> map, Class clazz) {
        myMoudle.startRequest(url, headMap, map, clazz, new MyCallBack() {
            @Override
            public void setData(Object data) {
                iView.success(data);
            }

            @Override
            public void setError(Object error) {
                iView.error(error);
            }
        });
    }

    @Override
    public void startOk(String url, Map<String, Object> headMap, Map<String, Object> map, Class clazz) {

    }


    //防止内存泄漏
    public void onDatach() {
        if (myMoudle != null) {
            myMoudle = null;
        }
        if (iView != null) {
            iView = null;
        }
    }
}
