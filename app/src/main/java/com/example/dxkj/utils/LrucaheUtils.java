package com.example.dxkj.utils;

import android.graphics.Bitmap;
import android.support.v4.util.LruCache;

public class LrucaheUtils {
	int maxMemory; // 需要的最大缓存空间
	private LruCache<String, Bitmap> mMemoryCahe; // 缓存
	private static LrucaheUtils lrucaheUtils;

	// 单例模式
	public static LrucaheUtils getInteance() {
		if (lrucaheUtils == null) {
			lrucaheUtils = new LrucaheUtils();
		}
		return lrucaheUtils;
	}

	// 构造方法
	private LrucaheUtils() {
		maxMemory = (int) (Runtime.getRuntime().maxMemory() / 1024); // 最大缓存空间
		if (mMemoryCahe == null) {
			mMemoryCahe = new LruCache<String, Bitmap>(maxMemory / 8) {

				@Override
				protected int sizeOf(String key, Bitmap value) {
					// 重写此方法重新衡量图片大小，默认返回图片数量
					return value.getRowBytes() * value.getHeight() / 1024;
				}

				@Override
				protected void entryRemoved(boolean evicted, String key,
						Bitmap oldValue, Bitmap newValue) {
					// TODO Auto-generated method stub
					super.entryRemoved(evicted, key, oldValue, newValue);
				}
			};
		}
	}

	// 将图片放入缓存
	public void putBitMap(String key, Bitmap bitmap) {
		if (mMemoryCahe.get(key) == null) {
			if (key != null && bitmap != null) {
				mMemoryCahe.put(key, bitmap);
			} else {
				System.out.println("图片已存在");
			}
		}
	}

	// 从缓存中获取图片
	public Bitmap getBitMap(String key) {
		Bitmap bitMap = mMemoryCahe.get(key);
		if (bitMap != null) {
			return bitMap;
		}
		return null;
	}

}
