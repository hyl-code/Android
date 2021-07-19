package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    //是否使用特殊的标题栏背景颜色，android5.0以上可以设置状态栏背景色，如果不使用则使用透明色值
    protected boolean useThemestatusBarColor = false;
    //是否使用状态栏文字和图标为暗色，如果状态栏采用了白色系，则需要使状态栏和图标为暗色，android6.0以上可以设置
    protected boolean useStatusBarColor = true;

    private TextView mResult;
    private TextView mProgress;
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
    private ImageButton mBackspace;
    //private Button mLeft;
    //private Button mRight;
    private Button mPercent;
    private Button mDivide;
    private Button mMultiply;
    private Button mSub;
    private Button mAdd;
    private Button mEqual;
    private Button mDot;

    private String text = "";
    private double res = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setStatusBar();

        mOne = findViewById(R.id.one);
        mOne.setOnClickListener(listener);

        mTwo = findViewById(R.id.two);
        mTwo.setOnClickListener(listener);

        mThree = findViewById(R.id.three);
        mThree.setOnClickListener(listener);

        mFour = findViewById(R.id.four);
        mFour.setOnClickListener(listener);

        mFive = findViewById(R.id.five);
        mFive.setOnClickListener(listener);

        mSix = findViewById(R.id.six);
        mSix.setOnClickListener(listener);

        mSeven = findViewById(R.id.seven);
        mSeven.setOnClickListener(listener);

        mEight = findViewById(R.id.eight);
        mEight.setOnClickListener(listener);

        mNine = findViewById(R.id.nine);
        mNine.setOnClickListener(listener);

        mZero = findViewById(R.id.zero);
        mZero.setOnClickListener(listener);

        mClean = findViewById(R.id.clean);
        mClean.setOnClickListener(listener);

        mBackspace = findViewById(R.id.backspace);
        mBackspace.setOnClickListener(listener);

        mDivide = findViewById(R.id.divide);
        mDivide.setOnClickListener(listener);

        mMultiply = findViewById(R.id.multiply);
        mMultiply.setOnClickListener(listener);

        mSub = findViewById(R.id.subtract);
        mSub.setOnClickListener(listener);

        mAdd = findViewById(R.id.add);
        mAdd.setOnClickListener(listener);

        mEqual = findViewById(R.id.equal);
        mEqual.setOnClickListener(listener);

        mDot = findViewById(R.id.point);
        mDot.setOnClickListener(listener);

        /*mLeft = findViewById(R.id.left);
        mLeft.setOnClickListener(listener);

        mRight = findViewById(R.id.right);
        mRight.setOnClickListener(listener);*/

        mPercent = findViewById(R.id.percent_image);
        mPercent.setOnClickListener(listener);

        mResult = findViewById(R.id.result);
        mProgress = findViewById(R.id.progress);


    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        return super.onOptionsItemSelected(item);
    }

    private View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.one :
                    text = text + String.valueOf(1);
                    mProgress.setText(text);
                    break;
                case R.id.two :
                    text = text + String.valueOf(2);
                    mProgress.setText(text);
                    break;
                case R.id.three :
                    text = text + String.valueOf(3);
                    mProgress.setText(text);
                    break;
                case R.id.four :
                    text = text +  String.valueOf(4);
                    mProgress.setText(text);
                    break;
                case R.id.five :
                    text = text +  String.valueOf(5);
                    mProgress.setText(text);
                    break;
                case R.id.six:
                    text = text +  String.valueOf(6);
                    mProgress.setText(text);
                    break;
                case R.id.seven:
                    text = text +  String.valueOf(7);
                    mProgress.setText(text);
                    break;
                case R.id.eight:
                    text = text +  String.valueOf(8);
                    mProgress.setText(text);
                    break;
                case R.id.nine:
                    text = text +  String.valueOf(9);
                    mProgress.setText(text);
                    break;
                case R.id.zero:
                    text = text + String.valueOf(0);
                    mProgress.setText(text);
                    break;
                case R.id.point:
                    text = text + ".";
                    mProgress.setText(text);
                    break;
                /*case R.id.left:
                    text  = text + "(";
                    mProgress.setText(text);
                    break;
                case R.id.right:
                    text = text + ")";
                    mProgress.setText(text);
                    break;*/
                case R.id.clean:
                    text = "";
                    mProgress.setText("0");
                    mResult.setText(text);
                    mBackspace.setEnabled(true);
                    mEqual.setEnabled(true);
                    break;
                case R.id.backspace:
                    if(text.length() == 1){
                        text = "";
                        mProgress.setText("0");
                    }else{
                        text = text.substring(0,text.length() - 1);
                        mProgress.setText(text);
                    }
                    break;
                case R.id.divide:
                    text = text +  "÷";
                    mProgress.setText(text);
                    break;
                case R.id.multiply:
                    text = text + "×";
                    mProgress.setText(text);
                    break;
                case R.id.subtract:
                    text = text +  "-";
                    mProgress.setText(text);
                    break;
                case R.id.add:
                    text = text +  "+";
                    mProgress.setText(text);
                    break;
                case R.id.percent_image:
                    if(text != "") {
                        text = text + "×0.01";
                        mProgress.setText(text);
                    }else{
                        mProgress.setText("0");
                    }
                    break;
                case R.id.equal:
                    res = result(text);
                    String end = String.valueOf(res);
                    if(end.contains(".")) {
                        int a = end.indexOf('.');
                        int count = 0;
                        for (int i = a; i < end.length(); i++) {
                            if (end.charAt(i) == '0') {
                                count++;
                            } else {
                                count = 0;
                            }
                        }
                        if (count != 0) {
                            end = end.substring(0, a + count - 1);

                        }
                    }
                    mResult.setText(end);
                    text = "";
                    mBackspace.setEnabled(false);
                    mEqual.setEnabled(false);
                    break;
            }
        }
    };

    /*public void priority(String s){
        double sec;

        String s1 = s.substring(s.lastIndexOf('(') , s.indexOf(')'));
        String s2 = s.substring(s.lastIndexOf('(') + 1, s.indexOf(')') - 1);
        sec = calculate(s2);
        s = s.replace(s1, sec + "");
    }*/


    public Double result(String s){
        double total;
        /*int i;
        a:
        for(i = s.indexOf('('); i <= s.lastIndexOf('('); i++) {
            if (!s.contains("(") && !s.contains(")")){
                break a;
            }else {
                double sec;

                String s1 = s.substring(s.lastIndexOf('(') , s.indexOf(')'));
                String s2 = s.substring(s.lastIndexOf('(') + 1, s.indexOf(')') - 1);
                sec = calculate(s2);
                s = s.replace(s1, sec + "");
            }
        }*/
        total = calculate(s);
        return total;
    }

    public Double calculate(String s){
        //分割字符然后放进数组
        String s1 = s.replace("+", "-");
        String[] str = s1.split("-");
        double total = 0;
        //遍历数组 把里面的乘除结果算出来
        for (String str1 : str) {
            if (str1.contains("×") || str1.contains("÷")) {
                double part = 0;
                for (int i = 0; i < str1.length(); ) {
                    int count = 1;
                    a:
                    for (int j = i + 1; j < str1.length(); j++) {
                        char c = str1.charAt(j);
                        if (c == '×' || c == '÷') {
                            break a;
                        } else {
                            count++;
                        }
                    }

                    //将数字截取出来
                    String s2 = str1.substring(i, i + count);
                    double d = Double.parseDouble(s2);
                    if (i == 0) {
                        part = d;
                    } else {
                        char c1 = str1.charAt(i - 1);
                        if (c1 == '×') {
                            part *= d;
                        } else if (c1 == '÷') {
                            //如果除数为0，直接返回null;
                            if (d == 0)
                                return null;
                            part /= d;
                        }
                    }
                    i += count + 1;
                }
                s = s.replace(str1, part + "");
            }
        }
        //进行加减运算
        for (int i = 0; i < s.length(); i++) {
            int count = 1;
            a:
            for (int j = i + 1; j < s.length(); j++) {
                char c = s.charAt(j);
                if (c == '+' || c == '-') {
                    break a;
                } else {
                    count++;
                }
            }
            String s3 = s.substring(i, i + count);
            double d2 = Double.parseDouble(s3);
            if (i == 0) {
                total = d2;
            } else {
                char c = s.charAt(i - 1);
                if (c == '+') {
                    total += d2;
                } else if (c == '-') {
                    total -= d2;
                }
            }
            i += count;
        }
        return total;
    }



    protected void setStatusBar() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {//5.0及以上
            View decorView = getWindow().getDecorView();
            int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
            decorView.setSystemUiVisibility(option);
            //根据上面设置是否对状态栏单独设置颜色
            if (useThemestatusBarColor) {
                getWindow().setStatusBarColor(getResources().getColor(R.color.gray));//设置状态栏背景色
            } else {
                getWindow().setStatusBarColor(Color.TRANSPARENT);//透明
            }
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {//4.4到5.0
            WindowManager.LayoutParams localLayoutParams = getWindow().getAttributes();
            localLayoutParams.flags = (WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS | localLayoutParams.flags);
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && useStatusBarColor) {//android6.0以后可以对状态栏文字颜色和图标进行修改
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
    }

}