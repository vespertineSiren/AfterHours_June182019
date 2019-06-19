package dev.vespertine.myapplication.fragments

import android.content.Context
import android.graphics.*
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.graphics.drawable.toBitmap

import kotlinx.android.synthetic.main.fragment_pdmask.*
import android.graphics.Bitmap
import androidx.core.view.drawToBitmap
import dev.vespertine.myapplication.R


class PDMask : Fragment() {

    lateinit var bitmap : Bitmap

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_pdmask, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var original = pikachu_image.convertToBitmap()
        bitmap = Bitmap.createBitmap(original.width, original.height, Bitmap.Config.ARGB_8888)

        var canvas = Canvas(bitmap)

        canvas.drawBitmap(original, 0F,0F, null)

        var maskPaint = Paint()
        var mask = createCircle(original.width, original.height)
        var mode = PorterDuffXfermode(PorterDuff.Mode.DST_IN)
        maskPaint.xfermode = mode
        canvas.drawBitmap(mask, 0F, 0F, maskPaint)

        pikachu_image.setImageBitmap(bitmap)
    }

    companion object {
        fun newInstance() = PDMask()
    }

    fun createCircle(width: Int, height: Int): Bitmap {
        val paint = Paint()
        paint.apply {
            style = Paint.Style.FILL
            color = Color.GREEN
        }


        val bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bitmap)
        val radius = Math.min(width, height) * 0.45f
        canvas.drawCircle((width / 2).toFloat(), (height / 2).toFloat(), radius, paint)

        return bitmap
    }

}

fun View.convertToBitmap(): Bitmap {
    val measureSpec = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED)
    measure(measureSpec, measureSpec)
    layout(0, 0, measuredWidth, measuredHeight)
    val r = Bitmap.createBitmap(measuredWidth, measuredHeight, Bitmap.Config.ARGB_8888)
    r.eraseColor(Color.TRANSPARENT)
    val canvas = Canvas(r)
    draw(canvas)
    return r }
