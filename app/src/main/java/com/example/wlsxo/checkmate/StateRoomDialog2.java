package com.example.wlsxo.checkmate;

import android.app.Dialog;
import android.content.Context;
import android.media.Image;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class StateRoomDialog2 {

    private Context context;

    public StateRoomDialog2(Context context) {
        this.context = context;
    }

    // 호출할 다이얼로그 함수를 정의한다.
    public void callFunction() {

        // 커스텀 다이얼로그를 정의하기위해 Dialog클래스를 생성한다.
        final Dialog dlg = new Dialog(context);

        // 액티비티의 타이틀바를 숨긴다.
        dlg.requestWindowFeature(Window.FEATURE_NO_TITLE);

        // 커스텀 다이얼로그의 레이아웃을 설정한다.
        dlg.setContentView(R.layout.stateroom_dialog2);

        // 커스텀 다이얼로그를 노출한다.
        dlg.show();

        // 커스텀 다이얼로그의 각 위젯들을 정의한다.
        final TextView message1 = (TextView) dlg.findViewById(R.id.roomname2);
        final ImageView imageView1 = (ImageView) dlg.findViewById(R.id.imageViewdialog1);
        final ImageView imageView2 = (ImageView) dlg.findViewById(R.id.imageViewdialog2);
        final ImageView imageView3 = (ImageView) dlg.findViewById(R.id.imageViewdialog3);
        final ImageView imageView4 = (ImageView) dlg.findViewById(R.id.imageViewdialog4);
        final TextView message2 = (TextView) dlg.findViewById(R.id.comment2);
        final Button okButton = (Button) dlg.findViewById(R.id.dialog_ok);
        final Button cancelButton = (Button) dlg.findViewById(R.id.dialog_Cancel);

//        TODO 방 상세 정보(돋보기 눌렀을때) 후에 사진 클릭시 그 사람 상세정보 보기
        imageView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StateRoomDialog1 customDialog = new StateRoomDialog1(context);

                customDialog.callFunction();
            }
        });

        imageView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StateRoomDialog1 customDialog = new StateRoomDialog1(context);

                customDialog.callFunction();
            }
        });

        imageView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StateRoomDialog1 customDialog = new StateRoomDialog1(context);

                customDialog.callFunction();
            }
        });

        imageView4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StateRoomDialog1 customDialog = new StateRoomDialog1(context);

                customDialog.callFunction();
            }
        });

        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "확인 했습니다.", Toast.LENGTH_SHORT).show();

                // 커스텀 다이얼로그를 종료한다.
                dlg.dismiss();
            }
        });

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "취소 했습니다.", Toast.LENGTH_SHORT).show();

                // 커스텀 다이얼로그를 종료한다.
                dlg.dismiss();
            }
        });
    }
}
