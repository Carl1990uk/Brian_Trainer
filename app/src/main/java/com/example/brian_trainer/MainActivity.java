package com.example.brian_trainer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.activity.ComponentActivity;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    Button goButton;
    ArrayList<Integer> answer = new ArrayList<Integer>();
    int locationAnswer;
    TextView resultTextView;
    int score = 0;
    int numberOfQ = 0;
    TextView scoreTextView;
    Button button0;
    Button button1;
    Button button2;
    Button button3;
    TextView sumTextView;
    TextView timerTextView;
    Button playAgain;
    ConstraintLayout gameLayout;

    public void playAgain(View view){
        score = 0;
        numberOfQ = 0;
        timerTextView.setText("30s");
        scoreTextView.setText(Integer.toString(score)+"/"+Integer.toString(numberOfQ));
        newQuestion();
        playAgain.setVisibility(View.INVISIBLE);

        new CountDownTimer(5100, 100){

            @Override
            public void onTick(long l) {
                timerTextView.setText(String.valueOf(l/1000)+ "s");
            }

            @Override
            public void onFinish() {
                resultTextView.setText("Done!");
                playAgain.setVisibility(View.VISIBLE);
            }
        }.start();
    }
    public void chooseAnswer(View view){
        if(Integer.toString(locationAnswer).equals(view.getTag().toString())){
            resultTextView.setText("Correct!");
            score++;
        }else{
            resultTextView.setText("Wrong");
        }
        numberOfQ++;
        scoreTextView.setText(Integer.toString(score)+"/"+Integer.toString(numberOfQ));
        newQuestion();
    }
    public void startButton(View view){
        goButton.setVisibility(View.INVISIBLE);
        gameLayout.setVisibility(View.VISIBLE);
        playAgain(findViewById(R.id.timeTextView));
    }
    public void newQuestion(){
        Random rand = new Random();

        int a = rand.nextInt(21);
        int b = rand.nextInt(21);

        sumTextView.setText(Integer.toString(a) + " + " + Integer.toString(b));
        locationAnswer = rand.nextInt(4);
        answer.clear();
        for(int i = 0; i < 4; i++){
            if(i == locationAnswer){
                answer.add(a+b);
            }else{
                int wrongAnswer = rand.nextInt(41);
                while(wrongAnswer == a + b){
                    wrongAnswer =rand.nextInt(41);
                }
                answer.add(wrongAnswer);
            }
        }
        button0.setText(Integer.toString(answer.get(0)));
        button1.setText(Integer.toString(answer.get(1)));
        button2.setText(Integer.toString(answer.get(2)));
        button3.setText(Integer.toString(answer.get(3)));
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sumTextView = findViewById(R.id.sumTextView);
        button0 = findViewById(R.id.button0);
        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        resultTextView = findViewById(R.id.resultTextView);
        scoreTextView = findViewById(R.id.scoreTextView);
        timerTextView = findViewById(R.id.timeTextView);
        playAgain = findViewById(R.id.reset);
        gameLayout = findViewById(R.id.gameLayout);
        goButton = findViewById(R.id.goButton);

        goButton.setVisibility(View.VISIBLE);
        gameLayout.setVisibility(View.INVISIBLE);

    }
}