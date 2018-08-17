package ph.commlinked.shoppinoy.Activity;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import ph.commlinked.shoppinoy.R;
import ph.commlinked.shoppinoy.fragments.accountFragment;
import ph.commlinked.shoppinoy.fragments.chatFragment;
import ph.commlinked.shoppinoy.fragments.homeFragment;
import ph.commlinked.shoppinoy.fragments.notifFragment;
import ph.commlinked.shoppinoy.fragments.settingsFragment;

public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private int[] tabIcons = {
            R.drawable.ic_action_home,
            R.drawable.ic_action_notif,
            R.drawable.ic_action_message,
            R.drawable.ic_action_myaccount,
            R.drawable.ic_action_settings
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
        setupTabIcons();

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            public void onPageScrollStateChanged(int state) {}
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {}

            public void onPageSelected(int position) {
                if (position == 0){
                    Toast.makeText(getApplicationContext(), ""+position,Toast.LENGTH_SHORT).show();
                }else if(position == 1){
                    Toast.makeText(getApplicationContext(), ""+position,Toast.LENGTH_SHORT).show();
                }else if(position == 2){
                    Toast.makeText(getApplicationContext(), ""+position,Toast.LENGTH_SHORT).show();
                }else if(position == 3){
                    Toast.makeText(getApplicationContext(), ""+position,Toast.LENGTH_SHORT).show();
                }else if(position == 4){
                    Toast.makeText(getApplicationContext(), ""+position,Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(), loginActivity.class);
                    startActivity(intent);
                }
            }
        });
    }

    private void setupTabIcons() {
        tabLayout.getTabAt(0).setIcon(tabIcons[0]);
        tabLayout.getTabAt(1).setIcon(tabIcons[1]);
        tabLayout.getTabAt(2).setIcon(tabIcons[2]);
        tabLayout.getTabAt(3).setIcon(tabIcons[3]);
        tabLayout.getTabAt(4).setIcon(tabIcons[4]);
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new homeFragment(), "ONE");
        adapter.addFragment(new notifFragment(), "TWO");
        adapter.addFragment(new chatFragment(), "THREE");
        adapter.addFragment(new accountFragment(), "FOUR");
        adapter.addFragment(new settingsFragment(), "FIVE");
        viewPager.setAdapter(adapter);
    }

    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return null;
        }
    }


}
