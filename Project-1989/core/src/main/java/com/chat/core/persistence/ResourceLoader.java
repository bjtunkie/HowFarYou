package com.chat.core.persistence;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.nio.file.Path;

public class ResourceLoader {

    private static String defaultResource;

    public static final int MAIN = 0;
    public static final int DEBUG = 1;

    public synchronized static void init(int type, Class<?>... parents) {

        Class<?> parent = parents[0];
        String classpath = parent.getResource("").getPath();
        System.out.println("Working on path: " + classpath);

        int indexOfClasses = classpath.indexOf("/classes/");
        int indexOfJar = classpath.indexOf(".jar!");
        if (indexOfClasses > 0) {
            String prefix = classpath.substring(0, indexOfClasses);
            defaultResource = prefix + (type == MAIN ? "/resources/main/" : "/resources/test/");
        } else if (indexOfJar > 0) {
            String prefix = classpath.substring(0, indexOfJar + 5);
            defaultResource = prefix + "/";
        }


        System.out.println("Default resource folder: " + defaultResource);

    }

    public static URI getURI(String nameOfFile) {
        return getFileFromResource(nameOfFile).toURI();
    }

    public static URL getURL(String nameOfFile) {
        try {
            return getFileFromResource(nameOfFile).toURI().toURL();
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static InputStream getFromResource(String nameOfFile) {

        try {
            return new FileInputStream(defaultResource + nameOfFile);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.exit(0);
        }
        return null;
    }

    public static String getResourceAsString(String nameOfFile) {
        InputStream is = getFromResource(nameOfFile);
        InputStreamReader r = new InputStreamReader(is);
        BufferedReader reader = new BufferedReader(r);
        String line = "";
        StringBuilder builder = new StringBuilder();
        try {
            while ((line = reader.readLine()) != null) {
                builder.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return builder.toString();
    }

    private static File getFileFromResource(String m) {
        String path = defaultResource + m;
        return new File(path);
    }

    public static Path getAbsolutePath(String res) {
        return Path.of(defaultResource + res);
    }
}
