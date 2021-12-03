package com.kauruck.coastEngine.core.resources;

import java.io.InputStream;
import java.util.stream.Stream;

import static com.kauruck.coastEngine.core.resources.ResourceLoader.RESOURCES_LOGGER;

public abstract class ResourceHandler<T> {

    public abstract String[] getValidFileExtensions();

    public T loadFromString(String s, String fileName){
        RESOURCES_LOGGER.warn("There is no string parser defined for: " + this.getClass().getName());
        return null;
    }

    public T loadFromStream(InputStream s, String fileName){
        RESOURCES_LOGGER.warn("There is no stream parser defined for: " + this.getClass().getName());
        return null;
    }

    public boolean useStream(){
        return false;
    }
}
