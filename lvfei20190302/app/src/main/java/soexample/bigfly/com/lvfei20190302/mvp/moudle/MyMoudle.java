package soexample.bigfly.com.lvfei20190302.mvp.moudle;
import java.util.Map;

import soexample.bigfly.com.lvfei20190302.mvp.mycallback.MyCallBack;

/**
 * <p>文件描述：<p>
 * <p>作者：${吕飞}<p>
 * <p>创建时间：2019/2/23   18:13<p>
 * <p>更改时间：2019/2/23   18:13<p>
 * <p>版本号：1<p>
 */

public interface MyMoudle {
    void startRequest(String url, Map<String,Object> headMap, Map<String,Object>map, Class clazz, MyCallBack myCallBack);
    void startOk(String url, Map<String,Object>headMap, Map<String,Object>map,Class clazz, MyCallBack myCallBack);
}
