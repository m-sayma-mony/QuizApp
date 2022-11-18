package com.example.quizapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button true_Btn;
    private Button false_Btn;
    private TextView question_Text_View;
    private ImageButton prvBtn;
    private ImageButton nextBtn;


    private int currentQuestionIndex = 0;

    private Question[] questionsBank = new Question[]{
            new Question(R.string.question_body, false),
            new Question(R.string.question_world, true),
            new Question(R.string.question_space, true),
            new Question(R.string.question_crocodiles, false),
            new Question(R.string.question_clock, false),
            new Question(R.string.question_river, true),
            new Question(R.string.question_mass, true),
            new Question(R.string.question_mango, true),
            new Question(R.string.question_pole, false),
            new Question(R.string.question_cricket, false)
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        false_Btn = findViewById(R.id.falseBtn);
        true_Btn = findViewById(R.id.trueBtn);
        question_Text_View = findViewById(R.id.questionTextView);
        prvBtn = findViewById(R.id.prv_btn);
        nextBtn = findViewById(R.id.next_btn);

        true_Btn.setOnClickListener(this);
        false_Btn.setOnClickListener(this);
        prvBtn.setOnClickListener(this);
        nextBtn.setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){

            case R.id.favorite:
                startActivity(new Intent(MainActivity.this, FavoritePage.class));
                break;

            case R.id.history:
                startActivity(new Intent(MainActivity.this, HistoryPage.class));
                break;

            case R.id.setting:
                startActivity(new Intent(MainActivity.this, SettingPage.class));
                break;

            case R.id.exit:
                finish();
                break;

            default:
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.trueBtn:
                checkAnswer(true);
                break;

            case R.id.falseBtn:
                checkAnswer(false);
                break;

            case R.id.next_btn:
                currentQuestionIndex = (currentQuestionIndex + 1) % questionsBank.length;
                updateQuestion();
                break;

            case R.id.prv_btn:
                if (currentQuestionIndex > 0){
                    currentQuestionIndex = (currentQuestionIndex - 1) % questionsBank.length;
                    updateQuestion();
                }
                break;
        }
    }
    private void updateQuestion(){
        Log.d("current", "onclick" +currentQuestionIndex);
        question_Text_View.setText(questionsBank[currentQuestionIndex].getAnswerResId());
    }
    private void checkAnswer(boolean userChoosenCorrect){
        boolean answerIsTrue = questionsBank[currentQuestionIndex].isAnswerTrue();

        int toastMassageId = 0;
        if(userChoosenCorrect == answerIsTrue){
            toastMassageId = R.string.correct_ans;
        }
        else{
            toastMassageId = R.string.wrong_ans;
        }
        Toast.makeText(MainActivity.this, toastMassageId, Toast.LENGTH_SHORT).show();
    }
}