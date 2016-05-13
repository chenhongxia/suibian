package com.example.suishouji.dao;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.suishouji.base.BaseApplication;
import com.example.suishouji.utils.ContantSharedPreferendeUtil;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;


/**
 * @description: 数据库的创建和更新帮助类
 * @date: 2015-6-12 下午11:52:49
 * @author: yems
 */
public class IMDataBaseHelper extends OrmLiteSqliteOpenHelper
{
	private static final String TAG = "IMDataBaseHelper";
	private static final String DATABASE_NAME = "_green_one.db";
	private static final int DB_VERSION = 1;

	private Map<String, Dao> daos = new HashMap<String, Dao>();
	private static IMDataBaseHelper instance;

	private IMDataBaseHelper(Context context, String userName)
	{
		super(context, userName + DATABASE_NAME, null, DB_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource)
	{
	}

	@Override
	public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion)
	{
		onCreate(database, connectionSource);
	}

	/**
	 * 单例获取该Helper
	 * 
	 * @param context
	 * @return
	 */
	/**
	 * @param context
	 * @param userName
	 *            用户名（数据库前缀，用于区分不同用户的数据库）
	 * @return
	 * @description:
	 * @date: 2015-6-14 下午12:47:17
	 * @author: yems
	 */
	public static IMDataBaseHelper getHelper()
	{
		if (instance == null)
		{
			synchronized (TAG)
			{
				if (instance == null)
					instance = new IMDataBaseHelper(BaseApplication.getInstance().getContext(), ContantSharedPreferendeUtil.getUsernName(BaseApplication.getInstance().getContext()));
			}
		}
		return instance;
	}

	public synchronized Dao getDao(Class clazz) throws SQLException
	{
		Dao dao = null;
		String className = clazz.getSimpleName();

		if (daos.containsKey(className))
		{
			dao = daos.get(className);
		}
		if (dao == null)
		{
			dao = super.getDao(clazz);
			daos.put(className, dao);
		}
		return dao;
	}

	public void release()
	{
		daos.clear();
		instance = null;
	}
}
