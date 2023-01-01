package com.adiliqbal.finize.database.converters

import androidx.room.TypeConverter
import com.adiliqbal.finize.database.model.TagEntity
import com.adiliqbal.finize.database.util.AppJson.decodeJson
import com.adiliqbal.finize.database.util.AppJson.toJson

class ListConverters {

	@TypeConverter
	fun stringToExperienceList(value: String?): List<TagEntity>? =
		value?.decodeJson<List<TagEntity>>()

	@TypeConverter
	fun experienceListToString(value: List<TagEntity>?) = value?.toJson()

	@TypeConverter
	fun stringToStringList(value: String?): List<String>? = value?.decodeJson<List<String>>()

	@TypeConverter
	fun stringListToString(value: List<String>?): String? = value?.toJson()
}
