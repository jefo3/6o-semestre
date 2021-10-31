package com.example.componentebasicos

import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebView
import android.widget.MediaController
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

}