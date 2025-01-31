<h1 align="center">🚀✨Lançamentos✨🚀</h1>

---

<h1 align="center">📌 Versão Atual - Alpha v0.4.3 - 08/12/24 📌</h1>

- **Interface CLI (Command Line Interface):**  
  - Por enquanto, a interface é via linha de comando, ainda em versão Alpha.  
- **Sons de alerta** adicionados para diferentes situações:  
  - Som de alerta quando a nave for atacada.  
  - Som de alerta quando o ciclo de mineração terminar.  
  - Som de alerta quando o ciclo total de mineração acabar.  
- **Segurança de desligamento do servidor:**  
  - A nave para de minerar e retorna automaticamente à estação quando o servidor estiver prestes a ser desligado.  
- **Memorização de clicks** implementada:  
  - Melhora o desempenho ao trabalhar em conjunto com o Tesseract e o reconhecimento de imagens OCR.  
- **Reconhecimento de botões e palavras por OCR:**  
  - Aprimoramento na detecção de botões e textos na interface do jogo.  
- **Identificação do cargo hold em compact mode:**  
  - Ao atingir a capacidade máxima, a nave retorna automaticamente à estação.  
- **Pausa automática em tela não full screen:**  
  - O script pausa automaticamente se a tela não estiver em modo full screen.  
  - O script é retomado automaticamente quando a tela volta ao modo full screen.  
- **Comportamento in-game:**  
  - Suporte a drones mineradores e drones de combate.  
  - Identifica se a nave está sob ataque:  
    - Em caso de ataque, a nave foge para a estação e desliga o script automaticamente.  
- **Requisitos:**  
  - Estação espacial e área de mineração devem estar no mesmo sistema solar.  
  - Labels necessários para rotas:  
    - `HOMESTATION`: estação para armazenar minérios.  
    - `ASTEROIDBELTI` a `ASTEROIDBELTV`: áreas de mineração.  

---

<h1 align="center">💻 Tecnologias Utilizadas 💻</h1>

O projeto faz uso das seguintes tecnologias e bibliotecas:

- **Linguagem:** Java 18
- **Gerenciamento de dependências:** Maven
- **Reconhecimento de texto (OCR):** Tesseract via Tess4J (v5.10.0)
- **Manipulação de JSON:** Jackson Databind (v2.15.2)
- **Utilização de tuplas:** JavaTuples (v1.2)
- **Testes:** JUnit 4 (v4.13.2)

---
<h1 align="center">📜 Histórico de Versões 📜</h1>

### **Alpha v0.1.0 - 02/02/24**
- Minérios priorizados do menor para o maior, facilitando adição de novos tipos.
- Minérios disponíveis: *Veldspar, Concentrated V., Dense V., Scordite, Condensed S.*

### **Alpha v0.2.0 - 27/02/24**
- Abas repadronizadas para maior estabilidade.
- Algoritmo de *ExtractOre* otimizado para lidar com falhas frequentes.

### **Alpha v0.2.1 - 03/03/24**
- Shortcut `'L'` substitui a necessidade de abrir a HUD manualmente para acessar localizações.
- Atualizado **Tesseract** de versão 4.5.1 para 5.10.0.

### **Alpha v0.2.2 - 06/03/24**
- Aba de *Lock Target* agora possui tolerância de tempo para resetar o script caso o botão não seja localizado.

### **Alpha v0.3.0 - 18/03/24**
- Identificação de "Approaching" e canhões implementada por varredura de pixels.

### **Alpha v0.3.1 - 27/03/24**
- Início da repadronização do mecanismo de empurrar a tela (*dragScreen*).

### **Alpha v0.3.2 - 14/04/24**
- Seleção de *target* adaptada para situações como: *Free target, Lock target e Alpha target.*

### **Alpha v0.3.3 - 03/05/24**
- Expansão de cores para *Lock Target* e *Free Target*, aumentando precisão.
- Definido tempo para *DragScreen* em casos de verificação presa na *compactMaxCargo*.

### **Alpha v0.3.4 - 12/05/24**
- Redução e generalização da área de busca de retângulos no método *setDestination*.

### **Alpha v0.3.5 - 17/05/24**
- Introduzido *CargoDeposit*: transfere carga sem verificar se o mining hold está cheio.

### **Alpha v0.3.6 - 01/06/24**
- Método experimental para verificar *targets* que não sejam asteroides.

### **Alpha v0.3.7 - 12/06/24**
- Suporte inicial a drones mineradores (*Launch, Engage, Return*).

### **Alpha v0.3.8 - 20/06/24**
- Implementação de detecção de ataques à nave (*1ª versão*).

### **Alpha v0.3.9 - 28/07/24**
- Métodos para disparar alertas sonoros.

### **Alpha v0.3.9.1 - 03/08/24**
- Método de engajamento de drones adicionado.

### **Alpha v0.4.0 - 03/08/24**
- Alertas em formato **.wav** para atenção do usuário.

### **Alpha v0.4.1 - 24/08/24**
- Logs textuais para monitorar ciclos de mineração.

### **Alpha v0.4.2 - 22/09/24**
- Alternância entre 5 *asteroid belts* implementada.

### **Alpha v0.4.3 - 08/12/24**
- Pausa de script caso a resolução não seja full screen ou a instância do EVE não esteja em primeira plano

---

<h1 align="center">🔧 Bug Fixes 🔧</h1>

### **Alpha v0.0.0 - 02/02/24**
- Corrigido algoritmo que não reconhecia minério de prioridade 0.

### **Alpha v0.1.0 - 08/02/24**
- Ajuste no atributo `timeStart` e aumento da tolerância para *LOCKTARGET*.

### **Alpha v0.1.0 - 18/02/24**
- Correção da flag para priorização de minérios.
- Código de aproximação por duplo-clique em vez de uso de setas.

### **Alpha v0.3.0 - 24/03/24**
- Correção no algoritmo de verificação de opacidade de canhões.

### **Alpha v0.3.5 - 26/05/24**
- Tratamento para exceção `NullPointerException` do Tesseract.

### **Alpha v0.3.6 - 07/06/24**
- Refinamento do método *invalidTarget* em *ExtractOre*.

### **Alpha v0.3.7 - 19/06/24**
- Adicionado *sleep* para exceções no Tesseract.

---

<h1 align="center">🚀✨Releases✨🚀</h1>

---

<h1 align="center">📌 Current Version - Alpha v0.4.3 - 08/12/24 📌</h1>

- **Interface CLI (Command Line Interface):**  
  - For now, the interface is via command line, still in Alpha version.  
- **Alert sounds** added for different situations:  
  - Alert sound when the ship is attacked.  
  - Alert sound when the mining cycle ends.  
  - Alert sound when the total mining cycle ends.  
- **Server shutdown safety:**  
  - The ship stops mining and automatically returns to the station when the server is about to shut down.  
- **Click memorization** implemented:  
  - Improves performance when working together with Tesseract and OCR image recognition.  
- **Button and text recognition by OCR:**  
  - Enhanced detection of buttons and texts in the game interface.  
- **Cargo hold identification in compact mode:**  
  - When reaching maximum capacity, the ship automatically returns to the station.  
- **Automatic pause in non-full screen mode:**  
  - The script pauses automatically if the screen is not in full screen mode.  
  - The script resumes automatically when the screen returns to full screen mode.  
- **In-game behavior:**  
  - Support for mining drones and combat drones.  
  - Identifies if the ship is under attack:  
    - In case of attack, the ship flees to the station and automatically shuts down the script.  
- **Requirements:**  
  - Space station and mining area must be in the same solar system.  
  - Required labels for routes:  
    - `HOMESTATION`: station to store minerals.  
    - `ASTEROIDBELTI` to `ASTEROIDBELTV`: mining areas.  

---

<h1 align="center">💻 Technologies Used 💻</h1>

The project uses the following technologies and libraries:

- **Language:** Java 18
- **Dependency management:** Maven
- **Text recognition (OCR):** Tesseract via Tess4J (v5.10.0)
- **JSON manipulation:** Jackson Databind (v2.15.2)
- **Tuple usage:** JavaTuples (v1.2)
- **Testing:** JUnit 4 (v4.13.2)

---

<h1 align="center">📜 Version History 📜</h1>

### **Alpha v0.1.0 - 02/02/24**
- Minerals prioritized from smallest to largest, facilitating the addition of new types.
- Available minerals: *Veldspar, Concentrated V., Dense V., Scordite, Condensed S.*

### **Alpha v0.2.0 - 27/02/24**
- Tabs standardized for greater stability.
- *ExtractOre* algorithm optimized to handle frequent failures.

### **Alpha v0.2.1 - 03/03/24**
- Shortcut `'L'` replaces the need to manually open the HUD to access locations.
- Updated **Tesseract** from version 4.5.1 to 5.10.0.

### **Alpha v0.2.2 - 06/03/24**
- *Lock Target* tab now has time tolerance to reset the script if the button is not located.

### **Alpha v0.3.0 - 18/03/24**
- Identification of "Approaching" and cannons implemented via pixel scanning.

### **Alpha v0.3.1 - 27/03/24**
- Start of standardization of the screen push mechanism (*dragScreen*).

### **Alpha v0.3.2 - 14/04/24**
- *Target* selection adapted for situations such as: *Free target, Lock target, and Alpha target.*

### **Alpha v0.3.3 - 03/05/24**
- Color expansion for *Lock Target* and *Free Target*, increasing accuracy.
- Defined time for *DragScreen* in cases of verification stuck in *compactMaxCargo*.

### **Alpha v0.3.4 - 12/05/24**
- Reduction and generalization of the search area for rectangles in the *setDestination* method.

### **Alpha v0.3.5 - 17/05/24**
- Introduced *CargoDeposit*: transfers cargo without checking if the mining hold is full.

### **Alpha v0.3.6 - 01/06/24**
- Experimental method to verify *targets* that are not asteroids.

### **Alpha v0.3.7 - 12/06/24**
- Initial support for mining drones (*Launch, Engage, Return*).

### **Alpha v0.3.8 - 20/06/24**
- Implementation of ship attack detection (*1st version*).

### **Alpha v0.3.9 - 28/07/24**
- Methods to trigger sound alerts.

### **Alpha v0.3.9.1 - 03/08/24**
- Drone engagement method added.

### **Alpha v0.4.0 - 03/08/24**
- Alerts in **.wav** format for user attention.

### **Alpha v0.4.1 - 24/08/24**
- Text logs to monitor mining cycles.

### **Alpha v0.4.2 - 22/09/24**
- Switching between 5 *asteroid belts* implemented.

### **Alpha v0.4.3 - 08/12/24**
- Script pause if the resolution is not full screen or the EVE instance is not in the foreground.

---

<h1 align="center">🔧 Bug Fixes 🔧</h1>

### **Alpha v0.0.0 - 02/02/24**
- Fixed algorithm that did not recognize priority 0 mineral.

### **Alpha v0.1.0 - 08/02/24**
- Adjusted `timeStart` attribute and increased tolerance for *LOCKTARGET*.

### **Alpha v0.1.0 - 18/02/24**
- Fixed flag for mineral prioritization.
- Approach code via double-click instead of using arrows.

### **Alpha v0.3.0 - 24/03/24**
- Fixed algorithm for verifying cannon opacity.

### **Alpha v0.3.5 - 26/05/24**
- Handling of `NullPointerException` for Tesseract.

### **Alpha v0.3.6 - 07/06/24**
- Refinement of the *invalidTarget* method in *ExtractOre*.

### **Alpha v0.3.7 - 19/06/24**
- Added *sleep* for Tesseract exceptions.

---
