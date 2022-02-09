package com.example.retrofit;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.retrofit.bean.Bean;
import com.example.retrofit.retrofit.IBeanService;
import com.example.retrofit.retrofit.Retrofit;

import org.w3c.dom.Text;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText mEditText;
    private TextView mTextView;
    private Button mButton;

    private IBeanService service;
    private Retrofit retrofit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mEditText = (EditText) findViewById(R.id.et_input);
        mTextView = (TextView) findViewById(R.id.tv_2);
        mButton = (Button) findViewById(R.id.bt_query);

        retrofit = Retrofit.getRetrofit();
        service = retrofit.getService();

        mButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.bt_query:
                query();
                break;
        }
    }

    private void query(){
        mTextView.setText("");
        String num = mEditText.getText().toString();

        if(num.isEmpty()){
            Toast.makeText(MainActivity.this,"请输入ID", Toast.LENGTH_SHORT).show();
            return;
        }

        Call<Bean> call = service.getMenuById(num);
        call.enqueue(new Callback<Bean>() {
            @Override
            public void onResponse(Call<Bean> call, Response<Bean> response) {
                if(response.isSuccessful()){
                    Bean result = response.body();
                    if(result != null){
                        String msg = result.getMessage();
                        String name = result.getName();
                        mTextView.setText("message=" + msg + "name=" + name);
                    }
                }
            }

            @Override
            public void onFailure(Call<Bean> call, Throwable t) {
                Toast.makeText(MainActivity.this, "查找失败 不存在", Toast.LENGTH_SHORT).show();
            }
        });
    }
}