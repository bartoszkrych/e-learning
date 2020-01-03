package com.pam.elearning.model;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Entity(tableName = "ANSWER", foreignKeys = @ForeignKey(
        entity = Question.class,
        parentColumns = "id",
        childColumns = "question_id",
        onDelete = ForeignKey.CASCADE))
public class Answer {

    @PrimaryKey
    private Integer id;

    private String contents;
    private Integer question_id;

    private Boolean isCorrect = false;
    private Boolean isSelected = false;
}
