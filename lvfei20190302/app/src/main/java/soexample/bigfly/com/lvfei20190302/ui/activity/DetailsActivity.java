package soexample.bigfly.com.lvfei20190302.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebSettings;
import android.webkit.WebView;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import soexample.bigfly.com.lvfei20190302.R;
import soexample.bigfly.com.lvfei20190302.bean.MyCaseData;
import soexample.bigfly.com.lvfei20190302.mvp.ipresenter.IPresenterImpl;
import soexample.bigfly.com.lvfei20190302.mvp.iview.IView;
import soexample.bigfly.com.lvfei20190302.utils.Constans;

public class DetailsActivity extends AppCompatActivity implements IView {
    @BindView(R.id.My_WebView)
    WebView MyWebView;
    private int zhi;
    private IPresenterImpl iPresenter;
    private Map<String, Object> headMap = new HashMap<>();
    private Map<String, Object> map = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        zhi = intent.getIntExtra("zhi", 0);
        iPresenter = new IPresenterImpl(this);
        map.put("commodityId", zhi);
        iPresenter.startRequest(Constans.XIANGQING, headMap, map, MyCaseData.class);
    }

    @Override
    public void success(Object data) {
        MyCaseData caseData = (MyCaseData) data;
        WebSettings settings = MyWebView.getSettings();
        settings.setJavaScriptEnabled(true);//支持JS
        String js = "<script type=\"text/javascript\">" +
                "var imgs = document.getElementsByTagName('img');" + // 找到img标签
                "for(var i = 0; i<imgs.length; i++){" +  // 逐个改变
                "imgs[i].style.width = '100%';" +  // 宽度改为100%
                "imgs[i].style.height = 'auto';" +
                "}" +
                "</script>";
        MyWebView.loadDataWithBaseURL(null, caseData.getResult().getDetails() + js, "text/html", "utf-8", null);
    }

    @Override
    public void error(Object error) {

    }
}
