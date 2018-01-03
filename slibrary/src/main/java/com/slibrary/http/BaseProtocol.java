package com.slibrary.http;

import android.content.Context;
import android.text.TextUtils;

import com.slibrary.utils.IOUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * 访问网络的基类
 * 
 * @author Kevin
 * 
 */
public abstract class BaseProtocol<T> {
	private Context mContext;

	public BaseProtocol(Context context) {
		mContext = context;
	}

	/**
	 * 获取数据
	 * 
	 * @param index
	 *            分页请求数据的起始位置
	 */
	public T getData(int index) {
		// 先从本地缓存中读取数据,如果有,就直接返回,如果没有,才从网络加载
		String result = getCache(index);
		if (result == null) {
			result = getDataFromNet(index);
		}

		if (result != null) {
			return parseJson(result);
		}

		return null;
	}

	/**
	 * 从本地缓存中读取数据
	 */
	private String getCache(int index) {
		// 获取系统缓存目录
		File cacheDir = mContext.getExternalCacheDir();
		// 以网络链接作为文件名称,保证特定接口对应特定数据
		String name;
		if (index != -1) {
			if (!TextUtils.isEmpty(getParams())) {
				name = getKey() + "?index=" + index + "&" + getParams();
			} else {
				name = getKey() + "?index=" + index;
			}
		} else {
			if (!TextUtils.isEmpty(getParams())) {
				name = getKey() + "?" + getParams();
			} else {
				name = getKey();
			}
		}
		File cacheFile = new File(cacheDir, name);

		if (cacheFile.exists()) {// 缓存文件存在
			BufferedReader reader = null;
			try {
				reader = new BufferedReader(new FileReader(cacheFile));
				String validTime = reader.readLine();// 读取第一行内容,缓存截止时间
				long currentTime = System.currentTimeMillis();
				if (currentTime < Long.parseLong(validTime)) {// 当前时间小于缓存截止时间,说明缓存还在有效期范围内

					String line = null;
					StringBuffer sb = new StringBuffer();
					while ((line = reader.readLine()) != null) {
						sb.append(line);
					}

					return sb.toString();
				}

			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				IOUtils.close(reader);
			}
		}

		return null;
	}

	/**
	 * 向本地缓存写数据
	 */
	private void setCache(String result, int index) {
		// 获取系统缓存目录
		File cacheDir = mContext.getExternalCacheDir();
		// 以网络链接作为文件名称,保证特定接口对应特定数据
		String name;
		if (index != -1) {
			if (!TextUtils.isEmpty(getParams())) {
				name = getKey() + "?index=" + index + "&" + getParams();
			} else {
				name = getKey() + "?index=" + index;
			}
		} else {
			if (!TextUtils.isEmpty(getParams())) {
				name = getKey() + "?" + getParams();
			} else {
				name = getKey();
			}
		}
		File cacheFile = new File(cacheDir, name);

		FileWriter writer = null;
		try {
			writer = new FileWriter(cacheFile);

			// 缓存有效期限, 截止时间设定为半小时之后
			long validTime = System.currentTimeMillis() + 30 * 60 * 1000;
			writer.write(validTime + "\n");// 将缓存截止时间写入文件第一行
			writer.write(result);
			writer.flush();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			IOUtils.close(writer);
		}
	}

	/**
	 * 访问网络获取数据
	 * 
	 * @param index
	 *            分页请求数据的起始位置
	 * @return
	 */
	private String getDataFromNet(int index) {
		// TODO: 2018/1/2 实现从网络加载数据
		return null;
	}

	// 获取网络接口的具体地址,每个页面都不一样,必须由子类实现
	public abstract String getKey();

	// 获取网络接口的具体参数,每个页面都不一样,必须由子类实现
	public abstract String getParams();

	/**
	 * 解析json数据 ,每个页面要求的解析对象都不一样,必须由子类实现
	 * 
	 * @param result
	 */
	public abstract T parseJson(String result);

}
