package com.example.wlsxo.checkmate;

import android.util.Log;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class InsertMemberRequest4  extends StringRequest {
    final static private String URL =
            "http://wodud2083.cafe24.com/Checkmate/insertMember4.php";//php이름 지정
    private Map<String, String> parameters;

    public InsertMemberRequest4(int roomNumber, String [] memberID, Response.Listener<String> listener) {
        super(Method.POST,URL, listener, null);
        parameters = new HashMap<>();

        parameters.put("roomNum",roomNumber + "");

        parameters.put("oneID",memberID[0]);
        parameters.put("twoID",memberID[1]);
        parameters.put("threeID",memberID[2]);
        parameters.put("fourID",memberID[3]);

        Log.i("맴버1",parameters.get("oneID"));
        Log.i("맴버2",parameters.get("twoID"));
        Log.i("맴버3",parameters.get("threeID"));
        Log.i("맴버4",parameters.get("fourID"));

    }

    @Override
    public  Map<String, String> getParams() { return parameters; }
}
