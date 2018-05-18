package br.com.oxxynet.meuquarto.api

import br.com.oxxynet.anpeviapp.models.Convidado
import br.com.oxxynet.meuquarto.api.RequestConsultarConvidado
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface AlarmeApi {
    @POST("/InscricaoWorkshop/ValidarQrCode")
    fun confirmarConvidado(@Body requestConsultarConvidado: RequestConsultarConvidado): Call<Convidado>

    @POST("/InscricaoWorkshop/RetornarInscricaoQrCode")
    fun obterConvidado(@Body requestConsultarConvidado: RequestConsultarConvidado): Call<Convidado>

    @POST("/InscricaoWorkshop/RetornarListaQrCodeValidados")
    fun obterConvidadosValidados(): Call<List<Convidado>?>

}