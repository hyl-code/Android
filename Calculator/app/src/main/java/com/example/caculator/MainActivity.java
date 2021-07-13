package com.example.caculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button mOne;
    private Button mTwo;
    private Button mThree;
    private Button mFour;
    private Button mFive;
    private Button mSix;
    private Button mSeven;
    private Button mEight;
    private Button mNine;
    private Button mZero;
    private Button mClean;
    private Button mBackspace;
    private Button mMod;
    private Button mDivide;
    private Button mMultiply;
    private Button mSub;
    private Button mAdd;
    private Button mEqual;
    private Button mDot;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mOne = (Button) findViewById(R.id.one);
        mTwo = (Button) findViewById(R.id.two);
        mThree = (Button) findViewById(R.id.three);
        mFour = (Button) findViewById(R.id.four);
        mFive = (Button) findViewById(R.id.five);
        mSix = (Button) findViewById(R.id.six);
        mSeven = (Button) findViewById(R.id.seven);
        mEight = (Button) findViewById(R.id.eight);
        mNine = (Button) findViewById(R.id.nine);
        mZero = (Button) findViewById(R.id.zero);
        mClean = (Button) findViewById(R.id.clean);
        mBackspace = (Button) findViewById(R.id.backspace);
        mMod = (Button) findViewById(R.id.mod);
        mDivide = (Button) findViewById(R.id.divide);
        mMultiply = (Button) findViewById(R.id.multiply);
        mSub = (Button) findViewById(R.id.subtract);
        mAdd = (Button) findViewById(R.id.add);
        mEqual = (Button) findViewById(R.id.equal);
        mDot = (Button) findViewById(R.id.point);
    }
}