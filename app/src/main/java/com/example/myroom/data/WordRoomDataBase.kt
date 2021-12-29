package com.example.myroom.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

/**
 *   yy
 *   2021/12/28
 */
@Database(entities = [Word::class], version = 3, exportSchema = true)
abstract class WordRoomDataBase : RoomDatabase() {

    abstract fun wordDao():WordDao

    companion object{

        var migration_1_2 = object : Migration(1, 2){
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("ALTER TABLE word ADD header TEXT")
            }
        }

        var migration_2_3 = object : Migration(2, 3){
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("ALTER TABLE word ADD size INTGER Default 0 not null")
            }
        }

        @Volatile
        private var instance:WordRoomDataBase?=null

        fun getDataBase (context: Context):WordRoomDataBase
        {
            val tempInstance = instance
            if ( tempInstance != null )
            {
                return tempInstance
            }
            synchronized(this)
            {
                val instan = Room.databaseBuilder(context.applicationContext,
                                    WordRoomDataBase::class.java,
                                    "word_database")
                    .addMigrations(migration_1_2, migration_2_3)
                    .build()
                instance = instan
                return instan
            }
        }

    }

}