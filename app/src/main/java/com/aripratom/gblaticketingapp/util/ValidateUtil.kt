package com.aripratom.gblaticketingapp.util

import android.util.Patterns
import android.widget.EditText
import android.widget.Spinner

object ValidateUtil {

    fun etToString(editText: EditText): String {
        return editText.text.toString()
    }

    fun etToInt(editText: EditText): Int {
        return if (editText.text.isEmpty()) 0
        else editText.text.toString().toInt()
    }


    fun setError(editText: EditText, msg: String) {
        editText.requestFocus()
        editText.error = msg
    }

    fun emailValidate(etEmail: EditText, msg: String, listener: () -> Unit) {
        if (Patterns.EMAIL_ADDRESS.matcher(etToString(etEmail)).matches()) listener()
        else setError(etEmail, msg)
    }

    fun passwordValidate(etPass: EditText, msg: String, listener: () -> Unit) {
        if (etToString(etPass).length >= 8) listener()
        else setError(etPass, msg)
    }

    fun etValidate(editText: EditText, msg: String, listener: () -> Unit) {
        if (etToString(editText).isNotEmpty()) listener()
        else setError(editText, msg)
    }

    fun validate(editText: EditText, msg: String) : Boolean {
        return if (etToString(editText).isNotEmpty()) true
        else {
            setError(editText, msg)
            false
        }
    }

    fun kodePembayaranValidate(etKP: EditText, kode: String, msg: String, listener: () -> Unit) {
        if (etToString(etKP) == kode) listener()
        else setError(etKP, msg)
    }

    fun emailValidateTiket(etEmail: EditText, email: String, msg: String, listener: () -> Unit) {
        if (etToString(etEmail) == email) listener()
        else setError(etEmail, msg)
    }


}