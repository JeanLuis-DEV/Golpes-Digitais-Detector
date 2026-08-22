# Detector de Golpes Digitais

Aplicação desktop em Java para analisar mensagens e indicar sinais comuns de golpes digitais. O projeto combina normalização de texto, regras de negócio e um catálogo de padrões suspeitos para apresentar um nível de risco, a pontuação calculada e os motivos encontrados.

> O resultado é um alerta de apoio à decisão. Nenhuma classificação substitui a verificação da fonte, do canal oficial da instituição e dos dados envolvidos.

## Funcionalidades

- Interface gráfica desktop para colar, analisar e limpar mensagens.
- Normalização de maiúsculas, minúsculas e acentos, com tolerância a erros ortográficos frequentes em solicitações suspeitas.
- Detecção de pedidos de dados sensíveis em português, inglês, espanhol e francês, inclusive quando os idiomas são misturados na mesma mensagem.
- Reconhecimento de diminutivos usados para disfarçar termos sensíveis, como `cartãozinho`.
- Identificação estrutural de pedidos de fotos do rosto, selfies e dados de biometria facial.
- Classificação em três níveis: `POSSIVELMENTE LEGÍTIMO`, `SUSPEITO` e `POSSIVELMENTE GOLPE`.
- Pontuação transparente e lista dos motivos que levaram ao resultado.
- Identificação de sinais como:
  - urgência, pressão temporal e pedidos de confirmação imediata;
  - links, domínios e arquivos potencialmente perigosos;
  - solicitação de senhas, códigos, documentos, dados bancários e imagens de cartão;
  - cartão bloqueado, uso não reconhecido, clonagem e tentativas de fraude;
  - pedidos de PIX, transferências, taxas antecipadas e pagamentos para terceiros;
  - falsas centrais de atendimento, bloqueios de conta e verificação de identidade;
  - acesso remoto, investimentos com retorno garantido e o golpe do PIX por engano.
- Cenários automatizados de regressão para validar comportamentos conhecidos.

## Como a análise funciona

1. A mensagem é validada e normalizada.
2. O detector procura palavras, frases, links, domínios, valores monetários e extensões de arquivo.
3. A análise estrutural combina ações solicitadas, dados sensíveis, pagamentos, pretextos, ameaças e contexto técnico, incluindo flexões verbais.
4. Relatos sobre uma situação suspeita são diferenciados de ordens diretas para reduzir falsos positivos e evitar a soma duplicada do mesmo sinal.
5. Contextos comerciais explícitos, como emissão de nota fiscal, evitam que termos genéricos como `valor` e `cadastro` sejam tratados isoladamente como fraude.
6. Regras simples e compostas atribuem pontos para cada evidência encontrada.
7. A soma é convertida em uma classificação de risco:

| Pontuação | Classificação |
| --- | --- |
| 0 a 3 | POSSIVELMENTE LEGÍTIMO |
| 4 a 6 | SUSPEITO |
| 7 ou mais | POSSIVELMENTE GOLPE |

Exemplo de mensagem identificada como alto risco:

```text
Seu cartão foi bloqueado, envie a foto frente e verso do cartão.
```

Ela reúne um alerta sobre cartão e a solicitação de dados sensíveis, alcançando a pontuação necessária para uma recomendação de cautela máxima.

## Tecnologias e recursos

- **Java 17+**
- **Swing/AWT** para a interface gráfica desktop
- **Java Collections** (`List`, `ArrayList`) para catálogo de termos e motivos
- **Regex** (`Pattern`) para valores monetários, e-mails, extensões e busca de termos
- **Normalizer** para remoção de acentos
- **Records** para representar regras e resultados de forma imutável
- Apenas bibliotecas da **JDK**: não há dependências externas

## Estrutura do projeto

```text
src/
├── Main.java                         # Inicialização da interface gráfica
├── CenarioTeste.java                 # Cenários de validação sem dependências externas
└── Modelos/
    ├── TelaDetectorGolpes.java       # Interface Swing
    ├── DetectorGolpes.java           # Orquestra a análise e a pontuação
    ├── AnalisadorMensagem.java       # Normalização e identificação de padrões
    ├── CatalogoGolpes.java           # Termos e regras de detecção
    ├── RegraGolpe.java               # Modelo imutável de uma regra
    ├── ResultadoAnalise.java         # Resultado da análise
    └── ConsoleApp.java               # Alternativa de interação pelo terminal
```

## Como executar

### Pré-requisito

Instale o JDK 17 ou superior e confirme a instalação:

```powershell
java -version
javac -version
```

### Compilar e abrir a interface

No PowerShell, a partir da raiz do projeto:

```powershell
New-Item -ItemType Directory -Force -Path out | Out-Null
javac --release 17 -encoding UTF-8 -d out (Get-ChildItem -Path src -Recurse -Filter *.java | ForEach-Object FullName)
java -cp out Main
```

Cole ou digite uma mensagem, clique em **Analisar mensagem** e confira a classificação, a pontuação e os sinais detectados.

## Executar os cenários de teste

Após compilar o projeto, execute:

```powershell
java -cp out CenarioTeste
java -cp out CenarioAuditoria100Golpes
java -cp out CenarioAuditoria200NovasMensagens
```

Os cenários cobrem mensagens comuns, links, arquivos perigosos, acesso remoto, transferências, pedidos multilíngues de dados sensíveis e bloqueio de cartão. As auditorias adicionais validam 200 golpes e 100 mensagens suspeitas, com conjuntos independentes de frases.

## Limitações e uso responsável

O detector utiliza heurísticas baseadas em regras. Portanto, uma mensagem legítima pode receber alerta e uma fraude inédita pode não conter termos conhecidos. Antes de enviar dinheiro, códigos, documentos ou imagens de cartão:

- confirme a solicitação usando um canal oficial e independente;
- não clique em links recebidos por mensagens inesperadas;
- não compartilhe senhas, códigos de autenticação, CVV, fotos de cartão ou documentos;
- procure diretamente o aplicativo, site ou telefone oficial da instituição.

## Créditos

**Criadores do projeto**

- Jean Luis Machado
- Cauã G. Fernandes

**Revisão de código:** ChatGPT

**Melhorias sugeridas por IA.**

## Contribuições

Contribuições são bem-vindas. Para adicionar regras, priorize expressões específicas, inclua um cenário em `CenarioTeste.java` e confirme que os casos existentes continuam aprovados.

## Licença

O projeto ainda não possui uma licença declarada. Antes de reutilizá-lo ou distribuí-lo, defina uma licença adequada no repositório.
