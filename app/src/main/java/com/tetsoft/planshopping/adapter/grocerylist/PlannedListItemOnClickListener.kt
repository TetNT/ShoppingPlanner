package com.tetsoft.planshopping.adapter.grocerylist

import com.tetsoft.planshopping.db.planned.PlannedList

class PlannedListItemOnClickListener (val clickListener: (plannedList: PlannedList) -> Unit) {
    fun onClick(plannedList: PlannedList) {
        clickListener(plannedList)
    }
}