package plus.mcpe.mcpe_plus.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.util.AttributeSet;
import android.widget.ImageView;

public class CircleImageView extends ImageView {
    PorterDuffXfermode mXfermode = new PorterDuffXfermode(PorterDuff.Mode.DST_ATOP);
    float mRadius;
    int mWidth;
    Paint mPaint;
    Matrix mMatrix;
    BitmapShader mShader;

    public CircleImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mMatrix = new Matrix();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        mRadius = w / 2;
        mWidth = w;
        super.onSizeChanged(w, h, oldw, oldh);
    }


    @Override
    protected void onDraw(@NonNull Canvas canvas) {
        Drawable drawable = getDrawable();
        if (getDrawable() == null) return;
        Bitmap bmp = drawable2Bitmap(drawable);
        mShader = new BitmapShader(bmp, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
        int size = Math.min(bmp.getWidth(), bmp.getHeight());
        float scale = mWidth * 1.0f / size;
        mMatrix.setScale(scale, scale);
        mShader.setLocalMatrix(mMatrix);

        mPaint.setShader(mShader);
        canvas.drawCircle(mRadius, mRadius, mRadius, mPaint);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(widthMeasureSpec, heightMeasureSpec);//assert width=height
    }

    private Bitmap drawable2Bitmap(Drawable d) {
        if (d instanceof BitmapDrawable) {
            BitmapDrawable bd = (BitmapDrawable) d;
            return bd.getBitmap();
        }
        int w = d.getIntrinsicWidth();
        int h = d.getIntrinsicHeight();
        d.setBounds(0, 0, w, h);
        Bitmap bmp = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        Canvas c = new Canvas(bmp);
        d.draw(c);
        return bmp;
    }
}

