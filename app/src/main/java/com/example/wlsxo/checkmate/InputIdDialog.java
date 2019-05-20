package com.example.wlsxo.checkmate;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class InputIdDialog extends Dialog implements View.OnClickListener{

    private Button positiveButton;
    private Button negativeButton;
    private EditText idText;
    private Context context;

    private CustomDialogListener customDialogListener;

    public InputIdDialog(Context context) {
        super(context);
        this.context = context;
    }


    //인터페이스 설정
    interface CustomDialogListener{
        void onPositiveClicked(String member_id);
        void onNegativeClicked();
    }

    //호출할 리스너 초기화
    public void setDialogListener(CustomDialogListener customDialogListener){
        this.customDialogListener = customDialogListener;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.inputid_dialog);

        //init
        positiveButton = (Button)findViewById(R.id.dialog_ok2);
        negativeButton = (Button)findViewById(R.id.dialog_Cancel2);
        idText = (EditText)findViewById(R.id.IDtext);


        //버튼 클릭 리스너 등록
        positiveButton.setOnClickListener(this);
        negativeButton.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.dialog_ok2: //확인 버튼을 눌렀을 때
                //각각의 변수에 EidtText에서 가져온 값을 저장
                String member_id = idText.getText().toString();

                //인터페이스의 함수를 호출하여 변수에 저장된 값들을 Activity로 전달
                customDialogListener.onPositiveClicked(member_id);

                // 커스텀 다이얼로그를 종료한다.
                dismiss();
                break;

            case R.id.dialog_Cancel2: //취소 버튼을 눌렀을 때
                customDialogListener.onNegativeClicked();
                cancel();
                break;
        }
    }
}


