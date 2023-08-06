package com.example.bakingbarons;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
//import com.google.firebase.database.DatabaseReference;
//import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Signup#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Signup extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Signup() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Signup.
     */
    // TODO: Rename and change types and number of parameters
    public static Signup newInstance(String param1, String param2) {
        Signup fragment = new Signup();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_signup, container, false);
    }

    AppCompatEditText username, mail, password, confirmPassword, phoneno;
    AppCompatButton signupButton;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    FirebaseAuth mAuth;
    DrawerLayout drawerLayout;


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        username = view.findViewById(R.id.username);
        mail = view.findViewById(R.id.mail);
        phoneno = view.findViewById(R.id.phoneNo);
        password = view.findViewById(R.id.Password);
        confirmPassword = view.findViewById(R.id.confirmPass);
        signupButton = view.findViewById(R.id.signBtn);

        drawerLayout = view.findViewById(R.id.drawerLayout);


        mAuth = FirebaseAuth.getInstance();
        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String uName = username.getText().toString();
                String email = mail.getText().toString().trim();
                String phoneNo = phoneno.getText().toString();
                String pass = password.getText().toString();
                String confirmPass = confirmPassword.getText().toString();

                // Perform input validation here

                if(pass.length() < 8){
                    password.setError("Password is To short");
                }

                mAuth.createUserWithEmailAndPassword(email, pass)
                        .addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {

//                                    // Sign in success, update UI with the signed-in user's information
                                    Toast.makeText(getContext(), "Authentication Success.",
                                            Toast.LENGTH_SHORT).show();

                                    try {
                                        firebaseDatabase = FirebaseDatabase.getInstance();
                                        databaseReference = firebaseDatabase.getReference();
                                        FirebaseUser f = FirebaseAuth.getInstance().getCurrentUser();
                                        String uId = f.getUid();
                                        //
                                        HashMap<String, Object> hashMap = new HashMap<>();
                                        hashMap.put("username", uName);
                                        hashMap.put("email", email);
                                        hashMap.put("phone", phoneNo);
                                        hashMap.put("password", pass);
                                        databaseReference.child("Users").child(uId)
                                                .setValue(hashMap);

                                        loadFragment(new Login());
                                    }catch (Exception e){
                                        Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                                    }
                                } else {
                                    // If sign in fails, display a message to the user.
                                    Toast.makeText(getContext(), "Authentication failed: " + task.getException().getMessage(),
                                            Toast.LENGTH_SHORT).show();
                                }
                            }
             });
            }
        });




        //Realtime datbase & authentication initialization
//        firebaseDatabase = FirebaseDatabase.getInstance();
//        databaseReference = firebaseDatabase.getReference();
//
//        signupButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                String uName = username.getText().toString();
//                String mail = email.getText().toString();
//                String phoneNo = phoneno.getText().toString();
//                String pass = password.getText().toString();
//                String confirmPass = confirmPassword.getText().toString();
//
//                HashMap<String, Object> hashMap = new HashMap<>();
//                hashMap.put("username", uName);
//                hashMap.put("email", mail);
//                hashMap.put("phone", phoneNo);
//                hashMap.put("password", pass);
//                databaseReference.child("Users").child(phoneNo)
//                                        .setValue(hashMap);
//                Toast.makeText(getContext(), "Data added successfully", Toast.LENGTH_SHORT).show();
//            }
//        });

    }
    //    function for loading Fragments
    private void loadFragment(Fragment fragment) {
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.Container, fragment);
        fragmentTransaction.commit();
        drawerLayout.closeDrawer(GravityCompat.START);
    }
}