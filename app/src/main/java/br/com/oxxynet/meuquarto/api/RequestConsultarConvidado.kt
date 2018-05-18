package br.com.oxxynet.meuquarto.api

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class RequestConsultarConvidado (@SerializedName("sIdInscricao")@Expose val id: String)