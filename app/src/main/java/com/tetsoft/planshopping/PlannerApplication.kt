package com.tetsoft.planshopping

import android.app.Application
import com.tetsoft.planshopping.db.planned.PlannedListRepository
import com.tetsoft.planshopping.db.PlannerDatabase
import com.tetsoft.planshopping.db.product.ProductRepository
import com.tetsoft.planshopping.db.selected.SelectedProductRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class PlannerApplication : Application() {
    private val applicationScope = CoroutineScope(SupervisorJob())

    val productRepository by lazy {
        ProductRepository(
            PlannerDatabase.getDatabase(this, applicationScope).productDao()
        )
    }

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