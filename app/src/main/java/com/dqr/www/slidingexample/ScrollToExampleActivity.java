package com.dqr.www.slidingexample;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

/**
 * Description：
 * Author：LiuYM
 * Date： 2017-02-23 17:41
 */

public class ScrollToExampleActivity extends AppCompatActivity {

    private TextView mTvScroll;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scroll_to_example_layout);
        mTvScroll = (TextView) findViewById(R.id.tv_scroll);

        mTvScroll.setOnTouchListener(new View.OnTouchListener() {
            int startX;
            int endX;
            int startY;
            int endY;
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()){
                    case MotionEvent.ACTION_DOWN:
                        startX= (int) event.getX();
                        startY= (int) event.getY();
                        break;
                    case MotionEvent.ACTION_MOVE:
                        endY= (int) event.getY();
                        endX= (int) event.getX();

                        int distanceX=endX-startX;
                        int distanceY=endY-startY;
                        mTvScroll.scrollTo(-distanceX,-distanceY);
                        break;
                }


                return true;
            }
        });
    }
}
