package com.tetsoft.planshopping.adapter.grocerylist

import com.tetsoft.planshopping.db.entity.GroceryList

class GroceryListItemOnClickListener (val clickListener: (groceryList: GroceryList) -> Unit) {
    fun onClick(groceryList: GroceryList) {
        clickListener(groceryList)
    }
}