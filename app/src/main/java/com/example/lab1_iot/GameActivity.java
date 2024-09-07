package com.example.lab1_iot;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GameActivity extends AppCompatActivity {

    private String[] words = {"FIBRA", "REDES", "ANTENA", "PROPA", "CLOUD", "TELECO"};
    private String selectedWord;
    private List<TextView> letterViews;
    private ImageView towerImage;
    private GridLayout lettersGrid;
    private int wrongGuesses;
    private int maxGuesses = 6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        towerImage = findViewById(R.id.tower_image);
        lettersGrid = findViewById(R.id.letters_grid);
        letterViews = new ArrayList<>();

        setupNewGame();

        Button newGameButton = findViewById(R.id.btn_new_game);
        newGameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setupNewGame();
            }
        });
    }

    private void setupNewGame() {
        // Escoge una palabra al azar
        selectedWord = words[new Random().nextInt(words.length)];
        wrongGuesses = 0;

        // Restablece la imagen del ahorcado
        towerImage.setImageResource(R.drawable.tower_image);  // Imagen inicial

        // Configura la palabra oculta
        setupWordLayout();

        // Genera los botones de letras
        setupLetterButtons();
    }

    private void setupWordLayout() {
        LinearLayout wordLayout = findViewById(R.id.word_layout);
        wordLayout.removeAllViews();
        letterViews.clear();

        for (int i = 0; i < selectedWord.length(); i++) {
            TextView letterView = new TextView(this);
            letterView.setText("_ ");
            letterView.setTextSize(24);
            wordLayout.addView(letterView);
            letterViews.add(letterView);
        }
    }

    private void setupLetterButtons() {
        lettersGrid.removeAllViews();
        for (char letter = 'A'; letter <= 'Z'; letter++) {
            Button letterButton = new Button(this);
            letterButton.setText(String.valueOf(letter));
            letterButton.setTextSize(16);
            GridLayout.LayoutParams params = new GridLayout.LayoutParams();
            params.width = 120;  // Ancho del botón
            params.height = 120; // Alto del botón
            letterButton.setLayoutParams(params);

            letterButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    letterButton.setEnabled(false);
                    checkLetter(((Button) v).getText().toString());
                }
            });
            lettersGrid.addView(letterButton);
        }
    }

    private void checkLetter(String letter) {
        boolean correctGuess = false;

        for (int i = 0; i < selectedWord.length(); i++) {
            if (selectedWord.charAt(i) == letter.charAt(0)) {
                letterViews.get(i).setText(letter);
                correctGuess = true;
            }
        }

        if (!correctGuess) {
            wrongGuesses++;
            updateHangmanImage();
        }

        checkGameStatus();
    }

    private void updateHangmanImage() {
        int[] towerImages = {
                R.drawable.head, R.drawable.body, R.drawable.arm_right,
                R.drawable.arm_left, R.drawable.leg_left, R.drawable.leg_right
        };

        if (towerImage != null) {
            // Cambia el tamaño del ImageView aquí
            towerImage.getLayoutParams().width = 750;  // Ajusta el ancho (por ejemplo, 300px)
            towerImage.getLayoutParams().height = 750; // Ajusta el alto (por ejemplo, 300px)
            towerImage.requestLayout();
        if (wrongGuesses <= maxGuesses) {
            towerImage.setImageResource(towerImages[wrongGuesses - 1]);
        }
        }
    }

    private void checkGameStatus() {
        boolean won = true;

        for (TextView letterView : letterViews) {
            if (letterView.getText().equals("_ ")) {
                won = false;
                break;
            }
        }

        if (won) {
            Toast.makeText(this, "¡Ganaste!", Toast.LENGTH_LONG).show();
        } else if (wrongGuesses >= maxGuesses) {
            Toast.makeText(this, "¡Perdiste!", Toast.LENGTH_LONG).show();
        }
    }
}
