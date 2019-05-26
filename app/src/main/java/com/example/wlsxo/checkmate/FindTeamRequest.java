package com.example.wlsxo.checkmate;

import android.util.Log;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;


public class FindTeamRequest  extends StringRequest {
    final static private String URL =
            "http://wodud2083.cafe24.com/Checkmate/check_teamnumber.php";//php이름 지정
    private Map<String, String> parameters;

    public FindTeamRequest( Response.Listener<String> listener) {
        super(Method.POST,URL, listener, null);
        parameters = new HashMap<>();
    }

    @Override
    public  Map<String, String> getParams() { return parameters; }
}