package in.harsh.otpcontactsapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class ComposeOTP extends AppCompatActivity {


    RandomiseOTP randomiseOTP;

    DatabaseHelper_Adapter databaseHelper_adapter;


    String mobileno,body_initial,otp,body,twilio_heroku_url;
    String contact_name , OTP_time;

    CurrentTime currentTime;

    private OkHttpClient mClient = new OkHttpClient();

    EditText message_edit;

    Button sendotp,refreshotp;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compose_otp);


        databaseHelper_adapter = new DatabaseHelper_Adapter(this);

        twilio_heroku_url = "https://harshtwiliosmsbackend.herokuapp.com";

        body_initial="Hi. Your OTP is: ";



        //collecting all the shared preferences

        SharedPreferences sharedPreferences =   getSharedPreferences("activefile", Context.MODE_PRIVATE);
        mobileno = sharedPreferences.getString("active_mobile"," ");

        contact_name = sharedPreferences.getString("active_firstname"," ")+" "+sharedPreferences.getString("active_lastname"," ");

        mobileno = sharedPreferences.getString("active_mobile"," ");












        // Now getting random OTP

        randomiseOTP = new RandomiseOTP();

        otp=randomiseOTP.randomise();


        body = body_initial+otp;


        message_edit = (EditText) findViewById(R.id.message);
        message_edit.setEnabled(false);
        message_edit.setClickable(true);
        message_edit.setText(body);







        sendotp = (Button) findViewById(R.id.button_send);
        sendotp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                body =  message_edit.getEditableText().toString();



                try {

                    Toast.makeText(getApplicationContext(), "Please Wait...", Toast.LENGTH_LONG).show();

                    post(twilio_heroku_url + "/sms", new Callback() {

                        @Override
                        public void onFailure(Call call, IOException e) {
                            Toast.makeText(getApplicationContext(), "Something went wrong!!", Toast.LENGTH_SHORT).show();

                            e.printStackTrace();
                        }

                        @Override
                        public void onResponse(Call call, final Response response) throws IOException {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                   Toast.makeText(getApplicationContext(),response.toString(),Toast.LENGTH_LONG).show();
                                    Toast.makeText(getApplicationContext(), "SMS Sent!!", Toast.LENGTH_LONG).show();

                                    currentTime = new CurrentTime(ComposeOTP.this);
                                    OTP_time = currentTime.getdatetimeFormat().toString();


                                    long id = databaseHelper_adapter.add_logEntry(contact_name,OTP_time,otp,mobileno);

                                    if(id<0)
                                    {
                                        Toast.makeText(getApplicationContext(), "Log addition failed!!", Toast.LENGTH_LONG).show();

                                    }
                                    else if (id>0)
                                    {


                                    }




                                    Intent intent = new Intent(ComposeOTP.this, MainActivity.class);
                                    startActivity(intent);

                                }
                            });
                        }
                    });
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }


                });




        refreshotp = (Button) findViewById(R.id.button_refreshotp);
        refreshotp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


            randomiseOTP = new RandomiseOTP();

            otp = randomiseOTP.randomise();

            body = body_initial+otp;

            message_edit.setText(body);

                Toast.makeText(getApplicationContext(), "OTP Refreshed!!", Toast.LENGTH_LONG).show();








            }
        });


    }



    Call post(String url, Callback callback) throws IOException {
        RequestBody formBody = new FormBody.Builder()
                .add("To", mobileno)
                .add("Body",body)
                .build();
        Request request = new Request.Builder()
                .url(url)
                .post(formBody)
                .build();
        Call response = mClient.newCall(request);
        response.enqueue(callback);
        return response;
    }

}
