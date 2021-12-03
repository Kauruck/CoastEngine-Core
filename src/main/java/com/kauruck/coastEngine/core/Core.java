package com.kauruck.coastEngine.core;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.kauruck.coastEngine.core.resources.JsonHandler;
import com.kauruck.coastEngine.core.resources.ResourceLoader;
import com.kauruck.coastEngine.core.resources.TextHandler;

public class Core {

    public static void init(){
        ResourceLoader.registerResourceHandler(new TextHandler(), String.class);
        ResourceLoader.registerResourceHandler(new JsonHandler(), JsonElement.class);
    }
}
