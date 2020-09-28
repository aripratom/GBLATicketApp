package com.aripratom.gblaticketingapp.ui.profiledit

import android.net.Uri
import com.aripratom.gblaticketingapp.data.User

interface EditProfilView {
    fun showData(user: User?)
    fun showLoading(title: String)
    fun hideLoading()
    fun showUpdated(title: String)
    fun showFail(title: String)
    //fun showUpdatedPhoto(path: Uri)
    //fun showChoosePhoto()
}