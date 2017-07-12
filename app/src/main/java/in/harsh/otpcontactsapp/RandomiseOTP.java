package in.harsh.otpcontactsapp;

import java.util.Random;

/**
 * Created by harsh singh on 13-07-2017.
 */

public class RandomiseOTP {

    String OTP=null;


    public String randomise(){

//        Now we will try to create random otp with the help of Random class.
//        range here is --->> range = maximumint - minimumint + 1;
//
//        int range = maximum - minimum + 1;
//        int randomNum =  random.nextInt(range) + minimum;



        Random random = new Random();

        int range1 = (10-1)+1;
        int range2 = (10-0)+1;

        int random_dig1 = random.nextInt(range1)+1;

        int random_dig2 = random.nextInt(range2)+0;

        int random_dig3 = random.nextInt(range2);

        int random_dig4 = random.nextInt(range2);

        int random_dig5 = random.nextInt(range2);

        int random_dig6 = random.nextInt(range2);




        OTP = String.valueOf(random_dig1)+String.valueOf(random_dig2)+String.valueOf(random_dig3)
                +String.valueOf(random_dig4)+String.valueOf(random_dig5)+String.valueOf(random_dig6);


        return OTP;

    }




}
