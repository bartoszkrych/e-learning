package com.pam.elearning.model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import lombok.Data;

@Data
@Entity(tableName = "LESSON")
public class Lesson {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    private Integer id;

    private String title;
    private String text;
}
