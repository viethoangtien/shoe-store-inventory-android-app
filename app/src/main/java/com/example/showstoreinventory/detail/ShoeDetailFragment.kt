package com.example.showstoreinventory.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.showstoreinventory.MainActivityViewModel
import com.example.showstoreinventory.R
import com.example.showstoreinventory.databinding.FragmentShoeDetailBinding

class ShoeDetailFragment : Fragment() {
    private lateinit var binding: FragmentShoeDetailBinding
    private val mainActivityViewModel: MainActivityViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_shoe_detail, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            mainActivityViewModel = this@ShoeDetailFragment.mainActivityViewModel
            lifecycleOwner = this@ShoeDetailFragment

            buttonCancel.setOnClickListener {
                findNavController().popBackStack()
            }
            buttonSave.setOnClickListener {
                this@ShoeDetailFragment.mainActivityViewModel.saveShoeDetail()
                findNavController().popBackStack()
            }
        }
    }
}