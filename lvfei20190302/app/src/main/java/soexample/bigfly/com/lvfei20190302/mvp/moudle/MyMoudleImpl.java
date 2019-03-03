package soexample.bigfly.com.lvfei20190302.mvp.moudle;

import com.google.gson.Gson;

import java.util.Map;

import soexample.bigfly.com.lvfei20190302.mvp.mycallback.MyCallBack;
import soexample.bigfly.com.lvfei20190302.utils.RetrofitUtils;

public class MyMoudleImpl implements MyMoudle {
    @Override
    public void startRequest(String url, Map<String, Object> headMap, Map<String, Object> map, final Class clazz, final MyCallBack myCallBack) {
        RetrofitUtils.getInstance().get(url, headMap, map, new RetrofitUtils.setMyHttpListener() {
            @Override
            public void success(Object data) {
                Gson gson = new Gson();
                Object o = gson.fromJson((String) data, clazz);
                myCallBack.setData(o);
            }

            @Override
            public void error(Object error) {
                myCallBack.setError(error);
            }
        });
    }

    @Override
    public void startOk(String url, Map<String, Object> headMap, Map<String, Object> map, Class clazz, MyCallBack myCallBack) {

    }
}
