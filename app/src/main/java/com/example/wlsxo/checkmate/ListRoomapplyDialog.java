
package com.example.wlsxo.checkmate;
import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ListRoomapplyDialog {

    private Context context;

    public ListRoomapplyDialog(Context context) {
        this.context = context;
    }

    // 호출할 다이얼로그 함수를 정의한다.
    public void callFunction() {

        // 커스텀 다이얼로그를 정의하기위해 Dialog클래스를 생성한다.
        final Dialog dlg = new Dialog(context);

        // 액티비티의 타이틀바를 숨긴다.
        dlg.requestWindowFeature(Window.FEATURE_NO_TITLE);

        // 커스텀 다이얼로그의 레이아웃을 설정한다.
        dlg.setContentView(R.layout.listroom_applydialog);

        // 커스텀 다이얼로그를 노출한다.
        dlg.show();

        // 커스텀 다이얼로그의 각 위젯들을 정의한다.

        final EditText idcode1 = (EditText) dlg.findViewById(R.id.inputidcode1);
        final EditText idcode2 = (EditText) dlg.findViewById(R.id.inputidcode2);
        final EditText idcode3 = (EditText) dlg.findViewById(R.id.inputidcode3);
        final EditText idcode4 = (EditText) dlg.findViewById(R.id.inputidcode4);

        final Button okButton = (Button) dlg.findViewById(R.id.dialog_ok);
        final Button cancelButton = (Button) dlg.findViewById(R.id.dialog_Cancel);


        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "신청 되었습니다.", Toast.LENGTH_SHORT).show();

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
