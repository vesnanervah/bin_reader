package com.example.binreader

import android.app.Application
import com.example.binreader.data.AppContainer
import com.example.binreader.data.MockAppContainer

class MockApplication: Application() {
    lateinit var container: AppContainer

    override fun onCreate() {
        container = MockAppContainer()
        super.onCreate()
    }
}