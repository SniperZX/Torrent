package com.rock.software.torrent.Parser;

import android.text.TextUtils;

import com.rock.software.torrent.Bean.ResultBean;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


/**
 * Created by Administrator on 2016-02-21.
 */
public class XHubParser implements Parser {
    List<ResultBean> list;

    @Override
    public List<ResultBean> parser(String str) {
        list = new ArrayList<>();
        if(!TextUtils.isEmpty(str)&&str.length()>10){
        try {
            JSONObject jsonObject = new JSONObject(str);
            JSONObject jsonObject1 = jsonObject.optJSONObject("data");
            if (jsonObject1 != null) {
                Iterator<String> keys = jsonObject1.keys();
                while (keys.hasNext()) {
                    ResultBean resultBean = new ResultBean();
                    String key = (String) keys.next().toString().trim();
                    resultBean.magnet = "magnet:?xt=urn:btih:" + key;
                    JSONObject json = jsonObject1.optJSONObject(key);
                    resultBean.name = json.optString("title");
                    resultBean.size = json.optString("size");
                    resultBean.detail = json.optString("day");
                    list.add(resultBean);
                }
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        }
        return list;
    }
}
