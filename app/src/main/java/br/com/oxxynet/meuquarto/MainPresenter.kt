package br.com.oxxynet.meuquarto

import android.content.Context
import br.com.oxxynet.meuquarto.api.ApiUtils
import br.com.oxxynet.meuquarto.repository.SharedPreferencesRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainPresenter(val view: MainView) {

    val apiUtils: ApiUtils = ApiUtils()

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
        view.showLoading()
//        val request = RequestConsultarConvidado(contents)
        apiUtils.getConvidadoAPIService().obterConvidado(request).enqueue(object : Callback<Convidado> {
            override fun onFailure(call: Call<Convidado>?, t: Throwable) {
                view.hideLoading()
                view.showError(t)
            }

            override fun onResponse(call: Call<Convidado>?, response: Response<Convidado>?) {
                view.hideLoading()
//                if (response?.isSuccessful == true)
//                    if (response.body()?.mensagem.isNullOrBlank()) {
//                        view.preencherDadosRepresentante(response.body())
//                        this@MainPresenter.id = contents
//                    } else {
//                        view.convidadoEntradaErro(response.body()?.mensagem)
//                    }
//                else
//                    view.showError(ErroServidorException("Erro ao se conectar no servidor, por favor verifique sua conexão e tente novamente."))
            }
        })
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