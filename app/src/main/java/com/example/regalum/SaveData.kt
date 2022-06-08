package com.example.regalum

import android.os.Environment
import java.io.*

private lateinit var NOMBRE_ARCHIVO: String
private lateinit var archivo: File
private lateinit var ruta: String
private lateinit var dataSet: String
private lateinit var numc: String

//Directorio donde se guarda el archivo
private var directory =
    Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)

fun file(name: String) { //Crea el archivo
    NOMBRE_ARCHIVO = name  //Nombre del archivo
    ruta = "$directory/$NOMBRE_ARCHIVO" //Ruta del archivo
    archivo = File(ruta) //Instancia del archivo
}

// Funcion para leer los datos del archivo
fun readFile(): String {
    dataSet = "" //Inicializamos el string
    if (archivo.exists()) { //Si el archivo existe
        val fileR = FileReader(archivo) //Leemos el archivo
        val br = BufferedReader(fileR) //Creamos un buffer para leer el archivo
        var cadena: String? = null //Cadena que almacenara el contenido del archivo
        while ({ cadena = br.readLine(); cadena }() != null) { //Mientras haya lineas en el archivo
            dataSet += "$cadena\n" //Agregamos el contenido de la linea al string
        }
        br.close() //Cerramos el buffer
        fileR.close() //Cerramos el archivo
    }
    return dataSet; //Retornamos el string
}

//Función para escribir en el archivo
fun guardarDatos(data: String): String {
    if (!archivo.exists()) { //Si el archivo no existe, lo crea
        archivo.createNewFile() //Crea el archivo
    }
    val fileWrite = FileWriter(archivo, true) //Crea el archivo
    val write = BufferedWriter(fileWrite)   //Crea el buffer
    write.write(data) //Escribe en el archivo
    write.close() //Cierra el buffer
    fileWrite.close() //Cierra el archivo
    return data //Retorna el dato guardado
}

//Validar si existe un dato dentro del archivo txt
fun validar(data: String): Boolean {
    if (archivo.exists()) { //Si el archivo existe
        val fileR = FileReader(archivo) //Leemos el archivo
        val br = BufferedReader(fileR) //Creamos un buffer para leer el archivo
        var cadena: String? = null //Cadena que almacenara el contenido del archivo
        //Mientras haya lineas en el archivo
        while ({ cadena = br.readLine(); cadena }() != null) {
            //println("${cadena!!.split(", ")[0]} -> ${data}" )
            println(" -> ${cadena!!.split(", ")[0]}")
            if (cadena!!.split(",")[0].equals(data)) {
                return true//Retorna true si el dato existe
            }
        }
        br.close() //Cerramos el buffer
        fileR.close() //Cerramos el archivo
    }
    return false //Retornamos false si no existe el dato
}
