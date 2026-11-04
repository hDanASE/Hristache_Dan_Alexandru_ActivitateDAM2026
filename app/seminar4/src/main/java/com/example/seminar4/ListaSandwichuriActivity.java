package com.example.seminar4;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class ListaSandwichuriActivity extends AppCompatActivity {

    private ArrayList<Sandwich> lista;
    private ArrayAdapter<Sandwich> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_lista_sandwichuri);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        lista = getIntent().getParcelableArrayListExtra("lista");
        if (lista == null) {
            lista = new ArrayList<>();
        }

//        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, lista);
        ListView listView = findViewById(R.id.lvSandwichuri);
        listView.setAdapter(adapter);

        trimiteRezultat();

        listView.setOnItemClickListener((parent, view, position, id) ->
                Toast.makeText(this, lista.get(position).toString(), Toast.LENGTH_LONG).show());

        listView.setOnItemLongClickListener((parent, view, position, id) -> {
            lista.remove(position);
            adapter.notifyDataSetChanged();
            trimiteRezultat();
            return true;
        });
    }

    private void trimiteRezultat() {
        Intent rezultat = new Intent();
        rezultat.putParcelableArrayListExtra("lista", lista);
        setResult(RESULT_OK, rezultat);
    }
}
