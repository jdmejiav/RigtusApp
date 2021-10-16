package co.rigctus.rigctus;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class cuadro_dialogo_estado {

    public cuadro_dialogo_estado(Context contexto){
        final Dialog dialogo = new Dialog(contexto);

        dialogo.requestWindowFeature(Window.FEATURE_NO_TITLE);
        FirebaseDatabase database=FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("sensor");
        DatabaseReference refHome = database.getReference("home");
        DatabaseReference refLuces,refLuzCocina;
        refLuces = refHome.child("luces");
        refLuzCocina = refLuces.child("luz_cocina");
        dialogo.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialogo.setContentView(R.layout.cuadro_dialogo_estado);
        final ImageView imgTanque = dialogo.findViewById(R.id.imgTanque);
        imgTanque.setImageResource(R.drawable.imgtanque0);


        final Button btnEntendido = dialogo.findViewById(R.id.btnEntendido);
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                int value=dataSnapshot.getValue(Integer.class);
                if (value>=18){
                    imgTanque.setImageResource(R.drawable.imgtanque0);
                }else
                {
                    if(value<18&&value>=13){
                        imgTanque.setImageResource(R.drawable.imgtanque20);
                    }else {
                        if(value<13&&value>=10){
                        imgTanque.setImageResource(R.drawable.imgtanque50);
                    }else{
                            imgTanque.setImageResource(R.drawable.imgtanque80);



                        }
                    }
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        estado(refLuzCocina,btnEntendido,dialogo);
        dialogo.show();
    }
    private void estado(final DatabaseReference refled, final Button btnEstadoOregano, final Dialog dialogo){
        btnEstadoOregano.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogo.dismiss();
                refled.setValue(false);
            }
        });
    }
}
