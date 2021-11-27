package com.onedoor.fakeusergenerator.ui

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.onedoor.fakeusergenerator.databinding.FakeUserViewHolderBinding
import com.onedoor.fakeusergenerator.FakeUser

class FakeUsersRecyclerViewAdapter :
    ListAdapter<FakeUser, FakeUsersRecyclerViewAdapter.ViewHolder>(FakeUserDiffCallback()) {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        return ViewHolder.from(parent)
    }

    class ViewHolder private constructor(val binding: FakeUserViewHolderBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: FakeUser) {
            binding.fakeUser = item
            binding.root.setOnClickListener {
                val action =
                    FakeUserListFragmentDirections.actionMainFragmentToFakeUserInfoFragment(item)
                it.findNavController().navigate(action)
            }
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = FakeUserViewHolderBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }
    }


    class FakeUserDiffCallback : DiffUtil.ItemCallback<FakeUser>() {
        override fun areItemsTheSame(oldItem: FakeUser, newItem: FakeUser): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: FakeUser, newItem: FakeUser): Boolean {
            return oldItem == newItem
        }

    }
}