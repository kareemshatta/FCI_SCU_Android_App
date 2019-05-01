package com.example.kareem.fci_scu_project.fragments;


import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.kareem.fci_scu_project.R;
import com.example.kareem.fci_scu_project.adapters.NotificationRVAdapter;
import com.example.kareem.fci_scu_project.classes.Notification;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class NotificationFragment extends Fragment {

    private RecyclerView notifRecyclerView;
    private List<Notification> notifList;

    public NotificationFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_notification, container, false);
        notifList = new ArrayList<>();
        // retrive course data from database later
        notifList.add(new Notification("kareem shatta","data structure"));
        notifList.add(new Notification("kareem shatta","data mining"));
        notifList.add(new Notification("shady shatta","advanced"));
        notifList.add(new Notification("youssef shatta","algebra"));
        notifList.add(new Notification("jeo shatta","operating system"));

        notifRecyclerView = view.findViewById(R.id.notification_fragment_recyclerView);
        NotificationRVAdapter myAdapter = new NotificationRVAdapter(getActivity(),notifList);
        notifRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        notifRecyclerView.setAdapter(myAdapter);

        return view;
    }

}
