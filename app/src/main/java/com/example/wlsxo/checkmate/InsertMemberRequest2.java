package com.example.wlsxo.checkmate;

import android.util.Log;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class InsertMemberRequest2  extends StringRequest {
    final static private String URL =
            "http://wodud2083.cafe24.com/Checkmate/insertMember2.php";//php이름 지정
    private Map<String, String> parameters;

    public InsertMemberRequest2(int roomNumber, String [] memberID, Response.Listener<String> listener) {
        super(Method.POST,URL, listener, null);
        parameters = new HashMap<>();

        parameters.put("roomNum",roomNumber + "");

        parameters.put("oneID",memberID[0]);
        parameters.put("twoID",memberID[1]);

        Log.i("맴버1",parameters.get("oneID"));
        Log.i("맴버2",parameters.get("twoID"));

        Log.i("방번호",parameters.get("roomNum"));

    }

    @Override
    public  Map<String, String> getParams() { return parameters; }
}
