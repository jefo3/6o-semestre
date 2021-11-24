package com.example.navegaoentretelas.Biblioteca

class Livro {

    internal var titulo: String? = null
    internal var autor: String? = null
    internal var qntPaginas: String? = null
    internal var id: Int = 0

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