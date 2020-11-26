package com.example.mynote

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.mynote.fragment.fragmentRecyclerViewNote.FragmentBasicNotes
import com.example.mynote.fragment.fragmentTabLayout.FragmentTabLayout

class MainActivity : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction()
            .replace(R.id.mainContainer, FragmentTabLayout()).commit()
    }
}