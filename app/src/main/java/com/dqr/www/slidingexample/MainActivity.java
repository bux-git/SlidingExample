package com.dqr.www.slidingexample;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import static com.dqr.www.slidingexample.R.id.btn_start_scroll;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button mBtnStartScroll;
    private Button mBtnScroll;
    private Button mBtnPull;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mBtnStartScroll = (Button) findViewById(btn_start_scroll);
        mBtnScroll = (Button) findViewById(R.id.btnScroll);
        mBtnPull = (Button) findViewById(R.id.btnPull);

        mBtnStartScroll.setOnClickListener(this);
        mBtnScroll.setOnClickListener(this);
        mBtnPull.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_start_scroll:
                startActivity(new Intent(MainActivity.this,ScrollToExampleActivity.class));
                break;
            case R.id.btnScroll:
                startActivity(new Intent(MainActivity.this,ScrollExampleActivity.class));
                break;
            case R.id.btnPull:
                startActivity(new Intent(MainActivity.this,PullDownActivity.class));
                break;
        }
    }
}
