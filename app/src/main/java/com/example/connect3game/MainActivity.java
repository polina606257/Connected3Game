package com.example.connect3game;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.gridlayout.widget.GridLayout;

public class MainActivity extends AppCompatActivity  {

    // 0 - redPlayer, 1 - yellowPlayer, 2 - empty
    int activePlayer = 0;
    int [] gameState = {2, 2, 2, 2, 2, 2, 2, 2, 2};
    int [][] winPosition = {{0,1,2}, {3,4,5}, {6,7,8}, {0,3,6}, {1,4,7}, {2,5,8}, {0,4,8}, {2,4,6}};
    boolean gameIsActive = true;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void doStep(View view) {
        ImageView counter = (ImageView) view;
        int taggedCounter = Integer.valueOf(counter.getTag().toString());

        if (gameState[taggedCounter] == 2 && gameIsActive) {

            gameState[taggedCounter] = activePlayer;

            counter.setTranslationX(-1500);

            if (activePlayer == 0) {
                counter.setImageResource(R.drawable.red);
                activePlayer = 1;
            } else {
                counter.setImageResource(R.drawable.yellow);
                activePlayer = 0;
            }

            counter.animate().translationXBy(1500).rotation(3600).setDuration(300);

            for (int[] winPosition : winPosition) {
                if (gameState[winPosition[0]] == gameState[winPosition[1]] && gameState[winPosition[1]]
                        == gameState[winPosition[2]] && gameState[winPosition[0]] != 2) {
                    String message = "";
                    if (gameState[winPosition[0]] == 0) {
                        gameIsActive = false;
                        message = "Red player";
                    } else {
                        gameIsActive = false;
                        message = "Yellow player";
                    }

                    TextView wonTextView = (TextView) findViewById(R.id.wonTextView);
                    Button playAgainButton = (Button) findViewById(R.id.playAgainButton);

                    wonTextView.setText(message + " has won!");
                    wonTextView.setVisibility(View.VISIBLE);
                    playAgainButton.setVisibility(View.VISIBLE);
                }
            }
        }
    }


    public void playAgain(View view) {
        TextView wonTextView = (TextView) findViewById(R.id.wonTextView);
        Button playAgainButton = (Button) findViewById(R.id.playAgainButton);

        wonTextView.setVisibility(View.INVISIBLE);
        playAgainButton.setVisibility(View.INVISIBLE);

        GridLayout grid = (GridLayout) findViewById(R.id.grid);

        for(int i=0; i<grid.getChildCount(); i++) {
            ImageView count = (ImageView) grid.getChildAt(i);
            count.setImageDrawable(null);
        }

        activePlayer = 0;

        for (int i = 0; i < 9; i++) {
            gameState[i] = 2;
        }

        gameIsActive = true;
    }
}
