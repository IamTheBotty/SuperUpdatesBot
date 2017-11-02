package utils;

import com.google.common.base.Objects;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pavel on 01.11.17.
 */
public class WhiteList {

    private List<String> whiteList = new ArrayList<String>();

    public WhiteList(){
        //BotTesting
        whiteList.add("-312418133");
        //Bazaar Jacuzzi
        whiteList.add("-239504069");
        //Crazy team drinking
        whiteList.add("-201458489");
        //Yopta.space
        whiteList.add("-1001090868559");
        //FrontTalks
        whiteList.add("-126117957");
    }

    public void add(String s) {
        if (s != null && Objects.equal(s, "") && !whiteList.contains(s)) {
            whiteList.add(s);
        }
    }

    public List<String> getList() {
        return whiteList;
    }

}
