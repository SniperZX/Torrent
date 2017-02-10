package com.rock.software.torrent.Parser;

import com.rock.software.torrent.Bean.ResultBean;

import java.util.List;


/**
 * Created by Administrator on 2016-02-21.
 */
public interface Parser {

    public List<ResultBean> parser(String str);
}
