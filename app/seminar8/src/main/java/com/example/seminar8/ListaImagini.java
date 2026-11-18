package com.example.seminar8;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class ListaImagini extends AppCompatActivity {
    private ArrayList<Bitmap>imagini=new ArrayList<>();
    private List<ImaginiDomeniu> lista=new ArrayList<ImaginiDomeniu>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_lista_imagini);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        List<String> linkuriImagini=new ArrayList<>();
        linkuriImagini.add("https://thumb.wikimedia.org/wikipedia/commons/thumb/0/0b/Bacon%2C_lettuce%2C_tomato%2C_and_avocado.jpg/1280px-Bacon%2C_lettuce%2C_tomato%2C_and_avocado.jpg?utm_source=en.wikipedia.org&utm_campaign=index&utm_content=thumbnail");
        linkuriImagini.add("https://www.tasteofhome.com/wp-content/uploads/2026/02/Chicken-Club-Sandwich_FT25_280733_EC_1202_5.jpg");
        linkuriImagini.add("https://www.sharmispassions.com/wp-content/uploads/2026/02/cream-cheese-sandwich6.jpg");
        linkuriImagini.add("https://www.seriouseats.com/thmb/qf85SxAxYIJayGn9TS3DBYOoyEA=/1500x0/filters:no_upscale():max_bytes(150000):strip_icc():focal(749x0:751x2)/__opt__aboutcom__coeus__resources__content_migration__serious_eats__seriouseats.com__images__2016__04__20160418-sandwich-recipes-roundup-16-542d52343f3743799a6663852a787f47.jpg");
        linkuriImagini.add("https://www.foodandwine.com/thmb/UGhU2wG_ebXAkFqUGlqSTo_7Vv8=/1500x0/filters:no_upscale():max_bytes(150000):strip_icc()/chopped-cheese-FT-RECIPE0725-76cbe240b84f479a8f55d64d070fcafc.jpg");
        final List<String> linkuriDetalii=new ArrayList<>();
        linkuriDetalii.add("https://en.wikipedia.org/wiki/BLT");
        linkuriDetalii.add("https://en.wikipedia.org/wiki/Club_sandwich");
        linkuriDetalii.add("https://en.wikipedia.org/wiki/Cucumber_sandwich");
        linkuriDetalii.add("https://en.wikipedia.org/wiki/Sandwich");
        linkuriDetalii.add("https://en.wikipedia.org/wiki/Chopped_cheese");

        ListView lv=findViewById(R.id.imagini);
        Executor executor= Executors.newSingleThreadExecutor();
        Handler handler=new Handler(Looper.myLooper());
        executor.execute(new Runnable() {
            @Override
            public void run() {
                for(String link:linkuriImagini){
                    HttpURLConnection con=null;
                    try{
                        URL url=new URL(link);
                        con=(HttpURLConnection) url.openConnection();
                        // unele site-uri (Wikimedia, Food&Wine) refuza User-Agent-ul implicit Java
                        con.setRequestProperty("User-Agent","Mozilla/5.0 (Linux; Android 14)");
                        InputStream is=con.getInputStream();
                        imagini.add(BitmapFactory.decodeStream(is));
                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                        if(con!=null){
                            con.disconnect();
                        }
                    }
                }
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        for(int i=0;i<imagini.size();i++){
                            lista.add(new ImaginiDomeniu("Sandwich "+(i+1),imagini.get(i),linkuriDetalii.get(i)));
                        }
                        ImagineAdapter adapter=new ImagineAdapter(lista,ListaImagini.this,R.layout.adaptor_personalizat);
                        lv.setAdapter(adapter);
                    }
                });
            }
        });

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent it=new Intent(ListaImagini.this, WebViewActivity.class);
                it.putExtra("link",lista.get(position).getLink());
                startActivity(it);
            }
        });
    }
}