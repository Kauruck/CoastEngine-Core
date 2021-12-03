package com.kauruck.coastEngine.core.resources;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class JsonHandler extends ResourceHandler<JsonObject> {


    @Override
    public String[] getValidFileExtensions() {
        return new String[]{"json"};
    }

    @Override
    public JsonObject loadFromString(String s, String fileName) {
        JsonElement element = JsonParser.parseString(s);
        if(!element.isJsonObject()) {
            ResourceLoader.RESOURCES_LOGGER.error("There is missformated json file: " + fileName);
            return null;
        }
        return element.getAsJsonObject();
    }
}
