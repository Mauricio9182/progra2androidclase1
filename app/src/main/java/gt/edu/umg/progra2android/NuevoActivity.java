package gt.edu.umg.progra2android;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import BaseDatos.DbHelper;

public class NuevoActivity extends AppCompatActivity {

    EditText txtNombre, txtTelefono, txtEmail;
    Button btnGuardar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_nuevo);

        // Vincular los elementos de la interfaz con las variables
        txtNombre = findViewById(R.id.txtNombre);
        txtTelefono = findViewById(R.id.txtTelefono);
        txtEmail = findViewById(R.id.txtEmail);
        btnGuardar = findViewById(R.id.btnGuardar);

        // Instanciar DbHelper
        DbHelper dbHelper = new DbHelper(this);

        // Lógica para el botón Guardar
        btnGuardar.setOnClickListener(v -> {
            String nombre = txtNombre.getText().toString();
            String telefono = txtTelefono.getText().toString();
            String email = txtEmail.getText().toString();

            // Verificar si los campos están vacíos
            if (nombre.isEmpty() || telefono.isEmpty() || email.isEmpty()) {
                Toast.makeText(this, "Por favor completa todos los campos", Toast.LENGTH_SHORT).show();
            } else {
                // Insertar el contacto en la base de datos
                long resultado = dbHelper.insertarContacto(nombre, telefono, email);

                if (resultado != -1) {
                    Toast.makeText(this, "Contacto guardado con éxito", Toast.LENGTH_SHORT).show();
                    // Aquí podrías limpiar los campos después de guardar el contacto
                    txtNombre.setText("");
                    txtTelefono.setText("");
                    txtEmail.setText("");
                } else {
                    Toast.makeText(this, "Error al guardar el contacto", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // Ajustar los insets de la ventana
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}
