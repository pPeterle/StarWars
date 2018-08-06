package com.peterle.pedro.presentation.mapper

import com.peterle.pedro.domain.model.People
import com.peterle.pedro.presentation.model.PeopleView

object PeopleViewMapper: Mapper<People, PeopleView> {

    override fun maptToView(model: People): PeopleView = PeopleView(model.name)

}