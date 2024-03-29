package co.edu.upb.ejercicios.principiosvisuales07_02_2023

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import java.util.Locale

// Bundle es un tipo de dato que da animaciones
// El signo "?" que recibe un valor nulo
// Content: Configuraciones de la vista
// !! no va a pasar nulos

class MainActivity : AppCompatActivity(), TextToSpeech.OnInitListener {

    var tts : TextToSpeech? = null


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        // Inicializar el tts

        tts = TextToSpeech(this,this)

        findViewById<Button>(R.id.btnPlay).setOnClickListener { hablar() }

    }



    private fun hablar(){

        var mensaje:String = findViewById<EditText>(R.id.etPrincipal).text.toString()

        if(mensaje.isEmpty()){

            findViewById<TextView>(R.id.tvTextoAyuda).text = "Porfavor ingresa una cadena de texto"
        }

        tts!!.speak(mensaje,TextToSpeech.QUEUE_FLUSH,null,"")

    }


 // TextToSpeach. onInit

    override fun onInit(status: Int) {


      if (status == TextToSpeech.SUCCESS) {

          findViewById<TextView>(R.id.tvTextoAyuda).text = "Listo!"    // .text viene de la clase TextView
          tts!!.setLanguage(Locale("ES"))

      }else {

          findViewById<TextView>(R.id.tvTextoAyuda).text = "No disponible :("

      }

    }

    override fun onDestroy() {

        if(tts != null){
            tts!!.stop()
            tts!!.shutdown()
        }

        super.onDestroy()
    }


}