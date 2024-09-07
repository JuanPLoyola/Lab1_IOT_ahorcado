package com.example.lab1_iot;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText etUsername;
    private Button btnPlay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etUsername = findViewById(R.id.et_username);
        btnPlay = findViewById(R.id.btn_play);

        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = etUsername.getText().toString();

                if (username.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Por favor, ingrese su nombre", Toast.LENGTH_SHORT).show();
                } else {
                    // Aquí puedes iniciar una nueva actividad o realizar alguna acción
                    Intent intent = new Intent(MainActivity.this, GameActivity.class);
                    intent.putExtra("USERNAME", username);
                    startActivity(intent);
                }
            }
        });
    }
}
