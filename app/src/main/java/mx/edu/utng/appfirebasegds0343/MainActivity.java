package mx.edu.utng.appfirebasegds0343;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {
    private TextView tvMensaje;
    private EditText etTexto;

    //Referencia a la base de datos
    DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
    DatabaseReference mensajeRef = ref.child("mimensaje");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Se ligan los elementos de la vista
        tvMensaje = (TextView) findViewById(R.id.tvMensaje);
        etTexto = (EditText) findViewById(R.id.etTexto);
    }
    public void modificar(View view){
        //Acción del boton de Modificar
        String mensaje = etTexto.getText().toString();
        mensajeRef.setValue(mensaje); //Actualiza los datos en la base de datos
        etTexto.setText("");
    }

    @Override
    protected void onStart() {
        super.onStart();

        mensajeRef.addValueEventListener(new ValueEventListener() {
        //ref.child("misusuarios").addValueEventListener(new ValueEventListener()){
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String value = snapshot.getValue(String.class);
                tvMensaje.setText(value);
            /*    if(snapshot.exists()){
                    int i=1;
                    for(DataSnapshot ds: snapshot.getChildren()){
                        String foto = ds.child("foto").getValue().toString();
                        String nombre = ds.child("nombre").getValue().toString() + " " +
                                ds.child("apellido").getValue().toString();
                        String email = ds.child("email").getValue().toString();
                        usuarioList.add(new Usuario(i,nombre,foto,email));
                        i++;
                    }
                    usuariosAdaptador = new UsuariosAdapter(usuarioList, MainActivity.this);
                    recyclerUsuarios.setAdapter(usuariosAdaptador);
                }*/
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error){


            }

        });
    }
}