package com.defenseunicorns.flyaware.data.local

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface IcaoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(icao: IcaoEntity)

    @Delete
    suspend fun delete(icao: IcaoEntity)

    @Query("SELECT * FROM icao_table")
    suspend fun getAll(): List<IcaoEntity>

    @Query("SELECT * FROM icao_table")
    fun observeAll(): Flow<List<IcaoEntity>>
}