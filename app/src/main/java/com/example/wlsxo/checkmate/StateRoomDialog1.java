package com.example.wlsxo.checkmate;

import android.app.Dialog;
import android.content.Context;
import android.media.Image;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class StateRoomDialog1 {

    private Context context;

    public StateRoomDialog1(Context context) {
        this.context = context;
    }

    // 호출할 다이얼로그 함수를 정의한다.
    public void callFunction() {

        // 커스텀 다이얼로그를 정의하기위해 Dialog클래스를 생성한다.
        final Dialog dlg = new Dialog(context);

        // 액티비티의 타이틀바를 숨긴다.
        dlg.requestWindowFeature(Window.FEATURE_NO_TITLE);

        // 커스텀 다이얼로그의 레이아웃을 설정한다.
        dlg.setContentView(R.layout.stateroom_dialog1);

        // 커스텀 다이얼로그를 노출한다.
        dlg.show();

        // 커스텀 다이얼로그의 각 위젯들을 정의한다.
        final ImageView imageView1 = (ImageView) dlg.findViewById(R.id.registerImg2);
        final TextView  message1 = (TextView) dlg.findViewById(R.id.nameText2);
        final TextView message2 = (TextView) dlg.findViewById(R.id.ageText2);
        final TextView message3 = (TextView) dlg.findViewById(R.id.deptText2);
        final TextView message4 = (TextView) dlg.findViewById(R.id.hobbyText2);
        final TextView spinner1 = (TextView) dlg.findViewById(R.id.liveSpinner2);
        final TextView spinner2 = (TextView) dlg.findViewById(R.id.heightSpinner2);
        final TextView spinner3 = (TextView) dlg.findViewById(R.id.bodySpinner2);
        final TextView message5 = (TextView) dlg.findViewById(R.id.charmText2);
        final Button okButton = (Button) dlg.findViewById(R.id.resisterButton2);


        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(context, "확인 했습니다.", Toast.LENGTH_SHORT).show();

                // 커스텀 다이얼로그를 종료한다.
                dlg.dismiss();
            }
        });

    }
}
