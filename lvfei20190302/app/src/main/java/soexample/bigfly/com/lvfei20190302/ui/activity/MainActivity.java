package soexample.bigfly.com.lvfei20190302.ui.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import soexample.bigfly.com.lvfei20190302.R;
import soexample.bigfly.com.lvfei20190302.ui.fragment.ConsultFragment;
import soexample.bigfly.com.lvfei20190302.ui.fragment.HomePageFragment;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.homePage)
    TextView homePage;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.MyFra)
    FrameLayout MyFra;
    private FragmentManager manager;
    private HomePageFragment homePageFragment;
    private ConsultFragment consultFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        manager = getSupportFragmentManager();
        homePageFragment = new HomePageFragment();
        consultFragment = new ConsultFragment();
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.dh);
        }

        MainActivity.this.manager.beginTransaction().replace(R.id.MyFra,homePageFragment).commit();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_home:
                MainActivity.this.manager.beginTransaction().replace(R.id.MyFra,homePageFragment).commit();
                break;
            case R.id.nav_shop:
                MainActivity.this.manager.beginTransaction().replace(R.id.MyFra,consultFragment).commit();
                break;


        }
        return super.onOptionsItemSelected(item);
    }
}
