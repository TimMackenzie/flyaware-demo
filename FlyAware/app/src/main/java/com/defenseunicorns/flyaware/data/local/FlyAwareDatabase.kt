package com.defenseunicorns.flyaware.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [IcaoEntity::class], version = 1)
abstract class FlyAwareDatabase : RoomDatabase() {
    abstract fun icaoDao(): IcaoDao
}