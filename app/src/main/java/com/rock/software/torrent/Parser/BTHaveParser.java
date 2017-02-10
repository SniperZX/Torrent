package com.rock.software.torrent.Parser;

import com.rock.software.torrent.Bean.ResultBean;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * Created by Administrator on 2016-02-22.
 */
public class BTHaveParser implements Parser {

    List<ResultBean> list;

    @Override
    public List<ResultBean> parser(String str) {
        //<a href="(.*?)" class="download">磁力链接</a>
        list = new ArrayList<>();
        Pattern pattern = Pattern.compile("target=\"_blank\">(.*?)</a>");
        Matcher matcher = pattern.matcher(str);
        Pattern pat2 = Pattern.compile("<a href=\"(.*?)\" class=\"download\">磁力链接</a>");
        Matcher mat2 = pat2.matcher(str);
        int num = 0;
        while (matcher.find() && mat2.find()) {
            ResultBean sb = new ResultBean();
            sb.name = matcher.group(1);
            sb.magnet = mat2.group(1);
            list.add(sb);
        }
        return list;
    }


}
