package com.example.binreader

import com.example.binreader.data.MockAppContainer

class MockApplication: AbstractApplication() {

    override fun onCreate() {
        container = MockAppContainer()
        super.onCreate()
    }
}