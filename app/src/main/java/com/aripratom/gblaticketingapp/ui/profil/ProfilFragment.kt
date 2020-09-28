package com.aripratom.gblaticketingapp.ui.profil

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.aripratom.gblaticketingapp.R
import com.aripratom.gblaticketingapp.data.TiketPengguna
import com.aripratom.gblaticketingapp.data.User
import com.aripratom.gblaticketingapp.ui.login.LoginFragmentManager
import com.aripratom.gblaticketingapp.ui.profiledit.EditProfilActivity
import com.aripratom.gblaticketingapp.ui.tiketku.TiketkuAdapter
import com.aripratom.gblaticketingapp.util.gone
import com.aripratom.gblaticketingapp.util.visible
import kotlinx.android.synthetic.main.fragment_profil.*
import org.jetbrains.anko.noButton
import org.jetbrains.anko.sdk27.coroutines.onClick
import org.jetbrains.anko.support.v4.alert
import org.jetbrains.anko.support.v4.onRefresh
import org.jetbrains.anko.support.v4.startActivity
import org.jetbrains.anko.yesButton


class ProfilFragment : Fragment(), ProfilView {

    private lateinit var presenter: ProfilPresenter
    private var user: User? = null
    private lateinit var adapter: TiketkuAdapter
    private val tiket: MutableList<TiketPengguna?> = mutableListOf()



    override fun showData(user: User?) {
        this.user = user

        //Glide.with(this).load(user?.photo).placeholder(R.color.colorMuted).into(imgAva)
        tvName.text = user?.name
        tvEmail.text = user?.email
        tvCity.text = user?.city

    }

    override fun showTiket(ticket: List<TiketPengguna?>) {
        this.tiket.clear()
        this.tiket.addAll(ticket)
        adapter.notifyDataSetChanged()
    }

    override fun showLoading() {
        srlTiketku.isRefreshing = true
    }

    override fun hideLoading() {
        srlTiketku.isRefreshing = false
    }

    override fun emptyPemesasnan() {
        rv_tiketku_profile.gone()
        tvEmptyProfil.visible()
    }

    override fun notEmptyPemesanan() {
        rv_tiketku_profile.visible()
        tvEmptyProfil.gone()
    }


    override fun doLogout() {
        alert {
            title = "Keluar dari akun ini?"
            yesButton {
                startActivity<LoginFragmentManager>()
                activity?.finish()
            }
            noButton { it.dismiss() }
            isCancelable = false
        }.show()
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {val view = inflater.inflate(R.layout.fragment_profil, container, false)
        setHasOptionsMenu(true)
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        //(activity as AppCompatActivity).setSupportActionBar(toolbarProfile)

        presenter = ProfilPresenter(this)

        presenter.getUserData()
        presenter.getTiket()
        setupRecyclerView()

        srlTiketku.onRefresh { presenter.getTiket() }

        btnEditProfile.onClick { startActivity<EditProfilActivity>("user" to user) }
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        super.onCreateOptionsMenu(menu, inflater)
        menu?.clear()
        inflater?.inflate(R.menu.menu_profil, menu)
    }

    @SuppressLint("InflateParams")
    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.nav_about -> {
                val mDialogView = LayoutInflater.from(activity).inflate(R.layout.layout_about, null)
                AlertDialog.Builder(context!!)
                    .setView(mDialogView)
                    .show()
            }
            R.id.nav_logout -> presenter.logout()

        }
        return super.onOptionsItemSelected(item)
    }

    private fun setupRecyclerView() {
        adapter = TiketkuAdapter(tiket)
        rv_tiketku_profile.setHasFixedSize(true)
        rv_tiketku_profile.layoutManager = LinearLayoutManager(context)
        rv_tiketku_profile.adapter = adapter


    }

    override fun onResume() {
        super.onResume()
        presenter.getUserData()
        presenter.getTiket()
    }
}
