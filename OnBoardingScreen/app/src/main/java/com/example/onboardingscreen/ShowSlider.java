package com.example.onboardingscreen;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import org.w3c.dom.Text;

public class ShowSlider extends AppCompatActivity {
    private ViewPager mViewPager;
    private int currentPage = 0;
    private TextView nextButton,prevButton;
    private LinearLayout dotsLayout;
    private TextView[] mDots;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_slider);

        nextButton = findViewById(R.id.button_next);
        prevButton = findViewById(R.id.button_previous);
        dotsLayout = findViewById(R.id.linearLayout);

        mViewPager = findViewById(R.id.viewpager);
        SliderAdapter sliderAdapter = new SliderAdapter(this);
        mViewPager.setAdapter(sliderAdapter);
        mViewPager.addOnPageChangeListener(viewListener);

        addDots(0);

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(currentPage == 2){
                    Intent intent = new Intent(ShowSlider.this,MainActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                }
                else{
                    mViewPager.setCurrentItem(currentPage+1);

                }
            }
        });
        prevButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mViewPager.setCurrentItem(currentPage-1);
            }
        });
    }

    public void addDots(int position){
        mDots = new TextView[3];
        dotsLayout.removeAllViews(); // ei line call na korle proti change listen e 3ta kore dot add hobe...
        for(int i=0; i<mDots.length; i++){
            mDots[i] = new TextView(this);
            mDots[i].setText(Html.fromHtml("&#8226"));
            mDots[i].setTextSize(35);
            mDots[i].setTextColor(getResources().getColor(R.color.colorGray));

            dotsLayout.addView(mDots[i]);
        }
        if(mDots.length>0){
            mDots[position].setTextColor(getResources().getColor(R.color.colorPrimary));
        }
    }

    ViewPager.OnPageChangeListener viewListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {


            //adding dot positions
            addDots(position);

            currentPage = position;
            if (position == 0){
                prevButton.setVisibility(View.INVISIBLE);
                prevButton.setEnabled(false);
                prevButton.setText("");

            }else if (position == 2){
                nextButton.setText("START");

                prevButton.setText("BACK");
                prevButton.setVisibility(View.VISIBLE);
                prevButton.setEnabled(true);
            }else{
                prevButton.setVisibility(View.VISIBLE);
                prevButton.setEnabled(true);
                prevButton.setText("BACK");

                nextButton.setText("NEXT");

            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };
}
