package com.example.binreader

import android.app.Application
import com.example.binreader.data.AppContainer

abstract class AbstractApplication: Application() {

    lateinit var container: AppContainer

}