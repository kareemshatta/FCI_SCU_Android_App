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
            R.drawable.ic_chating,
            R.drawable.ic_material,
            R.drawable.ic_groups
    };
    private String[] slideHeading = {
            "Intercommunication",
            "Educational services ",
            "Team work"
    };
    private String[] slideDesc = {
            "Help you to intercommunication between the teaching staff and students.",
            "help you to share materials, Assignments, Follow-up and Discussions.",
            "help you to create homogenous team, Team meeting and Finding suitable time for meeting.\n"
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
