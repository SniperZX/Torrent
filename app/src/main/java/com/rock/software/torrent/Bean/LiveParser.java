package com.rock.software.torrent.Bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhaohongru on 2017-02-08.
 * PackageName: com.rock.software.torrent.Bean.LiveParser
 * Description：
 */
public class LiveParser implements Parcelable {


    /**
     * Result : true
     * RetObject : [{"ID":52116,"Url":"fukxbiuuq;0071/331/2:7/2650mjwfimt3.dod/xbtv/do0dduw220{/n4v9t","IsHD":false},{"ID":48627,"Url":"elrpiuuq;00236/99/:3/277;411120QMUW099999:6703350433233878302/n4v9e","IsHD":false},{"ID":44409,"Url":"egresunq;0069/311/242/3;2:460mjwfuw0dduw22p","IsHD":false},{"ID":51669,"Url":"gismlniuuq;00329/355/258/229;749:0xuwmjwf0njhv3071:128232`5u","IsHD":false},{"ID":51308,"Url":"ghceuliuuq;00334/86/4/93;91920mjwf0mjwf50DDUW22031110DDUW22.3111.opef6/n4v9@gnu>y375`3111l`nqfhut'bvuiUzqf>3'opef>6'uzqf>mjwfc","IsHD":false},{"ID":50671,"Url":"gmphcciuuq;00294/318/366/2:1;910mjwf0qsphsbn0mjwf0dduw22024111110nog/n4v9r","IsHD":false},{"ID":52115,"Url":"dgwiuuq;00xxx/bodipsfejowfstjpo/dpn0uw0et0xbtv21/qiq@je>dduw22z","IsHD":false},{"ID":45166,"Url":"fekcviuuq;00232/362/5:/3150imt0m{v.dduw22/n4v9n","IsHD":false},{"ID":53373,"Url":"dzoiuuq;00223/364/49/73;910mjwfimt3.dod/xbtv/do0dduw220{/n4v9c","IsHD":false},{"ID":53676,"Url":"fnuwciuuq;00dduw2/wujnf/douw/eojpo/dpn;9111;91110mjwf0op0347`0tfh10joefy/n4v9iuuq;00dduw2/wujnf/douw/eojpo/dpn;9111;91110mjwf0op034:`0tfh10joefy/n4v9z","IsHD":false},{"ID":48371,"Url":"gryqnbiuuq;00294/318/35:/80QMUW040335043323366630joefy/n4v9p","IsHD":false},{"ID":48139,"Url":"grqyqdiuuq;006:/57/29/244;92250mouwdi01211111111111111111111111111119:@Gtw`puzqf>2'GwTfje>'Gtw`gjmfuzqf>'Gtw`duzqf>'Gtw`dje>'Gtw`dibo`imt`tf`jey>'Gtw`sbuf`je>'Gtw`UCu>'Gtw`TijguFobcmf>'Gtw`TijguUtq>'Gtw`TW`QBSBN2>'Qspwjefs`je>'Qdpoufou`je>y","IsHD":false},{"ID":52117,"Url":"gcvmykiuuq;00xxx/sbejp477/dpn0uw0jnbhft0douw/btq@vsm>dduw2l","IsHD":true},{"ID":49308,"Url":"gmpbvwiuuq;00htmctfsw/juw/dnwjefp/do0{/n4v9@bvuiDpef>1822151:433258463786'tucJe>114912GG112492112624CD31CB7F59E7'Dpoufouje>9:9473:927251:5664:'npt>kckii{tutm'mjwfnpef>2'diboofm.je>xbtvtzub","IsHD":false},{"ID":52992,"Url":"ezvliuuq;00ll/mvplf/ofu/do0dr0ebuw/qiq@je>131b","IsHD":false},{"ID":51227,"Url":"dbkiuuq;00222/4:/337/214;922302311111121120xmet;91910ztufo.cvtjoftt0mjwf0dduw.220/n4v9h","IsHD":false},{"ID":45131,"Url":"enloiuuq;00232/362/5:/3150imt0dduw22/n4v9p","IsHD":false},{"ID":50870,"Url":"ffonxiuuq;00291/211/2/3610deo0jquw0Uwpe01120di111111:1::11111122370joefy/n4v9q","IsHD":false},{"ID":50871,"Url":"ensjiuuq;00291/211/2/3610deo0jquw0Uwpe01120di111111:1::11111122380joefy/n4v9x","IsHD":false},{"ID":51159,"Url":"gozfkpiuuq;00ctu/npcjmf/mjwf/cftuwdeo/dpn/do0mjwf0qsphsbn0mjwf::20xfjyjodduw220mjwf/n4v9@tf>xfjyjo'du>2'`dq>2'`gl>s","IsHD":false},{"ID":49169,"Url":"dppiuuq;00333/295/68/2460mjwf/bjtiboh/dumdeo/dpn011111221351127`20fodpefs020qmbzmjtu/n4v9s","IsHD":false},{"ID":51589,"Url":"hoqbyhuiuuq;00333/295/68/2460mjwf/bjtiboh/dumdeo/dpn011111221351127`20fodpefs010qmbzmjtu/n4v9p","IsHD":false},{"ID":51737,"Url":"howwhhbiuuq;00333/2:2/35/6;772103030di111111:1::11111122380joefy/n4v9@jtqdpef>4n","IsHD":false}]
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

    public static class RetObjectBean {
        /**
         * ID : 52116
         * Url : fukxbiuuq;0071/331/2:7/2650mjwfimt3.dod/xbtv/do0dduw220{/n4v9t
         * IsHD : false
         */

        private int ID;
        private String Url;
        private boolean IsHD;

        public int getID() {
            return ID;
        }

        public void setID(int ID) {
            this.ID = ID;
        }

        public String getUrl() {
            return Url;
        }

        public void setUrl(String Url) {
            this.Url = Url;
        }

        public boolean isIsHD() {
            return IsHD;
        }

        public void setIsHD(boolean IsHD) {
            this.IsHD = IsHD;
        }
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

    public LiveParser() {
    }

    protected LiveParser(Parcel in) {
        this.Result = in.readByte() != 0;
        this.ErrorMsg = in.readParcelable(Object.class.getClassLoader());
        this.RetObject = new ArrayList<RetObjectBean>();
        in.readList(this.RetObject, RetObjectBean.class.getClassLoader());
    }

    public static final Parcelable.Creator<LiveParser> CREATOR = new Parcelable.Creator<LiveParser>() {
        @Override
        public LiveParser createFromParcel(Parcel source) {
            return new LiveParser(source);
        }

        @Override
        public LiveParser[] newArray(int size) {
            return new LiveParser[size];
        }
    };
}
