package com.example.restaurante.clases

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.Marker
import com.squareup.picasso.Picasso

class PicassoMarker internal constructor(var mMarker: Marker) : com.squareup.picasso.Target{
    override fun hashCode(): Int {
        return mMarker.hashCode()
    }
    override fun equals(o: Any?): Boolean {
        return if (o is PicassoMarker) {
            val marker = o.mMarker
            mMarker == marker
        } else {
            false
        }
    }
    override fun onBitmapLoaded(bitmap: Bitmap?, from: Picasso.LoadedFrom?) {
        mMarker.setIcon(BitmapDescriptorFactory.fromBitmap(bitmap))
    }
    // override fun onBitmapFailed(errorDrawable: Drawable?) {}
    override fun onPrepareLoad(placeHolderDrawable: Drawable?) {}
    override fun onBitmapFailed(errorDrawable: Drawable?) {
        TODO("Not yet implemented")
    }
}