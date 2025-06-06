package minks2.com.github.kotlings.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import minks2.com.github.kotlings.R
import minks2.com.github.kotlings.data.EventoExtremo

class EventoExtremoAdapter(
    private val eventos: MutableList<EventoExtremo>,
    private val onDeleteClickListener: (EventoExtremo) -> Unit
) : RecyclerView.Adapter<EventoExtremoAdapter.EventoExtremoViewHolder>() {

    class EventoExtremoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textViewLocal: TextView = itemView.findViewById(R.id.textViewLocal)
        val textViewTipoEvento: TextView = itemView.findViewById(R.id.textViewTipoEvento)
        val textViewGrauImpacto: TextView = itemView.findViewById(R.id.textViewGrauImpacto)
        val textViewDataEvento: TextView = itemView.findViewById(R.id.textViewDataEvento)
        val textViewPessoasAfetadas: TextView = itemView.findViewById(R.id.textViewPessoasAfetadas)
        val buttonExcluirItem: Button = itemView.findViewById(R.id.buttonExcluirItem)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventoExtremoViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_evento_extremo, parent, false)
        return EventoExtremoViewHolder(view)
    }

    override fun onBindViewHolder(holder: EventoExtremoViewHolder, position: Int) {
        val evento = eventos[position]
        holder.textViewLocal.text = "Local: ${evento.nomeLocal}"
        holder.textViewTipoEvento.text = "Tipo: ${evento.tipoEvento}"
        holder.textViewGrauImpacto.text = "Impacto: ${evento.grauImpacto}"
        holder.textViewDataEvento.text = "Data: ${evento.dataEvento}"
        holder.textViewPessoasAfetadas.text = "Pessoas Afetadas: ${evento.pessoasAfetadas}"

        holder.buttonExcluirItem.setOnClickListener {
            // Cria um AlertDialog para confirmação
            AlertDialog.Builder(holder.itemView.context).setTitle("Confirmar Exclusão")
                .setMessage("Tem certeza de que deseja excluir o evento em '${evento.nomeLocal}'?")

                .setPositiveButton("Sim") { dialog, which ->
                    // Se o usuário clicar "Sim", então executa a exclusão
                    onDeleteClickListener(evento)
                }
                // O botão "Não" não faz nada, apenas fecha o diálogo
                .setNegativeButton("Não", null).show()
        }
    }

    override fun getItemCount(): Int = eventos.size

    fun removeEvento(evento: EventoExtremo) {
        val position = eventos.indexOf(evento)
        if (position != -1) {
            eventos.removeAt(position)
            notifyItemRemoved(position)
            notifyItemRangeChanged(position, eventos.size)
        }
    }
}