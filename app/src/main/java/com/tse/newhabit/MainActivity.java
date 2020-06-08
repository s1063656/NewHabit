package com.tse.newhabit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    public String userEmail;
    private ViewPager mViewPager;
    private TabLayout mTabLayout;
    private ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
    public static int lastPosition = 0;
    public static ArrayList<Habit> HabitList = new ArrayList<>();
    /*private HomeFragment mHomeFragment;*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        Intent it = getIntent();
        db.collection(it.getStringExtra("USERID"))
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            int i = 0;
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                HabitList.add(new Habit(document.getString("habitName"),document.get("beginDate")));
                                Log.d("TAG", document.getId() + " => " + HabitList.get(i).getTitle());
                                Log.d("TAG", document.getId() + " => " + document.getData());
                                i++;
                            }
                        } else {
                            Log.w("TAG", "Error getting documents.", task.getException());
                        }
                    }
                });
        try{Thread.sleep(2000);}catch (Exception e){}
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
        mViewPager.setCurrentItem(1);
    }
    private void setupViewPager(ViewPager viewPager) {
        adapter.addFragment(new HomeFragment(), "Home");
        adapter.addFragment(new CreateFragment(), "Create");
        adapter.addFragment(new InfoFragment(), "Info");
        viewPager.setAdapter(adapter);
    }

}
