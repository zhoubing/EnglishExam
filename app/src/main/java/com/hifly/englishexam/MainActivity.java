package com.hifly.englishexam;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;

import com.hifly.englishexam.databinding.ActivityMainBinding;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private final ArrayList<Fragment> fragments = new ArrayList<>(10);
    private final ArrayList<Question> questions = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding activityMainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(activityMainBinding.getRoot());
        initQuestions();
        for (int i = 0; i < questions.size(); i++) {
            if (questions.get(i).getType() == 0) {
                SelectionQuestionFragment selectionQuestionFragment = new SelectionQuestionFragment(questions.get(i));
                fragments.add(selectionQuestionFragment);
            } else if (questions.get(i).getType() == 1) {
                WordPuzzleFragment wordPuzzleFragment = new WordPuzzleFragment(questions.get(i));
                fragments.add(wordPuzzleFragment);
            }
        }
        activityMainBinding.viewpage.setOffscreenPageLimit(10);
        activityMainBinding.viewpage.setUserInputEnabled(false);
        activityMainBinding.viewpage.setAdapter(new FragmentStateAdapter(this) {
            @NonNull
            @Override
            public Fragment createFragment(int position) {
                return fragments.get(position);
            }

            @Override
            public int getItemCount() {
                return fragments.size();
            }
        });
        activityMainBinding.viewpage.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {

            }
        });


        LocationListener mLocationListener = new LocationListener() {

            @Override
            public void onLocationChanged(Location location) {
                Log.e("GPS", "" + location.getLatitude());
                Geocoder geocoder = new Geocoder(MainActivity.this);
                try {
                    List<Address> addrs = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {

            }

            @Override
            public void onProviderEnabled(String provider) {

            }

            @Override
            public void onProviderDisabled(String provider) {

            }
        };
        LocationManager mLocationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        try {
            if (mLocationManager.getProvider(LocationManager.NETWORK_PROVIDER) != null)
                mLocationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, mLocationListener);
            if (mLocationManager.getProvider(LocationManager.GPS_PROVIDER) != null)
                mLocationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, mLocationListener);
        } catch(SecurityException e) {
            e.printStackTrace();
        }
    }

    private void initQuestions() {
        Question question = new Question();
        question.setType(0);
        question.setTitle("Come here, Mary, look, the computer game is______.");
        question.setSelection1("in");
        question.setSelection2("Play");
        question.setSelection3("on");
        question.setAnswer("A");
        questions.add(question);

        question = new Question();
        question.setType(1);
        question.setTitle("theirs(宾格)");
        question.setAnswer("them");
        questions.add(question);
    }
}