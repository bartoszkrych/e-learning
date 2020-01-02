package com.pam.elearning.model;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import lombok.Data;


@Data
@Entity(tableName = "QUESTION", foreignKeys = @ForeignKey(
        entity = Lesson.class,
        parentColumns = "id",
        childColumns = "lesson_id",
        onDelete = ForeignKey.CASCADE))
public class Question {

    @PrimaryKey
    private Integer id;

    private String contents;
    private Integer lesson_id;
}
