package com.elshafee.androidclassmay.breakingbadapi.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.elshafee.androidclassmay.R

class CharacterImageFragment : Fragment() {
    private val args:CharacterImageFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.activity_character_image_fragment, container, false)

    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val ivChracterImageSingle =  requireActivity().findViewById<ImageView>(R.id.ivCharacterImageSingle)
        Glide.with(requireContext()).load(args.imageUrl).centerCrop().into(ivChracterImageSingle)
    }
}


