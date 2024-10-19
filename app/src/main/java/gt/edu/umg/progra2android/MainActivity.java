package gt.edu.umg.progra2android;

import static android.app.ProgressDialog.show;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import BaseDatos.DbHelper;

public class MainActivity extends AppCompatActivity {

    Button btnSaludo, btnCrearDb, btnCrearRegistro;
    TextView tvSaludo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        //Codigo nuevo
        btnSaludo = findViewById(R.id.btnSaludo);
        tvSaludo = findViewById(R.id.tvSaludo);
        btnCrearDb = findViewById(R.id.btnCrearDb);
        btnCrearRegistro = findViewById(R.id.btnCrearRegistro);

        //Crear db

        btnCrearDb.setOnClickListener(v ->{

            //Crear base de datos

            DbHelper dbHelper = new DbHelper(this);
            dbHelper.getWritableDatabase();
            SQLiteDatabase db = dbHelper.getWritableDatabase();

            if(db != null)
            {
                Toast.makeText(this,"Creando base de datos", Toast.LENGTH_SHORT).show();
                tvSaludo.setText("Creando base de datos");
            }else{
                Toast.makeText(this,"Error al crear base de datos", Toast.LENGTH_SHORT).show();
                tvSaludo.setText("Error al crear base de datos");
            }
        });

        //crear registro llama a la activity NuevoActivity FALTA CREAR Y REFERENCIAR EL BOTON

        btnCrearRegistro.setOnClickListener(v -> {
            Toast.makeText(this, "Creando Registro", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, NuevoActivity.class);
            startActivity(intent);

        });

        btnSaludo.setOnClickListener(v ->{
            Toast.makeText(this,"Aviso karlita", Toast.LENGTH_SHORT).show();
            tvSaludo.setText("Hola Karlita");
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}