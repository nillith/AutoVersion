# AutoVersion
Generating versionCode and versionName automatically for Android Project With Android Studio based on git commit count

*Read this in other languages: [English](README.md), [简体中文](README.zh-cn.md).*

### How to use
####1 Add gradle **buildscript** dependencies
In your project level build.gradle
```groovy
buildscript {
	...
    dependencies {
        classpath 'com.nillith.android:autoversion:1.0.1'
    }
}
```
####2 Sync Project
Click Sync Project With Gradle Files button on Android Studio
####3 Setup android config
In you module level build.gradle
```groovy
import com.nillith.android.tools.build.version.AutoVersion
  // import AutoVersion

apply plugin: 'com.android.application'

AutoVersion.setVersionNumber(1, 0, 0) // pass in VersionMajor, VersionMinor and VersionPatch.(autoversion only generate VersionBuild for you)

android {
...
    defaultConfig {
        ...
        versionCode AutoVersion.versionCode // set versionCode. Its actually the commit count of current git repo
        versionName AutoVersion.versionName // same as "$major.$minor.$patch.$versionCode"
		...
    }
	...
}
```
