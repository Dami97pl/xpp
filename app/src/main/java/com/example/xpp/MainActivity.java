package com.example.xpp;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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
    private int aktualnyProc = 100;
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
            if(aktualnyProc > 0){
                aktualnyProc -= 10;
                kwadrat.setAlpha(aktualnyProc / 100f);
                przyciskMinus.setEnabled(aktualnyProc > 0);
                przyciskMinus.setEnabled(aktualnyProc > 0);
                wartosc.setText(aktualnyProc + "%");
            }
        });

        przyciskPlus.setOnClickListener(v -> {
            if(aktualnyProc < 100){
                aktualnyProc += 10;
                kwadrat.setAlpha(aktualnyProc / 100f);
                przyciskPlus.setEnabled(aktualnyProc < 100);
                wartosc.setText(aktualnyProc + "%");
            }
        });
    }
}