@echo off

REM @Name		 
REM @Author	Oliver Avarello
REM @Version	30.01.2018
REM @Snapshort	1.0.0
REM 
REM Description:
REM	Gibt das programm auf der konsole aus

cls
GOTO BEGIN_AUSGABE


:BEGIN_AUSGABE
echo Abschreibungen Consolen version

echo Waehlen sie aus:
echo
echo 1 - Konsolen ausgabe
echo 2 - JavaFX GUI starten
echo 0 - Beenden

set /p auswahl="Bitte waehlen sie aus:"

echo  EIGEGEBEN WURDE : %auswahl%

if "0" EQU "%auswahl%" (
GOTO truenull
) else (
GOTO :start1
)

:start1
if "1" EQU "%auswahl%"  (
GOTO trueeins
) else (
GOTO :start2
)

:start2
if "2" EQU "%auswahl%" (
GOTO truezwei 
) else (
GOTO :falseInput
)

:truenull
echo programm wird beendet
pause
GOTO ENDE


:trueeins
echo sie haben sich fuer die KonsolenAusgabe entschieden
REM clean - Loeschen aller erzeugten Artefakte und des target-Verzeichnisses
REM package - Build und Erzeugung der Ergebnis-Artefakte
REM exec:java - Startet die Konsolenausgabe
mvn clean package exec:java
GOTO SLEEP



:truezwei
echo sie haben sich fuer die UserInterface entschieden
REM clean - Loeschen aller erzeugten Artefakte und des target-Verzeichnisses
REM jfx:run - ist ein plugin das Build und Erzeugung der Ergebnis-Artefakte
REM 			und starten das user Interface mit Javafx
mvn clean jfx:run
pause
GOTO ENDE


:falseInput
echo ihre eingabe war falsch programm wird beendet 
pause
GOTO ENDE


:ENDE 
echo programm wird beendet
exit 0

:SLEEP
PAUSE
GOTO ENDE
