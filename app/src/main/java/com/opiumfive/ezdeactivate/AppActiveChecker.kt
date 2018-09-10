package com.opiumfive.ezdeactivate

import com.google.firebase.remoteconfig.FirebaseRemoteConfigSettings
import com.google.firebase.remoteconfig.FirebaseRemoteConfig

object AppActiveChecker {

    private const val DEFAULT_EXPIRATION_TIME = 600L // 10 mins
    private const val IS_ACTIVE_KEY = "app_enabled"

    init {
        val remoteConfig = FirebaseRemoteConfig.getInstance()
        val configSettings = FirebaseRemoteConfigSettings.Builder().setDeveloperModeEnabled(BuildConfig.DEBUG).build()
        remoteConfig.setConfigSettings(configSettings)
        remoteConfig.setDefaults(R.xml.remote_config_defaults)
    }

    fun checkIfActive(checked: (Boolean) -> Unit) {
        val remoteConfig = FirebaseRemoteConfig.getInstance()

        val expTime = if (remoteConfig.info.configSettings.isDeveloperModeEnabled) 0L else DEFAULT_EXPIRATION_TIME

        remoteConfig.fetch(expTime).addOnCompleteListener {

            if (it.isSuccessful) {
                remoteConfig.activateFetched()
            }

            checked.invoke(remoteConfig.getBoolean(IS_ACTIVE_KEY))
        }
    }
}
