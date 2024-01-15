package hr.algebra.dogapp.dao

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

private const val DB_NAME = "dogDb.db"
private const val DB_VERSION = 1
private const val TABLE_NAME = "dogItems"
private const val CREATE_TABLE = "CREATE TABLE $TABLE_NAME (" +
        "_id TEXT PRIMARY KEY," +
        "message TEXT," +
        "status TEXT" +
        ")"

private const val DROP_TABLE = "drop table $TABLE_NAME"

class DogSqlHelper(context: Context?) : SQLiteOpenHelper(
    context,
    DB_NAME,
    null,
    DB_VERSION
), Repository {
    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(CREATE_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL(DROP_TABLE)
        onCreate(db)
    }

    override fun delete(selection: String?, selectionArgs: Array<String>?) =
        writableDatabase.delete(TABLE_NAME, selection, selectionArgs)

    override fun insert(values: ContentValues?) =
        writableDatabase.insert(TABLE_NAME, null, values)

    override fun query(
        projection: Array<String>?,
        selection: String?,
        selectionArgs: Array<String>?,
        sortOrder: String?
    ) = writableDatabase.query(
        TABLE_NAME,
        projection,
        selection,
        selectionArgs,
        null,
        null,
        sortOrder
    )

    override fun update(values: ContentValues?, selection: String?, selectionArgs: Array<String>?) =
        writableDatabase.update(TABLE_NAME, values, selection, selectionArgs)
}