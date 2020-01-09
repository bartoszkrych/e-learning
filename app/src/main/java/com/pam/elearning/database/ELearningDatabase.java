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

                lessonDao.insert(new Lesson(
                        1,
                        "Chopin",
                        "Frédéric François Chopin (UK: /ˈʃɒpæ̃/, US: /ʃoʊˈpæn/, French: [ʃɔpɛ̃], Polish: [ˈʂɔpɛn]; 1 March 1810 – 17 October 1849) was a Polish composer and virtuoso pianist of the Romantic era who wrote primarily for solo piano. He has maintained worldwide renown as a leading musician of his era, one whose \"poetic genius was based on a professional technique that was without equal in his generation.\n" +
                                "\n" +
                                "Chopin was born Fryderyk Franciszek Chopin in the Duchy of Warsaw and grew up in Warsaw, which in 1815 became part of Congress Poland. A child prodigy, he completed his musical education and composed his earlier works in Warsaw before leaving Poland at the age of 20, less than a month before the outbreak of the November 1830 Uprising. At 21, he settled in Paris. Thereafter—in the last 18 years of his life—he gave only 30 public performances, preferring the more intimate atmosphere of the salon. He supported himself by selling his compositions and by giving piano lessons, for which he was in high demand. Chopin formed a friendship with Franz Liszt and was admired by many of his other musical contemporaries (including Robert Schumann). ",
                        "http://www.amclassical.com/mp3/amclassical_chopin_prelude_in_c_minor.mp3"));
                lessonDao.insert(new Lesson(
                        2,
                        "Big Buck Bunny",
                        "Big Buck Bunny (code-named Project Peach) is a 2008 short computer-animated comedy film featuring animals of the forest, made by the Blender Institute, part of the Blender Foundation. Like the foundation's previous film, Elephants Dream, the film was made using Blender, a free and open-source software application for 3D computer modeling and animation developed by the same foundation. Unlike that earlier project, the tone and visuals departed from a cryptic story and dark visuals to one of comedy, cartoons, and light-heartedness.\n" +
                        "\n" +
                                "It was released as an open-source film under the Creative Commons Attribution 3.0 license.",
                        "http://clips.vorwaerts-gmbh.de/VfE_html5.mp4"));
                lessonDao.insert(new Lesson(
                        3,
                        "da Vinci",
                        "Leonardo di ser Piero da Vinci (Italian: [leoˈnardo di ˌsɛr ˈpjɛːro da (v)ˈvint); 14/15 April 1452 – 2 May 1519), known as Leonardo da Vinci (English: /ˌliːəˈnɑːrdoʊ də ˈvɪntʃi, ˌliːoʊˈ-, ˌleɪoʊˈ-/ LEE-ə-NAR-doh də VIN-chee, LEE-oh-, LAY-oh-), was an Italian polymath of the Renaissance whose areas of interest included invention, drawing, painting, sculpture, architecture, science, music, mathematics, engineering, literature, anatomy, geology, astronomy, botany, paleontology, and cartography. He has been variously called the father of palaeontology, ichnology, and architecture, and is widely considered one of the greatest painters of all time (despite perhaps only 15 of his paintings having survived).\n" +
                                "\n" +
                                "Born out of wedlock to a notary, Piero da Vinci, and a peasant woman, Caterina, in Vinci, in the region of Florence, Italy, Leonardo was educated in the studio of the renowned Italian painter Andrea del Verrocchio. Much of his earlier working life was spent in the service of Ludovico il Moro in Milan, and he later worked in Rome, Bologna and Venice. He spent his last three years in France, where he died in 1519. ",
                        "https://drukarnia-krakowska.pl/wp-content/uploads/2019/04/Mona-Lisa-Leonardo-da-Vinci-reprodukcje-obrazow-galeria-sztuki.jpg"));
                questionDao.insert(new Question(4, "Where was Frédéric François Chopin born?", 1));
                answerDao.insert(new Answer(5, "French", 4, false));
                answerDao.insert(new Answer(6, "Poland", 4, true));
                answerDao.insert(new Answer(7, "United States", 4, false));
                questionDao.insert(new Question(8, "Big Buck Bunny was made using...", 2));
                answerDao.insert(new Answer(9, "Blender", 8, true));
                answerDao.insert(new Answer(10, "3ds Max", 8, false));
                answerDao.insert(new Answer(11, "Maya", 8, false));
                questionDao.insert(new Question(12, "Who taught Leonardo Da Vinci?", 3));
                answerDao.insert(new Answer(13, "Botticelli", 12, false));
                answerDao.insert(new Answer(14, "di Lodovico", 12, false));
                answerDao.insert(new Answer(15, "del Verrocchio", 12, true));
            });
        }
    };
}
