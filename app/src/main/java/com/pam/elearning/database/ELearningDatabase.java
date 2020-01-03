package com.pam.elearning.database;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

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
                            .addCallback(callback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    private static RoomDatabase.Callback callback = new RoomDatabase.Callback() {
        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db) {
            super.onOpen(db);
            databaseWriteExecutor.execute(() -> {
                LessonDao lessonDao = INSTANCE.lessonDao();
                AnswerDao answerDao = INSTANCE.answerDao();
                QuestionDao questionDao = INSTANCE.questionDao();

                lessonDao.insert(new Lesson(1, "Chopin", "DEFAULT TXT ABOUT CHOPIN"));
                lessonDao.insert(new Lesson(2, "Polanski", "DEFAULT TXT ABOUT POLANSKI"));
                lessonDao.insert(new Lesson(3, "da Vinci", "DEFAULT TXT ABOUT DA VINCI"));
            });
        }
    };
}
