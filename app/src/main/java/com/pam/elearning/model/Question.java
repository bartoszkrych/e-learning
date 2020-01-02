package com.pam.elearning.model;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import lombok.Data;


@Data
@Entity(tableName = "QUESTION")
public class Question {

    @PrimaryKey
    private Integer id;

    private String contents;
}
