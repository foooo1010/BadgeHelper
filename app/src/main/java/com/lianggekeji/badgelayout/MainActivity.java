package com.lianggekeji.badgelayout;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.lianggekeji.library.BadgeImageHelper;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {


    @Bind(R.id.text)
    ImageView text;
    @Bind(R.id.activity_main)
    LinearLayout activityMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        final BadgeImageHelper myDrawable=new BadgeImageHelper.Builder(text)
                .backColor(0xff00ff00).padding(3).create();
        myDrawable.setBadgeText("3");
    }
}
