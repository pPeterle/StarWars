package com.peterle.pedro.remote.mapper

import com.peterle.pedro.domain.model.Planet
import com.peterle.pedro.remote.model.PlanetModel

object PlanetModelMapper: Mapper<PlanetModel, Planet> {
    override fun mapFromModel(model: PlanetModel): Planet =
            Planet(model.name)
}