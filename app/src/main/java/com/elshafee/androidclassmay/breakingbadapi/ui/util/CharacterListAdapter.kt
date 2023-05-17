package com.elshafee.androidclassmay.breakingbadapi.ui.util

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.elshafee.androidclassmay.R
import com.elshafee.androidclassmay.breakingbadapi.model.BreakingBadCharacter
import com.elshafee.androidclassmay.databinding.ItemCharacterBinding


class CharacterListAdapter(private val clickCallBack: ((BreakingBadCharacter) -> Unit)?) :
    ListAdapter<BreakingBadCharacter, CharacterListAdapter.CharacterViewHolder>(CharacterCompare()) {

    class CharacterViewHolder(val binding: ItemCharacterBinding) :
        RecyclerView.ViewHolder(binding.root) {
        private val tvCharacterName: TextView = itemView.findViewById(R.id.tvCharacterName)
        private val tvCharacterNickName: TextView = itemView.findViewById(R.id.tvCharacterNickName)
        private val tvCharacterBirthday: TextView = itemView.findViewById(R.id.tvCharacterBirthday)
        private val tvCharcterOccupation: TextView =
            itemView.findViewById(R.id.tvCharacterOccupation)
        private val tvCharacterStatus: TextView = itemView.findViewById(R.id.tvCharacterStatus)
        private val ivCharcterImage: ImageView = itemView.findViewById(R.id.ivCharterImage)


        fun bind(characte: BreakingBadCharacter) {
            tvCharacterName.text = characte.name
            tvCharacterBirthday.text = characte.birthday
            tvCharacterNickName.text = characte.nickname
            tvCharacterStatus.text = characte.status
            tvCharcterOccupation.text = characte.occupation.joinToString(", ")
            if (characte.img != null) {
                Glide.with(itemView).load(characte.img).centerCrop().into(ivCharcterImage)
            }
        }


        companion object {
            fun create(parent: ViewGroup): CharacterViewHolder {
                return CharacterViewHolder(
                    ItemCharacterBinding.inflate(
                        LayoutInflater.from(parent.context), parent, false
                    )
                )
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        return CharacterViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        val character = getItem(position)
        holder.bind(character)
        holder.itemView.setOnClickListener{
            clickCallBack?.invoke(character)
        }
    }


}

class CharacterCompare : DiffUtil.ItemCallback<BreakingBadCharacter>() {
    override fun areItemsTheSame(
        oldItem: BreakingBadCharacter, newItem: BreakingBadCharacter
    ): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(
        oldItem: BreakingBadCharacter, newItem: BreakingBadCharacter
    ): Boolean {
        return oldItem.name == newItem.name && oldItem.nickname == newItem.nickname && oldItem.img == newItem.img && oldItem.status == newItem.status && oldItem.birthday == newItem.birthday && oldItem.occupation == newItem.occupation
    }
}