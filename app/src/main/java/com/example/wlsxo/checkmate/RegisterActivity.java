package com.example.wlsxo.checkmate;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URLEncoder;

public class RegisterActivity extends AppCompatActivity {
    private static final int PICK_FROM_CAMERA = 0;
    private static final int PICK_FROM_ALBUM = 200;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


        final EditText idText = (EditText) findViewById(R.id.idText);
        final EditText passwordText = (EditText) findViewById(R.id.passwordText);
        final EditText nameText = (EditText) findViewById(R.id.nameText);
        final EditText ageText = (EditText) findViewById(R.id.ageText);
        final RadioGroup sexRadio = (RadioGroup) findViewById(R.id.sexRadio);
        final EditText deptText = (EditText) findViewById(R.id.deptText);
        final EditText hobbyText = (EditText) findViewById(R.id.hobbyText);
        final Spinner areaSpinner = (Spinner) findViewById(R.id.areaSpinner);
        final Spinner heightSpinner = (Spinner) findViewById(R.id.heightSpinner);
        final Spinner bodySpinner = (Spinner) findViewById(R.id.bodySpinner);
        final EditText charmText = (EditText) findViewById(R.id.charmText);
        final EditText kakaoText = (EditText) findViewById(R.id.kakaoText);

        imageView = findViewById(R.id.registerImg);



        imageView.setOnClickListener(new View.OnClickListener() {

            DialogInterface.OnClickListener albumListener = new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    doTakeAlbumAction();
                }
            };

            DialogInterface.OnClickListener cancelListener = new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            };

            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(RegisterActivity.this)
                        .setTitle("업로드할 이미지 선택")
                        .setPositiveButton("앨범선택", albumListener)
                        .setNegativeButton("취소", cancelListener)
                        .show();
            }
        });


        Button resisterButton = (Button) findViewById(R.id.resisterButton); //회원가입 버튼 클릭 시
        resisterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {//파라미터 값 읽어오기
                String userID = idText.getText().toString();
                String userPassword = passwordText.getText().toString();
                String userName = nameText.getText().toString();
                int userAge = Integer.parseInt(ageText.getText().toString());
                String userSex = ( (RadioButton)findViewById(sexRadio.getCheckedRadioButtonId()) ).getText().toString();
                String userDept  = deptText.getText().toString();
                String userHobby = hobbyText.getText().toString();
                String userArea = areaSpinner.getSelectedItem().toString();
                String userHeight = heightSpinner.getSelectedItem().toString();
                String userBody = bodySpinner.getSelectedItem().toString();
                String userCharm = charmText.getText().toString();
                String userKakao  = kakaoText.getText().toString();


                //이미지뷰의 사진을 비트맵으로 추출
                BitmapDrawable bitmapDrawable = (BitmapDrawable) imageView.getDrawable();
                Bitmap tmpBitmap = bitmapDrawable.getBitmap();

                //변수에 사진 삽입
                String userPicture = BitMapToString(tmpBitmap);

                Log.v("변수에 사진 삽입 결과",userPicture);


                Response.Listener<String> responseListener = new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {//아래에서 보낸 파라미터와 php요청에 대한 응답받음
                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("success");
                            if (success) {
                                AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
                                builder.setMessage("회원 등록에 성공했습니다.")
                                        .setPositiveButton("확인", null)
                                        .create()
                                        .show();

                                //php로 읽어온 데이터들을 인텐트에 담고 이동
                                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                                RegisterActivity.this.startActivity(intent);
                            } else {
                                AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
                                builder.setMessage("회원 등록에 실패했습니다.")
                                        .setNegativeButton("다시 시도", null)
                                        .create()
                                        .show();
                                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                                RegisterActivity.this.startActivity(intent);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };
                //파라미터들을 객체에 담는다.
                RegisterRequest registerRequest = new RegisterRequest(userID, userPassword, userName, userAge,
                        userDept, userHobby, userHeight, userBody,
                        userCharm, userKakao, userSex, userArea , userPicture, responseListener);

                //큐에 파라미터가 담긴 객체를 넣는다.
                RequestQueue queue = Volley.newRequestQueue(RegisterActivity.this);
                queue.add(registerRequest);
            }
        });
    }


    //이미지 불러오기 그다음은 onActivity
    public void doTakeAlbumAction() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, PICK_FROM_ALBUM);
    }

    //불러온 이미지를 이미지 뷰에 저장
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode == PICK_FROM_ALBUM && resultCode == RESULT_OK
                && data != null && data.getData() != null) {

            Uri selectedImageUri = data.getData();
            Bitmap bitmap = null;
            try {
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(),selectedImageUri);

                Bitmap scaled = Bitmap.createScaledBitmap(bitmap,imageView.getWidth(),imageView.getHeight(),true);
                imageView.setImageBitmap(scaled);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    //이미지 인코딩
    public String  BitMapToString(Bitmap bitmap){
        ByteArrayOutputStream baos = new  ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG,100, baos);    //bitmap compress
        byte [] arr = baos.toByteArray();
        String image= Base64.encodeToString(arr, Base64.DEFAULT);


        return image;

    }
}
