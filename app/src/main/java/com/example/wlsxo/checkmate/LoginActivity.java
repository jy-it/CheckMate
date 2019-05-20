package com.example.wlsxo.checkmate;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final EditText idText = (EditText) findViewById(R.id.idText1);
        final EditText passwordText = (EditText) findViewById(R.id.passwordText1);
        final Button loginButton = (Button) findViewById(R.id.loginButton);
        final TextView registerButton = (TextView) findViewById(R.id.resisterButton);

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {//회원가입 버튼 클릭시
                Intent registerIntent = new Intent(LoginActivity.this, RegisterActivity.class);
                LoginActivity.this.startActivity(registerIntent);
            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {//로그인 버튼 클릭 시

                //쿼리에 넣을 데이터
                final String userID = idText.getText().toString();
                final String userPassword = passwordText.getText().toString();

                //데이터 받는 부분
                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("success");

                            if(success) {
                                //받은 데이터 읽어와서
                                String ID = jsonResponse.getString("userID");
                                String PW = jsonResponse.getString("userPassword");
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

                                String userPicture = jsonResponse.getString("userPicture");

                                //인텐트에 넣고 원하는 곳으로 전송
                                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                intent.putExtra("userID",ID);
                                intent.putExtra("userPassword",PW);
                                intent.putExtra("userName", userName);
                                intent.putExtra("userAge", userAge + "");
                                intent.putExtra("userDept", userDept);
                                intent.putExtra("userHobby", userHobby);
                                intent.putExtra("userHeight", userHeight);
                                intent.putExtra("userBody", userBody);
                                intent.putExtra("userCharm", userCharm);
                                intent.putExtra("userKakao", userKakao);
                                intent.putExtra("userSex", userSex);
                                intent.putExtra("userArea", userArea);

                                intent.putExtra("userPicture", userPicture) ;

                                LoginActivity.this.startActivity(intent);
                            }
                            else {//데이터 송수신 실패시
                                AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
                                builder.setMessage("로그인에 실패하였습니다.")
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

                LoginRequest loginRequest = new LoginRequest(userID, userPassword, responseListener);//보낼 데이터를 객체로 만듬
                RequestQueue queue = Volley.newRequestQueue(LoginActivity.this);//데이터를 실을 큐
                queue.add(loginRequest);//데이터 전송
            }
        });

    }
}
