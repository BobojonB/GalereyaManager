package com.mrpresident952gmail.kmanager

import android.content.Context
import android.content.Intent
import android.media.MediaPlayer
import android.net.Uri
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.provider.MediaStore
import android.support.v4.content.FileProvider
import android.support.v7.app.AlertDialog
import android.support.v7.widget.GridLayoutManager
import android.view.View
import android.view.ViewGroup
import android.widget.*
import kotlinx.android.synthetic.main.activity_kmanager.*
import java.io.File
import java.util.*


class KManagerActivity : AppCompatActivity(), FileAdapter.CallBack {

    var pler = MediaPlayer()
    var files = ArrayList<ModelFile>()
    var path: String = "/storage/emulated/0/"
    var adapter = FileAdapter()
    var n = 1
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kmanager)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        val editTextFolder: EditText = EditText(this)
        editTextFolder.hint = "Papka nomini kiriting : "
        val editTextFile: EditText = EditText(this)
        val editTextFilek: EditText = EditText(this)
        editTextFile.hint = "Fayl nomini kiriting : "
        editTextFilek.hint = "Fayl kengaytmasini kiriting : "
        recyclerView.layoutManager = GridLayoutManager(this, 4)
        startFile()
        fab.setOnClickListener {
            AlertDialog.Builder(this)
                    .setTitle("Papka yaratish")
                    .setMessage("Yangi papka nomini kiriting:")
                    .setView(editTextFolder)
                    .setPositiveButton("Yaratish") { dialog, which ->
                        val f = File(path + '/' + editTextFolder.text.toString())
                        try {
                            if (!f.exists()) {
                                f.mkdir()
                            }
                            startFile()
                        } catch (e: Exception) {
                            Toast.makeText(this, e.stackTrace.toString(), Toast.LENGTH_SHORT).show()
                        }

                    }
                    .setNegativeButton("Qaytish") { dialog, which ->
                        Toast.makeText(this, "ochmadi", Toast.LENGTH_SHORT).show()
                    }
                    .show()
        }
        fab.setOnLongClickListener {
            AlertDialog.Builder(this)
                    .setTitle("Fayl yaratish")
                    .setMessage("Yangi fal yaratish")
                    .setView(editTextFile)
                    .setView(editTextFilek)
                    .setPositiveButton("Yaratish") { dialog, which ->
                        val f = File(path + '/' + editTextFile.text.toString() + '.' + editTextFilek.text.toString())
                        try {
                            f.createNewFile()
                            startFile()
                        } catch (e: Exception) {
                            Toast.makeText(this, e.stackTrace.toString(), Toast.LENGTH_SHORT).show()
                        }
                    }
                    .setNegativeButton("Qaytish") { dialog, which ->
                        Toast.makeText(this, "Fayl yaratilmadi", Toast.LENGTH_SHORT).show()

                    }
                    .show()
            true
        }

    }


    override fun onItemLongClick(name: String) {
        AlertDialog.Builder(this)
                .setTitle("$name fayli o'chirilsinmi")
                .setMessage("Ushbu $name fayli o'chadi")
                .setPositiveButton("YES") { dialog, which ->
                    val f = File(path + '/' + name)
                    try {
                        Toast.makeText(this, f.path.toString(), Toast.LENGTH_SHORT).show()
                        f.delete()
                        startFile()
                    } catch (e: Exception) {
                        Toast.makeText(this, e.stackTrace.toString(), Toast.LENGTH_SHORT).show()
                    }

                }
                .setNegativeButton("Yo'q") { dialog, which ->
                    Toast.makeText(this, "ochmadi", Toast.LENGTH_SHORT).show()
                }
                .show()
    }


    override fun onItemClick(path_temp: String) {
        n++
        toolBarButton()
        if (path.endsWith('/', true)) {
            path += path_temp + '/'
        } else {
            path += '/' + path_temp + '/'
        }
        val dir = File(path)

        if (dir.isDirectory) {
            startFile()
        } else if (dir.toString().toLowerCase().endsWith(".mp4")||
                dir.toString().toLowerCase().endsWith(".mkv")||
                dir.toString().toLowerCase().endsWith(".3gp")||
                dir.toString().toLowerCase().endsWith(".avi")||
                dir.toString().toLowerCase().endsWith(".mov")||
                dir.toString().toLowerCase().endsWith(".flv")||
                dir.toString().toLowerCase().endsWith(".wmv")||
                dir.toString().toLowerCase().endsWith(".mpg")||
                dir.toString().toLowerCase().endsWith(".mpeg")||
                dir.toString().toLowerCase().endsWith(".m2v")||
                dir.toString().toLowerCase().endsWith(".m4v")||
                dir.toString().toLowerCase().endsWith(".webm")

                                                    ) {
            val i = Intent(Intent.ACTION_VIEW)
            i.setDataAndType(Uri.parse(dir.path), "video/*")
            startActivity(i)
            path = back(path)
        } else if (dir.path.toLowerCase().endsWith(".mp3", true) ||
                dir.path.toLowerCase().endsWith(".ogg", true) ||
                dir.path.toLowerCase().endsWith(".aa", true) ||
                dir.path.toLowerCase().endsWith(".aac", true) ||
                dir.path.toLowerCase().endsWith(".aax", true) ||
                dir.path.toLowerCase().endsWith(".act", true) ||
                dir.path.toLowerCase().endsWith(".aiff", true) ||
                dir.path.toLowerCase().endsWith(".amr", true) ||
                dir.path.toLowerCase().endsWith(".ape", true) ||
                dir.path.toLowerCase().endsWith(".au", true) ||
                dir.path.toLowerCase().endsWith(".awb", true) ||
                dir.path.toLowerCase().endsWith(".dct", true) ||
                dir.path.toLowerCase().endsWith(".dss", true) ||
                dir.path.toLowerCase().endsWith(".dvf", true) ||
                dir.path.toLowerCase().endsWith(".flac", true) ||
                dir.path.toLowerCase().endsWith(".m4a", true) ||
                dir.path.toLowerCase().endsWith(".m4b", true) ||
                dir.path.toLowerCase().endsWith(".m4p", true) ||
                dir.path.toLowerCase().endsWith(".oga", true) ||
                dir.path.toLowerCase().endsWith(".raw", true) ||
                dir.path.toLowerCase().endsWith(".wav", true) ||
                dir.path.toLowerCase().endsWith(".wv", true) ||
                dir.path.toLowerCase().endsWith(".8svx", true)) {
//            val i = Intent(Intent.ACTION_VIEW)
//            i.addCategory(Intent.CATEGORY_OPENABLE)
//            i.setType("audio/*")
//            i.data =Uri.parse(dir.path)

            val i = Intent(Intent.ACTION_VIEW)
            i.setDataAndType(Uri.parse(dir.path), "audio/*")
            startActivity(i)
            path = back(path)

        } else if (dir.path.toString().toLowerCase().endsWith(".jpg") ||
                dir.path.toString().toLowerCase().endsWith(".jpeg") ||
                dir.path.toString().toLowerCase().endsWith(".png") ||
                dir.path.toString().toLowerCase().endsWith(".bmp")) {
            Toast.makeText(this,dir.path,Toast.LENGTH_LONG).show()
            val i = Intent(Intent.ACTION_VIEW)
            i.setDataAndType(Uri.parse(dir.path),"image/*")
//            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(i)
            path = back(path)
        }else {
            Toast.makeText(this,"Aniqlanmagan",Toast.LENGTH_SHORT).show()
            path = back(path)
        }
    }

    private fun back(p0: String): String {
        var p = File(p0).parentFile.toString()
        if (!p.endsWith('/', true))
            p += '/'
        return p
    }

    override fun onBackPressed() {
        n--
        toolBarButton()
        if (path.equals("/") ||
                path.equals("//")
                ) {
            super.onBackPressed()
        } else {
            if (main_Layout.visibility == View.GONE) {
                empty.visibility = View.GONE
                main_Layout.visibility = View.VISIBLE
            }
            path = back(path)
            Toast.makeText(this, path, Toast.LENGTH_SHORT).show()
            startFile()
        }
    }

    private fun toolBarButton() {
        if (n <= 1) {
            supportActionBar?.setDisplayHomeAsUpEnabled(false)
        } else {
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
        }

    }

    private fun startFile() {
        val dir = File(path)
        val list = dir.list()
        files.clear()
        if (list != null) {
            for (file in list!!) {
                if (!file.startsWith(".")) {
                    val a = File(file)
                    files.add(ModelFile(name = a.name, uri = path))
                }
            }
            adapter.callBack = this
            adapter.setItems(files)
            recyclerView.adapter = adapter
            pathAndroid.text = path
        }
        if (files.isEmpty()) {
            main_Layout.visibility = View.GONE
            empty.visibility = View.VISIBLE
        }
    }

    fun isMusic(musicPath: String): Boolean {
        return true
    }
}
