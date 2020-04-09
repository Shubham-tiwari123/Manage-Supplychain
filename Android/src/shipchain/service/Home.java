package com.example.shipchain.service;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.example.shipchain.R;
import com.example.shipchain.requestAPI.GetProductInfoReqAPI;

public class Home extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_home,container,false);

        ImageView getDetails = view.findViewById(R.id.get_detail);
        final EditText productID = view.findViewById(R.id.product_Id);

        getDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView textView = view.findViewById(R.id.set_product_info);
                textView.setVisibility(View.INVISIBLE);
                GetProductInfoReqAPI reqAPI = new GetProductInfoReqAPI(getContext(),view);
                TextView result = view.findViewById(R.id.result_cap);
                result.setVisibility(View.VISIBLE);
                reqAPI.execute(productID.getText().toString());
            }
        });
        return view;
    }
}
