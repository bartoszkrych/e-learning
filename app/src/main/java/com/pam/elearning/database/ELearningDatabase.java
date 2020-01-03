package com.pam.elearning.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.pam.elearning.dao.AnswerDao;
import com.pam.elearning.dao.LessonDao;
import com.pam.elearning.dao.QuestionDao;
import com.pam.elearning.model.Answer;
import com.pam.elearning.model.Lesson;
import com.pam.elearning.model.Question;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Lesson.class, Question.class, Answer.class}, version = 1, exportSchema = false)
public abstract class ELearningDatabase extends RoomDatabase {

    public abstract LessonDao lessonDao();

    public abstract QuestionDao questionDao();

    public abstract AnswerDao answerDao();

    private static volatile ELearningDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    public static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public static ELearningDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (ELearningDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            ELearningDatabase.class, "e_learning_database")
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
