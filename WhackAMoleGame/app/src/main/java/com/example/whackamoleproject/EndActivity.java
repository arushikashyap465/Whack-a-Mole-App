package com.example.whackamoleproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class EndActivity extends AppCompatActivity {

    TextView txtGameOver, txtReason, txtScore;
    Button btnMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end);

        txtGameOver = findViewById(R.id.txtGameOver);
        txtReason = findViewById(R.id.txtReason);
        txtScore = findViewById(R.id.txtScore);
        btnMenu = findViewById(R.id.btnMenu);

        Intent intent = getIntent();
        final int scoreValue = intent.getExtras().getInt("Score");
        final String reasonValue = intent.getExtras().getString("Reason");

        txtScore.setText("Your score was: "+" "+String.valueOf(scoreValue));
        txtReason.setText(reasonValue);

        btnMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EndActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });


    }
}