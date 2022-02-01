package com.tetsoft.planshopping.db.selected

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface SelectedProductDao {
    @Query("SELECT COUNT(*) FROM selected_products_table WHERE productListId = :plannedListId")
    fun getSelectedProductsCount(plannedListId: Int) : Int

    @Query("SELECT * FROM selected_products_table WHERE productListId = :plannedListId")
    fun getSelectedProductsForTheList(plannedListId: Int) : Flow<List<SelectedProduct>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addSelectedProduct(selectedProduct: SelectedProduct)

    @Delete
    suspend fun removeSelectedProduct(selectedProduct: SelectedProduct)

}