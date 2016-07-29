# AutoVersion
Android Studio根据git仓库提交数自动生成安卓versionCode和versionName。

*其他语言版本: [English](README.md), [简体中文](README.zh-cn.md).*

### 使用方法
####1 添加 gradle **buildscript** 依赖
在project级build.gradle文件中
```groovy
buildscript {
	...
    dependencies {
        classpath 'com.nillith.android:autoversion:1.0.1' // 在这里添加autoversion依赖
    }
}
```
####2 配置android config：
在Module级的build.gradle文件中
```groovy
...
apply plugin: 'com.nillith.autoversion'

autoVersion {
    major 1
    minor 0
    patch 0
    // 你也可以在这里自己给定build。这样build版本号将不会自动生成。
    // build 0
}

android {
...
    defaultConfig {
        ...
        versionCode autoVersion.code // 设置versionCode, 实际值autoVersion.build，若未指定，则为当前git仓库的提交数
        versionName autoVersion.name // 设置versionName, 等同于"$major.$minor.$patch.$versionCode"
		...
    }
	...
}
```

如果工作正常，Gradle console 在 sync 时打印一下信息。

```
AutoVersion:versionCode: xxx
AutoVersion:versionName: x.x.x.x
```