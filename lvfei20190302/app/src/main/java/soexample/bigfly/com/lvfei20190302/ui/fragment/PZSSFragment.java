package soexample.bigfly.com.lvfei20190302.ui.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.stx.xhb.xbanner.XBanner;
import com.stx.xhb.xbanner.transformers.Transformer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import soexample.bigfly.com.lvfei20190302.R;
import soexample.bigfly.com.lvfei20190302.bean.MyBannerData;
import soexample.bigfly.com.lvfei20190302.bean.MyContentData;
import soexample.bigfly.com.lvfei20190302.mvp.ipresenter.IPresenterImpl;
import soexample.bigfly.com.lvfei20190302.mvp.iview.IView;
import soexample.bigfly.com.lvfei20190302.ui.activity.DetailsActivity;
import soexample.bigfly.com.lvfei20190302.ui.adapter.MyMlssAdapter;
import soexample.bigfly.com.lvfei20190302.ui.adapter.MyPzssAdapter;
import soexample.bigfly.com.lvfei20190302.utils.Constans;

/**
 * A simple {@link Fragment} subclass.
 */
public class PZSSFragment extends Fragment implements IView {

    @BindView(R.id.PZSSxbanner)
    XBanner PZSSxbanner;
    @BindView(R.id.MyPZSS)
    RecyclerView MyPZSS;
    Unbinder unbinder;
    private IPresenterImpl iPresenter;
    private ArrayList<String> datas = new ArrayList<>();
    private ArrayList<MyContentData.ResultBean.PzshBean.CommodityListBeanX>lists = new ArrayList<>();
    private Map<String,Object> headMap = new HashMap<>();
    private Map<String,Object>map = new HashMap<>();
    private MyPzssAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View inflate = inflater.inflate(R.layout.fragment_pzs, null);
        unbinder = ButterKnife.bind(this, inflate);
        initData();
        proLogic();
        return inflate;
    }



    private void initData() {
        iPresenter = new IPresenterImpl(this);
        iPresenter.startRequest(Constans.BANNER_URL,headMap,map, MyBannerData.class);
        iPresenter.startRequest(Constans.CONTENT,headMap,map,MyContentData.class);
    }
    private void proLogic() {
        GridLayoutManager manager = new GridLayoutManager(getActivity(),2);
        adapter = new MyPzssAdapter(lists,getActivity());
        MyPZSS.setLayoutManager(manager);
        MyPZSS.setAdapter(adapter);
        //品质生活的点击事件
        adapter.setClickCallBack(new MyPzssAdapter.ClickCallBack() {
            @Override
            public void callBack(int i) {
                Intent intent = new Intent(getActivity(), DetailsActivity.class);
                intent.putExtra("zhi", i);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void success(Object data) {
        if (data instanceof MyBannerData) {
            final MyBannerData myBanner = (MyBannerData) data;
            //此处需要加判空,否则轮播图不出来
            List<MyBannerData.ResultBean> result = myBanner.getResult();
            for (int i = 0; i < myBanner.getResult().size(); i++) {
                datas.add(myBanner.getResult().get(i).getImageUrl());
            }
            if (!datas.isEmpty()) {
                PZSSxbanner.setData(myBanner.getResult(), null);
                PZSSxbanner.loadImage(new XBanner.XBannerAdapter() {
                    @Override
                    public void loadBanner(XBanner banner, Object model, View view, int position) {
                        Glide.with(getActivity()).load(myBanner.getResult().get(position).getImageUrl()).into((ImageView) view);
                    }
                });
                //设置样式，里面有很多种样式可以自己都看看效果
                PZSSxbanner.setPageTransformer(Transformer.Default);//横向移动
                PZSSxbanner.setPageTransformer(Transformer.Alpha); //渐变，效果不明显
                PZSSxbanner.setPageTransformer(Transformer.ZoomFade); // 缩小本页，同时放大另一页
                PZSSxbanner.setPageTransformer(Transformer.ZoomCenter); //本页缩小一点，另一页就放大
                PZSSxbanner.setPageTransformer(Transformer.ZoomStack); // 本页和下页同事缩小和放大
                PZSSxbanner.setPageTransformer(Transformer.Stack);  //本页和下页同时左移
                PZSSxbanner.setPageTransformer(Transformer.Depth);  //本页左移，下页从后面出来
                PZSSxbanner.setPageTransformer(Transformer.Zoom);  //本页刚左移，下页就在后面
                //  设置xbanner求换页面的时间
                PZSSxbanner.setPageChangeDuration(1000);
            }
        }else if (data instanceof MyContentData){

            MyContentData myContentData = (MyContentData) data;
            lists.addAll(myContentData.getResult().getPzsh().getCommodityList());

        }
    }

    @Override
    public void error(Object error) {

    }
}
