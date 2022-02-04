package com.tetsoft.planshopping.di

import com.tetsoft.planshopping.db.planned.PlannedListRepository
import com.tetsoft.planshopping.db.planned.PlannedListRepositoryImpl
import com.tetsoft.planshopping.db.product.ProductRepository
import com.tetsoft.planshopping.db.product.ProductRepositoryImpl
import com.tetsoft.planshopping.db.selected.SelectedProductRepository
import com.tetsoft.planshopping.db.selected.SelectedProductRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindPlannedListRepository(impl: PlannedListRepositoryImpl) : PlannedListRepository

    @Binds
    @Singleton
    abstract fun bindProductRepository(impl: ProductRepositoryImpl) : ProductRepository

    @Binds
    @Singleton
    abstract fun bindSelectedProductsRepository(impl: SelectedProductRepositoryImpl) :
            SelectedProductRepository
}