package com.example.e_greetingsapp.Activity

import android.app.DownloadManager
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Toast
import com.example.e_greetingsapp.R
import com.squareup.picasso.Picasso
import com.squareup.picasso.Target
import kotlinx.android.synthetic.main.activity_full_screen.*
import java.io.ByteArrayOutputStream
import java.lang.Exception

class FullScreenActivity : AppCompatActivity()
{
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_full_screen)

        val i: Intent = getIntent()
        var originalUrl = i.getStringExtra("image_position")!!
        Picasso.get().load(originalUrl).into(photo)

        img_download.setOnClickListener()
        {
            var downloadmanager:DownloadManager= getSystemService(DOWNLOAD_SERVICE) as DownloadManager
            val uri = Uri.parse(originalUrl)
            val request: DownloadManager.
            Request = DownloadManager.Request(uri)
            request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
            downloadmanager.enqueue(request)
            Toast.makeText(this@FullScreenActivity, "Downloading Started", Toast.LENGTH_SHORT)
                .show()

        }
        img_share.setOnClickListener()
        {
            Picasso.get().load(originalUrl).into(object :Target{
                override fun onBitmapLoaded(bitmap: Bitmap?, from: Picasso.LoadedFrom?) {
                    shareprepareintent(bitmap!!)
                }

                override fun onBitmapFailed(e: Exception?, errorDrawable: Drawable?) {

                }

                override fun onPrepareLoad(placeHolderDrawable: Drawable?) {

                }
            })
        }

}
    fun shareprepareintent(bitmap: Bitmap)
    {
        val shareIntent = Intent()
        shareIntent.setAction(Intent.ACTION_SEND)
        shareIntent.putExtra(Intent.EXTRA_STREAM, getImageUri(this@FullScreenActivity, bitmap))
        shareIntent.putExtra(Intent.EXTRA_TEXT, "Hey there" + "https://www.tops-int.com")
        shareIntent.setType("image/*")
        shareIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
        startActivity(Intent.createChooser(shareIntent, "Share Opportunity"))
    }
    companion object {

        fun getImageUri(inContext: Context, bitmapInImage: Bitmap): Uri {
            val bytes = ByteArrayOutputStream()
            bitmapInImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes)
            val path: String = MediaStore.Images.Media.insertImage(
                inContext.contentResolver,
                bitmapInImage,
                "Greetings",
                null
            )
            return Uri.parse(path)
        }

}


}