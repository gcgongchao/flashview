package com.gc.flashview.listener;

import android.view.View;
/**
 * 功能：向外提供点击事件的接口，其中position代表的是图片的索引，即第几张图片
 * @author Android将军
 *
 */
public interface FlashViewListener 
{
	public void onClick(int position); 
}
