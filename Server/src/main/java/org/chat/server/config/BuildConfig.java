package org.chat.server.config;

public class BuildConfig {

    public static final Boolean DEBUG;


    static {

        String _profile = System.getProperty("spring.profiles.active");
        DEBUG = (null != _profile) && _profile.toLowerCase().startsWith("dev");


    }
}
