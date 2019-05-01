package com.example.kareem.fci_scu_project.expandable;

/**
 * Created by youssef on 7/2/2019.
 */

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.kareem.fci_scu_project.R;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by zerones on 04-Oct-17.
 */

public class ExpandableListAdapter extends BaseExpandableListAdapter {

    public  static ArrayList<ArrayList<HashMap<String, String>>> childItems;
    public static ArrayList<HashMap<String, String>> parentItems;
    public LayoutInflater inflater;
    public Context context;
    public HashMap<String, String> child;
    public int count = 0;
    public boolean isFromMyCategoriesFragment;

    public ExpandableListAdapter(Context context, ArrayList<HashMap<String, String>> parentItems,
                                 ArrayList<ArrayList<HashMap<String, String>>> childItems, boolean isFromMyCategoriesFragment) {

        this.parentItems = parentItems;
        this.childItems = childItems;
        this.context = context;
        this.isFromMyCategoriesFragment = isFromMyCategoriesFragment;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    @Override
    public int getGroupCount() {
        return parentItems.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return (childItems.get(groupPosition)).size();
    }

    @Override
    public Object getGroup(int i) {
        return null;
    }

    @Override
    public Object getChild(int i, int i1) {
        return null;
    }

    @Override
    public long getGroupId(int i) {
        return 0;
    }

    @Override
    public long getChildId(int i, int i1) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(final int groupPosition, final boolean b, View convertView, ViewGroup viewGroup) {
        final ViewHolderParent viewHolderParent;
        if (convertView == null) {

                convertView = inflater.inflate(R.layout.parent_row, null);

            viewHolderParent = new ViewHolderParent();

            viewHolderParent.parent_row_name_tv = convertView.findViewById(R.id.parent_row_name_tv);
            viewHolderParent.parent_row_cb = convertView.findViewById(R.id.parent_row_cb);
            viewHolderParent.parent_row_iv = convertView.findViewById(R.id.parent_row_iv);
            convertView.setTag(viewHolderParent);
        } else {
            viewHolderParent = (ViewHolderParent) convertView.getTag();
        }

        if (parentItems.get(groupPosition).get(Parameter.IS_CHECKED).equalsIgnoreCase(Constants.BOX_CHECKED)) {
            viewHolderParent.parent_row_cb.setChecked(true);
            notifyDataSetChanged();

        } else {
            viewHolderParent.parent_row_cb.setChecked(false);
            notifyDataSetChanged();
        }

        viewHolderParent.parent_row_cb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (viewHolderParent.parent_row_cb.isChecked()) {
                    parentItems.get(groupPosition).put(Parameter.IS_CHECKED, Constants.BOX_CHECKED);

                    for (int i = 0; i < childItems.get(groupPosition).size(); i++) {
                        childItems.get(groupPosition).get(i).put(Parameter.IS_CHECKED, Constants.BOX_CHECKED);
                    }
                    notifyDataSetChanged();

                } else {
                    parentItems.get(groupPosition).put(Parameter.IS_CHECKED, Constants.BOX_UNCHECKED);
                    for (int i = 0; i < childItems.get(groupPosition).size(); i++) {
                        childItems.get(groupPosition).get(i).put(Parameter.IS_CHECKED, Constants.BOX_UNCHECKED);
                    }
                    notifyDataSetChanged();
                }
            }
        });

        Constants.childItems = childItems;
        Constants.parentItems = parentItems;

        viewHolderParent.parent_row_name_tv.setText(parentItems.get(groupPosition).get(Parameter.PARENT_NAME));

        return convertView;
    }

    @Override
    public View getChildView(final int groupPosition, final int childPosition, final boolean b, View convertView, ViewGroup viewGroup) {

        final ViewHolderChild viewHolderChild;
        child = childItems.get(groupPosition).get(childPosition);

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.child_row, null);
            viewHolderChild = new ViewHolderChild();

            viewHolderChild.child_row_name_tv = convertView.findViewById(R.id.child_row_name_tv);
            viewHolderChild.child_row_cb = convertView.findViewById(R.id.child_row_cb);
            viewHolderChild.child_row_line_devide = convertView.findViewById(R.id.child_row_line_devide);
            convertView.setTag(viewHolderChild);
        } else {
            viewHolderChild = (ViewHolderChild) convertView.getTag();
        }

        if (childItems.get(groupPosition).get(childPosition).get(Parameter.IS_CHECKED).equalsIgnoreCase(Constants.BOX_CHECKED)) {
            viewHolderChild.child_row_cb.setChecked(true);
            notifyDataSetChanged();
        } else {
            viewHolderChild.child_row_cb.setChecked(false);
            notifyDataSetChanged();
        }

        viewHolderChild.child_row_name_tv.setText(child.get(Parameter.CHILD_NAME));
        viewHolderChild.child_row_cb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (viewHolderChild.child_row_cb.isChecked()) {
                    count = 0;
                    childItems.get(groupPosition).get(childPosition).put(Parameter.IS_CHECKED, Constants.BOX_CHECKED);
                    notifyDataSetChanged();
                } else {
                    count = 0;
                    childItems.get(groupPosition).get(childPosition).put(Parameter.IS_CHECKED, Constants.BOX_UNCHECKED);
                    notifyDataSetChanged();
                }

                for (int i = 0; i < childItems.get(groupPosition).size(); i++) {
                    if (childItems.get(groupPosition).get(i).get(Parameter.IS_CHECKED).equalsIgnoreCase(Constants.BOX_CHECKED)) {
                        count++;
                    }
                }
                if (count == childItems.get(groupPosition).size()) {
                    parentItems.get(groupPosition).put(Parameter.IS_CHECKED, Constants.BOX_CHECKED);
                    notifyDataSetChanged();
                } else {
                    parentItems.get(groupPosition).put(Parameter.IS_CHECKED, Constants.BOX_UNCHECKED);
                    notifyDataSetChanged();
                }


                Constants.childItems = childItems;
                Constants.parentItems = parentItems;
            }
        });

        return convertView;
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return false;
    }

    @Override
    public void onGroupCollapsed(int groupPosition) {
        super.onGroupCollapsed(groupPosition);
    }

    @Override
    public void onGroupExpanded(int groupPosition) {
        super.onGroupExpanded(groupPosition);
    }

    private class ViewHolderParent {

        TextView parent_row_name_tv;
        CheckBox parent_row_cb;
        ImageView parent_row_iv;
    }

    private class ViewHolderChild {

        TextView child_row_name_tv;
        CheckBox child_row_cb;
        View child_row_line_devide;
    }


}