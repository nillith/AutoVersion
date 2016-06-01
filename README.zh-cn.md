# AutoVersion
Android Studio自动生成安卓versionCode和versionName
*Read this in other languages: [English](README.md), [简体中文](README.zh-cn.md).*

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
####2 点击Android Studio上Sync Project With Gradle Files 按钮
####3 配置android config：
在Module级的build.gradle文件中
```groovy
import com.nillith.android.tools.build.version.AutoVersion // 导包
apply plugin: 'com.android.application'

AutoVersion.setVersionNumber(1, 0, 0) // 分别传人app的major、minor和patch版本号(autoversion只自动生成build版本号)

android {
...
    defaultConfig {
        ...
        versionCode AutoVersion.versionCode // 设置versionCode
        versionName AutoVersion.versionName // 设置versionName, 等同于"$major.$minor.$patch.$versionCode"
		...
    }
	...
}
```
