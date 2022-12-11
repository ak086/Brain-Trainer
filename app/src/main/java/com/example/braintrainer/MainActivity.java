package com.example.braintrainer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Button goButton;
    int locationOfCurrentAnswers;
    TextView resultTextView;
    int score=0;
    int numberOfQuestions=0;
    TextView result;
    Button option1;
    Button option2;
    Button option3;
    Button option4;
    TextView questionTextView;
    TextView timerTextView;
    ArrayList<Integer> answers=new ArrayList<Integer>();
    Button playAgainButton;
    ConstraintLayout gameLayout;
    public void goButton(View view){
        goButton.setVisibility(View.INVISIBLE);
        playAgain(findViewById(R.id.playAgainButton));
        gameLayout.setVisibility(View.VISIBLE);
    }

    public void chooseAnswer(View view){
        if(Integer.toString(locationOfCurrentAnswers).equals(view.getTag())){
            //Log.i("Answer Matched","CORRECT");
            resultTextView.setText("CORRECT");
            resultTextView.setVisibility(View.VISIBLE);
            score++;
        }else{
           Log.i("Wrong Answer","INCORRECT");
           resultTextView.setText("WRONG");
           resultTextView.setVisibility(View.VISIBLE);

        }
        numberOfQuestions++;
        result.setText(score+"/"+numberOfQuestions);
        newQuestions();
    }



    public void  newQuestions(){
        Random random=new Random();
        int a=random.nextInt(21);
        int b=random.nextInt(21);
        questionTextView.setText(Integer.toString(a)+"+"+Integer.toString(b));
        locationOfCurrentAnswers= random.nextInt(4);

        answers.clear();
        for(int i=0;i<4;i++){
            if(i==locationOfCurrentAnswers){
                answers.add(a+b);
            }else{
                int wrongAnswer=random.nextInt(41);
                while(wrongAnswer==a+b) {
                    wrongAnswer=random.nextInt(41);
                }
                answers.add(wrongAnswer);
            }

        }
        option1.setText(Integer.toString(answers.get(0)));
        option2.setText(Integer.toString(answers.get(1)));
        option3.setText(Integer.toString(answers.get(2)));
        option4.setText(Integer.toString(answers.get(3)));


    }

    public void playAgain(View view){
        score=0;
        numberOfQuestions=0;
        timerTextView.setText("30s");
        newQuestions();
        result.setText(score+"/"+numberOfQuestions);
        playAgainButton.setVisibility(View.INVISIBLE);
        resultTextView.setText("");
        CountDownTimer countDownTimer=new CountDownTimer(30100,1000) {
            @Override
            public void onTick(long l) {

                timerTextView.setText(String.valueOf(l/1000)+"s");
            }

            @Override
            public void onFinish() {
                resultTextView.setText("DONE");
                playAgainButton.setVisibility(View.VISIBLE);
            }
        }.start();

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        goButton = findViewById(R.id.button);
        questionTextView = findViewById(R.id.textView3);
        resultTextView = findViewById(R.id.resultTextView);
        result = findViewById(R.id.textView2);
         option1 = findViewById(R.id.button0);
         option2 = findViewById(R.id.button1);
         option3 = findViewById(R.id.button2);
         option4 = findViewById(R.id.button3);
        timerTextView = findViewById(R.id.textView);
        playAgainButton=findViewById(R.id.playAgainButton);
        gameLayout=findViewById(R.id.gameLayout);

        goButton.setVisibility(View.VISIBLE);


    }
}