# TitleBar
一款自带沉浸式的标题栏

## 最新版本
``` 
implementation 'com.github.huangjingf:titleBar:1.0.2'
```

## 基本使用
```
<com.huangjingf.titlelayout.widget.TitleBar
      android:id="@+id/titleLayout"
      android:layout_width="match_parent"
      android:layout_height="wrap_content"
      android:background="#fff"
      android:elevation="1dp"
      app:backFinish="true"
      app:title="这是标题" />
```

## 全局沉浸式
```
//可写在BaseActivity或BaseFragment中
StatusBarUtils.statusBarTranslucent(activity);
```
