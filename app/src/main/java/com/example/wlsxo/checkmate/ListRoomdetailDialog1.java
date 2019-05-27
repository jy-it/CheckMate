package com.example.wlsxo.checkmate;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class ListRoomdetailDialog1 {

    private Context context;

    public ListRoomdetailDialog1(Context context) {
        this.context = context;
    }

    public static RoomVO roomVO = new RoomVO();

    static String uname;

    // 호출할 다이얼로그 함수를 정의한다.
    public void callFunction(Context c, int r_num) {

        // 커스텀 다이얼로그를 정의하기위해 Dialog클래스를 생성한다.
        final Dialog dlg = new Dialog(context);

        // 액티비티의 타이틀바를 숨긴다.
        dlg.requestWindowFeature(Window.FEATURE_NO_TITLE);

        // 커스텀 다이얼로그의 레이아웃을 설정한다.
        dlg.setContentView(R.layout.listroom_detaildialog1);

        // 커스텀 다이얼로그를 노출한다.
        dlg.show();

        // 커스텀 다이얼로그의 각 위젯들을 정의한다.
        final TextView r_title = (TextView) dlg.findViewById(R.id.textview_rtitle);
        final TextView r_content = (TextView) dlg.findViewById(R.id.textview_comment);

        final ImageView imageView1 = (ImageView) dlg.findViewById(R.id.imageViewdialog1);
        final ImageView imageView2 = (ImageView) dlg.findViewById(R.id.imageViewdialog2);
        final ImageView imageView3 = (ImageView) dlg.findViewById(R.id.imageViewdialog3);
        final ImageView imageView4 = (ImageView) dlg.findViewById(R.id.imageViewdialog4);
        final Button okButton = (Button) dlg.findViewById(R.id.dialog_ok);
        final Button cancelButton = (Button) dlg.findViewById(R.id.dialog_Cancel);


       // roomVO.Clear();
        //roomVO = new RoomVO();

        roomVO.setR_num(r_num);


        Log.i("방번호 = ", r_num + "");


        //방 정보를 읽어서 객체에 저장한다.
        Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonResponse = new JSONObject(response);
                    boolean success = jsonResponse.getBoolean("success");

                    if(success) {
                        //받은 데이터 읽어와서

                        Log.i("읽어온 방 정보","제목 : " + jsonResponse.getString("r_title") + " / 내용 : " + jsonResponse.getString("coment") );
                        String rtitle = jsonResponse.getString("r_title");
                        String coment = jsonResponse.getString("coment");

                        String reader_id = jsonResponse.getString("reader_id");
                        int join_num = jsonResponse.getInt("join_num");

                        roomVO.setR_title(rtitle);
                        roomVO.setComent(coment);
                        roomVO.setJoin_num(join_num);
                        roomVO.setReader_id(reader_id);

                        r_title.setText(rtitle);
                        r_content.setText(coment);






                        //방장의 정보를 읽어온 후 객체에 값을 저장한다.
                        Response.Listener<String> responseListener = new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                try {
                                    JSONObject jsonResponse = new JSONObject(response);
                                    boolean success = jsonResponse.getBoolean("success");

                                    if(success) {
                                        //받은 데이터 읽어와서

                                        String userID = jsonResponse.getString("userID");
                                        //userPassword = jsonResponse.getString("userPassword");
                                        String userName = jsonResponse.getString("userName");
                                        String userAge = jsonResponse.getString("userAge");
                                        String userDept = jsonResponse.getString("userDept");
                                        String userHobby = jsonResponse.getString("userHobby");
                                        String userHeight = jsonResponse.getString("userHeight");
                                        String userBody = jsonResponse.getString("userBody");
                                        String userCharm = jsonResponse.getString("userCharm");
                                        String userKakao = jsonResponse.getString("userKakao");
                                        String userSex = jsonResponse.getString("userSex");
                                        String userArea = jsonResponse.getString("userArea");

                                        Bitmap userPicture = MainActivity.StringToBitMap(jsonResponse.getString("userPicture"));

                                        UserVO userVO = new UserVO();
                                        userVO.setUserID(userID);
                                        userVO.setUserName(userName);
                                        userVO.setUserAge(userAge);
                                        userVO.setUserDept(userDept);
                                        userVO.setUserHobby(userHobby);
                                        userVO.setUserHeight(userHeight);
                                        userVO.setUserBody(userBody);
                                        userVO.setUserCharm(userCharm);
                                        userVO.setUserKakao(userKakao);
                                        userVO.setUserSex(userSex);
                                        userVO.setUserArea(userArea);
                                        userVO.setUserPicture(userPicture);

                                        //방장 정보를 객체에 저장
                                        roomVO.member_info.add(userVO);

                                        imageView1.setImageBitmap(roomVO.member_info.get(0).getUserPicture());




                                        //방의 맴버 리스트를 불러온다.

                                        Response.Listener<String> responseListener = new Response.Listener<String>() {
                                            @Override
                                            public void onResponse(String response) {
                                                try {

                                                    JSONArray jsonArray = new JSONArray(response);

                                                    ArrayList<String> members = new ArrayList<String>();

                                                    for( int i = 0 ; i < jsonArray.length(); i++ ) {

                                                        JSONObject jsonObject = jsonArray.getJSONObject(i);

                                                        boolean success = jsonObject.getBoolean("success");
                                                        if (success) {
                                                            String member_id = jsonObject.getString("member_id");

                                                            members.add(member_id);

                                                            Log.i("방 맴버 " + i + " = ",members.get(i));
                                                        }
                                                    }


                                                    //각 맴버들을 객체에 저장 및 화면에 뿌림
                                                    for( int i = 0 ; i < members.size(); i++ ){

                                                        Response.Listener<String> responseListener = new Response.Listener<String>() {
                                                            @Override
                                                            public void onResponse(String response) {
                                                                try {
                                                                    JSONObject jsonResponse = new JSONObject(response);
                                                                    boolean success = jsonResponse.getBoolean("success");

                                                                    if(success) {
                                                                        //받은 데이터 읽어와서

                                                                        String userID = jsonResponse.getString("userID");
                                                                        //userPassword = jsonResponse.getString("userPassword");
                                                                        String userName = jsonResponse.getString("userName");
                                                                        String userAge = jsonResponse.getString("userAge");
                                                                        String userDept = jsonResponse.getString("userDept");
                                                                        String userHobby = jsonResponse.getString("userHobby");
                                                                        String userHeight = jsonResponse.getString("userHeight");
                                                                        String userBody = jsonResponse.getString("userBody");
                                                                        String userCharm = jsonResponse.getString("userCharm");
                                                                        String userKakao = jsonResponse.getString("userKakao");
                                                                        String userSex = jsonResponse.getString("userSex");
                                                                        String userArea = jsonResponse.getString("userArea");

                                                                        Bitmap userPicture = MainActivity.StringToBitMap(jsonResponse.getString("userPicture"));

                                                                        UserVO userVO = new UserVO();
                                                                        userVO.setUserID(userID);
                                                                        userVO.setUserName(userName);
                                                                        userVO.setUserAge(userAge);
                                                                        userVO.setUserDept(userDept);
                                                                        userVO.setUserHobby(userHobby);
                                                                        userVO.setUserHeight(userHeight);
                                                                        userVO.setUserBody(userBody);
                                                                        userVO.setUserCharm(userCharm);
                                                                        userVO.setUserKakao(userKakao);
                                                                        userVO.setUserSex(userSex);
                                                                        userVO.setUserArea(userArea);
                                                                        userVO.setUserPicture(userPicture);


                                                                        //방장 정보를 객체에 저장
                                                                        roomVO.member_info.add(userVO);
                                                                        Intent intent = new Intent();
                                                                        //intent.p("userVO",userVO);



                                                                    }
                                                                    else {//데이터 송수신 실패시
                                                                        AlertDialog.Builder builder = new AlertDialog.Builder(context);
                                                                        builder.setMessage("유저 정보 조회에 실패하였습니다.")
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

                                                        UserInfoRequest userInfoRequest = new UserInfoRequest(members.get(i), responseListener);//보낼 데이터를 객체로 만듬
                                                        RequestQueue queue = Volley.newRequestQueue(context);//데이터를 실을 큐
                                                        queue.add(userInfoRequest);//데이터 전송
                                                    }

                                                    Log.i("ArrayList 맴버객체 길이 : ", roomVO.member_info.size() + "");

                                                    if(members.size() == 2){
                                                        imageView2.setImageBitmap(roomVO.member_info.get(1).getUserPicture());

                                                    }
                                                    else if(members.size() == 3){
                                                        imageView2.setImageBitmap(roomVO.member_info.get(1).getUserPicture());
                                                        imageView3.setImageBitmap(roomVO.member_info.get(2).getUserPicture());

                                                    }
                                                    else  if(members.size() == 4){
                                                        imageView2.setImageBitmap(roomVO.member_info.get(1).getUserPicture());
                                                        imageView3.setImageBitmap(roomVO.member_info.get(2).getUserPicture());
                                                        imageView4.setImageBitmap(roomVO.member_info.get(3).getUserPicture());

                                                    }



                                                } catch (Exception e)
                                                {
                                                    e.printStackTrace();
                                                }
                                            }
                                        };

                                        FindMemberRequest findMemberRequest = new FindMemberRequest(roomVO.getR_num(),responseListener);
                                        RequestQueue queue = Volley.newRequestQueue(context);
                                        queue.add(findMemberRequest);










                                    }
                                    else {//데이터 송수신 실패시
                                        AlertDialog.Builder builder = new AlertDialog.Builder(context);
                                        builder.setMessage("유저 정보 조회에 실패하였습니다.")
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

                        UserInfoRequest userInfoRequest = new UserInfoRequest(roomVO.getReader_id(), responseListener);//보낼 데이터를 객체로 만듬
                        RequestQueue queue = Volley.newRequestQueue(context);//데이터를 실을 큐
                        queue.add(userInfoRequest);//데이터 전송






                    }
                    else {//데이터 송수신 실패시
                        AlertDialog.Builder builder = new AlertDialog.Builder(context);
                        builder.setMessage("방 정보 조회에 실패하였습니다.")
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

        RoomInfoRequest roomInfoRequest = new RoomInfoRequest(r_num, responseListener);//보낼 데이터를 객체로 만듬
        RequestQueue queue = Volley.newRequestQueue(context);//데이터를 실을 큐
        queue.add(roomInfoRequest);//데이터 전송












//        TODO 방 상세 정보(돋보기 눌렀을때) 후에 사진 클릭시 그 사람 상세정보 보기
        imageView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ListRoomdetailDialog2 customDialog = new ListRoomdetailDialog2(context);

                customDialog.callFunction();
            }
        });

        //        TODO 방 상세 정보(돋보기 눌렀을때) 후에 사진 클릭시 그 사람 상세정보 보기
        imageView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ListRoomdetailDialog2 customDialog = new ListRoomdetailDialog2(context);

                customDialog.callFunction();
            }
        });

        //        TODO 방 상세 정보(돋보기 눌렀을때) 후에 사진 클릭시 그 사람 상세정보 보기
        imageView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ListRoomdetailDialog2 customDialog = new ListRoomdetailDialog2(context);

                customDialog.callFunction();
            }
        });

        //        TODO 방 상세 정보(돋보기 눌렀을때) 후에 사진 클릭시 그 사람 상세정보 보기
        imageView4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ListRoomdetailDialog2 customDialog = new ListRoomdetailDialog2(context);

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
