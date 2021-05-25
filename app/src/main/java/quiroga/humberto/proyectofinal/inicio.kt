package quiroga.humberto.proyectofinal

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import android.widget.Button
import android.widget.ImageButton

class inicio : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_inicio)


        val botonAtras: ImageButton = findViewById(R.id.botonAtras)
        val botonBusqueda: ImageButton = findViewById(R.id.botonBusqueda)
        val botonEspañol: ImageButton = findViewById(R.id.botonEspañol)
        val botonMatematicas: ImageButton = findViewById(R.id.botonMatematicas)
        val botonMensajes: ImageButton = findViewById(R.id.botonMensajes)
        val botonGuardados: ImageButton = findViewById(R.id.botonGuardados)
        val botonConfiguraciones: ImageButton = findViewById(R.id.botonConfiguraciones)


        botonBusqueda.setOnClickListener{
            val intent: Intent = Intent(this, PopUpBusqueda::class.java)
            startActivity(intent)
        }

        botonEspañol.setOnClickListener {
            val intent: Intent = Intent(this, espanol::class.java)
            startActivity(intent)
        }

        botonMatematicas.setOnClickListener {
            val intent: Intent = Intent(this, matematicas::class.java)
            startActivity(intent)
        }

        botonMensajes.setOnClickListener {
            val intent: Intent = Intent(this, chat::class.java)
            startActivity(intent)
        }

        botonGuardados.setOnClickListener {
            val intent: Intent = Intent(this, guardados::class.java)
            startActivity(intent)
        }

        botonConfiguraciones.setOnClickListener {
            val intent: Intent = Intent(this, configuraciones::class.java)
            startActivity(intent)
        }

        botonAtras.setOnClickListener{
            val intent: Intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}