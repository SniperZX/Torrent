package com.huanrong.velley;

import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.HttpHeaderParser;
import com.google.gson.Gson;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2016/5/16 0016.
 */
public class GsonRequest<T> extends Request<T>{

    private Gson gson;
    private Class<T> mclass;
    private Response.Listener<T> mListener;
    HashMap<String, String> headers;

    public GsonRequest(int method, String url, Class<T> mclass, Response.Listener<T> mlistener, Response.ErrorListener listener) {
        super(method, url, listener);
        gson = new Gson();
        this.mclass = mclass;
        this.mListener = mlistener;
    }

    public GsonRequest(int method, String url, Class<T> mclass, Response.Listener<T> mlistener) {
        super(method, url, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                VolleyLog.e("请求出错：" + volleyError.toString());
            }
        });
        gson = new Gson();
        this.mclass = mclass;
        this.mListener = mlistener;
    }


    @Override
    protected Response<T> parseNetworkResponse(NetworkResponse response) {
        try {
            String je = new String(response.data, HttpHeaderParser.parseCharset(response.headers, "utf-8"));
            Log.e("network","resp"+je);
            return Response.success( gson.fromJson(je,mclass), HttpHeaderParser.parseCacheHeaders(response));
        } catch (UnsupportedEncodingException var3) {
            return Response.error(new ParseError(var3));
        }
    }

    @Override
    protected void deliverResponse(T t) {
        if(this.mListener != null) {
            this.mListener.onResponse(t);
        }
    }

    @Override
    public Map<String, String> getHeaders() throws AuthFailureError {
        HashMap<String, String> lastHeader = new HashMap<String, String>();
//        lastHeader.putAll(headers);
        lastHeader.put("language",Headers.getInstance().getLanguage());
        lastHeader.put("apptoken", Headers.getInstance().getApptoken());
        lastHeader.put("appSecret", Headers.getInstance().getAppSecret());
        return lastHeader;
    }

    public void addHeaders(HashMap<String, String> headers) {
        this.headers = headers;
    }

}
