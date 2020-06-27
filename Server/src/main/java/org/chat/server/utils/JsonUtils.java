package org.chat.server.utils;


import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.chat.server.config.BuildConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.annotation.Annotation;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

@Retention(RetentionPolicy.RUNTIME)
@interface JsonIgnore {
}

public final class JsonUtils {
    /**
     * The main issue that could occur is when a super class and subclass has both declared
     * the same variable. Such case will throw a Illegal state exception or Runtime Exception.
     * <p>
     * There are three ways to tackle it. (1) Becareful in declaring variables.
     * (2) add an exclusion strategy in this gson file.
     * (3) figure out the name of variable and make sure you add that name in the list of "knownOffenders"
     * Point (3) seems to be efficient. Hence using that.
     */
    private static final String[] knownOffenders = {"serialVersionUID"};

    private transient static final String TAG = JsonUtils.class.getName();
    private transient static final Logger LOGGER = LoggerFactory.getLogger(JsonUtils.class);
    private static final String JsonIgnoreTag = JsonIgnore.class.getName().toLowerCase();
    private static final ExclusionStrategy exclusionStrategy = new ExclusionStrategy() {
        @Override
        public boolean shouldSkipField(FieldAttributes attributes) {
            for (Annotation annotation : attributes.getAnnotations()) {
                if (annotation.toString()
                        .toLowerCase()
                        .contains(JsonIgnoreTag)) return true;
            }

            String fieldName = attributes.getName();
//            Class<?> theClass = attributes.getDeclaringClass();

            for (String mString : knownOffenders) {
                if (mString.equals(fieldName)) return true;
            }
            return false;
            /**
             * uncomment and run this part to find the known offenders in your class in future :)
             */
            //            return isFieldInSuperclass(theClass, fieldName);
        }

        @Override
        public boolean shouldSkipClass(Class<?> clazz) {
            return false;
        }

        private boolean isFieldInSuperclass(Class<?> subclass, String fieldName) {
            Class<?> superclass = subclass.getSuperclass();
            Field field;
            while (superclass != null) {
                field = getField(superclass, fieldName);
                if (field != null) {
                    LOGGER.info(fieldName);
                    return true;
                }
                superclass = superclass.getSuperclass();
            }
            return false;
        }

        private Field getField(Class<?> theClass, String fieldName) {
            try {
                return theClass.getDeclaredField(fieldName);
            } catch (Exception e) {
                return null;
            }
        }

    };

    //@formatter:off
    private static final Gson _deb = (new GsonBuilder())
            .excludeFieldsWithModifiers(Modifier.VOLATILE)
            .setPrettyPrinting()
            .create();
    private static final Gson _rel1 = (new GsonBuilder())
            .excludeFieldsWithModifiers(Modifier.TRANSIENT)
            .addSerializationExclusionStrategy(exclusionStrategy)
            .addDeserializationExclusionStrategy(exclusionStrategy)
            .create();

    private static final Gson _rel2 = (new GsonBuilder())
            .create();
    //@formatter:on

    public static String toJson(Object o) {
        return _cur.toJson(o);
    }

    public static <T> T fromJson(String _j, Class<T> classOfT) {
        return _cur.fromJson(_j, classOfT);
    }

    private static final JsonSelector _cur = BuildConfig.DEBUG ? new JsonSelector(_deb) : new JsonSelector(_rel2);

    private static final class JsonSelector {
        final Gson gson;

        private JsonSelector(Gson gson) {
            this.gson = gson;
        }

        private String toJson(Object o) {
            return gson.toJson(o);
        }

        <T> T fromJson(String _j, Class<T> classOfT) {
            return gson.fromJson(_j, classOfT);
        }
    }
}
