package quiroga.humberto.proyectofinal

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import android.widget.Button
import android.widget.ImageButton

class matematicas : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_matematicas)


        val botonAtras: ImageButton = findViewById(R.id.botonAtras)
        val botonPasoAPaso: Button = findViewById(R.id.botonPasoAPaso)
        val botonFormulas: Button = findViewById(R.id.botonFormulas)

        botonPasoAPaso.setOnClickListener {
            val intent: Intent = Intent(this, pasos::class.java)
            startActivity(intent)
        }

        botonFormulas.setOnClickListener {
            val intent: Intent = Intent(this, formulas::class.java)
            startActivity(intent)
        }

        botonAtras.setOnClickListener{
            val intent: Intent = Intent(this, inicio::class.java)
            startActivity(intent)
        }
    }
}