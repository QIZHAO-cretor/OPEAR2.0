package com.example.opera20;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.opera20.QuizContract.QuestionTable;

import java.util.ArrayList;

public class QuizDbHelper extends SQLiteOpenHelper {

        private static final String DATABASE_NAME = "GoQuiz.db";
        private static final int DATBASE_VERSION = 1;

        private SQLiteDatabase db;


        QuizDbHelper(Context context) {
            super(context, DATABASE_NAME,null, DATBASE_VERSION);
        }



        @Override
        public void onCreate(SQLiteDatabase db) {

            this.db = db;

            final String SQL_CREATE_QUESTIONS_TABLE = "CREATE TABLE " +
                    QuestionTable.TABLE_NAME + " ( " +
                    QuestionTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    QuestionTable.COLUMN_QUESTION + " TEXT, " +
                    QuestionTable.COLUMN_OPTION1 + " TEXT, " +
                    QuestionTable.COLUMN_OPTION2 + " TEXT, " +
                    QuestionTable.COLUMN_OPTION3 + " TEXT, " +
                    QuestionTable.COLUMN_OPTION4 + " TEXT, " +
                    QuestionTable.COLUMN_ANSWER_NR + " INTEGER" +
                    ")";

            db.execSQL(SQL_CREATE_QUESTIONS_TABLE);
            fillQuestionsTable();
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

            db.execSQL("DROP TABLE IF EXISTS " + QuestionTable.TABLE_NAME);
            onCreate(db);

        }


        private void fillQuestionsTable()
        {

            Questions q1 = new Questions("Quels instruments débutent l’ouverture de La Traviata ?","Les violons","Les hautbois","Les timbales","Les flûtes",1);
            addQuestions(q1);


            Questions q2 = new Questions("Combien de pages contient la partition d’Alto ?","12","25","121","58",4);
            addQuestions(q2);


            Questions q3 = new Questions("Quel genre musical accompagne la demande en mariage de Violetta?","Fox-trot","Marche funèbre","Valse","Reggae",2);
            addQuestions(q3);


            Questions q4 = new Questions("Quel thème célèbre chanté par le chœur la flûte double-t-elle ?","Addio","Ouverture","Zingarelle","Libiamo",4);
            addQuestions(q4);


            Questions q5 = new Questions("Quel est le prochain opéra au Zénith ? ","Carmen","La Traviata","Boule et bill","Avatar",2);
            addQuestions(q5);


            Questions q6 = new Questions("Dans quel air le hautbois dialogue-t-il avec Violetta ?","Addio","Libiamo","Zingarelle","Chœur des matadors",1);
            addQuestions(q6);


            Questions q7 = new Questions("Quel moment important les timbales accompagnent-elles ?","La rencontre avec Alfredo","La fête chez Flora","Le sermon de Germont","La mort de Violetta",4);
            addQuestions(q7);


            Questions q8 = new Questions("À quel ensemble de personnages les castagnettes sont-elles associées dans l’opéra ?","Les bohémiennes","Les matadors","Les invités de la fête","Les domestiques",1);
            addQuestions(q8);


            Questions q9 = new Questions("Dans combien de mesures la harpe se joue-t-elle dans la Traviata ?","15","24","2","65",2);
            addQuestions(q9);


            Questions q10 = new Questions("Quel thème le violoncelle expose-t-il dans l’ouverture ?","L’amour","La haine","Le désespoir","La joie",3);
            addQuestions(q10);

            Questions q11 = new Questions("De quel roman célèbre s’est inspiré Verdi pour composer La Traviata ?","Les 3 mousquetaires","Germinal","Harry Potter","La Dame aux Camélias",4);
            addQuestions(q11);

            Questions q12 = new Questions("Violetta exerçait la profession de :","Demi-mondaine","Fleuriste","Sans profession","Actrice",1);
            addQuestions(q12);

            Questions q13 = new Questions("Alfredo fait la connaissance de Violetta à :","Venise","Paris","Berlin","Londres",2);
            addQuestions(q13);

            Questions q14 = new Questions("La meilleure amie de Violetta se nomme :","Maria","Christina","Flora","Giselda",3);
            addQuestions(q14);

            Questions q15 = new Questions("Au début de l’opéra, Violetta est fiancée à :","Baron Douphol","Alfredo","Germont","Le docteur",1);
            addQuestions(q15);
        }

        private void addQuestions(Questions question){

            ContentValues cv = new ContentValues();
            cv.put(QuestionTable.COLUMN_QUESTION,question.getQuestion());
            cv.put(QuestionTable.COLUMN_OPTION1,question.getOption1());
            cv.put(QuestionTable.COLUMN_OPTION2,question.getOption2());
            cv.put(QuestionTable.COLUMN_OPTION3,question.getOption3());
            cv.put(QuestionTable.COLUMN_OPTION4,question.getOption4());
            cv.put(QuestionTable.COLUMN_ANSWER_NR,question.getAnswerNr());
            db.insert(QuestionTable.TABLE_NAME,null,cv);

        }

        public ArrayList<Questions> getAllQuestions() {

            ArrayList<Questions> questionList = new ArrayList<>();
            db = getReadableDatabase();



            String Projection[] = {

                    QuestionTable._ID,
                    QuestionTable.COLUMN_QUESTION,
                    QuestionTable.COLUMN_OPTION1,
                    QuestionTable.COLUMN_OPTION2,
                    QuestionTable.COLUMN_OPTION3,
                    QuestionTable.COLUMN_OPTION4,
                    QuestionTable.COLUMN_ANSWER_NR
            };



            Cursor c = db.query(QuestionTable.TABLE_NAME,
                    Projection,
                    null,
                    null,
                    null,
                    null,
                    null);


            if (c.moveToFirst()) {
                do {

                    Questions question = new Questions();
                    question.setQuestion(c.getString(c.getColumnIndex(QuestionTable.COLUMN_QUESTION)));
                    question.setOption1(c.getString(c.getColumnIndex(QuestionTable.COLUMN_OPTION1)));
                    question.setOption2(c.getString(c.getColumnIndex(QuestionTable.COLUMN_OPTION2)));
                    question.setOption3(c.getString(c.getColumnIndex(QuestionTable.COLUMN_OPTION3)));
                    question.setOption4(c.getString(c.getColumnIndex(QuestionTable.COLUMN_OPTION4)));
                    question.setAnswerNr(c.getInt(c.getColumnIndex(QuestionTable.COLUMN_ANSWER_NR)));

                    questionList.add(question);

                } while (c.moveToNext());

            }
            c.close();
            return questionList;

        }

    }

