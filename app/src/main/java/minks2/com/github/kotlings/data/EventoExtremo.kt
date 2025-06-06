package minks2.com.github.kotlings.data

data class EventoExtremo(
    val nomeLocal: String,
    val tipoEvento: String,
    val grauImpacto: String,
    val dataEvento: String,
    val pessoasAfetadas: Int
)