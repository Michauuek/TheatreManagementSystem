package com.example.db.model
import com.example.db.schemas.StarWarsFilm
import com.example.db.schemas.UserRatings
import com.example.db.model.UserRating.Companion.referrersOn
import kotlinx.serialization.*
import org.jetbrains.exposed.dao.Entity
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID


@Serializable
data class User(
    val id: Int,
    val email: String,
    val password: String,
    var authToken: String? = null,
)

class UserRating(id: EntityID<Int>): IntEntity(id) {
    companion object : IntEntityClass<UserRating>(UserRatings)

    var value by UserRatings.value

}


