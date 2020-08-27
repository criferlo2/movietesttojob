/***
 * AUTHOR: CRISTHIAN LOMBANA
 * cristhian.lombana@hashitoapps.com
 */
package com.hashitoapps.movies_test_to_job.application

import android.app.Application
import di.moduleDomain
import di.moduleUserInterface
import org.koin.android.ext.android.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin(this, listOf(moduleDomain, moduleUserInterface))
    }

}