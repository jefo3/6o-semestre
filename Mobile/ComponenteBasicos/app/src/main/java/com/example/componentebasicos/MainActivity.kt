package com.example.componentebasicos

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.*
import android.widget.AdapterView.OnItemSelectedListener
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        estadoLampada()

        autoCompleteEstados()

        listaEstados()

        clickLongo()


    }

    fun estadoLampada(){
        val toggleButton1 = findViewById<ToggleButton>(R.id.toggleButton1)

        toggleButton1.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View) {
                val texto: String

                if (toggleButton1.isChecked) {
                    texto = "lampada 1: Ligada"
                } else {
                    texto = "lampada 1: Desligada"
                }

                Toast.makeText(
                    applicationContext, texto,
                    Toast.LENGTH_SHORT
                ).show()
            }

        })
    }

    fun pegarTexto(view: View){
        val editText = findViewById<EditText>(R.id.editText)

        val msg = editText.text.toString()

        Toast.makeText(applicationContext, msg, Toast.LENGTH_LONG).show()
    }

    fun autoCompleteEstados(){
        val autoComplete = findViewById<AutoCompleteTextView>(R.id.idAutoComplete)

        val str =  listOf<String>(
            "Acre",
            "Amazonas",
            "Brasilia",
            "Ceara",
            "São Paulo",
            "Rio de Janeiro",
            "São jorge"
        )

        val adp: ArrayAdapter<String> = ArrayAdapter<String>(
            this,
            android.R.layout.simple_dropdown_item_1line, str
        )

        autoComplete.setAdapter(adp)
    }

    fun listaEstados(){
        val str =  listOf<String>(
            "Acre",
            "Amazonas",
            "Brasilia",
            "Ceara",
            "São Paulo",
            "Rio de Janeiro",
            "São jorge"
        )
        val spnr = findViewById<Spinner>(R.id.spinner)

        val adapter: ArrayAdapter<String>  = ArrayAdapter<String>(
            this, android.R.layout.simple_spinner_item, str
        )

        spnr.adapter = adapter

        spnr.onItemSelectedListener = object : OnItemSelectedListener {

            override fun onItemSelected(arg0: AdapterView<*>, arg1: View, arg2: Int, arg3: Long) {

                val position: Int = spnr.selectedItemPosition
                Toast.makeText(
                    applicationContext,
                    "você selecionou " + str[+position],
                    Toast.LENGTH_LONG
                ).show()
            }

            override fun onNothingSelected(arg0: AdapterView<*>) {


            }

        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val mainView = findViewById<RelativeLayout>(R.id.main_view)

        if(item.itemId == R.id.menuAmarelo ){
            if(item.isChecked){
                item.isChecked = false
            }else{
                item.isChecked = true
            }
            mainView.setBackgroundColor(Color.YELLOW)
            return true
        }else if(item.itemId == R.id.menuVerde){
            if(item.isChecked){
                item.isChecked = false
            }else{
                item.isChecked = true
            }
            mainView.setBackgroundColor(Color.GREEN)
            return true
        }else if(item.itemId == R.id.normal){
            if(item.isChecked){
                item.isChecked = false
            }else{
                item.isChecked = true
            }
            mainView.setBackgroundColor(Color.WHITE)
            return true
        }

        return true
    }

    fun clickLongo(){
        val button2 = findViewById<Button>(R.id.button2)

        button2.setOnLongClickListener {
            Toast.makeText(
                applicationContext,
                "você realizou um click longo",  Toast.LENGTH_LONG
            ).show()
            true
        }
    }

    fun proxTela(view: View){
        val intent = Intent(this, MainActivity2::class.java)
        startActivity(intent)
    }

}

