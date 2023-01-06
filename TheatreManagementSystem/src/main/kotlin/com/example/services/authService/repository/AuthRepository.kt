package com.example.services.authService.repository

import com.example.auth.GoogleUserInfo
import com.example.db.DatabaseFactory
import com.example.db.extension.toOauthWhitelist
import com.example.db.schemas.OauthWhiteListTable
import com.example.response.auth.Privilege
import org.jetbrains.exposed.sql.select



class AuthRepository {
    suspend fun getPrivilege(user: GoogleUserInfo?): Privilege
    {
        if (user == null)
            return Privilege.GUEST

        return DatabaseFactory.dbQuery {
            OauthWhiteListTable.select {
                OauthWhiteListTable.email eq user.email
            }.firstOrNull()?.let {
                when (it.toOauthWhitelist()?.role) {
                    "admin" -> Privilege.ADMIN
                    "actor" -> Privilege.ACTOR
                    else -> Privilege.GUEST
                }
            }
        }?: return Privilege.GUEST
    }
}