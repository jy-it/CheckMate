
package com.example.wlsxo.checkmate;

import android.util.Log;

        import com.android.volley.Response;
        import com.android.volley.toolbox.StringRequest;

        import java.util.HashMap;
        import java.util.Map;

public class FindRoomNumberRequest  extends StringRequest {
    final static private String URL =
            "http://wodud2083.cafe24.com/Checkmate/findRoomNumber.php";//php이름 지정
    private Map<String, String> parameters;

    public FindRoomNumberRequest(String [] memberID, Response.Listener<String> listener) {
        super(Method.POST,URL, listener, null);
        parameters = new HashMap<>();

        parameters.put("readerID",memberID[0]);


        Log.i("번호 알아내는 아이디 = ",parameters.get("readerID"));
    }

    @Override
    public  Map<String, String> getParams() { return parameters; }
}