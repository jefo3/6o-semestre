package com.example.navegaoentretelas

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.navegaoentretelas.Biblioteca.Livro
import com.example.navegaoentretelas.constants.Constants
import com.example.navegaoentretelas.constants.Constants.Companion.RESULT_ADD

@Suppress("DEPRECATION")
class MainActivity : AppCompatActivity() {

    private var selected = -1
    private lateinit var adapter: ArrayAdapter<Livro>
    private lateinit var listaLivros:ArrayList<Livro>

    var idContador = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        listaLivros = ArrayList()

        adapter = ArrayAdapter<Livro>(
            this,
            android.R.layout.simple_list_item_1, listaLivros
        )


        val listeView = findViewById<ListView>(R.id.lista)
        listeView.adapter = adapter
        listeView.setSelector(android.R.color.holo_blue_light)

        listeView.setOnItemClickListener{ parent, view, position, id ->
            selected = position

        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {

            R.id.delete ->
                deleteIten()
            R.id.add ->
                addItem()
            R.id.edit ->
                editItem()
            else -> "Invalid month"
        }

        return true
    }

    fun deleteIten(){
        if (selected >= 0) {
            listaLivros.removeAt(selected)
            adapter.notifyDataSetChanged()
        } else {
            Toast.makeText(
                applicationContext,
                "selecione um item",
                Toast.LENGTH_LONG
            ).show()
        }

    }

    fun addItem() {
        val intent = Intent(this, MainActivity2::class.java)

        startActivityForResult(intent, Constants.REQUEST_ADD())
    }

    fun editItem() {

        if (selected >= 0) {
            val intent = Intent(this, MainActivity2::class.java)

            var livro: Livro = listaLivros.get(selected)

            intent.putExtra("title", livro.titulo)
            intent.putExtra("autor", livro.autor)
            intent.putExtra("pagina", livro.qntPaginas)
            intent.putExtra("id", livro.id.toString().toInt())

            startActivityForResult(intent, Constants.REQUEST_EDIT())

        } else {
            Toast.makeText(
                applicationContext,
                "selecione um item",
                Toast.LENGTH_LONG
            ).show()
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(requestCode == Constants.REQUEST_ADD() && resultCode == RESULT_ADD()){

            val titulo: String = data?.getStringExtra("titulo").toString()
            val autor = data?.getStringExtra("autor").toString()
            val pagina = data?.getStringExtra("paginas").toString()

            val livro:Livro = Livro(titulo, autor, pagina, idContador)
            idContador++;

            listaLivros.add(livro)
            adapter.notifyDataSetChanged()

        }else if(requestCode == Constants.REQUEST_EDIT() && resultCode == RESULT_ADD()){

            val titulo: String = data?.getStringExtra("titulo").toString()
            val autor = data?.getStringExtra("autor").toString()
            val pagina = data?.getStringExtra("paginas").toString()

            val id: Int = data?.getIntExtra("id", -1).toString().toInt()

            for (livro in listaLivros) {
                if(livro.id == id){
                    livro.titulo = titulo
                    livro.autor = autor
                    livro.qntPaginas = pagina
                }
            }


        }else if(resultCode == Constants.RESULT_CANCEL()){
            Toast.makeText(
                applicationContext,
                "CANCELADO",
                Toast.LENGTH_LONG
            ).show()
        }
    }
}