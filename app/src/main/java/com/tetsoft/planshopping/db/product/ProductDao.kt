package com.tetsoft.planshopping.db.product

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface ProductDao {
    @Query("SELECT * FROM products_table ORDER BY name")
    fun getAllProducts() : Flow<List<Product>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addProduct(product: Product)

    @Delete
    suspend fun deleteProduct(product: Product)

    @Query("DELETE FROM products_table")
    suspend fun clearProductTable()

    @Update
    suspend fun updateProduct(product: Product)

}