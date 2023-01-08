package com.adiliqbal.finize.network.service.notion

import com.adiliqbal.finize.network.BuildConfig
import com.adiliqbal.finize.network.Retrofit.Companion.NOTION_API

interface NotionAuthService {
	companion object {
		fun loginLink(loginUri: String) =
			"${NOTION_API}/oauth/authorize?client_id=${BuildConfig.NOTION_AUTH_ID}&response_type=code&owner=user&redirect_uri=${loginUri}"
	}
}