package BaseDeDatos;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

public class DBcontactos extends DBHelper{


        Context context;

        public DBcontactos(@Nullable Context context) {
            super(context);
            this.context = context;
        }

        //este metodo inserta un contacto en la tabla t_contactos

        public long insertaContacto(String nombre, String telefono, String email) {
            try{
                if (nombre.isEmpty() || telefono.isEmpty() || email.isEmpty()) {
                    return -1;
                }
                DBHelper dbHelper = new DBHelper(context);
                SQLiteDatabase db = dbHelper.getWritableDatabase();

                ContentValues values = new ContentValues();
                values.put("nombre", nombre);
                values.put("telefono", telefono);
                values.put("email", email);

                long id = db.insert(DBHelper.TABLE_CONTACTOS, null, values);
                return id;
            } catch (Exception e) {
                return -1;
            }

        }

    }

