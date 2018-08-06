package com.peterle.pedro.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.peterle.pedro.local.model.FilmDetailsEntity
import io.reactivex.Maybe

@Dao
interface FilmDetailsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg film: FilmDetailsEntity)

    @Query("SELECT * FROM FilmsDetails WHERE episodeId = :id")
    fun getAllfilmsDetails(id: String): Maybe<FilmDetailsEntity>
}