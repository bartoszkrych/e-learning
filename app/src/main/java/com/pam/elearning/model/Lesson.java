package com.pam.elearning.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Entity(tableName = "LESSON")
public class Lesson {

    @PrimaryKey(autoGenerate = true)
    private Integer id;

    private String title;
    private String text;
    private String url;

    public Lesson() {
    }
}
