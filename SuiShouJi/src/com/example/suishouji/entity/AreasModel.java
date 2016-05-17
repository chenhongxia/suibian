/**
 * 
 */
package com.example.suishouji.entity;

import java.io.Serializable;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * @description:
 * @date: 2015-7-1 下午7:33:35
 * @author: huangqp
 */
@DatabaseTable(tableName = "areas")
public class AreasModel implements Serializable
{	
	@DatabaseField(generatedId = true)
	private int id;
	@DatabaseField(columnName = "parent_id")
	private int parentId;
	@DatabaseField(columnName = "area_name")
	private String name;
	@DatabaseField(columnName = "postCode")
	private String postCode;
	@DatabaseField(columnName = "level")
	private int level;

	public AreasModel(int id, int pid, String name) {
		this.id = id;
		this.parentId = pid;
		this.name = name;
	}

	public int getParentId()
	{
		return parentId;
	}

	public void setParentId(int parentId)
	{
		this.parentId = parentId;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getPostCode()
	{
		return postCode;
	}

	public void setPostCode(String postCode)
	{
		this.postCode = postCode;
	}

	public int getLevel()
	{
		return level;
	}

	public void setLevel(int level)
	{
		this.level = level;
	}

	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}
	
}
