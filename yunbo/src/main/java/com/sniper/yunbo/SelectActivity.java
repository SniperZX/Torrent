package com.sniper.yunbo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.huanrong.velley.BaseRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class SelectActivity extends AppCompatActivity {

    List<MagnetInfo> list;
    VideoInfo videoInfo;
    private RequestQueue mRequestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select);
        videoInfo = new VideoInfo();
        ListView listView = (ListView) findViewById(R.id.list);
        String data = getIntent().getStringExtra("json");
        list = new ArrayList<>();
        try {
            JSONObject jsonObject = new JSONObject(data);
            JSONArray jsonArray = jsonObject.getJSONArray("data");
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                MagnetInfo mg = new MagnetInfo();
                mg.setData(jsonObject1.optString("data"));
                mg.setName(jsonObject1.optString("name"));
                mg.setSize(jsonObject1.optString("size"));
                list.add(mg);
            }
            MyAdapter myAdapter = new MyAdapter(list);
            listView.setAdapter(myAdapter);

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }


    class MyAdapter extends BaseAdapter {
        public MyAdapter(List<MagnetInfo> list) {
            this.list = list;
        }

        List<MagnetInfo> list;

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int position) {
            return list.get(position);
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            View view = convertView;
            ViewHolder viewHolder;
            if (view == null) {
                viewHolder = new ViewHolder();
                view = getLayoutInflater().inflate(R.layout.listitem, parent, false);
                viewHolder.data = (TextView) view.findViewById(R.id.data);
                viewHolder.name = (TextView) view.findViewById(R.id.name);
                viewHolder.size = (TextView) view.findViewById(R.id.size);
                viewHolder.linearLayout = (LinearLayout) view.findViewById(R.id.item);
                view.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) view.getTag();
            }
            viewHolder.name.setText(list.get(position).getName());
            viewHolder.data.setText(list.get(position).getData());
            viewHolder.size.setText(list.get(position).getSize());
            viewHolder.linearLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    Intent intent = new Intent();
//                    intent.putExtra("data", list.get(position).getData());
//                    intent.putExtra("name", list.get(position).getName());
//                    setResult(RESULT_OK, intent);
//                    finish();
                    videoInfo.setTitle(list.get(position).getName());
                    getPlayUrl(list.get(position).getData());
                }
            });
            return view;
        }
    }

    class ViewHolder {
        public TextView name;
        public TextView data;
        public TextView size;
        public LinearLayout linearLayout;
    }



    public void getPlayUrl(String info) {
        //http://api.btvda.com/jk/bb.php?do=parse_xf_magnet&data=266c69737449443d34266461746149443d323734353533267549443d2673746174653d31303126
        String requestUrl = "http://api.btvda.com/jk/bb.php?do=parse_xf_magnet&data=" + info;
        BaseRequest baseRequest = new BaseRequest(requestUrl, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                try {
                    String info = jsonObject.getJSONObject("data").getString("play_url");
                    String cookie = jsonObject.getJSONObject("data").getString("play_url_cookie");
                    String result = RC4(info.replace("\r\n", ""), "key");
                    videoInfo.setCookie(cookie);
                    videoInfo.setUrl(result + "/M.mkv");
                    Intent intent = new Intent(SelectActivity.this, VitamioActivity.class);
                    intent.putExtra("videoInfo", videoInfo);
                    startActivity(intent);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {

            }
        });
        mRequestQueue = Volley.newRequestQueue(getApplicationContext());
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
