package com.example.wlsxo.checkmate;

import android.util.Log;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;


public class InsertApplicantRequest3  extends StringRequest {
    final static private String URL =
            "http://wodud2083.cafe24.com/Checkmate/insertapplicant3.php";//php이름 지정
    private Map<String, String> parameters;

    public InsertApplicantRequest3(int roomNumber, int t_num, String memberID1, String memberID2, String memberID3, Response.Listener<String> listener) {
        super(Method.POST,URL, listener, null);
        parameters = new HashMap<>();

        parameters.put("r_num",roomNumber + "");
        parameters.put("t_num",t_num + "");

        parameters.put("oneID",memberID1);
        parameters.put("twoID",memberID2);
        parameters.put("threeID",memberID3);

        Log.i("맴버1",parameters.get("oneID"));
        Log.i("맴버1",parameters.get("twoID"));
        Log.i("맴버1",parameters.get("threeID"));
        Log.i("방번호",parameters.get("r_num"));
        Log.i("팀번호",parameters.get("t_num"));

    }

    @Override
    public  Map<String, String> getParams() { return parameters; }
}