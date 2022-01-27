package com.tetsoft.planshopping

import android.app.Application
import com.tetsoft.planshopping.db.GroceryListRepository
import com.tetsoft.planshopping.db.PlannerDatabase
import com.tetsoft.planshopping.db.ProductRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class PlannerApplication : Application() {
    val applicationScope = CoroutineScope(SupervisorJob())

    val productRepository by lazy {
        ProductRepository(PlannerDatabase.getDatabase(this, applicationScope).plannerDao())
    }

    val listsRepository by lazy {
        GroceryListRepository(PlannerDatabase.getDatabase(this, applicationScope).plannerDao())
    }
}