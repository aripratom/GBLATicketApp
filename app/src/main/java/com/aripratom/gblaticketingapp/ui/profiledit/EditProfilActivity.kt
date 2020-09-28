package com.aripratom.gblaticketingapp.ui.profiledit

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import com.aripratom.gblaticketingapp.R
import com.aripratom.gblaticketingapp.data.User
import com.aripratom.gblaticketingapp.util.CityUtil
import com.aripratom.gblaticketingapp.util.LoadingDialog
import com.aripratom.gblaticketingapp.util.ValidateUtil.emailValidate
import com.aripratom.gblaticketingapp.util.ValidateUtil.etToString
import com.aripratom.gblaticketingapp.util.ValidateUtil.passwordValidate
import com.aripratom.gblaticketingapp.util.ValidateUtil.setError
import com.aripratom.gblaticketingapp.util.ValidateUtil.validate
import kotlinx.android.synthetic.main.activity_edit_profil.*
import org.jetbrains.anko.design.snackbar
import org.jetbrains.anko.toast

class EditProfilActivity : AppCompatActivity(), EditProfilView {

    private lateinit var presenter: EditProfilPresenter
    private lateinit var email: String

    override fun showData(user: User?) {
        //Glide.with(this).load(user?.photo).placeholder(R.color.colorPrimary).into(imgUserPhoto)

        etNameUpdate.setText(user?.name)
        etCityUpdate.setText(user?.city)
        etEmailUpdate.setText(user?.email)
        etPhoneUpdate.setText(user?.phone)

        email = user?.email!!

        etCityUpdate.setAdapter(ArrayAdapter(this, R.layout.spinner_item, CityUtil.city))
    }

    override fun showLoading(title: String) {
        LoadingDialog.showLoading(this, title)
    }

    override fun hideLoading() {
        LoadingDialog.hideLoading()
    }

    override fun showUpdated(title: String) {
        toast(title)
        finish()
    }

    override fun showFail(title: String) {
        scrollView.snackbar(title)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_profil)

//        setSupportActionBar(toolbarEditProfile)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_close)

        val user: User = intent.getParcelableExtra("user")

        presenter = EditProfilPresenter(this)
        presenter.getData(user)

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_done, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item?.itemId == android.R.id.home) finish()
        else if (item?.itemId == R.id.nav_done) {

            if (validate(etEmailUpdate, getString(R.string.name_validate)) and
                validate(etCityUpdate, getString(R.string.city_validate))) {

                emailValidate(etEmailUpdate, getString(R.string.email_validate)) {
                    email = etToString(etEmailUpdate)
                }

                if (etNewPassword.text.isNotEmpty()) {
                    passwordValidate(etNewPassword, getString(R.string.password_validate)) {
                        passwordValidate(etRetypePassword, getString(R.string.password_validate)) {
                            if (etToString(etNewPassword) == etToString(etRetypePassword))
                                update(etToString(etNewPassword))
                            else
                                setError(etRetypePassword, "Password tidak cocok, Coba Lagi")
                        }
                    }
                } else update(null)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun update(pass: String?) {
        presenter.updateProfile(
            etToString(etNameUpdate),
            etToString(etCityUpdate),
            email,
            etToString(etPhoneUpdate),
            pass
        )
    }
}
