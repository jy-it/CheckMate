package com.example.wlsxo.checkmate;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.ByteArrayOutputStream;
import java.net.URLEncoder;

public class MainActivity extends AppCompatActivity {

    Button matching_btn;
    Button ranking_btn;
    Button waiting_btn;
    Button evaluate_btn;

    de.hdodenhof.circleimageview.CircleImageView myimg_view;
    TextView myname;


    static public String userID;
    static public String userPassword;
    static public String userName;
    static public String userAge;
    static public String userDept;
    static public String userHobby;
    static public String userHeight;
    static public String userBody;
    static public String userCharm;
    static public String userKakao;
    static public String userSex;
    static public String userArea;
    static public Bitmap userPicture;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mainmenu);

        Intent login_intent = getIntent();

        userID = login_intent.getStringExtra("userID");
        userPassword = login_intent.getStringExtra("userPassword");
        userName = login_intent.getStringExtra("userName");
        userAge = login_intent.getStringExtra("userAge");
        userDept = login_intent.getStringExtra("userDept");
        userHobby = login_intent.getStringExtra("userHobby");
        userHeight = login_intent.getStringExtra("userHeight");
        userBody = login_intent.getStringExtra("userBody");
        userCharm = login_intent.getStringExtra("userCharm");
        userKakao = login_intent.getStringExtra("userKakao");
        userSex = login_intent.getStringExtra("userSex");
        userArea = login_intent.getStringExtra("userArea");
        userPicture = StringToBitMap(login_intent.getStringExtra("userPicture"));

        //상단 바
        myimg_view = (de.hdodenhof.circleimageview.CircleImageView)findViewById(R.id.indexImage);
        myname = (TextView)findViewById(R.id.indexID);
        myimg_view.setImageBitmap(userPicture);
        myname.setText(userName);

        matching_btn = (Button)findViewById(R.id.matching_btn);
        ranking_btn = (Button)findViewById(R.id.ranking_btn);
        waiting_btn = (Button)findViewById(R.id.waiting_btn);
        evaluate_btn = (Button)findViewById(R.id.evaluate_btn);
    }

    public void goRoomList(View view){//매칭방(방목록, 방생성)
        Intent intent1 = new Intent(this,RoomListActivity.class);
        startActivity(intent1);
    }

    public void goRanking(View v){//랭킹
        Intent intent = new Intent(this, RankingActivity.class);
        startActivity(intent);
    }
    public void goWaitingRoom(View view){//매칭대기방, 매칭완료방
        Intent intent = new Intent(this,StateRoomActivity.class);
        startActivity(intent);
    }

    public void goEvaluate(View view){//얼굴평가
        Intent intent1 = new Intent(this,HogamActivity.class);
        startActivity(intent1);
    }

    //이미지 디코딩
    public static Bitmap StringToBitMap(String encodedImage){
        try{
            byte [] encodeByte=Base64.decode(encodedImage,Base64.DEFAULT);
            Bitmap bitmap=BitmapFactory.decodeByteArray(encodeByte, 0, encodeByte.length);
            return bitmap;
        }catch(Exception e){
            Log.e("예외발생",e.getMessage());

            return null;
        }
    }

}


