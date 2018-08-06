package com.peterle.pedro.local.model

import androidx.room.*
import com.peterle.pedro.domain.model.People
import com.peterle.pedro.domain.model.Planet
import com.peterle.pedro.local.converters.PeopleTypeConverters
import com.peterle.pedro.local.converters.PlanetsTypeConverters

@Entity(tableName = "FilmsDetails")
data class FilmDetailsEntity(
        var title: String,
        @PrimaryKey(autoGenerate = false)
        var episodeId: Int,
        var releaseDate: String,
        var characters: List<People>,
        var planets: List<Planet>
)