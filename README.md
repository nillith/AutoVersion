# AutoVersion
Generating versionCode and versionName automatically for Android Project With Android Studio based on git commit count

*Read this in other languages: [English](README.md), [简体中文](README.zh-cn.md).*

### How to use
####1 Add gradle **buildscript** dependencies
In your root project level build.gradle
```groovy
buildscript {
	...
    dependencies {
        classpath 'com.nillith.android:autoversion:1.1.0'
        ...
    }
}
```
####2 Setup android config
In you module level build.gradle
```groovy

...
apply plugin: 'com.nillith.autoversion'

autoVersion {
    major 1
    minor 0
    patch 0
    // You can also specify a build here. AutoVersion will use it instead of git commit count.
    // build 3
}


android {
...
    defaultConfig {
        ...
        versionCode autoVersion.code // same as build. build will be the commit count of current git repo if not specified.
        versionName autoVersion.name // same as "$major.$minor.$patch.$versionCode"
		...
    }
	...
}
```
If doing right. Gradle console will print the following message when sync

```
AutoVersion:versionCode: xxx
AutoVersion:versionName: x.x.x.x
```
