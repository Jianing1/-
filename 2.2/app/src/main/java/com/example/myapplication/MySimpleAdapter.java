package com.example.myapplication;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SimpleAdapter;

import java.util.List;
import java.util.Map;

public class MySimpleAdapter extends SimpleAdapter {

    private final int[] colors = new int[]{0x30ff0000, 0x300000FF};
    private final int cuttentIndex = -1;

    public MySimpleAdapter(Context context, List<? extends Map<String, ?>> data, int resource, String[] from, int[] to) {
        super(context, data, resource, from, to);
    }
    public View getView(int position, View convertView, ViewGroup parent){
        View view=super.getView(position,convertView,parent);
        int colorPos=position%colors.length;
        view.setBackgroundColor(colors[colorPos]);
        if(position==cuttentIndex)
            view.setBackgroundResource(R.color.purple_700);
        return view;
    }
}
