package com.defenseunicorns.flyaware.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "icao_table")
data class IcaoEntity(
    @PrimaryKey val icao: String
)