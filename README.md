# TitleBar
一款自带沉浸式的标题栏

## 最新版本
``` 
implementation 'com.github.huangjingf:titleBar:1.0.3'
```

## 前置配置
需要将应用设置为NoActionBar主题，如修改应用style为
```
    <style name="AppTheme" parent="Theme.AppCompat.Light.NoActionBar">
```

## 基本使用
```
<com.huangjingf.titlelayout.widget.TitleBar
      android:id="@+id/titleBar"
      android:layout_width="match_parent"
      android:layout_height="wrap_content"
      android:background="#000"
      android:elevation="1dp"
      app:backFinish="true"
      app:title="这是标题" />
```

## 设置菜单点击事件
```
//文字
titleBar.setOptionsClickListener("保存", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                
            }
        });
        
//图片
ImageView imageView = new ImageView(activity);
imageView.setImageResource(R.mipmap.pic);
        titleBar.setOptionsClickListener(imageView, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //do something
            }
        });
```

## 全局沉浸式
```
//可写在BaseActivity或BaseFragment中
StatusBarUtils.statusBarTranslucent(activity);
```
## 属性与方法
| 属性 | 参数类型 | 作用 |默认值
|:-----------:|:--------:|:---------:|:---------:|
|app:backFinish|boolean|是否有back键及back功能|false|
|app:backShow|boolean|是否有back键|false|
|app:fitStatus|boolean|是否沉浸式|true|
|app:title|string|标题内容||
|app:titleColor|color|标题字体颜色|Color.White|
|app:optionColor|color|菜单字体颜色|Color.White|
