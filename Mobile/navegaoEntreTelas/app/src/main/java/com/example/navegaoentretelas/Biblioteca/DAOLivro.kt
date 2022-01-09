package com.example.navegaoentretelas.Biblioteca

import com.google.android.gms.tasks.Task
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.Query
import kotlin.collections.HashMap


class DAOLivro {
    var databaseRefence : DatabaseReference

    constructor() {
        var db :FirebaseDatabase = FirebaseDatabase.getInstance()

        this.databaseRefence = db.getReference(Livro::class.java.simpleName)
    }

     fun add( livro: Livro): Task<Void> {
         return this.databaseRefence.push().setValue(livro)
    }

    fun update(key: String?, hashMap: HashMap<String, Any>): Task<Void> {
        return this.databaseRefence.child(key.toString()).updateChildren(hashMap)
    }

    fun delete( key: String): Task<Void> {
        return this.databaseRefence.child(key).removeValue()
    }

    fun getDatabase(): Query {
        return this.databaseRefence
    }

}