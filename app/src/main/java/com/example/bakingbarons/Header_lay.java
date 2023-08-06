package com.example.bakingbarons;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Header_lay extends AppCompatActivity {

    FirebaseAuth mAuth;
    FirebaseUser firebaseUser;
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.header_lay);

//        textView = findViewById(R.id.drawerEmail);
        firebaseUser = mAuth.getCurrentUser();
        Toast.makeText(getApplicationContext(), "Working", Toast.LENGTH_SHORT).show();


//        textView.setText(firebaseUser.getEmail().toString());
    }
}