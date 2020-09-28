package com.aripratom.gblaticketingapp.util

import android.app.Dialog
import android.content.Context
import android.view.Window
import com.aripratom.gblaticketingapp.R
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.loading_dialog.*

object LoadingDialog {
    private lateinit var dialog: Dialog

    fun showLoading(context: Context, title: Int) {
        dialog = Dialog(context).apply {
            requestWindowFeature(Window.FEATURE_NO_TITLE)
            setContentView(R.layout.loading_dialog)
            setCancelable(false)
        }

        dialog.textView.text = context.resources.getString(title)
        Glide.with(context).asGif().load(R.drawable.loadingball).into(dialog.progressBar)
        dialog.show()
    }

    fun showLoading(context: Context, title: String) {
        dialog = Dialog(context).apply {
            requestWindowFeature(Window.FEATURE_NO_TITLE)
            setContentView(R.layout.loading_dialog)
            setCancelable(false)
        }

        dialog.textView.text = title
        Glide.with(context).asGif().load(R.drawable.loadingball).into(dialog.progressBar)
        dialog.show()
    }

    fun hideLoading() {
        dialog.dismiss()
    }
}