package ch.supsi.localchat.frontend;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class ReadJSON {
    public static JSONArray parseArray(String json) {
        JSONArray input = new JSONArray(json);
        return input;

    }

    public static JSONObject parseObject(String json) {
        JSONObject input=new JSONObject(json);
        return input;
    }
}
