package com.example.seminar4;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private TextView tvRezultat;
    private ArrayList<Sandwich> listaSandwichuri = new ArrayList<>();
    private final ActivityResultLauncher<Intent> launcherLista =
            registerForActivityResult(
                    new ActivityResultContracts.StartActivityForResult(),
                    result -> {
                        if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                            ArrayList<Sandwich> actualizata =
                                    result.getData().getParcelableArrayListExtra("lista");
                            if (actualizata != null) {
                                listaSandwichuri = actualizata;
                                tvRezultat.setText("Sandwichuri in lista: " + listaSandwichuri.size());
                            }
                        }
                    });

    private final ActivityResultLauncher<Intent> launcherAdauga =
            registerForActivityResult(
                    new ActivityResultContracts.StartActivityForResult(),
                    result -> {
                        if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                            Sandwich sandwich = result.getData().getParcelableExtra("sandwich");
                            if (sandwich != null) {
                                listaSandwichuri.add(sandwich);
                                tvRezultat.setText(sandwich.toString());
                                Toast.makeText(this, sandwich.toString(), Toast.LENGTH_LONG).show();
                            }
                        }
                    });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        tvRezultat = findViewById(R.id.tvRezultat);

    }

    public void culegeDate(View view) {
        EditText et=findViewById(R.id.nume_ET);
        CheckBox cb=findViewById(R.id.eStudent_CB);

        String nume=et.getText().toString();
        Boolean eStudent=cb.isChecked();
    }

    public void deschideLista(View view) {
        Intent it = new Intent(this, ListaSandwichuriActivity.class);
        it.putParcelableArrayListExtra("lista", listaSandwichuri);
        launcherLista.launch(it);
    }

    public void deschideAdaugare(View view) {
        Intent it = new Intent(this, AdaugaSandwichActivity.class);
        launcherAdauga.launch(it);
    }
}