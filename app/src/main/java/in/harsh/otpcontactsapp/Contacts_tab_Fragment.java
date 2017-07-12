package in.harsh.otpcontactsapp;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;


/**
 * Created by harsh singh on 12-07-2017.
 */

public class Contacts_tab_Fragment extends Fragment {


    View Contactstabview;

    String Json_string = null;

    JSONObject jsonObject;

    JSONArray jsonArray;

    ArrayList<String> firstnames,lastnames, mobilenos,arraylist_firstandlastname;





    ArrayAdapter arrayadapter_contacts;


    ListView listview_contactsObject;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        Contactstabview = inflater.inflate(R.layout.fragment_contactstab,container,false);


        // first of all we will initialize our arraylists

        arraylist_firstandlastname = new ArrayList<String>();

        firstnames = new ArrayList<String>();

        lastnames = new ArrayList<String>();

        mobilenos = new ArrayList<String>();





        // now we will try to convert json file into a string so that we are able to parse it further.



        // first of all we need to load the json file

        Json_string = loadJson();


        //Now parse the json string to get contacts out of it

        parsejson(Json_string);




        listview_contactsObject = Contactstabview.findViewById(R.id.listView_contacts);

        arrayadapter_contacts = new ArrayAdapter(getContext(),android.R.layout.simple_list_item_1,arraylist_firstandlastname);
        listview_contactsObject.setAdapter(arrayadapter_contacts);



        listview_contactsObject.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                int position  = listview_contactsObject.getCheckedItemPosition();

                setSharedpreferences(position);

                Intent intent = new Intent(getActivity(),ContactInfo.class);
                startActivity(intent);






            }
        });


        return Contactstabview;
    }

    private void setSharedpreferences(int position) {

       //As position for all the arraylist would be similar and all other corresponding parts could be gotten from there.


        SharedPreferences.Editor editor = getActivity().getSharedPreferences("activefile", Context.MODE_PRIVATE).edit();
        editor.putString("active_firstname",firstnames.get(position));
        editor.putString("active_lastname",lastnames.get(position));
        editor.putString("active_mobile",mobilenos.get(position));
        editor.commit();








    }


    private String loadJson() {



        InputStream is = getResources().openRawResource(R.raw.contacts_json);
        Writer writer = new StringWriter();
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            String line = reader.readLine();
            while (line != null) {
                writer.write(line);
                line = reader.readLine();
            }
        } catch (Exception e) {
            Log.e("Logreadjson", "Unhandled exception while using JSONResourceReader", e);
        } finally {
            try {
                is.close();
            } catch (Exception e) {
                Log.e("logreadjson2", "Unhandled exception while using JSONResourceReader", e);
            }
        }

        return writer.toString();





    }

    private void parsejson(String Json_string) {

        if(Json_string==null){


            Toast.makeText(getContext(),"JSON IS EMPTY!!",Toast.LENGTH_LONG).show();
            return;

        }

        try {
            jsonObject = new JSONObject(Json_string);

            jsonArray = jsonObject.getJSONArray("Contacts");


            String firstname , lastname , mobile ;

            int count=0;


            while(count<jsonArray.length()){


                JSONObject inner_jsonobject = jsonArray.getJSONObject(count);

                firstname = inner_jsonobject.getString("FirstName");

                lastname = inner_jsonobject.getString("LastName");

                mobile = inner_jsonobject.getString("Contactno");



                arraylist_firstandlastname.add(firstname+"\t"+lastname);
                firstnames.add(firstname);
                lastnames.add(lastname);
                mobilenos.add(mobile);


                count++;



            }

        } catch (JSONException e) {
            e.printStackTrace();
        }


    }


}
