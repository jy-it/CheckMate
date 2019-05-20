package com.example.wlsxo.checkmate;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.annotation.Nullable;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.ImageView;

public class Fragment1 extends Fragment {
    View view;
    BigImgDialog cd;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_fragment1, container, false);

       final ImageButton imgbtn1 = (ImageButton)view.findViewById(R.id.picture1);
       final ImageButton imgbtn2 = (ImageButton)view.findViewById(R.id.picture2);
       final ImageButton imgbtn3 = (ImageButton)view.findViewById(R.id.picture3);
       final ImageButton imgbtn4 = (ImageButton)view.findViewById(R.id.picture4);
       final ImageButton imgbtn5 = (ImageButton)view.findViewById(R.id.picture5);
       final ImageButton imgbtn6 = (ImageButton)view.findViewById(R.id.picture6);
       final ImageButton imgbtn7 = (ImageButton)view.findViewById(R.id.picture7);
       final ImageButton imgbtn8 = (ImageButton)view.findViewById(R.id.picture8);
       final ImageButton imgbtn9 = (ImageButton)view.findViewById(R.id.picture9);
       final ImageButton imgbtn10 = (ImageButton)view.findViewById(R.id.picture10);

        final ImageView imgview = (ImageView)view.findViewById(R.id.bigimg);

        DisplayMetrics dm = getActivity().getResources().getDisplayMetrics(); //디바이스 화면크기를 구하기위해
        int width = dm.widthPixels; //디바이스 화면 너비
        int height = dm.heightPixels; //디바이스 화면 높이

        cd = new BigImgDialog(getActivity());
        WindowManager.LayoutParams wm = cd.getWindow().getAttributes();  //다이얼로그의 높이 너비 설정하기위해
        wm.copyFrom(cd.getWindow().getAttributes());  //여기서 설정한값을 그대로 다이얼로그에 넣겠다는의미
        wm.width = width / 2;  //화면 너비의 절반
        wm.height = height / 2;  //화면 높이의 절반


        imgbtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                BitmapDrawable bitmapDrawable = (BitmapDrawable) imgbtn1.getDrawable();
                Bitmap tmpBitmap = bitmapDrawable.getBitmap();
                cd.setImgView(tmpBitmap);
                cd.show();  //다이얼로그
            }
        });

        imgbtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                BitmapDrawable bitmapDrawable = (BitmapDrawable) imgbtn2.getDrawable();
                Bitmap tmpBitmap = bitmapDrawable.getBitmap();
                cd.setImgView(tmpBitmap);
                cd.show();  //다이얼로그
            }
        });

        imgbtn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                BitmapDrawable bitmapDrawable = (BitmapDrawable) imgbtn3.getDrawable();
                Bitmap tmpBitmap = bitmapDrawable.getBitmap();
                cd.setImgView(tmpBitmap);
                cd.show();  //다이얼로그
            }
        });

        imgbtn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                BitmapDrawable bitmapDrawable = (BitmapDrawable) imgbtn4.getDrawable();
                Bitmap tmpBitmap = bitmapDrawable.getBitmap();
                cd.setImgView(tmpBitmap);
                cd.show();  //다이얼로그
            }
        });

        imgbtn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                BitmapDrawable bitmapDrawable = (BitmapDrawable) imgbtn5.getDrawable();
                Bitmap tmpBitmap = bitmapDrawable.getBitmap();
                cd.setImgView(tmpBitmap);
                cd.show();  //다이얼로그
            }
        });

        imgbtn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                BitmapDrawable bitmapDrawable = (BitmapDrawable) imgbtn6.getDrawable();
                Bitmap tmpBitmap = bitmapDrawable.getBitmap();
                cd.setImgView(tmpBitmap);
                cd.show();  //다이얼로그
            }
        });

        imgbtn7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                BitmapDrawable bitmapDrawable = (BitmapDrawable) imgbtn7.getDrawable();
                Bitmap tmpBitmap = bitmapDrawable.getBitmap();
                cd.setImgView(tmpBitmap);
                cd.show();  //다이얼로그
            }
        });

        imgbtn8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                BitmapDrawable bitmapDrawable = (BitmapDrawable) imgbtn8.getDrawable();
                Bitmap tmpBitmap = bitmapDrawable.getBitmap();
                cd.setImgView(tmpBitmap);
                cd.show();  //다이얼로그
            }
        });

        imgbtn9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                BitmapDrawable bitmapDrawable = (BitmapDrawable) imgbtn9.getDrawable();
                Bitmap tmpBitmap = bitmapDrawable.getBitmap();
                cd.setImgView(tmpBitmap);
                cd.show();  //다이얼로그
            }
        });

        imgbtn10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                BitmapDrawable bitmapDrawable = (BitmapDrawable) imgbtn10.getDrawable();
                Bitmap tmpBitmap = bitmapDrawable.getBitmap();
                cd.setImgView(tmpBitmap);
                cd.show();  //다이얼로그
            }
        });

        return view;
    }
}

