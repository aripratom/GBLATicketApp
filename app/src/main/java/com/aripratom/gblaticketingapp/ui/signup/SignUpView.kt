package com.aripratom.gblaticketingapp.ui.signup

interface SignUpView {
    fun showCity()
    fun showLoading()
    fun hideLoading()
    fun signUpSuccess()
    fun signUpFail()
}