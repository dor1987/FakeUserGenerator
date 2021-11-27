package com.onedoor.fakeusergenerator.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.onedoor.fakeusergenerator.viewmodels.FakeUserListFragmentViewModel
import com.onedoor.fakeusergenerator.viewmodels.FakeUsersViewModelFactory
import com.onedoor.fakeusergenerator.R
import com.onedoor.fakeusergenerator.database.local.FakeUsersLocalDataBase
import com.onedoor.fakeusergenerator.databinding.FragmentMainListBinding

class FakeUserListFragment : Fragment(R.layout.fragment_main_list) {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentMainListBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_main_list, container, false
        )
        val application = requireNotNull(this.activity).application

        // Create an instance of the ViewModel Factory.
        val dataSource = FakeUsersLocalDataBase.getInstance(application).getFakeUsersDao()
        val viewModelFactory = FakeUsersViewModelFactory(dataSource, application)

        //Gets the viewModel
        val fakeUsersViewModel =
            ViewModelProvider(this, viewModelFactory)[FakeUserListFragmentViewModel::class.java]

        binding.fakeUSerListFragmentViewModel = fakeUsersViewModel

        val adapter = FakeUsersRecyclerViewAdapter()
        binding.list.adapter = adapter

        //Observe to data changes
        fakeUsersViewModel.fakeUsers.observe(viewLifecycleOwner, Observer {
            it?.let {
                adapter.submitList(it)
            }
        })
        //Observe to errors
        fakeUsersViewModel.errors.observe(viewLifecycleOwner, Observer {
            it?.let {
                Toast.makeText(context, "Request to server failed ", Toast.LENGTH_SHORT).show()
            }
        })

        binding.lifecycleOwner = this

        return binding.root
    }
}