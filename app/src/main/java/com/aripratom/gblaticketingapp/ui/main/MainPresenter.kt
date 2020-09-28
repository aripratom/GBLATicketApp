package com.aripratom.gblaticketingapp.ui.main

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.aripratom.gblaticketingapp.R
import com.aripratom.gblaticketingapp.ui.notifikasi.NotifikasiFragment
import com.aripratom.gblaticketingapp.ui.pertandingan.PertandinganFragment
import com.aripratom.gblaticketingapp.ui.profil.ProfilFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.iid.FirebaseInstanceId

class MainPresenter (private val view: MainView) {

    private val uid = FirebaseAuth.getInstance().currentUser?.uid
    private val db = FirebaseDatabase.getInstance().reference.child("user")

    private val home = PertandinganFragment()
    private val notif = NotifikasiFragment()
    private val profil = ProfilFragment()

    fun checkLogin() {
        try {
            if (FirebaseAuth.getInstance().currentUser == null) view.toLogin()
            else {
                FirebaseInstanceId.getInstance().instanceId.addOnSuccessListener { p0 ->
                    val token = p0?.token.toString()
                    db.child(uid!!).child("tokenId").setValue(token)
                }
            }
        } catch (ex: Exception) {
            view.toLogin()
            ex.printStackTrace()
        }
    }

    fun changeView(fm: FragmentManager, fragment: Fragment) {
        val transaction = fm.beginTransaction()

        val current = fm.primaryNavigationFragment
        if (current != null) transaction.hide(current)

        if (!fragment.isAdded) transaction.add(R.id.content, fragment, fragment::class.java.simpleName)
        else {
            transaction.show(fragment)
        }

        transaction.apply {
            setPrimaryNavigationFragment(fragment)
            setReorderingAllowed(true)
        }.commitNowAllowingStateLoss()
    }

    fun selectedView(fm: FragmentManager)
            = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_home -> changeView(fm, home)
            R.id.navigation_notification -> changeView(fm, notif)
            R.id.navigation_profile -> changeView(fm, profil)
        }
        true
    }
}