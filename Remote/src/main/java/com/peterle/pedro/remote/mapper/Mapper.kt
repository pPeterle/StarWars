package com.peterle.pedro.remote.mapper

interface Mapper<in M, out E> {
    fun mapFromModel(model: M): E
}