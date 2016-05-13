package com.example.suishouji.base.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.Bitmap;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.util.Base64;

/**
 * 操作SharedPreference的工具类，关注保存和读取
 * 
 * @author Dave
 * 
 */
public class SharedPreferenceUtil {

	public static int MODE_READABLE_WRITEABLE = Context.MODE_WORLD_READABLE
			+ Context.MODE_WORLD_WRITEABLE;
	private static final String mDataBasesName = "sp_object";

	/**
	 * 根据文件名和访问模式获取对应的SharedPreferences
	 * 
	 * @param context
	 *            上下文
	 * @param fileName
	 *            文件名
	 * @param mode
	 *            访问模式
	 * @return
	 */
	public static SharedPreferences getSharedPreferences(Context context,
			String fileName, String key, int mode) {
		if (TextUtils.isEmpty(fileName)) {
			fileName = key;
		}
		if (fileName == null) {
			return PreferenceManager.getDefaultSharedPreferences(context);
		} else {
			return context.getSharedPreferences(fileName, mode);
		}
	}

	/**
	 * 根据文件名和访问模式获取的SharedPreference中key对应的String类型值
	 * ,如果文件名为空,则以从默认的SharedPreference获取,如果没有提供访问模式
	 * ,则以默认的Context.Mode_Private方式访问
	 * 
	 * @param context
	 * @param fileName
	 * @param key
	 * @param defaultValue
	 * @param mode
	 * @return
	 */
	public static String getString(Context context, String fileName,
			String key, String defaultValue, int mode) {

		boolean contailByDefault = containByNew(context, fileName, key);
		if (!contailByDefault) {
			SharedPreferences sharedPreferences = getDefaultSharedPreferences(
					context, fileName, key, mode);
			return sharedPreferences.getString(key, defaultValue);
		}

		SharedPreferences pre = getSharedPreferences(context, fileName, key,
				mode);
		String values = pre.getString(key, defaultValue);
		return values;
	}

	/**
	 * @see {@link SharedPreferenceUtil#getString(Context, String, String, String, int)}
	 */
	public static String getString(Context context, String fileName,
			String key, String defaultValue) {
		return getString(context, fileName, key, defaultValue, 0);
	}

	/**
	 * @see {@link #getString(Context, String, String, String, int)}
	 */
	public static String getString(Context context, String key,
			String defaultValue, int mode) {
		return getString(context, null, key, defaultValue, mode);
	}

	/**
	 * @see {@link #getString(Context, String, String, String, int)}
	 */
	public static String getString(Context context, String key,
			String defaultValue) {
		return getString(context, null, key, defaultValue, 0);
	}

	/**
	 * 根据文件名和访问模式获取的SharedPreference中key对应的boolean类型值
	 * ,如果文件名为空,则以从默认的SharedPreference获取,如果没有提供访问模式
	 * ,则以默认的Context.Mode_Private方式访问
	 * 
	 * @param context
	 * @param fileName
	 * @param key
	 * @param defaultValue
	 * @param mode
	 * @return
	 */
	public static boolean getBoolean(Context context, String fileName,
			String key, boolean defaultValue, int mode) {

		boolean contailByDefault = containByNew(context, fileName, key);
		if (!contailByDefault) {
			SharedPreferences sharedPreferences = getDefaultSharedPreferences(
					context, fileName, key, mode);
			return sharedPreferences.getBoolean(key, defaultValue);
		}

		SharedPreferences pre = getSharedPreferences(context, fileName, key,
				mode);
		boolean values = pre.getBoolean(key, defaultValue);
		return values;
	}

	/**
	 * @see {@link #getBoolean(Context, String, String, boolean, int)}
	 */
	public static boolean getBoolean(Context context, String fileName,
			String key, boolean defaultValue) {
		return getBoolean(context, fileName, key, defaultValue, 0);
	}

	/**
	 * @see {@link #getBoolean(Context, String, String, boolean, int)}
	 */
	public static boolean getBoolean(Context context, String key,
			boolean defaultValue, int mode) {
		return getBoolean(context, null, key, defaultValue, mode);
	}

	/**
	 * @see {@link #getBoolean(Context, String, String, boolean, int)}
	 */
	public static boolean getBoolean(Context context, String key,
			boolean defaultValue) {
		return getBoolean(context, null, key, defaultValue, 0);
	}

	/**
	 * 根据文件名和访问模式获取的SharedPreference中key对应的long类型值
	 * ,如果文件名为空,则以从默认的SharedPreference获取,如果没有提供访问模式
	 * ,则以默认的Context.Mode_Private方式访问
	 * 
	 * @param context
	 * @param fileName
	 * @param key
	 * @param defaultValue
	 * @param mode
	 * @return
	 */
	public static long getLong(Context context, String fileName, String key,
			long defaultValue, int mode) {

		boolean contailByDefault = containByNew(context, fileName, key);
		if (!contailByDefault) {
			SharedPreferences sharedPreferences = getDefaultSharedPreferences(
					context, fileName, key, mode);
			return sharedPreferences.getLong(key, defaultValue);
		}

		SharedPreferences pre = getSharedPreferences(context, fileName, key,
				mode);
		long values = pre.getLong(key, defaultValue);
		return values;
	}

	/**
	 * @see #getLong(Context, String, String, long, int)
	 */
	public static long getLong(Context context, String fileName, String key,
			long defaultValue) {
		return getLong(context, fileName, key, defaultValue, 0);
	}

	/**
	 * @see #getLong(Context, String, String, long, int)
	 */
	public static long getLong(Context context, String key, long defaultValue,
			int mode) {
		return getLong(context, null, key, defaultValue, mode);
	}

	/**
	 * @see #getLong(Context, String, String, long, int)
	 */
	public static long getLong(Context context, String key, long defaultValue) {
		return getLong(context, null, key, defaultValue, 0);
	}

	/**
	 * 根据文件名和访问模式获取的SharedPreference中key对应的int类型值
	 * ,如果文件名为空,则以从默认的SharedPreference获取,如果没有提供访问模式
	 * ,则以默认的Context.Mode_Private方式访问
	 * 
	 * @param context
	 * @param fileName
	 * @param key
	 * @param defaultValue
	 * @param mode
	 * @return
	 */
	public static int getInt(Context context, String fileName, String key,
			int defaultValue, int mode) {

		boolean contailByDefault = containByNew(context, fileName, key);
		if (!contailByDefault) {
			SharedPreferences sharedPreferences = getDefaultSharedPreferences(
					context, fileName, key, mode);
			return sharedPreferences.getInt(key, defaultValue);
		}

		SharedPreferences pre = getSharedPreferences(context, fileName, key,
				mode);
		int values = pre.getInt(key, defaultValue);
		return values;
	}

	/**
	 * @see {@link #getInt(Context, String, String, int, int)}
	 */
	public static int getInt(Context context, String fileName, String key,
			int defaultValue) {
		return getInt(context, fileName, key, defaultValue, 0);
	}

	/**
	 * @see {@link #getInt(Context, String, String, int, int)}
	 */
	public static int getInt(Context context, String key, int defaultValue,
			int mode) {
		return getInt(context, null, key, defaultValue, mode);
	}

	/**
	 * @see {@link SharedPreferenceUtil#getInt(Context, String, String, int, int)}
	 */
	public static int getInt(Context context, String key, int defaultValue) {
		return getInt(context, null, key, defaultValue, 0);
	}

	/**
	 * 根据文件名和访问模式获取的SharedPreference中key对应的float类型值
	 * ,如果文件名为空,则以从默认的SharedPreference获取,如果没有提供访问模式
	 * ,则以默认的Context.Mode_Private方式访问
	 * 
	 * @param context
	 * @param fileName
	 * @param key
	 * @param defaultValue
	 * @param mode
	 * @return
	 */
	public static float getFloat(Context context, String fileName, String key,
			float defaultValue, int mode) {

		boolean contailByDefault = containByNew(context, fileName, key);
		if (!contailByDefault) {
			SharedPreferences sharedPreferences = getDefaultSharedPreferences(
					context, fileName, key, mode);
			return sharedPreferences.getFloat(key, defaultValue);
		}

		SharedPreferences pre = getSharedPreferences(context, fileName, key,
				mode);
		float values = pre.getFloat(key, defaultValue);
		return values;
	}

	/**
	 * @see {@link #getFloat(Context, String, String, float, int)}
	 */
	public static float getFloat(Context context, String fileName, String key,
			float defaultValue) {
		return getFloat(context, fileName, key, defaultValue, 0);
	}

	/**
	 * @see {@link #getFloat(Context, String, String, float, int)}
	 */
	public static float getFloat(Context context, String key,
			float defaultValue, int mode) {
		return getFloat(context, null, key, defaultValue, mode);
	}

	/**
	 * @see {@link #getFloat(Context, String, String, float, int)}
	 */
	public static float getFloat(Context context, String key, float defaultValue) {
		return getFloat(context, null, key, defaultValue, 0);
	}

	// /**
	// * 根据文件名和访问模式返回对应SharedPreference中所有的以Map映射的键值
	// *
	// ，如果没有提供文件名，则以默认的SharedPreference获取，如果没有提供访问方式，则使用默认的Context.Mode_private
	// *
	// * @param context
	// * @param fileName
	// * @param mode
	// * @return
	// */
	// public static Map<String, ?> getAll(Context context, String fileName,
	// int mode) {
	// SharedPreferences pre = getSharedPreferences(context, fileName, null,
	// mode);
	// return pre.getAll();
	// }
	//
	// /**
	// * @see #getAll(Context, String, int)
	// */
	// public static Map<String, ?> getAll(Context context, String fileName) {
	// return getAll(context, fileName, 0);
	// }
	//
	// /**
	// * @see #getAll(Context, String, int)
	// */
	// public static Map<String, ?> getAll(Context context, int mode) {
	// return getAll(context, null, mode);
	// }
	//
	// /**
	// * @see SharedPreferenceUtil#getAll(Context, String, int)
	// */
	// public static Map<String, ?> getAll(Context context) {
	// return getAll(context, null, 0);
	// }

	/**
	 * 保存String类型的值到指定的文件名和指定key的SharedPreference
	 * 如果没用提供文件名，则保存到默认的SharedPreference,如果没有提供访问模式，则使用默认的Mode_Private
	 * 
	 * @param context
	 *            上下文
	 * @param fileName
	 *            文件名
	 * @param key
	 *            保存value的键
	 * @param value
	 *            需要保存的值
	 * @param mode
	 *            访问模式
	 */
	public static void setStringValue(Context context, String fileName,
			String key, String value, int mode) {
		SharedPreferences.Editor editor = getSharedPreferences(context,
				fileName, key, mode).edit();
		editor.putString(key, value);
		editor.commit();
	}

	/**
	 * @see #getString(Context, String, String, String, int)
	 */
	public static void setStringValue(Context context, String fileName,
			String key, String value) {
		setStringValue(context, fileName, key, value, 0);
	}

	/**
	 * @see #getString(Context, String, String, String, int)
	 */
	public static void setStringValue(Context context, String key,
			String value, int mode) {
		setStringValue(context, null, key, value, mode);
	}

	/**
	 * @see #getString(Context, String, String, String, int)
	 */
	public static void setStringValue(Context context, String key, String value) {
		setStringValue(context, null, key, value, 0);
	}

	/**
	 * 保存boolean类型的值到指定的文件名和指定key的SharedPreference
	 * 如果没用提供文件名，则保存到默认的SharedPreference,如果没有提供访问模式，则使用默认的Mode_Private
	 * 
	 * @param context
	 *            上下文
	 * @param fileName
	 *            文件名
	 * @param key
	 *            保存value的键
	 * @param value
	 *            需要保存的值
	 * @param mode
	 *            访问模式
	 */
	public static void setBooleanValue(Context context, String fileName,
			String key, boolean value, int mode) {
		SharedPreferences.Editor editor = getSharedPreferences(context,
				fileName, key, 0).edit();
		editor.putBoolean(key, value);
		editor.commit();
	}

	/**
	 * @see #setBooleanValue(Context, String, String, boolean, int)
	 */
	public static void setBooleanValue(Context context, String fileName,
			String key, boolean value) {
		setBooleanValue(context, fileName, key, value, 0);
	}

	/**
	 * @see #setBooleanValue(Context, String, String, boolean, int)
	 */
	public static void setBooleanValue(Context context, String key,
			boolean value, int mode) {
		setBooleanValue(context, null, key, value, mode);
	}

	/**
	 * @see #setBooleanValue(Context, String, String, boolean, int)
	 */
	public static void setBooleanValue(Context context, String key,
			boolean value) {
		setBooleanValue(context, null, key, value, 0);
	}

	/**
	 * 保存long类型的值到指定的文件名和指定key的SharedPreference
	 * 如果没用提供文件名，则保存到默认的SharedPreference,如果没有提供访问模式，则使用默认的Mode_Private
	 * 
	 * @param context
	 *            上下文
	 * @param fileName
	 *            文件名
	 * @param key
	 *            保存value的键
	 * @param value
	 *            需要保存的值
	 * @param mode
	 *            访问模式
	 */
	public static void setLongValue(Context context, String fileName,
			String key, long value, int mode) {
		SharedPreferences.Editor editor = getSharedPreferences(context,
				fileName, key, mode).edit();
		editor.putLong(key, value);
		editor.commit();
	}

	/**
	 * @see #setLongValue(Context, String, String, long, int)
	 */
	public static void setLongValue(Context context, String fileName,
			String key, long value) {
		setLongValue(context, fileName, key, value, 0);
	}

	/**
	 * @see #setLongValue(Context, String, String, long, int)
	 */
	public static void setLongValue(Context context, String key, long value,
			int mode) {
		setLongValue(context, null, key, value, mode);
	}

	/**
	 * @see #setLongValue(Context, String, String, long, int)
	 */
	public static void setLongValue(Context context, String key, long value) {
		setLongValue(context, null, key, value, 0);
	}

	/**
	 * 保存int类型的值到指定的文件名和指定key的SharedPreference
	 * 如果没用提供文件名，则保存到默认的SharedPreference,如果没有提供访问模式，则使用默认的Mode_Private
	 * 
	 * @param context
	 *            上下文
	 * @param fileName
	 *            文件名
	 * @param key
	 *            保存value的键
	 * @param value
	 *            需要保存的值
	 * @param mode
	 *            访问模式
	 */
	public static void setIntValue(Context context, String fileName,
			String key, int value, int mode) {
		SharedPreferences.Editor editor = getSharedPreferences(context,
				fileName, key, mode).edit();
		editor.putInt(key, value);
		editor.commit();
	}

	/**
	 * @see #setIntValue(Context, String, String, int, int)
	 */
	public static void setIntValue(Context context, String fileName,
			String key, int value) {
		setIntValue(context, fileName, key, value, 0);
	}

	/**
	 * @see #setIntValue(Context, String, String, int, int)
	 */
	public static void setIntValue(Context context, String key, int value,
			int mode) {
		setIntValue(context, null, key, value, mode);
	}

	/**
	 * @see #setIntValue(Context, String, String, int, int)
	 */
	public static void setIntValue(Context context, String key, int value) {
		setIntValue(context, null, key, value, 0);
	}

	/**
	 * 保存float类型的值到指定的文件名和指定key的SharedPreference
	 * 如果没用提供文件名，则保存到默认的SharedPreference,如果没有提供访问模式，则使用默认的Mode_Private
	 * 
	 * @param context
	 *            上下文
	 * @param fileName
	 *            文件名
	 * @param key
	 *            保存value的键
	 * @param value
	 *            需要保存的值
	 * @param mode
	 *            访问模式
	 */
	public static void setFloatValue(Context context, String fileName,
			String key, float value, int mode) {
		SharedPreferences.Editor editor = getSharedPreferences(context,
				fileName, key, mode).edit();
		editor.putFloat(key, value);
		editor.commit();
	}

	/**
	 * @see SharedPreferenceUtil#setFloatValue(Context, String, String, float,
	 *      int)
	 */
	public static void setFloatValue(Context context, String fileName,
			String key, float value) {
		setFloatValue(context, fileName, key, value, 0);
	}

	/**
	 * @see #setFloatValue(Context, String, String, float, int)
	 */
	public static void setFloatValue(Context context, String key, float value,
			int mode) {
		setFloatValue(context, null, key, value, mode);
	}

	/**
	 * @see #setFloatValue(Context, String, String, float, int)
	 */
	public static void setFloatValue(Context context, String key, float value) {
		setFloatValue(context, null, key, value, 0);
	}

	public static void setObject(Context ctx, String ShpfKey, Object object) {
		SharedPreferences preferences = ctx.getSharedPreferences(
				mDataBasesName, Context.MODE_PRIVATE);
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		String oAuth_Base64 = null;
		try {
			ObjectOutputStream oos = new ObjectOutputStream(baos);
			if (object instanceof Bitmap) {
				ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
				((Bitmap) object).compress(Bitmap.CompressFormat.PNG, 100,
						outputStream);
				oAuth_Base64 = new String(Base64.encode(
						outputStream.toByteArray(), Base64.DEFAULT));
			} else {
				oos.writeObject(object);
				oAuth_Base64 = new String(Base64.encode(baos.toByteArray(),
						Base64.DEFAULT));
			}

			Editor editor = preferences.edit();
			editor.putString(ShpfKey, oAuth_Base64);
			editor.commit();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static <T> T getObject(Context ctx, String ShpfKey) {
		Object object = null;
		SharedPreferences preferences = ctx.getSharedPreferences(
				mDataBasesName, Context.MODE_PRIVATE);
		String string = preferences.getString(ShpfKey, "");
		if (string == "") {
			return null;
		}

		byte[] base64 = Base64.decode(string.getBytes(), Base64.DEFAULT);
		ByteArrayInputStream bais = new ByteArrayInputStream(base64);
		try {
			ObjectInputStream bis = new ObjectInputStream(bais);
			object = bis.readObject();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return (T) object;
	}

	// /**
	// * 保存以Map映射的所有的键值对到指定文件名的SharedPreference,如果没有提供文件名则使用默认的SharedPreference
	// * ,如果没有提供访问模式,则使用默认的Mode_Private
	// * <p>
	// * Map中的值类型只支持
	// *
	// <code>int(Integer),long(Long),float(Float),boolean(Boolean),String</code>
	// * 类型
	// *
	// * @param context
	// * 上下文
	// * @param all
	// * 映射
	// * @param fileName
	// * 文件名
	// * @param mode
	// * 访问模式
	// * @throws InvalidMemoryTypeException
	// */
	// public static void setAll(Context context, Map<String, ?> all,
	// String fileName, int mode) throws InvalidMemoryTypeException {
	// SharedPreferences.Editor editor = getSharedPreferences(context,
	// fileName, null, mode).edit();
	// Set<String> keySet = all.keySet();
	// Iterator<String> keyIterator = keySet.iterator();
	// String key;
	// Object value;
	// while (keyIterator.hasNext()) {
	// key = keyIterator.next();
	// value = all.get(key);
	// if (value instanceof Boolean) {
	// editor.putBoolean(key, ((Boolean) value).booleanValue());
	// } else if (value instanceof Float) {
	// editor.putFloat(key, ((Float) value).floatValue());
	// } else if (value instanceof Long) {
	// editor.putLong(key, ((Long) value).longValue());
	// } else if (value instanceof Integer) {
	// editor.putInt(key, ((Integer) value).intValue());
	// } else if (value instanceof String) {
	// editor.putString(key, (String) value);
	// } else {
	// throw new InvalidMemoryTypeException("非法类型异常");
	// }
	// }
	// editor.commit();
	// }
	//
	// /**
	// * @throws InvalidMemoryTypeException
	// * @see #setAll(Context, Map, String, int)
	// */
	// public static void setAll(Context context, Map<String, ?> all,
	// String fileName) throws InvalidMemoryTypeException {
	// setAll(context, all, fileName, 0);
	// }
	//
	// /**
	// * @throws InvalidMemoryTypeException
	// * @see #setAll(Context, Map, String, int)
	// */
	// public static void setAll(Context context, Map<String, ?> all, int mode)
	// throws InvalidMemoryTypeException {
	// setAll(context, all, null, mode);
	// }
	//
	// /**
	// * @throws InvalidMemoryTypeException
	// * @see #setAll(Context, Map, String, int)
	// */
	// public static void setAll(Context context, Map<String, ?> all)
	// throws InvalidMemoryTypeException {
	// setAll(context, all, null, 0);
	// }

	/**
	 * 查询对应的SharedPreference文件中是否包含key对应的映射
	 * <p>
	 * 如果没有提供文件名则使用默认的SharedPreference,如果没有提供访问模式,则使用默认的Mode_Private
	 * 
	 * @param context
	 *            上下文
	 * @param fileName
	 *            文件名
	 * @param key
	 *            键
	 * @param mode
	 *            访问模式
	 * @return
	 */
	public static boolean contain(Context context, String fileName, String key,
			int mode) {
		File file = getSharedPreferenceItem(context, key);

		return file != null;
	}

	/**
	 * see {@link #contain(Context, String, String, int)}
	 */
	public static boolean contain(Context context, String key, int mode) {
		File file = getSharedPreferenceItem(context, key);

		return file != null;
	}

	/**
	 * see {@link #contain(Context, String, String, int)}
	 */
	public static boolean contain(Context context, String fileName, String key) {
		File file = getSharedPreferenceItem(context, key);

		return file != null;
	}

	/**
	 * see {@link #contain(Context, String, String, int)}
	 */
	public static boolean contain(Context context, String key) {
		File file = getSharedPreferenceItem(context, key);

		return file != null;
	}

	/**
	 * 移除对应的SharedPreference文件中包含key对应的映射
	 * 
	 * @param context
	 * @param fileName
	 * @param key
	 * @param mode
	 */
	public static void remove(Context context, String fileName, String key,
			int mode) {
		File file = getSharedPreferenceItem(context, key);
		if (file != null) {
			file.delete();
		}
	}

	/**
	 * see {@link #remove(Context, String, String, int)}
	 */
	public static void remove(Context context, String fileName, String key) {
		File file = getSharedPreferenceItem(context, key);
		if (file != null) {
			file.delete();
		}
	}

	/**
	 * see {@link #remove(Context, String, String, int)}
	 */
	public static void remove(Context context, String key) {
		File file = getSharedPreferenceItem(context, key);
		if (file != null) {
			file.delete();
		}
	}

	/**
	 * 清空指定SP文件名的内容
	 * 
	 * @param context
	 * @param fileName
	 */
	public static void removeAll(Context context, String fileName) {
		try {

			File file = getSharedPreferenceDir(context);
			if (file == null) {
				return;
			}
			File[] files = file.listFiles();
			for (File item : files) {
				item.delete();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static File getSharedPreferenceItem(Context context, String filename) {
		try {

			File file = getSharedPreferenceDir(context);
			if (file == null) {
				return null;
			}
			File item = new File(file, filename + ".xml");
			if (item.exists()) {
				return item;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static File getSharedPreferenceDir(Context context) {
		try {

			File file = new File(context.getFilesDir().getParent(),
					"shared_prefs");
			if (file.exists()) {
				return file;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	private static boolean containByNew(Context context, String fileName,
			String key) {
		boolean containNew = contain(context, fileName, key);
		return containNew;
	}

	private static SharedPreferences getDefaultSharedPreferences(
			Context context, String fileName, String key, int mode) {
		if (fileName == null) {
			return PreferenceManager.getDefaultSharedPreferences(context);
		} else {
			return context.getSharedPreferences(fileName, mode);
		}
	}
}
