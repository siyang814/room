package com.example.myroom.data

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

/**
 *   yy
 *   2021/12/28
 */
@Entity
data class Word(
    var word:String?="",
    var name:String?="",
    var age:Int=0,
    @PrimaryKey(autoGenerate = true)
    var id:Long=0,
    var header:String?="",
    var size:Int = 0,
)
{
    @Ignore
    constructor(word: String, name: String, age: Int, header: String?, size: Int):this()
    {
        this.word = word
        this.name = name
        this.age = age
        this.header = header
        this.size = size
    }

    override fun toString(): String {
        return "Word(word=$word, name=$name, age=$age, id=$id, header=$header, size=$size)"
    }


}

