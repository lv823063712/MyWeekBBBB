package soexample.bigfly.com.lvfei20190302.ui.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
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
import soexample.bigfly.com.lvfei20190302.mvp.ipresenter.IPresenter;
import soexample.bigfly.com.lvfei20190302.mvp.ipresenter.IPresenterImpl;
import soexample.bigfly.com.lvfei20190302.mvp.iview.IView;
import soexample.bigfly.com.lvfei20190302.ui.activity.DetailsActivity;
import soexample.bigfly.com.lvfei20190302.ui.adapter.MyRxxpAdapter;
import soexample.bigfly.com.lvfei20190302.utils.Constans;

/**
 * A simple {@link Fragment} subclass.
 */
public class HotFragment extends Fragment implements IView {

    @BindView(R.id.xbanner)
    XBanner xbanner;
    @BindView(R.id.MyRXXP)
    RecyclerView MyRecy;
    Unbinder unbinder;

    private IPresenterImpl iPresenter;
    private ArrayList<String> datas = new ArrayList<>();
    private ArrayList<MyContentData.ResultBean.RxxpBean.CommodityListBean>lists = new ArrayList<>();
    private Map<String,Object>headMap = new HashMap<>();
    private Map<String,Object>map = new HashMap<>();
    private MyRxxpAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View inflate = inflater.inflate(R.layout.fragment_home, null);
        unbinder = ButterKnife.bind(this, inflate);

        iPresenter = new IPresenterImpl(this);
        iPresenter.startRequest(Constans.BANNER_URL,headMap,map, MyBannerData.class);
        iPresenter.startRequest(Constans.CONTENT,headMap,map,MyContentData.class);
        StaggeredGridLayoutManager manager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        MyRecy.setLayoutManager(manager);
        adapter = new MyRxxpAdapter(lists,getActivity());
        MyRecy.setAdapter(adapter);
        adapter.setClickCallBack(new MyRxxpAdapter.ClickCallBack() {
            @Override
            public void callBack(int i) {

                Intent intent = new Intent(getActivity(), DetailsActivity.class);
                intent.putExtra("zhi", i);
                startActivity(intent);
            }
        });
        return inflate;
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
                xbanner.setData(myBanner.getResult(), null);
                xbanner.loadImage(new XBanner.XBannerAdapter() {
                    @Override
                    public void loadBanner(XBanner banner, Object model, View view, int position) {
                        Glide.with(getActivity()).load(myBanner.getResult().get(position).getImageUrl()).into((ImageView) view);
                    }
                });
                //设置样式，里面有很多种样式可以自己都看看效果
                xbanner.setPageTransformer(Transformer.Default);//横向移动
                xbanner.setPageTransformer(Transformer.Alpha); //渐变，效果不明显
                xbanner.setPageTransformer(Transformer.ZoomFade); // 缩小本页，同时放大另一页
                xbanner.setPageTransformer(Transformer.ZoomCenter); //本页缩小一点，另一页就放大
                xbanner.setPageTransformer(Transformer.ZoomStack); // 本页和下页同事缩小和放大
                xbanner.setPageTransformer(Transformer.Stack);  //本页和下页同时左移
                xbanner.setPageTransformer(Transformer.Depth);  //本页左移，下页从后面出来
                xbanner.setPageTransformer(Transformer.Zoom);  //本页刚左移，下页就在后面
                //  设置xbanner求换页面的时间
                xbanner.setPageChangeDuration(1000);
            }
        }else if (data instanceof MyContentData){

            MyContentData myContentData = (MyContentData) data;
            lists.addAll(myContentData.getResult().getRxxp().getCommodityList());

        }
    }

    @Override
    public void error(Object error) {
        Log.e("九宫格失败", "error: " + error);
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
