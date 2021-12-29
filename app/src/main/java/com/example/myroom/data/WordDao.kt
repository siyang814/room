package com.example.myroom.data

import androidx.lifecycle.LiveData
import androidx.room.*

/**
 *   yy
 *   2021/12/28
 */
@Dao
interface WordDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert (word:Word)
    @Delete
    fun delete(word:Word):Int
    @Update
    fun update(word: Word):Int
    @Query("select * from word order by id asc")
    fun getAsc ():List<Word>

    @Query("select * from word order by id desc")
    fun getDesc ():List<Word>

    @Query("select * from word where name in (:selectName) order by word desc limit 1")
    fun getByName (selectName:String):Word

    @Query("select * from word where id = :selectId order by word desc limit 1")
    fun getById (selectId:Long):Word

    @Query("select * from word order by id desc limit 1")
    fun getLastId ():Word

    @Query("select * from word order by id asc limit 1")
    fun getFirstId ():Word

}