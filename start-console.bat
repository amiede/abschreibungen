@echo

REM @Name		 
REM @Author	Oliver Avarello
REM @Version	29.01.2018
REM @Snapshort	1.0.0
REM 
REM Description:
REM	Gibt das programm auf der konsole aus

REM clean - Loeschen aller erzeugten Artefakte und des target-Verzeichnisses
REM package - Build und Erzeugung der Ergebnis-Artefakte

mvn clean package

REM exec:java - Startet die Konsolenausgabe

mvn exec:java