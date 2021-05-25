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

class palabrascon : AppCompatActivity() {

    private val storage = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_palabrascon)


        val botonAtras: ImageButton = findViewById(R.id.botonAtras)
        val iniciaCon: EditText = findViewById(R.id.iniciaCon)
        val conEstaLetra: EditText = findViewById(R.id.conEstaLetra)
        val conEstaCadena: EditText = findViewById(R.id.conEstaCadena)

        val palabra1: TextView = findViewById(R.id.palabra1)
        val palabra2: TextView = findViewById(R.id.palabra2)
        val palabra3: TextView = findViewById(R.id.palabra3)
        val palabra4: TextView = findViewById(R.id.palabra4)
        val palabra5: TextView = findViewById(R.id.palabra5)





        botonAtras.setOnClickListener{
            val intent: Intent = Intent(this, espanol::class.java)
            startActivity(intent)
        }


        //Condicion donde se ver si hay palabra que inicia con letra
        iniciaCon.setOnKeyListener(object : View.OnKeyListener {
            override fun onKey(v: View?, keyCode: Int, event: KeyEvent): Boolean {

                if (event.action == KeyEvent.ACTION_DOWN &&
                    keyCode == KeyEvent.KEYCODE_ENTER
                ) {

                    storage.collection("diccionario")
                        .whereGreaterThanOrEqualTo("palabra", iniciaCon.text.toString())
                        .whereLessThanOrEqualTo("palabra", "${iniciaCon.text.toString()}\uF7FF")
                        .get()
                        .addOnSuccessListener { documents ->
                            for (document in documents) {
                                Log.d("diccionario", "${document.id} => ${document.data}")

                                if(!document.getString("palabra").equals("")){
                                    palabra1.text = document.getString("palabra")

                                    Toast.makeText(baseContext, "Definicion encontrada :)",
                                        Toast.LENGTH_SHORT).show()

                                }else{
                                    Toast.makeText(baseContext, "Error: No se encuentra esa palabra",
                                        Toast.LENGTH_SHORT).show()

                                }

                            }
                        }
                        .addOnFailureListener { exception ->
                            Log.w("diccionario", "Error getting documents: ", exception)
                        }
                    return true
                }
                return false
            }
        })

        conEstaLetra.setOnKeyListener(object : View.OnKeyListener {
            override fun onKey(v: View?, keyCode: Int, event: KeyEvent): Boolean {

                if (event.action == KeyEvent.ACTION_DOWN &&
                    keyCode == KeyEvent.KEYCODE_ENTER
                ) {

                    storage.collection("diccionario")
                        .whereGreaterThanOrEqualTo("palabra", iniciaCon.text.toString())
                        .whereLessThanOrEqualTo("palabra", "${iniciaCon.text.toString()}\uF7FF")
                        .get()
                        .addOnSuccessListener { documents ->
                            for (document in documents) {
                                Log.d("diccionario", "${document.id} => ${document.data}")

                                if(!document.getString("palabra").equals("")){
                                    palabra1.text = document.getString("palabra")

                                    Toast.makeText(baseContext, "Definicion encontrada :)",
                                        Toast.LENGTH_SHORT).show()

                                }else{
                                    Toast.makeText(baseContext, "Error: No se encuentra esa palabra",
                                        Toast.LENGTH_SHORT).show()

                                }

                            }
                        }
                        .addOnFailureListener { exception ->
                            Log.w("diccionario", "Error getting documents: ", exception)
                        }
                    return true
                }
                return false
            }
        })

        conEstaCadena.setOnKeyListener(object : View.OnKeyListener {
            override fun onKey(v: View?, keyCode: Int, event: KeyEvent): Boolean {

                if (event.action == KeyEvent.ACTION_DOWN &&
                    keyCode == KeyEvent.KEYCODE_ENTER
                ) {

                    storage.collection("diccionario")
                        .whereGreaterThanOrEqualTo("palabra", iniciaCon.text.toString())
                        .whereLessThanOrEqualTo("palabra", "${iniciaCon.text.toString()}\uF7FF")
                        .get()
                        .addOnSuccessListener { documents ->
                            for (document in documents) {
                                Log.d("diccionario", "${document.id} => ${document.data}")

                                if(!document.getString("palabra").equals("")){
                                    palabra1.text = document.getString("palabra")

                                    Toast.makeText(baseContext, "Definicion encontrada :)",
                                        Toast.LENGTH_SHORT).show()

                                }else{
                                    Toast.makeText(baseContext, "Error: No se encuentra esa palabra",
                                        Toast.LENGTH_SHORT).show()

                                }

                            }
                        }
                        .addOnFailureListener { exception ->
                            Log.w("diccionario", "Error getting documents: ", exception)
                        }
                    return true
                }
                return false
            }
        })

    }
}