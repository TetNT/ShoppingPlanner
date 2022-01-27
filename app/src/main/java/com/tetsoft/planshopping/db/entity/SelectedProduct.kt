package com.tetsoft.planshopping.db.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "selected_product",
    foreignKeys = [
        ForeignKey(
            entity = GroceryList::class,
            parentColumns = ["id"],
            onDelete = ForeignKey.CASCADE,
            childColumns = ["productListId"]),
        ForeignKey(
            entity = Product::class,
            parentColumns = ["id"],
            childColumns = ["productId"],
            onDelete = ForeignKey.CASCADE)])
data class SelectedProduct (
    @PrimaryKey(autoGenerate = true) val id: Int,
    val productId: Int,
    val amount: Int,
    val checked: Boolean,   // checked if a user has gotten a product
    val productListId: Int
)