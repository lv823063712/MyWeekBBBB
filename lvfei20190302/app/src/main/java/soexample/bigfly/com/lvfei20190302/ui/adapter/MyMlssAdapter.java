package soexample.bigfly.com.lvfei20190302.ui.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import soexample.bigfly.com.lvfei20190302.R;
import soexample.bigfly.com.lvfei20190302.bean.MyContentData;

/**
 * <p>文件描述：<p>
 * <p>作者：${吕飞}<p>
 * <p>创建时间：2019/3/2   17:31<p>
 * <p>更改时间：2019/3/2   17:31<p>
 * <p>版本号：1<p>
 */

public class MyMlssAdapter  extends RecyclerView.Adapter<MyMlssAdapter.ViewHolder> {
    private List<MyContentData.ResultBean.MlssBean.CommodityListBeanXX> datas;
    private Context context;
    private View inflate;

    public MyMlssAdapter(List<MyContentData.ResultBean.MlssBean.CommodityListBeanXX> datas, Context context) {
        this.datas = datas;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        this.context = viewGroup.getContext();
        inflate = LayoutInflater.from(context).inflate(R.layout.mlss_item, viewGroup, false);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {
        viewHolder.tv_mlss_title.setText(datas.get(i).getCommodityName());
        viewHolder.tv_mlss_price.setText("¥" + datas.get(i).getPrice() + "");
        Glide.with(context).load(datas.get(i).getMasterPic()).into(viewHolder.iv_mlss_img);
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (clickCallBack != null) {
                    clickCallBack.callBack(datas.get(i).getCommodityId());
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private final ImageView iv_mlss_img;
        private final TextView tv_mlss_title;
        private final TextView tv_mlss_price;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            iv_mlss_img = itemView.findViewById(R.id.My_mlss_Img);
            tv_mlss_title = itemView.findViewById(R.id.My_mlss_title);
            tv_mlss_price = itemView.findViewById(R.id.My_mlss_Price);

        }
    }

    //定义接口
    private ClickCallBack clickCallBack;

    public void setClickCallBack(ClickCallBack clickCallBack) {
        this.clickCallBack = clickCallBack;
    }

    public interface ClickCallBack {
        void callBack(int i);
    }

}
