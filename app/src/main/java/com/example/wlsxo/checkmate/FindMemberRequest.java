package com.example.wlsxo.checkmate;

import android.util.Log;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class FindMemberRequest  extends StringRequest {
    final static private String URL =
            "http://wodud2083.cafe24.com/Checkmate/find_Member.php";//php이름 지정
    private Map<String, String> parameters;

    public FindMemberRequest(int r_num,  Response.Listener<String> listener) {
        super(Method.POST,URL, listener, null);
        parameters = new HashMap<>();

        parameters.put("r_num",r_num + "");

        Log.i("맴버 리스트 추출 방번호",parameters.get("r_num"));

    }

    @Override
    public  Map<String, String> getParams() { return parameters; }
}
