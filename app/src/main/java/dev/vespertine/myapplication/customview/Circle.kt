package dev.vespertine.myapplication.customview


import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import android.view.View

class Circle @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 1) : View(context, attrs, defStyleAttr) {

    private  val paint : Paint
    private val rect: RectF

    private var angle = 0F

    init {

        rect = RectF()
        paint = Paint()
        paint.apply {
            isAntiAlias = true
            style = Paint.Style.STROKE
            strokeWidth = 30F
            strokeCap = Paint.Cap.BUTT
            color = Color.RED
        }
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        val height = (height / 2.0).toFloat()
        val width = (width / 2.0).toFloat()
        val size: Float

        size = if (height < width) (height - paint.strokeWidth) else (width - paint.strokeWidth)

        rect.set(width - size, height - size, width + size, height + size)

        canvas.drawArc(rect, START_ANGLE_POINT.toFloat(), angle, false, paint)

    }


    fun getAngle() = angle

    fun setAngle(value: Float) {
        angle = value
    }

    companion object {
        private val START_ANGLE_POINT = 120
    }
}

