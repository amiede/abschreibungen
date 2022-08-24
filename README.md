# Abschreibungen
Java-Beispiel für Abschreibungen im betrieblichen Rechnungswesen


# Abschreibungsprojekt downloaden und starten
In den Releases Tab wechseln und die gewünschte Version auswählen.

Für Windows: htwsaar-abschreibungenWindows.Server.2022-runner.jar
Für Linux: htwsaar-abschreibungenLinux-runner.jar
Für Mac: htwsaar-abschreibungenMac.OS.X-runner.jar

Um die Jar zu starten kann, wenn man Java 17 richtig eingerichtet hat, auf das Jar doppelklicken.
Alternativ erst mittels ``java --version`` testen, ob es Java 17 ist und anschließend 
``java -jar <gewünschteVersion>`` ausführen.


# Wie baut man das Abschreibungsprojekt?

## Vorabbedingungen:

- Maven 3.8.x
- Java 17

1. ``git clone https://github.com/amiede/abschreibungen.git``
2. ```cd abschreibungen```
3. ```mvn quarkus:dev```

# Wie man eine Veröffentlichung erstellt
- Erstellen eines Drafts mit einem entsprechenden Tag.
- Der Workflow sollte für alle Plattformen ausgelöst und erstellt werden.
- Nachdem der Workflow abgeschlossen ist, kann die Version zur Veröffentlichung freigegeben werden.