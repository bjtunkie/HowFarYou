package com.chat.desktop.service;

public abstract class AbstractFXWindow implements FXWindow {

    private FXWindow base;
    protected App app;
    private final Thread thread = new Thread(this::run);
    private final Object lock = new Object();
    private volatile boolean active = false;

    {
        thread.start();
    }

    @Override
    public void onScreenSet(FXWindow from, Object... input) {
        synchronized (lock) {
            base = from;
            if (input != null) {
                for (Object o : input) {
                    if (o instanceof App) {
                        app = (App) o;
                    }
                }
            }
            active = true;
            lock.notifyAll();
        }
    }

    @Override
    public void onClose() {

    }

    @Override
    public <T extends FXWindow> void setScreen(Class<T> clazz) {
        synchronized (lock) {
            base.setScreen(clazz);
            base = null;
        }
    }

    private void run() {
        while (true) {

            if (!active) {
                synchronized (lock) {
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }

            work();
            active = false;

        }
    }

    public abstract void work();
}
