package com.example.kareem.fci_scu_project.expandable;

/**
 * Created by youssef on 7/2/2019.
 */

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.kareem.fci_scu_project.R;

public class CheckedFragment extends Fragment {
    public static String GET_FRAGMENT = null;
    private TextView fragment_checked_parent_tv, fragment_checked_child_tv;
    public static String parent="";
    public static String child="";


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        GET_FRAGMENT = "CheckedFragment";
        View view = inflater.inflate(R.layout.fragment_checked,container,false);
        fragment_checked_parent_tv = view.findViewById(R.id.fragment_checked_parent_tv);
        fragment_checked_child_tv = view.findViewById(R.id.fragment_checked_child_tv);

        int parent_count = Constants.parentItems.size();
        int child_count = Constants.childItems.size();

        for (int i = 0; i < parent_count; i++ ){

            String isChecked = Constants.parentItems.get(i).get(Parameter.IS_CHECKED);

            if (isChecked.equalsIgnoreCase(Constants.BOX_CHECKED))
            {
                parent = fragment_checked_parent_tv.getText()+Constants.parentItems.get(i).get(Parameter.PARENT_NAME)+",";
                fragment_checked_parent_tv.setText(parent);
            }

            for (int j = 0; j < child_count; j++ ){

                String isChildChecked =  Constants.childItems.get(i).get(j).get(Parameter.IS_CHECKED);

                if (isChildChecked.equalsIgnoreCase(Constants.BOX_CHECKED))
                {   int m = j+1;
                    child = fragment_checked_child_tv.getText()+Constants.parentItems.get(i).get(Parameter.PARENT_NAME)+" "+m+" , ";
                    fragment_checked_child_tv.setText(child);

                }

            }

        }





        return view;
    }


}
