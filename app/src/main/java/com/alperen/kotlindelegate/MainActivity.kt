package com.alperen.kotlindelegate

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner

class MainActivity : AppCompatActivity(), LifecycleLogger by LifecycleLoggerImplementation(){

    //property delegates
    private val myVariable by lazy {
        println("this is a lazy implementation")
         10
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        registerLifecycleOwner(this@MainActivity)
//eğer property delegate'i initialize edersen yani ona ulaşırsan println'i görür ve değeri hafızaya alırsın
        // bunun için println (myVariable) demek bile yeterli
        println(myVariable)
    }
}
interface LifecycleLogger {
    fun registerLifecycleOwner(owner: LifecycleOwner)
}
class LifecycleLoggerImplementation: LifecycleLogger, LifecycleEventObserver {
    override fun registerLifecycleOwner(owner: LifecycleOwner) {
        owner.lifecycle.addObserver(this)
    }

    override fun onStateChanged(source: LifecycleOwner, event: Lifecycle.Event) {
        when(event){
            Lifecycle.Event.ON_RESUME -> println("on resume executed")
            Lifecycle.Event.ON_PAUSE -> println("on pause executed")
            else -> Unit
        }

    }
}