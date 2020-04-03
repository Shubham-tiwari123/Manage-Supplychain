package com.example.shipchain.service;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.shipchain.R;
import com.example.shipchain.service.LoginUser;

public class UserProfile extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.fragment_user_profile,container,false);
        /*String[] mobileArray = {"Android","IPhone","WindowsMobile","Blackberry",
                "WebOS","Ubuntu","Windows7","Max OS X","Android","IPhone","WindowsMobile","Blackberry",
                "WebOS","Ubuntu","Windows7","Max OS X"};

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
                R.layout.activity_listview, mobileArray);

        ListView listView = (ListView) view.findViewById(R.id.mobile_list);
        listView.setAdapter(adapter);*/

        TextView logoutUser = view.findViewById(R.id.logout_user);

        logoutUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), LoginUser.class);
                startActivity(intent);
            }
        });
        return view;
    }
}
