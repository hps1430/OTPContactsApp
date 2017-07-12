package in.harsh.otpcontactsapp;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by harsh singh on 12-07-2017.
 */

public class Logs_tab_Fragment extends Fragment {

    View LogstabView;


    DatabaseHelper_Adapter database_important;
    private ArrayList<String> arrayList ;
    private ArrayAdapter<String> arrayAdapter ;
    ListView listView;






    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        LogstabView = inflater.inflate(R.layout.fragment_logstab,container,false);

        database_important = new DatabaseHelper_Adapter(getContext()); //following line in DatabaseHelper_Adapter class would create database
        // that is get writable database function would create database.



        listView = (ListView) LogstabView.findViewById(R.id.listView_logs);
        arrayList = (ArrayList<String>) database_important.getallLogs().clone();
        arrayAdapter = new ArrayAdapter<String>(getContext(),android.R.layout.simple_list_item_1,arrayList);
        listView.setAdapter(arrayAdapter);












        return LogstabView;
    }
}
