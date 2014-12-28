package com.gc.flashview.effect;

import android.support.v4.view.ViewPager.PageTransformer;
import android.view.View;

public class DepthPageTransformer implements PageTransformer {
	private static float MIN_SCALE = 0.5f;

	/**
	 * position参数指明给定页面相对于屏幕中心的位置。它是一个动态属性，会随着页面的滚动而改变。当一个页面填充整个屏幕是，它的值是0，
	 * 当一个页面刚刚离开屏幕的右边时
	 * ，它的值是1。当两个也页面分别滚动到一半时，其中一个页面的位置是-0.5，另一个页面的位置是0.5。基于屏幕上页面的位置
	 * ，通过使用诸如setAlpha()、setTranslationX()、或setScaleY()方法来设置页面的属性，来创建自定义的滑动动画。
	 */
	@Override
	public void transformPage(View view, float position) {
		int pageWidth = view.getWidth();
		if (position < -1) { // [-Infinity,-1)
								// This page is way off-screen to the left.
			view.setAlpha(0);
			view.setTranslationX(0);
		} else if (position <= 0) { // [-1,0]
									// Use the default slide transition when
									// moving to the left page
			view.setAlpha(1);
			view.setTranslationX(0);
			view.setScaleX(1);
			view.setScaleY(1);
		} else if (position <= 1) { // (0,1]
									// Fade the page out.
			view.setAlpha(1 - position);
			// Counteract the default slide transition
			view.setTranslationX(pageWidth * -position);
			// Scale the page down (between MIN_SCALE and 1)
			float scaleFactor = MIN_SCALE + (1 - MIN_SCALE)
					* (1 - Math.abs(position));
			view.setScaleX(scaleFactor);
			view.setScaleY(scaleFactor);
		} else { // (1,+Infinity]
					// This page is way off-screen to the right.
			view.setAlpha(0);
			view.setScaleX(1);
			view.setScaleY(1);
		}
	}

}