package com.example.kareem.fci_scu_project.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.kareem.fci_scu_project.R;

public class SliderAdapter extends PagerAdapter {

    private Context context;
    private LayoutInflater layoutInflater;

    private int[] slideIcons = {
            R.drawable.ic_launcher_foreground,
            R.drawable.ic_launcher_foreground,
            R.drawable.ic_launcher_foreground
    };
    private String[] slideHeading = {
            "heading 1",
            "heading 2",
            "heading 3"
    };
    private String[] slideDesc = {
            "desc1",
            "desc2",
            "desc3"
    };

    public SliderAdapter(Context context ) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return slideHeading.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view == o;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position ) {
        layoutInflater = (LayoutInflater)context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.slide_layout,container,false);
        ImageView photo = view.findViewById(R.id.slide_photo);
        TextView heading = view.findViewById(R.id.slide_heading);
        TextView desc = view.findViewById(R.id.slide_desc);

        photo.setImageResource(slideIcons[position]);
        heading.setText(slideHeading[position]);
        desc.setText(slideDesc[position]);
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem( ViewGroup container, int position, Object object ) {
        container.removeView((RelativeLayout)object);
    }
}
