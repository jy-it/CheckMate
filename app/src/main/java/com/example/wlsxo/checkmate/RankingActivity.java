package com.example.wlsxo.checkmate;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class RankingActivity extends AppCompatActivity implements View.OnClickListener
{

    ImageButton bt1,bt2,bt3;
    FragmentManager fm;
    FragmentTransaction tran;
    Fragment1 frag1;
    Fragment2 frag2;

    de.hdodenhof.circleimageview.CircleImageView myimg_view;
    TextView myname;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ranking);

        bt1 = (ImageButton) findViewById(R.id.btn_man);
        bt2 = (ImageButton) findViewById(R.id.btn_woman);
        bt1.setOnClickListener(this);
        bt2.setOnClickListener(this);
        frag1 = new Fragment1(); //프래그먼트 객채셍성
        frag2 = new Fragment2(); //프래그먼트 객채셍성
        setFrag(0); //프래그먼트 교체
    }


    @Override
    public void onClick(View v){
        switch (v.getId()){
            case R.id.btn_man:
                setFrag(0);
                break;
            case R.id.btn_woman:
                setFrag(1);
                break;
        }
    }
    public void setFrag(int n){    //프래그먼트를 교체하는 작업을 하는 메소드를 만들었습니다
        fm = getSupportFragmentManager();
        tran = fm.beginTransaction();
        switch (n){
            case 0:
                tran.replace(R.id.main_frame, frag1);  //replace의 매개변수는 (프래그먼트를 담을 영역 id, 프래그먼트 객체) 입니다.
                tran.commit();
                break;
            case 1:
                tran.replace(R.id.main_frame, frag2);  //replace의 매개변수는 (프래그먼트를 담을 영역 id, 프래그먼트 객체) 입니다.
                tran.commit();
                break;
        }
    }
}



