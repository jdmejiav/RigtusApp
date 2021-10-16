package co.rigctus.rigctus;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class activity_inicio extends AppCompatActivity {
    FirebaseDatabase database=FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("message");
    Context context = this;
    ImageButton btnRelojOregano;
    ImageButton btnRelojMenta;
    ImageButton btnRelojAlbahaca;
    ImageButton btnEstadoOregano;
    ImageButton btnEstadoMenta;
    ImageButton btnEstadoAlbahaca;
    DatabaseReference refHome = database.getReference("home");
    DatabaseReference refLuces,refLuzCocina, refBotones, refLuzSala, refPulsadorA;

    Switch switchPlanta1;
    TextView textEstadoPulsador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);
        myRef.setValue("Hola, Mundo!");

        clickEnEstado();
        clickEnRelojes();
        refLuces = refHome.child("luces");
        refLuzSala = refLuces.child("luz_sala");
        refLuzCocina = refLuces.child("luz_cocina");
        refBotones = refHome.child("botones");
        refPulsadorA = refBotones.child("pulsador_a");
        switchPlanta1 =  findViewById(R.id.switchPlanta1);
        controlLED(refLuzSala, switchPlanta1);
        estado(refLuzCocina,btnEstadoOregano);
        estadoPulsador(refPulsadorA, textEstadoPulsador);

    }
    private void estado(final DatabaseReference refled, final ImageButton btnEstadoOregano){
        btnEstadoOregano.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new cuadro_dialogo_estado(context);
                refled.setValue(true);

            }
        });


    }
    private void controlLED(final DatabaseReference refLed, final Switch switchPlanta1) {

        switchPlanta1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                refLed.setValue(isChecked);

            }
        });

        refLed.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Boolean estado_led  = (Boolean) dataSnapshot.getValue();
                switchPlanta1.setChecked(estado_led);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) { }

        });
    }

    private void estadoPulsador(final DatabaseReference refPulsador_a, final TextView textEstadoPulsador) {

        refPulsador_a.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Boolean estado_pulsador = (Boolean) dataSnapshot.getValue();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) { }
        });
    }
    private void clickEnRelojes (){

        //Eventos de click

        btnRelojOregano = (ImageButton) findViewById(R.id.btnRelojOregano);
        btnRelojMenta = (ImageButton) findViewById(R.id.btnRelojMenta);
        btnRelojAlbahaca= (ImageButton) findViewById(R.id.btnRelojAlbahaca);

        btnRelojOregano.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new cuadro_dialogo_tiempo(context);
            }
        });

        btnRelojMenta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new cuadro_dialogo_tiempo(context);
            }
        });

        btnRelojAlbahaca.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new cuadro_dialogo_tiempo(context);
            }
        });
    }

    private void clickEnEstado(){

        //Eventos de click

        btnEstadoOregano = (ImageButton) findViewById(R.id.btnEstadoOregano);
        btnEstadoMenta = (ImageButton) findViewById(R.id.btnEstadoMenta);
        btnEstadoAlbahaca = (ImageButton) findViewById(R.id.btnEstadoAlbahaca);

        btnEstadoOregano.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new cuadro_dialogo_estado(context);
            }
        });

        btnEstadoMenta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new cuadro_dialogo_estado(context);

            }
        });

        btnEstadoAlbahaca.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new cuadro_dialogo_estado(context);

            }
        });

    }
}
