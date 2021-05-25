package quiroga.humberto.proyectofinal

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.google.firebase.auth.*

class registro : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registro)


        auth = Firebase.auth

        val botonAtras: ImageButton = findViewById(R.id.botonAtras)
        val botonSiguiente: Button = findViewById(R.id.botonSiguiente)

        botonAtras.setOnClickListener{
            val intent: Intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        botonSiguiente.setOnClickListener{
            valida_registro()
        }
    }

    private fun valida_registro(){
        val et_correo: EditText = findViewById(R.id.textoUsuarioRegistro)
        val et_contra1: EditText = findViewById(R.id.textoContraseñaRegistro)
        val et_contra2: EditText = findViewById(R.id.textoRepetirContraseña)

        var correo: String = et_correo.text.toString()
        var contra1: String = et_contra1.text.toString()
        var contra2: String = et_contra2.text.toString()

        if(!correo.isNullOrBlank() && !contra1.isNullOrBlank() &&
            !contra2.isNullOrBlank()){

            if(contra1 == contra2){

                registrarFirebase(correo, contra1)

            }else{
                Toast.makeText(this, "Las contraseñas no coinciden",
                    Toast.LENGTH_SHORT).show()
            }

        }else{
            Toast.makeText(this, "Error: Ingresar datos",
                Toast.LENGTH_SHORT).show()
        }
    }

    private fun registrarFirebase(email: String, password: String){
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    val user = auth.currentUser

                    val intent: Intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)

                    Toast.makeText(baseContext, "Ahora puedes ingresar",
                        Toast.LENGTH_SHORT).show()


                    if (user != null) {
                        Toast.makeText(baseContext, "${user.email} :Se autentico correctamente",
                            Toast.LENGTH_SHORT).show()
                    }
                    //updateUI(user)
                } else {
                    // If sign in fails, display a message to the user.
                    //Log.w(TAG, "createUserWithEmail:failure", task.exception)
                    Toast.makeText(baseContext, "Error: Autenticacion Fallida",
                        Toast.LENGTH_SHORT).show()
                    //updateUI(null)
                }
            }
    }

}