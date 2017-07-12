package in.harsh.otpcontactsapp;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 * Created by harsh singh on 12-07-2017.
 */

public class DatabaseHelper_Adapter {

    database_important database_imp;

    public DatabaseHelper_Adapter(Context context) {


        database_imp = new database_important(context);
        database_imp.getWritableDatabase();


    }


    public ArrayList<String> getallLogs() {

        ArrayList<String> data = new ArrayList<String>();
        String[] col = {database_important.Field_Logid,
                database_important.Field_ContactName,
                database_important.Field_OTP,
                database_important.Field_OTP_creation_date
        };

        SQLiteDatabase db = database_imp.getReadableDatabase();
        Cursor cursor = db.query(database_important.Table_Name_LOGS,col,null,null,null,null,database_important.Field_OTP_creation_date);


        if (cursor!=null) {
            cursor.move(0);

        }
        while (cursor.moveToNext()) {

            int index_Contact_name = cursor.getColumnIndex(database_important.Field_ContactName);
            int index_OTP = cursor.getColumnIndex(database_important.Field_OTP);

            int index_OTP_creation_date = cursor.getColumnIndex(database_important.Field_OTP_creation_date);


            String cname = cursor.getString(index_Contact_name);

            String c_otp_date = cursor.getString(index_OTP_creation_date);

            String c_otp = cursor.getString(index_OTP);



            String row = cname.toUpperCase()+"\t"+c_otp_date+"\t"+c_otp;




            data.add(row);


        }

        db.close();
        cursor.close();
        return data;

    }






    static class database_important extends SQLiteOpenHelper {

        private Context context;
        private static final String Database_Name = "ContactsOTPlogs.db";
        private static final int Database_Version = 1;

        private static final String Table_Name_LOGS = "LogsTable";
        private static final String Field_Logid = "_id_log";
        private static final String Field_ContactName = "contact_name";
        private static final String Field_OTP_creation_date = "OTP_creation_date";
        private static final String Field_OTP = "OTP";













        String create_logs ="CREATE TABLE IF NOT EXISTS " + Table_Name_LOGS + "(" +Field_Logid+" INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,"
                +Field_ContactName+ " TEXT,"
                +Field_OTP+ " TEXT,"
                +Field_OTP_creation_date+ " TEXT);";









        public database_important(Context context) {
            super(context,Database_Name,null,Database_Version);
            this.context = context;

        }

        @Override
        public void onCreate(SQLiteDatabase db) {


            // sqLiteDatabase = new database_important_adapter(context).getWritableDatabase();

            try {


                db.execSQL(create_logs);


            } catch (Exception e) {
               e.printStackTrace();
            }


        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldversion, int newversion)

        {


            try {


                db.execSQL("DROP TABLE IF EXISTS " + Table_Name_LOGS);

                onCreate(db);
            } catch (SQLException e) {

                e.printStackTrace();

            }


        }





    }



}