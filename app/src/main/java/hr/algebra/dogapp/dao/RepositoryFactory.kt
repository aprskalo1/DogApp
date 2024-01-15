package hr.algebra.dogapp.dao

import android.content.Context

fun getRepository(context: Context?) = DogSqlHelper(context)