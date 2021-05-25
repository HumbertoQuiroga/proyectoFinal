package quiroga.humberto.proyectofinal

import android.app.Activity
import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.WindowManager
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.firestore.FirebaseFirestore


class PopUpBusqueda : AppCompatActivity() {
    private var darkStatusBar = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        overridePendingTransition(0, 0)
        setContentView(R.layout.activity_pop_up_busqueda)

        val botonAtras: ImageButton = findViewById(R.id.botonAtras)
        val textoPopUp: EditText = findViewById(R.id.textoPopUp)
        val botonBuscarPopUp: Button = findViewById(R.id.botonPopupBuscar)


        // Establecer la apariencia de la barra de estado para diferentes niveles de API
        if (Build.VERSION.SDK_INT in 19..20) {
            setWindowFlag(this, true)
        }
        if (Build.VERSION.SDK_INT >= 19) {
            window.decorView.systemUiVisibility =
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
        }
        if (Build.VERSION.SDK_INT >= 21) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                // If you want dark status bar, set darkStatusBar to true
                if (darkStatusBar) {
                    this.window.decorView.systemUiVisibility =
                        View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
                }
                this.window.statusBarColor = Color.TRANSPARENT
                setWindowFlag(this, false)
            }
        }


        botonAtras.setOnClickListener {
            val intent: Intent = Intent(this, inicio::class.java)
            startActivity(intent)
        }

        botonBuscarPopUp.setOnClickListener {
            var textoPopAEnviar: String = textoPopUp.text.toString().trim()
            val intent: Intent = Intent(this, busqueda::class.java)
            intent.putExtra("texto", textoPopAEnviar)
            startActivity(intent)
        }

    }

    private fun setWindowFlag(activity: Activity, on: Boolean) {
        val win = activity.window
        val winParams = win.attributes
        if (on) {
            winParams.flags = winParams.flags or WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
        } else {
            winParams.flags =
                winParams.flags and WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS.inv()
        }
        win.attributes = winParams
    }


}
