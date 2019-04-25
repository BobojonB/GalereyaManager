package com.mrpresident952gmail.kmanager

import android.graphics.Bitmap
import android.media.ThumbnailUtils
import android.net.Uri
import android.provider.MediaStore
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.squareup.picasso.Picasso
import java.io.File


class FileAdapter : RecyclerView.Adapter<FileAdapter.ViewHolder>() {
    var callBack: CallBack? = null

    interface CallBack {
        fun onItemClick(name: String)
        fun onItemLongClick(name: String)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.model, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder!!.bindView(data[position])
    }

    var data: ArrayList<ModelFile> = ArrayList<ModelFile>()

    override fun getItemCount(): Int = data.size


    fun setItems(model: ArrayList<ModelFile>) {
        data.clear()
        model.sortBy { it.name }
        data.addAll(model)
        notifyDataSetChanged()
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val image: ImageView = itemView.findViewById<ImageView>(R.id.iconca)
        val name: TextView = itemView.findViewById<TextView>(R.id.name)


        fun bindView(modelFile: ModelFile) {

            val dir = File(modelFile.uri + "${modelFile.name}")
            if (dir.isDirectory) {
                image.setImageResource(R.drawable.folder_outline)
            } else if ( modelFile.name.toLowerCase().endsWith(".mp3", true) ||
                        modelFile.name.endsWith(".ogg", true) ||
                        modelFile.name.endsWith(".aa", true) ||
                        modelFile.name.endsWith(".aac", true) ||
                        modelFile.name.endsWith(".aax", true) ||
                        modelFile.name.endsWith(".act", true) ||
                        modelFile.name.endsWith(".aiff", true) ||
                        modelFile.name.endsWith(".amr", true) ||
                        modelFile.name.endsWith(".ape", true) ||
                        modelFile.name.endsWith(".au", true) ||
                        modelFile.name.endsWith(".awb", true) ||
                        modelFile.name.endsWith(".dct", true) ||
                        modelFile.name.endsWith(".dss", true) ||
                        modelFile.name.endsWith(".dvf", true) ||
                        modelFile.name.endsWith(".flac", true) ||
                        modelFile.name.endsWith(".m4a", true) ||
                        modelFile.name.endsWith(".m4b", true) ||
                        modelFile.name.endsWith(".m4p", true) ||
                        modelFile.name.endsWith(".oga", true) ||
                        modelFile.name.endsWith(".raw", true) ||
                        modelFile.name.endsWith(".wav", true) ||
                        modelFile.name.endsWith(".wv", true) ||
                        modelFile.name.endsWith(".8svx", true)) {
                image.setImageResource(R.drawable.music_circle)
            } else if (modelFile.name.endsWith(".jpg", true) ||
                    modelFile.name.endsWith(".jpeg", true) ||
                    modelFile.name.endsWith(".png", true) ||
                    modelFile.name.endsWith(".ico", true)) {
                val f = File(modelFile.uri + "${modelFile.name}")
                Picasso.get()
                        .load(f)
                        .placeholder(R.drawable.image)
                        .fit()
                        .into(image)


//                image.setImageResource(R.drawable.image)
            } else if (dir.toString().toLowerCase().endsWith(".mp4") ||
                    dir.toString().toLowerCase().endsWith(".mkv") ||
                    dir.toString().toLowerCase().endsWith(".3gp") ||
                    dir.toString().toLowerCase().endsWith(".avi") ||
                    dir.toString().toLowerCase().endsWith(".mov") ||
                    dir.toString().toLowerCase().endsWith(".flv") ||
                    dir.toString().toLowerCase().endsWith(".wmv") ||
                    dir.toString().toLowerCase().endsWith(".mpg") ||
                    dir.toString().toLowerCase().endsWith(".mpeg") ||
                    dir.toString().toLowerCase().endsWith(".m2v") ||
                    dir.toString().toLowerCase().endsWith(".m4v") ||
                    dir.toString().toLowerCase().endsWith(".webm")) {

//                Bitmap bmThumbnail = ThumbnailUtils.extractThumbnail(ThumbnailUtils.createVideoThumbnail(message.getFilePath(),
//                        MediaStore.Video.Thumbnails.FULL_SCREEN_KIND), MAX_WIDTH, MAX_HEIGHT);
                val f = File(modelFile.uri + "${modelFile.name}")
//                val bitmap = ThumbnailUtils.extractThumbnail (ThumbnailUtils.createVideoThumbnail(f.toString() ,
//                        MediaStore.Video.Thumbnails.FULL_SCREEN_KIND),72,72)

                Picasso.get()
                        .load(R.drawable.video)
                        .placeholder(R.drawable.video)
                        .fit()
                        .into(image)

//                image.setImageBitmap(bitmap)
            } else if (modelFile.name.endsWith(".ppt", true) ||
                    modelFile.name.endsWith(".pptx", true) ||
                    modelFile.name.endsWith(".doc", true) ||
                    modelFile.name.endsWith(".docx", true) ||
                    modelFile.name.endsWith(".xls", true) ||
                    modelFile.name.endsWith(".xlsx", true) ||
                    modelFile.name.endsWith(".doc", true) ||
                    modelFile.name.endsWith(".pdf", true)) {
                image.setImageResource(R.drawable.file)
            } else if (modelFile.name.endsWith(".vcf", true)) {
                image.setImageResource(R.drawable.account)
            } else if (modelFile.name.endsWith(".7z") ||
                    modelFile.name.endsWith(".zip") ||
                    modelFile.name.endsWith(".rar")) {
                Picasso.get()
                        .load(R.drawable.ic_zip)
                        .placeholder(R.drawable.ic_zip)
                        .fit()
                        .into(image)
            } else {
                image.setImageResource(R.drawable.file_question)
            }
            name.setText(modelFile.name)

            itemView.setOnClickListener {
                callBack?.onItemClick(modelFile.name)
            }
            itemView.setOnLongClickListener {
                callBack?.onItemLongClick(modelFile.name)
                true
            }

        }

    }

}