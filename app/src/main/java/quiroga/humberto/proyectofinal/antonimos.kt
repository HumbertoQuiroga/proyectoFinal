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

class antonimos : AppCompatActivity() {

    private val storage = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_antonimos)


        val botonAtras: ImageButton = findViewById(R.id.botonAtras)
        val palabraAntonimo: EditText = findViewById(R.id.textoPalabraAntonimo)
        val textoAntonimo1: TextView = findViewById(R.id.textoAntonimo1)
        val textoAntonimo2: TextView = findViewById(R.id.textoAntonimo2)
        val textoAntonimo3: TextView = findViewById(R.id.textoAntonimo3)
        val textoAntonimo4: TextView = findViewById(R.id.textoAntonimo4)
        val textoAntonimo5: TextView = findViewById(R.id.textoAntonimo5)


        botonAtras.setOnClickListener{
            val intent: Intent = Intent(this, espanol::class.java)
            startActivity(intent)
        }

        palabraAntonimo.setOnKeyListener(object : View.OnKeyListener {
            override fun onKey(v: View?, keyCode: Int, event: KeyEvent): Boolean {

                if (event.action == KeyEvent.ACTION_DOWN &&
                    keyCode == KeyEvent.KEYCODE_ENTER
                ) {

                    storage.collection("diccionario").whereEqualTo("palabra",palabraAntonimo.text.toString())
                        .get()
                        .addOnSuccessListener { result ->
                            for (document in result) {
                                Log.d("diccionario", "${document.id} => ${document.data}")
                                if(!document.getString("antonimo1").equals("")){
                                    textoAntonimo1.text = document.getString("antonimo1")
                                    textoAntonimo2.text = document.getString("antonimo2")
                                    textoAntonimo3.text = document.getString("antonimo3")
                                    textoAntonimo4.text = document.getString("antonimo4")
                                    textoAntonimo5.text = document.getString("antonimo5")

                                    Toast.makeText(baseContext, "Antonimos encontrados :)",
                                        Toast.LENGTH_SHORT).show()

                                }else{
                                    textoAntonimo1.text = ""
                                    textoAntonimo2.text = ""
                                    textoAntonimo3.text = ""
                                    textoAntonimo4.text = ""
                                    textoAntonimo5.text = ""

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