package com.example.xpp;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.ColorUtils;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {
    private int aktualnyProcent = 100;
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

        //1
        TextView napis = findViewById(R.id.napis);
        SeekBar seekB = findViewById(R.id.seekB);
        TextView napisProcenty = findViewById(R.id.napisProcenty);
        int koTekstu = Color.BLACK;

        //2
        View kwadrat = findViewById(R.id.viewKwadrat);
        Button przyciskMinus = findViewById(R.id.przyciskminus);
        Button przyciskPlus = findViewById(R.id.przyciskplus);
        TextView wartosc = findViewById(R.id.wartosc);

        //1
        seekB.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                int alpha = Math.round((progress / 100f) * 255);
                int kolorZAlpha = ColorUtils.setAlphaComponent(koTekstu, alpha);
                napis.setTextColor(kolorZAlpha);
                napisProcenty.setText(progress + "%");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        //2
        przyciskMinus.setOnClickListener(v -> {
            if (aktualnyProcent > 0) {
                aktualnyProcent -= 10;
                kwadrat.setAlpha(aktualnyProcent / 100f);
                wartosc.setText(aktualnyProcent + "%");
                przyciskMinus.setEnabled(aktualnyProcent > 0);
                przyciskPlus.setEnabled(aktualnyProcent < 100);
            }
        });

        przyciskPlus.setOnClickListener(v -> {
            if (aktualnyProcent < 100) {
                aktualnyProcent += 10;
                kwadrat.setAlpha(aktualnyProcent / 100f);
                wartosc.setText(aktualnyProcent + "%");
                przyciskMinus.setEnabled(aktualnyProcent > 0);
                przyciskPlus.setEnabled(aktualnyProcent < 100);
            }
        });

        //3
        LinearLayout panel = findViewById(R.id.panelGlowny);
        TextView tekst = findViewById(R.id.textViewTekst);
        RadioGroup grupaOpcji = findViewById(R.id.Opcje);
        SeekBar suwak = findViewById(R.id.przedzialPrze);


        int kolorTla = Color.parseColor("#2196F3");
        int kolorTekstu = Color.WHITE;

        grupaOpcji.setOnCheckedChangeListener((group, checkedId) -> {
            panel.setAlpha(1f);
            panel.setBackgroundColor(kolorTla);
            tekst.setTextColor(kolorTekstu);
            suwak.setProgress(100);
        });

        suwak.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                int alpha255 = progress * 255 / 100;
                float alphaFloat = progress / 100f;
                int wybrana = grupaOpcji.getCheckedRadioButtonId();

                if (wybrana == R.id.przycisktylkotlo) {
                    panel.setBackgroundColor(ColorUtils.setAlphaComponent(kolorTla, alpha255));
                } else if (wybrana == R.id.przycisktylkotekst) {
                    tekst.setTextColor(ColorUtils.setAlphaComponent(kolorTekstu, alpha255));
                } else if (wybrana == R.id.przyciskcalypanel) {
                    panel.setAlpha(alphaFloat);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

//        Odpowiedz do zadania 1
//        Przy 0% zniknie tylko sam tekst, a użytkownik zobaczy pusty prostokąt (zmieniamy jedynie kolor czcionki)
//        Odpowiedz do zadania 3
//        Tylko tło - zmienia przezroczystość samego tła panelu, a tekst i przycisk w środku zostają w pełni widoczne.
//        Cały panel - zmienia przezroczystość całego kontenera razem z tym, co jest w środku (tekstem i przyciskiem).
    }
}