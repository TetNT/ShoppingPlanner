package com.tetsoft.planshopping.ui.planned

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.LiveData
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.tetsoft.planshopping.MainActivity
import com.tetsoft.planshopping.PlannerApplication
import com.tetsoft.planshopping.R
import com.tetsoft.planshopping.adapter.SelectedProductsAdapter
import com.tetsoft.planshopping.databinding.FragmentPlannedListEditBinding
import com.tetsoft.planshopping.db.planned.PlannedList
import com.tetsoft.planshopping.db.selected.SelectedProduct
import com.tetsoft.planshopping.ui.product.ProductSelectionViewModel
import com.tetsoft.planshopping.ui.product.ProductSelectionViewModelFactory


class PlannedListEditFragment : Fragment() {

    private var _binding : FragmentPlannedListEditBinding? = null
    private val binding get() = _binding!!

    private lateinit var selectedProducts : LiveData<List<SelectedProduct>>

    private val viewModel : PlannedListViewModel by viewModels({activity as MainActivity}) {
        PlannedListViewModelFactory((activity?.application as PlannerApplication).listsRepository)
    }

    private val selectionViewModel: ProductSelectionViewModel by viewModels({activity as MainActivity}) {
        ProductSelectionViewModelFactory((activity?.application as PlannerApplication).selectionRepository)
    }

    fun addPlannedList(plannedList: PlannedList) {
        viewModel.addPlannedList(plannedList)
    }

    fun updatePlannedList(plannedList: PlannedList) {
        viewModel.updatePlannedList(plannedList)
    }

    private fun deletePlannedList(plannedList: PlannedList) {
        viewModel.deletePlannedList(plannedList)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupViews()

        val selectedList = viewModel.plannedList.value
        if (selectedList != null)
            selectionViewModel.selectPlannedList(selectedList)

        selectedProducts = selectionViewModel.getSelectedProductsFromList(selectedList!!.id)
        val adapter = SelectedProductsAdapter()
        binding.rvPlannedProducts.adapter = adapter
        binding.rvPlannedProducts.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)

        selectedProducts.observe(viewLifecycleOwner) {
            adapter.updateData(it)
        }
    }

    private fun setupViews() {
        val selectedList = viewModel.plannedList.value
        if (selectedList != null) {
            binding.etListName.setText(selectedList.name)
            binding.etListBudget.setText(selectedList.budget.toString())
        }

        binding.buttonSelectProducts.setOnClickListener {

        }

        binding.buttonDeleteList.setOnClickListener {
            if (selectedList != null) {
                deletePlannedList(selectedList)
                findNavController().navigateUp()
            }
        }

        binding.linkAddToList.setOnClickListener {
            findNavController().navigate(R.id.action_plannedListEdit_to_productList)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPlannedListEditBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}