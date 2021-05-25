package quiroga.humberto.proyectofinal

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import android.widget.Button
import android.widget.ImageButton

class espanol : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_espanol)


        val botonAtras: ImageButton = findViewById(R.id.botonAtras)
        val botonSinonimos: Button = findViewById(R.id.botonSinonimos)
        val botonAntonimos: Button = findViewById(R.id.botonAntonimos)
        val botonPalabrascon: Button = findViewById(R.id.botonPalabrascon)
        val botonDiccionario: Button = findViewById(R.id.botonBusquedaDiccionarios)

        botonAtras.setOnClickListener{
            val intent: Intent = Intent(this, inicio::class.java)
            startActivity(intent)
        }

        botonDiccionario.setOnClickListener{
            val intent: Intent = Intent(this, palabra::class.java)
            startActivity(intent)
        }

        botonSinonimos.setOnClickListener{
            val intent: Intent = Intent(this, sinonimos::class.java)
            startActivity(intent)
        }

        botonAntonimos.setOnClickListener{
            val intent: Intent = Intent(this, antonimos::class.java)
            startActivity(intent)
        }

        botonPalabrascon.setOnClickListener{
            val intent: Intent = Intent(this, palabrascon::class.java)
            startActivity(intent)
        }



    }



}