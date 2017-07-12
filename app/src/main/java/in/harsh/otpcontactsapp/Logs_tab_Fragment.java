package in.harsh.otpcontactsapp;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by harsh singh on 12-07-2017.
 */

public class Logs_tab_Fragment extends Fragment {

    View LogstabView;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        LogstabView = inflater.inflate(R.layout.fragment_logstab,container,false);













        return LogstabView;
    }
}
