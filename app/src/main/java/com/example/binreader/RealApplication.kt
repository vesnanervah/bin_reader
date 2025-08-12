package com.example.binreader

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.example.binreader.data.RealAppContainer

class RealApplication: AbstractApplication() {
    val dataStore: DataStore<Preferences> by preferencesDataStore(name = "cache")


    override fun onCreate() {
        container = RealAppContainer(dataStore)
        super.onCreate()
    }
}