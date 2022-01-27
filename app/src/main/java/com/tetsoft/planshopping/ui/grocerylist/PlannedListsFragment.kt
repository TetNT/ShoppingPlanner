package com.tetsoft.planshopping.ui.grocerylist

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.tetsoft.planshopping.MainActivity
import com.tetsoft.planshopping.PlannerApplication
import com.tetsoft.planshopping.adapter.grocerylist.GroceryListAdapter
import com.tetsoft.planshopping.adapter.grocerylist.GroceryListItemOnClickListener
import com.tetsoft.planshopping.databinding.FragmentGroceryListsBinding
import com.tetsoft.planshopping.db.entity.GroceryList

class PlannedListsFragment : Fragment() {

    val viewModel: GroceryListViewModel by viewModels({activity as MainActivity}) {
         GroceryListViewModelFactory((activity?.application as PlannerApplication).listsRepository)
    }

    private var _binding : FragmentGroceryListsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentGroceryListsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val onClickListener = GroceryListItemOnClickListener { groceryList ->
            viewModel.selectGroceryList(groceryList)
        }
        val adapter = GroceryListAdapter(onClickListener)
        binding.rvGroceryLists.adapter = adapter
        binding.rvGroceryLists.layoutManager = LinearLayoutManager(
            requireContext(),
            LinearLayoutManager.VERTICAL,
            false
        )
        viewModel.groceryLists.observe(viewLifecycleOwner) {
            adapter.updateData(it)
        }

        binding.buttonAddList.setOnClickListener {
            viewModel.addGroceryList(GroceryList(0, "This is a very long name to test how it's gonna look like.", 0.0))
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}