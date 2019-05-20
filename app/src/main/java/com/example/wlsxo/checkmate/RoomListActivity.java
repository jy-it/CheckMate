package com.example.wlsxo.checkmate;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class RoomListActivity extends AppCompatActivity {

    de.hdodenhof.circleimageview.CircleImageView myimg_view;
    TextView myname;


    ArrayList<String> arraylist;
    ArrayAdapter<String> arrayAdapter;
    private Spinner sp;
    private RadioButton rbtn_male, rbtn_female;
    private RadioGroup radioGroup;
    private ListView listview ;
    private ListViewAdapter adapter;
    private int[] img = {R.drawable.male,R.drawable.female,R.drawable.male,R.drawable.female,R.drawable.male,R.drawable.female,R.drawable.male,R.drawable.female,R.drawable.male,R.drawable.female,R.drawable.male,R.drawable.female};
    //방 제목, 인원 수 직접설정
    //private String[] Title = {"방 제목:1","방 제목:2", "방 제목:3", "방 제목:4", "방 제목:5", "방 제목:6", "방 제목:7", "방 제목:8", "방 제목:9", "방 제목:10", "방 제목:11", "방 제목:12"};
    //private String[] Context = {"인원 수:1","인원 수:2","인원 수:3","인원 수:4","인원 수:5","인원 수:6","인원 수:7","인원 수:8","인원 수:9","인원 수:10","인원 수:11","인원 수:12"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_roomlist);

        //상단 바
        myimg_view = (de.hdodenhof.circleimageview.CircleImageView)findViewById(R.id.indexImage);
        myname = (TextView)findViewById(R.id.indexID);
        myimg_view.setImageBitmap(MainActivity.userPicture);
        myname.setText(MainActivity.userName);

        arraylist = new ArrayList<String>();
        arraylist.add("1명");
        arraylist.add("2명");
        arraylist.add("3명");
        arraylist.add("4명");


        arrayAdapter = new ArrayAdapter<>(getApplicationContext(),android.R.layout.simple_spinner_dropdown_item,arraylist);

        ArrayAdapter<String> s_adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_dropdown_item, arraylist);
        //스피너 속성
        Spinner sp = (Spinner) this.findViewById(R.id.spinner);
        sp.setPrompt("인원 수 선택"); // 스피너 제목
        sp.setAdapter(s_adapter);
        sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(getApplicationContext(), arraylist.get(i),
                        Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }


        });




        //라디오 버튼 설정
        rbtn_male = (RadioButton) findViewById(R.id.rbtn_male);
        rbtn_female = (RadioButton) findViewById(R.id.rbtn_female);
        rbtn_male.setOnClickListener(radioButtonClickListener);
        rbtn_female.setOnClickListener(radioButtonClickListener);

        //라디오 그룹 설정
        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        radioGroup.setOnCheckedChangeListener(radioGroupButtonChangeListener);



        //변수 초기화
        adapter = new ListViewAdapter();
        listview = (ListView) findViewById(R.id.List_view);

        //어뎁터 할당
        listview.setAdapter(adapter);

        //adapter를 통한 값 전달
        for(int i=0; i<img.length;i++){
            adapter.addVO(ContextCompat.getDrawable(this,img[i]),"방 번호: "+i, "인원 수: ");
        }

    }

    public void goCreateRoom(View view){
        Intent intent = new Intent(this,CreateRoomActivity.class);
        startActivity(intent);
    }



    //라디오 버튼 클릭 리스너
    RadioButton.OnClickListener radioButtonClickListener = new RadioButton.OnClickListener(){
        @Override
        public void onClick(View view) {
              }

    };

    //라디오 그룹 클릭 리스너
    RadioGroup.OnCheckedChangeListener radioGroupButtonChangeListener = new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
            if(i == R.id.rbtn_male){
                Toast.makeText(RoomListActivity.this, "남성", Toast.LENGTH_SHORT).show();
            }
            else if(i == R.id.rbtn_female){
                Toast.makeText(RoomListActivity.this, "여성", Toast.LENGTH_SHORT).show();
            }
        }
    };





/* 버튼으로 성별, 인원수 선택할 경우
    public void clickShowDialog_sex(View view) {
        final CharSequence items[] = { "남성", "여성"};

        AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);
        dialog.setTitle("Select sex");
        dialog.setCancelable(false);
        dialog.setSingleChoiceItems(items, -1, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int which) {
                Toast.makeText(getApplicationContext(), items[which], Toast.LENGTH_SHORT).show();
            }
        });
        dialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int which) {
                dialogInterface.dismiss();
            }
        });

        dialog.show();
    }



    public void clickShowDialog_numofpeople(View view) {
        final CharSequence items[] = { "1명", "2명", "3명", "4명" };

        AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);
        dialog.setTitle("Select number of people");
        dialog.setCancelable(false);
        dialog.setSingleChoiceItems(items, -1, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int which) {
                Toast.makeText(getApplicationContext(), items[which], Toast.LENGTH_SHORT).show();
            }
        });
        dialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int which) {
                dialogInterface.dismiss();
            }
        });

        dialog.show();
    }



*/









}
