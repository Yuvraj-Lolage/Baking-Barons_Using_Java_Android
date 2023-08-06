package com.example.bakingbarons;

import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

import java.util.zip.Inflater;

public class Home extends Fragment {

    public Home() {
        // Required empty public constructor
    }

    Button selectLocationBtn;
    ImageButton notificationBtn;
    @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View view = inflater.inflate(R.layout.fragment_home, container, false);

//            // Find the TextView in the layout
//            TextView textViewProfileEmail = view.findViewById(R.id.textViewProfileEmail);
//
//            // Set the desired text
//            String profileEmail = "example@example.com";
//            textViewProfileEmail.setText(profileEmail);

            return view;
    }

    TextView drawerEmailTextView;
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        selectLocationBtn = view.findViewById(R.id.locationbtn);
//        drawerEmailTextView = view.findViewById(R.id.drawerEmail);

        selectLocationBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String[] pincodeList = {"2006072", "2006042"};
                AlertDialog.Builder alert = new AlertDialog.Builder(view.getContext());
                AlertDialog.Builder stateAlert = new AlertDialog.Builder(view.getContext());

                final ImageView imageView = new ImageView(view.getContext());
                imageView.setMaxWidth(300);
                imageView.setMaxHeight(300);
                alert.setIcon(R.drawable.ic_baseline_location_on_24);
                final AppCompatEditText editText = new AppCompatEditText(view.getContext());
                editText.setHint(" Enter pincode");
                alert.setCancelable(true);
                alert.setTitle("  Select Delivery Location");
                alert.setView(editText);
                alert.setPositiveButton("Submit", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String pincode = editText.getText().toString();
                        boolean result = false;
                        for(String x:pincodeList){
                            if(x.equals(pincode)){
                                result = true;
                                break;
                            }
                        }
                        if(result){
                            imageView.setImageResource(R.drawable.ic_baseline_check_circle_24);
                            alert.setView(imageView);
                            alert.show();
                        }
                        else {
                            imageView.setImageResource(R.drawable.ic_baseline_cancel_24);
                            alert.setView(imageView);
                            alert.show();
                        }
                    }
                });
                alert.setNegativeButton("Close", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });

                alert.show();
            }
        });
    }
}