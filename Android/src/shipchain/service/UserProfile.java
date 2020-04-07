package com.example.shipchain.service;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.example.shipchain.R;

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

        SharedPreferences sharedPreferences = getContext().getSharedPreferences("LoginUserDetails",
                Context.MODE_PRIVATE);
        final String phoneNumber = sharedPreferences.getString("number", "");
        final String email = sharedPreferences.getString("email", "");
        final String pass = sharedPreferences.getString("pass", "");

        TextView userPhone = view.findViewById(R.id.user_phoneNumber);
        TextView userEmail = view.findViewById(R.id.user_email_id);
        TextView userPass = view.findViewById(R.id.user_email_pass);

        userPhone.setText("Phone : "+phoneNumber);
        userEmail.setText("Email : "+email);
        userPass.setText("Password : "+pass);
        TextView logoutUser = view.findViewById(R.id.logout_user);

        logoutUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences = getContext().
                        getSharedPreferences("LoginUserStatus", Context.MODE_PRIVATE);

                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putBoolean("LoginStatus",false);
                editor.apply();

                final ProgressDialog dialog = new ProgressDialog(getContext());
                dialog.setMessage("Logging out...");
                dialog.show();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (dialog.isShowing()) {
                            dialog.dismiss();
                            Intent intent = new Intent(getActivity(), LoginUser.class);
                            startActivity(intent);
                        }
                    }
                }, 1500);
            }
        });
        return view;
    }
}
