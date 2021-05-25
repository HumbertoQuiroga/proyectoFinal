package quiroga.humberto.proyectofinal

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Window
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.firestore.FirebaseFirestore

class busqueda : AppCompatActivity() {

    private val storage = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_busqueda)
        val bundle = intent.extras

        val textoABuscar = bundle!!.getString("texto")

        val botonAtras: ImageButton = findViewById(R.id.botonAtras)

        val nombreTema1: TextView = findViewById(R.id.nombreTema1)
        val nombreTema2: TextView = findViewById(R.id.nombreTema2)
        val nombreTema3: TextView = findViewById(R.id.nombreTema3)

        val descripcionTema1: TextView = findViewById(R.id.descripcionTema1)
        val descripcionTema2: TextView = findViewById(R.id.descripcionTema2)
        val descripcionTema3: TextView = findViewById(R.id.descripcionTema3)

        storage.collection("temas").whereEqualTo("materia",textoABuscar.toString())
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    Log.d("temas", "${document.id} => ${document.data}")
                    if(!document.getString("nombreTema1").equals("")){
                        nombreTema1.text = document.getString("nombreTema1")
                        descripcionTema1.text = document.getString("descripcionTema1")

                        nombreTema2.text = document.getString("nombreTema2")
                        descripcionTema2.text = document.getString("descripcionTema2")

                        nombreTema3.text = document.getString("nombreTema3")
                        descripcionTema3.text = document.getString("descripcionTema3")

                        Toast.makeText(baseContext, "Tema encontrado :)",
                            Toast.LENGTH_SHORT).show()

                    }else{

                        Toast.makeText(baseContext, "Error: No se encuentra esa palabra",
                            Toast.LENGTH_SHORT).show()

                    }

                }
            }
            .addOnFailureListener { exception ->
                Log.w("temas", "Error getting documents.", exception)
            }


        botonAtras.setOnClickListener{
            val intent: Intent = Intent(this, inicio::class.java)
            startActivity(intent)
        }
    }
}