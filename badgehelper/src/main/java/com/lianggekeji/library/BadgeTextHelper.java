package com.lianggekeji.library;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.text.Layout;
import android.text.TextUtils;
import android.widget.TextView;

/**
 * Created by Yi on 16/7/21.
 */

public class BadgeTextHelper extends Drawable {
    private Paint textPaint;
    private Paint backPaint;
    private TextView textView;
    private float left_distance;
    private float bottom_distance;
    private float badgePadding;


    private String str;

    private int badgeTextColor;

    private void setBadgeBackColor(int badgeBackColor) {
        backPaint.setColor(badgeBackColor);
    }

    private BadgeTextHelper(TextView textView) {
        this.textView = textView;
        textPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        backPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    }

    private void setBadgeTextSize(int badgeTextSize) {
        textPaint.setTextSize(badgeTextSize);
    }

    public void setBadgeText(String str) {
        this.str = str;
        textView.invalidate();
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
        Rect rect = new Rect();
        textView.getLineBounds(0, rect); //得到文字范围
        Layout layout = textView.getLayout(); //得到绘制文字的模板
        Rect layoutRect=new Rect();
        layout.getLineBounds(0,layoutRect);
        Rect textRect = new Rect();
        textPaint.getTextBounds(str, 0, str.length(), textRect);
        float r = (textRect.height() + badgePadding * 2) / 2;
        canvas.drawRoundRect(rect.left+layout.getLineRight(0) + left_distance-r,
                textRect.top - badgePadding + rect.top + bottom_distance,
                rect.left+layout.getLineRight(0) + left_distance+textRect.width()+r,
                textRect.bottom + badgePadding + rect.top + bottom_distance,
                badgePadding + r, badgePadding + r, backPaint);
        canvas.drawText(str,
                rect.left+layout.getLineRight(0) + left_distance,
                rect.top + bottom_distance,
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
        private TextView textView;
        private int backColor;

        public Builder(TextView textView) {
            this.textView = textView;
            Context context = textView.getContext();
            badgeTextSize = DisplayUtil.sp2px(context, 14);
            bottom_distance = DisplayUtil.dp2px(context, 8);
            badgePadding = DisplayUtil.dp2px(context, 5);
            left_distance = DisplayUtil.dp2px(context, 8)+badgePadding;
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
            this.badgePadding=badgePadding;
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

        public BadgeTextHelper create() {
            final BadgeTextHelper badgeTextDrawable = new BadgeTextHelper(textView);
            badgeTextDrawable.setBadgeText(str);
            badgeTextDrawable.setBadgeTextSize(badgeTextSize);
            badgeTextDrawable.setLeft_distance(left_distance);
            badgeTextDrawable.setBottom_distance(bottom_distance);
            badgeTextDrawable.setBadgePadding(badgePadding);
            badgeTextDrawable.setBadgeTextColor(badgeTextColor);
            badgeTextDrawable.setBadgeBackColor(backColor);
            textView.post(new Runnable() {
                @Override
                public void run() {
                    textView.getOverlay().add(badgeTextDrawable);
                }
            });
            return badgeTextDrawable;
        }
    }
}
