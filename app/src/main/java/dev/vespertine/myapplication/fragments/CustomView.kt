package dev.vespertine.myapplication.fragments

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import dev.vespertine.myapplication.R
import dev.vespertine.myapplication.customview.Circle
import dev.vespertine.myapplication.customview.CircleAngleAnimation
import kotlinx.android.synthetic.main.fragment_custom_view.*


class CustomView : Fragment() {


    var angleInc : Int = 30
    lateinit var toAnimate : Circle

    private lateinit var animation : CircleAngleAnimation

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_custom_view, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        toAnimate = circle_view
        animation = CircleAngleAnimation(toAnimate, angleInc)
        animation.duration = 1000L

        toAnimate.setOnClickListener{
            toAnimate.startAnimation(animation)
            animation.incremenet()
            if(toAnimate.getAngle() >= 270.0) {
                toAnimate.isClickable = false
            }
        }
    }

    companion object {
        fun newInstance() = CustomView()
    }
}
