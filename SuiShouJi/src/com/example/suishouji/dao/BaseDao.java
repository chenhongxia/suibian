package com.example.suishouji.dao;

import android.content.Context;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.DeleteBuilder;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.stmt.UpdateBuilder;

import java.sql.SQLException;
import java.util.List;

public abstract class BaseDao<T>
{
	private Dao<T, Integer> mDao;
	private IMDataBaseHelper mHelper;

	public BaseDao(Context context, Class<T> clazz)
	{
		try
		{
			mHelper = IMDataBaseHelper.getHelper();
			mDao = mHelper.getDao(clazz);
		} catch (SQLException e)
		{
			e.printStackTrace();
		}
	}

	public Dao<T, Integer> getDao()
	{
		return mDao;
	}

	public void setDao(Dao<T, Integer> mDao)
	{
		this.mDao = mDao;
	}

	public IMDataBaseHelper getHelper()
	{
		return mHelper;
	}

	public void setHelper(IMDataBaseHelper mHelper)
	{
		this.mHelper = mHelper;
	}

	/**
	 * @param bean
	 *            表对应的实体类
	 * @description: 添加数据到指定Bean对应的表中
	 * @date: 2015-6-13 下午8:46:03
	 * @author： yems
	 */
	public void add(T bean)
	{
		try
		{
			mDao.createOrUpdate(bean);
		} catch (SQLException e)
		{
			e.printStackTrace();
		}
	}

	public  List<T> quaryForAll(){
		try
		{
			return mDao.queryForAll();
		} catch (SQLException e)
		{
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * @param columnName
	 * @param value
	 * @description: 删除指定列的数据
	 * @date: 2015-6-16 下午2:07:57
	 * @author: yems
	 */
	public void deleteBuilder(final String columnName, final Object value)
	{
		try
		{
			DeleteBuilder<T, Integer> deleteBuilder = mDao.deleteBuilder();
			deleteBuilder.where().eq(columnName, value);
			deleteBuilder.delete();
		} catch (SQLException e)
		{
			e.printStackTrace();
		}
	}

	/**
	 * @param columnNameFirst
	 * @param valueFirst
	 * @param columnNameSecond
	 * @param valueSecond
	 * @description: 联合删除指定列
	 * @date: 2015-7-6 下午2:18:27
	 * @author: yems
	 */
	public void deleteBuilder(String columnNameFirst, Object valueFirst, String columnNameSecond, Object valueSecond)
	{
		try
		{
			DeleteBuilder<T, Integer> deleteBuilder = mDao.deleteBuilder();
			deleteBuilder.where().eq(columnNameFirst, valueFirst).and().eq(columnNameSecond, valueSecond);
			deleteBuilder.delete();
		} catch (SQLException e)
		{
			e.printStackTrace();
		}
	}

	/**
	 * @param columnName
	 * @param value
	 * @description: 更新指定列的数据
	 * @date: 2015-6-29 上午9:01:46
	 * @author: yems
	 */
	public void updateBuilder(String columnName, Object value)
	{
		try
		{
			UpdateBuilder<T, Integer> updateBuilder = mDao.updateBuilder();
			updateBuilder.where().eq(columnName, value);
			updateBuilder.update();
		} catch (SQLException e)
		{
			e.printStackTrace();
		}
	}

	/**
	 * @param columnName
	 * @param value
	 * @return
	 * @description: 查询指定列的值
	 * @date: 2015-6-29 上午9:08:46
	 * @author: yems
	 */
	public List<T> queryBuilder(String columnName, Object value)
	{
		try
		{
			QueryBuilder<T, Integer> queryBuilder = mDao.queryBuilder();
			queryBuilder.where().eq(columnName, value);
			return queryBuilder.query();
		} catch (SQLException e)
		{
			e.printStackTrace();
		}
		return null;
	}
	
	public List<T> queryBuilder(String columnName,Object value,String groupBy){
		try
		{
			QueryBuilder<T, Integer> queryBuilder = mDao.queryBuilder().groupBy(groupBy);
			queryBuilder.where().eq(columnName, value);
			return queryBuilder.query();
		} catch (SQLException e)
		{
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * @param columnName
	 * @param value
	 * @return
	 * @description: 模糊搜索
	 * @date: 2015-8-17 下午2:30:00
	 * @author: yems
	 */
	public List<T> fuzzyQueryBuilder(String columnName, Object value)
	{
		StringBuilder sb = new StringBuilder();
		sb.append("%").append(value).append("%");
		try
		{
			QueryBuilder<T, Integer> queryBuilder = mDao.queryBuilder();
			queryBuilder.where().like(columnName, sb.toString());
			return queryBuilder.query();
		} catch (SQLException e)
		{
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * @param firstColumnName
	 * @param firstValue
	 * @param secondColumnName
	 * @param secondValue
	 * @return
	 * @description: 模糊搜索指定两中类型的数据
	 * @date: 2015-9-24 上午11:13:27
	 * @author: yems
	 */
	public List<T> fuzzyQueryBuilders(String firstColumnName, String secondColumnName, Object searchValue)
	{
		StringBuilder sb = new StringBuilder();
		sb.append("%").append(searchValue).append("%");
		try
		{
			QueryBuilder<T, Integer> queryBuilder = mDao.queryBuilder();
			queryBuilder.where().like(firstColumnName, sb.toString()).or().like(secondColumnName, sb.toString());
			return queryBuilder.query();
		} catch (SQLException e)
		{
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * @param columnNameFirst
	 * @param valueFirst
	 * @param columnNameSecond
	 * @param valueSecond
	 * @return
	 * @description: 查询两个指定条件列的值
	 * @date: 2015-7-9 上午9:43:16
	 * @author: yems
	 */
	public List<T> queryBuilder(String columnNameFirst, Object valueFirst, String columnNameSecond, Object valueSecond)
	{
		try
		{
			QueryBuilder<T, Integer> queryBuilder = mDao.queryBuilder();
			queryBuilder.where().eq(columnNameFirst, valueFirst).and().eq(columnNameSecond, valueSecond);
			return queryBuilder.query();
		} catch (SQLException e)
		{
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 
	 * @description: 关闭数据库
	 * @date: 2015-6-16 下午3:46:25
	 * @author: yems
	 */
	public void closeDb()
	{
		mHelper.close();
	}

	/**
	 * 
	 * @param updateCol
	 *            需更新的字段名
	 * @param whereCol
	 *            条件字段名
	 * @param updateValue
	 *            更新值
	 * @param whereValue
	 *            条件值
	 * @description: 修改指定列的值
	 * @date: 2015-6-24 下午2:09:41
	 * @author： huangqp
	 */
	public void updateBuilder(String updateCol, String whereCol, Object updateValue, Object whereValue)
	{
		try
		{
			UpdateBuilder<T, Integer> updateBuilder = mDao.updateBuilder();
			updateBuilder.updateColumnValue(updateCol, updateValue).where().eq(whereCol, whereValue);
			updateBuilder.update();
		} catch (SQLException e)
		{
			e.printStackTrace();
		}
	}

	public void updateBuilder(String updateCol, Object updateValue, String firstWhereCol, Object firstWhereValue, String secondWhereCol, Object secondWhereValue)
	{
		try
		{
			UpdateBuilder<T, Integer> updateBuilder = mDao.updateBuilder();
			updateBuilder.updateColumnValue(updateCol, updateValue).where().eq(firstWhereCol, firstWhereValue).and().eq(secondWhereCol, secondWhereValue);
			updateBuilder.update();
		} catch (SQLException e)
		{
			e.printStackTrace();
		}
	}
}
