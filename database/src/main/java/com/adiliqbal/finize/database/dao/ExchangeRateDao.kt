package com.adiliqbal.finize.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.adiliqbal.finize.database.model.ExchangeRateEntity

@Dao
abstract class ExchangeRateDao {

    @Query("SELECT * FROM `exchange-rates` WHERE `from` = :fromIso AND `to` = :toIso")
    abstract suspend fun get(fromIso: String, toIso: String): ExchangeRateEntity?

    @Transaction
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insert(vararg items: ExchangeRateEntity)

    @Transaction
    @Query("DELETE FROM `exchange-rates` WHERE `from` = :iso OR `to` = :iso")
    abstract suspend fun delete(iso: String)

    @Transaction
    @Query("DELETE FROM `exchange-rates`")
    abstract fun clear()
}
