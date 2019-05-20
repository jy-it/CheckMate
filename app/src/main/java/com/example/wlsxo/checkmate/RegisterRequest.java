package com.example.wlsxo.checkmate;

import android.util.Base64;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class RegisterRequest extends StringRequest {
    final static private String URL =
            "http://wodud2083.cafe24.com/Checkmate/registerMember.php";
    private Map<String, String> parameters;

    public  RegisterRequest(String userID, String userPassword, String userName, int userAge,
                            String userDept ,String userHobby, String userHeight, String userBody,
                            String userCharm, String userKakao, String userSex, String userArea, String userPicture,
                            Response.Listener<String> listener) {
        super(Method.POST,URL,listener,null);
        parameters = new HashMap<>();
        parameters.put("userID", userID);
        parameters.put("userPassword", userPassword);
        parameters.put("userName", userName);
        parameters.put("userAge", userAge + "");
        parameters.put("userDept", userDept);
        parameters.put("userHobby", userHobby);
        parameters.put("userHeight", userHeight);
        parameters.put("userBody", userBody);
        parameters.put("userCharm", userCharm);
        parameters.put("userKakao", userKakao);
        parameters.put("userSex", userSex);
        parameters.put("userArea", userArea);

        parameters.put("userPicture", userPicture);
    }

    @Override
    public Map<String, String> getParams() {
        return parameters;
    }
}
