package com.rock.software.torrent.Parser;

import com.rock.software.torrent.Bean.ResultBean;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by zhaohongru on 2017-01-19.
 * PackageName: com.rock.software.torrent.Parser.BTottParser
 * Description：
 */


public class BTottParser implements Parser {

    List<ResultBean> list;


    @Override
    public List<ResultBean> parser(String str) {
        list = new ArrayList<>();
        Pattern pattern = Pattern.compile("<a title=\"(.*?)\" target=\"_blank\" href=\"(.*?)\">");
        Matcher matcher = pattern.matcher(str);
        while (matcher.find()) {
            ResultBean sb = new ResultBean();
            sb.name = matcher.group(1);
            sb.magnet = matcher.group(2);
            sb.magnet = "magnet:?xt=urn:btih:" + sb.magnet.substring(sb.magnet.indexOf("btott.com/")+10, sb.magnet.indexOf(".html"));
            list.add(sb);
        }
        return list;
    }
}
