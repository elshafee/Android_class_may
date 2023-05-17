package com.elshafee.androidclassmay.breakingbadapi.model

import android.provider.ContactsContract.CommonDataKinds.Nickname
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "character")
data class BreakingBadCharacter(
    @PrimaryKey
    @SerializedName("char_id")
    val id:Int,
    val name:String,
    val birthday:String,
    val occupation:Array<String>,
    val img:String?,
    val nickname: String,
    val status:String
) {
}