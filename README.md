# Mobility+ — Acessibilidade Inclusiva: Mobilidade sem Barreiras

## Sobre o projeto

O **Mobility+** é um aplicativo de acessibilidade, com o objetivo de contribuir para uma mobilidade urbana mais inclusiva por meio da tecnologia. A aplicação permite cadastrar e consultar estabelecimentos, visualizar informações sobre seus recursos de acessibilidade e registrar preferências relacionadas às necessidades dos usuários.

A proposta é facilitar o acesso a informações que auxiliem pessoas com deficiência ou mobilidade reduzida a identificar características dos locais antes de visitá-los.

## Problema identificado

A acessibilidade urbana ainda apresenta diversas barreiras para pessoas com deficiência permanente ou mobilidade reduzida. Muitas vezes, a falta de informações sobre a estrutura de estabelecimentos e espaços urbanos dificulta o planejamento de deslocamentos e a escolha de locais adequados.

Nem sempre é possível saber antecipadamente se um local possui rampa de acesso, piso tátil ou recursos de sinalização sonora e visual. Além disso, a acessibilidade não deve ser tratada como uma característica única. Um estabelecimento pode possuir rampa, mas não oferecer piso tátil, por exemplo. Por isso, é importante apresentar os recursos individualmente, permitindo que cada pessoa avalie quais características atendem às suas necessidades.

## Público beneficiado

O Mobility+ é direcionado principalmente a:

- Pessoas com deficiência física;
- Pessoas com deficiência visual;
- Pessoas com deficiência auditiva;
- Pessoas com deficiência intelectual;
- Pessoas com mobilidade reduzida;
- Usuários de cadeira de rodas, bengalas ou muletas;
- Pessoas interessadas em consultar informações sobre acessibilidade.

## Solução proposta

O Mobility+ propõe uma aplicação mobile para cadastrar, consultar e visualizar informações sobre estabelecimentos e suas características de acessibilidade.

Por meio do aplicativo, o usuário pode consultar os locais cadastrados, pesquisar estabelecimentos, utilizar filtros e visualizar detalhes sobre os recursos disponíveis.

A aplicação também apresenta um perfil de acessibilidade, no qual o usuário pode selecionar características relacionadas às suas necessidades.

Dessa forma, a solução busca facilitar o acesso à informação e contribuir para escolhas mais conscientes e uma maior autonomia na mobilidade urbana.

## Objetivos de Desenvolvimento Sustentável (ODS)

O projeto está relacionado aos Objetivos de Desenvolvimento Sustentável estabelecidos pela Organização das Nações Unidas (ONU), especialmente:

### ODS 10 — Redução das Desigualdades

O ODS 10 busca reduzir as desigualdades e promover a inclusão social. O Mobility+ relaciona-se a esse objetivo ao facilitar o acesso a informações sobre acessibilidade, contribuindo para que pessoas com diferentes necessidades possam tomar decisões mais conscientes sobre os espaços que desejam frequentar.

### ODS 11 — Cidades e Comunidades Sustentáveis

O ODS 11 busca tornar as cidades e os assentamentos humanos mais inclusivos, seguros, resilientes e sustentáveis. O projeto contribui para essa proposta ao incentivar a identificação e a divulgação de características de acessibilidade nos estabelecimentos urbanos.

## Funcionalidades

**Tela inicial:** apresenta o nome do aplicativo, sua proposta e os principais caminhos de navegação.

**Cadastro de estabelecimentos:** permite registrar nome, endereço, descrição e características de acessibilidade dos locais.

**Consulta de locais:** apresenta os estabelecimentos cadastrados para consulta.

**Pesquisa e filtros:** permite pesquisar estabelecimentos e filtrar os resultados por características de acessibilidade e favoritos.

**Detalhes do estabelecimento:** apresenta informações do local selecionado e seus recursos de acessibilidade.

**Ações sobre os registros:** disponibiliza opções de edição, exclusão e marcação de favoritos.

**Perfil de acessibilidade:** permite selecionar características relacionadas às necessidades do usuário, como tipos de deficiência, uso de dispositivos de apoio e mobilidade reduzida.

## Recursos de acessibilidade considerados

| Recurso | Finalidade |
|---|---|
| Rampa de acesso | Facilitar o acesso físico aos estabelecimentos |
| Piso tátil | Auxiliar na orientação e mobilidade |
| Sinalização sonora | Oferecer informações por áudio |
| Sinalização visual | Facilitar a comunicação e orientação visual |

A proposta é apresentar cada recurso individualmente, reconhecendo que as necessidades de acessibilidade variam de pessoa para pessoa.

## Tecnologias utilizadas

- **Kotlin:** linguagem de programação utilizada no desenvolvimento;
- **Android Studio:** ambiente de desenvolvimento;
- **Jetpack Compose:** construção das interfaces;
- **Material Design 3:** componentes e padrões visuais;
- **Navigation Compose:** navegação entre as telas;
- **Room Database:** persistência local dos registros;
- **ViewModel:** gerenciamento do estado da aplicação;
- **Repository:** organização do acesso aos dados;
- **Kotlin Coroutines:** execução de operações assíncronas;
- **Kotlin Flow:** observação e atualização dos dados.

## Organização do projeto

O projeto foi organizado em camadas, buscando separar as responsabilidades da interface, dos dados e da lógica de apresentação.

![organizacao-projeto](docs/screenshots/organizacao-projeto.png)

[**Abrir imagem da organização do projeto**](docs/screenshots/organizacao-projeto.png)

## Persistência de dados

O aplicativo utiliza Room Database para armazenar localmente os estabelecimentos cadastrados e as preferências de acessibilidade.

A organização da persistência utiliza:

- **Entity:** representa os dados dos estabelecimentos e das preferências;
- **DAO:** define as operações de acesso ao banco de dados;
- **Database:** configura o banco de dados Room;
- **Repository:** centraliza o acesso aos dados;
- **ViewModel:** disponibiliza os dados para a interface.

A utilização do Room permite armazenar os registros no dispositivo, sem exigir um backend próprio ou sincronização em nuvem.

## Identidade visual

A identidade visual do Mobility+ segue uma proposta moderna, minimalista e intuitiva.

A interface utiliza tons de azul, branco e cinza claro, buscando transmitir confiança, clareza, tecnologia e acolhimento.

A organização visual prioriza a legibilidade, a navegação simples e a apresentação objetiva das informações.

---

## Telas do aplicativo

As capturas a seguir apresentam as principais telas do Mobility+, demonstrando o fluxo de utilização, desde a apresentação inicial até o cadastro e a consulta dos estabelecimentos e as preferências de acessibilidade.

### Tela inicial

A tela inicial apresenta o nome e a proposta do Mobility+, além de disponibilizar acesso às principais funcionalidades do aplicativo.

![Tela inicial do Mobility+](docs/screenshots/tela-inicial.png)

[**Abrir imagem da tela inicial**](docs/screenshots/tela-inicial