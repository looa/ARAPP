package com.mengdd.paintable;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;

/**
 * This abstract class provides many methods paint objects on a given Canvas.
 * 
 * These codes are adapted from these source:
 * 1."android-augment-reality-framework" project link:
 * http://code.google.com/p/android-augment-reality-framework/
 * 
 * 
 * 2.The book: "Pro Android Augmented Reality"
 * http://www.apress.com/9781430239451 Official repository for Pro Android
 * Augmented Reality: https://github.com/RaghavSood/ProAndroidAugmentedReality
 * 
 * @author Justin Wetherell <phishman3579@gmail.com>
 * @author Dandan Meng <mengdandanno1@163.com>
 * @version 1.0
 * @since 2013-07-01
 */
public abstract class PaintableObject {

    private Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);

    public Paint getPaint() {
        return mPaint;
    }

    public void setPaint(Paint paint) {
        this.mPaint = paint;
    }

    public PaintableObject() {
        if (null == mPaint) {
            mPaint = new Paint();
        }
        mPaint.setTextSize(16);
        mPaint.setAntiAlias(true);
        mPaint.setColor(Color.BLUE);
        mPaint.setStyle(Paint.Style.STROKE);

    }

    /**
     * Get the width of the paintable object.
     * 
     * @return float width
     */
    public abstract float getWidth();

    /**
     * Get the height of the paintable object.
     * 
     * @return float height
     */
    public abstract float getHeight();

    /**
     * Paints this object on the given canvas.
     */
    public abstract void paint(Canvas canvas);

    /**
     * Should we fill this paintable object.
     */
    public void setFill(boolean fill) {
        if (fill) {
            mPaint.setStyle(Paint.Style.FILL);
        }
        else {
            mPaint.setStyle(Paint.Style.STROKE);
        }
    }

    /**
     * Set the color of the paintable object.
     */
    public void setColor(int c) {
        mPaint.setColor(c);
    }

    /**
     * Set the stroke with of the paint used to render this object.
     */
    public void setStrokeWidth(float w) {
        mPaint.setStrokeWidth(w);
    }

    /**
     * Get the width of the text String.
     * 
     * @param txt
     *            String to get the width of.
     * @return float width of the text String.
     * @throws NullPointerException
     *             if the String param is NULL.
     */
    public float getTextWidth(String txt) {
        if (null == txt) {
            throw new IllegalArgumentException("String txt is null!");
        }
        return mPaint.measureText(txt);
    }

    /**
     * Get the ascent of the paint element.
     * 
     * @return float ascent of the text.
     */
    public float getTextAsc() {
        return -mPaint.ascent();
    }

    /**
     * Get the decent of the paint element.
     * 
     * @return float decent of the text.
     */
    public float getTextDesc() {
        return mPaint.descent();
    }

    /**
     * Set the font size of the paint object.
     * 
     * @param size
     *            to set the font.
     */
    public void setFontSize(float size) {
        mPaint.setTextSize(size);
    }

    /**
     * Paint a line on the given Canvas.
     * 
     * @param canvas
     *            Canvas to paint on.
     * @param x1
     *            Beginning X to draw line.
     * @param y1
     *            Beginning Y to draw line.
     * @param x2
     *            Ending X to draw line.
     * @param y2
     *            Ending Y to draw line.
     * @throws IllegalArgumentException
     *             if Canvas is NULL.
     */
    public void paintLine(Canvas canvas, float x1, float y1, float x2, float y2) {
        if (null == canvas) {
            throw new IllegalArgumentException("canvas is null!");

        }

        canvas.drawLine(x1, y1, x2, y2, mPaint);
    }

    /**
     * Paint a rectangle on the given Canvas.
     * 
     * @param canvas
     *            Canvas to paint on.
     * @param x
     *            X location of the rectangle.
     * @param y
     *            Y location of the rectangle.
     * @param width
     *            Width of the rectangle.
     * @param height
     *            Height of the rectangle.
     * @throws IllegalArgumentException
     *             if Canvas is NULL.
     */
    public void paintRect(Canvas canvas, float x, float y, float width,
            float height) {
        if (null == canvas) {
            throw new IllegalArgumentException("canvas is null!");

        }

        canvas.drawRect(x, y, x + width, y + height, mPaint);
    }

    /**
     * Paint a rectangle with round corners on the given Canvas.
     * 
     * @param canvas
     *            Canvas to paint on.
     * @param x
     *            X location of the rectangle.
     * @param y
     *            Y location of the rectangle.
     * @param width
     *            Width of the rectangle.
     * @param height
     *            Height of the rectangle.
     * @throws IllegalArgumentException
     *             if Canvas is NULL.
     */
    public void paintRoundedRect(Canvas canvas, float x, float y, float width,
            float height) {
        paintRoundedRect(canvas, x, y, width, height, 15f, 15f);

    }

    public void paintRoundedRect(Canvas canvas, float x, float y, float width,
            float height, float roundX, float roundY) {
        if (null == canvas) {
            throw new IllegalArgumentException("canvas is null!");

        }

        RectF rect = new RectF(x, y, x + width, y + height);
        canvas.drawRoundRect(rect, roundX, roundY, mPaint);
    }

    /**
     * Paint a bitmap on the given Canvas.
     * 
     * @param canvas
     *            Canvas to paint on.
     * @param bitmap
     *            Bitmap to paint.
     * @param src
     *            Source rectangle.
     * @param dst
     *            Destination rectangle.
     * @throws IllegalArgumentException
     *             if Canvas or Bitmap is NULL.
     */
    public void paintBitmap(Canvas canvas, Bitmap bitmap, Rect src, Rect dst) {
        if (null == canvas || null == bitmap) {
            throw new IllegalArgumentException("canvas or bitmap is null!");

        }

        canvas.drawBitmap(bitmap, src, dst, mPaint);
    }

    /**
     * Paint a bitmap on the given Canvas.
     * 
     * @param canvas
     *            Canvas to paint on.
     * @param bitmap
     *            Bitmap to paint.
     * @param left
     *            Left location to draw the bitmap.
     * @param top
     *            Top location to draw the bitmap.
     * @throws IllegalArgumentException
     *             if Canvas or Bitmap is NULL.
     */
    public void paintBitmap(Canvas canvas, Bitmap bitmap, float left, float top) {
        if (null == canvas || null == bitmap) {
            throw new IllegalArgumentException("canvas or bitmap is null!");

        }

        canvas.drawBitmap(bitmap, left, top, mPaint);
    }

    /**
     * Paint a circle on the given Canvas.
     * 
     * @param canvas
     *            Canvas to paint on.
     * @param x
     *            Center X coordinate of the circle.
     * @param y
     *            Center Y coordinate of the circle.
     * @param radius
     *            Radius of the circle.
     * @throws IllegalArgumentException
     *             if Canvas is NULL.
     */
    public void paintCircle(Canvas canvas, float x, float y, float radius) {
        if (null == canvas) {
            throw new IllegalArgumentException("canvas is null!");

        }

        canvas.drawCircle(x, y, radius, mPaint);
    }

    /**
     * Paint text on the given Canvas.
     * 
     * @param canvas
     *            Canvas to paint on.
     * @param x
     *            X Coordinate of the text.
     * @param y
     *            Y coordinate of the text.
     * @param text
     *            String to paint on the Canvas.
     * @throws IllegalArgumentException
     *             if Canvas or String param is NULL.
     */
    public void paintText(Canvas canvas, float x, float y, String text) {
        if (null == canvas || null == text) {
            throw new IllegalArgumentException("canvas or text is null!");

        }

        canvas.drawText(text, x, y, mPaint);
    }

    /**
     * Paint generic object on the Canvas.
     * 
     * @param canvas
     *            Canvas to paint on.
     * @param obj
     *            Object to paint on the Canvas.
     * @param x
     *            X coordinate of the object.
     * @param y
     *            Y coordinate of the object.
     * @param rotation
     *            Rotation of the object.
     * @param scale
     *            Scale of the object.
     * @throws IllegalArgumentException
     *             if Canvas or Object is NULL.
     */
    public void paintObj(Canvas canvas, PaintableObject obj, float x, float y,
            float rotation, float scale) {
        if (canvas == null || obj == null) {
            throw new IllegalArgumentException("canvas==null or obj == null");
        }

        canvas.save();
        canvas.translate((x + obj.getWidth() / 2), (y + obj.getHeight() / 2));
        canvas.rotate(rotation);
        canvas.scale(scale, scale);
        canvas.translate(-(obj.getWidth() / 2), -(obj.getHeight() / 2));
        obj.paint(canvas);
        canvas.restore();
    }

    /**
     * Paint path on the given Canvas.
     * 
     * @param canvas
     *            Canvas to paint on.
     * @param path
     *            Path to paint on the Canvas.
     * @param x
     *            X coordinate of the path.
     * @param y
     *            Y coordinate of the path.
     * @param width
     *            Width of the path.
     * @param height
     *            Height of the path.
     * @param rotation
     *            Rotation of the path.
     * @param scale
     *            Scale of the path.
     * @throws IllegalArgumentException
     *             if Canvas or Path is NULL.
     */
    public void paintPath(Canvas canvas, Path path, float x, float y,
            float width, float height, float rotation, float scale) {

        if (null == canvas || null == path) {
            throw new IllegalArgumentException("canvas or path is null!");

        }

        canvas.save();
        canvas.translate(x + width / 2, y + height / 2);
        canvas.rotate(rotation);
        canvas.scale(scale, scale);
        canvas.translate(-(width / 2), -(height / 2));
        canvas.drawPath(path, mPaint);
        canvas.restore();
    }
}
