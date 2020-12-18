package services

import models.{Habitat, Pokemon}

import scala.concurrent.Future

trait PokeService {
  def getPokemonByHabitat(habitat:String): Any
}
