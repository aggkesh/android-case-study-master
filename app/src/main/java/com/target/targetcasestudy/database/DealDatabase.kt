package com.target.targetcasestudy.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Deal::class, Price::class], version = 1)
abstract class DealDatabase : RoomDatabase() {
    abstract fun dealDao(): DealDao
}