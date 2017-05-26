package app.dgandroid.eu.beerplease.customs;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;
import app.dgandroid.eu.beerplease.R;

/**
 * Created by Duilio on 24/05/2017.
 */

public class FlatItem extends ViewGroup {

    private Context mContext;
    private String nameBeer;
    private String tagBeer;
    private Bitmap myBitmap;
    private TextPaint namePaint;
    private TextPaint tagPaint;
    private float scale;

    public FlatItem(Context context, AttributeSet attrs, int defStyle) {
        super(context,attrs,defStyle);
        init(context);
    }

    public FlatItem(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init(context);
    }

    public void init(Context context) {
        this.mContext = context;
        Resources res = getResources();
        setBackgroundColor(res.getColor(R.color.white));

        scale = res.getDisplayMetrics().density;
        namePaint = new TextPaint ();
        namePaint.setStyle(Paint.Style.FILL_AND_STROKE);
        namePaint.setTypeface(Typeface.DEFAULT_BOLD);
        namePaint.setColor(Color.BLACK);
        namePaint.setAntiAlias(true);
        namePaint.setTextSize((int) (18 * scale));
        namePaint.setSubpixelText(true);
        tagPaint = new TextPaint();
        tagPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        tagPaint.setColor(Color.DKGRAY);
        tagPaint.setAntiAlias(true);
        tagPaint.setTextSize((int) (14 * scale));
        tagPaint.setSubpixelText(true);

        getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                    getViewTreeObserver().removeOnGlobalLayoutListener(this);
                } else {
                    getViewTreeObserver().removeGlobalOnLayoutListener(this);
                }
                myBitmap = getResizedBitmap(myBitmap, getWidth(), getHeight());
            }
        });
    }

    @Override
    protected void onLayout(boolean b, int i, int i1, int i2, int i3) {
        int count = this.getChildCount();
        for (int io = 0; io < count; io++) {
            View child = this.getChildAt(io);
            child.layout(0, 0, child.getMeasuredWidth(), child.getMeasuredHeight());
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        fixText(getNameBeer(), getTagBeer(), canvas);
        float imageY = (getHeight() - myBitmap.getHeight()) /2;
        canvas.drawBitmap(myBitmap, (int)(8*scale), imageY, null);
    }

    public void setMyBitmap(Bitmap bitmap) {
        this.myBitmap = bitmap;
    }

    public void setTagBeer(String tagBeer) {
        this.tagBeer = tagBeer;
        requestLayout();
        invalidate();
    }

    public String getTagBeer() {
        return this.tagBeer;
    }

    public void setNameBeer(String nameBeer){
        this.nameBeer = nameBeer;
        requestLayout();
        invalidate();
    }

    public String getNameBeer(){
        return this.nameBeer;
    }

    public void setIconFromURL(final String urlImage, final Context context) {
        Picasso.with(context)
                .load(urlImage)
                .into(new Target() {
                    @Override
                    public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                        setMyBitmap(bitmap);
                    }

                    @Override
                    public void onBitmapFailed(Drawable errorDrawable) {
                        setMyBitmap(BitmapFactory.decodeResource(mContext.getResources(),
                                R.drawable.default_beer));
                    }

                    @Override
                    public void onPrepareLoad(Drawable placeHolderDrawable) {
                        setMyBitmap(BitmapFactory.decodeResource(mContext.getResources(),
                                R.drawable.default_beer));
                    }
                });
    }

    private Bitmap getResizedBitmap(Bitmap bitmap, int reqWidth, int reqHeight) {
        Matrix matrix = new Matrix();
        RectF src = new RectF(0,0, bitmap.getWidth(), bitmap.getHeight());
        RectF dst = new RectF(0,0, reqWidth, reqHeight);
        matrix.setRectToRect(src, dst, Matrix.ScaleToFit.CENTER);
        return Bitmap.createBitmap(bitmap, 0 , 0 , bitmap.getWidth(), bitmap.getHeight(), matrix, true);
    }

    public void fixText(String text, String text2, Canvas canvas) {
        int width = (int)(216*scale);
        Layout.Alignment alignment = Layout.Alignment.ALIGN_NORMAL;
        float spacingMultiplier = 1;
        float spacingAddition = 0;
        boolean includePadding = false;
        int margin = (int)(16*scale);

        StaticLayout s2 = new StaticLayout(text2, tagPaint, width, alignment, spacingMultiplier, spacingAddition, includePadding);
        StaticLayout sl = new StaticLayout(text, namePaint, width, alignment, spacingMultiplier, spacingAddition, includePadding);
        //float textHeight2 = getTextHeight(text2, tagPaint);
        //float textHeight1 = getTextHeight(text, namePaint);
        //int numberOfTextLines2 = s2.getLineCount();
        //int numberOfTextLines1 = sl.getLineCount();
        //float textYCoordinate2 = canvas.getClipBounds().exactCenterY() - ((numberOfTextLines2 * textHeight2) / 2);
        canvas.save();
        canvas.translate(getWidth()/2 - (int)(24*scale), canvas.getClipBounds().exactCenterY() +sl.getHeight()-margin/2);
        s2.draw(canvas);
        canvas.restore();
        //float textYCoordinate1 = canvas.getClipBounds().exactCenterY() - ((numberOfTextLines1 * textHeight1) / 2);
        canvas.save();
        canvas.translate(getWidth()/2 - (int)(24*scale), canvas.getClipBounds().exactCenterY()-margin); //getHeight()/2
        sl.draw(canvas);
        canvas.restore();
    }
    /* private float getTextHeight(String text, Paint paint) {
        Rect rect = new Rect();
        paint.getTextBounds(text, 0, text.length(), rect);
        return rect.height();
    } */
}