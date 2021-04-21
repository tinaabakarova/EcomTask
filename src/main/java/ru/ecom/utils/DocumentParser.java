package ru.ecom.utils;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.thoughtworks.xstream.XStream;

import java.util.Base64;

public class DocumentParser<T> {
    private Class clazz;

    public DocumentParser(Class clazz) {
        this.clazz = clazz;
    }

    public T getDtoFromRequest(StringBuilder stringBuilder, String tag){
        XStream xstream = new XStream();
        JsonObject jsonObject = JsonParser.parseString(stringBuilder.toString()).getAsJsonObject();
        xstream.processAnnotations(clazz);
        byte[] decodedXml = Base64.getDecoder().decode(jsonObject.get(tag).getAsString());
        return (T) xstream.fromXML(new String(decodedXml));
    }

    public String getValueFromRequest(StringBuilder stringBuilder, String tag){
        JsonObject jsonObject = JsonParser.parseString(stringBuilder.toString()).getAsJsonObject();
        return jsonObject.get(tag).getAsString();
    }

    public static String getJsonStringFromObject(Object o){
        return new Gson().toJson(o);
    }

    public static JsonObject getJsonElementFromObject(Object o){
        return new Gson().toJsonTree(o).getAsJsonObject();
    }
}
