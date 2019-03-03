package soexample.bigfly.com.lvfei20190302.ui.adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;

import java.util.ArrayList;

/**
 * <p>文件描述：<p>
 * <p>作者：${吕飞}<p>
 * <p>创建时间：2019/3/2   16:52<p>
 * <p>更改时间：2019/3/2   16:52<p>
 * <p>版本号：1<p>
 */

public class MyTabFragmentAdapter extends FragmentPagerAdapter {
    private ArrayList<Fragment> datas;
    private ArrayList<String> titles;

    public MyTabFragmentAdapter(FragmentManager fm, ArrayList<Fragment> datas, ArrayList<String> titles) {
        super(fm);
        this.datas = datas;
        this.titles = titles;
    }

    @Override
    public Fragment getItem(int i) {
        return datas.get(i);
    }

    @Override
    public int getCount() {
        return datas.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        Log.e("zzz", "getPageTitle: "+position );
        return titles.get(position);
    }

}
