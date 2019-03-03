package soexample.bigfly.com.lvfei20190302.ui.fragment;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import soexample.bigfly.com.lvfei20190302.R;
import soexample.bigfly.com.lvfei20190302.ui.adapter.MyTabFragmentAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomePageFragment extends Fragment {
    @BindView(R.id.My_Tab)
    TabLayout MyTab;
    @BindView(R.id.MyFragment)
    ViewPager MyFragment;
    Unbinder unbinder;
    private ArrayList<Fragment> datas = new ArrayList<>();
    private ArrayList<String> lists = new ArrayList<>();
    private FragmentManager manager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View inflate = inflater.inflate(R.layout.fragment_home_page, null);
        unbinder = ButterKnife.bind(this, inflate);
        manager = getActivity().getSupportFragmentManager();
        MyFragment.setOffscreenPageLimit(0);
        initData();

        return inflate;
    }
    private void initData() {
        datas.add(new HotFragment());
        datas.add(new MlssFragment());
        datas.add(new PZSSFragment());
        lists.add("热销新品");
        lists.add("魔丽时尚");
        lists.add("品质生活");
        MyFragment.setAdapter(new MyTabFragmentAdapter(manager,datas,lists));
        MyTab.setupWithViewPager(MyFragment);

    }

   /* @Override
    public void onResume() {
        super.onResume();
        Log.e("zzz", "onResume: "+getUserVisibleHint());
        boolean i = true;
        if (getUserVisibleHint()==i){
            i=false;
        }else{
            setClazyShowUi();
            i=true;
        }
    }

    private void setClazyShowUi() {
        lists.clear();
        datas.clear();

    }
*/
  /*  @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser==true){
            Log.e("zzz", "setUserVisibleHint: "+isVisibleToUser );
            setClazyShowUi();
        }else{


        }
    }*/

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        lists.clear();
        unbinder.unbind();
    }
}
