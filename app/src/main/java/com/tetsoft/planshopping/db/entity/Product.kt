package com.tetsoft.planshopping.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "product_table")
data class Product (
    @PrimaryKey(autoGenerate = true) val id: Int,
    val name: String,
    val price: Double
        )