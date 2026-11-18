package com.example.seminar8;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

public class ImagineAdapter extends BaseAdapter {
    private List<ImaginiDomeniu>imagini=null;
    private Context ctx;
    private int resursaLayout;

    public ImagineAdapter(List<ImaginiDomeniu> imagini, Context ctx, int resursaLayout) {
        this.imagini = imagini;
        this.ctx = ctx;
        this.resursaLayout = resursaLayout;
    }

    public List<ImaginiDomeniu> getImagini() {
        return imagini;
    }

    public void setImagini(List<ImaginiDomeniu> imagini) {
        this.imagini = imagini;
    }

    public Context getCtx() {
        return ctx;
    }

    public void setCtx(Context ctx) {
        this.ctx = ctx;
    }

    public int getResursaLayout() {
        return resursaLayout;
    }

    public void setResursaLayout(int resursaLayout) {
        this.resursaLayout = resursaLayout;
    }

    @Override
    public int getCount() {
        return imagini.size();
    }

    @Override
    public Object getItem(int position) {
        return imagini.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater= LayoutInflater.from(ctx);
        View v=inflater.inflate(resursaLayout,parent,false);
        ImageView ivImagine=v.findViewById(R.id.imagineIVCustom);
        TextView tvDescriere=v.findViewById(R.id.tvDescriereCustom);

        ImaginiDomeniu imagine=(ImaginiDomeniu) getItem(position);
        ivImagine.setImageBitmap(imagine.getImagine());
        tvDescriere.setText(imagine.getTextAfisat());

        return v;
    }
}
