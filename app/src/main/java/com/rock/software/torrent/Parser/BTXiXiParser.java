package com.rock.software.torrent.Parser;

import android.text.TextUtils;

import com.rock.software.torrent.Bean.ResultBean;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by zhaohongru on 2017-01-18.
 * PackageName: com.rock.software.torrent.Parser.BTXiXiParser
 * Description：
 */
public class BTXiXiParser implements Parser {

    List<ResultBean> list;

    @Override
    public List<ResultBean> parser(String str) {
        list = new ArrayList<>();
        if(!TextUtils.isEmpty(str)) {
            Pattern pattern = Pattern.compile("<a title=\"(.*?)\" target=\"_blank\" href=\"(.*?)\">");
            Matcher matcher = pattern.matcher(str);
            while (matcher.find()) {
                ResultBean sb = new ResultBean();
                sb.name = matcher.group(1);
                sb.magnet = matcher.group(2);
                //http://www.btkiki.com/torrent/0459c8622e2669566ba07cf3620a80ac1d7e5ef4.html
                sb.magnet = "magnet:?xt=urn:btih:" + sb.magnet.substring(sb.magnet.indexOf("torrent")+8, sb.magnet.indexOf(".html"));
                list.add(sb);
            }
        }
        return list;
    }
}
