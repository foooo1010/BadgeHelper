package com.lianggekeji.badgelayout;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lianggekeji.library.BadgeImageHelper;
import com.lianggekeji.library.BadgeTextHelper;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {


    @Bind(R.id.activity_main)
    LinearLayout activityMain;
    @Bind(R.id.image_view)
    ImageView imageView;
    @Bind(R.id.text_view)
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        BadgeImageHelper myDrawable = new BadgeImageHelper.Builder(imageView)
                .backColor(0xffff0000).leftMargin(0).bottomMargin(0).padding(15).create();
        myDrawable.setBadgeText("3");
        BadgeTextHelper textHelper=new BadgeTextHelper.Builder(textView)
                .padding(15).leftMargin(0).textColor(0xff000000).bottomMargin(0).create();
        textHelper.setBadgeText("99+");

    }
}
