package com.tetsoft.planshopping.db

import com.tetsoft.planshopping.db.entity.GroceryList

class GroceryListRepository(private val plannerDao: PlannerDao) {

    val allLists = plannerDao.getAllPlannedLists()

    suspend fun addList(groceryList: GroceryList) {
        plannerDao.addPlannedList(groceryList)
    }
}