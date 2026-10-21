package com.example.seminar4;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class AdaugaSandwichActivity extends AppCompatActivity {

    private EditText etNume, etPret;
    private CheckBox cbVegetarian;
    private RadioGroup rgMarime;
    private Spinner spPaine;
    private RatingBar rbRating;
    private Switch swSos;
    private Button btnSalveaza;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_adauga_sandwich);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        etNume = findViewById(R.id.etNume);
        etPret = findViewById(R.id.etPret);
        cbVegetarian = findViewById(R.id.cbVegetarian);
        rgMarime = findViewById(R.id.rgMarime);
        spPaine = findViewById(R.id.spPaine);
        rbRating = findViewById(R.id.rbRating);
        swSos = findViewById(R.id.swSos);
        btnSalveaza = findViewById(R.id.btnSalveaza);

        btnSalveaza.setOnClickListener(v -> salveaza());
    }

    private void salveaza() {
        String nume = etNume.getText().toString().trim();
        String pretText = etPret.getText().toString().trim();
        if (nume.isEmpty() || pretText.isEmpty()) {
            Toast.makeText(this, "Completează numele și prețul!", Toast.LENGTH_SHORT).show();
            return;
        }
        double pret = Double.parseDouble(pretText);

        int idMarime = rgMarime.getCheckedRadioButtonId();
        String marime = "Mediu";
        if (idMarime == R.id.rbMic) marime = "Mic";
        else if (idMarime == R.id.rbMare) marime = "Mare";

        Sandwich sandwich = new Sandwich(
                nume,
                pret,
                cbVegetarian.isChecked(),
                marime,
                spPaine.getSelectedItem().toString(),
                rbRating.getRating(),
                swSos.isChecked());

        Intent rezultat = new Intent();
        rezultat.putExtra("sandwich", sandwich);
        setResult(RESULT_OK, rezultat);
        finish();
    }
}