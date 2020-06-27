package org.chat.server;


import com.hello.lib.net.Hello;
import org.chat.server.comm.CoreThread;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CoreApplication {
    private static final Logger LOG = LoggerFactory.getLogger(CoreApplication.class);

    public static void main(String[] args) {
        Hello.instantiate(true, CoreThread.class);
    }

}
