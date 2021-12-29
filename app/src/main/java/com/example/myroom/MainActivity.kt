package com.example.myroom

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import com.example.myroom.data.Word
import com.example.myroom.data.WordRoomDataBase
import com.example.myroom.databinding.ActivityMainBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private lateinit var wordRoomDataBase:WordRoomDataBase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        wordRoomDataBase = WordRoomDataBase.getDataBase(this)

        binding.btn1.setOnClickListener {
            lifecycleScope.launch(Dispatchers.IO)
            {
                var builder = StringBuilder()
                var asc = wordRoomDataBase.wordDao().getAsc()

                asc?.let {

                    it.forEachIndexed { index, word ->
                        Log.w("MYUTIL", "index=${index}, word=${word.toString()}")
                        builder.append("index=$index,${word.toString()}, \n")
                    }
                }
                builder.append("========desc=====\n")
                Common.log("=========")
                var desc = wordRoomDataBase.wordDao().getDesc()
                desc?.let {
                    it.forEachIndexed { index, word ->
                        Log.w("MYUTIL", "index=${index}, word=${word.toString()}")
                        builder.append("index=$index,${word.toString()}, \n")
                    }
                }

//                条件name查找
                var wordName = wordRoomDataBase.wordDao().getByName("张三")
                builder.append("===searchName====\n")
                builder.append("$wordName\n")
//              根据id查找
                var wordId = wordRoomDataBase.wordDao().getById(1)
                builder.append("===search by id====\n")
                builder.append("$wordId\n")

                var word = wordRoomDataBase.wordDao().getFirstId()
                builder.append("===search first id====\n")
                builder.append("$word\n")

                word = wordRoomDataBase.wordDao().getLastId()
                builder.append("===search last id====\n")
                builder.append("$word\n")

                //更新， 返回 1 成功， 0 失败
                word.age = 199
                var num = wordRoomDataBase.wordDao().update(word)
                builder.append("===update last id age====\n")
                builder.append("$num\n")

                withContext(Dispatchers.Main)
                {
                    binding.tv1.text = builder.toString()
                }

            }

        }

//        test()

    }

    private fun test ()
    {
        lifecycleScope.launch(Dispatchers.IO){
            var word = Word( "三", "张三")
            wordRoomDataBase.wordDao().insert(word)
            word = Word( "四", "李四")
            wordRoomDataBase.wordDao().insert(word)
        }

    }
}