package com.tetsoft.planshopping.ui.product

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.hilt.navigation.fragment.hiltNavGraphViewModels
import androidx.navigation.fragment.findNavController
import com.tetsoft.planshopping.R
import com.tetsoft.planshopping.databinding.FragmentEditProductBinding

class EditProductFragment : Fragment() {

    private var _binding : FragmentEditProductBinding? = null
    private val binding get() = _binding!!

    private val productViewModel: ProductViewModel by hiltNavGraphViewModels(R.id.main_navigation)

    private val selectionViewModel: ProductSelectionViewModel by hiltNavGraphViewModels(R.id.main_navigation)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentEditProductBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupViews()
        val pickedProduct = productViewModel.editingProduct.value
        if (pickedProduct != null) {
            selectionViewModel.rememberProduct(pickedProduct)
        }
    }

    private fun setupViews() {
        if (!productViewModel.isEditing()) {
            binding.buttonSaveProduct.text = requireContext().getString(R.string.add)
            binding.buttonDeleteProduct.visibility = View.GONE
        } else {
            val editingProduct = productViewModel.editingProduct.value
            binding.etProductName.setText(editingProduct!!.name)
            binding.etProductPrice.setText(editingProduct.price.toString())
        }
        binding.buttonSaveProduct.setOnClickListener {
            saveProduct()
            findNavController().navigateUp()
        }
        binding.buttonDeleteProduct.setOnClickListener {
            if (productViewModel.isEditing()) {
                productViewModel.deleteCurrentProduct()
                findNavController().navigateUp()
            }
        }
        val currentList = selectionViewModel.currentPlannedList
        binding.addToList.setOnClickListener {
            saveProduct()
            productViewModel.selectProduct(selectionViewModel.selectedProduct.value)

            selectionViewModel.pickCurrentProduct(1)
            Toast.makeText(requireContext(),
                "?????????????? ?????????????????? ?? ???????????? ${currentList.name}",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    private fun saveProduct() {
        productViewModel.saveProduct(
            binding.etProductName.text.toString(),
            binding.etProductPrice.text.toString()
        )
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}