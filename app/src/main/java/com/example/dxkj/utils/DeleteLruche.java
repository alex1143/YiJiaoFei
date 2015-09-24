package com.example.dxkj.utils;

import java.io.File;

/**
 * 清空缓存
 * @author 赫明阳
 *
 */
public class DeleteLruche {

	public static void deleteLruche(File dirtory) {
		if (dirtory != null && dirtory.exists() && dirtory.isDirectory()) {
			for (File item : dirtory.listFiles()) {
				item.delete();
			}
		}
	}
}
