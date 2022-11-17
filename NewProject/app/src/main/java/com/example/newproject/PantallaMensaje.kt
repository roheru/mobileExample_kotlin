package com.example.newproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class PantallaMensaje : AppCompatActivity() {
    var texto1: TextView?=null;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val instanciaFirebase=FirebaseFirestore.getInstance()
        setContentView(R.layout.activity_pantalla_mensaje)
        texto1=findViewById(R.id.txt1txt1);
        texto1?.text="El resultado es ";
        instanciaFirebase.collection("prueba").add(mapOf("cedula" to 1234567890,"apellido" to "peréz","nombre" to "FFF"))
            .addOnSuccessListener {
                instanciaFirebase.collection("prueba").document(it.id).update(mapOf("idDoc" to it.id))
        }.addOnFailureListener {
            Toast.makeText(this,it.message,Toast.LENGTH_LONG).show()
        };
        instanciaFirebase.collection("prueba").whereEqualTo("nombre","FFF").get().addOnSuccessListener {
            it.documents.forEach {
                Log.i("items",it.data.toString());
                Log.i("cedula",it?.data?.get("cedula").toString())

            }
        }
    }
}