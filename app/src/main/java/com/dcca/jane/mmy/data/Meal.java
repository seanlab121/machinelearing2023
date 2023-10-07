package com.dcca.jane.mmy.data;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;
import androidx.annotation.NonNull;

import java.util.Date;

/**
 * Created by Luke on 02/02/2018.
 *
 * Structure for Meals in database.
 */
@Entity
@TypeConverters(DateConverter.class)
public class Meal implements Comparable<Meal>{
    @PrimaryKey(autoGenerate = true)
    public long id;

//    i.e. breakfast (1), lunch (2), dinner (3), snacks (4).
    public int mealType;

    public String mealTitle;

    public Date mealTime;

    public Double totalCalories;
    public Double totalCarbs;
    public Double totalSugars;
    public Double totalFat;
    public Double totalSats;
    public Double totalProtein;
    public Double totalSodium;

    public int compareTo(@NonNull Meal m) {
        return this.mealType - m.mealType;
    }
}
