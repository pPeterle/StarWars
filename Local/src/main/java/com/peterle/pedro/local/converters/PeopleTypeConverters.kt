package com.peterle.pedro.local.converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.peterle.pedro.domain.model.People

class PeopleTypeConverters {

    @TypeConverter
    fun stringToMeasurements(json: String): List<People> {
        val gson = Gson()
        val type = object : TypeToken<List<People>>() {

        }.type
        return gson.fromJson(json, type)
    }

    @TypeConverter
    fun measurementsToString(list: List<People>): String {
        val gson = Gson()
        val type = object : TypeToken<List<People>>() {

        }.type
        return gson.toJson(list, type)
    }
}