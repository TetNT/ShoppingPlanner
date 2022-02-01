package com.tetsoft.planshopping.ui.planned

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.tetsoft.planshopping.MainActivity
import com.tetsoft.planshopping.PlannerApplication
import com.tetsoft.planshopping.R
import com.tetsoft.planshopping.adapter.grocerylist.PlannedListAdapter
import com.tetsoft.planshopping.adapter.grocerylist.PlannedListItemOnClickListener
import com.tetsoft.planshopping.databinding.FragmentPlannedListsBinding
import com.tetsoft.planshopping.db.planned.PlannedList

class PlannedListsFragment : Fragment() {

    val viewModel: PlannedListViewModel by viewModels({activity as MainActivity}) {
         PlannedListViewModelFactory((activity?.application as PlannerApplication).listsRepository)
    }

    private var _binding : FragmentPlannedListsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPlannedListsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val onClickListener = PlannedListItemOnClickListener { groceryList ->
            viewModel.selectPlannedList(groceryList)
            findNavController().navigate(R.id.action_plannedLists_to_plannedListEdit)
        }
        val adapter = PlannedListAdapter(onClickListener)
        binding.rvPlannedLists.adapter = adapter
        binding.rvPlannedLists.layoutManager = LinearLayoutManager(
            requireContext(),
            LinearLayoutManager.VERTICAL,
            false
        )
        viewModel.groceryLists.observe(viewLifecycleOwner) {
            adapter.updateData(it)
            binding.rvPlannedLists.scrollToPosition(0)
        }

        binding.buttonAddList.setOnClickListener {
            viewModel.addPlannedList(PlannedList(0, "This is a very long name to test how it's gonna look like.", 0.0))
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}