package com.gc.flashview.effect;

import android.support.v4.view.ViewPager.PageTransformer;
import android.view.View;

import com.nineoldandroids.view.ViewHelper;

public class AccordionTransformer implements PageTransformer {

	@Override
	public void transformPage(View view, float position) {
		if (position < -1) {
			ViewHelper.setPivotX(view, view.getMeasuredWidth() * 0.5f);
			ViewHelper.setPivotY(view, view.getMeasuredHeight() * 0.5f);
			ViewHelper.setScaleX(view, 1);
		} else if (position <= 0) {
			ViewHelper.setPivotX(view, view.getMeasuredWidth());
			ViewHelper.setPivotY(view, 0);
			ViewHelper.setScaleX(view, 1 + position);
		} else if (position <= 1) {
			ViewHelper.setPivotX(view, 0);
			ViewHelper.setPivotY(view, 0);
			ViewHelper.setScaleX(view, 1 - position);
		} else {
			ViewHelper.setPivotX(view, view.getMeasuredWidth() * 0.5f);
			ViewHelper.setPivotY(view, view.getMeasuredHeight() * 0.5f);
			ViewHelper.setScaleX(view, 1);
		}
	}
}
