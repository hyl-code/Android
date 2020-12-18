package com.example.geoquiz;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class QuizActivity extends AppCompatActivity {

    private static final String TAG = "QuizActivity";
    private static final String KEY_INDEX = "index";
    private static final String QUESTION_LIST = "question_list";
    private static final int REQUEST_CODE_CHEAT = 0;
    private static final String bug2 = "bug2";
    private static final String bug3 = "bug3";
    private static final String EXTRA_CHEAT_NUMS = "com.example.android.geoquiz.cheat_nums";
    private static int Cheat_Nums = 3;

    private Button mTrueButton;
    private Button mFalseButton;
    private Button mPrevButton;
    private Button mCheatButton;
    private Button mNextButton;
    private float mTrueAnswer = 0;
    private float mFalseAnswer = 0;
    private float mCorrectAnswer = 0;
    private float answerLength = 0;
    private TextView mQuestionTextView;
    private TextView mCheatNumsTextView;

    private Question[] mQuestionBank = new Question[]{
            new Question(R.string.question_australia, true),
            new Question(R.string.question_oceans, true),
            new Question(R.string.question_mideast, false),
            new Question(R.string.question_africa, false),
            new Question(R.string.question_americas, true),
            new Question(R.string.question_asia, true)
    };

    private int mCurrentIndex = 0;
    private boolean[] mIsCheater = new boolean[mQuestionBank.length];

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate(Bundle) called");
        setContentView(R.layout.activity_quiz);

        if (savedInstanceState != null) {
            mCurrentIndex = savedInstanceState.getInt(KEY_INDEX, 0);
            mIsCheater[mCurrentIndex] = savedInstanceState.getBoolean(bug2, false);

            int[] mQuestionAnswerArray = savedInstanceState.getIntArray(QUESTION_LIST);
            for (int i = 0; i < mQuestionBank.length; i++) {
                mQuestionBank[i].setAnswered(mQuestionAnswerArray[i]);
            }
            mIsCheater[mCurrentIndex] = savedInstanceState.getBoolean(bug2, false);
        }

        for(int i = 0; i < mQuestionBank.length; i++){
            mIsCheater[i] = false;
        }

        mQuestionTextView = (TextView) findViewById(R.id.question_text_view);
        mQuestionTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCurrentIndex = (mCurrentIndex + 1) % mQuestionBank.length;
                updateQuestion();
            }
        });

        int question = mQuestionBank[mCurrentIndex].getTextResId();
        mQuestionTextView.setText(question);
        mCheatNumsTextView = (TextView)findViewById(R.id.cheat_nums);

        mTrueButton = (Button) findViewById(R.id.true_button);
        mTrueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(true);
                mTrueAnswer++;
            }
        });

        mFalseButton = (Button) findViewById(R.id.false_button);
        mFalseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(false);
                mFalseAnswer++;
            }
        });

        mPrevButton = (Button) findViewById(R.id.prev_button);
        mPrevButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mCurrentIndex > 0) {
                    mCurrentIndex = (mCurrentIndex - 1);
                    updateQuestion();
                } else {
                    Toast toast = Toast.makeText(QuizActivity.this, "This is page one.", Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.BOTTOM, 0, 0);
                    toast.show();
                }
                updateQuestion();
            }
        });

        mCheatButton = (Button) findViewById(R.id.cheat_button);
        mCheatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean answerIsTrue = mQuestionBank[mCurrentIndex].isAnswerTrue();
                Intent intent = CheatActivity.newIntent(QuizActivity.this, answerIsTrue);
                intent.putExtra(EXTRA_CHEAT_NUMS, Cheat_Nums);
                startActivityForResult(intent, REQUEST_CODE_CHEAT);
            }
        });

        mNextButton = (Button) findViewById(R.id.next_button);
        mNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCurrentIndex = (mCurrentIndex + 1) % mQuestionBank.length;
                mIsCheater[mCurrentIndex] = false;
                if (answerLength == mQuestionBank.length) {
                    double i = mCorrectAnswer / mQuestionBank.length;
                    double y = i * 100;
                    Log.i(TAG, "onCreate:   i" + i);
                    Toast.makeText(QuizActivity.this, String.valueOf(y), Toast.LENGTH_SHORT).show();
                }
                updateQuestion();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode,resultCode,data);
        if (requestCode != Activity.RESULT_OK) {
            return;
        }

        if (requestCode == REQUEST_CODE_CHEAT) {
            if (data == null) {
                return;
            }
            mIsCheater[mCurrentIndex] = CheatActivity.wasAnswerShown(data);
            Cheat_Nums = data.getIntExtra(EXTRA_CHEAT_NUMS, 0);
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        if(Cheat_Nums == 0){
            mCheatButton.setEnabled(false);
        }
        mCheatNumsTextView.setText("Remaining number of cheating:" + Cheat_Nums);
        Log.d(TAG, "onStart() called");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG, "onResume() called");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d(TAG, "onPause() called");
    }

    @Override
    protected void onSaveInstanceState(Bundle saveInstanceState) {
        super.onSaveInstanceState(saveInstanceState);
        Log.i(TAG, "onSaveInstanceState");
        saveInstanceState.putInt(KEY_INDEX, mCurrentIndex);

        int[] mQuestionAnswerArray = new int[mQuestionBank.length];
        for (int i = 0; i < mQuestionBank.length; i++) {
            mQuestionAnswerArray[i] = mQuestionBank[i].isAnswered();
        }
        saveInstanceState.putIntArray(QUESTION_LIST, mQuestionAnswerArray);
        saveInstanceState.putBooleanArray(bug3, mIsCheater);
    }

    private void updateQuestion() {
        int question = mQuestionBank[mCurrentIndex].getTextResId();
        mQuestionTextView.setText(question);
        setButtons();
    }

    private void setButtons() {
        if (mQuestionBank[mCurrentIndex].isAnswered() > 0) {
            mTrueButton.setEnabled(false);
            mFalseButton.setEnabled(false);
        } else {
            mTrueButton.setEnabled(true);
            mFalseButton.setEnabled(true);
        }
    }

    public void onStop() {
        super.onStop();
        Log.d(TAG, "onStop() called");
    }

    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy() called");
    }

    private void checkAnswer(boolean userPressTrue) {
        boolean answerIsTrue = mQuestionBank[mCurrentIndex].isAnswerTrue();

        int messageResId = 0;

        if (mIsCheater[mCurrentIndex]) {
            messageResId = R.string.judgment_toast;
        } else {
                if (userPressTrue == answerIsTrue) {
                    mQuestionBank[mCurrentIndex].setAnswered(2);
                    messageResId = R.string.correct_toast;
                    mCorrectAnswer++;
                    Log.i(TAG, "checkAnswer:  mCorrectAnswer    " + mCorrectAnswer);
                } else {
                    mQuestionBank[mCurrentIndex].setAnswered(1);
                    messageResId = R.string.incorrect_toast;
                }
            }
            setButtons();
            Toast.makeText(this, messageResId, Toast.LENGTH_SHORT).show();
        }
    }