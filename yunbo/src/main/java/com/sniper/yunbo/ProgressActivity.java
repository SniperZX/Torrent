package com.sniper.yunbo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.huanrong.velley.BaseRequest;

import org.json.JSONException;
import org.json.JSONObject;

public class ProgressActivity extends AppCompatActivity {

    protected TextView progress;
    private String magnet;
    private RequestQueue mRequestQueue;
    VideoInfo videoInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_progress);
        initView();
        magnet = getIntent().getStringExtra("magnet");
        addText("传入地址:" + magnet);
        addText("开始处理地址。。。");
        addText("处理结果" + transMag());
        addText("获取播放信息。。。");
        getPlayInfo();
    }


    private void initView() {
        progress = (TextView) findViewById(R.id.progress);
    }

    public void addText(String str) {
        StringBuilder sb = new StringBuilder(progress.getText());
        sb.append("\n");
        sb.append(str);
        progress.setText(sb.toString());
    }

    public String transMag() {
        if (magnet.contains("magnet")) {
            magnet = magnet.substring(magnet.indexOf("btih:") + 5);
        }
        return magnet;
    }

    public void getPlayInfo() {
        videoInfo = new VideoInfo();
        //http://api.btvda.com/jk/bb.php?do=get_magnet_info&hash=62C7D21584F76552DD5F9794B546987BE4FDE650
        String requestUrl = "http://api.btvda.com/jk/bb.php?do=get_magnet_info&hash=" + magnet;
        addText("requestUrl" + requestUrl);
        BaseRequest baseRequest = new BaseRequest(requestUrl, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                addText("结果：" + jsonObject.toString());
                try {
                    if(jsonObject.getJSONArray("data").length()>1) {
                        addText("====》》信息大于1条==》》跳转选择页面");
                        Intent intent = new Intent(ProgressActivity.this, SelectActivity.class);
                        intent.putExtra("json", jsonObject.toString());
                        startActivity(intent);
                    }else {
                        addText("====》》信息1条==》》解析开始");
                        String info = jsonObject.getJSONArray("data").getJSONObject(0).getString("data");
                        String name = jsonObject.getJSONArray("data").getJSONObject(0).getString("name");
                        addText("结果info："+info+"------> name:"+name);
                        videoInfo.setTitle(name);
                        getPlayUrl(info);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                addText("错误=====>" + volleyError.getMessage());
            }
        });
        mRequestQueue = Volley.newRequestQueue(getApplicationContext());
        mRequestQueue.add(baseRequest);

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK) {
//                                String info = jsonObject.getJSONArray("data").getJSONObject(0).getString("data");
//                    String name = jsonObject.getJSONArray("data").getJSONObject(0).getString("name");
            String info = data.getStringExtra("data");
            String name = data.getStringExtra("name");
            addText("结果info：" + info + "------> name:" + name);
            videoInfo.setTitle(name);
            getPlayUrl(info);


        }
    }

    public void getPlayUrl(String info) {
        //http://api.btvda.com/jk/bb.php?do=parse_xf_magnet&data=266c69737449443d34266461746149443d323734353533267549443d2673746174653d31303126
        String requestUrl = "http://api.btvda.com/jk/bb.php?do=parse_xf_magnet&data=" + info;
        addText("获取播放地址" + requestUrl);
        BaseRequest baseRequest = new BaseRequest(requestUrl, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                addText("结果：" + jsonObject.toString());
                try {
                    String info = jsonObject.getJSONObject("data").getString("play_url");
                    String cookie = jsonObject.getJSONObject("data").getString("play_url_cookie");
                    addText("结果info：" + info + "----->   cookie:" + cookie);
                    addText("开始解析。。。。");
                    String result = RC4(info.replace("\r\n", ""), "key");
                    addText("解析结果：" + result);
                    videoInfo.setCookie(cookie);
                    videoInfo.setUrl(result + "/M.mkv");
                    addText("跳转播放页面。。。。");
                    Intent intent = new Intent(ProgressActivity.this, VitamioActivity.class);
                    intent.putExtra("videoInfo", videoInfo);
                    startActivity(intent);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                addText("错误=====>" + volleyError.getMessage());
            }
        });
        mRequestQueue.add(baseRequest);

    }

    public String RC4(String data, String key) {
        String v1;
        if (data != null && key != null) {
            try {
                v1 = new String(RC4Base(HexString2Bytes(data), key), "GBK");
            } catch (Exception v0) {
                v0.printStackTrace();
                v1 = "";
            }
        } else {
            v1 = "";
        }

        return v1;
    }


    private byte[] RC4Base(byte[] input, String mKkey) {
        int v4 = 0;
        int v6 = 0;
        byte[] v1 = initKey(mKkey);
        byte[] v2 = new byte[input.length];
        int v0;
        for (v0 = 0; v0 < input.length; ++v0) {
            v4 = v4 + 1 & 255;
            v6 = (v1[v4] & 255) + v6 & 255;
            byte v3 = v1[v4];
            v1[v4] = v1[v6];
            v1[v6] = v3;
            v2[v0] = ((byte) (input[v0] ^ v1[(v1[v4] & 255) + (v1[v6] & 255) & 255]));
        }

        return v2;
    }

    private byte[] initKey(String aKey) {
        byte[] v7 = null;
        int v10 = 256;
        try {
            byte[] v0 = aKey.getBytes("GBK");
            byte[] v5 = new byte[256];
            int v2;
            for (v2 = 0; v2 < v10; ++v2) {
                v5[v2] = ((byte) v2);
            }

            int v3 = 0;
            int v4 = 0;
            if (v0 != null && v0.length != 0) {
                v2 = 0;
            } else {
                return v7;
            }

            while (v2 < v10) {
                v4 = (v0[v3] & 255) + (v5[v2] & 255) + v4 & 255;
                byte v6 = v5[v2];
                v5[v2] = v5[v4];
                v5[v4] = v6;
                v3 = (v3 + 1) % v0.length;
                ++v2;
            }

            return v5;
        } catch (Exception v1) {
            v1.printStackTrace();
            return v7;
        }
    }


    private byte uniteBytes(byte src0, byte src1) {
        return ((byte) ((((char) ((((char) Byte.decode("0x" + new String(new byte[]{src0})).byteValue()))
                << 4))) ^ (((char) Byte.decode("0x" + new String(new byte[]{src1})).byteValue()))));
    }

    private byte[] HexString2Bytes(String src) {
        byte[] v2;
        try {
            int v3 = src.length();
            v2 = new byte[v3 / 2];
            byte[] v4 = src.getBytes("GBK");
            int v1;
            for (v1 = 0; v1 < v3 / 2; ++v1) {
                v2[v1] = uniteBytes(v4[v1 * 2], v4[v1 * 2 + 1]);
            }
        } catch (Exception v0) {
            v0.printStackTrace();
            v2 = new byte[0];
        }

        return v2;
    }


}
