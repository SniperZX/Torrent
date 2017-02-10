package com.rock.software.torrent.Bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhaohongru on 2017-02-08.
 * PackageName: com.rock.software.torrent.Bean.LiveBean
 * Description：
 */
public class LiveBean implements Parcelable {

    /**
     * Result : true
     * RetObject : [{"Group":{"ID":16,"Name":"收藏","Icon":"favgroup","Order":1000},"Stations":[]},{"Group":{"ID":3,"Name":"央视","Icon":"cctv","Order":90},"Stations":[{"Name":"CCTV-10科教","ID":93,"Icon":"","Desc":""},{"Name":"CCTV-11戏曲","ID":94,"Icon":"","Desc":""},{"Name":"CCTV-12社会与法","ID":95,"Icon":"","Desc":""},{"Name":"CCTV-13新闻","ID":96,"Icon":"","Desc":""},{"Name":"CCTV-14少儿","ID":99,"Icon":"","Desc":""},{"Name":"CCTV-15音乐","ID":100,"Icon":"","Desc":""},{"Name":"CCTV-1综合","ID":101,"Icon":"","Desc":""},{"Name":"CCTV-2财经","ID":102,"Icon":"","Desc":""},{"Name":"CCTV-3综艺","ID":103,"Icon":"","Desc":""},{"Name":"CCTV-4中文国际","ID":104,"Icon":"","Desc":""},{"Name":"CCTV-5+体育赛事","ID":114,"Icon":"","Desc":""},{"Name":"CCTV-5体育","ID":115,"Icon":"","Desc":""},{"Name":"CCTV-6电影","ID":116,"Icon":"","Desc":""},{"Name":"CCTV-7军事与农业","ID":117,"Icon":"","Desc":""},{"Name":"CCTV-8电视剧","ID":119,"Icon":"","Desc":""},{"Name":"CCTV-9纪录","ID":120,"Icon":"","Desc":""},{"Name":"CCTV-世界地理","ID":125,"Icon":"","Desc":""},{"Name":"CCTV-中学生","ID":126,"Icon":"","Desc":""},{"Name":"CCTV-发现之旅","ID":130,"Icon":"","Desc":""},{"Name":"CCTV-国防军事","ID":131,"Icon":"","Desc":""},{"Name":"CCTV-女性时尚","ID":136,"Icon":"","Desc":""},{"Name":"CCTV-怀旧剧场","ID":138,"Icon":"","Desc":""},{"Name":"CCTV-文化精品","ID":139,"Icon":"","Desc":""},{"Name":"CCTV-新闻(英)","ID":140,"Icon":"","Desc":""},{"Name":"CCTV-法语","ID":142,"Icon":"","Desc":""},{"Name":"CCTV-电视指南","ID":143,"Icon":"","Desc":""},{"Name":"CCTV-第一剧场","ID":144,"Icon":"","Desc":""},{"Name":"CCTV-西班牙语","ID":150,"Icon":"","Desc":""},{"Name":"CCTV-西语","ID":151,"Icon":"","Desc":""},{"Name":"CCTV-证券资讯","ID":153,"Icon":"","Desc":""},{"Name":"CCTV-阿拉伯语","ID":154,"Icon":"","Desc":""},{"Name":"CCTV-阿语","ID":155,"Icon":"","Desc":""},{"Name":"CCTV-风云剧场","ID":156,"Icon":"","Desc":""},{"Name":"CCTV-风云足球","ID":157,"Icon":"","Desc":""},{"Name":"CCTV-风云音乐","ID":158,"Icon":"","Desc":""},{"Name":"CCTV-高尔夫网球","ID":159,"Icon":"","Desc":""},{"Name":"中国气象频道","ID":813,"Icon":"","Desc":""},{"Name":"CCTV-央视台球","ID":3211,"Icon":"","Desc":""},{"Name":"CCTV-新科动漫","ID":4867,"Icon":"","Desc":""},{"Name":"IPTV收视指南","ID":7095,"Icon":"","Desc":""},{"Name":"IPTV相声小品","ID":7096,"Icon":"","Desc":""},{"Name":"IPTV谍战剧场","ID":7097,"Icon":"","Desc":""},{"Name":"IPTV经典电影","ID":7098,"Icon":"","Desc":""},{"Name":"IPTV魅力时尚","ID":7099,"Icon":"","Desc":""},{"Name":"IPTV少儿动画","ID":7100,"Icon":"","Desc":""},{"Name":"IPTV3+","ID":7105,"Icon":"","Desc":""},{"Name":"IPTV6+","ID":7106,"Icon":"","Desc":""},{"Name":"IPTV5+","ID":7107,"Icon":"","Desc":""},{"Name":"IPTV8+","ID":7108,"Icon":"","Desc":""},{"Name":"CNTV古装剧场","ID":7748,"Icon":"","Desc":""},{"Name":"CNTV军旅剧场","ID":7750,"Icon":"","Desc":""},{"Name":"CNTV明星大片","ID":7752,"Icon":"","Desc":""},{"Name":"CNTV农业致富","ID":7753,"Icon":"","Desc":""},{"Name":"CNTV完美游戏","ID":7754,"Icon":"","Desc":""},{"Name":"CNTV健康有约","ID":7757,"Icon":"","Desc":""},{"Name":"CNTV海外剧场","ID":7758,"Icon":"","Desc":""},{"Name":"CNTV动画王国","ID":7760,"Icon":"","Desc":""},{"Name":"CNTV精品记录","ID":7761,"Icon":"","Desc":""},{"Name":"CNTV金牌综艺","ID":7762,"Icon":"","Desc":""},{"Name":"CNTV惊悚悬疑","ID":7763,"Icon":"","Desc":""}]},{"Group":{"ID":4,"Name":"卫视","Icon":"weishi","Order":80},"Stations":[{"Name":"东南卫视","ID":782,"Icon":"","Desc":""},{"Name":"东方卫视","ID":784,"Icon":"","Desc":""},{"Name":"中国黄河","ID":817,"Icon":"","Desc":""},{"Name":"云南卫视","ID":891,"Icon":"","Desc":""},{"Name":"健康卫视","ID":944,"Icon":"","Desc":""},{"Name":"兵团卫视","ID":967,"Icon":"","Desc":""},{"Name":"内蒙古卫视","ID":969,"Icon":"","Desc":""},{"Name":"农民卫视","ID":972,"Icon":"","Desc":""},{"Name":"北京卫视","ID":989,"Icon":"","Desc":""},{"Name":"厦门卫视","ID":1062,"Icon":"","Desc":""},{"Name":"吉林卫视","ID":1086,"Icon":"","Desc":""},{"Name":"四川卫视","ID":1120,"Icon":"","Desc":""},{"Name":"天津卫视","ID":1183,"Icon":"","Desc":""},{"Name":"宁夏卫视","ID":1221,"Icon":"","Desc":""},{"Name":"安徽卫视","ID":1233,"Icon":"","Desc":""},{"Name":"山东卫视","ID":1270,"Icon":"","Desc":""},{"Name":"山西卫视","ID":1280,"Icon":"","Desc":""},{"Name":"广东卫视","ID":1304,"Icon":"","Desc":""},{"Name":"广西卫视","ID":1322,"Icon":"","Desc":""},{"Name":"康巴卫视","ID":1328,"Icon":"","Desc":""},{"Name":"延边卫视","ID":1330,"Icon":"","Desc":""},{"Name":"新疆卫视","ID":1433,"Icon":"","Desc":""},{"Name":"旅游卫视","ID":1456,"Icon":"","Desc":""},{"Name":"江苏卫视","ID":1598,"Icon":"","Desc":""},{"Name":"江西卫视","ID":1609,"Icon":"","Desc":""},{"Name":"河北卫视","ID":1636,"Icon":"","Desc":""},{"Name":"河南卫视","ID":1650,"Icon":"","Desc":""},{"Name":"浙江卫视","ID":1910,"Icon":"","Desc":""},{"Name":"海峡卫视","ID":1932,"Icon":"","Desc":""},{"Name":"深圳卫视","ID":1948,"Icon":"","Desc":""},{"Name":"湖北卫视","ID":1976,"Icon":"","Desc":""},{"Name":"湖南卫视","ID":1995,"Icon":"","Desc":""},{"Name":"甘肃卫视","ID":2070,"Icon":"","Desc":""},{"Name":"西藏卫视","ID":2257,"Icon":"","Desc":""},{"Name":"贵州卫视","ID":2272,"Icon":"","Desc":""},{"Name":"辽宁卫视","ID":2295,"Icon":"","Desc":""},{"Name":"重庆卫视","ID":2349,"Icon":"","Desc":""},{"Name":"陕西卫视","ID":2407,"Icon":"","Desc":""},{"Name":"青海卫视","ID":2430,"Icon":"","Desc":""},{"Name":"黑龙江卫视","ID":2494,"Icon":"","Desc":""}]},{"Group":{"ID":5,"Name":"地方","Icon":"difang","Order":75},"Stations":[{"Name":"北京少儿","ID":991,"Icon":"","Desc":""},{"Name":"北京影视","ID":992,"Icon":"","Desc":""},{"Name":"北京文艺","ID":993,"Icon":"","Desc":""},{"Name":"北京新闻","ID":994,"Icon":"","Desc":""},{"Name":"北京生活","ID":995,"Icon":"","Desc":""},{"Name":"北京科教","ID":996,"Icon":"","Desc":""},{"Name":"北京财经","ID":997,"Icon":"","Desc":""},{"Name":"北京青年","ID":998,"Icon":"","Desc":""},{"Name":"山东公共","ID":1268,"Icon":"","Desc":""},{"Name":"山东农科","ID":1269,"Icon":"","Desc":""},{"Name":"山东生活","ID":1275,"Icon":"","Desc":""},{"Name":"广东国际","ID":1305,"Icon":"","Desc":""},{"Name":"广东新闻","ID":1308,"Icon":"","Desc":""},{"Name":"广东珠江","ID":1310,"Icon":"","Desc":""},{"Name":"河北公共","ID":1634,"Icon":"","Desc":""},{"Name":"河北少儿科教","ID":1638,"Icon":"","Desc":""},{"Name":"河北影视","ID":1639,"Icon":"","Desc":""},{"Name":"河北经济","ID":1641,"Icon":"","Desc":""},{"Name":"河北购物","ID":1643,"Icon":"","Desc":""},{"Name":"河北都市","ID":1644,"Icon":"","Desc":""},{"Name":"河南民生","ID":1654,"Icon":"","Desc":""},{"Name":"河南都市","ID":1658,"Icon":"","Desc":""},{"Name":"河南国际","ID":4173,"Icon":"","Desc":""},{"Name":"北京纪实","ID":4762,"Icon":"","Desc":""},{"Name":"温江电视台","ID":5786,"Icon":"","Desc":""}]},{"Group":{"ID":6,"Name":"影视","Icon":"film","Order":70},"Stations":[{"Name":"CCTV-6电影","ID":116,"Icon":"","Desc":""},{"Name":"CHC高清电影","ID":191,"Icon":"","Desc":""},{"Name":"上视电视剧","ID":773,"Icon":"","Desc":""},{"Name":"东方电影","ID":790,"Icon":"","Desc":""},{"Name":"乐视《甄嬛传》","ID":856,"Icon":"","Desc":""},{"Name":"乐视电影","ID":863,"Icon":"","Desc":""},{"Name":"乐视美剧","ID":871,"Icon":"","Desc":""},{"Name":"乐视韩剧","ID":875,"Icon":"","Desc":""},{"Name":"北京影视","ID":992,"Icon":"","Desc":""},{"Name":"山东影视","ID":1273,"Icon":"","Desc":""},{"Name":"广州影视","ID":1314,"Icon":"","Desc":""},{"Name":"杭州影视","ID":1519,"Icon":"","Desc":""},{"Name":"河北影视","ID":1639,"Icon":"","Desc":""},{"Name":"湖北影视","ID":1980,"Icon":"","Desc":""},{"Name":"湖南电视剧","ID":1998,"Icon":"","Desc":""},{"Name":"珠江电影","ID":2058,"Icon":"","Desc":""},{"Name":"莲花卫视","ID":2227,"Icon":"","Desc":""},{"Name":"重庆影视","ID":2353,"Icon":"","Desc":""},{"Name":"都市剧场","ID":2732,"Icon":"","Desc":""},{"Name":"星卫电影台","ID":2886,"Icon":"","Desc":""},{"Name":"武侠剧场","ID":3599,"Icon":"","Desc":""},{"Name":"彼洋电影","ID":3849,"Icon":"","Desc":""},{"Name":"乐视动作电影","ID":4172,"Icon":"","Desc":""},{"Name":"乐视-隋唐英雄","ID":4408,"Icon":"","Desc":""},{"Name":"乐视喜剧电影","ID":4564,"Icon":"","Desc":""},{"Name":"诸城影视","ID":4634,"Icon":"","Desc":""},{"Name":"内蒙古影视剧","ID":5865,"Icon":"","Desc":""},{"Name":"峨眉电影","ID":5954,"Icon":"","Desc":""},{"Name":"军旅剧场","ID":5959,"Icon":"","Desc":""},{"Name":"欢乐喜剧人","ID":6301,"Icon":"","Desc":""},{"Name":"乐视周星驰影视","ID":6329,"Icon":"","Desc":""},{"Name":"乐视成龙影视","ID":6330,"Icon":"","Desc":""},{"Name":"抗战剧场","ID":6331,"Icon":"","Desc":""},{"Name":"女性剧场","ID":6336,"Icon":"","Desc":""},{"Name":"热门美剧","ID":6337,"Icon":"","Desc":""},{"Name":"宫斗剧场","ID":6338,"Icon":"","Desc":""},{"Name":"鹿鼎记","ID":6339,"Icon":"","Desc":""},{"Name":"励志剧场","ID":6340,"Icon":"","Desc":""},{"Name":"情景喜剧剧场","ID":6341,"Icon":"","Desc":""},{"Name":"士兵突击","ID":6343,"Icon":"","Desc":""},{"Name":"西游记","ID":6344,"Icon":"","Desc":""},{"Name":"亮剑","ID":6345,"Icon":"","Desc":""},{"Name":"包青天","ID":6346,"Icon":"","Desc":""},{"Name":"跟播美剧","ID":6350,"Icon":"","Desc":""},{"Name":"经典美剧","ID":6351,"Icon":"","Desc":""},{"Name":"奇幻美剧","ID":6353,"Icon":"","Desc":""},{"Name":"科幻美剧","ID":6354,"Icon":"","Desc":""},{"Name":"年代剧场","ID":6357,"Icon":"","Desc":""},{"Name":"华语电影","ID":6358,"Icon":"","Desc":""},{"Name":"日韩电影","ID":6359,"Icon":"","Desc":""},{"Name":"虐恋美剧","ID":6360,"Icon":"","Desc":""},{"Name":"高冷美剧","ID":6361,"Icon":"","Desc":""},{"Name":"单元美剧","ID":6362,"Icon":"","Desc":""},{"Name":"热血动作美剧","ID":6363,"Icon":"","Desc":""},{"Name":"兄弟剧场","ID":6364,"Icon":"","Desc":""},{"Name":"姐妹淘剧场","ID":6366,"Icon":"","Desc":""},{"Name":"青春偶像美剧","ID":6367,"Icon":"","Desc":""},{"Name":"虎妈猫爸","ID":6372,"Icon":"","Desc":""},{"Name":"聊斋新编","ID":6374,"Icon":"","Desc":""},{"Name":"武媚娘传奇","ID":6378,"Icon":"","Desc":""},{"Name":"催泪剧场","ID":6486,"Icon":"","Desc":""},{"Name":"小众美剧","ID":6487,"Icon":"","Desc":""},{"Name":"还珠格格","ID":6488,"Icon":"","Desc":""},{"Name":"肥皂美剧","ID":6491,"Icon":"","Desc":""},{"Name":"CIBN电影导视","ID":6671,"Icon":"","Desc":""},{"Name":"香港电影","ID":6679,"Icon":"","Desc":""},{"Name":"电视剧联播","ID":6680,"Icon":"","Desc":""},{"Name":"神话剧场","ID":6681,"Icon":"","Desc":""},{"Name":"乐视-章子怡","ID":7086,"Icon":"","Desc":""},{"Name":"IPTV谍战剧场","ID":7097,"Icon":"","Desc":""},{"Name":"IPTV经典电影","ID":7098,"Icon":"","Desc":""},{"Name":"IPTV6+","ID":7106,"Icon":"","Desc":""},{"Name":"IPTV8+","ID":7108,"Icon":"","Desc":""},{"Name":"芈月传","ID":7295,"Icon":"","Desc":""},{"Name":"琅琊榜","ID":7300,"Icon":"","Desc":""},{"Name":"少年神探狄仁杰","ID":7315,"Icon":"","Desc":""},{"Name":"IPTV热播剧场","ID":7356,"Icon":"","Desc":""},{"Name":"CIBN精品影院","ID":7389,"Icon":"","Desc":""},{"Name":"寂寞空庭春欲晚","ID":7581,"Icon":"","Desc":""},{"Name":"女医明妃传","ID":7582,"Icon":"","Desc":""},{"Name":"太子妃升职记","ID":7585,"Icon":"","Desc":""},{"Name":"辽宁家庭影院1","ID":7733,"Icon":"","Desc":""},{"Name":"辽宁家庭影院3","ID":7734,"Icon":"","Desc":""},{"Name":"辽宁老电影","ID":7735,"Icon":"","Desc":""},{"Name":"辽宁家庭剧场1","ID":7736,"Icon":"","Desc":""},{"Name":"辽宁家庭剧场3","ID":7737,"Icon":"","Desc":""},{"Name":"辽宁家庭影院4","ID":7738,"Icon":"","Desc":""},{"Name":"辽宁刑侦剧场","ID":7739,"Icon":"","Desc":""},{"Name":"辽宁海外剧场","ID":7740,"Icon":"","Desc":""},{"Name":"辽宁卡通剧场","ID":7741,"Icon":"","Desc":""},{"Name":"CNTV古装剧场","ID":7748,"Icon":"","Desc":""},{"Name":"CNTV军旅剧场","ID":7750,"Icon":"","Desc":""},{"Name":"CNTV明星大片","ID":7752,"Icon":"","Desc":""},{"Name":"CNTV海外剧场","ID":7758,"Icon":"","Desc":""},{"Name":"CNTV惊悚悬疑","ID":7763,"Icon":"","Desc":""},{"Name":"港产经典","ID":8089,"Icon":"","Desc":""},{"Name":"港产电影","ID":8176,"Icon":"","Desc":""},{"Name":"港古装片","ID":8179,"Icon":"","Desc":""},{"Name":"港爱情片","ID":8180,"Icon":"","Desc":""},{"Name":"港喜剧片","ID":8181,"Icon":"","Desc":""},{"Name":"港动作片","ID":8182,"Icon":"","Desc":""},{"Name":"港鬼怪片","ID":8183,"Icon":"","Desc":""}]},{"Group":{"ID":7,"Name":"体育","Icon":"sports","Order":65},"Stations":[{"Name":"CCTV-5+体育赛事","ID":114,"Icon":"","Desc":""},{"Name":"CCTV-5体育","ID":115,"Icon":"","Desc":""},{"Name":"CCTV-风云足球","ID":157,"Icon":"","Desc":""},{"Name":"CCTV-高尔夫网球","ID":159,"Icon":"","Desc":""},{"Name":"上视五星体育","ID":765,"Icon":"","Desc":""},{"Name":"乐视体育","ID":859,"Icon":"","Desc":""},{"Name":"乐视网球","ID":870,"Icon":"","Desc":""},{"Name":"先锋乒羽","ID":947,"Icon":"","Desc":""},{"Name":"兰州体育","ID":961,"Icon":"","Desc":""},{"Name":"劲爆体育","ID":986,"Icon":"","Desc":""},{"Name":"北京体育","ID":987,"Icon":"","Desc":""},{"Name":"天元围棋","ID":1178,"Icon":"","Desc":""},{"Name":"广东体育","ID":1302,"Icon":"","Desc":""},{"Name":"延边新闻综合","ID":1331,"Icon":"","Desc":""},{"Name":"新疆汉语体育","ID":1440,"Icon":"http://v2.togic.com/upload/channels/1372233022372xinjiang.png","Desc":""},{"Name":"杭州体育","ID":1514,"Icon":"","Desc":""},{"Name":"杭州少儿","ID":1518,"Icon":"","Desc":""},{"Name":"欧洲足球","ID":1554,"Icon":"","Desc":""},{"Name":"江苏体育休闲","ID":1596,"Icon":"","Desc":""},{"Name":"河北都市","ID":1644,"Icon":"","Desc":""},{"Name":"甘肃卫视","ID":2070,"Icon":"","Desc":""},{"Name":"电子体育","ID":2079,"Icon":"","Desc":""},{"Name":"福建体育","ID":2124,"Icon":"","Desc":""},{"Name":"辽宁体育","ID":2292,"Icon":"","Desc":""},{"Name":"欧洲体育","ID":2919,"Icon":"","Desc":""},{"Name":"CCTV-央视台球","ID":3211,"Icon":"","Desc":""},{"Name":"乐视足球","ID":3348,"Icon":"","Desc":""},{"Name":"德甲","ID":3405,"Icon":"","Desc":""},{"Name":"乐视篮球","ID":3705,"Icon":"","Desc":""},{"Name":"乐视体育2台","ID":5922,"Icon":"","Desc":""},{"Name":"兰州综艺体育","ID":6735,"Icon":"","Desc":""},{"Name":"IPTV5+","ID":7107,"Icon":"","Desc":""},{"Name":"乐视体育+","ID":7303,"Icon":"","Desc":""},{"Name":"乐视中超","ID":7580,"Icon":"","Desc":""},{"Name":"乐视中超1","ID":8343,"Icon":"","Desc":""},{"Name":"乐视中超2","ID":8344,"Icon":"","Desc":""}]},{"Group":{"ID":8,"Name":"纪录","Icon":"document","Order":60},"Stations":[{"Name":"CCTV-9纪录","ID":120,"Icon":"","Desc":""},{"Name":"上视纪实","ID":774,"Icon":"","Desc":""},{"Name":"东方纪实","ID":791,"Icon":"","Desc":""},{"Name":"乐视纪录片","ID":868,"Icon":"","Desc":""},{"Name":"社会纪实","ID":2117,"Icon":"","Desc":""},{"Name":"金鹰纪实","ID":2372,"Icon":"","Desc":""},{"Name":"乐视《舌尖上的中国》","ID":4570,"Icon":"","Desc":""},{"Name":"北京纪实","ID":4762,"Icon":"","Desc":""},{"Name":"CIBN纪录片","ID":6666,"Icon":"","Desc":""},{"Name":"韩国音乐","ID":7019,"Icon":"","Desc":""}]},{"Group":{"ID":9,"Name":"财经","Icon":"caijing","Order":55},"Stations":[{"Name":"CCTV-2财经","ID":102,"Icon":"","Desc":""},{"Name":"CCTV-证券资讯","ID":153,"Icon":"","Desc":""},{"Name":"东方财经","ID":792,"Icon":"","Desc":""},{"Name":"北京财经","ID":997,"Icon":"","Desc":""},{"Name":"南方经济科教","ID":1028,"Icon":"","Desc":""},{"Name":"广东会展","ID":1301,"Icon":"","Desc":""},{"Name":"广东房产","ID":1307,"Icon":"","Desc":""},{"Name":"广州经济","ID":1317,"Icon":"","Desc":""},{"Name":"浙江经视","ID":1920,"Icon":"","Desc":""},{"Name":"湖北经视","ID":1987,"Icon":"","Desc":""},{"Name":"湖南经视","ID":1999,"Icon":"","Desc":""},{"Name":"第一财经","ID":2140,"Icon":"","Desc":""},{"Name":"财富天下","ID":2265,"Icon":"","Desc":""},{"Name":"辽宁经济","ID":2300,"Icon":"","Desc":""},{"Name":"武汉经济","ID":6238,"Icon":"","Desc":""}]},{"Group":{"ID":10,"Name":"资讯","Icon":"news","Order":50},"Stations":[{"Name":"CCTV-13新闻","ID":96,"Icon":"","Desc":""},{"Name":"CCTV-9纪录","ID":120,"Icon":"","Desc":""},{"Name":"上视新闻综合","ID":770,"Icon":"http://tv.togic.com:8080/ShowTimeService/images/346.png","Desc":""},{"Name":"东方纪实","ID":791,"Icon":"","Desc":""},{"Name":"东森新闻","ID":798,"Icon":"","Desc":""},{"Name":"凤凰卫视资讯台","ID":979,"Icon":"","Desc":""},{"Name":"北京新闻","ID":994,"Icon":"","Desc":""},{"Name":"广东新闻","ID":1308,"Icon":"","Desc":""},{"Name":"新华中文","ID":1416,"Icon":"","Desc":""},{"Name":"天空新闻","ID":4676,"Icon":"","Desc":""}]},{"Group":{"ID":11,"Name":"儿童","Icon":"child","Order":45},"Stations":[{"Name":"CCTV-14少儿","ID":99,"Icon":"","Desc":""},{"Name":"乐视亲子","ID":858,"Icon":"","Desc":""},{"Name":"优漫卡通","ID":933,"Icon":"","Desc":""},{"Name":"动漫秀场","ID":983,"Icon":"","Desc":""},{"Name":"北京少儿","ID":991,"Icon":"","Desc":""},{"Name":"南京少儿","ID":1012,"Icon":"","Desc":""},{"Name":"卡酷少儿","ID":1045,"Icon":"","Desc":""},{"Name":"嘉佳卡通","ID":1112,"Icon":"","Desc":""},{"Name":"山东少儿","ID":1272,"Icon":"","Desc":""},{"Name":"广州少儿","ID":1313,"Icon":"","Desc":""},{"Name":"新动漫","ID":1415,"Icon":"","Desc":""},{"Name":"杭州少儿","ID":1518,"Icon":"","Desc":""},{"Name":"河北少儿科教","ID":1638,"Icon":"","Desc":""},{"Name":"济南少儿","ID":1899,"Icon":"","Desc":""},{"Name":"深圳少儿","ID":1953,"Icon":"","Desc":""},{"Name":"炫动卡通","ID":2033,"Icon":"","Desc":""},{"Name":"金鹰卡通","ID":2371,"Icon":"","Desc":""},{"Name":"黑龙江少儿","ID":2496,"Icon":"","Desc":""},{"Name":"Cartoon Network","ID":2928,"Icon":"","Desc":""},{"Name":"乐视儿歌","ID":4235,"Icon":"","Desc":""},{"Name":"CCTV-新科动漫","ID":4867,"Icon":"","Desc":""},{"Name":"广东少儿","ID":5290,"Icon":"","Desc":""},{"Name":"内蒙古少儿","ID":5867,"Icon":"","Desc":""},{"Name":"倒霉熊","ID":6272,"Icon":"","Desc":""},{"Name":"熊出没","ID":6315,"Icon":"","Desc":""},{"Name":"猫和老鼠","ID":6316,"Icon":"","Desc":""},{"Name":"名侦探柯南","ID":6321,"Icon":"","Desc":""},{"Name":"大头儿子","ID":6483,"Icon":"","Desc":""},{"Name":"蜡笔小新","ID":6484,"Icon":"","Desc":""},{"Name":"IPTV少儿动画","ID":7100,"Icon":"","Desc":""},{"Name":"双语动画","ID":7590,"Icon":"","Desc":""},{"Name":"CNTV动画王国","ID":7760,"Icon":"","Desc":""},{"Name":"靖天卡通","ID":7938,"Icon":"","Desc":""},{"Name":"HOT少儿动漫","ID":7969,"Icon":"","Desc":""}]},{"Group":{"ID":12,"Name":"农业","Icon":"nongye","Order":40},"Stations":[{"Name":"CCTV-7军事与农业","ID":117,"Icon":"","Desc":""},{"Name":"农民卫视","ID":972,"Icon":"","Desc":""},{"Name":"山东农科","ID":1269,"Icon":"","Desc":""},{"Name":"重庆公共农村","ID":2348,"Icon":"","Desc":""},{"Name":"浙江公共新农村","ID":3801,"Icon":"","Desc":""},{"Name":"绵阳新农村信息","ID":4965,"Icon":"","Desc":""},{"Name":"农业致富","ID":5966,"Icon":"","Desc":""},{"Name":"巨鹿农业生活","ID":6571,"Icon":"","Desc":""},{"Name":"诸城党建农科","ID":6794,"Icon":"","Desc":""}]},{"Group":{"ID":13,"Name":"游戏","Icon":"game","Order":35},"Stations":[{"Name":"GTV游戏竞技","ID":299,"Icon":"http://v2.togic.com/upload/channels/1378986652325tentent_games.png","Desc":""},{"Name":"游戏竞技","ID":1971,"Icon":"","Desc":""},{"Name":"游戏风云","ID":1972,"Icon":"","Desc":""},{"Name":"电子竞技","ID":2080,"Icon":"","Desc":""},{"Name":"乐视游戏","ID":6328,"Icon":"","Desc":""}]},{"Group":{"ID":14,"Name":"娱乐","Icon":"yule","Order":30},"Stations":[{"Name":"CCTV-11戏曲","ID":94,"Icon":"","Desc":""},{"Name":"CCTV-3综艺","ID":103,"Icon":"","Desc":""},{"Name":"CCTV-风云音乐","ID":158,"Icon":"","Desc":""},{"Name":"东方娱乐","ID":786,"Icon":"","Desc":""},{"Name":"乐视音乐","ID":876,"Icon":"","Desc":""},{"Name":"北京文艺","ID":993,"Icon":"","Desc":""},{"Name":"南京娱乐","ID":1011,"Icon":"","Desc":""},{"Name":"德州娱乐","ID":1351,"Icon":"","Desc":""},{"Name":"湖南娱乐","ID":1997,"Icon":"","Desc":""},{"Name":"石家庄娱乐","ID":2109,"Icon":"","Desc":""},{"Name":"郑州影视戏曲","ID":2331,"Icon":"","Desc":""},{"Name":"乐视娱乐","ID":3700,"Icon":"","Desc":""},{"Name":"乐视演唱会","ID":3701,"Icon":"","Desc":""},{"Name":"乐视综艺","ID":3864,"Icon":"","Desc":""},{"Name":"乐视MV","ID":3874,"Icon":"","Desc":""},{"Name":"乐视戏曲","ID":4233,"Icon":"","Desc":""},{"Name":"乐视古典音乐","ID":4396,"Icon":"","Desc":""},{"Name":"诸城娱乐","ID":4635,"Icon":"","Desc":""},{"Name":"邢台公共娱乐","ID":5183,"Icon":"","Desc":""},{"Name":"广东综艺","ID":5288,"Icon":"","Desc":""},{"Name":"郭德纲","ID":6304,"Icon":"","Desc":""},{"Name":"IPTV相声小品","ID":7096,"Icon":"","Desc":""},{"Name":"IPTV魅力时尚","ID":7099,"Icon":"","Desc":""},{"Name":"IPTV3+","ID":7105,"Icon":"","Desc":""},{"Name":"最强大脑","ID":7298,"Icon":"","Desc":""},{"Name":"CNTV金牌综艺","ID":7762,"Icon":"","Desc":""},{"Name":"熊猫相声专场","ID":8413,"Icon":"","Desc":""},{"Name":"郭德纲单口相声","ID":8414,"Icon":"","Desc":""}]},{"Group":{"ID":15,"Name":"教育","Icon":"education","Order":25},"Stations":[{"Name":"CETV-中国教育1","ID":173,"Icon":"","Desc":""},{"Name":"上视教育","ID":769,"Icon":"","Desc":""},{"Name":"合肥教育法制","ID":1078,"Icon":"","Desc":""},{"Name":"山东教育","ID":1274,"Icon":"","Desc":""},{"Name":"广东现代教育","ID":1309,"Icon":"http://v2.togic.com/upload/channels/1380537884944323.png","Desc":""},{"Name":"枣庄生活教育","ID":1533,"Icon":"","Desc":""},{"Name":"浙江教育科技","ID":1915,"Icon":"","Desc":""},{"Name":"湖北教育","ID":1981,"Icon":"","Desc":""},{"Name":"辽宁教育青少","ID":2298,"Icon":"","Desc":""},{"Name":"幼儿教育","ID":3254,"Icon":"","Desc":""},{"Name":"上海教育","ID":3591,"Icon":"","Desc":""},{"Name":"佛陀教育直播","ID":3778,"Icon":"","Desc":""},{"Name":"广东经济科教","ID":5287,"Icon":"","Desc":""},{"Name":"吉林教育","ID":5931,"Icon":"","Desc":""},{"Name":"郑州教育","ID":6055,"Icon":"","Desc":""},{"Name":"CIBN教育","ID":7391,"Icon":"","Desc":""}]},{"Group":{"ID":17,"Name":"港澳台","Icon":"gangaotai","Order":20},"Stations":[{"Name":"三立台湾","ID":756,"Icon":"","Desc":""},{"Name":"三立都会","ID":759,"Icon":"","Desc":""},{"Name":"东森新闻","ID":798,"Icon":"","Desc":""},{"Name":"东森综合","ID":801,"Icon":"","Desc":""},{"Name":"人间卫视","ID":928,"Icon":"","Desc":""},{"Name":"八大戏剧","ID":954,"Icon":"","Desc":""},{"Name":"八大第一台","ID":955,"Icon":"","Desc":""},{"Name":"凤凰卫视中文台","ID":975,"Icon":"","Desc":""},{"Name":"凤凰卫视资讯台","ID":979,"Icon":"","Desc":""},{"Name":"凤凰卫视香港台","ID":980,"Icon":"","Desc":""},{"Name":"宏观卫视","ID":1244,"Icon":"","Desc":""},{"Name":"汉天卫视","ID":1591,"Icon":"","Desc":""},{"Name":"澳亚卫视","ID":2015,"Icon":"","Desc":""},{"Name":"纬来戏剧","ID":2146,"Icon":"","Desc":""},{"Name":"莲花卫视","ID":2227,"Icon":"","Desc":""},{"Name":"非凡新闻","ID":2435,"Icon":"","Desc":""},{"Name":"香港卫视","ID":2471,"Icon":"","Desc":""},{"Name":"年代剧场","ID":6357,"Icon":"","Desc":""},{"Name":"冠军电视台","ID":6641,"Icon":"","Desc":""},{"Name":"靖天资讯","ID":7774,"Icon":"","Desc":""},{"Name":"靖天卡通","ID":7938,"Icon":"","Desc":""},{"Name":"靖天育乐","ID":7939,"Icon":"","Desc":""},{"Name":"靖洋卡通","ID":7941,"Icon":"","Desc":""},{"Name":"靖天日本","ID":7949,"Icon":"","Desc":""},{"Name":"靖天国际","ID":7950,"Icon":"","Desc":""},{"Name":"奥视惊艳","ID":8096,"Icon":"","Desc":""},{"Name":"奥视香蕉","ID":8097,"Icon":"","Desc":""}]},{"Group":{"ID":18,"Name":"宗教","Icon":"zongjiao","Order":15},"Stations":[{"Name":"唯心卫视","ID":1106,"Icon":"","Desc":""},{"Name":"佛陀教育直播","ID":3778,"Icon":"","Desc":""}]},{"Group":{"ID":19,"Name":"海外","Icon":"haiwai","Order":10},"Stations":[{"Name":"HBO","ID":302,"Icon":"","Desc":""},{"Name":"法国时尚","ID":1665,"Icon":"","Desc":""},{"Name":"美国中文电视","ID":2180,"Icon":"","Desc":""},{"Name":"美国之音","ID":2181,"Icon":"","Desc":""},{"Name":"韩国阿里郎","ID":2605,"Icon":"","Desc":""},{"Name":"英国BBC科学","ID":6990,"Icon":"","Desc":""}]}]
     * ErrorMsg : null
     */

    private boolean Result;
    private String ErrorMsg;
    private List<RetObjectBean> RetObject;

    public boolean isResult() {
        return Result;
    }

    public void setResult(boolean Result) {
        this.Result = Result;
    }

    public Object getErrorMsg() {
        return ErrorMsg;
    }

    public void setErrorMsg(String ErrorMsg) {
        this.ErrorMsg = ErrorMsg;
    }

    public List<RetObjectBean> getRetObject() {
        return RetObject;
    }

    public void setRetObject(List<RetObjectBean> RetObject) {
        this.RetObject = RetObject;
    }

    public static class RetObjectBean implements Parcelable {
        /**
         * Group : {"ID":16,"Name":"收藏","Icon":"favgroup","Order":1000}
         * Stations : []
         */

        private GroupBean Group;
        private List<Station> Stations;

        public GroupBean getGroup() {
            return Group;
        }

        public void setGroup(GroupBean Group) {
            this.Group = Group;
        }

        public List<Station> getStations() {
            return Stations;
        }

        public void setStations(List<Station> Stations) {
            this.Stations = Stations;
        }

        public static class GroupBean implements Parcelable {
            /**
             * ID : 16
             * Name : 收藏
             * Icon : favgroup
             * Order : 1000
             */

            private int ID;
            private String Name;
            private String Icon;
            private int Order;

            public int getID() {
                return ID;
            }

            public void setID(int ID) {
                this.ID = ID;
            }

            public String getName() {
                return Name;
            }

            public void setName(String Name) {
                this.Name = Name;
            }

            public String getIcon() {
                return Icon;
            }

            public void setIcon(String Icon) {
                this.Icon = Icon;
            }

            public int getOrder() {
                return Order;
            }

            public void setOrder(int Order) {
                this.Order = Order;
            }

            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel dest, int flags) {
                dest.writeInt(this.ID);
                dest.writeString(this.Name);
                dest.writeString(this.Icon);
                dest.writeInt(this.Order);
            }

            public GroupBean() {
            }

            protected GroupBean(Parcel in) {
                this.ID = in.readInt();
                this.Name = in.readString();
                this.Icon = in.readString();
                this.Order = in.readInt();
            }

            public static final Creator<GroupBean> CREATOR = new Creator<GroupBean>() {
                @Override
                public GroupBean createFromParcel(Parcel source) {
                    return new GroupBean(source);
                }

                @Override
                public GroupBean[] newArray(int size) {
                    return new GroupBean[size];
                }
            };
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeParcelable(this.Group, flags);
            dest.writeList(this.Stations);
        }

        public RetObjectBean() {
        }

        protected RetObjectBean(Parcel in) {
            this.Group = in.readParcelable(GroupBean.class.getClassLoader());
            this.Stations = new ArrayList<Station>();
            in.readList(this.Stations, Station.class.getClassLoader());
        }

        public static final Creator<RetObjectBean> CREATOR = new Creator<RetObjectBean>() {
            @Override
            public RetObjectBean createFromParcel(Parcel source) {
                return new RetObjectBean(source);
            }

            @Override
            public RetObjectBean[] newArray(int size) {
                return new RetObjectBean[size];
            }
        };
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeByte(this.Result ? (byte) 1 : (byte) 0);
        dest.writeList(this.RetObject);
    }

    public static class Station implements Parcelable {
        public String getDesc() {
            return Desc;
        }

        public void setDesc(String desc) {
            Desc = desc;
        }

        public String getName() {
            return Name;
        }

        public void setName(String name) {
            Name = name;
        }

        public String getIcon() {
            return Icon;
        }

        public void setIcon(String icon) {
            Icon = icon;
        }

        public int getID() {
            return ID;
        }

        public void setID(int ID) {
            this.ID = ID;
        }

        private String Desc;
        private int ID;
        private String Icon;
        private String Name;

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.Desc);
            dest.writeInt(this.ID);
            dest.writeString(this.Icon);
            dest.writeString(this.Name);
        }

        public Station() {
        }

        protected Station(Parcel in) {
            this.Desc = in.readString();
            this.ID = in.readInt();
            this.Icon = in.readString();
            this.Name = in.readString();
        }

        public static final Creator<Station> CREATOR = new Creator<Station>() {
            @Override
            public Station createFromParcel(Parcel source) {
                return new Station(source);
            }

            @Override
            public Station[] newArray(int size) {
                return new Station[size];
            }
        };
    }

    public LiveBean() {
    }

    protected LiveBean(Parcel in) {
        this.Result = in.readByte() != 0;
        this.RetObject = new ArrayList<RetObjectBean>();
        in.readList(this.RetObject, RetObjectBean.class.getClassLoader());
    }

    public static final Parcelable.Creator<LiveBean> CREATOR = new Parcelable.Creator<LiveBean>() {
        @Override
        public LiveBean createFromParcel(Parcel source) {
            return new LiveBean(source);
        }

        @Override
        public LiveBean[] newArray(int size) {
            return new LiveBean[size];
        }
    };
}
