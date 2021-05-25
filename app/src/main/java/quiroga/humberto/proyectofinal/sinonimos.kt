package quiroga.humberto.proyectofinal

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.View
import android.view.Window
import android.widget.*
import com.google.firebase.firestore.FirebaseFirestore

class sinonimos : AppCompatActivity() {

    private val storage = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sinonimos)


        val botonAtras: ImageButton = findViewById(R.id.botonAtras)
        val palabraSinonimo: EditText = findViewById(R.id.textoPalabraSinonimo)
        val textoSinonimo1: TextView = findViewById(R.id.textoSinonimo1)
        val textoSinonimo2: TextView = findViewById(R.id.textoSinonimo2)
        val textoSinonimo3: TextView = findViewById(R.id.textoSinonimo3)
        val textoSinonimo4: TextView = findViewById(R.id.textoSinonimo4)
        val textoSinonimo5: TextView = findViewById(R.id.textoSinonimo5)

        botonAtras.setOnClickListener{
            val intent: Intent = Intent(this, espanol::class.java)
            startActivity(intent)
        }

        palabraSinonimo.setOnKeyListener(object : View.OnKeyListener {
            override fun onKey(v: View?, keyCode: Int, event: KeyEvent): Boolean {

                if (event.action == KeyEvent.ACTION_DOWN &&
                    keyCode == KeyEvent.KEYCODE_ENTER
                ) {

                    storage.collection("diccionario").whereEqualTo("palabra",palabraSinonimo.text.toString())
                        .get()
                        .addOnSuccessListener { result ->
                            for (document in result) {
                                Log.d("diccionario", "${document.id} => ${document.data}")
                                if(!document.getString("sinonimo1").equals("")){
                                    textoSinonimo1.text = document.getString("sinonimo1")
                                    textoSinonimo2.text = document.getString("sinonimo2")
                                    textoSinonimo3.text = document.getString("sinonimo3")
                                    textoSinonimo4.text = document.getString("sinonimo4")
                                    textoSinonimo5.text = document.getString("sinonimo5")

                                    Toast.makeText(baseContext, "Sinonimos encontrados :)",
                                        Toast.LENGTH_SHORT).show()

                                }else{
                                    textoSinonimo1.text = ""
                                    textoSinonimo2.text = ""
                                    textoSinonimo3.text = ""
                                    textoSinonimo4.text = ""
                                    textoSinonimo5.text = ""

                                    Toast.makeText(baseContext, "Error: No se encuentra esa palabra",
                                        Toast.LENGTH_SHORT).show()

                                }

                            }
                        }
                        .addOnFailureListener { exception ->
                            Log.w("diccionario", "Error getting documents.", exception)
                        }
                    return true
                }
                return false
            }
        })

    }
}