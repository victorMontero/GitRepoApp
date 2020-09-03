package com.android.gitrepoapp.db

import androidx.room.TypeConverter
import com.android.gitrepoapp.models.Owner

class Converters {


    @TypeConverter
    fun fromOwner(owner : Owner) : String {
        return owner.login
    }

    @TypeConverter
        fun toOwner(login: String): Owner{
        return Owner(login, login, login, login, login, true, login, login, login, login)
    }
}