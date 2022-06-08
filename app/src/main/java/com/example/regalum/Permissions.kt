package com.example.regalum

import android.Manifest.permission.*
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager.*
import androidx.core.app.ActivityCompat
import androidx.core.app.ActivityCompat.checkSelfPermission

private var PERMISSION_REQUEST_CODE = 999
private lateinit var arrayPermission: Array<String>
private lateinit var cont: Context

//Function to check if we have permissions or not -> true when we have permission
fun hasPermission(): Boolean = checkSelfPermission( cont as Activity, WRITE_EXTERNAL_STORAGE) == PERMISSION_GRANTED

//Function detected the request permisssion when run app
fun requestPermission(context: Context): Boolean {
    //Get Context
    cont = context
    //If not permission
    if (!hasPermission()) { //If not permission
        //Init Array
        allPermissions() //Init Array
        ActivityCompat.requestPermissions(context as Activity,arrayPermission,PERMISSION_REQUEST_CODE)
        return true
    }
    return false
}

fun allPermissions() { //Init Array
    arrayPermission = arrayOf(
        WRITE_EXTERNAL_STORAGE, //Write external storage
        READ_EXTERNAL_STORAGE //Read external storage
    )
}