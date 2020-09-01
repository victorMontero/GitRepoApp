package com.android.gitrepoapp.db

import android.content.Context
import androidx.databinding.adapters.Converters
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.android.gitrepoapp.models.RepositoryResponseItem

@Database(
    entities = [RepositoryResponseItem::class],
    version = 1
)

@TypeConverters(com.android.gitrepoapp.db.Converters::class)
abstract class RepositoryDatabase : RoomDatabase() {

    abstract fun getRepositoryDao(): RepositoryDao

    companion object {
        @Volatile
        private var instance: RepositoryDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: createDatabase(context).also { instance = it }
        }

        private fun createDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                RepositoryDatabase::class.java,
                "repository_db.db"
            ).build()
    }
}


