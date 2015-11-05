1.NavigationView基本用法

2.NavigationView覆蓋在Statusbar和Toolbar之間

3.NavigationView覆蓋在Statusbar之上

DrawerLayout如果加了android:fitsSystemWindows="true",
并且在v21-style中添加了<item name="android:statusBarColor">@android:color/transparent</item>
和<item name="android:windowDrawsSystemBarBackgrounds">true</item>
就可以让DrawerLayout覆盖在StatusBar上
