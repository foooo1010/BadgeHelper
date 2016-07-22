package com.lianggekeji.library;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.widget.ImageView;

/**
 * Created by Yi on 16/7/21.
 */

public class BadgeImageHelper extends Drawable {
    private Paint textPaint;
    private Paint backPaint;
    private ImageView imageView;
    private float left_distance;
    private float bottom_distance;
    private float badgePadding;


    private String str;

    private int badgeTextColor;

    private void setBadgeBackColor(int badgeBackColor) {
        backPaint.setColor(badgeBackColor);
    }

    private BadgeImageHelper(ImageView View) {
        this.imageView = View;
        textPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        backPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    }

    private void setBadgeTextSize(int badgeTextSize) {
        textPaint.setTextSize(badgeTextSize);
    }

    public void setBadgeText(String str) {
        this.str = str;
        imageView.invalidate();
    }

    private void setLeft_distance(float left_distance) {
        this.left_distance = left_distance;
    }


    private void setBottom_distance(float bottom_distance) {
        this.bottom_distance = bottom_distance;
    }

    private void setBadgePadding(float badgePadding) {
        this.badgePadding = badgePadding;
    }

    private void setBadgeTextColor(int badgeTextColor) {
        this.badgeTextColor = badgeTextColor;
        textPaint.setColor(this.badgeTextColor);
    }

    @Override
    public void draw(Canvas canvas) {
        if (TextUtils.isEmpty(str)) return;
        Drawable drawable = imageView.getDrawable();
        if (drawable == null) return;
        float[] f = new float[9];
        imageView.getImageMatrix().getValues(f);
        final float tansX = f[Matrix.MTRANS_X];
        final float tansY = f[Matrix.MTRANS_Y];
        int paddingLeft = imageView.getPaddingLeft();
        int paddingTop = imageView.getPaddingTop();

        Rect bounds = drawable.getBounds();

        Rect textRect = new Rect();
        textPaint.getTextBounds(str, 0, str.length(), textRect);
        float r = (textRect.height() + badgePadding * 2) / 2;
        canvas.drawRoundRect(paddingLeft + tansX + bounds.right + tansX + left_distance - r,
                textRect.top - badgePadding + paddingTop + bounds.top + bottom_distance+tansY,
                paddingLeft + tansX + bounds.right + tansX + left_distance  + textRect.right + r,
                textRect.bottom + badgePadding + paddingTop + bounds.top + bottom_distance+tansY,
                badgePadding + r, badgePadding + r, backPaint);
        canvas.drawText(str,
                paddingLeft + bounds.right + left_distance + tansX,
                paddingTop + bounds.top + bottom_distance + tansY,
                textPaint);
    }


    @Override
    public void setAlpha(int alpha) {
    }

    @Override
    public void setColorFilter(ColorFilter colorFilter) {
    }

    @Override
    public int getOpacity() {
        return 0;
    }

    public static class Builder {
        private int badgeTextSize;
        private float left_distance;
        private float bottom_distance;
        private float badgePadding;
        private String str;
        private int badgeTextColor;
        private ImageView imageView;
        private int backColor;

        public Builder(ImageView imageView) {
            this.imageView = imageView;
            Context context = imageView.getContext();
            badgeTextSize = DisplayUtil.sp2px(context, 14);
            bottom_distance = DisplayUtil.dp2px(context, 8);
            badgePadding = DisplayUtil.dp2px(context, 5);
            left_distance = DisplayUtil.dp2px(context, 8) + badgePadding;
            badgeTextColor = 0xffffffff;
            backColor = 0xffff0000;
        }

        public Builder backColor(int backColor) {
            this.backColor = backColor;
            return this;
        }

        public Builder textSize(int badgeTextSize) {
            this.badgeTextSize = badgeTextSize;
            return this;
        }

        public Builder leftMargin(float left_distance) {
            this.left_distance = left_distance;
            return this;
        }

        public Builder bottomMargin(float bottom_distance) {
            this.bottom_distance = bottom_distance;
            return this;
        }

        public Builder padding(float badgePadding) {
            this.badgePadding = badgePadding;
            return this;
        }

        public Builder text(String str) {
            this.str = str;
            return this;
        }

        public Builder textColor(int badgeTextColor) {
            this.badgeTextColor = badgeTextColor;
            return this;
        }

        public BadgeImageHelper create() {
            final BadgeImageHelper badgeTextDrawable = new BadgeImageHelper(imageView);
            badgeTextDrawable.setBadgeText(str);
            badgeTextDrawable.setBadgeTextSize(badgeTextSize);
            badgeTextDrawable.setLeft_distance(left_distance);
            badgeTextDrawable.setBottom_distance(bottom_distance);
            badgeTextDrawable.setBadgePadding(badgePadding);
            badgeTextDrawable.setBadgeTextColor(badgeTextColor);
            badgeTextDrawable.setBadgeBackColor(backColor);
            imageView.post(new Runnable() {
                @Override
                public void run() {
                    imageView.getOverlay().add(badgeTextDrawable);
                }
            });
            return badgeTextDrawable;
        }
    }
}
