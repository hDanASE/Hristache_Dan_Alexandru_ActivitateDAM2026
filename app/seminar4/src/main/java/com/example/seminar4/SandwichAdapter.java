package com.example.seminar4;

import android.content.Context;
import android.media.Rating;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.List;

public class SandwichAdapter extends BaseAdapter {
    private List<Sandwich> sandwichuri=null;
    private Context ctx;
    private int resurseLayout;

    public SandwichAdapter(List<Sandwich> sandwichuri, Context ctx, int resurseLayout) {
        this.sandwichuri = sandwichuri;
        this.ctx = ctx;
        this.resurseLayout = resurseLayout;
    }

    @Override
    public int getCount() {
        return sandwichuri.size();
    }

    @Override
    public Object getItem(int position) {
        return sandwichuri.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater=LayoutInflater.from(ctx);
        View v=inflater.inflate(resurseLayout,parent,false);
        TextView numeSw=v.findViewById(R.id.numeSw);
        TextView pretSw=v.findViewById(R.id.pretSw);
        TextView vegetarianSw=v.findViewById(R.id.vegetarianSw);
        TextView marimeSw=v.findViewById(R.id.marimeSw);
        TextView tipPaineSw=v.findViewById(R.id.tipPaineSw);
        TextView ratingSw=v.findViewById(R.id.ratingSw);
        TextView sosSw=v.findViewById(R.id.sosSw);
        Sandwich sandwich=(Sandwich)getItem(position);
        numeSw.setText(sandwich.getNume());
        Double pret=sandwich.getPret();
        String pretAux=pret.toString();
        pretSw.setText(pretAux);
        String veg=""+sandwich.isVegetarian();
        marimeSw.setText("Mediu");
        RadioGroup rgMarime=v.findViewById(R.id.rgMarime);
        RadioButton rbMic=v.findViewById(R.id.rbMic);
        RadioButton rbMare=v.findViewById(R.id.rbMare);
        int idButonApasat=rgMarime.getCheckedRadioButtonId();
        marimeSw.setText("Mediu");
        if(idButonApasat==R.id.rbMic){
            marimeSw.setText("Mic");
        }
        else if(idButonApasat==R.id.rbMare){
            marimeSw.setText("Mare");
        }
        Spinner spPaine=v.findViewById(R.id.spPaine);
        String tipPaine=spPaine.getSelectedItem().toString();
        tipPaineSw.setText(tipPaine);
        String a = ""+sandwich.getRating();
        return v;
    }
}
