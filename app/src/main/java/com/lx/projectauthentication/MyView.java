package com.lx.projectauthentication;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

public class MyView extends View {
    public MyView(Context context) {
        super(context);
    }

    public MyView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MyView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    float xVector = 0;
    Paint mPaint = new Paint();

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawCircle(xVector, getHeight() / 2, 50, mPaint);
    }

    float startX;
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                startX = event.getX();
                break;
            case MotionEvent.ACTION_MOVE:
                float endX = event.getX();
                if (endX - startX > 10) {
                    if (xVector!=getWidth()) {
                        xVector+=10;
                    }
                } else if (startX - endX > 10) {
                    if (xVector!=0) {
                        xVector-=10;
                    }
                }
                invalidate();
                break;
            case MotionEvent.ACTION_UP:
                break;
        }

        return true;
    }
}
