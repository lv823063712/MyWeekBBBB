package soexample.bigfly.com.lvfei20190302.mvp.ipresenter;

import java.util.Map;

public interface IPresenter {
    void startRequest(String url, Map<String,Object> headMap, Map<String,Object>map, Class clazz);
    void startOk(String url,Map<String,Object>headMap, Map<String,Object>map, Class clazz);
}
