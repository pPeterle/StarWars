package com.peterle.pedro.presentation.mapper

import com.peterle.pedro.domain.model.Planet
import com.peterle.pedro.presentation.model.PlanetView

object PlanetViewMapper: Mapper<Planet, PlanetView> {
    override fun maptToView(model: Planet): PlanetView = PlanetView(model.name)
}
