package com.example.onboardingscreen;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

public class SliderAdapter extends PagerAdapter {
    LayoutInflater layoutInflater;
    Context context;

    String[] pageNumbers = {"1","2","3"};


    public SliderAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return pageNumbers.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        //must be cast to ScrollView
        return view == (ScrollView) object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {

        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.sample_slider,container,false);


        TextView textView = view.findViewById(R.id.firstTV);
        ImageView imageView = view.findViewById(R.id.imageView2);

        textView.setText("The page number is "+pageNumbers[position]);

        //resouce array access

        Resources res = context.getResources();
        TypedArray icons = res.obtainTypedArray(R.array.icons);
        Drawable drawable = icons.getDrawable(position);

        //setting icons according to position
        imageView.setImageDrawable(drawable);

        container.addView(view);

        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((ScrollView) object);
    }
}
