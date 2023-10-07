package com.dcca.jane.mmy.data;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import android.content.Context;

/*
 * Created by Luke on 03/02/2018. Adapted from The Android Open Source Project's work -->
 *
 * Copyright 2017, The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */


@Database(entities = {Ingredient.class, Meal.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase{

    private static AppDatabase dbInstance;

    public abstract IngredientDao ingredientModel();
    public abstract MealDao mealModel();


    public static AppDatabase getInMemoryDatabase(Context context) {
        if(dbInstance == null){
            dbInstance = Room.inMemoryDatabaseBuilder(context.getApplicationContext(), AppDatabase.class)
////                    For testing purposes only! remove from main thread in future build
                    .allowMainThreadQueries()
                    .build();
        }
        return dbInstance;
    }

    public static void destroyInstance(){dbInstance = null;}
}
