package com.example.showstoreinventory.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.activity.OnBackPressedCallback
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.showstoreinventory.MainActivityViewModel
import com.example.showstoreinventory.R
import com.example.showstoreinventory.databinding.FragmentShoeListBinding
import com.example.showstoreinventory.databinding.FragmentWelcomeBinding
import com.example.showstoreinventory.databinding.ViewShoeBinding
import com.example.showstoreinventory.login.LoginViewModel
import com.example.showstoreinventory.model.Shoe

class ShoeListFragment : Fragment() {
    private lateinit var binding: FragmentShoeListBinding
    private val mainActivityViewModel: MainActivityViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_shoe_list, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.buttonAdd.setOnClickListener {
            findNavController().navigate(ShoeListFragmentDirections.toShoeDetailFragment())
        }
        mainActivityViewModel.shoeListLiveData.observe(viewLifecycleOwner) { shoeList ->
            binding.layoutShoe.removeAllViews()
            shoeList.forEach { shoe: Shoe ->
                addView(shoe)
            }
        }
    }

    private fun addView(shoe: Shoe) {
        val showViewBinding: ViewShoeBinding = DataBindingUtil.inflate<ViewShoeBinding>(
            layoutInflater, R.layout.view_shoe, binding.layoutShoe, false
        )
        showViewBinding.apply {
            textShoeName.text = getString(R.string.shoe_name_text, shoe.name)
            textCompany.text = getString(R.string.company_text, shoe.company)
            textSize.text = getString(R.string.size_text, shoe.size)
            textDescription.text = getString(R.string.description_text, shoe.description)
        }
        binding.layoutShoe.addView(showViewBinding.root)
    }
}