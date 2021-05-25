package quiroga.humberto.proyectofinal

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Window
import android.widget.*
import com.google.firebase.firestore.FirebaseFirestore

class chat : AppCompatActivity() {

    private val storage = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)


        val botonAtras: ImageButton = findViewById(R.id.botonAtras)
        val botonEnviar: Button = findViewById(R.id.botonEnviar)
        val textoEnviado: TextView = findViewById(R.id.textoEscrito)
        val textoRecibido: TextView = findViewById(R.id.textoChatRecibido)
        val textoEscrito: EditText = findViewById(R.id.textoMensajeEscrito)

        botonAtras.setOnClickListener{
            val intent: Intent = Intent(this, inicio::class.java)
            startActivity(intent)
        }


        botonEnviar.setOnClickListener {

            textoEnviado.setText(textoEscrito.text.toString().trim())

            storage.collection("mensajes").whereEqualTo("mensaje",textoEscrito.text.toString())
                .get()
                .addOnSuccessListener { result ->
                    for (document in result) {
                        Log.d("mensajes", "${document.id} => ${document.data}")
                        if(!document.getString("respuesta").isNullOrEmpty()){
                            textoRecibido.text = document.getString("respuesta")

                            Toast.makeText(baseContext, "Mnesaje recibido",
                                Toast.LENGTH_SHORT).show()

                        }else{
                            textoRecibido.text = "Escriba pmensaje"



                        }

                    }
                }
                .addOnFailureListener { exception ->
                    Toast.makeText(baseContext, "Error: No se encuentra respuesta",
                        Toast.LENGTH_SHORT).show()
                    Log.w("mensajes", "Error getting documents.", exception)
                }

        }


    }
}