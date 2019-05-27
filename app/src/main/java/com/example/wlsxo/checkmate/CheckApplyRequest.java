package com.example.wlsxo.checkmate;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;


public class CheckApplyRequest  extends StringRequest {
    final static private String URL =
            "http://wodud2083.cafe24.com/Checkmate/check_apply.php";//php이름 지정
    private Map<String, String> parameters;

    public CheckApplyRequest(String userID, int r_num, Response.Listener<String> listener) {
        super(Method.POST,URL, listener, null);
        parameters = new HashMap<>();
        parameters.put("userID", userID);
        parameters.put("r_num", r_num + "");
    }

    @Override
    public  Map<String, String> getParams() { return parameters; }
}



