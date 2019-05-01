package com.example.kareem.fci_scu_project.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.kareem.fci_scu_project.R;
import com.example.kareem.fci_scu_project.adapters.SliderAdapter;

public class SlideActivity extends AppCompatActivity {

    private ViewPager slideVP;
    private LinearLayout dotLayout;
    private SliderAdapter sliderAdapter;
    private TextView[] dots;
    private Button slideSkip;
    private int currentPage = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slide);
        getSupportActionBar().hide();
        init();
    }

    private void init() {
        slideVP = findViewById(R.id.slide_viewpager);
        dotLayout = findViewById(R.id.slide_dots_layout);
        slideSkip = findViewById(R.id.slide_skip_btn);
        sliderAdapter = new SliderAdapter(this);
        slideVP.setAdapter(sliderAdapter);
        addDotsIndicator(0);
        slideVP.addOnPageChangeListener(view_listner);
        slideSkip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick( View view ) {
                if (currentPage == 2){
                    startActivity(new Intent(getBaseContext(),LoginActivity.class));
                    finish();
                }else{
                    slideVP.setCurrentItem(currentPage + 1);
                }

            }
        });

    }

    public void addDotsIndicator(int position){
        dots = new TextView[3];
        for (int i=0 ; i < dots.length ; i++){
            dots[i] = new TextView(this);
            dots[i].setText(Html.fromHtml("&#8226;"));
            dots[i].setTextSize(35);
            dots[i].setTextColor(getResources().getColor(R.color.colorTransporterWhite));
            dotLayout.addView(dots[i]);
        }
        if(dots.length > 0){
            dots[position].setTextColor(getResources().getColor(R.color.colorAccent));
        }
    }
    ViewPager.OnPageChangeListener view_listner = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled( int position, float positionOffset, int positionOffsetPixels ) {

        }

        @Override
        public void onPageSelected( int position ) {
            dotLayout.removeAllViews();
            addDotsIndicator(position);
            currentPage = position;

            if (position == dots.length-1 ){
                slideSkip.setText(R.string.slide_finish_btn_txt);

            }else{
                slideSkip.setText(R.string.slide_skip_btn_txt);
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }


    };




}
