package dev.vespertine.myapplication.customview

import android.view.animation.Animation
import android.view.animation.Transformation

class CircleAngleAnimation(private val circle: Circle, angleIncrement: Int) : Animation() {

    var oldAngle: Float
    var newAngle: Float
    private val angleIncrement: Float

    init {
        this.oldAngle = circle.getAngle()
        this.newAngle = circle.getAngle()
        this.angleIncrement = angleIncrement.toFloat()
    }

    override fun applyTransformation(interpolatedTime: Float, transformation: Transformation) {
        val angle = oldAngle + ((newAngle - oldAngle) * interpolatedTime)

        circle.setAngle(angle)
        circle.requestLayout()


    }

    fun incremenet() {
        oldAngle = newAngle
        newAngle += angleIncrement

    }

}