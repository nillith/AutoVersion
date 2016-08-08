# AutoVersion
Gradle plugin for managing android app versionCode and versionName. Auto update versionCode on every git commit.

*Read this in other languages: [English](README.md), [简体中文](README.zh-cn.md).*

### How to use
####1 Dependencies

In root project build.gradle file
```groovy
buildscript {
	...
    dependencies {
        classpath 'com.nillith.android:autoversion:1.1.0'
        ...
    }
}
```
####2 Config
In app module build.gradle file
```groovy
...
apply plugin: 'com.nillith.autoversion'

// Config your version numbers here. 
autoVersion {
    major 1
    minor 0
    patch 0
    // build 3 // You can provide your own build number to disable auto versionCode update.
}


android {
...
    defaultConfig {
        ...
        versionCode autoVersion.code // same as build.
        versionName autoVersion.name // same as "$major.$minor.$patch.$build"
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

## License

Nillith, 2016. Licensed under an [Apache-2](LICENSE.txt) license.
