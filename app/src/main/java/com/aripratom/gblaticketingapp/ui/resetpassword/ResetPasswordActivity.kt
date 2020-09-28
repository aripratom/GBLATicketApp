package com.aripratom.gblaticketingapp.ui.resetpassword

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.aripratom.gblaticketingapp.R
import com.aripratom.gblaticketingapp.util.LoadingDialog
import com.aripratom.gblaticketingapp.util.ValidateUtil.emailValidate
import com.aripratom.gblaticketingapp.util.ValidateUtil.etToString
import kotlinx.android.synthetic.main.activity_reset_password.*
import org.jetbrains.anko.alert
import org.jetbrains.anko.yesButton

class ResetPasswordActivity : AppCompatActivity(), ResetPasswordView {

    private lateinit var presenter: ResetPasswordPresenter

    override fun showLoading() {
        LoadingDialog.showLoading(this, R.string.reset_password)
    }

    override fun hideLoading() {
        LoadingDialog.hideLoading()
    }

    override fun showEmailSent() {
        alert(getString(R.string.email_sent)) {
            yesButton { finish() }
            isCancelable = false
        }.show()
    }

    override fun emailNotFound() {
        alert(getString(R.string.email_not_found)) {
            yesButton { it.dismiss() }
            isCancelable = false
        }.show()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reset_password)

        presenter = ResetPasswordPresenter(this)
    }

    fun resetPassword(view: View) {
        emailValidate(etEmail, getString(R.string.email_validate)) {
            presenter.resetPassword(etToString(etEmail))
        }
    }
}
