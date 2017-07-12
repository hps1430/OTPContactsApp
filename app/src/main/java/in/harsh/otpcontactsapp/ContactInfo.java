package in.harsh.otpcontactsapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ContactInfo extends AppCompatActivity {



    EditText contact_name , contact_number;




    Button sendmessage ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_info);



        SharedPreferences sharedPreferences = getSharedPreferences("activefile", Context.MODE_PRIVATE);
        String firstname = sharedPreferences.getString("active_firstname","");
        String lastname  = sharedPreferences.getString("active_lastname","");
        String mobileno  = sharedPreferences.getString("active_mobile","");





        contact_name = (EditText) findViewById(R.id.person_name);
        contact_name.setText(firstname+" "+lastname);
        contact_name.setEnabled(false);
        contact_name.setClickable(true);



        contact_number = (EditText) findViewById(R.id.person_contact_edit);
        contact_number.setText(mobileno);



        sendmessage = (Button) findViewById(R.id.button_sendmessage);
        sendmessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                int check_results = check_arguments();


                 if(check_results==0)
                 {
                     return;

                 }
                else{


                     SharedPreferences.Editor editor = getSharedPreferences("activefile",Context.MODE_PRIVATE).edit();
                     editor.putString("active_mobile",contact_number.getText().toString());
                     editor.commit();


                     Intent intent = new Intent(ContactInfo.this,ComposeOTP.class);
                     startActivity(intent);



                 }








            }

            private int check_arguments() {

                int result = 1;


                if (TextUtils.isEmpty(contact_number.getText().toString())) {

                    contact_number.setError("Input Contact number");
                    result = 0;

                    return result;

                }
                if (contact_number.getText().toString().length()<10) {

                    contact_number.setError("This number does not look good enough");
                    result = 0;

                    return result;

                } else {

                    return result;
                }
            }


        });





    }




}
