package gt.edu.umg.progra2android;

import static android.app.ProgressDialog.show;

import android.annotation.SuppressLint;
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

import BaseDeDatos.DBHelper;

public class MainActivity extends AppCompatActivity {
Button btmsaludar, btnCreardb;
TextView tvSaludar;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        btmsaludar=findViewById(R.id.btmsaludar);
        tvSaludar=findViewById(R.id.tvSaludo);
        btnCreardb=findViewById(R.id.btnCreardb);

        btmsaludar = findViewById(R.id.btmsaludar);
        tvSaludar = findViewById(R.id.tvSaludo);
        btmsaludar.setOnClickListener(v ->{
                Toast.makeText(this, "hola pablo", Toast.LENGTH_SHORT).show();
                tvSaludar.setText("hola pablo");;
        });
        btnCreardb.setOnClickListener(v ->{
            DBHelper dbHelper = new DBHelper(this);
            dbHelper.getWritableDatabase();
            SQLiteDatabase db = dbHelper.getWritableDatabase();
            if (db!=null){
                Toast.makeText(this, "DB Creada!", Toast.LENGTH_SHORT).show();
            }
            else {
                Toast.makeText(this, "Error al crear DB!", Toast.LENGTH_SHORT).show();
            }
        });


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}