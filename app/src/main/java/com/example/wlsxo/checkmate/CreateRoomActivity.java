package com.example.wlsxo.checkmate;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class CreateRoomActivity extends AppCompatActivity implements View.OnClickListener{

    ImageView imageview1;
    ImageView imageview2;
    ImageView imageview3;
    ImageView imageview4;

    EditText r_Title;
    EditText r_Comment;

    int member_count;

    int roomNumber;

    String [] member_arr = new String[4];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_createroom);

        //초기화
        member_count = 0;
        for(int i = 0 ; i < 4 ; i++){
            member_arr[i] = new String();
        }



        imageview1 = (ImageView) findViewById(R.id.imageView1);
        imageview2 = (ImageView) findViewById(R.id.imageView2);
        imageview3 = (ImageView) findViewById(R.id.imageView3);
        imageview4 = (ImageView) findViewById(R.id.imageView4);

        r_Title = (EditText)findViewById(R.id.roomname);
        r_Comment = (EditText)findViewById(R.id.comment);


        //int joinNum = charmText.getText().toString();

        Button success = (Button) findViewById(R.id.Success);
        success.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String roomTitle = r_Title.getText().toString();
                String roomComment = r_Comment.getText().toString();




                Response.Listener<String> responseListener = new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {//아래에서 보낸 파라미터와 php요청에 대한 응답받음
                        try {

                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("success");
                            if (success) {
                                AlertDialog.Builder builder = new AlertDialog.Builder(CreateRoomActivity.this);
                                builder.setMessage("방 생성에 성공했습니다.")
                                        .setPositiveButton("확인", null)
                                        .create()
                                        .show();





                                //방번호 추출
                                Response.Listener<String> responseListener_rnum = new Response.Listener<String>() {

                                    @Override
                                    public void onResponse(String response) {//아래에서 보낸 파라미터와 php요청에 대한 응답받음
                                        try {

                                            JSONObject jsonResponse = new JSONObject(response);
                                            boolean success = jsonResponse.getBoolean("success");
                                            if (success) {

                                                roomNumber = jsonResponse.getInt("roomNumber");


                                                Log.i("방 번호 출력 = ", roomNumber + " 아이디 = " + member_arr[0]);









                                                //맴버 등록
                                                Response.Listener<String> responseListener_member = new Response.Listener<String>() {

                                                    @Override
                                                    public void onResponse(String response) {//아래에서 보낸 파라미터와 php요청에 대한 응답받음
                                                        try {

                                                            JSONObject jsonResponse = new JSONObject(response);
                                                            boolean success = jsonResponse.getBoolean("success");
                                                            if (success) {

                                                                Log.i("맴버등록 ","SQL은 성공");
                                                                Intent intent = new Intent(CreateRoomActivity.this, RoomListActivity.class);
                                                                CreateRoomActivity.this.startActivity(intent);


                                                            } else {
                                                                Log.i("맴버등록 ","실패");
                                                                Toast.makeText(getApplicationContext(), "맴버 DB에 저장 실패", Toast.LENGTH_LONG).show();
                                                            }
                                                        } catch (JSONException e) {
                                                            Log.i("맴버등록","예외발생");
                                                            e.printStackTrace();
                                                        }
                                                    }
                                                };
                                                if(member_count == 1){
                                                    //파라미터들을 객체에 담는다.
                                                    InsertMemberRequest1 insertMemberRequest1 = new InsertMemberRequest1(roomNumber, member_arr, responseListener_member);

                                                    //큐에 파라미터가 담긴 객체를 넣는다.
                                                    RequestQueue queue = Volley.newRequestQueue(CreateRoomActivity.this);
                                                    queue.add(insertMemberRequest1);
                                                }
                                                else if(member_count == 2){
                                                    //파라미터들을 객체에 담는다.
                                                    InsertMemberRequest2 insertMemberRequest2 = new InsertMemberRequest2(roomNumber, member_arr, responseListener_member);

                                                    //큐에 파라미터가 담긴 객체를 넣는다.
                                                    RequestQueue queue = Volley.newRequestQueue(CreateRoomActivity.this);
                                                    queue.add(insertMemberRequest2);
                                                }
                                                else if(member_count == 3){
                                                    //파라미터들을 객체에 담는다.
                                                    InsertMemberRequest3 insertMemberRequest3 = new InsertMemberRequest3(roomNumber, member_arr, responseListener_member);

                                                    //큐에 파라미터가 담긴 객체를 넣는다.
                                                    RequestQueue queue = Volley.newRequestQueue(CreateRoomActivity.this);
                                                    queue.add(insertMemberRequest3);
                                                }
                                                else if(member_count == 4){
                                                    //파라미터들을 객체에 담는다.
                                                    InsertMemberRequest4 insertMemberRequest4 = new InsertMemberRequest4(roomNumber, member_arr, responseListener_member);

                                                    //큐에 파라미터가 담긴 객체를 넣는다.
                                                    RequestQueue queue = Volley.newRequestQueue(CreateRoomActivity.this);
                                                    queue.add(insertMemberRequest4);
                                                }


                                                Toast.makeText(getApplicationContext(), "맴버 등록 완료", Toast.LENGTH_LONG).show();




                                            } else {
                                                Log.i("방 번호 추출 실패"," ");
                                            }
                                        } catch (JSONException e) {
                                            e.printStackTrace();

                                            Log.i("방 번호 추출 실패","예외발생");
                                        }
                                    }
                                };

                                //파라미터들을 객체에 담는다.
                                FindRoomNumberRequest findRoomNumberRequest = new FindRoomNumberRequest(member_arr, responseListener_rnum);

                                //큐에 파라미터가 담긴 객체를 넣는다.
                                RequestQueue queue_rnum = Volley.newRequestQueue(CreateRoomActivity.this);
                                queue_rnum.add(findRoomNumberRequest);




                                Intent intent = new Intent(CreateRoomActivity.this, RoomListActivity.class);
                                CreateRoomActivity.this.startActivity(intent);


                            } else {
                                AlertDialog.Builder builder = new AlertDialog.Builder(CreateRoomActivity.this);
                                builder.setMessage("방 생성에 실패했습니다.")
                                        .setNegativeButton("다시 시도", null)
                                        .create()
                                        .show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };
                CreateRoomRequest1 createRoomRequest1 = new CreateRoomRequest1(roomTitle,roomComment,member_count, member_arr, responseListener);

                //큐에 파라미터가 담긴 객체를 넣는다.
                RequestQueue queue = Volley.newRequestQueue(CreateRoomActivity.this);
                queue.add(createRoomRequest1);

                Toast.makeText(getApplicationContext(), "방 생성 완료", Toast.LENGTH_LONG).show();

            }
        });

        Button cancel = (Button) findViewById(R.id.Cancel);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "취소", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(CreateRoomActivity.this, RoomListActivity.class);
                CreateRoomActivity.this.startActivity(intent);

            }
        });
        //createroom 버튼 클릭시 -------------------------//





        //일단 자신은 포함
        member_count++;
        imageview1.setImageBitmap(MainActivity.userPicture);
        member_arr[0] = MainActivity.userID;

        imageview2.setOnClickListener(this);
        imageview3.setOnClickListener(this);
        imageview4.setOnClickListener(this);


        //createroom 버튼 클릭시 -----------------------------//
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){

            case R.id.imageView2:
                InputIdDialog dialog2 = new InputIdDialog(this);
                dialog2.setDialogListener(new InputIdDialog.CustomDialogListener() {
                    @Override
                    public void onPositiveClicked(String member_id) {
                        Toast.makeText(getApplicationContext(), "2번째 맴버 등록 성공 = " + member_id, Toast.LENGTH_LONG).show();

                        member_count++;

                        //쿼리에 넣을 데이터
                        String memberID = member_id;

                        //데이터 받는 부분
                        Response.Listener<String> responseListener = new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                try {
                                    JSONObject jsonResponse = new JSONObject(response);
                                    boolean success = jsonResponse.getBoolean("success");

                                    if(success) {
                                        //받은 데이터 읽어와서
                                        String memberID = jsonResponse.getString("userID");
                                        String memberName = jsonResponse.getString("userName");
                                        String memberPicture = jsonResponse.getString("userPicture");

                                        imageview2.setImageBitmap(MainActivity.StringToBitMap(memberPicture));
                                        member_arr[1] = memberID;
                                    }
                                    else {//데이터 송수신 실패시
                                        AlertDialog.Builder builder = new AlertDialog.Builder(CreateRoomActivity.this);
                                        builder.setMessage("맴버 등록에 실패하였습니다.")
                                                .setNegativeButton("다시 시도",null)
                                                .create()
                                                .show();
                                    }
                                } catch (Exception e)
                                {
                                    e.printStackTrace();
                                }
                            }
                        };

                        RoomMemberRequest roomMemberRequest = new RoomMemberRequest(memberID, responseListener);//보낼 데이터를 객체로 만듬
                        RequestQueue queue = Volley.newRequestQueue(CreateRoomActivity.this);//데이터를 실을 큐
                        queue.add(roomMemberRequest);//데이터 전송

                    }

                    @Override
                    public void onNegativeClicked() {
                        Toast.makeText(getApplicationContext(), "취소 성공", Toast.LENGTH_LONG).show();
                    }
                });
                dialog2.show();
                break;

            case R.id.imageView3:
                InputIdDialog dialog3 = new InputIdDialog(this);
                dialog3.setDialogListener(new InputIdDialog.CustomDialogListener() {
                    @Override
                    public void onPositiveClicked(String member_id) {
                        Toast.makeText(getApplicationContext(), "3번째 맴버 등록 성공 = " + member_id, Toast.LENGTH_LONG).show();

                        member_count++;

                        //쿼리에 넣을 데이터
                        String memberID = member_id;

                        //데이터 받는 부분
                        Response.Listener<String> responseListener = new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                try {
                                    JSONObject jsonResponse = new JSONObject(response);
                                    boolean success = jsonResponse.getBoolean("success");

                                    if(success) {
                                        //받은 데이터 읽어와서
                                        String memberID = jsonResponse.getString("userID");
                                        String memberName = jsonResponse.getString("userName");
                                        String memberPicture = jsonResponse.getString("userPicture");

                                        imageview3.setImageBitmap(MainActivity.StringToBitMap(memberPicture));
                                        member_arr[2] = memberID;
                                    }
                                    else {//데이터 송수신 실패시
                                        AlertDialog.Builder builder = new AlertDialog.Builder(CreateRoomActivity.this);
                                        builder.setMessage("맴버 등록에 실패하였습니다.")
                                                .setNegativeButton("다시 시도",null)
                                                .create()
                                                .show();
                                    }
                                } catch (Exception e)
                                {
                                    e.printStackTrace();
                                }
                            }
                        };

                        RoomMemberRequest roomMemberRequest = new RoomMemberRequest(memberID, responseListener);//보낼 데이터를 객체로 만듬
                        RequestQueue queue = Volley.newRequestQueue(CreateRoomActivity.this);//데이터를 실을 큐
                        queue.add(roomMemberRequest);//데이터 전송

                    }

                    @Override
                    public void onNegativeClicked() {
                        Toast.makeText(getApplicationContext(), "취소 성공", Toast.LENGTH_LONG).show();
                    }
                });
                dialog3.show();
                break;

            case R.id.imageView4:
                InputIdDialog dialog4 = new InputIdDialog(this);
                dialog4.setDialogListener(new InputIdDialog.CustomDialogListener() {
                    @Override
                    public void onPositiveClicked(String member_id) {
                        Toast.makeText(getApplicationContext(), "4번째 맴버 등록 성공 = " + member_id, Toast.LENGTH_LONG).show();

                        member_count++;

                        //쿼리에 넣을 데이터
                        String memberID = member_id;

                        //데이터 받는 부분
                        Response.Listener<String> responseListener = new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                try {
                                    JSONObject jsonResponse = new JSONObject(response);
                                    boolean success = jsonResponse.getBoolean("success");

                                    if(success) {
                                        //받은 데이터 읽어와서
                                        String memberID = jsonResponse.getString("userID");
                                        String memberName = jsonResponse.getString("userName");
                                        String memberPicture = jsonResponse.getString("userPicture");

                                        imageview4.setImageBitmap(MainActivity.StringToBitMap(memberPicture));
                                        member_arr[3] = memberID;
                                    }
                                    else {//데이터 송수신 실패시
                                        AlertDialog.Builder builder = new AlertDialog.Builder(CreateRoomActivity.this);
                                        builder.setMessage("맴버 등록에 실패하였습니다.")
                                                .setNegativeButton("다시 시도",null)
                                                .create()
                                                .show();
                                    }
                                } catch (Exception e)
                                {
                                    e.printStackTrace();
                                }
                            }
                        };

                        RoomMemberRequest roomMemberRequest = new RoomMemberRequest(memberID, responseListener);//보낼 데이터를 객체로 만듬
                        RequestQueue queue = Volley.newRequestQueue(CreateRoomActivity.this);//데이터를 실을 큐
                        queue.add(roomMemberRequest);//데이터 전송

                    }

                    @Override
                    public void onNegativeClicked() {
                        Toast.makeText(getApplicationContext(), "취소 성공", Toast.LENGTH_LONG).show();
                    }
                });
                dialog4.show();
                break;
        }
    }


}
