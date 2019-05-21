package com.example.wlsxo.checkmate;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class RoomRequest extends StringRequest {
    final static private String URL =
            "http://wodud2083.cafe24.com/Checkmate/roomlist.php";//php이름 지정
    private Map<String, String> parameters;

    public RoomRequest(Response.Listener<String> listener) {
        super(Method.POST,URL, listener, null);
        parameters = new HashMap<>();
    }

    @Override
    public  Map<String, String> getParams() { return parameters; }
}
