package com.tetsoft.planshopping.ui.product

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
import com.tetsoft.planshopping.adapter.product.ProductItemOnClickListener
import com.tetsoft.planshopping.adapter.product.ProductAdapter
import com.tetsoft.planshopping.databinding.FragmentProductListBinding

class ProductListFragment : Fragment() {

    private var _binding: FragmentProductListBinding? = null
    private val binding get() = _binding!!

    private val viewModel: ProductViewModel by viewModels({activity as MainActivity}) {
        ProductViewModelFactory((activity?.application as PlannerApplication).productRepository)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProductListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val listener = ProductItemOnClickListener { product ->
            viewModel.selectProduct(product)
            findNavController().navigate(R.id.action_productList_to_editProduct)
        }

        val adapter = ProductAdapter(listener)
        binding.rvProducts.adapter = adapter
        binding.rvProducts.layoutManager = LinearLayoutManager(
            requireContext(),
            LinearLayoutManager.VERTICAL,
            false
        )

        binding.buttonAddProduct.setOnClickListener {
            viewModel.selectProduct(null)
            findNavController().navigate(R.id.action_productList_to_editProduct)
        }

        viewModel.products.observe(viewLifecycleOwner) { productList ->
            adapter.updateData(productList)
        }
    }

}