package com.test.masschallenge.util

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.drawable.Drawable
import android.text.SpannableString
import android.text.Spanned
import android.text.style.ReplacementSpan
import android.util.Log
import android.view.LayoutInflater
import android.view.Window
import android.widget.TextView
import androidx.annotation.StyleRes
import androidx.databinding.DataBindingUtil
import com.test.masschallenge.R
import com.test.masschallenge.databinding.DialogSimpleProgressBinding
import kotlin.math.roundToInt

fun materialSimpleProgressDialog(
    context: Context,
    @StyleRes theme: Int = R.style.ThemeDialog_Dark
): Dialog {
    return Dialog(context, theme).apply {
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setCancelable(true)
        val binder = DataBindingUtil.inflate<DialogSimpleProgressBinding>(
            LayoutInflater.from(context),
            R.layout.dialog_simple_progress,
            null,
            false
        )
        setContentView(binder.root)
    }
}

fun createUnderLineOnText(view: TextView, txt:String){
    SpannableString(txt).apply {
        setSpan(
            object : ReplacementSpan() {
                @SuppressLint("UseCompatLoadingForDrawables")
                private val drawable: Drawable =
                    view.context.resources.getDrawable(R.drawable.text_underline, null)

                override fun draw(
                    canvas: Canvas,
                    text: CharSequence,
                    start: Int,
                    end: Int,
                    x: Float,
                    top: Int,
                    y: Int,
                    bottom: Int,
                    paint: Paint
                ) {
                    drawable.setBounds(
                        (x.toInt()).minus(10),
                        top,
                        (x + measureText(paint, text, start, end)).plus(0).toInt(),
                        (bottom + (view.height - (bottom - top)) / 2.0f).roundToInt()
                    )
                    Log.e(
                        "BBB",
                        "draw: ${
                            (x + measureText(
                                paint,
                                text,
                                start,
                                end
                            )).plus(0).toInt()
                        } ---${(x.toInt()).minus(10)}"
                    )
                    canvas.drawText(text, start, end, x, y.toFloat(), paint)
                    drawable.draw(canvas)
                }

                override fun getSize(
                    paint: Paint,
                    text: CharSequence,
                    start: Int,
                    end: Int,
                    fm: Paint.FontMetricsInt?
                ): Int = paint.measureText(text, start, end).roundToInt()


                private fun measureText(
                    paint: Paint,
                    text: CharSequence,
                    start: Int,
                    end: Int
                ): Float = paint.measureText(text, start, end)

            },
            0,
            txt.length,
            Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
        )

        view.setText(this, TextView.BufferType.SPANNABLE)
    }

}
