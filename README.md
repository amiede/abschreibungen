# abschreibungen
Java-Beispiel für Abschreibungen im betrieblichen Rechnungswesen

Für das Programm wurde das Build Tool Maven verwendet.

Um das Build Tool maven nutzen zu können müssen dieses von der offizielle Produkt Webseite [apache Maven](https://maven.apache.org/download.cgi)
herunterladen und installieren. Eine kleine Installation und Konfiguration Anleitung finden sie ebenfalls auf der
offiziellen Webseite: [apache Maven](https://maven.apache.org/install.html)

Um das Tool verwenden zu können gehen sie in das Projekt Verzeichnis und starten die Konsole.

Mit dem Befehl:

```bash
mvn clean jfx:run
```

Starten sie das JavaFx Beispiel für das Abschreibungstool.

Mit dem Befehl:

```bash
mvn clean package exec:java
```

Starten sie die Konsolen Ausgabe.

Alternativ können sie das Shell script:
```powershell
start
```
im Verzeichnis verwenden und die gewünschte ausgabe von Maven Builden lassen.

Bei der Verwendung von Windows verwenden sie die
```bash
start.bat
```
diese im ideal fall mit der Eingabeaufforderung oder der Power Shell zu starten.

Alternativ können die Ides Netbeans, Eclipse, IntelliJ oder JDevekloper verwendet werden.
