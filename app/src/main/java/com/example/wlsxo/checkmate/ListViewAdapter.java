package com.example.wlsxo.checkmate;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ListViewAdapter extends BaseAdapter {

    private ArrayList<ListViewItem> listVO = new ArrayList<ListViewItem>() ;

    int r_num = 0;//kjt-수정(매칭신청)
    ArrayList<Integer> roomNumData = new ArrayList<Integer>();//kjt-수정(매칭신청)

    Context context;

    public ListViewAdapter(Context context) {
        roomNumData.clear();//kjt-수정(매칭신청)
        this.context = context;
    }

    @Override
    public int getCount() {
        return listVO.size() ;
    }

    //이 부분에서 리스트뷰에 데이터를 넣어줌


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //postion = ListView의 위치      /   첫번째면 position = 0
        final int pos = position;
        final Context context = parent.getContext();


        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.listview_roomlist, parent, false);
        }

        ImageView image = (ImageView) convertView.findViewById(R.id.image_sex) ;
        TextView title = (TextView) convertView.findViewById(R.id.rmlist_title) ;
        TextView numofperson = (TextView) convertView.findViewById(R.id.numofperson) ;
        Button btn_apply = convertView.findViewById(R.id.btn_apply);
        Button btn_detail =convertView.findViewById(R.id.btn_detail);



        ListViewItem listViewItem = listVO.get(position);

        // 아이템 내 각 위젯에 데이터 반영
        image.setImageDrawable(listViewItem.getImg());
        title.setText(listViewItem.getTitle());
        numofperson.setText(listViewItem.getnumofperson());

        r_num = listViewItem.getR_num();//kjt-수정(매칭신청)
        roomNumData.add(r_num);//kjt-수정(매칭신청)

        //리스트뷰 클릭 이벤트
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, roomNumData.get(pos) +"번 방이 클릭되었습니다.", Toast.LENGTH_SHORT).show();
            }
        });


        //매칭 신청
        btn_apply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(context, roomNumData.get(pos) +"번 방이 클릭되었습니다.", Toast.LENGTH_SHORT).show();

                // 커스텀 다이얼로그를 생성한다. 사용자가 만든 클래스이다.
                ListRoomapplyDialog customDialog = new ListRoomapplyDialog(context);
                // 커스텀 다이얼로그를 호출한다.
                // 커스텀 다이얼로그의 결과를 출력할 TextView를 매개변수로 같이 넘겨준다.



                //팀 번호를 받아온다.
                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("success");

                            if(success) {
                                //받은 데이터 읽어와서
                                final int t_num = jsonResponse.getInt("teamNumber");


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


                                idcode1.setText(MainActivity.userID); //일단 본인은 자동으로 추가
                                idcode1.setClickable(false);
                                idcode1.setFocusable(false);

                                okButton.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {



                                        //매칭 신청 맴버 등록
                                        Response.Listener<String> responseListener_member = new Response.Listener<String>() {

                                            @Override
                                            public void onResponse(String response) {//아래에서 보낸 파라미터와 php요청에 대한 응답받음
                                                try {

                                                    JSONObject jsonResponse = new JSONObject(response);
                                                    boolean success = jsonResponse.getBoolean("success");
                                                    if (success) {

                                                        Log.i("신청 맴버등록 ","SQL은 성공");


                                                    } else {
                                                        Log.i("맴버등록 ","실패");
                                                        Toast.makeText(context, "신청 맴버 DB에 저장 실패", Toast.LENGTH_LONG).show();
                                                    }
                                                } catch (JSONException e) {
                                                    Log.i("신청 맴버등록","예외발생");
                                                    e.printStackTrace();
                                                }
                                            }
                                        };


                                        if( (idcode2.getText().toString().matches("")) && (idcode3.getText().toString().matches("")) && (idcode4.getText().toString().matches(""))){ //1명 신청

                                            //파라미터들을 객체에 담는다.
                                            InsertApplicantRequest1 insertApplicantRequest1 = new InsertApplicantRequest1(roomNumData.get(pos), t_num, idcode1.getText().toString(), responseListener_member);

                                            //큐에 파라미터가 담긴 객체를 넣는다.
                                            RequestQueue queue = Volley.newRequestQueue(context);
                                            queue.add(insertApplicantRequest1);
                                            Toast.makeText(context, "신청 되었습니다.", Toast.LENGTH_SHORT).show();

                                        }
                                        else if( !(idcode2.getText().toString().matches("")) && (idcode3.getText().toString().matches("")) && (idcode4.getText().toString().matches(""))){//2명 신청

                                            //파라미터들을 객체에 담는다.
                                            InsertApplicantRequest2 insertApplicantRequest2 = new InsertApplicantRequest2(roomNumData.get(pos), t_num, idcode1.getText().toString(), idcode2.getText().toString(), responseListener_member);

                                            //큐에 파라미터가 담긴 객체를 넣는다.
                                            RequestQueue queue = Volley.newRequestQueue(context);
                                            queue.add(insertApplicantRequest2);
                                            Toast.makeText(context, "신청 되었습니다.", Toast.LENGTH_SHORT).show();
                                        }
                                        else if( !(idcode2.getText().toString().matches("")) && (!idcode3.getText().toString().matches("")) && (idcode4.getText().toString().matches(""))){//3명 신청

                                            //파라미터들을 객체에 담는다.
                                            InsertApplicantRequest3 insertApplicantRequest3 = new InsertApplicantRequest3(roomNumData.get(pos), t_num, idcode1.getText().toString(), idcode2.getText().toString(), idcode3.getText().toString(), responseListener_member);

                                            //큐에 파라미터가 담긴 객체를 넣는다.
                                            RequestQueue queue = Volley.newRequestQueue(context);
                                            queue.add(insertApplicantRequest3);
                                            Toast.makeText(context, "신청 되었습니다.", Toast.LENGTH_SHORT).show();
                                        }
                                        else if( !(idcode2.getText().toString().matches("")) && !(idcode3.getText().toString().matches("")) && !(idcode4.getText().toString().matches(""))){//4명 신청

                                            //파라미터들을 객체에 담는다.
                                            InsertApplicantRequest4 insertApplicantRequest4 = new InsertApplicantRequest4(roomNumData.get(pos), t_num, idcode1.getText().toString(), idcode2.getText().toString(), idcode3.getText().toString(), idcode4.getText().toString(), responseListener_member);

                                            //큐에 파라미터가 담긴 객체를 넣는다.
                                            RequestQueue queue = Volley.newRequestQueue(context);
                                            queue.add(insertApplicantRequest4);
                                            Toast.makeText(context, "신청 되었습니다.", Toast.LENGTH_SHORT).show();
                                        }



                                        //팀 번호를 업데이트 한다

                                        //데이터 받는 부분
                                        Response.Listener<String> responseListener = new Response.Listener<String>() {
                                            @Override
                                            public void onResponse(String response) {
                                                try {
                                                    JSONObject jsonResponse = new JSONObject(response);
                                                    boolean success = jsonResponse.getBoolean("success");

                                                    if(success) {
                                                        Toast.makeText(context, "팀 번호 업데이트 성공.", Toast.LENGTH_SHORT).show();
                                                    }
                                                    else {//데이터 송수신 실패시
                                                        Toast.makeText(context, "팀 번호 업데이트 실패.", Toast.LENGTH_SHORT).show();
                                                    }
                                                } catch (Exception e)
                                                {
                                                    e.printStackTrace();
                                                }
                                            }
                                        };

                                        UpdateTeamNumberRequest updateTeamNumberRequest = new UpdateTeamNumberRequest(responseListener);//보낼 데이터를 객체로 만듬
                                        RequestQueue queue = Volley.newRequestQueue(context);//데이터를 실을 큐
                                        queue.add(updateTeamNumberRequest);//데이터 전송






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
                            else {//데이터 송수신 실패시
                                Toast.makeText(context, "팀번호를 가져오지 못했습니다.", Toast.LENGTH_SHORT).show();
                            }
                        } catch (Exception e)
                        {
                            e.printStackTrace();
                        }
                    }
                };
                FindTeamRequest findTeamRequest = new FindTeamRequest(responseListener);//보낼 데이터를 객체로 만듬
                RequestQueue queue = Volley.newRequestQueue(context);//데이터를 실을 큐
                queue.add(findTeamRequest);//데이터 전송

                //customDialog.callFunction(roomNumData.get(pos));
            }
        });













        //방 세부정보 조회
        btn_detail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 커스텀 다이얼로그를 생성한다. 사용자가 만든 클래스이다.
                ListRoomdetailDialog1 customDialog = new ListRoomdetailDialog1(context);
                // 커스텀 다이얼로그를 호출한다.
                // 커스텀 다이얼로그의 결과를 출력할 TextView를 매개변수로 같이 넘겨준다.
                customDialog.callFunction();
            }
        });

        return convertView;
    }


    @Override
    public long getItemId(int position) {
        return position ;
    }


    @Override
    public Object getItem(int position) {
        return listVO.get(position) ;
    }



    // 데이터값 넣어줌
    public void addVO(Drawable room_sex, String title, String numofperson, int r_num ) {
        ListViewItem item = new ListViewItem();

        //kjt-수정(매칭신청)
        item.setR_num(r_num);

        item.setImg(room_sex);
        item.setTitle(title);
        item.setnumofperson(numofperson);
        listVO.add(item);
    }


}