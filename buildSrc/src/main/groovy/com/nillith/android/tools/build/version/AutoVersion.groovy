package com.nillith.android.tools.build.version

public class AutoVersion {
    private static final def MSG_HEAD = "AutoVersion"
    private static def major = 0
    private static def minor = 0
    private static def patch = 0

    private static void stop(Exception e) {
        throw new RuntimeException("${MSG_HEAD}:error\n${e.getClass().getCanonicalName()}\n${e.getMessage()}")
    }

    public static void setVersionNumber(int major, int minor, int patch) {
        if (major < 0 || minor < 0 || patch < 0) {
            stop(new IllegalStateException("Negative Version Number!"))
            return;
        }
        AutoVersion.major = major
        AutoVersion.minor = minor
        AutoVersion.patch = patch
    }

    private static int getCommitCount() {
        try {
            def commitCount = 'git rev-list HEAD --count'.execute().text.trim().toInteger()
            if (commitCount < 0) {
                stop(new RuntimeException('git not found!'))
            } else {
                return commitCount
            }
        } catch (Exception e) {
            stop(e)
        }
    }

    static int getVersionCode() {
        try {
            def versionCode = "${major}${minor}${patch}${getCommitCount()}".toInteger()
            println "${MSG_HEAD}:versionCode: ${versionCode}"
            return versionCode;
        } catch (Exception e) {
            stop(e)
        }
    }

    static String getVersionName() {
        def versionName = "${major}.${minor}.${patch}.${getCommitCount()}"
        println "${MSG_HEAD}:versionName: ${versionName}"
        return versionName
    }
}
