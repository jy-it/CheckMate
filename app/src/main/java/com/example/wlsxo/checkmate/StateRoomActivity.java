package com.example.wlsxo.checkmate;


import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class StateRoomActivity extends AppCompatActivity {
    //waitingroom 용
    ArrayList<String> arraylist;
    ArrayAdapter<String> arrayAdapter;
    private ListView listview2 ;
    private ListWaitingAdapter adapter2;
    private int[] img = {R.drawable.seoul,R.drawable.busan,R.drawable.seoul,R.drawable.busan,
            R.drawable.seoul,R.drawable.busan,R.drawable.seoul,R.drawable.busan};
    //waitingroom 용

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stateroom);

        //makingroom 리스트-------------------------------------------------//
        ListView listview ;
        ListMakingAdapter adapter;

        // Adapter 생성
        adapter = new ListMakingAdapter() ;

        // 리스트뷰 참조 및 Adapter달기
        listview = (ListView) findViewById(R.id.makingList);
        listview.setAdapter(adapter);

        // 첫 번째 아이템 추가.
        adapter.addItem(ContextCompat.getDrawable(this, R.drawable.ic_launcher_background),
                "", "") ;
        // 두 번째 아이템 추가.
        adapter.addItem(ContextCompat.getDrawable(this, R.drawable.busan),
                "", "") ;
        // 세 번째 아이템 추가.
        adapter.addItem(ContextCompat.getDrawable(this, R.drawable.seoul),
                "", "") ;

        // 위에서 생성한 listview에 클릭 이벤트 핸들러 정의.
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView parent, View v, int position, long id) {
                // get item
                MakingStateVO item = (MakingStateVO) parent.getItemAtPosition(position) ;

                Drawable iconDrawable = item.getIcon() ;
                Toast.makeText(getApplicationContext()  ,"ID를 입력하세요",Toast.LENGTH_LONG).show();
                // TODO : use item data.
            }
        }) ;

        //------------makingroom 리스트-------------------//

        //------------waitingroom 리스트-------------------//
        arraylist = new ArrayList<String>();
        arraylist.add("1명");
        arraylist.add("2명");
        arraylist.add("3명");
        arraylist.add("4명");

        //변수 초기화
        adapter2 = new ListWaitingAdapter();
        listview2 = (ListView) findViewById(R.id.List_view);

        //어뎁터 할당
        listview2.setAdapter(adapter2);

        //adapter를 통한 값 전달
        for(int i=0; i<img.length;i++){
            adapter2.addVO(ContextCompat.getDrawable(this,img[i]),"방 번호: "+i, "인원 수: ");
        }

        // 커스텀 다이얼로그에서 입력한 메시지를 출력할 TextView 를 준비한다.
        final Button button1 = (Button) findViewById(R.id.room_add2);

        // 커스텀 다이얼로그를 호출할 이미지뷰을 정의한다.
/*        final Button button = (Button) button1.findViewById(R.id.room_add2);

        // 커스텀 다이얼로그 호출할 클릭 이벤트 리스너 정의
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 커스텀 다이얼로그를 생성한다. 사용자가 만든 클래스이다.
                StateRoomDialog2 customDialog = new StateRoomDialog2(StateRoomActivity.this);
                // 커스텀 다이얼로그를 호출한다.
                // 커스텀 다이얼로그의 결과를 출력할 TextView를 매개변수로 같이 넘겨준다.
                customDialog.callFunction(button1);
            }
        });*/
        //------------waitingroom 리스트-------------------//
    }

    public void extractInfo(View view){

    }

}


