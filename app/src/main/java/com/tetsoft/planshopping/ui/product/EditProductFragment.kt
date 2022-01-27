package com.tetsoft.planshopping.ui.product

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.tetsoft.planshopping.MainActivity
import com.tetsoft.planshopping.PlannerApplication
import com.tetsoft.planshopping.databinding.FragmentEditProductBinding
import com.tetsoft.planshopping.db.entity.Product

class EditProductFragment : Fragment() {

    private var _binding : FragmentEditProductBinding? = null
    private val binding get() = _binding!!

    private val viewModel: ProductViewModel by viewModels({ activity as MainActivity }) {
        ProductViewModelFactory((activity?.application as PlannerApplication).productRepository)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentEditProductBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val selectedProduct = viewModel.selectedProduct.value
        if (selectedProduct == null) {
            binding.buttonSaveProduct.text = "Добавить"
            binding.buttonDeleteProduct.visibility = View.GONE
        } else {
            binding.etProductName.setText(selectedProduct.name)
            binding.etProductPrice.setText(selectedProduct.price.toString())
        }
        binding.buttonSaveProduct.setOnClickListener {
            val name = binding.etProductName.text.toString()
            val price = binding.etProductPrice.text.toString().toDouble()
            if (selectedProduct == null) {
                viewModel.addProduct(Product(0, name, price))
                Toast.makeText(requireContext(), "Added", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(requireContext(), "Updated", Toast.LENGTH_SHORT).show()
                viewModel.updateProduct(Product(selectedProduct.id, name, price))
            }
            findNavController().navigateUp()
        }
        binding.buttonDeleteProduct.setOnClickListener {
            if (selectedProduct!= null) {
                viewModel.deleteProduct(selectedProduct)
                findNavController().navigateUp()
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}