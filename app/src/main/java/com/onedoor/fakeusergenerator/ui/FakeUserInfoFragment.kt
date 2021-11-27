package com.onedoor.fakeusergenerator.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.onedoor.fakeusergenerator.*
import com.onedoor.fakeusergenerator.databinding.FragmentFakeUserInfoBinding
import com.onedoor.fakeusergenerator.viewmodels.FakeUserInfoFragmentViewModel
import com.onedoor.fakeusergenerator.viewmodels.FakeUserInfoViewModelFactory


class FakeUserInfoFragment : Fragment(R.layout.fragment_fake_user_info) {

    private val args: FakeUserInfoFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentFakeUserInfoBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_fake_user_info, container, false
        )

        // Create an instance of the ViewModel Factory.
        val fakeUser = args.fakeUser
        val viewModelFactory = FakeUserInfoViewModelFactory(fakeUser)

        val fakeUserInfoViewModel =
            ViewModelProvider(this, viewModelFactory)[FakeUserInfoFragmentViewModel::class.java]

        binding.fakeUserInfoViewModel = fakeUserInfoViewModel
        binding.lifecycleOwner = this

        return binding.root
    }
}