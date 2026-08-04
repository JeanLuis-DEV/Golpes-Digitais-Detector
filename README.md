# Detector de Golpes Digitais

Breve descrição

Projeto em Java que analisa mensagens de texto (uma ou várias linhas) para identificar sinais comuns de golpes digitais. O detector normaliza o texto, busca termos e padrões suspeitos e atribui uma pontuação que é convertida em um nível de risco.

Objetivo

Ajudar a identificar automaticamente mensagens potencialmente fraudulentas, oferecendo indicação de risco e motivos detectados para facilitar a verificação manual.

Funcionalidades principais

- Normalização de texto (remoção de acentos e caixa baixa).
- Detecção de links, domínios e padrões de valores monetários.
- Conjunto extenso de listas de termos (urgência, prêmio, pedido de dados, transferência, acesso remoto etc.).
- Regras que somam pontos por sinais encontrados e classificam o risco (baixo, suspeito, possivelmente golpe).
- Saída com nível de risco, pontuação e lista de motivos detectados.

Principais arquivos

- src\Main.java — ponto de entrada (interface por console).
- src\CenarioTeste.java — casos de teste / validação.
- src\Modelos\DetectorGolpes.java — coordena a análise.
- src\Modelos\NormalizadorMensagem.java — normaliza o texto.
- src\Modelos\AnalisadorConteudoMensagem.java — procura termos, links e valores.
- src\Modelos\CatalogoTermosGolpe.java — listas de termos e domínios.
- src\Modelos\Verificacoes.java — aplica regras e pontuações.
- src\Modelos\Execucaoif.java, MapMotivos.java, DeclaracoesCondicionais.java — utilidades para registro de motivos e verificação.
- src\Modelos\LeitorMensagemConsole.java, InterfaceConsole.java — leitura de entrada e apresentação.

Tecnologias

- Java 11+ (apenas biblioteca padrão).

Como compilar e executar

1) Compilar todos os fontes (a partir da raiz do projeto):

   javac -d out src\**\*.java

2) Executar o programa principal:

   java -cp out Main

   - Cole ou digite a mensagem e encerre com uma linha contendo apenas: FIM

3) Executar cenários de teste (opcional):

   java -cp out CenarioTeste

Observações

- O detector é baseado em regras e listas de termos — não substitui análise humana ou soluções de detecção baseadas em ML.
- Para aprimorar: permitir carregar listas de termos externas, ajustar pesos, criar testes automatizados e empacotar como JAR.

Contribuição

Pull requests são bem-vindos. Abra uma issue descrevendo a melhoria antes de mudanças significativas.

Licença

Use conforme sua necessidade (adicione uma licença explícita se desejar).
