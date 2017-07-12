package in.harsh.otpcontactsapp;

import android.content.Context;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by harsh singh on 13-07-2017.
 */

public class CurrentTime {



    public CurrentTime(Context context){




    }

    public String getdateformat(){

        Calendar calendar = Calendar.getInstance();

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MMM/yyyy");
        String date = simpleDateFormat.format(calendar.getTime()).toString();



        return date;



    }


    public String getdatetimeFormat(){


        Calendar calendar2 = Calendar.getInstance();

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MMM/yyyy-kk:mm");
        String dateandtime = simpleDateFormat.format(calendar2.getTime()).toString();

        return dateandtime;
    }




}
