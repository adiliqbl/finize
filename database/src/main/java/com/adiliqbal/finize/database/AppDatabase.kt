package com.adiliqbal.finize.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.adiliqbal.finize.database.converters.DateConverters
import com.adiliqbal.finize.database.converters.ListConverters
import com.adiliqbal.finize.database.dao.AccountDao
import com.adiliqbal.finize.database.dao.BudgetDao
import com.adiliqbal.finize.database.dao.TransactionDao
import com.adiliqbal.finize.database.dao.UserDao
import com.adiliqbal.finize.database.model.AccountEntity
import com.adiliqbal.finize.database.model.BudgetEntity
import com.adiliqbal.finize.database.model.TransactionEntity
import com.adiliqbal.finize.database.model.UserWithProfile

@Database(
    entities =
    [
        UserWithProfile::class,
        AccountEntity::class,
        BudgetEntity::class,
        TransactionEntity::class
    ],
    version = 1,
    autoMigrations = []
)
@TypeConverters(DateConverters::class, ListConverters::class)
internal abstract class AppDatabase : RoomDatabase() {

    companion object {

        private const val DB_NAME = "finize-database"

        @Volatile
        private var INSTANCE: AppDatabase? = null

        internal fun getInstance(context: Context): AppDatabase =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: buildDatabase(context).also {
                    INSTANCE = it
                }
            }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(context.applicationContext, AppDatabase::class.java, DB_NAME)
                .fallbackToDestructiveMigration()
                .build()
    }

    abstract fun userDao(): UserDao

    abstract fun accountDao(): AccountDao

    abstract fun budgetDao(): BudgetDao

    abstract fun transactionDao(): TransactionDao
}
