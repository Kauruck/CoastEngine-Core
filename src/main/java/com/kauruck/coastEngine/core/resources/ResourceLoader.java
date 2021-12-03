package com.kauruck.coastEngine.core.resources;

import com.kauruck.coastEngine.core.exception.NoHandlerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.*;

public class ResourceLoader {

    public static final Logger RESOURCES_LOGGER = LoggerFactory.getLogger("Resources Manger");

    private static final Map<Class<?>, ResourceHandler> resourceHandlers = new HashMap<>();

    public static void registerResourceHandler(ResourceHandler handler, Class<?> clazz){
        if(resourceHandlers.containsKey(clazz))
            throw new IllegalArgumentException("There is a handler for " + clazz.getName() + "already");

        resourceHandlers.put(clazz, handler);
    }

    public static <T> T loadResources(ResourceLocation resourceLocation, Class<T> classToLoad) throws IOException, NoHandlerException {
        String path = resourceLocation.getAsPath();
        String dir = getDirectory(path);
        List<String> availableFiles = getResourceFiles(dir);
        if(availableFiles.size() == 0)
            throw new FileNotFoundException("Could not load resourceLocation: " + resourceLocation);

        if(!resourceHandlers.containsKey(classToLoad))
            throw new NoHandlerException(classToLoad.getName());

        ResourceHandler<T> handler = resourceHandlers.get(classToLoad);

        String[] extensions = handler.getValidFileExtensions();

        String fileName = getFullFileName(availableFiles, extensions);
        if(fileName == null)
            throw new FileNotFoundException("There is no file with the extension(s): " + Arrays.toString(extensions) + " in " + resourceLocation);

        if(handler.useStream()){
            return handler.loadFromStream(getResourceAsStream(dir + fileName), fileName);
        }else{
            return handler.loadFromString(loadAsString(dir + fileName), fileName);
        }

    }

    private static String loadAsString(String path) throws IOException{
        StringBuilder builder = new StringBuilder();
        try (
                InputStream in = getResourceAsStream(path);
                BufferedReader br = new BufferedReader(new InputStreamReader(in))) {
            String line;

            while ((line = br.readLine()) != null) {
                builder.append(line)
                        .append("\n");
            }
        }
        return builder.toString();
    }

    private static String getFullFileName(List<String> fileNames, String[] validExtension){
        return fileNames.stream()
                .filter((s) -> filterFileExtension(s, validExtension))
                .findAny()
                .orElse(null);
    }

    private static boolean filterFileExtension(String fileName, String[] validExtensions){
        String extension = getFileExtension(fileName);
        return Arrays.stream(validExtensions).allMatch(s -> s.equalsIgnoreCase(extension));
    }
    
    private static String getFileExtension(String fileName){
        int i = fileName.lastIndexOf('.');
        return fileName.substring(i+1);
    }


    private static String getDirectory(String path){
        int lastSlash = path.lastIndexOf('/');
        return path.substring(0, lastSlash) + '/';
    }

    private static String getFileName(String path){
        int lastSlash = path.lastIndexOf('/');
        return path.substring(lastSlash + 1);
    }

    private static List<String> getResourceFiles(String path) throws IOException {
        List<String> filenames = new ArrayList<>();

        try (
                InputStream in = getResourceAsStream(path);
                BufferedReader br = new BufferedReader(new InputStreamReader(in))) {
            String resource;

            while ((resource = br.readLine()) != null) {
                filenames.add(resource);
            }
        }

        return filenames;
    }

    private static InputStream getResourceAsStream(String resource) {
        final InputStream in
                = getContextClassLoader().getResourceAsStream(resource);

        return in == null ? ResourceLoader.class.getResourceAsStream(resource) : in;
    }

    private static ClassLoader getContextClassLoader() {
        return Thread.currentThread().getContextClassLoader();
    }

}
