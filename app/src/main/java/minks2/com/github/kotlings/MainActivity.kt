
package minks2.com.github.kotlings

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import minks2.com.github.kotlings.adapter.EventoExtremoAdapter
import minks2.com.github.kotlings.data.EventoExtremo

import android.text.Editable
import android.text.TextWatcher

class MainActivity : AppCompatActivity() {

    private lateinit var editTextNomeLocal: EditText
    private lateinit var editTextTipoEvento: EditText
    private lateinit var editTextGrauImpacto: EditText
    private lateinit var editTextDataEvento: EditText
    private lateinit var editTextPessoasAfetadas: EditText
    private lateinit var buttonIncluir: Button
    private lateinit var recyclerViewEventos: RecyclerView
    private lateinit var buttonIdentificacao: Button

    private val listaEventos = mutableListOf<EventoExtremo>()
    private lateinit var eventoExtremoAdapter: EventoExtremoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Inicialização dos componentes da UI
        editTextNomeLocal = findViewById(R.id.editTextNomeLocal)
        editTextTipoEvento = findViewById(R.id.editTextTipoEvento)
        editTextGrauImpacto = findViewById(R.id.editTextGrauImpacto)
        editTextDataEvento = findViewById(R.id.editTextDataEvento)
        editTextPessoasAfetadas = findViewById(R.id.editTextPessoasAfetadas)
        buttonIncluir = findViewById(R.id.buttonIncluir)
        recyclerViewEventos = findViewById(R.id.recyclerViewEventos)
        buttonIdentificacao = findViewById(R.id.buttonIdentificacao)

        editTextDataEvento.addTextChangedListener(object : TextWatcher {
            private var current = ""
            private val ddmmyyyy = "DDMMYYYY"
            private val divider = '/'

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (s.toString() != current) {
                    var clean = s.toString().replace("[^\\d.]".toRegex(), "")

                    var formatted = ""
                    if (clean.length > 0) {
                        if (clean.length <= 2) {
                            formatted = clean
                        } else if (clean.length <= 4) {
                            formatted = clean.substring(0, 2) + divider + clean.substring(2)
                        } else if (clean.length <= 8) { // DDMMYYYY
                            formatted = clean.substring(0, 2) + divider + clean.substring(2, 4) + divider + clean.substring(4)
                        } else {
                            // Se digitou mais de 8 dígitos, limita ao máximo de 8
                            formatted = clean.substring(0, 2) + divider + clean.substring(2, 4) + divider + clean.substring(4, 8)
                        }
                    }

                    // Garante que o comprimento total seja no máximo 10 (DD/MM/YYYY)
                    if (formatted.length > 10) {
                        formatted = formatted.substring(0, 10)
                    }

                    current = formatted

                    editTextDataEvento.setText(current)
                    editTextDataEvento.setSelection(current.length)
                }
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun afterTextChanged(s: Editable?) {
            }
        })


        // Configura o RecyclerView
        eventoExtremoAdapter = EventoExtremoAdapter(listaEventos) { evento ->
            eventoExtremoAdapter.removeEvento(evento)
            Toast.makeText(this, "Evento excluído: ${evento.nomeLocal}", Toast.LENGTH_SHORT).show()
        }
        recyclerViewEventos.adapter = eventoExtremoAdapter
        recyclerViewEventos.layoutManager = LinearLayoutManager(this)

        // Listener para o botão Incluir
        buttonIncluir.setOnClickListener {
            incluirEvento()
        }

        // Listener para o botão de identificação
        buttonIdentificacao.setOnClickListener {
            val intent = Intent(this, IdentificacaoActivity::class.java)
            startActivity(intent)
        }
    }

    private fun incluirEvento() {
        val nomeLocal = editTextNomeLocal.text.toString().trim()
        val tipoEvento = editTextTipoEvento.text.toString().trim()
        val grauImpacto = editTextGrauImpacto.text.toString().trim()
        val dataEvento = editTextDataEvento.text.toString().trim()
        val pessoasAfetadasStr = editTextPessoasAfetadas.text.toString().trim()

        if (nomeLocal.isEmpty() || tipoEvento.isEmpty() || grauImpacto.isEmpty() || dataEvento.isEmpty() || pessoasAfetadasStr.isEmpty()) {
            Toast.makeText(this, "Por favor, preencha todos os campos.", Toast.LENGTH_SHORT).show()
            return
        }

        val pessoasAfetadas = pessoasAfetadasStr.toIntOrNull()
        if (pessoasAfetadas == null || pessoasAfetadas <= 0) {
            Toast.makeText(this, "Número de pessoas afetadas deve ser maior que zero.", Toast.LENGTH_SHORT).show()
            return
        }

        val novoEvento = EventoExtremo(nomeLocal, tipoEvento, grauImpacto, dataEvento, pessoasAfetadas)
        listaEventos.add(novoEvento)
        eventoExtremoAdapter.notifyItemInserted(listaEventos.size - 1)

        editTextNomeLocal.text.clear()
        editTextTipoEvento.text.clear()
        editTextGrauImpacto.text.clear()
        editTextDataEvento.text.clear()
        editTextPessoasAfetadas.text.clear()

        Toast.makeText(this, "Evento incluído com sucesso!", Toast.LENGTH_SHORT).show()
    }
}