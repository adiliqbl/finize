package com.adiliqbal.finize.database.converters

import androidx.room.TypeConverter
import com.adiliqbal.finize.database.model.TagEntity
import com.adiliqbal.finize.database.util.AppJson.decodeJson
import com.adiliqbal.finize.database.util.AppJson.toJson

class ObjectConverters {

	@TypeConverter
	fun stringToTag(value: String?): TagEntity? =
		value?.decodeJson<TagEntity>()

	@TypeConverter
	fun tagToString(value: TagEntity?) = value?.toJson()
}
