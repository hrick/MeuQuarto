package br.com.oxxynet.meuquarto

interface MainView {
    fun mostrarLayoutHome()
    fun mostrarLayoutAlarmeAtivado()
    fun mostrarLayoutTocandoAlarme()
    fun fazerLigacao(telefone: String)
    fun showNumeroMae(numeroCadastrado: String)
    fun showLoading()
    fun showError(t: Throwable)
    fun hideLoading()
    fun setTextLoading(mensagem: String)
}