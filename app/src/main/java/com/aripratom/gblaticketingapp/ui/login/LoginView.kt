package com.aripratom.gblaticketingapp.ui.login

interface LoginView {
    fun showLoading()
    fun hideLoading()
    fun loginSuccess()
    fun loginFail()
}