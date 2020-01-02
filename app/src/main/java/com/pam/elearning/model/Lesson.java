package com.pam.elearning.model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import lombok.Data;

@Data
@Entity(tableName = "LESSON", foreignKeys = @ForeignKey(
        entity = Question.class,
        parentColumns = "id",
        childColumns = "question_id",
        onDelete = ForeignKey.CASCADE))
public class Lesson {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    private Integer id;

    private String title;
    private String text;

    private Integer question_id;
}
