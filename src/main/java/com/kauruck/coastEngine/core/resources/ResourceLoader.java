package com.kauruck.coastEngine.core.resources;

import com.kauruck.coastEngine.core.exception.NoHandlerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.*;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

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
        List<String> availableFiles = loadDir(dir);
        if(availableFiles.size() == 0)
            throw new FileNotFoundException("Could not load resourceLocation: " + resourceLocation);

        if(!resourceHandlers.containsKey(classToLoad))
            throw new NoHandlerException(classToLoad.getName());

        ResourceHandler<T> handler = resourceHandlers.get(classToLoad);

        String[] extensions = handler.getValidFileExtensions();

        List<String> validFiles = getValidFiles(availableFiles, extensions);
        String fileName = validFiles.stream()
                .filter((s) -> s.contains(resourceLocation.getAsPath()))
                .findAny()
                .orElse(null);

        if(fileName == null)
            throw new FileNotFoundException("There is no file with the extension(s): " + Arrays.toString(extensions) + " in " + resourceLocation);

        if(handler.useStream()){
            return handler.loadFromStream(getResourceAsStream(fileName), resourceLocation);
        }else{
            return handler.loadFromString(loadAsString(fileName), resourceLocation);
        }

    }

    public static List<String> loadDir(String path) throws IOException {
        List<String> out = new ArrayList<>();
        final File jarFile = new File(ResourceLoader.class.getProtectionDomain().getCodeSource().getLocation().getPath());

        if(jarFile.isFile()) {  // Run with JAR file
            final JarFile jar = new JarFile(jarFile);
            Enumeration<JarEntry> entries = jar.entries(); //gives ALL entries in jar
            while(entries.hasMoreElements()) {
                final String name = entries.nextElement().getName();
                if (name.startsWith(path) && name.lastIndexOf("/") != name.length() - 1) { //filter according to the path
                    out.add(name);
                }
            }
            jar.close();
        }
        { // Run with IDE
            final URL url = ResourceLoader.class.getResource("/" + path);
            //String basePath = ResourceLoader.class.getResource("/").getPath();
            final String regex = path.replace("/", "\\/")+".*";
            final Pattern resourcePattern = Pattern.compile(regex);
            /*if(basePath.startsWith("/"))
                basePath = basePath.substring(1);*/
            if (url != null) {
                try {
                    final File apps = new File(url.toURI());
                    for (File app : apps.listFiles()) {
                        String currentPath = app.getPath();
                        //So that windows does not annoy me
                        currentPath = currentPath.replace("\\","/");
                        //Trim the base path, so that loadResource will work
                        Matcher matcher = resourcePattern.matcher(currentPath);
                        matcher.find();
                        String match = matcher.group();
                        out.add(match);
                    }
                } catch (URISyntaxException | IllegalArgumentException ex) {
                    // never happens
                }
            }
        }

        return out;
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

    private static List<String> getValidFiles(List<String> fileNames, String[] validExtension){
        return fileNames.stream()
                .filter((s) -> filterFileExtension(s, validExtension))
                .collect(Collectors.toList());
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

    public static List<String> getResourceFiles(String path) throws IOException {
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

    private static InputStream getResourceAsStream(String resource) throws IOException {
        URL fileURL = getContextClassLoader().getResource(resource);
        if(fileURL == null)
            fileURL = ResourceLoader.class.getClassLoader().getResource(resource);
        return fileURL.openStream();
    }

    private static ClassLoader getContextClassLoader() {
        return Thread.currentThread().getContextClassLoader();
    }

}
