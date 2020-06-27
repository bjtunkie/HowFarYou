package com.hello.lib.net.tool;

@FunctionalInterface
public interface MessageResponse {

    void onResponse(Object response);
}
