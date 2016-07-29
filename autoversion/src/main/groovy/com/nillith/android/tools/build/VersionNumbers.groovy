package com.nillith.android.tools.build


class VersionNumbers {
    private static final def UN_SPECIFIED = -1;
    def major = 0
    def minor = 0
    def patch = 0
    def build = UN_SPECIFIED

    public <T> void setMajor(T number) {
        major = validate(number)
    }

    public <T> void setMinor(T number) {
        minor = validate(number)
    }

    public <T> void setPatch(T number) {
        patch = validate(number)
    }

    public <T> void setBuild(T number) {
        build = validate(number)
    }

    public def getBuild() {
        if (UN_SPECIFIED == build) {
            build = Utils.commitCount;
        }
        return build;
    }

    private static final
    def INVALID_VERSION_NUMBER = 'Version Number must be a non-negative integer!'

    private static <T> int validate(T number) {
        def intNumber
        try {
            intNumber = number.toInteger()
        } catch (Exception e) {
            Messager.failBuild(new IllegalArgumentException(INVALID_VERSION_NUMBER))
            return 0;
        }
        if (intNumber < 0) {
            Messager.failBuild(new IllegalArgumentException(INVALID_VERSION_NUMBER))
            return 0;
        }
        return intNumber
    }
}
