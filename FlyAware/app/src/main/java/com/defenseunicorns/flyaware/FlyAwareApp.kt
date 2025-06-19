package com.defenseunicorns.flyaware

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

/**
 * This top-level application is needed to ensure Hilt is able to inject as needed
 */
@HiltAndroidApp
class FlyAwareApp : Application()