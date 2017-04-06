package com.bagicode.www.bagipukesmasvideo;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by bagicode on 06/04/17.
 */

public class CostumListLaporAdapter extends BaseAdapter {

    private Activity activity;
    private LayoutInflater inflater;
    private List<model_lapor> movieItems;

    public CostumListLaporAdapter(Activity activity, List<model_lapor> movieItems) {
        this.activity = activity;
        this.movieItems = movieItems;
    }

    @Override
    public int getCount() {
        return movieItems.size();
    }

    @Override
    public Object getItem(int location) {
        return movieItems.get(location);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (inflater == null)
            inflater = (LayoutInflater) activity
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null)
            convertView = inflater.inflate(R.layout.costum_listlapor, null);


        TextView judul = (TextView) convertView.findViewById(R.id.list_laporjudul);
        TextView tgl = (TextView) convertView.findViewById(R.id.list_dr);

        model_lapor m = movieItems.get(position);

        judul.setText(m.get_judul());
        tgl.setText(m.get_tgl());

        return convertView;
    }
}
