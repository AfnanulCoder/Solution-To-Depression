package com.afnanulcoder.aspirant;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Profile extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    String theType = "Please Wait and Try Again";

    //---------------------------------------Need Help Views

    String nName, nAge, nEmail, nPassword, nSomething, nGender, nLevel;

    //------------------------------------------------------

    //------------------------------------Want To Help Views

    String wName, wAge, wEmail, wPassword, wGender;

    //------------------------------------------------------

    TextView nameET, typeET, ageET, genderET, emailET, somethingET, levelET;
    ImageView propic;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);


        nameET = findViewById(R.id.nameID);
        typeET = findViewById(R.id.typeID);
        levelET = findViewById(R.id.levelID);
        ageET = findViewById(R.id.ageID);
        genderET = findViewById(R.id.genderID);
        emailET = findViewById(R.id.emailID);
        somethingET = findViewById(R.id.somethingID);
        propic = findViewById(R.id.proPicID);


        mAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();

        databaseReference = firebaseDatabase.getReference().child("UserList").child(mAuth.getUid());

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                theType = dataSnapshot.child("type").getValue().toString();
                if(theType.equals("Need"))
                {
                    nName = dataSnapshot.child("nName").getValue().toString();
                    nAge = dataSnapshot.child("nAge").getValue().toString();
                    nEmail = dataSnapshot.child("nEmail").getValue().toString();
                    nSomething = dataSnapshot.child("nSomething").getValue().toString();
                    nGender = dataSnapshot.child("nGender").getValue().toString();
                    nLevel = dataSnapshot.child("nLevel").getValue().toString();

                    if(nGender.equals("Female"))
                    {
                        propic.setImageResource(R.drawable.female_pro_pic);
                    }
                    else {
                        propic.setImageResource(R.drawable.male_pro_pic);
                    }
                    nameET.setText(nName);
                    typeET.setText(theType+" Help");
                    levelET.setText("Level "+nLevel);
                    ageET.setText("Age: "+nAge);
                    genderET.setText("Gender: "+nGender);
                    emailET.setText(nEmail);
                    somethingET.setText(nSomething);

                }else if(theType.equals("WantTo")){
                    wName = dataSnapshot.child("wName").getValue().toString();
                    wAge = dataSnapshot.child("wAge").getValue().toString();
                    wEmail = dataSnapshot.child("wEmail").getValue().toString();
                    wGender = dataSnapshot.child("wGender").getValue().toString();
                    if(wGender.equals("Female"))
                    {
                        propic.setImageResource(R.drawable.female_pro_pic);
                    }
                    else {
                        propic.setImageResource(R.drawable.male_pro_pic);
                    }
                    nameET.setText(wName);
                    typeET.setText(theType+" Help");
                    ageET.setText("Age: "+wAge);
                    genderET.setText("Gender: "+wGender);
                    emailET.setText(wEmail);
                    somethingET.setText("");

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError)
            {

            }
        });




    }

    public void TheLogOut(View view)
    {
        FirebaseAuth.getInstance().signOut();
        finish();
        Intent intent = new Intent(Profile.this, MainActivity.class);
        startActivity(intent);
    }

    public void GoToEditProfile(View view)
    {

    }
}
