package com.tetsoft.planshopping

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class PlannerApplication : Application()

    val listsRepository by lazy {
        PlannedListRepository(
            PlannerDatabase.getDatabase(this, applicationScope).plannedListDao()
        )
    }

    val selectionRepository by lazy {
        SelectedProductRepository(
            PlannerDatabase.getDatabase(this, applicationScope).selectedProductDao()
        )
    }
}