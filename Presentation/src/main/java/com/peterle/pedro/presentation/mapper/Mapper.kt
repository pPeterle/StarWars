package com.peterle.pedro.presentation.mapper

interface Mapper<in T, out V> {

    fun maptToView(model: T): V
}