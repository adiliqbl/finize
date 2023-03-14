package com.adiliqbal.finize.database.converters

import androidx.room.TypeConverter
import com.adiliqbal.finize.database.util.AppJson.decodeJson
import com.adiliqbal.finize.database.util.AppJson.toJson

internal class ListConverters {

    @TypeConverter fun toStringList(value: String?): List<String>? = value?.decodeJson<List<String>>()

    @TypeConverter fun fromStringList(value: List<String>?): String? = value?.toJson()
}
