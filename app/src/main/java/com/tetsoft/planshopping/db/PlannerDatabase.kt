package com.tetsoft.planshopping.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.tetsoft.planshopping.db.product.Product
import com.tetsoft.planshopping.db.planned.PlannedList
import com.tetsoft.planshopping.db.selected.SelectedProduct
import com.tetsoft.planshopping.db.planned.PlannedListDao
import com.tetsoft.planshopping.db.product.ProductDao
import com.tetsoft.planshopping.db.selected.SelectedProductDao
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch


@Database(entities = [Product::class, PlannedList::class, SelectedProduct::class], version = 3)
abstract class PlannerDatabase : RoomDatabase() {
    abstract fun productDao() : ProductDao

    abstract fun plannedListDao() : PlannedListDao

    abstract fun selectedProductDao() : SelectedProductDao

    companion object {
        private var INSTANCE : PlannerDatabase? = null

        fun getDatabase(context: Context, scope: CoroutineScope) : PlannerDatabase {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(
                    context,
                    PlannerDatabase::class.java,
                    "PlannerDB"
                )
                    .addCallback(PlannerDatabaseCallback(scope))
                        // TODO: replace this with proper migration when release
                    .fallbackToDestructiveMigration()
                    .build()
            }
            return INSTANCE as PlannerDatabase
        }
    }

    private class PlannerDatabaseCallback(private val scope: CoroutineScope) : RoomDatabase.Callback() {
        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            INSTANCE?.let { database ->
                scope.launch {
                    populateDatabase(database.plannerDao())
                }
            }
        }

        suspend fun populateDatabase(plannerDao: PlannerDao) {
            populateProducts(plannerDao)
            populatePlannedLists(plannerDao)
        }

        suspend fun populateProducts(plannerDao: PlannerDao) {
            plannerDao.addProduct(Product(0, "Apples", 1.60))
            plannerDao.addProduct(Product(0, "Bread", 1.30))
            plannerDao.addProduct(Product(0, "Milk", 2.30))
            plannerDao.addProduct(Product(0, "Chocolate", 3.50))
        }

        suspend fun populatePlannedLists(plannerDao: PlannerDao) {
            plannerDao.addPlannedList(GroceryList(0, "For new year", 80.0))
            plannerDao.addPlannedList(GroceryList(0, "Birthday", 100.0))
            plannerDao.addPlannedList(GroceryList(0, "Tomorrow", 15.0))
        }

    }


}