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

class formulas : AppCompatActivity() {

    private val storage = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_formulas)


        val botonAtras: ImageButton = findViewById(R.id.botonAtras)
        val formula1: TextView = findViewById(R.id.formula1)
        val formula2: TextView = findViewById(R.id.formula2)
        val formula3: TextView = findViewById(R.id.formula3)
        val formula4: TextView = findViewById(R.id.formula4)
        val formula5: TextView = findViewById(R.id.formula5)
        val formula6: TextView = findViewById(R.id.formula6)
        val formula7: TextView = findViewById(R.id.formula7)
        val formula8: TextView = findViewById(R.id.formula8)

        botonAtras.setOnClickListener{
            val intent: Intent = Intent(this, matematicas::class.java)
            startActivity(intent)
        }

        //Consulta de las formulas
        storage.collection("matematicas").whereEqualTo("lista","formulas")
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    Log.d("matematicas", "${document.id} => ${document.data}")
                    if(!document.getString("formula1").equals("")){
                        formula1.text = document.getString("formula1")
                        formula2.text = document.getString("formula2")
                        formula3.text = document.getString("formula3")
                        formula4.text = document.getString("formula4")
                        formula5.text = document.getString("formula5")
                        formula6.text = document.getString("formula6")
                        formula7.text = document.getString("formula7")
                        formula8.text = document.getString("formula8")

                        Toast.makeText(baseContext, "Formulas encontradas :)",
                            Toast.LENGTH_SHORT).show()

                    }else{
                        Toast.makeText(baseContext, "Error: No se encuentran formulas",
                            Toast.LENGTH_SHORT).show()

                    }

                }
            }
            .addOnFailureListener { exception ->
                Log.w("matematicas", "Error getting documents.", exception)
            }


    }
}