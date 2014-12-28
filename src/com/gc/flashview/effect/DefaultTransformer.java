package com.gc.flashview.effect;

import android.support.v4.view.ViewPager.PageTransformer;
import android.view.View;

public class DefaultTransformer implements PageTransformer {

	@Override
	public void transformPage(View view, float arg1) {
		view.setAlpha(1);
		view.setTranslationX(0);
		view.setTranslationY(0);
		view.setPivotX(view.getWidth() / 2);
		view.setPivotY(view.getHeight() / 2);
		view.setScaleX(1);
		view.setScaleY(1);
		view.setRotation(0);
	}

}
