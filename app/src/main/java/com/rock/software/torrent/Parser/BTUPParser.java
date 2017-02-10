package com.rock.software.torrent.Parser;

import com.rock.software.torrent.Bean.ResultBean;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * Created by Administrator on 2016-02-22.
 */
public class BTUPParser implements Parser {

    List<ResultBean> list;

    @Override
    public List<ResultBean> parser(String str) {
        list = new ArrayList<>();
        Pattern pattern = Pattern.compile("<li><a href=\"(.*?)\">(.*?)<");
        Matcher matcher = pattern.matcher(str);
        int num = 0;
        while (matcher.find()) {
            ResultBean sb = new ResultBean();
            sb.magnet = matcher.group(1);
            sb.name = matcher.group(2);
            sb.magnet = "magnet:?xt=urn:btih:" + sb.magnet.substring(sb.magnet.indexOf("info") + 5);
            list.add(sb);
        }
        list.remove(list.size() - 1);
        return list;
    }


}
