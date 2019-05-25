package com.example.wlsxo.checkmate;

import android.content.Intent;
import android.os.Bundle;
import android.service.autofill.UserData;
import android.support.annotation.IdRes;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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
    ArrayList<RoomData> roomData = new ArrayList<RoomData>();
    private int[] img = {R.drawable.male,R.drawable.female};
    //방 제목, 인원 수 직접설정
    //private String[] Title = {"방 제목:1","방 제목:2", "방 제목:3", "방 제목:4", "방 제목:5", "방 제목:6", "방 제목:7", "방 제목:8", "방 제목:9", "방 제목:10", "방 제목:11", "방 제목:12"};
    //private String[] Context = {"인원 수:1","인원 수:2","인원 수:3","인원 수:4","인원 수:5","인원 수:6","인원 수:7","인원 수:8","인원 수:9","인원 수:10","인원 수:11","인원 수:12"};

    //방 이미 만들었는지 체크하기 위한 변수
    int r_check = 0;

    @Override
    protected void onResume() {
        super.onResume();
        adapter = new ListViewAdapter();
        Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    roomData.clear();
                    JSONArray jsonArray = new JSONArray(response);

                    for( int i = 0 ; i < jsonArray.length(); i++ ) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        boolean success = jsonObject.getBoolean("success");
                        if (success) {
                            int r_num = jsonObject.getInt("r_num");
                            String r_title = jsonObject.getString("r_title");
                            int join_num = jsonObject.getInt("join_num");
                            String sex = jsonObject.getString("sex");
                            roomData.add(new RoomData(r_num, r_title, join_num, sex));
                        }
                    }
                    adapter = new ListViewAdapter();
                    listview = (ListView) findViewById(R.id.List_view);
                    listview.setAdapter(adapter);

                    //adapter를 통한 값 전달 : 성별 / 방 제목 / 인원 수 -> roomdata
                    for(int i=0; i<roomData.size();i++){
                        if(roomData.get(i).getSex().equals("남")) {
                            adapter.addVO(ContextCompat.getDrawable(getApplicationContext(), img[0])
                                    , "방 제목: " + roomData.get(i).getR_title()
                                    , "인원 수: " + roomData.get(i).getJoin_num());
                        }
                        else {
                            adapter.addVO(ContextCompat.getDrawable(getApplicationContext(), img[1])
                                    , "방 제목: " + roomData.get(i).getR_title()
                                    , "인원 수: " + roomData.get(i).getJoin_num());
                        }
                    }
                } catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        };

        RoomRequest roomRequest = new RoomRequest(responseListener);
        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        queue.add(roomRequest);


    }

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

    }

    public void gomain(View view){
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }

    public void goCreateRoom(View view){



        //이미 방을 만들었는지 검사
        Response.Listener<String> responseListener_check = new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {//아래에서 보낸 파라미터와 php요청에 대한 응답받음

                try {

                    JSONObject jsonResponse = new JSONObject(response);
                    boolean success = jsonResponse.getBoolean("success");
                    if (success) {

                        r_check = jsonResponse.getInt("checkCount");

                        Log.i("방 개수 = ",r_check + "");

                        //생성한 방의 개수가 0개라면 통과
                        if(r_check == 0){
                            Intent intent = new Intent(RoomListActivity.this,CreateRoomActivity.class);
                            startActivity(intent);
                        }
                        else {
                            AlertDialog.Builder builder = new AlertDialog.Builder(RoomListActivity.this);
                            builder.setMessage("이미 만들어진 방이 있습니다.")
                                    .setNegativeButton("돌아가기", null)
                                    .create()
                                    .show();
                        }

                    } else {
                        Log.i("방 만들었는지 체크","검사 실패");
                    }
                } catch (JSONException e) {
                    e.printStackTrace();

                    Log.i("방 만들었는지 체크","예외발생");
                }

            }
        };
        //파라미터들을 객체에 담는다.
        CheckCreateRoomRequest checkCreateRoomRequest = new CheckCreateRoomRequest(MainActivity.userID, responseListener_check);

        //큐에 파라미터가 담긴 객체를 넣는다.
        RequestQueue queue_check = Volley.newRequestQueue(RoomListActivity.this);
        queue_check.add(checkCreateRoomRequest);





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
