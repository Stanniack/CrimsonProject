<h1 align="center">✨ Releases / Lançamentos ✨</h1>

---

## Alpha v0.0.0 - 29/01/24
- **5 tipos de minérios** para testes: *Condensed Scordite, Scordite, Dense Veldspar e Veldspar.*
  - Mineração por prioridade.
- **Identificação do cargo hold** em compact mode ao atingir capacidade máxima:
  - Retorna à estação automaticamente.
  - *Drones não são suportados nesta versão (ver futuras atualizações).*
- **Requisitos:** 
  - Estação espacial e área de mineração devem estar no mesmo sistema solar.
  - Labels necessários para rotas:  
    - `HOMESTATION1`: estação para armazenar minérios.  
    - `MININGBOT1`: área de mineração.
- Sem interface gráfica (*versão Alpha para testes*).

---

## **Histórico de Versões**

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

---

## **Bug Fixes**

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

