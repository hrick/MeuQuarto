package br.com.oxxynet.meuquarto

import android.content.Context
import br.com.oxxynet.meuquarto.repository.SharedPreferencesRepository

class MainPresenter(val view: MainView) {

    lateinit var sharedPreferences: SharedPreferencesRepository

    fun ativarAlarme() {

    }

    fun ligarParaMae(context: Context) {
        sharedPreferences = SharedPreferencesRepository(context)
        val telefone = sharedPreferences.obterTelefoneMae()
        view.fazerLigacao(telefone)
    }

    fun mostrarCaixaDeTelefone(context: Context) {
        sharedPreferences = SharedPreferencesRepository(context)
        val telefone = sharedPreferences.obterTelefoneMae()
        view.showNumeroMae(telefone)
    }

    fun desativarAlarme() {

    }

    fun recarregarLayout(context: Context) {
        sharedPreferences = SharedPreferencesRepository(context)
        val estadoApp = sharedPreferences.obterEstadoApp()
        when (estadoApp) {
            EstadosApp.ALARME_DESATIVADO.estado -> {
                view.mostrarLayoutHome()
            }
            EstadosApp.INVADIDO_QUARTO.estado -> {
                view.mostrarLayoutTocandoAlarme()
            }
            EstadosApp.QUARTO_SEGURO.estado -> {
                view.mostrarLayoutAlarmeAtivado()
            }
        }
    }

    fun salvarNumeroRealizarLigacao(context: Context, telefone: String) {
        sharedPreferences = SharedPreferencesRepository(context)
        sharedPreferences.salvarTelefoneMae(telefone)
        ligarParaMae(context)
    }
}