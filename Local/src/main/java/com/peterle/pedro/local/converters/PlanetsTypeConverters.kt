package com.peterle.pedro.local.converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.peterle.pedro.domain.model.Planet


class PlanetsTypeConverters {
    @TypeConverter
    fun stringToMeasurements(json: String): List<Planet> {
        val gson = Gson()
        val type = object : TypeToken<List<Planet>>() {

        }.getType()
        return gson.fromJson(json, type)
    }

    @TypeConverter
    fun measurementsToString(list: List<Planet>): String {
        val gson = Gson()
        val type = object : TypeToken<List<Planet>>() {

        }.getType()
        return gson.toJson(list, type)
    }
}