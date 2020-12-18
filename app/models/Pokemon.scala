package models

import play.api.libs.json.Json

case class Pokemon(id: Int, name: String, private val base_experience: Int, height: Int, private val is_default: Boolean, order: Int, weight: Int) {
  def baseExperience: Int = base_experience
  def isDefault: Boolean = is_default
}

object Pokemon {
  implicit val pokemonJsonFormat = Json.format[Pokemon]
}