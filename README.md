# Monitoramento Climático Extremo

Este aplicativo Android, desenvolvido em Kotlin, permite o registro e monitoramento de eventos climáticos extremos. A interface foi construída seguindo as diretrizes do Material 3, oferecendo uma experiência de usuário moderna e intuitiva.

Projeto desenvolvido para a Global Solution 1 (GS1) da disciplina de Android + Kotlin.

## Funcionalidades Principais

* **Cadastro de Eventos:** Formulário completo para inserir detalhes do evento, como local, tipo, impacto, data e número de pessoas afetadas.
* **Listagem Dinâmica:** Os eventos cadastrados são exibidos em tempo real em uma lista com design moderno, utilizando `RecyclerView` e `CardView`.
* **Validação de Dados:** O sistema impede o cadastro de eventos com campos vazios e com número de pessoas afetadas inválido (menor ou igual a zero).
* **Exclusão de Itens:** Cada evento na lista possui uma opção para ser removido individualmente da lista.
* **Tela de Identificação:** Uma tela dedicada exibe as informações dos desenvolvedores do projeto.

## Funcionalidades Bônus e Melhorias

Para aprimorar a usabilidade e a qualidade técnica do projeto, foram implementadas as seguintes funcionalidades extras:

* ✅ **UI/UX com Material 3:** O projeto foi totalmente estilizado com componentes do Material 3, incluindo `TextInputLayout` para os campos do formulário e `CardView` para a lista, resultando em um visual limpo e profissional.
* ✅ **Seletor de Data (DatePicker):** Para melhorar a usabilidade e evitar erros de digitação, a inserção da data é feita através de um `DatePickerDialog` nativo.
* ✅ **Confirmação de Exclusão:** Para prevenir a remoção acidental de dados, um diálogo de confirmação é exibido antes de excluir um evento.

## Evidências de Execução (Screenshots)

### Tela Principal e Lista de Eventos
![Tela Principal com lista de eventos](images/WhatsApp%20Image%202025-06-06%20at%2016.21.09.jpeg)

### Tela de Cadastro Limpa
![Tela de Cadastro](images/WhatsApp%20Image%202025-06-06%20at%2016.21.09%20(2).jpeg)

### Funcionalidade Bônus: Seletor de Data
![Seletor de Data](images/WhatsApp%20Image%202025-06-06%20at%2016.21.09%20(3).jpeg)

### Funcionalidade Bônus: Confirmação de Exclusão
![Confirmação de Exclusão](images/WhatsApp%20Image%202025-06-06%20at%2016.21.08.jpeg)

### Tela de Identificação
![Tela de Identificação](images/WhatsApp%20Image%202025-06-06%20at%2016.21.09%20(1).jpeg)


## Desenvolvido por

* **Caio Sales Dias** - RM: 552286
* **Guilherme Bussolan** - RM: 552455

---