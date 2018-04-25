package br.com.oxxynet.meuquarto

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.text.InputType
import android.view.View
import android.widget.Toast
import com.afollestad.materialdialogs.MaterialDialog
import kotlinx.android.synthetic.main.frame_alarme_ativado.*
import kotlinx.android.synthetic.main.frame_home.*
import kotlinx.android.synthetic.main.frame_invadindo_quarto.*

class MainActivity : AppCompatActivity(), MainView {

    object Constantes {
        const val PERMISSIONS_REQUEST_CALL = 1
    }

    lateinit var presenter: MainPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        presenter = MainPresenter(this)

        btAtivarAlarme.setOnClickListener {
            presenter.ativarAlarme()
        }
        btLigarParaMae.setOnClickListener {
            presenter.mostrarCaixaDeTelefone(this)
        }
        btDesativarAlarme1.setOnClickListener {
            presenter.desativarAlarme()
        }
        btDesativarAlarme2.setOnClickListener {
            presenter.desativarAlarme()
        }
    }

    override fun onResume() {
        super.onResume()
        presenter.recarregarLayout(this)
        mostrarLayoutTocandoAlarme()
    }

    override fun mostrarLayoutHome() {
        llHome.visibility = View.VISIBLE
        llAlarmeAtivado.visibility = View.GONE
        llTocandoAlarme.visibility = View.GONE
    }


    override fun mostrarLayoutAlarmeAtivado() {
        llHome.visibility = View.GONE
        llAlarmeAtivado.visibility = View.VISIBLE
        llTocandoAlarme.visibility = View.GONE
    }

    override fun mostrarLayoutTocandoAlarme() {
        llHome.visibility = View.GONE
        llAlarmeAtivado.visibility = View.GONE
        llTocandoAlarme.visibility = View.VISIBLE
    }

    override fun showNumeroMae(numeroCadastrado: String){
        MaterialDialog.Builder(this)
                .title(getString(R.string.lbl_faazer_ligacao))
                .inputRange(10, 20)
                .content(getString(R.string.msg_inserir_numero_cadastrado))
                .inputType(InputType.TYPE_TEXT_FLAG_MULTI_LINE)
                .positiveText(getString(R.string.lbl_ligar))
                .negativeText(getString(R.string.lbl_cancelar))
                .input(getString(R.string.hint_telefone), numeroCadastrado)
                { dialog, input ->
                    dialog.dismiss()
                    presenter.salvarNumeroRealizarLigacao(this, input.toString())
                }.show()
    }

    override fun fazerLigacao(telefone: String) {
        if (ContextCompat.checkSelfPermission(this,
                        Manifest.permission.CALL_PHONE)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.CALL_PHONE, Manifest.permission.READ_CONTACTS), Constantes.PERMISSIONS_REQUEST_CALL)

        } else {
            val intent = Intent(Intent.ACTION_CALL)
            intent.data = Uri.parse("tel: $telefone")
            startActivity(intent)
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int,
                                            permissions: Array<String>, grantResults: IntArray) {
        when (requestCode) {
            Constantes.PERMISSIONS_REQUEST_CALL -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    presenter.mostrarCaixaDeTelefone(this)
                } else {
                    showDenied()
                }
                return
            }
        }
    }

    private fun showDenied() {
        Toast.makeText(this, getString(R.string.msg_precisa_permissoes), Toast.LENGTH_SHORT).show()
        finish()
    }
}
