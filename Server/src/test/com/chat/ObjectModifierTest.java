package com.chat;

import com.chat.sampleobjects.ModifierObject;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

public class ObjectModifierTest {

    private static final int staticOrTransientModifier = (Modifier.STATIC | Modifier.TRANSIENT);

    public static void main(String... args) {

        ModifierObject object = new ModifierObject() {
        };


        for (Field field : ModifierObject.class.getDeclaredFields()) {

            System.out.println("Analyzing field: " + field.getName());

            int modifier = field.getType().getModifiers();
//            boolean isStaticOrTransient = ((modifier & staticOrTransientModifier) != 0);
//            System.out.println("\tis Static or Transient: " + isStaticOrTransient);

        }

    }
}
