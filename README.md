flashview
=========

首页广告位轮播，用于应用程序首页的轮播图，如下图所展示（gif制作的不好，影响效果了，先这样吧，下更新，制作一副比较好的gif图片）：

 ![image] (https://github.com/gcgongchao/flashview/raw/master/images/flashviewgit.gif)
 
使用此library时实现上图中的轮播效果时，分为以下两步骤: <br/>
（1）在布局文件中加入如下代码块：<br/>
      
      <com.gc.flashview.FlashView
        android:id="@+id/flash_view"
        android:layout_width="match_parent"
        android:layout_height="300dp"
        android:layout_margin="10dp"/>
（2）在Activity或Fragment中，想如下那样使用该控件：<br/>

        flashView=(FlashView)findViewById(R.id.flash_view);
        imageUrls=new ArrayList<String>();
        imageUrls.add("http://www.qipaox.com/tupian/200810/20081051924582.jpg");
        imageUrls.add("http://www.bz55.com/uploads1/allimg/120312/1_120312100435_8.jpg");
        imageUrls.add("http://img3.iqilu.com/data/attachment/forum/201308/21/192654ai88zf6zaa60zddo.jpg");
        imageUrls.add("http://img2.pconline.com.cn/pconline/0706/19/1038447_34.jpg");<br/>
        imageUrls.add("http://www.kole8.com/desktop/desk_file-11/2/2/2012/11/2012113013552959.jpg");
        imageUrls.add("http://www.237.cc/uploads/pcline/712_0_1680x1050.jpg");
        flashView.setImageUris(imageUrls);
        
此代码使用比较简单，后续我会加入轮播切换的效果，至于切换动画的一些类已经在里面了，下次更新时，将和自定义的控件一起使用。
