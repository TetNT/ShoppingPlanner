package com.tetsoft.planshopping.db.selected

import androidx.room.*
import com.tetsoft.planshopping.db.planned.PlannedList
import com.tetsoft.planshopping.db.product.Product

@Entity(tableName = "selected_products_table",
    foreignKeys = [
        ForeignKey(
            entity = PlannedList::class,
            parentColumns = ["id"],
            onDelete = ForeignKey.CASCADE,
            childColumns = ["productListId"])
    ]
)
data class SelectedProduct (
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(index = true) val productListId: Int,
    @Embedded(prefix = "ref_") val product: Product,
    val amount: Int,
    val checked: Boolean   // checked if a user has gotten a product
)