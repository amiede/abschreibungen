@echo

REM @Name		 
REM @Author	Oliver Avarello
REM @Version	29.01.2018
REM @Snapshort	1.0.0
REM
REM Description:
REM	startet die javaFx view 

REM clean - Loeschen aller erzeugten Artefakte und des target-Verzeichnisses
REM jfx:run - ist ein plugin das Build und Erzeugung der Ergebnis-Artefakte
REM 			und starten das user Interface mit Javafx

echo maven wird gestartet:

mvn clean jfx:run