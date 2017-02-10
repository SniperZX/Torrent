package com.rock.software.torrent.Parser;

import com.rock.software.torrent.Bean.ResultBean;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * Created by Administrator on 2016-02-21.
 */
public class CiJiLuParser implements Parser {
    List<ResultBean> list;
//<a href="/video/(.*?)">   <img src=".*?" title
    @Override
    public List<ResultBean> parser(String str) {
        list = new ArrayList<>();
        Pattern pattern = Pattern.compile("<a href=\"/video/(.*?)\">");
        Matcher matcher = pattern.matcher(str);
        Pattern pattern1 = Pattern.compile("<img src=\"(.*?)\" title");
        Matcher matcher1 = pattern1.matcher(str);
        while (matcher.find()&&matcher1.find()) {
            ResultBean sb = new ResultBean();
            String tmp = matcher.group(1);
            sb.name = tmp.substring(tmp.indexOf("/")+1);
            String code = tmp.substring(0,tmp.indexOf("/"));
            sb.playUrl = "http://www.luluhei.in/media/player/config_v.php?vkey="+code+"-1-1";
            sb.imageUrl = matcher1.group(1);
            list.add(sb);
        }
        return list;
    }
}
