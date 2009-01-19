/**
 * Copyright (C) 2009 Michael A. MacDonald
 */
package com.antlersoft.android.dbimpl;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

/**
 * Specialization of ImplementationBase for tables that have an integer
 * primary key named _Id; this allows easy general purpose save and update
 * 
 * @author Michael A. MacDonald
 *
 */
public abstract class IdImplementationBase extends ImplementationBase {
	public abstract long get_Id();
	public abstract void set_Id(long id);
	
	/**
	 * Return the same ContentValues object with _ID field removed
	 * @param cv
	 * @return
	 */
	private static ContentValues removeId(ContentValues cv) {
		cv.remove("_ID");
		return cv;
	}
	
	public boolean Gen_insert(SQLiteDatabase db) {
		long id=db.insert(Gen_tableName(),null,removeId(Gen_getValues()));
		if (id!= -1)
		{
			set_Id(id);
			return true;
		}
		return false;
	}
	public int Gen_update(SQLiteDatabase db) {
		return db.update(Gen_tableName(), removeId(Gen_getValues()), "_ID = ?", new String[] { Long.toString(get_Id()) });
	}
}
