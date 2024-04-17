<h1 align="center"> Releases/Lançamentos </h1>

# Alpha v0.0.0 - 29/01/24

* Minera apenas 5 tipos de minérios: Condensed Scordite, Scordite, Dense Veldspar e Veldspar
  - Mineração por prioridade, respectivamente.

* Utiliza Apenas a nave Venture 5km³ cargo hold
    - Por enquanto não possui funcionalidade de drones mineradores.

* Estação espacial e área de mineração precisam estar no mesmo sistema solar
    - O bot encontra suas rotas por labels na aba de localizações;
    - HOMESTATION1 para a estação a ser estocado os minérios;
    - MININGBOT1 para a área de mineração.

* Não possui interface gráfica, uma vez que é apenas uma versão Alpha de testes

# Alpha v0.1.0 - 02/02/24

  * Prioridade dos minérios invertidos (De menor para o maior), assim podendo adicionar outros minérios sem muita alteração no código
    - 5 tipos de minérios: Veldspar, Concentrated V., Dense V., Scordite, Condensed S.

  * As demais funcionalidades são iguais ao do Alpha 0

# Alpha v0.2.0 - 27/02/24
   - As funcionalidades do Alpha 0.1 continuam as mesmas;
   - Abas foram repadronizadas para facilitar o uso do bot sem que ele quebre com frequência;
   - Algoritmo foi enxugado em ExtractOre para evitar que o algoritmo quebre com frequência ou, se quebrar, volte depois de um tempo.

# Alpha v0.2.1 - 03/03/24
  -  Não é mais necessário a HUD do jogo para abrir a aba de localização. O script ExtractOre agora utiliza a shortcut 'L' para tal função;
  -  Atualizando Tesseract 4.5.1 -> 5.10.0.

# Alpha v0.2.2 - 06/03/24
  - Aba de lock Target agora possui tempo de tolerância para resetar o script se caso o botão não for achado.

# Alpha v0.3.0 - 18/03/24
  - Agora "Approaching" e canhões são identificados por varredura de pixels.

# Alpha v0.3.1 - 27/03/24
  - O mecanismo de empurrar a tela (dragScreen) começou a ser repadronizado.

# Bug fixes

* Alpha v0.0.0 - 02/02/24
   - Consertado a parte do algoritmo que não reconhecia o minério de prioridade 0.
 
* Alpha v0.1.0 - 08/02/24
   - Atributo timeStart agora é reiniciado na condicional onde volta para o caso 5. O atributo timeStart não era reiniciado, portanto entrando em um possível looping entre o case 5 e 6;
   - Tolerância para achar LOCKTARGET aumentado para 10. Aumenta o tempo em 3 segundos mas minimiza possível erro de não achar LOCKTARGET mesmo ele existindo;
   - Alterando lógica do case 5 do script ExtractOre. Agora a checagem de tolerância de tempo para "Approaching" está aninhando com o else que aborda a lógica principal, evitando a var flag amountRect chegar dentro do antigo if valendo 4. Isso é um erro de lógica e poderia acontecer;
   - Se o script quebrasse, a nave ficaria parada no Asteroid sem minerar. Adicionando um tempo de tolerância de acordo com o tempo de mineração de cada nave (Nesse caso, 20 minutos) para verificar se os canhões estão ativados. Caso não, ativa os canhões.

* Alpha v0.1.0 - 18/02/24
  - Consertado flag que bugava a identificação de melhor minério de acordo com as regras de negócio. Agora a identificação ocorre normalmente, minera sempre o asteroid de maior prioridade encontrado;
  - Código de se aproximar do Asteroid foi otimizado. Agora a ação é feita por duplo-clique em vez de procurar pela seta e clicar.

<strike>* Alpha v0.3.0 - 24/03/24</strike>
  <strike>- Corrigindo algoritmo de verificação de canhão em modo opacidade, colocando no case correto, impedindo um looping infinito.</strike>
   

  
