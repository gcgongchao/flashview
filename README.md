flashview
=========

首页广告位轮播，用于应用程序首页的轮播图，如下图所展示：

 ![image] (https://github.com/gcgongchao/flashview/raw/master/images/flashviewgit20150128.gif)
 
使用此library时实现上图中的轮播效果时，分为以下两步骤: <br/>
（1）在布局文件中加入如下代码块：<br/>
      
      <com.gc.flashview.FlashView
        android:id="@+id/flash_view"
        android:layout_width="match_parent"
        android:layout_height="300dp"
        android:layout_margin="10dp"
        app:effect="cube"  
        />
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
        flashView.setEffect(EffectConstants.CUBE_EFFECT);//更改图片切换的动画效果
        
此代码使用比较简单，后续我会加入轮播切换的效果，至于切换动画的一些类已经在里面了，下次更新时，将和自定义的控件一起使用。<br/>
（3）如果想对图片进行点击事件监听，可以在你的Activity或Fragment实现FlashViewListener接口，用法如下：<br/>

        
        
          public class MainActivity extends Activity  implements FlashViewListener{
          private FlashView flashView;
          private List<String> imageUrls;
          @Override
          protected void onCreate(Bundle savedInstanceState) {
          super.onCreate(savedInstanceState);
          setContentView(R.layout.activity_main);
          flashView=(FlashView)findViewById(R.id.flash_view);
          imageUrls=new ArrayList<String>();
          imageUrls.add("http://www.qipaox.com/tupian/200810/20081051924582.jpg");
          imageUrls.add("http://www.bz55.com/uploads1/allimg/120312/1_120312100435_8.jpg");
          imageUrls.add("http://img3.iqilu.com/data/attachment/forum/201308/21/192654ai88zf6zaa60zddo.jpg");
          imageUrls.add("http://img2.pconline.com.cn/pconline/0706/19/1038447_34.jpg");
          flashView.setImageUris(imageUrls);
          flashView.setEffect(EffectConstants.CUBE_EFFECT);//更改图片切换的动画效果
          }
          @Override
          public void onClick(int position) {
          Toast.makeText(getApplicationContext(), "你的点击的是第"+(position+1)+"张图片！", 1000).show();
          }
 
	
如果在使用过程有任何bug，意见和指导，欢迎反馈与指导。本次加入的动画效果的代码来源于网络，在此感谢贡献此动画效果的作者。下次更新会尝试加入自己写的动画效果，欢迎star。
