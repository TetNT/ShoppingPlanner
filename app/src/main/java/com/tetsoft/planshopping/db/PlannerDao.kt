package com.tetsoft.planshopping.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.tetsoft.planshopping.db.entity.Product
import com.tetsoft.planshopping.db.entity.GroceryList

@Dao
interface PlannerDao {
    @Query("SELECT * FROM product_table ORDER BY name")
    fun getAllProducts() : LiveData<List<Product>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addProduct(product: Product)

    @Delete
    suspend fun deleteProduct(product: Product)

    @Query("DELETE FROM product_table")
    suspend fun clearProductTable()

    @Update
    suspend fun updateProduct(product: Product)

    //@Query("SELECT COUNT(*) FROM selected_product WHERE ")

    @Query("SELECT * FROM product_list_table")
    fun getAllPlannedLists() : LiveData<List<GroceryList>>

    @Insert
    suspend fun addPlannedList(groceryList: GroceryList)
}