package com.example.android.braintrainer;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    Button startButton;
    ArrayList<Integer> answers = new ArrayList<Integer>();
    int locationOfCorrectAnswers;
    int score = 0;
    int numberOfQuestions = 0;
    TextView resultTextView;
    TextView sumTextView;
    TextView pointsTextView;
    TextView timerTextView;
    RelativeLayout gameRelativeLayout;

    Button button0;
    Button button1;
    Button button2;
    Button button3;
    Button playAgainButton;


    public void playAgain(View view){
        score = 0;
        numberOfQuestions = 0;
        timerTextView.setText("30s");
        pointsTextView.setText("0/0");
        resultTextView.setText("");
        playAgainButton.setVisibility(View.INVISIBLE);
        generatedQuestions();

        new CountDownTimer(31000, 1000){

            @Override
            public void onTick(long millisUntilFinished) {

                timerTextView.setText(String.valueOf(millisUntilFinished/1000) + "s");
            }

            @Override
            public void onFinish() {
                playAgainButton.setVisibility(View.VISIBLE);
                timerTextView.setText("0s");
                resultTextView.setText("Your Score" + Integer.toString(score) + "/" + Integer.toString(numberOfQuestions));
            }
        }.start();


    }
    public void generatedQuestions(){
        Random rand = new Random();

        int a = rand.nextInt(21);
        int b = rand.nextInt(21);

        sumTextView.setText(Integer.toString(a) + " + " + Integer.toString(b));

        locationOfCorrectAnswers = rand.nextInt(4);

        answers.clear();
        int incorrectAnswer;
        for (int i =0; i<4;i++){
            if(i == locationOfCorrectAnswers){
                answers.add(a + b);
            }else {
                incorrectAnswer = rand.nextInt(41);
                while (incorrectAnswer == a+b){
                    incorrectAnswer = rand.nextInt(41);
                }
                answers.add(incorrectAnswer);
            }
        }

        button0.setText(Integer.toString((answers.get(0))));
        button1.setText(Integer.toString((answers.get(1))));
        button2.setText(Integer.toString((answers.get(2))));
        button3.setText(Integer.toString((answers.get(3))));

    }

    public void chooseAnswer(View view){

            if(view.getTag().toString().equals(Integer.toString(locationOfCorrectAnswers))){

                score++;
                resultTextView.setText("Correct!");
            }else {

                resultTextView.setText("Wrong!");
            }

            numberOfQuestions++;
            pointsTextView.setText(Integer.toString(score) + "/" + Integer.toString(numberOfQuestions));
            generatedQuestions();
    }

    public void start(View view){
        startButton.setVisibility(view.INVISIBLE);
        gameRelativeLayout.setVisibility(RelativeLayout.VISIBLE);

        playAgain(findViewById(R.id.playAgainButton));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startButton = (Button) findViewById(R.id.startButton);
        playAgainButton = (Button) findViewById(R.id.playAgainButton);

        sumTextView = (TextView) findViewById(R.id.sumTextView);
        resultTextView = (TextView) findViewById(R.id.resultTextView);
        pointsTextView = (TextView) findViewById(R.id.pointsTextView);
        timerTextView = (TextView) findViewById(R.id.timerTextView);
        gameRelativeLayout = (RelativeLayout) findViewById(R.id.gameRelativeLayout);

         button0 = (Button) findViewById(R.id.button0);
         button1 = (Button) findViewById(R.id.button1);
         button2 = (Button) findViewById(R.id.button2);
         button3 = (Button) findViewById(R.id.button3);


    }
}
