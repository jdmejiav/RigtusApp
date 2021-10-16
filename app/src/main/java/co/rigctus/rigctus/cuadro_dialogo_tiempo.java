package co.rigctus.rigctus;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.view.Window;
import android.widget.Button;

public class cuadro_dialogo_tiempo {

    public cuadro_dialogo_tiempo(Context contexto){

    final Dialog dialogo = new Dialog(contexto);
    dialogo.requestWindowFeature(Window.FEATURE_NO_TITLE);
    dialogo.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
    dialogo.setContentView(R.layout.cuadro_dialogo_tiempo);

    Button btnEntendido = dialogo.findViewById(R.id.btnEntendido);
    btnEntendido.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            dialogo.dismiss();
        }
    });
    dialogo.show();
    }
}
