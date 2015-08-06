package plus.mcpe.mcpe_plus;

import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.FrameLayout;
import android.widget.ImageView;

import plus.mcpe.mcpe_plus.model.DataModel;
import plus.mcpe.mcpe_plus.utils.UiExt;
import plus.mcpe.mcpe_plus.view.DataView;

public class MainActivity extends AppCompatActivity {
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle actionBarDrawerToggle;
    ViewPager pager;
    TabLayout tab;
    DataModel[] dataModels = new DataModel[4];
    DataView[] dataViews = new DataView[4];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
        drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        setupStatusBar();
        //noinspection ConstantConditions
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        actionBarDrawerToggle =
                new ActionBarDrawerToggle(MainActivity.this, drawerLayout, R.string.app_name, R.string.app_name);
        drawerLayout.setDrawerListener(actionBarDrawerToggle);
        tab = (TabLayout) findViewById(R.id.tabLayout);
        pager = (ViewPager) findViewById(R.id.viewPager);
        testData();

        setupViewPager();
        setupTabLayout();
    }

    private void setupStatusBar() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            ImageView imageView = (ImageView) findViewById(R.id.image);
            imageView.setLayoutParams(new FrameLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT, UiExt.getStatusBarHeight()));
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE |
                    View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        }
    }

    @Override
    public void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        actionBarDrawerToggle.syncState();
    }

    //TODO:使用正式数据
    private void testData() {
        dataModels[0] = dataModels[1] = dataModels[2] = dataModels[3] = new DataModel() {
            @Override
            public int getCount() {
                return 55;
            }

            @Override
            public String getTitle(int position) {
                return "test";
            }
        };
        for (int i = 0; i < 4; i++) {
            dataViews[i] = new DataView(this, dataModels[i]);
        }
    }

    private void setupViewPager() {
        pager.setAdapter(new MyPagerAdapter());
    }

    private void setupTabLayout() {
        tab.setupWithViewPager(pager);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id == android.R.id.home) {
            drawerLayout.openDrawer(Gravity.LEFT);
        }

        return super.onOptionsItemSelected(item);
    }

    class MyPagerAdapter extends android.support.v4.view.PagerAdapter {
        @Override
        public int getCount() {
            return 4;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return getString(R.string.map);
                case 1:
                    return getString(R.string.js);
                case 2:
                    return getString(R.string.skin);
                case 3:
                    return getString(R.string.texture);
                default:
                    return null;
            }
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            container.addView(dataViews[position],
                    new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                            ViewGroup.LayoutParams.WRAP_CONTENT));
            return dataViews[position];
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView(dataViews[position]);
        }
    }
}
