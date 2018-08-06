package com.peterle.pedro.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.peterle.pedro.data.SWAPILocal
import com.peterle.pedro.domain.model.FilmDetails
import com.peterle.pedro.local.db.SwapiDatabase
import com.peterle.pedro.local.mapper.FIlmDetailsMapper
import io.reactivex.Maybe
import io.reactivex.Observable
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SWAPILocalImpl @Inject constructor(context: Context): SWAPILocal {

    private val database: SwapiDatabase by lazy {
        Room.databaseBuilder(context,
                SwapiDatabase::class.java,
                "SWAPIDatabase")
                .fallbackToDestructiveMigration()
                .build()
    }

    private val filmDetailsDao = database.filmDetailsDao()


    override fun insertFilmDetails(filmDetails: FilmDetails) {
        filmDetailsDao.insert(FIlmDetailsMapper.fromDomain(filmDetails))
    }

    override fun getAllFilmsDetails(id: String): Maybe<FilmDetails> =
            filmDetailsDao.getAllfilmsDetails(id)
                    .map {  FIlmDetailsMapper.toDomain(it)  }


}