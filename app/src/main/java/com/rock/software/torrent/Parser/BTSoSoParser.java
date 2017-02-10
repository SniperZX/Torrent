package com.rock.software.torrent.Parser;

import com.rock.software.torrent.Bean.ResultBean;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * Created by Administrator on 2016-02-22.
 */
public class BTSoSoParser implements Parser {

    List<ResultBean> list;

    @Override
    public List<ResultBean> parser(String str) {
        //<a href="(.*?)" class="download">磁力链接</a>
        list = new ArrayList<>();
        Pattern pattern = Pattern.compile("target=\"_blank\" href=\"(.*?)\">(.*?)</a>");
        Matcher matcher = pattern.matcher(str);
        while (matcher.find()) {
            ResultBean sb = new ResultBean();
            sb.name = matcher.group(2);
            sb.magnet = matcher.group(1);
            sb.magnet = "magnet:?xt=urn:btih:" + sb.magnet.substring(1, sb.magnet.indexOf(".") - 1);
            list.add(sb);
        }
        return list;
    }


}
