package com.zjl.zjllab.PorterDuffXfermode;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by zjl on 2017/1/12.
 */

public class CanvasView extends View {
    private float fromX, fromY, toX, toY;

    private Paint paint;
    private PorterDuffXfermode xfermodeForLine;
    private PorterDuffXfermode xfermodeForBg;


    public CanvasView(Context context) {
        super(context);
        init();
    }

    public CanvasView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CanvasView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public CanvasView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private void init() {
        setLayerType(LAYER_TYPE_SOFTWARE, null);

        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        xfermodeForLine = new PorterDuffXfermode(PorterDuff.Mode.DST_OVER);
        xfermodeForBg = new PorterDuffXfermode(PorterDuff.Mode.CLEAR);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getActionMasked()) {
            case MotionEvent.ACTION_DOWN:
                fromX = event.getX();
                fromY = event.getY();
                break;
            default:
                toX = event.getX();
                toY = event.getY();
                invalidate();
                break;
        }
        return true;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        int width = canvas.getWidth();
        int height = canvas.getHeight();

        // clear canvas
        // TODO: 2017/1/13 fail to clear canvas when the based activity's window bg is transparent!
        /** method 1: fail**/
//        paint.setXfermode(xfermodeForBg);
//        paint.setColor(0xFFFFFF00);
//        canvas.drawPaint(paint);
//        paint.setXfermode(null);
        /** method 2: fail**/
//        canvas.drawColor(0xFFFFFFFF, PorterDuff.Mode.CLEAR);

        int layerid = canvas.saveLayer(0, 0, width, height, null, Canvas.ALL_SAVE_FLAG);
        {
            //draw circle
            paint.setColor(0xFF0000FF);
            canvas.drawCircle(width / 2f, height / 2f, Math.min(width / 6f, height / 6f), paint);

            //draw lines
            paint.setColor(0xFF000000);
            paint.setXfermode(xfermodeForLine);
            canvas.drawLine(fromX, fromY, toX, toY, paint);
            paint.setXfermode(null);
        }
        canvas.restoreToCount(layerid);
    }
}
