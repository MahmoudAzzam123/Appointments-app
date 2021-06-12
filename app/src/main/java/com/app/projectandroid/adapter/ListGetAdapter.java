package com.app.projectandroid.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.app.projectandroid.R;
import com.app.projectandroid.data.ReservationData;

import java.util.ArrayList;

public class ListGetAdapter extends BaseAdapter {
    ArrayList<ReservationData> data;
    Context context;

    public ListGetAdapter(ArrayList<ReservationData> data, Context context) {
        this.data = data;
        this.context = context;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int i) {
        return data.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View v = LayoutInflater.from(context).inflate(R.layout.custom_get_resrvation, null);

        TextView name = v.findViewById(R.id.custom_get_name);
        TextView date = v.findViewById(R.id.custom_get_date);
        TextView time = v.findViewById(R.id.custom_get_time);

        ReservationData d = (ReservationData) getItem(i);
        name.setText(d.getName());
        date.setText(d.getDate());
        time.setText(d.getTime());

        return v;
    }
}
