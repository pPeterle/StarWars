package com.peterle.pedro.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.peterle.pedro.local.converters.PeopleTypeConverters
import com.peterle.pedro.local.converters.PlanetsTypeConverters
import com.peterle.pedro.local.dao.FilmDetailsDao
import com.peterle.pedro.local.model.FilmDetailsEntity

@Database(
        entities = [
            FilmDetailsEntity::class
        ],
        version = 2
)
@TypeConverters(PeopleTypeConverters::class, PlanetsTypeConverters::class)
abstract class SwapiDatabase: RoomDatabase() {
    abstract fun filmDetailsDao(): FilmDetailsDao
}