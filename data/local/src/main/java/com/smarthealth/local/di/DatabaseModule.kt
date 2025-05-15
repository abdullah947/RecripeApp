package com.smarthealth.local.di

import androidx.room.Room
import com.smarthealth.local.data.db.database.DataBase
import org.koin.dsl.module


val databaseModule = module {

    single {
        Room.databaseBuilder(
            get(),
            DataBase::class.java,
            "Recipes.db"
        ).build()
    }

    single {
        get<DataBase>().recipeHistoryDao()
    }

    single {
        get<DataBase>().searchHistoryDao()
    }
}