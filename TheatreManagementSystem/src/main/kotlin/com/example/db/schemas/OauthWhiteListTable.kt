package com.example.db.schemas

import org.jetbrains.exposed.sql.Table

/**
 * ## Table used for storing oauth white list
 *
 * This table is used to store white list of administators that can have access to the admin panel or other resources.
 *
 * @see com.example.services.oauthService
 * @see com.example.auth
 */
object OauthWhiteListTable : Table("auth_white_list")
{
    /**
     * ## id
     * ## PRIMARY KEY
     * ## AUTO_INCREMENT
     *
     * Used to identify this entry.
     */
    val id = integer("id").autoIncrement()

    /**
     * ## Administrator email
     * Emails that are allowed to use in application for tasks that require administrator privileges.
     * for example adding new performance or seances. **This is email used to login to the application with google's oauth**
     *
     * @see com.example.auth.auth
     */
    val email = varchar("email", 256)

    /**
     * ## Role of this email
     * Role of this email, used to determine if this email is allowed to perform certain actions.
     */
    val role = varchar("role",256)
    override val primaryKey = PrimaryKey(id)
}
