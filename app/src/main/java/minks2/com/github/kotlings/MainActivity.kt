package minks2.com.github.kotlings

import android.app.DatePickerDialog
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
import java.util.Calendar

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

        // Configura o campo de data para abrir o DatePickerDialog
        editTextDataEvento.isFocusable = false
        editTextDataEvento.isClickable = true
        editTextDataEvento.setOnClickListener {
            showDatePickerDialog()
        }

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

    private fun showDatePickerDialog() {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog =
            DatePickerDialog(this, { _, selectedYear, selectedMonth, selectedDay ->
                // O mês retorna de 0-11, então some 1
                val formattedDate =
                    String.format("%02d/%02d/%04d", selectedDay, selectedMonth + 1, selectedYear)
                editTextDataEvento.setText(formattedDate)
            }, year, month, day)

        datePickerDialog.show()
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
            Toast.makeText(
                this,
                "Número de pessoas afetadas deve ser maior que zero.",
                Toast.LENGTH_SHORT
            ).show()
            return
        }

        val novoEvento =
            EventoExtremo(nomeLocal, tipoEvento, grauImpacto, dataEvento, pessoasAfetadas)
        listaEventos.add(0, novoEvento) // Adiciona no início da lista
        eventoExtremoAdapter.notifyItemInserted(0)
        recyclerViewEventos.scrollToPosition(0) // Rola para o topo

        // Limpa os campos
        editTextNomeLocal.text.clear()
        editTextTipoEvento.text.clear()
        editTextGrauImpacto.text.clear()
        editTextDataEvento.text.clear()
        editTextPessoasAfetadas.text.clear()

        Toast.makeText(this, "Evento incluído com sucesso!", Toast.LENGTH_SHORT).show()
    }
}