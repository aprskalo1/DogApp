package hr.algebra.dogapp

import android.content.ContentProvider
import android.content.ContentUris
import android.content.ContentValues
import android.content.UriMatcher
import android.database.Cursor
import android.net.Uri
import hr.algebra.dogapp.dao.Repository
import hr.algebra.dogapp.dao.getRepository

private const val AUTHORITY = "hr.algebra.dogapp.network.provider"
private const val PATH = "items"
val DOG_PROVIDER_CONTENT_URI: Uri = Uri.parse("content://$AUTHORITY/$PATH")

private const val ITEMS = 10
private const val ITEM_ID = 20

class DogProvider : ContentProvider() {

    private val URI_MATCHER = with(UriMatcher(UriMatcher.NO_MATCH)) {
        addURI(AUTHORITY, PATH, ITEMS)
        addURI(AUTHORITY, "$PATH/#", ITEM_ID)
        this
    }
    private lateinit var repository: Repository
    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<String>?): Int {
        return when (URI_MATCHER.match(uri)) {
            ITEMS -> repository.delete(selection, selectionArgs)
            ITEM_ID -> {
                uri.lastPathSegment?.let {
                    repository.delete("_id = ?", arrayOf(it))
                } ?: 0
            }
            else -> throw IllegalArgumentException("Unsupported URI: $uri")
        }
    }

    override fun getType(uri: Uri): String {
        return when (URI_MATCHER.match(uri)) {
            ITEMS -> "vnd.android.cursor.dir/$AUTHORITY.$PATH"
            ITEM_ID -> "vnd.android.cursor.item/$AUTHORITY.$PATH"
            else -> throw IllegalArgumentException("Unsupported URI: $uri")
        }
    }

    override fun insert(uri: Uri, values: ContentValues?): Uri? {
        val id = repository.insert(values)
        return ContentUris.withAppendedId(DOG_PROVIDER_CONTENT_URI, id)
    }

    override fun onCreate(): Boolean {
        repository = getRepository(context)
        return true
    }

    override fun query(
        uri: Uri, projection: Array<String>?, selection: String?,
        selectionArgs: Array<String>?, sortOrder: String?
    ): Cursor? = repository.query(projection, selection, selectionArgs, sortOrder)

    override fun update(
        uri: Uri, values: ContentValues?, selection: String?,
        selectionArgs: Array<String>?
    ): Int {
        return when (URI_MATCHER.match(uri)) {
            ITEMS -> repository.update(values, selection, selectionArgs)
            ITEM_ID -> {
                uri.lastPathSegment?.let {
                    repository.update(values, "_id = ?", arrayOf(it))
                } ?: 0
            }
            else -> throw IllegalArgumentException("Unsupported URI: $uri")
        }
    }
}