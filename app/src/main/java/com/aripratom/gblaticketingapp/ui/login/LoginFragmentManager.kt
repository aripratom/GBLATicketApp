package com.aripratom.gblaticketingapp.ui.login

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.aripratom.gblaticketingapp.R
import com.aripratom.gblaticketingapp.ui.signup.SignUpFragment
import com.google.android.material.tabs.TabLayout
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login_manager.*

class LoginFragmentManager: AppCompatActivity() {

    private val fm = supportFragmentManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_manager)

        fm.beginTransaction().replace(R.id.loginContent, LoginFragment()).commit()
        tabs.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{
            override fun onTabReselected(p0: TabLayout.Tab?) {

            }

            override fun onTabUnselected(p0: TabLayout.Tab?) {

            }

            override fun onTabSelected(p0: TabLayout.Tab?) {
                if (p0?.position == 0) fm.beginTransaction().replace(R.id.loginContent, LoginFragment()).commit()
                else fm.beginTransaction().replace(R.id.loginContent, SignUpFragment()).commit()
            }
        })
    }
}