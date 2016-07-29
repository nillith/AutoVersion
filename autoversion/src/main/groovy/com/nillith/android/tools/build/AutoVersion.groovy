package com.nillith.android.tools.build


public class AutoVersion extends VersionNumbers {
    public Integer getCode() {
        Messager.info("versionCode: ${build}")
        return build
    }

    public String getName() {
        def versionName = "${major}.${minor}.${patch}.${build}"
        Messager.info("versionName: ${versionName}")
        return versionName
    }
}

