package com.example.calculadoraimc.ui

import android.content.Context
import android.content.LocusId
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import android.widget.Toast
import com.example.calculadoraimc.R
import com.example.calculadoraimc.model.Usuario
import com.example.calculadoraimc.utils.convertStringToLocalDate
import java.time.LocalDate

class SignUpActivity : AppCompatActivity() {

    lateinit var editEmail: EditText
    lateinit var editSenha: EditText
    lateinit var editNome: EditText
    lateinit var editAltura: EditText
    lateinit var editDataNascimento: EditText
    lateinit var editProfissao: EditText
    lateinit var editSexo: EditText
    lateinit var radioF: EditText
    lateinit var radioM: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        editEmail = findViewById<EditText>(R.id.edit_text_email)
        editSenha = findViewById<EditText>(R.id.edit_text_senha)
        editNome = findViewById<EditText>(R.id.edit_text_nome)
        editAltura = findViewById<EditText>(R.id.edit_text_email)
        editDataNascimento = findViewById<EditText>(R.id.edit_text_nascimento)
        editProfissao = findViewById<EditText>(R.id.edit_text_profissao)
        editEmail = findViewById<EditText>(R.id.edit_text_email)

        supportActionBar!!.title = "Novo Usuário"

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        menuInflater.inflate(R.menu.menu_novo_usuario, menu)

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if (validarCampos()) {
            // Criar o objeto usuario
                val nascimento = convertStringToLocalDate(editDataNascimento.text.toString())
                val usuario = Usuario(
                1,
                editNome.text.toString(),
                editEmail.text.toString(),
                editSenha.text.toString(),
                0,
                editAltura.text.toString().toDouble(),
                LocalDate.of(
                    nascimento.year,
                    nascimento.monthValue,
                    nascimento.dayOfMonth
                ),
                editProfissao.text.toString(),
                    if (radioF.isChecked){
                        'F'
                    } else {
                        'M'
                    }

            )

            // Salvar o registro
            // Em um SharedPreferences

            // A instrução abaixo irá criar um
            // arquivo sharedPreferences, se não existir
            // Se existir, ele será aberto para edição
            val dados = getSharedPreferences(
                "usuario", Context.MODE_PRIVATE)

            // Vamos criar o objeto que permitirá a
            // edição dos dados do arquivo SharedPreferences
            val editor = dados.edit()
            editor.putInt("id", usuario.id)
            editor.putString("nome", usuario.nome)
            editor.putString("email", usuario.email)
            editor.putString("senha", usuario.senha)
            editor.putInt("peso", usuario.peso)
            editor.putFloat("altura", usuario.altura.toFloat())
            editor.putString("dataNascimento", usuario.dataNascimento.toString())
            editor.putString("profissao", usuario.profissao)
            editor.putString("sexo", usuario.sexo.toString())
            editor.apply()

        }

        Toast.makeText(this, "Usuário cadastrado!", Toast.LENGTH_SHORT).show()

        return true

    }

    fun validarCampos(): Boolean {
        var valido = true

        if (editEmail.text.isEmpty()) {
            editEmail.error = "O e-mail é obrigatório!"
            valido = false
        }

        if (editSenha.text.isEmpty()) {
            editSenha.error = "O e-mail é obrigatório!"
            valido = false
        }

        return true

    }
}