package com.example.shipchain.service;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.shipchain.R;

public class Home extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_home,container,false);

        ImageView getDetails = view.findViewById(R.id.get_detail);

        getDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView result = view.findViewById(R.id.result_cap);
                result.setVisibility(View.VISIBLE);
            }
        });
        return view;
    }
}
