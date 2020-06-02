package com.tse.newhabit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ViewPager mViewPager;
    private TabLayout mTabLayout;
    public static SQLiteDatabase db = null;
    public static int lastPosition = 0;
    public static ArrayList<Habit> HabitList = new ArrayList<>();
    /*private HomeFragment mHomeFragment;*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        db = openOrCreateDatabase("habit.db", Context.MODE_PRIVATE,null);
        String strcreatedb = "CREATE TABLE IF NOT EXISTS " + "tHabit (hID INTEGER PRIMARY KEY,hName TEXT NOT NULL, hDate TEXT NOT NULL)";
        db.execSQL(strcreatedb);
        strcreatedb = "CREATE TABLE IF NOT EXISTS " + "tAlarm (aID INTEGER PRIMARY KEY,hID INTEGER NOT NULL, aTime TEXT NOT NULL, FOREIGN KEY (hID) REFERENCES tHabit(hID))";
        db.execSQL(strcreatedb);
        strcreatedb = "CREATE TABLE IF NOT EXISTS " + "tDaily (dID INTEGER PRIMARY KEY,hID INTEGER NOT NULL, dDate TEXT , dDairy TEXT, FOREIGN KEY (hID) REFERENCES tHabit(hID))";
        db.execSQL(strcreatedb);
        setContentView(R.layout.activity_main);
        mViewPager = (ViewPager) findViewById(R.id.viewPager);
        mTabLayout = (TabLayout) findViewById(R.id.tabLayout);
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }
            @Override
            public void onPageSelected(int position) {
                lastPosition = position;
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        setupViewPager(mViewPager);
        mTabLayout.setupWithViewPager(mViewPager);
        mTabLayout.getTabAt(0).setIcon(R.drawable.home);
        mTabLayout.getTabAt(1).setIcon(R.drawable.create);
        mTabLayout.getTabAt(2).setIcon(R.drawable.info);

    }
    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new HomeFragment(), "Home");
        adapter.addFragment(new CreateFragment(), "Create");
        adapter.addFragment(new InfoFragment(), "Info");
        viewPager.setAdapter(adapter);
    }
}
