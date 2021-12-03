package com.kauruck.coastEngine.core.resources;

public class ResourceLocation {

    private final String namespace;
    private final String path;

    public ResourceLocation(String namespace, String path) {
        this.namespace = namespace;
        this.path = path;
    }

    public ResourceLocation(String resourceLocation){
        String[] parts = resourceLocation.split(":");
        if(parts.length != 2)
            throw new IllegalArgumentException("Missformatted resourceLocation. Should be <namespace>:<path>");
        namespace = parts[0];
        path = parts[1];
    }

    @Override
    public String toString() {
        return namespace + ":" + path;
    }

    public String getAsPath(){
        return namespace + "/" + path;
    }

    public String getPath() {
        return path;
    }

    public String getNamespace() {
        return namespace;
    }
}
