package com.tetsoft.planshopping

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.tetsoft.planshopping.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        val navHost =
//            supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
//        binding.mainBottomNavigation.setupWithNavController(navHost.navController)
    }
}