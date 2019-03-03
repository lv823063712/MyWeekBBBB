package soexample.bigfly.com.lvfei20190302.ui.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import soexample.bigfly.com.lvfei20190302.R;
import soexample.bigfly.com.lvfei20190302.ui.adapter.MyPzssAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class ConsultFragment extends Fragment {

    @BindView(R.id.MyWeb)
    WebView MyWeb;
    Unbinder unbinder;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View inflate = inflater.inflate(R.layout.fragment_consult, null);
        unbinder = ButterKnife.bind(this, inflate);

        //加载本地文件
        MyWeb.loadUrl("file:////android_asset/news.html");
        WebSettings settings = MyWeb.getSettings();
        settings.setJavaScriptEnabled(true);
        //设置WebViewClient客户端
        MyWeb.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
        return inflate;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
