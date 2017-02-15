package com.zjl.roundimageview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

/**
 * Created by zjl on 2017/2/14.
 */

public class RoundImageView extends ImageView {
    private static final String TAG = "RoundImageView";

    private Paint mPaint;
    private PorterDuffXfermode mPathXfermode;
    private Path mPath;
    private RectF mRectF;

    private float mRadiusTopLeft = 0;
    private float mRadiusTopRight = 0;
    private float mRadiusBottomRight = 0;
    private float mRadiusBottomLeft = 0;

    /**
     * The corners are ordered top-left, top-right,
     * bottom-right, bottom-left.
     */
    private float[] mRadii = new float[8];

    public RoundImageView(Context context) {
        super(context);
        init();
    }

    public RoundImageView(Context context, AttributeSet attrs) {
        super(context, attrs);

        if (attrs != null) {
            TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.RoundImageView);
            mRadiusTopLeft = array.getDimensionPixelSize(R.styleable.RoundImageView_riv_topLeft, 0);
            mRadiusTopRight = array.getDimensionPixelSize(R.styleable.RoundImageView_riv_topRight, 0);
            mRadiusBottomRight = array.getDimensionPixelSize(R.styleable.RoundImageView_riv_bottomRight, 0);
            mRadiusBottomLeft = array.getDimensionPixelSize(R.styleable.RoundImageView_riv_bottomLeft, 0);
            array.recycle();
        }

        init();
    }

    public RoundImageView(Context context, float radiusTopLeft, float radiusTopRight, float radiusBottomRight, float radiusBottomLeft) {
        super(context);
        mRadiusTopLeft = radiusTopLeft;
        mRadiusTopRight = radiusTopRight;
        mRadiusBottomRight = radiusBottomRight;
        mRadiusBottomLeft = radiusBottomLeft;

        init();
    }

    private void init() {
        Log.d(TAG, "init: ");
        mPathXfermode = new PorterDuffXfermode(PorterDuff.Mode.DST_IN);
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setXfermode(mPathXfermode);

        mRectF = new RectF();
        mPath = new Path();


        mRadii[0] = mRadiusTopLeft;
        mRadii[1] = mRadiusTopLeft;
        mRadii[2] = mRadiusTopRight;
        mRadii[3] = mRadiusTopRight;
        mRadii[4] = mRadiusBottomRight;
        mRadii[5] = mRadiusBottomRight;
        mRadii[6] = mRadiusBottomLeft;
        mRadii[7] = mRadiusBottomLeft;
    }

    public void setCornerRadius(float radiusTopLeft, float radiusTopRight, float radiusBottomRight, float radiusBottomLeft) {
        boolean tl = updateRadiusTopLeft(radiusTopLeft);
        boolean tr = updateRadiusTopRight(radiusTopRight);
        boolean br = updateRadiusBottomRight(radiusBottomRight);
        boolean bl = updateRadiusBottomLeft(radiusBottomLeft);

        if (tl || tr || br || bl) {
            invalidate();
        }
    }

    public void setRadiusTopLeft(float radiusTopLeft) {
        if (updateRadiusTopLeft(radiusTopLeft)) {
            invalidate();
        }
    }

    public void setRadiusTopRight(float radiusTopRight) {
        if (updateRadiusTopRight(radiusTopRight)) {
            invalidate();
        }
    }

    public void setRadiusBottomRight(float radiusBottomRight) {
        if (updateRadiusBottomRight(radiusBottomRight)) {
            invalidate();
        }
    }

    public void setRadiusBottomLeft(float radiusBottomLeft) {
        if (updateRadiusBottomLeft(radiusBottomLeft)) {
            invalidate();
        }
    }

    /**
     * @return whether changed
     */
    private boolean updateRadiusTopLeft(float radiusTopLeft) {
        if (mRadiusTopLeft != radiusTopLeft) {
            mRadiusTopLeft = radiusTopLeft;
            mRadii[0] = radiusTopLeft;
            mRadii[1] = radiusTopLeft;
            return true;
        }
        return false;
    }

    private boolean updateRadiusTopRight(float radiusTopRight) {
        if (mRadiusTopRight != radiusTopRight) {
            mRadiusTopRight = radiusTopRight;
            mRadii[2] = radiusTopRight;
            mRadii[3] = radiusTopRight;
            return true;
        }
        return false;
    }

    private boolean updateRadiusBottomRight(float radiusBottomRight) {
        if (mRadiusBottomRight != radiusBottomRight) {
            mRadiusBottomRight = radiusBottomRight;
            mRadii[4] = radiusBottomRight;
            mRadii[5] = radiusBottomRight;
            return true;
        }
        return false;
    }

    private boolean updateRadiusBottomLeft(float radiusBottomLeft) {
        if (mRadiusBottomLeft != radiusBottomLeft) {
            mRadiusBottomLeft = radiusBottomLeft;
            mRadii[6] = radiusBottomLeft;
            mRadii[7] = radiusBottomLeft;
            return true;
        }
        return false;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Log.d(TAG, "onDraw: ");
        mRectF.set(0, 0, getWidth(), getHeight());

        int saveCount = canvas.saveLayer(mRectF, null, Canvas.ALL_SAVE_FLAG);

        super.onDraw(canvas);
        mPath.reset();
        mPath.addRoundRect(mRectF, mRadii, Path.Direction.CW);
        canvas.drawPath(mPath, mPaint);

        canvas.restoreToCount(saveCount);
    }

    @Override
    protected Parcelable onSaveInstanceState() {
        Log.d(TAG, "onSaveInstanceState: ");
        Parcelable superState = super.onSaveInstanceState();
        SavedState ss = new SavedState(superState);
        ss.radiusTopLeft = mRadiusTopLeft;
        ss.radiusTopRight = mRadiusTopRight;
        ss.radiusBottomRight = mRadiusBottomRight;
        ss.radiusBottomLeft = mRadiusBottomLeft;

        return ss;
    }

    @Override
    protected void onRestoreInstanceState(Parcelable state) {
        Log.d(TAG, "onRestoreInstanceState: ");
        if (!(state instanceof SavedState)) {
            super.onRestoreInstanceState(state);
            return;
        }

        SavedState ss = (SavedState) state;
        super.onRestoreInstanceState(ss.mSuperState);

        mRadiusTopLeft = ss.radiusTopLeft;
        mRadiusTopRight = ss.radiusTopRight;
        mRadiusBottomRight = ss.radiusBottomRight;
        mRadiusBottomLeft = ss.radiusBottomLeft;

        mRadii[0] = mRadiusTopLeft;
        mRadii[1] = mRadiusTopLeft;
        mRadii[2] = mRadiusTopRight;
        mRadii[3] = mRadiusTopRight;
        mRadii[4] = mRadiusBottomRight;
        mRadii[5] = mRadiusBottomRight;
        mRadii[6] = mRadiusBottomLeft;
        mRadii[7] = mRadiusBottomLeft;
    }

    /**
     * User interface state that is stored by RoundImageView for implementing
     * {@link View#onSaveInstanceState}.
     */
    private static class SavedState implements Parcelable {
        public static final Parcelable.Creator<SavedState> CREATOR
                = new Parcelable.ClassLoaderCreator<SavedState>() {
            public SavedState createFromParcel(Parcel source, ClassLoader loader) {
                return new SavedState(source, loader);
            }

            public SavedState createFromParcel(Parcel in) {
                return new SavedState(in);
            }

            public SavedState[] newArray(int size) {
                return new SavedState[size];
            }
        };
        private final Parcelable mSuperState;

        private float radiusTopLeft = 0;
        private float radiusTopRight = 0;
        private float radiusBottomRight = 0;
        private float radiusBottomLeft = 0;

        SavedState(Parcelable superState) {
            mSuperState = superState;
        }

        private SavedState(Parcel in) {
            this(in, null);
        }

        private SavedState(Parcel in, ClassLoader loader) {
            mSuperState = in.readParcelable(loader);

            radiusTopLeft = in.readFloat();
            radiusTopRight = in.readFloat();
            radiusBottomRight = in.readFloat();
            radiusBottomLeft = in.readFloat();
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel out, int flags) {
            out.writeParcelable(mSuperState, flags);

            out.writeFloat(radiusTopLeft);
            out.writeFloat(radiusTopRight);
            out.writeFloat(radiusBottomRight);
            out.writeFloat(radiusBottomLeft);
        }

    }
}
