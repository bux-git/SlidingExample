package com.dqr.www.slidingexample;

import android.content.Context;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Scroller;

/**
 * Description：
 * Author：LiuYM
 * Date： 2017-02-24 14:53
 */

public class LinearLayoutSubClass extends LinearLayout {

    private Scroller mScroller;
    private boolean flag=true;
    private int duration;
    private int offsetY;

    public LinearLayoutSubClass(Context context) {
        super(context);
        init(context);
    }

    public LinearLayoutSubClass(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public LinearLayoutSubClass(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public LinearLayoutSubClass(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context);
    }

    /**
     * 1.初始化Scroller
         2.调用startScroll()开始滚动
         3.执行invalidate()刷新界面
         4.重写View的computeScroll()并在其内部实现与滚动相关的业务逻辑
         5.再次执行invalidate()刷新界面
     * @param context
     */
    private void init(Context context){
        mScroller = new Scroller(context);
        duration=1000;
    }

    View child;
    public void beginScroll(){
        child = getChildAt(0);
        if(flag){
            offsetY=-150;
            int startX=0;
            int startY=0;
            int dx=-500;
            int dy=0;
            mScroller.startScroll(startX,startY,dx,dy,duration);
            flag=false;
        }else{

            int startX=mScroller.getCurrX();
            int startY=mScroller.getCurrY();
            int dx=-startX;
            int dy=0;
            offsetY=-startY;
            mScroller.startScroll(startX,startY,dx,dy,duration);
            flag=true;
        }
        invalidate();
    }

    @Override
    public void computeScroll() {
        super.computeScroll();
        if(mScroller.computeScrollOffset()){
            scrollTo(mScroller.getCurrX(),offsetY);
           // Log.d("child", "computeScroll: left:"+child.getLeft()+" top:"+child.getTop()+"    right:"+child.getRight()+"  bottom:"+child.getBottom()
          // +"  llt:X"+getScrollX()+" y:"+getScrollY());

            invalidate();
        }
    }
}
