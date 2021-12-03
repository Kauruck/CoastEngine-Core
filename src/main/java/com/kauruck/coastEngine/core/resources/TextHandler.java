package com.kauruck.coastEngine.core.resources;

public class TextHandler extends ResourceHandler<String>{
    @Override
    public String[] getValidFileExtensions() {
        return new String[]{"txt"};
    }

    @Override
    public String loadFromString(String s, ResourceLocation resourceLocation) {
        return s;
    }
}
