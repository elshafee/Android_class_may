package com.elshafee.androidclassmay.shoppingitemlist.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.elshafee.androidclassmay.databinding.ActivityShoppingItemListBinding
import com.elshafee.androidclassmay.shoppingitemlist.db.ShoppingDatabase
import com.elshafee.androidclassmay.shoppingitemlist.model.ShoppingItem
import com.elshafee.androidclassmay.shoppingitemlist.repositry.ShoppingRepositry
import com.elshafee.androidclassmay.shoppingitemlist.ui.utils.ShoppingItemAdapter

class ShoppingItemListActivity : AppCompatActivity() {
    private lateinit var binding: ActivityShoppingItemListBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityShoppingItemListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val databse = ShoppingDatabase(this)
        val repositry = ShoppingRepositry(databse)
        val factory = ShoppingViewModelFactory(repositry)

        val viewModel = ViewModelProvider(this, factory).get(ShoppingViewModel::class.java)

        val adapter = ShoppingItemAdapter(listOf(), viewModel)
        binding.rvShoppingItems.adapter = adapter
        binding.rvShoppingItems.layoutManager = LinearLayoutManager(this)

        viewModel.getAllShoppingItem().observe(this, { shoppingitem ->
            adapter.items = shoppingitem
            adapter.notifyDataSetChanged()
        })

        binding.fabShoppingItem.setOnClickListener {
            AddShoppingItemDialog(this, object : AddDialogListener {
                override fun onAddButtonClickListener(item: ShoppingItem) {
                    viewModel.upsert(item)
                }
            }).show()

        }


    }
}