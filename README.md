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
