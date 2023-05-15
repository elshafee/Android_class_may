package com.elshafee.androidclassmay.shoppingitemlist.ui

import android.content.Context
import android.os.Binder
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatDialog
import com.elshafee.androidclassmay.databinding.ShoppingDialogBinding
import com.elshafee.androidclassmay.shoppingitemlist.model.ShoppingItem

class AddShoppingItemDialog(context:Context, var addDialogListener: AddDialogListener) :AppCompatDialog(context){

    lateinit var binding:ShoppingDialogBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ShoppingDialogBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.btnShoppingDialogAdd.setOnClickListener {
            var itemname = binding.etShoppingItemDialogName.text.toString()
            var itemamount = binding.etShoppingItemDialogAmount.text.toString()
            if (itemname.isEmpty() || itemamount.isEmpty()){
                Toast.makeText(context,"Please enter the item name and amount correctly",Toast.LENGTH_LONG).show()
            }else{
                val item = ShoppingItem(itemname, itemamount.toInt())
                addDialogListener.onAddButtonClickListener(item)
                dismiss()

            }
        }


        binding.btnShoppingDialogCancel.setOnClickListener {
            cancel()
        }


    }
}