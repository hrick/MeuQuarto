package br.com.oxxynet.meuquarto.repository

import android.content.Context
import br.com.oxxynet.meuquarto.EstadosApp


class SharedPreferencesRepository(private val context: Context) {

    object SharedKeys {
        const val KEY_TELEFONE_MAE = "KEY_TELEFONE_MAE"
        const val KEY_ESTADO_APP = "KEY_ESTADO_APP"
        const val SHARED_PREFERENCE_DB = "SHARED_APP_MEU_QUARTO"
    }

    fun salvarTelefoneMae(numeroMae: String) {
        val sharedPref = context.getSharedPreferences(SharedKeys.SHARED_PREFERENCE_DB, Context.MODE_PRIVATE)
        val editor = sharedPref.edit()
        editor.putString(SharedKeys.KEY_TELEFONE_MAE, numeroMae)
        editor.apply()
    }


    fun obterTelefoneMae(): String {
        val sharedPref = context.getSharedPreferences(SharedKeys.SHARED_PREFERENCE_DB, Context.MODE_PRIVATE)
        val numeroMae = sharedPref.getString(SharedKeys.KEY_TELEFONE_MAE, "")
        return numeroMae
    }


    fun salvarEstadoApp(estadoApp: Int) {
        val sharedPref = context.getSharedPreferences(SharedKeys.SHARED_PREFERENCE_DB, Context.MODE_PRIVATE)
        val editor = sharedPref.edit()
        editor.putInt(SharedKeys.KEY_ESTADO_APP, estadoApp)
        editor.apply()
    }


    fun obterEstadoApp(): Int {
        val sharedPref = context.getSharedPreferences(SharedKeys.SHARED_PREFERENCE_DB, Context.MODE_PRIVATE)
        val estadoApp = sharedPref.getInt(SharedKeys.KEY_ESTADO_APP, EstadosApp.ALARME_DESATIVADO.estado)
        return estadoApp
    }

}