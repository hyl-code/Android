package com.example.geoquiz;

import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

public class QuizActivity extends AppCompatActivity {

    private static final String TAG = "QuizActivity";
    private static final String KEY_INDEX = "index";
    private static final String QUESTION_LIST = "question_list";

    private Button mTrueButton;
    private Button mFalseButton;
    private Button mNextButton;
    private Button mPrevButton;
    private TextView mQuestionTextView;

    private Question[] mQuestionBank = new Question[]{
            new Question(R.string.question_australia, true),
            new Question(R.string.question_oceans, true),
            new Question(R.string.question_mideast, false),
            new Question(R.string.question_africa, false),
            new Question(R.string.question_americas, true),
            new Question(R.string.question_asia, true)
    };

    private int mCurrentIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate(Bundle) called");
        setContentView(R.layout.activity_quiz);

        if (savedInstanceState != null){
            mCurrentIndex = savedInstanceState.getInt(KEY_INDEX, 0);

            int[] mQuestionAnswerArray = savedInstanceState.getIntArray(QUESTION_LIST);
            for(int i = 0; i<mQuestionBank.length;i++){
                mQuestionBank[i].setAnswered(mQuestionAnswerArray[i]);
            }

        }

        mQuestionTextView = (TextView) findViewById(R.id.question_text_view);
        mQuestionTextView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                mCurrentIndex = (mCurrentIndex + 1) % mQuestionBank.length;
                updateQuestion();
            }
        });

        int question = mQuestionBank[mCurrentIndex].getTextResId();
        mQuestionTextView.setText(question);

        mTrueButton = (Button) findViewById(R.id.true_button);
        mTrueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(true);
            }
        });

        mFalseButton = (Button) findViewById(R.id.false_button);
        mFalseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(false);
            }
        });

        mNextButton = (Button) findViewById(R.id.next_button);
        mNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCurrentIndex = (mCurrentIndex + 1) % mQuestionBank.length;
                updateQuestion();
            }
        });

        mPrevButton = (Button) findViewById(R.id.prev_button);
        mPrevButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mCurrentIndex > 0){
                    mCurrentIndex = (mCurrentIndex - 1);
                    updateQuestion();
                }else{
                    Toast toast = Toast.makeText(QuizActivity.this,"This is page one.",Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.BOTTOM,0,0);
                    toast.show();
                }
            }
        });
        updateQuestion();
    }

    @Override
    public void onStart(){
        super.onStart();
        Log.d(TAG, "onStart() called");
    }

    @Override
    public void onResume(){
        super.onResume();
        Log.d(TAG, "onResume() called");
    }

    @Override
    public void onPause(){
        super.onPause();
        Log.d(TAG, "onPause() called");
    }

    @Override
    protected void onSaveInstanceState(Bundle saveInstanceState){
        super.onSaveInstanceState(saveInstanceState);
        Log.i(TAG, "onSaveInstanceState");
        saveInstanceState.putInt(KEY_INDEX,mCurrentIndex);
        int[] mQuestionAnswerArray = new int[mQuestionBank.length];
        for(int i = 0; i<mQuestionBank.length; i++){
            mQuestionAnswerArray[i] = mQuestionBank[i].isAnswered();
        }
        saveInstanceState.putIntArray(QUESTION_LIST,mQuestionAnswerArray);
    }

    private void updateQuestion(){
        int question = mQuestionBank[mCurrentIndex].getTextResId();
        mQuestionTextView.setText(question);
        setButtons();
    }

    private void setButtons(){
        if(mQuestionBank[mCurrentIndex].isAnswered() > 0){
            mTrueButton.setEnabled(false);
            mFalseButton.setEnabled(false);
        }else{
            mTrueButton.setEnabled(true);
            mFalseButton.setEnabled(true);
        }
    }

    public void onStop(){
        super.onStop();
        Log.d(TAG, "onStop() called");
    }

    public void onDestroy(){
        super.onDestroy();
        Log.d(TAG, "onDestroy() called");
    }

    private void checkAnswer(boolean userPressTrue){
        boolean answerIsTrue = mQuestionBank[mCurrentIndex].isAnswerTrue();
        int messageResId = 0;
        if(userPressTrue == answerIsTrue){
            mQuestionBank[mCurrentIndex].setAnswered(2);
            messageResId = R.string.incorrect_toast;
        }else{
            mQuestionBank[mCurrentIndex].setAnswered(1);
            messageResId = R.string.incorrect_toast;
        }
        setButtons();
        Toast.makeText(this,messageResId,Toast.LENGTH_SHORT).show();
    }

}
