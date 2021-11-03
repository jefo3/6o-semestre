package com.example.componentebasicos

import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.webkit.WebView
import android.widget.MediaController
import android.widget.RadioGroup
import android.widget.Toast
import android.widget.VideoView
import androidx.annotation.RequiresApi
import android.media.session.MediaController as MediaController1

class MainActivity2 : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        web()

        
    }

    fun web(){
        val myWebView: WebView = findViewById(R.id.webview)
        myWebView.loadUrl("https://www.google.com.br/")
        myWebView.settings.javaScriptEnabled = true
    }

    fun sexo(view: View){
        val sexo = findViewById<RadioGroup>(R.id.sexo)

        if(sexo.checkedRadioButtonId == R.id.radioButton){
            Toast.makeText(applicationContext, "você selecionou o sexo Masculino", Toast.LENGTH_LONG).show()
        }else if(sexo.checkedRadioButtonId == R.id.radioButton2){
            Toast.makeText(applicationContext, "você selecionou o sexo Femenino", Toast.LENGTH_LONG).show()
        }

    }

}