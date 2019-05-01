package com.example.kareem.fci_scu_project.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ExpandableListView;

import com.example.kareem.fci_scu_project.R;
import com.example.kareem.fci_scu_project.expandable.CheckedFragment;
import com.example.kareem.fci_scu_project.expandable.Constants;
import com.example.kareem.fci_scu_project.expandable.ExpandableListAdapter;
import com.example.kareem.fci_scu_project.expandable.Parameter;
import com.example.kareem.fci_scu_project.model.ChildItem;
import com.example.kareem.fci_scu_project.model.ParentItem;

import java.util.ArrayList;
import java.util.HashMap;


/**
 * A simple {@link Fragment} subclass.
 */
public class SelectStudentFragment extends Fragment {
    View view;

    private Button btn;
    private ExpandableListView expandable_list;

    private ArrayList<ParentItem> parent_item;
    private ArrayList<ChildItem> child_item;


    private ArrayList<HashMap<String, String>> parent_items;
    private ArrayList<ArrayList<HashMap<String, String>>> child_items;

    private ExpandableListAdapter expandableListAdapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view1 = inflater.inflate(R.layout.fragment_select_student, container, false);
        view = view1;
        btn = view1.findViewById(R.id.btn);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // old
//                getFragmentManager().beginTransaction().replace(R.id.main_container, new CheckedFragment())
//                        .addToBackStack("CheckedFragment").commit();

                //new
                getFragmentManager().beginTransaction()
                        .replace(R.id.main_container, new CheckedFragment(), "CheckedFragment")
                        .addToBackStack("CheckedFragment")
                        .commit();
                //new
            }
        });

        setupReferences();
        return view;

    }

    private void setupReferences() {

        expandable_list = view.findViewById(R.id.expandable_list);
        parent_item = new ArrayList<>();
        child_item = new ArrayList<>();

        child_item.clear();
        parent_item.clear();
        child_item.add(new ChildItem(parent_item.toString(), String.valueOf(1), "Abdo Hassan", Constants.BOX_UNCHECKED));
        child_item.add(new ChildItem(parent_item.toString(), String.valueOf(2), "Youssef Seddik", Constants.BOX_UNCHECKED));
        child_item.add(new ChildItem(parent_item.toString(), String.valueOf(3), "Ibrahim Ali", Constants.BOX_UNCHECKED));
        parent_item.add(new ParentItem("1", "GroupA", Constants.BOX_UNCHECKED, child_item));

        child_item.clear();

        child_item.add(new ChildItem(parent_item.toString(), String.valueOf(1), "Abdo Hassan", Constants.BOX_UNCHECKED));
        child_item.add(new ChildItem(parent_item.toString(), String.valueOf(2), "Youssef Seddik", Constants.BOX_UNCHECKED));
        child_item.add(new ChildItem(parent_item.toString(), String.valueOf(3), "Ibrahim Ali", Constants.BOX_UNCHECKED));
        parent_item.add(new ParentItem("2", "GroupB", Constants.BOX_UNCHECKED, child_item));

        child_item.clear();

        child_item.add(new ChildItem(parent_item.toString(), String.valueOf(1), "Abdo Hassan", Constants.BOX_UNCHECKED));
        child_item.add(new ChildItem(parent_item.toString(), String.valueOf(2), "Youssef Seddik", Constants.BOX_UNCHECKED));
        child_item.add(new ChildItem(parent_item.toString(), String.valueOf(3), "Ibrahim Ali", Constants.BOX_UNCHECKED));
        parent_item.add(new ParentItem("3", "GroupC", Constants.BOX_UNCHECKED, child_item));


        parent_items = new ArrayList<>();
        child_items = new ArrayList<>();

        for (ParentItem data : parent_item) {

            ArrayList<HashMap<String, String>> childArrayList = new ArrayList<>();
            HashMap<String, String> mapParent = new HashMap<>();

            mapParent.put(Parameter.PARENT_ID, data.getParentId());
            mapParent.put(Parameter.PARENT_NAME, data.getParentName());

            int countIsChecked = 0;
            for (ChildItem childItem : data.getChildItems()) {

                HashMap<String, String> mapChild = new HashMap<>();
                mapChild.put(Parameter.CHILD_ID, childItem.getChildId());
                mapChild.put(Parameter.CHILD_NAME, childItem.getChildName());
                mapChild.put(Parameter.PARENT_ID, childItem.getParentId());
                mapChild.put(Parameter.IS_CHECKED, childItem.getIsChecked());

                if (childItem.getIsChecked().equalsIgnoreCase(Constants.BOX_CHECKED)) {

                    countIsChecked++;
                }
                childArrayList.add(mapChild);
            }

            if (countIsChecked == data.getChildItems().size()) {

                data.setIsChecked(Constants.BOX_CHECKED);
            } else {
                data.setIsChecked(Constants.BOX_UNCHECKED);
            }

            mapParent.put(Parameter.IS_CHECKED, data.getIsChecked());
            child_items.add(childArrayList);
            parent_items.add(mapParent);

        }

        Constants.parentItems = parent_items;
        Constants.childItems = child_items;

        expandableListAdapter = new ExpandableListAdapter(getContext(), parent_items, child_items, false);
        expandable_list.setAdapter(expandableListAdapter);
    }


}
