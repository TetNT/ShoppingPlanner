package com.tetsoft.planshopping.di

import android.content.Context
import androidx.room.Room
import com.tetsoft.planshopping.db.PlannerDatabase
import com.tetsoft.planshopping.db.planned.PlannedListDao
import com.tetsoft.planshopping.db.product.ProductDao
import com.tetsoft.planshopping.db.selected.SelectedProductDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext appContext: Context) : PlannerDatabase {
        return Room.databaseBuilder(
            appContext,
            PlannerDatabase::class.java,
            "PlannerDB"
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    fun providePlannedListDao(plannerDatabase: PlannerDatabase) : PlannedListDao {
        return plannerDatabase.plannedListDao()
    }

    @Provides
    fun provideProductDao(plannerDatabase: PlannerDatabase) : ProductDao {
        return plannerDatabase.productDao()
    }

    @Provides
    fun provideSelectedProductDao(plannerDatabase: PlannerDatabase) : SelectedProductDao {
        return plannerDatabase.selectedProductDao()
    }
}