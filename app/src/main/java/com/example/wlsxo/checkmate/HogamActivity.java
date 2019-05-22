package com.example.wlsxo.checkmate;

import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

public class HogamActivity extends AppCompatActivity implements View.OnClickListener {

Button btn_heart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hogam);

        btn_heart = (Button)findViewById(R.id.btn_heart);
        btn_heart.setOnClickListener(this);

        /*
        imageView = (ImageView) findViewById(R.id.imageView1);

        imageView2 = (ImageView) findViewById(R.id.imageView2);
*/
    }


    @Override
    public void onClick(View v) {
            switch(v.getId())
            {
                case R.id.btn_heart :
                    btn_heart.setSelected(true);

                    break;
            }
/*
        if(index == 0) {
            imageView.setVisibility(View.VISIBLE);
            imageView2.setVisibility(View.GONE);
        }

        if(index == 1) {
            imageView.setVisibility(View.GONE);
            imageView2.setVisibility(View.VISIBLE);
        }


        count ++;
    */
        }


    }