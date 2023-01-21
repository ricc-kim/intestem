package com.example.carintel;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class SelectRegistrationActivity extends AppCompatActivity {

    private Button auctioneerButton, nonAuctioneerButton;
    private TextView backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_registration);

        auctioneerButton = findViewById(R.id.auctioneerButton);
        nonAuctioneerButton = findViewById(R.id.nonAuctioneerButton);
        backButton = findViewById(R.id.backButton);

        auctioneerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SelectRegistrationActivity.this, AuctioneerRegistrationActivity.class);
                startActivity(intent);
            }
        });

        nonAuctioneerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SelectRegistrationActivity.this, OthersRegistrationActivity.class);
                startActivity(intent);
            }
        });

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SelectRegistrationActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }
}