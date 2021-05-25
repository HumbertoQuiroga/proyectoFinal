package quiroga.humberto.proyectofinal

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        auth = Firebase.auth

        val botonIngresar: Button = findViewById(R.id.botonIngresar)
        val botonCrearCuenta: Button = findViewById(R.id.botonCrearCuenta)

        botonIngresar.setOnClickListener{
            valida_ingreso()
        }

        botonCrearCuenta.setOnClickListener{
            val intent: Intent = Intent(this,registro::class.java)
            startActivity(intent)
        }
    }

    private fun valida_ingreso(){
        val et_correo: EditText = findViewById(R.id.EditaTextoUsuario)
        val et_contra: EditText = findViewById(R.id.EditaTextoContraseña)

        var correo: String = et_correo.text.toString()
        var contra: String = et_contra.text.toString()

        if(!correo.isNullOrBlank() && !contra.isNullOrBlank()){

            ingresaFirebase(correo, contra)


        }else{
            Toast.makeText(this, "No has ingresado los datos",
                Toast.LENGTH_SHORT).show()
        }
    }

    private fun ingresaFirebase(email: String, password: String){
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    //Log.d(TAG, "signInWithEmail:success")
                    val user = auth.currentUser

                    val intent: Intent = Intent(this, inicio::class.java)
                    startActivity(intent)
                    //updateUI(user)
                    Toast.makeText(baseContext, "Correctamente autenticado",
                        Toast.LENGTH_SHORT).show()
                } else {
                    // If sign in fails, display a message to the user.
                    //Log.w(TAG, "signInWithEmail:failure", task.exception)
                    Toast.makeText(baseContext, "Error: Revisa usuario y contraseña",
                        Toast.LENGTH_SHORT).show()
                    //updateUI(null)
                }
            }
    }

}