package com.aripratom.gblaticketingapp.ui.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.aripratom.gblaticketingapp.ui.main.MainActivity
import com.aripratom.gblaticketingapp.R
import com.aripratom.gblaticketingapp.ui.resetpassword.ResetPasswordActivity
import com.aripratom.gblaticketingapp.util.LoadingDialog
import com.aripratom.gblaticketingapp.util.ValidateUtil.emailValidate
import com.aripratom.gblaticketingapp.util.ValidateUtil.etToString
import com.aripratom.gblaticketingapp.util.ValidateUtil.passwordValidate
import com.aripratom.gblaticketingapp.util.visible
import kotlinx.android.synthetic.main.fragment_login.*
import org.jetbrains.anko.sdk27.coroutines.onClick
import org.jetbrains.anko.support.v4.startActivity

class LoginFragment: Fragment(), LoginView {

    private lateinit var presenter: LoginPresenter

    override fun showLoading() {
        LoadingDialog.showLoading(activity!!, R.string.login)
    }

    override fun hideLoading() {
        LoadingDialog.hideLoading()
    }

    override fun loginSuccess() {
        startActivity<MainActivity>()
        activity?.finish()
    }

    override fun loginFail() {
        tvWrong.visible()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        presenter = LoginPresenter(this)

        btnLogin.onClick { login() }
        btnForgot.onClick { startActivity<ResetPasswordActivity>() }
    }

    private fun login() {
        emailValidate(etEmail, getString(R.string.email_validate)) {
            passwordValidate(etPassword, getString(R.string.password_validate)) {
                presenter.login(etToString(etEmail), etToString(etPassword))
            }
        }
    }
}
