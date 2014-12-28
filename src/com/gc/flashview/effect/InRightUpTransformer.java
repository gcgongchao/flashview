package com.gc.flashview.effect;

import android.support.v4.view.ViewPager.PageTransformer;
import android.view.View;

public class InRightUpTransformer implements PageTransformer {

	@Override
	public void transformPage(View view, float position) {
		int pageHeight = view.getHeight();
		if (position < -1) {
			view.setAlpha(1);
			view.setTranslationY(0);
		} else if (position <= 0) {
			view.setTranslationY(pageHeight * -position);
			view.setAlpha(1 + position);

			// Android 3.1以下版本的用下面方法；
			// ViewHelper.setTranslationY(view, pageHeight * -position);
			// ViewHelper.setAlpha(view, 1 + position);
		} else if (position <= 1) {
			view.setTranslationY(view.getHeight() * -position);
			view.setAlpha(1 - position);

			// Android 3.1以下版本的用下面方法；
			// ViewHelper.setTranslationY(view, pageHeight * -position);
			// ViewHelper.setAlpha(view, 1 - position);
		} else {
			view.setTranslationY(0);
			view.setAlpha(1);
		}
	}

}
