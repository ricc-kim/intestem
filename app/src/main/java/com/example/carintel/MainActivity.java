package com.example.carintel;

import android.view.MenuItem;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.example.carintel.adapter.UserAdapter;
import com.example.carintel.model.User;
import com.google.android.gms.dynamic.IFragmentWrapper;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import de.hdodenhof.circleimageview.CircleImageView;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, RecyclerViewInterface {

    private DrawerLayout drawerLayout;
    private Toolbar toolbar;
    private NavigationView navigationView;
    private CircleImageView nav_profile_image;
    private TextView nav_fullname, nav_email, nav_phonenumber, nav_type, logout;
    private DatabaseReference userRef;
    private RecyclerView recyclerView;
    private ProgressBar progressBar;
    private List<User> userList;
    private UserAdapter userAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Intestem");

        drawerLayout = findViewById(R.id.drawerLayout);
        navigationView = findViewById(R.id.navigationView);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(MainActivity.this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);

        progressBar = findViewById(R.id.progressBar);
        recyclerView = findViewById(R.id.recyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setReverseLayout(true);
        layoutManager.setStackFromEnd(true);
        recyclerView.setLayoutManager(layoutManager);

        userList = new ArrayList<>();
        userAdapter = new UserAdapter(MainActivity.this, userList, this);

        recyclerView.setAdapter(userAdapter);

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference()
                .child("users").child(FirebaseAuth.getInstance().getCurrentUser().getUid());
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String type = snapshot.child("type").getValue().toString();
                if (type.equals("Auctioneer")){
                    readOthers();
                }else {
                    readAuctioneers();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        nav_profile_image = navigationView.getHeaderView(0).findViewById(R.id.nav_user_image);
        nav_fullname = navigationView.getHeaderView(0).findViewById(R.id.nav_user_fullname);
        nav_email = navigationView.getHeaderView(0).findViewById(R.id.nav_user_email);
        nav_phonenumber = navigationView.getHeaderView(0).findViewById(R.id.nav_user_phonenumber);
        nav_type = navigationView.getHeaderView(0).findViewById(R.id.nav_user_type);

        userRef = FirebaseDatabase.getInstance().getReference().child("users").child(
                FirebaseAuth.getInstance().getCurrentUser().getUid());
        userRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()){
                    String name = snapshot.child("name").getValue().toString();
                    nav_fullname.setText(name);

                    String email = snapshot.child("email").getValue().toString();
                    nav_email.setText(email);

                    String phonenumber = snapshot.child("phonenumber").getValue().toString();
                    nav_phonenumber.setText(phonenumber);

                    String type = snapshot.child("type").getValue().toString();
                    nav_type.setText(type);

                    if (snapshot.hasChild("profilepictureurl")){
                        String imageUrl = snapshot.child("profilepictureurl").getValue().toString();
                        Glide.with(getApplicationContext()).load(imageUrl).into(nav_profile_image);
                    }else {
                        nav_profile_image.setImageResource(R.drawable.profile_image);
                    }

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



    }

    private void readAuctioneers() {
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("users");
        Query query = reference.orderByChild("type").equalTo("Auctioneer");
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                userList.clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                    User user = dataSnapshot.getValue(User.class);
                    userList.add(user);
                }
                userAdapter.notifyDataSetChanged();
                progressBar.setVisibility(View.GONE);

                if (userList.isEmpty()){
                    Toast.makeText(MainActivity.this, "No Users", Toast.LENGTH_SHORT).show();
                    progressBar.setVisibility(View.GONE);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    private void readOthers() {
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("users");
        Query query = reference.orderByChild("type").equalTo("Others");
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                userList.clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                    User user = dataSnapshot.getValue(User.class);
                    userList.add(user);
                }
                userAdapter.notifyDataSetChanged();
                progressBar.setVisibility(View.GONE);

                if (userList.isEmpty()){
                    Toast.makeText(MainActivity.this, "No Users", Toast.LENGTH_SHORT).show();
                    progressBar.setVisibility(View.GONE);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.profile:
                Intent intent = new Intent(MainActivity.this, ProfileActivity.class);
                startActivity(intent);
                break;

            case R.id.logout:
                Intent intent1 = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent1);
                break;

        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return false;
    }

    @Override
    public void onItemClick(int position) {
        Intent intent = new Intent(MainActivity.this, MainActivity2.class);
        startActivity(intent);

    }
}