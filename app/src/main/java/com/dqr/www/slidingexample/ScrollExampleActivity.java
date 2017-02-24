package com.dqr.www.slidingexample;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

/**
 * Description：
 * Author：LiuYM
 * Date： 2017-02-24 15:42
 */

public class ScrollExampleActivity extends AppCompatActivity {
    private Button mButton;
    private LinearLayoutSubClass mLltLayout;
    private ImageView mImageView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scroll_example_layout);
        mButton= (Button) findViewById(R.id.button);
        mLltLayout = (LinearLayoutSubClass) findViewById(R.id.linearLayoutSubClass);
        mImageView= (ImageView) findViewById(R.id.image);

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mLltLayout.beginScroll();
            }
        });


        mImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ScrollExampleActivity.this, "ImageView Click", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
