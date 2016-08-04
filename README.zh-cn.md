# AutoVersion
Android studio 管理 app versionCode和versionName的gradle插件。可以根据git仓库提交数自动更新versionCode。

*其他语言版本: [English](README.md), [简体中文](README.zh-cn.md).*

### 使用方法
####1 添加 依赖
在rott project 的build.gradle文件中
```groovy
buildscript {
	...
    dependencies {
        classpath 'com.nillith.android:autoversion:1.0.1' // 在这里添加autoversion依赖
    }
}
```
####2 配置
在app Module 的build.gradle文件中
```groovy
...
apply plugin: 'com.nillith.autoversion'

// 配置各版本号。
autoVersion {
    major 1
    minor 0
    patch 0
    // build 0 // 若这里指定了build版本号。versionCode将不会根据提交数自动更新
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

如果工作正常，sync 时 Gradle console 会打印以下信息。

```
AutoVersion:versionCode: xxx
AutoVersion:versionName: x.x.x.x
```

## License

Nillith, 2016. Licensed under an [Apache-2](LICENSE.txt) license.