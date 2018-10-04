[TOC]

## 一、《Android资源文件命名规约》

> 根据约束力强弱和故障故障敏感性，将规约分为“强制”、“推荐”、“参考”三类。
> * 【强制】：必须遵守，违反本约定或将会引起严重的后果； 
> * 【推荐】：尽量遵守，长期遵守有助于系统稳定性和合作效率的提升； 
> * 【参考】：充分理解，技术意识的引导，是个人学习、团队沟通、项目合作的方向。

<br/>

### 1、推荐

* 资源文件名需带有模块名前缀。

* layout资源文件的命名规约：

  <br/>

  * Activity的layout：以`module_activity`开头；
  * Fragment的layout：以`module_fragment`开头；
  * Dialog的layout：以`module_dialog`开头；
  * include的layout：以`module_include`开头；
  * ListView的行layout：以`module_list_item`开头；
  * RecyclerView的item layout：以`module_recycle_item`开头；
  * GridView的item layout：以`module_grid_item`开头；

* drawable资源文件名称以 “小写单词 + 下划线” 的方式命名，根据分辨率高低放置在不同的drawable目录下。如果介意APK包的大小，则可以只使用一套，让系统根据环境进行自动缩放。采用的具体规则如下：

  **模块名\_业务功能描述\_控件描述\_控件状态限定词**

  如：module1_login_btn_pressed。

* anim资源文件名称以 “小写单词 + 下划线” 的方式命名，采用的具体规则如下：

  **模块名\_逻辑名称_\[方向|序号]**

  <br/>

  * Tween动画（使用简单图像变换的动画，例如平移、缩放）资源文件：尽可能以通用的动画名称命名（“模块 + 动画 + 方向”）。如 module1_fade_in、module2_fade_out、module3_push_down_in。

  * Frame动画（按帧顺序播放图像的动画）资源文件：尽可能以 “模块 + 功能命名 + 序号”命名。如

    module1_loading_grey_001。

* color资源文件使用#AARRGGBB格式，写入`module_colors.xml`文件中，对于用到的每种颜色的命名规约如下：

  **模块名\_逻辑名称\_颜色**

  如：`<color name="module1_btn_bg_color">#33b5e5e5</color>`

* dimen资源文件以 “小写单词 + 下划线” 的方式命名，写入`module_dimens.xml`文件中，对于用到的每种dimen(尺寸)的命名规约如下：

  **模块名_描述信息**

  如：`<dimen name="module1_horizontal_line_height">1dp</dimen>`

* style资源文件以 “父style名称 . 当前style名称” 方式命名，写入`module_styles.xml`文件中，首字母大写。如：

  ```xml
  <style name="ParentTheme.ThisActivityTheme">
    ...
  </style>
  ```

* string资源文件或文本用到字符都需全部写入`module_strings.xml`文件中，对于用到的每个字符串均以“小写单词 + 下划线” 的方式命名，采用的具体规则如下：

  **模块名_逻辑名称**

  如：module1_login_tips、module2_homepage_notice_desc

* 资源id对应的View组件资源的名称以驼峰法命名，View组件资源的id则建议以View名的缩写作为前缀。采用的缩写表如下：

  | 控件               | 缩写   |
  | ---------------- | ---- |
  | LinearLayout     | ll   |
  | RelativeLayout   | rl   |
  | ConstraintLayout | cl   |
  | ListView         | lv   |
  | ScrollView       | sv   |
  | TextView         | tv   |
  | Button           | btn  |
  | ImageView        | iv   |
  | CheckBox         | cb   |
  | RadioButton      | rb   |
  | EditText         | et   |

  其他控件的缩写推荐使用小写字母并用下划线进行分隔。如：

  ProgressBar对应的缩写为progress_bar、DatePicker对应的缩写为date_picker。

* 图片根据分辨率置于不同屏幕密度的drawable目录下，否则可能在低密度的设备上导致内存占用增加，又可能在高密度的设备上导致图片显示不够清晰。

  Android提供了多种通用的屏幕密度，常用的如下：

  * ldpi - 120dpi
  * mdpi - 160dpi
  * hdpi - 240dpi
  * xhdpi - 320dpi
  * xxhdpi - 480dpi
  * xxxhdpi - 640dpi

  Android的屏幕分辨率和密度之间不存在严格的对应关系，因为还要考虑到屏幕的尺寸大小。我们平时应该避免基于屏幕分辨率来开发，而是通过适配不同的屏幕密度来保证控件和图片的显示效果。其中，不同drawable目录下的图片分辨率设置，参考不同密度的dpi比例关系。

  比如，我为了显示某个图标，将 48 x 48 的图标文件放在 drawable-mdpi 目录（160dpi）下； 将 72 x 72 的图标文件放在 drawable-hdpi 目录（240dpi）下；将 96 x 96 的图标 文件放在 drawable-xhdpi 目录（320dpi）下；将 144 x 144 的图标文件放在 drawable-xxhdpi 目录（480dpi）下。

  但是，如果我只是将一个 144 x 144 的图标文件放在 drawable 目录下，则不行！ 

