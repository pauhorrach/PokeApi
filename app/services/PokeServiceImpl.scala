package services

import javax.inject.Inject
import models.{Habitat, Pokemon}
import play.api.libs.json.Json
import play.api.libs.ws._

import scala.concurrent._
import ExecutionContext.Implicits.global

class PokeServiceImpl @Inject() (ws: WSClient) extends PokeService {

  override def getPokemonByHabitat(habitat: String): Any = {

    Future.sequence( ws.url(s"https://pokeapi.co/api/v2/pokemon-habitat/$habitat/").get().flatMap {
 response => if (response.status == 200) Future.successful(Json.parse(response.body).as[Habitat]) else null
    }.flatMap[List[Pokemon]]{
      habitat => habitat.pokemonSpecies.map {
        specie => getPokemonByName(specie.name) }} )
  }


  def getPokemonByName(name: String): Future[Pokemon] = {
    ws.url(s"https://pokeapi.co/api/v2/pokemon/$name/").get().flatMap { response =>
      if (response.status == 200) Future.successful(Json.parse(response.body).as[Pokemon]) else null
    }
  }
}