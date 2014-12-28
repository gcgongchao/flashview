package com.gc.flashview.effect;

import com.nineoldandroids.view.ViewHelper;

import android.support.v4.view.ViewPager.PageTransformer;
import android.view.View;

public class CubeTransformer implements PageTransformer {
	
	/**
	 * position参数指明给定页面相对于屏幕中心的位置。它是一个动态属性，会随着页面的滚动而改变。当一个页面填充整个屏幕是，它的值是0，
	 * 当一个页面刚刚离开屏幕的右边时，它的值是1。当两个也页面分别滚动到一半时，其中一个页面的位置是-0.5，另一个页面的位置是0.5。基于屏幕上页面的位置
	 * ，通过使用诸如setAlpha()、setTranslationX()、或setScaleY()方法来设置页面的属性，来创建自定义的滑动动画。
	 */
	@Override
	public void transformPage(View view, float position) {
		if (position <= 0) {
			//从右向左滑动为当前View
			
			//设置旋转中心点；
			ViewHelper.setPivotX(view, view.getMeasuredWidth());
			ViewHelper.setPivotY(view, view.getMeasuredHeight() * 0.5f);
			
			//只在Y轴做旋转操作
			ViewHelper.setRotationY(view, 90f * position);
		} else if (position <= 1) {
			//从左向右滑动为当前View
			ViewHelper.setPivotX(view, 0);
			ViewHelper.setPivotY(view, view.getMeasuredHeight() * 0.5f);
			ViewHelper.setRotationY(view, 90f * position);
		}
	}
}
