package com.example.navegaoentretelas

import android.R.id.edit
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.example.navegaoentretelas.constants.Constants


class MainActivity2 : AppCompatActivity() {

    lateinit var titulo: EditText
    lateinit var autor: EditText
    lateinit var paginas: EditText

    var isEdit:Boolean = false
    var idLivro:Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        titulo = findViewById(R.id.Etitulo)
        autor = findViewById(R.id.Eautor)
        paginas = findViewById(R.id.Epaginas)

        isEdit = false
        if (intent.extras != null) {
            val title = intent.extras!!["title"] as String?
            val au = intent.extras!!["autor"] as String?
            val pag = intent.extras!!["pagina"] as String?

            idLivro = intent.extras!!["id"] as Int

            titulo.setText(title.toString())
            autor.setText(au.toString())
            paginas.setText(pag.toString())

            isEdit = true

        }

    }

    fun cancelar(view: View){
        setResult(Constants.RESULT_CANCEL())
        finish()
    }

    fun add(view: View){

        val intent = Intent()

        intent.putExtra("titulo", titulo.text.toString())
        intent.putExtra("autor", autor.text.toString())
        intent.putExtra("paginas", paginas.text.toString())

        if(isEdit) intent.putExtra("id", idLivro.toString().toInt())

        setResult(Constants.RESULT_ADD(), intent)
        finish()
    }
}