package com.example.carintel;

import android.content.Intent;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.appcompat.widget.Toolbar;
import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import de.hdodenhof.circleimageview.CircleImageView;

public class ProfileActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private TextView name, email, auctioneerId, phoneNumber, type;
    private CircleImageView profileImage;
    private Button backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        toolbar = findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Profile");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        name = findViewById(R.id.name);
        email = findViewById(R.id.email);
        auctioneerId = findViewById(R.id.auctioneerId);
        phoneNumber = findViewById(R.id.phoneNumber);
        type = findViewById(R.id.type);
        profileImage = findViewById(R.id.profileImage);
        backButton = findViewById(R.id.backButton);

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference()
                .child("users").child(FirebaseAuth.getInstance().getCurrentUser().getUid());

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()){
                    type.setText(snapshot.child("type").getValue().toString());
                    name.setText(snapshot.child("name").getValue().toString());
                    email.setText(snapshot.child("email").getValue().toString());
                    auctioneerId.setText(snapshot.child("idnumber").getValue().toString());
                    phoneNumber.setText(snapshot.child("phonenumber").getValue().toString());

                   /* Glide.with(getApplicationContext()).load(snapshot.child("profileimageurl").getValue().toString()).into(profileImage);*/

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProfileActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull final MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                return true;
            default:
        }
        return super.onOptionsItemSelected(item);
    }
}