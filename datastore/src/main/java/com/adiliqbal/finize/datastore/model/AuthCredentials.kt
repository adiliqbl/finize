package com.adiliqbal.finize.datastore.model

import kotlinx.serialization.Serializable

@Serializable
data class AuthCredentials(
    val botId: String,
    val accessToken: String,
    val workspaceId: String?,
    val workspaceName: String?
)
