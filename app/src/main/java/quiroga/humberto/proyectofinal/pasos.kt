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

class pasos : AppCompatActivity() {

    private val storage = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pasos)


        val botonAtras: ImageButton = findViewById(R.id.botonAtras)
        val nombreEcuacion: EditText = findViewById(R.id.nombreEcuacion)
        val paso1: TextView = findViewById(R.id.textoPaso1)
        val paso2: TextView = findViewById(R.id.textoPaso2)
        val paso3: TextView = findViewById(R.id.textoPaso3)

        botonAtras.setOnClickListener{
            val intent: Intent = Intent(this, matematicas::class.java)
            startActivity(intent)
        }

        //Consulta BD al presionar enter en EditText
        nombreEcuacion.setOnKeyListener(object : View.OnKeyListener {
            override fun onKey(v: View?, keyCode: Int, event: KeyEvent): Boolean {

                if (event.action == KeyEvent.ACTION_DOWN &&
                    keyCode == KeyEvent.KEYCODE_ENTER
                ) {

                    storage.collection("matematicas").whereEqualTo("nombreEcuacion",nombreEcuacion.text.toString())
                        .get()
                        .addOnSuccessListener { result ->
                            for (document in result) {
                                Log.d("matematicas", "${document.id} => ${document.data}")
                                if(!document.getString("paso1").equals("")){
                                    paso1.text = document.getString("paso1")
                                    paso2.text = document.getString("paso2")
                                    paso3.text = document.getString("paso3")

                                    Toast.makeText(baseContext, "Ecuacion encontrada :)",
                                        Toast.LENGTH_SHORT).show()

                                }else{

                                    Toast.makeText(baseContext, "Error: No se encuentra esa ecuacion",
                                        Toast.LENGTH_SHORT).show()

                                }

                            }
                        }
                        .addOnFailureListener { exception ->
                            Log.w("matematicas", "Error getting documents.", exception)
                        }
                    return true
                }
                return false
            }
        })



    }
}