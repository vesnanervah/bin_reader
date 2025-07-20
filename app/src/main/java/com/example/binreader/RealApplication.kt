package com.example.binreader

import com.example.binreader.data.RealAppContainer

class RealApplication: AbstractApplication() {

    override fun onCreate() {
        container = RealAppContainer()
        super.onCreate()
    }
}