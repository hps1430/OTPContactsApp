package in.harsh.otpcontactsapp;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by harsh singh on 12-07-2017.
 */

public class ContactsAdapter extends ArrayAdapter {

    List list = new ArrayList();


    public ContactsAdapter(@NonNull Context context, @LayoutRes int resource) {
        super(context, resource);
    }


    @Override
    public void add(@Nullable Object object) {
        super.add(object);
        list.add(object);


    }


    @Override
    public int getCount() {
        return list.size();
    }

    @Nullable
    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return super.getView(position, convertView, parent);
    }







}
