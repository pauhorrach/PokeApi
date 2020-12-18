package controllers

import javax.inject._
import models.Habitat
import play.api._
import play.api.libs.json.Json
import play.api.mvc._
import services.PokeServiceImpl

import scala.concurrent._
import ExecutionContext.Implicits.global

/**
 * This controller creates an `Action` to handle HTTP requests to the
 * application's home page.
 */
@Singleton
class PokeController @Inject()(val controllerComponents: ControllerComponents, val pokeService: PokeServiceImpl) extends BaseController {

  def pokemonByHabitat(habitat: String): Action[AnyContent] = Action.async { _ =>
    pokeService.getPokemonByHabitat(habitat).map(r => Ok(Json.toJson(r)))
  }
}
