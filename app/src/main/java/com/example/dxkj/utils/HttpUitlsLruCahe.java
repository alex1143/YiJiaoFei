package com.example.dxkj.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.InputStream;

public class HttpUitlsLruCahe {

	// 加载图片
	public static Bitmap loadImage(Context context, String url) {
		Bitmap bitmap = null;
		LrucaheUtils lrucaheUtils = LrucaheUtils.getInteance();
		// 1.从缓存中加载图片
		if (lrucaheUtils != null) {
			System.out.println("从缓存中搜索图片");
			bitmap = lrucaheUtils.getBitMap(url);
			if (bitmap != null) {
				return bitmap;
			}
		}
		// 2.从磁盘中加载图片
		InputStream is = DiskLruCacheUtil.readFromDiskCache(url, context);
		if (is != null) {
			System.out.println("从磁盘中搜索图片");
			bitmap = BitmapFactory.decodeStream(is);
			return bitmap;
		}
		// 3.从网络获取图片
		byte[] data = HttpUtils.getUtils(url);
		if (data != null) {
			bitmap = BitmapFactory.decodeByteArray(data, 0, data.length);
			// 向缓存中写入图片
			lrucaheUtils.putBitMap(url, bitmap);
			// 想磁盘内存写入图片
			DiskLruCacheUtil.writeToDiskCache(url, data, context);
			return bitmap;
		}
		return null;
	}
}
