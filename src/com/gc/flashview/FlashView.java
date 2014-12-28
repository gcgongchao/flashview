package com.gc.flashview;

import java.lang.ref.WeakReference;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;











import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Message;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.support.v4.view.ViewPager.PageTransformer;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewParent;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.Interpolator;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Scroller;



/**
 * 
 * @author Chao Gong
 *
 */
@SuppressLint("HandlerLeak")
public class FlashView extends FrameLayout {

   
	private ImageLoaderWraper imageLoaderWraper;
	private ImageHandler mhandler = new ImageHandler(new WeakReference<FlashView>(this));
    
    private List<String> imageUris;
    private List<ImageView> imageViewsList;
    private List<ImageView> dotViewsList;
    private LinearLayout mLinearLayout;
    private ViewPager mViewPager;
   
    
  
    public FlashView(Context context) {
        this(context,null);
    }
    public FlashView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }
    public FlashView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        // TODO Auto-generated constructor stub
       
        initUI(context);
        if(!(imageUris.size()<=0))
        {
        	System.out.println("XXXXXXXXXXXX");
        	 setImageUris(imageUris);
        }
        
    }
    private void initUI(Context context){
    	  imageViewsList = new ArrayList<ImageView>();
          dotViewsList = new ArrayList<ImageView>();
          imageUris=new ArrayList<String>();
          imageLoaderWraper=ImageLoaderWraper.getInstance(context.getApplicationContext());
          LayoutInflater.from(context).inflate(R.layout.layout_slideshow, this, true);
          mLinearLayout=(LinearLayout)findViewById(R.id.linearlayout);
          mViewPager = (ViewPager) findViewById(R.id.viewPager);
         
         
    }
    public void setImageUris(List<String> imageuris)
    {
    	
    	for(int i=0;i<imageuris.size();i++)
    	{
    		imageUris.add(imageuris.get(i));
    		
    	}
    	LinearLayout.LayoutParams lp=new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT);
        lp.setMargins(5, 0, 0, 0);
        for(int i=0;i<imageUris.size();i++){
        	ImageView imageView=new ImageView(getContext());
        	imageView.setScaleType(ScaleType.FIT_XY);//锟斤拷锟斤拷锟斤拷幕
        	imageLoaderWraper.displayImage(imageUris.get(i), imageView);
        	imageViewsList.add(imageView);
        	ImageView viewDot =  new ImageView(getContext());
        		if(i == 0){  
        		  viewDot.setBackgroundResource(R.drawable.dot_white);  
        		}else{  
            	  viewDot.setBackgroundResource(R.drawable.dot_light);  
        		}  
        	viewDot.setLayoutParams(lp);
        	dotViewsList.add(viewDot);
        	mLinearLayout.addView(viewDot); 
      }
        mViewPager.setFocusable(true);
        mViewPager.setAdapter(new MyPagerAdapter());
      
        mViewPager.setOnPageChangeListener(new MyPageChangeListener());
        try {
            Field field = ViewPager.class.getDeclaredField("mScroller");
            field.setAccessible(true);
            FixedSpeedScroller scroller = new FixedSpeedScroller(mViewPager.getContext(),
                    new AccelerateInterpolator());
            field.set(mViewPager, scroller);
            scroller.setmDuration(1000);
        } catch (Exception e) {
//            LogUtils.e(TAG, "", e);
        }
       // mViewPager.setPageTransformer(true,  new CubeTransformer());
        mViewPager.setCurrentItem(100*imageViewsList.size());
         
        mhandler.sendEmptyMessageDelayed(ImageHandler.MSG_UPDATE_IMAGE, ImageHandler.MSG_DELAY); 
        System.out.println("currentItem:::"+mViewPager.getCurrentItem());
    }
   

   
    /** 
     * 
     * @param selectItems 
     */  
    private void setImageBackground(int selectItems){  
        for(int i=0; i<dotViewsList.size(); i++){  
            if(i == selectItems%dotViewsList.size()){  
            	dotViewsList.get(i).setBackgroundResource(R.drawable.dot_white);  
            }else{  
            	dotViewsList.get(i).setBackgroundResource(R.drawable.dot_light);  
            }  
        }  
    }  
    private class MyPagerAdapter  extends PagerAdapter{

        @Override
        public void destroyItem(View container, int position, Object object) {
        	
        }

        @Override
        public Object instantiateItem(View container, int position) {
        	System.out.println("位锟矫ｏ拷"+position);
        	position = position % imageViewsList.size();
        	
			if(position<0)
			{
				position=position+imageViewsList.size();
				
			}
			
			View view = imageViewsList.get(position);
			ViewParent vp=view.getParent();
			if(vp!=null)
			{
				ViewPager pager=(ViewPager)vp;
				pager.removeView(view);
			}
			((ViewPager) container).addView(view);
			return view;
        }

        @Override
        public int getCount() {
         return Integer.MAX_VALUE;
        }

        @Override
        public boolean isViewFromObject(View arg0, Object arg1) {
           return arg0 == arg1;
        }
       
    }
  
    
private class MyPageChangeListener implements OnPageChangeListener{

        

        @Override
        public void onPageScrollStateChanged(int arg0) {
            // TODO Auto-generated method stub
          
        	  switch (arg0) {  
              case ViewPager.SCROLL_STATE_DRAGGING: 
            	 
                  mhandler.sendEmptyMessage(ImageHandler.MSG_KEEP_SILENT);  
                  break;  
              case ViewPager.SCROLL_STATE_IDLE:  
            	  
                  mhandler.sendEmptyMessageDelayed(ImageHandler.MSG_UPDATE_IMAGE, ImageHandler.MSG_DELAY);  
                  break;  
              default:  
                  break;  
              }  
        }

        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {
            // TODO Auto-generated method stub
            
        }

        @Override
        public void onPageSelected(int pos) {// 锟铰憋拷为 pos 锟斤拷页锟斤拷锟斤拷锟斤拷锟阶帮拷锟绞憋拷锟斤拷锟斤拷么朔锟斤拷锟�
            // TODO Auto-generated method stub
        	System.out.println("eeeeeeeeeee::::"+pos);
        	mhandler.sendMessage(Message.obtain(mhandler, ImageHandler.MSG_PAGE_CHANGED, pos, 0));  
        	 setImageBackground(pos);  

        }
        
    }
    
    
  
    
    @SuppressWarnings("unused")
	private void destoryBitmaps() {

        for (int i = 0; i < imageViewsList.size(); i++) {
            ImageView imageView = imageViewsList.get(i);
            Drawable drawable = imageView.getDrawable();
            if (drawable != null) {
                
                drawable.setCallback(null);
            }
        }
    }
    
	public void setPageTransformer(boolean b,
			PageTransformer rotateTransformer) {
		// TODO Auto-generated method stub
		mViewPager.setPageTransformer(b, rotateTransformer);
	}
	/**
	 * 
	 * @author Chao Gong
	 *
	 */
	public class FixedSpeedScroller extends Scroller {
	    private int mDuration = 1500;
	                                                                                                            
	    public FixedSpeedScroller(Context context) {
	        super(context);
	    }
	                                                                                                            
	    public FixedSpeedScroller(Context context, Interpolator interpolator) {
	        super(context, interpolator);
	    }
	                                                                                                            
	    @Override
	    public void startScroll(int startX, int startY, int dx, int dy, int duration) {
	        // Ignore received duration, use fixed one instead
	        super.startScroll(startX, startY, dx, dy, mDuration);
	    }
	                                                                                                            
	    @Override
	    public void startScroll(int startX, int startY, int dx, int dy) {
	        // Ignore received duration, use fixed one instead
	        super.startScroll(startX, startY, dx, dy, mDuration);
	    }
	                                                                                                            
	    public void setmDuration(int time) {
	        mDuration = time;
	    }
	                                                                                                            
	    public int getmDuration() {
	        return mDuration;
	    }
	}

	 private static class ImageHandler extends Handler{  
	        
	       
	        protected static final int MSG_UPDATE_IMAGE  = 1;  
	         
	        protected static final int MSG_KEEP_SILENT   = 2;  
	         
	        protected static final int MSG_BREAK_SILENT  = 3;  
	        
	        protected static final int MSG_PAGE_CHANGED  = 4;  
	           
	        
	        protected static final long MSG_DELAY = 2000;  
	           
	     
	        private WeakReference<FlashView> weakReference;  
	        private int currentItem = 0;  
	           
	        protected ImageHandler(WeakReference<FlashView> wk){  
	            weakReference = wk;  
	            System.out.println("dsfdsfdsf:::"+currentItem);
	        }  
	           
	        @Override  
	        public void handleMessage(Message msg) {  
	            super.handleMessage(msg);  
	            //Log.d(LOG_TAG, "receive message " + msg.what);  
	            FlashView activity = weakReference.get();  
	            if (activity==null){  
	              
	                return ;  
	            }  
	            
	            if (activity.mhandler.hasMessages(MSG_UPDATE_IMAGE)){  
	            	if(currentItem>0)
	            	{
	            		System.out.println("锟狡筹拷::::"+currentItem);
		                activity.mhandler.removeMessages(MSG_UPDATE_IMAGE);  
	            	}
	            	
	            }  
	            switch (msg.what) {  
	            case MSG_UPDATE_IMAGE: 
	            	System.out.println("cccccc:::"+currentItem);
	                currentItem++;  
	                activity.mViewPager.setCurrentItem(currentItem);  
	               
	                
	                activity.mhandler.sendEmptyMessageDelayed(MSG_UPDATE_IMAGE, MSG_DELAY);  
	                break;  
	            case MSG_KEEP_SILENT:  
	              
	                break;  
	            case MSG_BREAK_SILENT:  
	                activity.mhandler.sendEmptyMessageDelayed(MSG_UPDATE_IMAGE, MSG_DELAY);  
	                break;  
	            case MSG_PAGE_CHANGED:  
	                
	                currentItem = msg.arg1;  
	                break;  
	            default:  
	                break;  
	            }   
	        }  
	    }  
}



