package io.github.staray.library;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by staray on 2016/12/22.
 */

public class RichTextView extends TextView {
    public static final int LEFT = 1, TOP = 2, RIGHT = 3, BOTTOM = 4;

    private int mHeight, mWidth;

    private int mLocation;

    private float scale;

    public RichTextView(Context context) {
        this(context, null);
    }

    public RichTextView(Context context, AttributeSet attrs) {
        super(context, attrs);

        TypedArray a = context.obtainStyledAttributes(attrs,
                R.styleable.RichTextView);

        scale = context.getResources().getDisplayMetrics().density;

        mWidth = a.getDimensionPixelSize(R.styleable.RichTextView_drawable_width, 0);
        mHeight = a.getDimensionPixelSize(R.styleable.RichTextView_drawable_height, 0);
        Drawable mDrawable = a.getDrawable(R.styleable.RichTextView_drawable_src);
        mLocation = a.getInt(R.styleable.RichTextView_drawable_location, TOP);

        a.recycle();
        //绘制Drawable宽高,位置
        drawDrawable(mDrawable);
    }

    /**
     * 绘制Drawable宽高,位置
     */
    public void drawDrawable(Drawable mDrawable) {
        if (mDrawable != null) {
            Bitmap bitmap = ((BitmapDrawable) mDrawable).getBitmap();
            drawBitmap(bitmap);
        }
    }

    public void drawBitmap(Bitmap bitmap) {
        if (null != bitmap) {
            Drawable drawable;
            if (mWidth != 0 && mHeight != 0) {
                drawable = new BitmapDrawable(getResources(), getBitmap(bitmap,
                        mWidth, mHeight));
            } else {
                drawable = new BitmapDrawable(getResources(), Bitmap.createScaledBitmap(bitmap, bitmap.getWidth(),
                        bitmap.getHeight(), true));
            }

            showDrawable(drawable);
        }
    }

    /**
     * setting the drawable height by dp
     *
     * @param mHeight
     */
    public void setmHeight(int mHeight) {
        this.mHeight = (int) (mHeight * scale);
    }

    /**
     * setting the drawable width by dp
     *
     * @param mWidth
     */
    public void setmWidth(int mWidth) {
        this.mWidth = (int) (mWidth * scale);
    }

    public void setmLocation(int mLocation) {
        this.mLocation = mLocation;
    }

    private void showDrawable(Drawable drawable) {
        if (mLocation == LEFT) {
            this.setCompoundDrawablesWithIntrinsicBounds(drawable, null,
                    null, null);
        } else if (mLocation == TOP) {
            this.setCompoundDrawablesWithIntrinsicBounds(null, drawable,
                    null, null);
        } else if (mLocation == RIGHT) {
            this.setCompoundDrawablesWithIntrinsicBounds(null, null,
                    drawable, null);
        } else if (mLocation == BOTTOM) {
            this.setCompoundDrawablesWithIntrinsicBounds(null, null, null,
                    drawable);
        }
    }

    /**
     * 缩放图片
     */
    public Bitmap getBitmap(Bitmap bm, int newWidth, int newHeight) {
        // 获得图片的宽高
        int width = bm.getWidth();
        int height = bm.getHeight();
        // 计算缩放比例
        float scaleWidth = (float) newWidth / width;
        float scaleHeight = (float) newHeight / height;
        // 取得想要缩放的matrix参数
        Matrix matrix = new Matrix();
        matrix.postScale(scaleWidth, scaleHeight);
        // 得到新的图片
        return Bitmap.createBitmap(bm, 0, 0, width, height, matrix, true);
    }
}
