package com.example.lab1_iot;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class StatisticsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics);

        // Obtener la referencia del TextView donde se mostrarán los resultados
        TextView statsList = findViewById(R.id.statsList);

        // Obtener el ArrayList de resultados que se pasó desde GameActivity
        ArrayList<String> gameResults = getIntent().getStringArrayListExtra("gameResults");

        // Verificar que el ArrayList no sea nulo antes de usarlo
        if (gameResults != null && !gameResults.isEmpty()) {
            // Mostrar los resultados en el TextView
            StringBuilder results = new StringBuilder();
            for (int i = 0; i < gameResults.size(); i++) {
                results.append("Juego ").append(i + 1).append(": ").append(gameResults.get(i)).append("\n");
            }
            statsList.setText(results.toString());
        } else {
            // Manejar el caso en que no haya resultados
            statsList.setText("No hay resultados disponibles");
        }
    }
}


