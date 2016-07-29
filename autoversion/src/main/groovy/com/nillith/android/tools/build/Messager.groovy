package com.nillith.android.tools.build


class Messager {
    private static final def MSG_HEAD = "AutoVersion"

    public static void info(String msg) {
        println "${MSG_HEAD}:${msg.trim()}"
    }

    private static void msgFrom(Exception e) {
        "${MSG_HEAD}:error\n${e.getClass().getCanonicalName()}\n${e.getMessage()}"
    }

    public static void info(Exception e) {
        println msgFrom(e)
    }

    public static void failBuild(Exception e) {
        throw new RuntimeException(msgFrom(e))
    }
}
