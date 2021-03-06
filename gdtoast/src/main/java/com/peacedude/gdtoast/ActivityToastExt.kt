package com.peacedude.gdtoast

import android.app.Activity
import android.app.Application
import android.graphics.Color
import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
import android.graphics.drawable.Drawable
import android.os.Build
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.ColorInt
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat.getDrawable


object Darot{
    var getGdDrawableBackgrnd = R.drawable.gd_toast_bkgrnd
}
fun Activity.changeDrawableBackgrnd(container:ViewGroup){
    val gdToastDrawableBackground = getDrawable(Darot.getGdDrawableBackgrnd)
    container.background = gdToastDrawableBackground
}
val Activity.getGdToastLayout: View
    get() {
        val layout by lazy {
            LayoutInflater.from(this).inflate(
                R.layout.gdtoast_layout, findViewById(R.id.toast_layout_vg)
            )
        }
        return layout
    }


/**
 * Set message constraint(limit)
 *
 * @param text
 * @param message
 */
fun Activity.messageConstraint(text: TextView, message: String) {
    if (message.length > 22) {
        text.setEms(22)
    }
}

/**
 * Shows simple toast message with
 *
 * @param message
 * @param gravity
 */
fun Activity.gdToast(message: String, gravity: Int) {

    val layout = getGdToastLayout
    val container: ViewGroup = layout.findViewById(R.id.toast_layout_vg)
    val text: TextView = layout.findViewById(R.id.toast_text)
    val image: ImageView = layout.findViewById(R.id.toast_icon)
    image.hide()
    text.setTextColor(Color.BLACK)
    changeDrawableBackgrnd(container)
    messageConstraint(text, message)
    text.text = message
    with(Toast(this)) {
        setGravity(gravity, 0, 50)
        duration = Toast.LENGTH_LONG
        view = layout
        show()
    }
}

/**
 * Shows simple toast message with
 *
 * @param message
 * @param icon
 * @param backgroundColor
 * @param gravity
 */

fun Activity.gdToast(
    message: String,
    @DrawableRes icon: Int,
    @ColorRes backgroundColor: Int,
    gravity: Int? = null
) {

    val layout = getGdToastLayout
    val container: ViewGroup = layout.findViewById(R.id.toast_layout_vg)
    val text: TextView = layout.findViewById(R.id.toast_text)
    val image: ImageView = layout.findViewById(R.id.toast_icon)
    val gdToastDrawableBackground = getDrawable(Darot.getGdDrawableBackgrnd)

    if (Build.VERSION.SDK_INT > Build.VERSION_CODES.M) {
        image.setImageDrawable(resources.getDrawable(icon, theme))
        gdToastDrawableBackground?.colorFilter = PorterDuffColorFilter(
            resources.getColor(backgroundColor, theme),
            PorterDuff.Mode.SRC_IN
        )
        text.setTextColor(resources.getColor(R.color.colorPrimary, theme))

    } else {
        image.setImageDrawable(resources.getDrawable(icon))
        text.setTextColor(resources.getColor(R.color.colorPrimary))
        gdToastDrawableBackground?.colorFilter =
            PorterDuffColorFilter(resources.getColor(backgroundColor), PorterDuff.Mode.SRC_IN)

    }
    container.background = gdToastDrawableBackground
    messageConstraint(text, message)
    text.text = message
    with(Toast(this)) {
        when {
            gravity != null -> setGravity(gravity, 0, 0)
            else -> setGravity(Gravity.BOTTOM, 0, 0)
        }

        duration = Toast.LENGTH_LONG
        view = layout
        show()
    }
}

/**
 * Shows simple toast message with
 *
 * @param message
 * @param gravity
 * @param xOffset
 * @param yOffSet
 */
fun Activity.gdToast(message: String, gravity: Int, coordinates: Pair<Int, Int>) {

    val layout = getGdToastLayout
    val container: ViewGroup = layout.findViewById(R.id.toast_layout_vg)
    val text: TextView = layout.findViewById(R.id.toast_text)
    val image: ImageView = layout.findViewById(R.id.toast_icon)
    changeDrawableBackgrnd(container)
    image.hide()

    if (Build.VERSION.SDK_INT > Build.VERSION_CODES.M) {
        text.setTextColor(resources.getColor(R.color.colorPrimary, theme))
    } else {
        text.setTextColor(resources.getColor(R.color.colorPrimary))
    }

    messageConstraint(text, message)
    text.text = message
    with(Toast(this)) {
        setGravity(gravity, coordinates.first, coordinates.second)
        duration = Toast.LENGTH_LONG
        view = layout
        show()
    }
}

/**
 * Shows simple toast message with
 *
 * @param message
 * @param icon
 * @param gravity
 */
fun Activity.gdToast(message: String, @DrawableRes icon: Int, gravity: Int) {

    val layout = getGdToastLayout
    val text: TextView = layout.findViewById(R.id.toast_text)
    val container: ViewGroup = layout.findViewById(R.id.toast_layout_vg)
    val image: ImageView = layout.findViewById(R.id.toast_icon)
    changeDrawableBackgrnd(container)
    if (Build.VERSION.SDK_INT > Build.VERSION_CODES.M) {
        image.setImageDrawable(resources.getDrawable(icon, theme))
        text.setTextColor(resources.getColor(R.color.colorPrimary, theme))
    } else {
        image.setImageDrawable(resources.getDrawable(icon))
        text.setTextColor(resources.getColor(R.color.colorPrimary))
    }

    messageConstraint(text, message)
    text.text = message
    with(Toast(this)) {
        setGravity(gravity, 0, 0)
        duration = Toast.LENGTH_LONG
        view = layout
        show()
    }
}


/**
 * Shows simple toast message with
 *
 * @param message
 * @param icon
 * @param backgroundColor
 * @param textColor
 * @param gravity
 * @param toastDuration
 */
fun Activity.gdToast(
    message: String,
    @DrawableRes icon: Int,
    backgroundColor: String,
    textColor: Int,
    gravity: Int,
    toastDuration: Int
) {

    val layout = getGdToastLayout
     val gdToastDrawableBackground = getDrawable(Darot.getGdDrawableBackgrnd)
    val container: ViewGroup = layout.findViewById(R.id.toast_layout_vg)
    val text: TextView = layout.findViewById(R.id.toast_text)
    val image: ImageView = layout.findViewById(R.id.toast_icon)


    gdToastDrawableBackground?.colorFilter =
        PorterDuffColorFilter(Color.parseColor(backgroundColor), PorterDuff.Mode.SRC_IN)

    if (Build.VERSION.SDK_INT > Build.VERSION_CODES.M) {
        image.setImageDrawable(resources.getDrawable(icon, theme))
        container.background = gdToastDrawableBackground
        text.setTextColor(resources.getColor(textColor, theme))
    } else {
        image.setImageDrawable(resources.getDrawable(icon))
        text.setTextColor(resources.getColor(textColor))
        container.background = gdToastDrawableBackground
    }

    messageConstraint(text, message)
    text.text = message
    with(Toast(this)) {
        setGravity(gravity, 0, 50)
        duration = toastDuration
        view = layout
        show()
    }
}

/**
 * Shows simple toast message with
 *
 * @param message
 * @param icon
 * @param backgroundColor
 * @param textColor
 * @param gravity
 * @param toastDuration
 */
fun Activity.gdToast(
    message: String,
    icon: Int,
    @ColorRes backgroundColor: Int,
    textColor: Int,
    gravity: Int,
    toastDuration: Int
) {

    val layout = getGdToastLayout
     val gdToastDrawableBackground = getDrawable(Darot.getGdDrawableBackgrnd)
    val container: ViewGroup = layout.findViewById(R.id.toast_layout_vg)
    val text: TextView = layout.findViewById(R.id.toast_text)
    val image: ImageView = layout.findViewById(R.id.toast_icon)

    if (Build.VERSION.SDK_INT > Build.VERSION_CODES.M) {
        image.setImageDrawable(resources.getDrawable(icon, theme))
        gdToastDrawableBackground?.colorFilter =
            PorterDuffColorFilter(
                resources.getColor(backgroundColor, theme),
                PorterDuff.Mode.SRC_IN
            )
        container.background = gdToastDrawableBackground
        text.setTextColor(resources.getColor(textColor, theme))
    } else {
        image.setImageDrawable(resources.getDrawable(icon))
        text.setTextColor(resources.getColor(textColor))
        gdToastDrawableBackground?.colorFilter =
            PorterDuffColorFilter(resources.getColor(backgroundColor), PorterDuff.Mode.SRC_IN)
        container.background = gdToastDrawableBackground
    }

    messageConstraint(text, message)
    text.text = message
    with(Toast(this)) {
        setGravity(gravity, 0, 50)
        duration = toastDuration
        view = layout
        show()
    }
}

/**
 * Shows simple toast message with
 *
 * @param message
 * @param icon
 * @param backgroundColor
 * @param textColor
 * @param gravity
 * @param x_Offset
 * @param y_OffSet
 * @param toastDuration
 */
fun Activity.gdToast(
    message: String,
    @DrawableRes icon: Int,
    @ColorRes backgroundColor: Int,
    textColor: Int,
    gravity: Int,
    x_Offset: Int,
    y_OffSet: Int,
    toastDuration: Int
) {

    val layout = getGdToastLayout
     val gdToastDrawableBackground = getDrawable(Darot.getGdDrawableBackgrnd)
    val container: ViewGroup = layout.findViewById(R.id.toast_layout_vg)
    val text: TextView = layout.findViewById(R.id.toast_text)
    val image: ImageView = layout.findViewById(R.id.toast_icon)


    if (Build.VERSION.SDK_INT > Build.VERSION_CODES.M) {
        image.setImageDrawable(resources.getDrawable(icon, theme))
        gdToastDrawableBackground?.colorFilter =
            PorterDuffColorFilter(
                resources.getColor(backgroundColor, theme),
                PorterDuff.Mode.SRC_IN
            )
        container.background = gdToastDrawableBackground
        text.setTextColor(resources.getColor(textColor, theme))
    } else {
        image.setImageDrawable(resources.getDrawable(icon))
        text.setTextColor(resources.getColor(textColor))
        gdToastDrawableBackground?.colorFilter =
            PorterDuffColorFilter(resources.getColor(backgroundColor), PorterDuff.Mode.SRC_IN)
        container.background = gdToastDrawableBackground
    }

    messageConstraint(text, message)
    text.text = message
    with(Toast(this)) {
        setGravity(gravity, x_Offset, y_OffSet)
        duration = toastDuration
        view = layout
        show()
    }
}

/**
 * Shows simple toast message with
 *
 * @param message
 * @param icon
 * @param backgroundColor
 * @param textColor
 * @param gravity
 * @param textGravity
 * @param x_Offset
 * @param y_OffSet
 * @param toastDuration
 */
fun Activity.gdToast(
    message: String,
    icon: Int,
    backgroundColor: Int,
    textColor: Int,
    gravity: Int,
    textGravity: Int,
    x_Offset: Int,
    y_OffSet: Int,
    toastDuration: Int

) {

    val layout = getGdToastLayout
     val gdToastDrawableBackground = getDrawable(Darot.getGdDrawableBackgrnd)
    val container: ViewGroup = layout.findViewById(R.id.toast_layout_vg)
    val text: TextView = layout.findViewById(R.id.toast_text)
    val image: ImageView = layout.findViewById(R.id.toast_icon)

    text.gravity = textGravity

    if (Build.VERSION.SDK_INT > Build.VERSION_CODES.M) {
        image.setImageDrawable(resources.getDrawable(icon, theme))
        gdToastDrawableBackground?.colorFilter =
            PorterDuffColorFilter(
                resources.getColor(backgroundColor, theme),
                PorterDuff.Mode.SRC_IN
            )
        container.background = gdToastDrawableBackground
        text.setTextColor(resources.getColor(textColor, theme))
    } else {
        image.setImageDrawable(resources.getDrawable(icon))
        text.setTextColor(resources.getColor(textColor))
        gdToastDrawableBackground?.colorFilter =
            PorterDuffColorFilter(resources.getColor(backgroundColor), PorterDuff.Mode.SRC_IN)
        container.background = gdToastDrawableBackground
    }

    messageConstraint(text, message)
    text.text = message
    with(Toast(this)) {
        setGravity(gravity, x_Offset, y_OffSet)
        duration = toastDuration
        view = layout
        show()
    }
}

fun Activity.gdToast(
    message: String,
    icon: Drawable?,
    backgroundColor: Int?=null,
    textColor: Int?=null,
    gravity: Int?=null,
    textGravity: Int?=null,
    x_Offset: Int = 0,
    y_OffSet: Int = 0,
    toastDuration:Int = Toast.LENGTH_LONG

) {

    val layout = getGdToastLayout
     val gdToastDrawableBackground = getDrawable(Darot.getGdDrawableBackgrnd)
    val container: ViewGroup = layout.findViewById(R.id.toast_layout_vg)
    val text: TextView = layout.findViewById(R.id.toast_text)
    val image: ImageView = layout.findViewById(R.id.toast_icon)

    if (textGravity != null) {
        text.gravity = textGravity
    }

    image.setImageDrawable(icon)

    if (Build.VERSION.SDK_INT > Build.VERSION_CODES.M) {
        when{
            backgroundColor != null -> {
                gdToastDrawableBackground?.colorFilter =
                PorterDuffColorFilter(
                    resources.getColor(backgroundColor, theme),
                    PorterDuff.Mode.SRC_IN
                )}
            textColor != null -> text.setTextColor(resources.getColor(textColor, theme))
            else ->{
                gdToastDrawableBackground?.colorFilter =
                    PorterDuffColorFilter(
                        resources.getColor(R.color.colorPrimary, theme),
                        PorterDuff.Mode.SRC_IN
                    )
                text.setTextColor(Color.BLACK)
            }
        }

        container.background = gdToastDrawableBackground

    } else {
        when{
            backgroundColor != null -> {
                gdToastDrawableBackground?.colorFilter =
                    PorterDuffColorFilter(resources.getColor(backgroundColor), PorterDuff.Mode.SRC_IN)}
            textColor != null -> text.setTextColor(resources.getColor(textColor))
            else ->{
                gdToastDrawableBackground?.colorFilter =
                    PorterDuffColorFilter(
                        resources.getColor(R.color.colorPrimary),
                        PorterDuff.Mode.SRC_IN
                    )
                text.setTextColor(Color.BLACK)
            }
        }
        container.background = gdToastDrawableBackground
    }

    messageConstraint(text, message)
    text.text = message
    var grav:Int = Gravity.CENTER
    with(Toast(this)) {
        when {
            gravity == null -> {
                grav = Gravity.CENTER
                setGravity(Gravity.CENTER, x_Offset, y_OffSet)
            }
            else ->{
                grav = gravity
                setGravity(gravity, x_Offset, y_OffSet)
                duration = toastDuration
            }

        }

        view = layout
        show()
    }
}