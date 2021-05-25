package quiroga.humberto.proyectofinal

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import com.google.firebase.database.*
import com.google.firebase.firestore.FirebaseFirestore


class palabra : AppCompatActivity() {

    private val storage = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_palabra)

        val botonAtras: ImageButton = findViewById(R.id.botonAtras)
        val palabraADefinir: EditText = findViewById(R.id.textoPalabraDiccionario)
        val textoDefinicion: TextView = findViewById(R.id.textoDefinicionPalabra)

        botonAtras.setOnClickListener{
            val intent: Intent = Intent(this, espanol::class.java)
            startActivity(intent)
        }

        palabraADefinir.setOnKeyListener(object : View.OnKeyListener {
            override fun onKey(v: View?, keyCode: Int, event: KeyEvent): Boolean {

                if (event.action == KeyEvent.ACTION_DOWN &&
                    keyCode == KeyEvent.KEYCODE_ENTER
                ) {

                    storage.collection("diccionario").whereEqualTo("palabra",palabraADefinir.text.toString())
                        .get()
                        .addOnSuccessListener { result ->
                            for (document in result) {
                                Log.d("diccionario", "${document.id} => ${document.data}")
                                if(!document.getString("definicion").isNullOrEmpty()){
                                    textoDefinicion.text = document.getString("definicion")

                                    Toast.makeText(baseContext, "Definicion encontrada :)",
                                        Toast.LENGTH_SHORT).show()

                                }else{
                                    textoDefinicion.text = "Escriba palabra registrada en diccionario"



                                }

                            }
                        }
                        .addOnFailureListener { exception ->
                            Toast.makeText(baseContext, "Error: No se encuentra esa palabra",
                                Toast.LENGTH_SHORT).show()
                            Log.w("diccionario", "Error getting documents.", exception)
                        }
                    return true
                }
                return false
            }
        })

    }
}