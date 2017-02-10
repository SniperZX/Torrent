package com.huanrong.velley;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2015/11/11.
 */
public class BaseRequest extends JsonObjectRequest {

    JSONObject jsonRequest;
    Method method;
    HashMap<String, String> headers;

    /**
     * get请求的默认构造方法
     */
    public BaseRequest(String url, Response.Listener<JSONObject> listener) {
        super(Method.GET, url, "", listener, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.e(error.toString());
            }
        });
        headers = new HashMap<String, String>();
    }

    public BaseRequest(String url, Response.Listener<JSONObject> listener,Response.ErrorListener errorListener ) {
        super(Method.GET, url, "", listener,errorListener);
        headers = new HashMap<String, String>();
    }


    /**
     * post方法的默认构造方法
     */
    public BaseRequest(String url, JSONObject jsonRequest, Response.Listener<JSONObject> listener) {
        super(Method.POST, url, jsonRequest, listener, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.e("" + error.toString());
            }
        });
        this.jsonRequest = jsonRequest;
        this.setRetryPolicy(new DefaultRetryPolicy(20 * 1000, 1, 1.0f));
        headers = new HashMap<String, String>();
    }

    /**
     * 默认构造方法
     *
     * @param method
     * @param url
     * @param jsonRequest
     * @param listener
     * @param errorListener
     */
    public BaseRequest(int method, String url, JSONObject jsonRequest, Response.Listener<JSONObject> listener, Response.ErrorListener errorListener) {
        super(method, url, jsonRequest, listener, errorListener);
        this.jsonRequest = jsonRequest;
        this.headers = new HashMap<String, String>();
    }

    @Override
    public Map<String, String> getHeaders() throws AuthFailureError, JSONException {
        HashMap<String, String> lastHeader = new HashMap<String, String>();
        headers.put("language",Headers.getInstance().getLanguage());
        headers.put("appToken",Headers.getInstance().getApptoken());
        headers.put("appSecret",Headers.getInstance().getAppSecret());
        lastHeader.putAll(headers);
        return lastHeader;
    }

    public void setHeaders(HashMap<String, String> headers) {
        this.headers = headers;
    }
}
