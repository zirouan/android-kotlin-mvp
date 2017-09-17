package br.com.liveo.mvp.base

import android.databinding.BindingAdapter
import android.graphics.Bitmap
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory
import android.text.TextUtils
import android.widget.ImageView
import android.widget.TextView
import br.com.liveo.mvp.model.User
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.BitmapImageViewTarget

/**
 * Created by rudsonlima on 8/29/17.
 */

open class BaseModel {

    companion object {
        @JvmStatic
        @BindingAdapter("imageLoadRounded")
        fun setImageLoadRounded(imageView: ImageView, urlImage: String) {
            if (!TextUtils.isEmpty(urlImage)) {

                Glide.with(imageView.context).load(urlImage).asBitmap().centerCrop().into(object : BitmapImageViewTarget(imageView) {
                    override fun setResource(resource: Bitmap) {
                        val circularBitmapDrawable = RoundedBitmapDrawableFactory.create(imageView.context.resources, resource)
                        circularBitmapDrawable.isCircular = true
                        imageView.setImageDrawable(circularBitmapDrawable)
                    }
                })
            }
        }

        @JvmStatic
        @BindingAdapter("nameWithLastName")
        fun setNameWithLastName(textView: TextView, user: User) {
            textView.text = java.lang.String.format("%s %s", user.name, user.lastName)
        }
    }
}
