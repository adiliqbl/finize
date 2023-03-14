package com.adiliqbal.finize.database.base

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.adiliqbal.finize.database.AppDatabase
import java.io.IOException
import org.junit.After
import org.junit.Before
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
internal abstract class BaseDatabaseTest {

    protected lateinit var db: AppDatabase

    @Before
    fun onCreateDB() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(context, AppDatabase::class.java).build()
    }

    @After @Throws(IOException::class) open fun onCloseDB() = db.close()
}
