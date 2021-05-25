package quiroga.humberto.proyectofinal

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import android.widget.Button
import android.widget.ImageButton

class configuraciones : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_configuraciones)


        val botonAtras: ImageButton = findViewById(R.id.botonAtras)
        val botonCerrarSesion: Button = findViewById(R.id.botonCerrarSesion)

        botonAtras.setOnClickListener{
            val intent: Intent = Intent(this, inicio::class.java)
            startActivity(intent)
        }

        botonCerrarSesion.setOnClickListener{
            val intent: Intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}