package com.peterle.pedro.remote.mapper

import com.peterle.pedro.domain.model.People
import com.peterle.pedro.remote.model.PeopleModel

object PeopleModelMapper: Mapper<PeopleModel, People> {
    override fun mapFromModel(model: PeopleModel): People =
            People(model.name)
}