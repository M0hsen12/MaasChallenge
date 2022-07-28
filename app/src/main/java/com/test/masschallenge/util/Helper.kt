package com.test.masschallenge.util

import android.annotation.SuppressLint
import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.drawable.Drawable
import android.location.LocationManager
import android.text.SpannableString
import android.text.Spanned
import android.text.style.ReplacementSpan
import android.util.Log
import android.view.LayoutInflater
import android.view.Window
import android.widget.TextView
import androidx.annotation.StyleRes
import androidx.core.content.ContextCompat.getSystemService
import androidx.databinding.DataBindingUtil
import com.test.masschallenge.R
import com.test.masschallenge.databinding.DialogSimpleMapProgressBinding
import com.test.masschallenge.databinding.DialogSimpleProgressBinding
import com.test.masschallenge.model.MapMarkerEntity
import com.test.masschallenge.model.response.activities.RowsItem
import com.test.masschallenge.model.response.places.DataItem
import com.test.masschallenge.ui.activities.MapsActivity
import dagger.android.support.DaggerAppCompatActivity
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

fun materialSimpleMapProgressDialog(
    context: Context,
    @StyleRes theme: Int = R.style.ThemeDialog_Dark
): Dialog {
    return Dialog(context, theme).apply {
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setCancelable(true)
        val binder = DataBindingUtil.inflate<DialogSimpleMapProgressBinding>(
            LayoutInflater.from(context),
            R.layout.dialog_simple_map_progress,
            null,
            false
        )
        setContentView(binder.root)
    }
}

fun createUnderLineOnText(view: TextView, txt: String) {
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

fun isLocationIsOn(activity: Activity, onError: ((e: Exception) -> Unit)? = null): Boolean {

    val lm = activity.getSystemService(DaggerAppCompatActivity.LOCATION_SERVICE) as LocationManager
    var gpsEnabled = false
    var networkEnabled = false
    try {
        gpsEnabled = lm.isProviderEnabled(LocationManager.GPS_PROVIDER)
    } catch (ex: Exception) {
        Log.e("TAG", "isLocationServicesThere: $ex")
        onError?.invoke(ex)
    }
    try {
        networkEnabled = lm.isProviderEnabled(LocationManager.NETWORK_PROVIDER)
    } catch (ex: Exception) {
        onError?.invoke(ex)
        Log.e("TAG", "isLocationServicesThere1: $ex")
    }
    return !gpsEnabled && !networkEnabled
}

fun eventsListToMap(list: List<com.test.masschallenge.model.response.events.DataItem>):
        List<MapMarkerEntity> {

    val tempList = arrayListOf<MapMarkerEntity>()

    list.forEach {
        tempList.add(
            MapMarkerEntity(
                id = it.id,
                title = it.name.fi,
                type = MapsActivity.EVENTS_KEY,
                lat = it.location.lat,
                lng = it.location.lon
            )
        )

    }
    return tempList

}

fun activitiesListToMap(list: List<RowsItem>):
        List<MapMarkerEntity> {

    val tempList = arrayListOf<MapMarkerEntity>()

    list.forEach {
        tempList.add(
            MapMarkerEntity(
                id = it.id,
                title = it.descriptions.en.name,
                type = MapsActivity.ACTIVITIES_KEY,
                lat = it.address.location.lon,
                lng = it.address.location.lat
            )
        )

    }
    return tempList
}

fun placesListToMap(list: List<DataItem>):
        List<MapMarkerEntity> {

    val tempList = arrayListOf<MapMarkerEntity>()

    list.forEach {
        tempList.add(
            MapMarkerEntity(
                id = it.id,
                title = it.name.en,
                type = MapsActivity.PLACES_KEY,
                lat = it.location.lat,
                lng = it.location.lon
            )
        )

    }
    return tempList
}