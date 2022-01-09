package com.example.navegaoentretelas.Biblioteca

class Livro {

    var titulo: String? = null
    var autor: String? = null
    var qntPaginas: String? = null
    var id: Int = 0
    var key: String? = null

    constructor()

    constructor(titulo:String, autor:String, qntPaginas:String, id:Int){
        this.id = id
        this.titulo = titulo
        this.autor = autor
        this.qntPaginas = qntPaginas
    }

    override fun toString(): String {
        return "Livro(titulo=$titulo, autor=$autor, qntPaginas=$qntPaginas, id=$id)"
    }


}