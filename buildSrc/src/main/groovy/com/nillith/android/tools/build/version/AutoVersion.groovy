package com.nillith.android.tools.build.version


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

class Config {
    static def major = 0
    static def minor = 0
    static def patch = 0
}

class Utils {
    static int getCommitCount() {
        try {
            def result = new StringBuilder()
            def error = new StringBuilder()
            def process = 'git rev-list HEAD --count'.execute()
            process.waitForProcessOutput(result, error)
            if (result.isInteger()) {
                return result.toInteger()
            } else {
                Messager.info("error:${error}")
                return 0
            }
        } catch (Exception e) {
            Messager.info(e)
        }
        return 0
    }


}

public class VersionCodeProxy {
    private static
    final String INVALID_FLAVOR_NUMBER = 'Invalid flavor number: flavor number must be an non-negative integer!'
    private boolean useFullVersion = false
    private int flavorNumber = -1

    public <T> T asType(Class<T> c) {
        String result = useFullVersion ? "${Config.major}${Config.minor}${Config.patch}${Utils.commitCount}" : "${Utils.commitCount}"
        result = flavorNumber >= 0 ? "${result}${flavorNumber}" : result
        Messager.info("versionCode: ${result}")
        return result.asType(c)
    }

    public VersionCodeProxy fullVersion() {
        useFullVersion = true
        return this
    }

    public int toInteger() {
        return asType(Integer.class)
    }

    public <T> VersionCodeProxy withFlavor(T flavorNumber) {
        int intFlavor
        try {
            intFlavor = flavorNumber.toInteger();
        } catch (Exception e) {
            Messager.failBuild(new IllegalArgumentException(INVALID_FLAVOR_NUMBER))
            return this
        }
        if (intFlavor < 0) {
            Messager.failBuild(new IllegalArgumentException(INVALID_FLAVOR_NUMBER))
        } else {
            this.flavorNumber = intFlavor
        }
        return this
    }
}

public class AutoVersion {
    private static final
    def INVALID_VERSION_NUMBER = 'Version Number must be a non-negative integer!'

    private static <T> int validate(T number) {
        int intNumber
        try {
            intNumber = number.toInteger()
        } catch (Exception e) {
            Messager.failBuild(new IllegalArgumentException(INVALID_VERSION_NUMBER))
        }
        if (intNumber < 0) {
            Messager.failBuild(new IllegalArgumentException(INVALID_VERSION_NUMBER))
        }
        return intNumber
    }

    private static <T> void setVersionMajor(T major) {
        Config.major = validate(major)
    }

    public
    static <TMajor, TMinor, TPatch> void setVersionNumber(TMajor major, TMinor minor, TPatch patch) {
        int intMajor
        int intMinor
        int intPatch
        try {
            intMajor = major.toInteger()
            intMinor = minor.toInteger()
            intPatch = patch.toInteger()
        } catch (Exception e) {
            Messager.failBuild(new IllegalArgumentException(INVALID_VERSION_NUMBER))
            return
        }
        if (intMajor < 0 || intMinor < 0 || intPatch < 0) {
            Messager.failBuild(new IllegalArgumentException(INVALID_VERSION_NUMBER))
            return
        }
        Config.major = intMajor
        Config.minor = intMinor
        Config.patch = intPatch
    }


    public static Integer  getVersionCode() {
        def commitCount = Utils.commitCount;
        Messager.info("versionCode: ${commitCount}")
        return commitCount
    }

    static String getVersionName() {
        def versionName = "${Config.major}.${Config.minor}.${Config.patch}.${Utils.commitCount}"
        Messager.info("versionName: ${versionName}")
        return versionName
    }
}

