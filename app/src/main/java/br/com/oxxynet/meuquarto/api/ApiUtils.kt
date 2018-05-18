package br.com.oxxynet.meuquarto.api

import br.com.oxxynet.anpeviapp.api.RetrofitClient

class ApiUtils  {


    val BASE_URL = "http://anpevi.org.br"

    fun getConvidadoAPIService(): AlarmeApi {
        return RetrofitClient.getClient(BASE_URL).create(AlarmeApi::class.java)
    }
}