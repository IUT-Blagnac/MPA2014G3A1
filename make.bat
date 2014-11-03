

set ASCIIDOCDIR=./tools/asciidoc-8.6.9/
set PLANTUMLDIR=./tools/
set SPRINTDIR=%~dp0
set SRCDIR=./src/Vue/
set BINDIR=./bin/
set SRCDOCDIR=./srcdoc/
set DOCDIR=./doc/
set PYTHONEXE=C:\Python27\
set SOURCEPATH=./src/Vue/;./src/Controle/;./src/Modele/

if "%1"=="exe" (
goto exe 
)
if "%1"=="doc" (
goto doc
)
if "%1"=="test" (
goto test
)
if "%1"=="" (
goto fin 
)

:exe
@echo ///////////////////////////////////////////////////////
@echo // COMPILATION des executables
@echo ///////////////////////////////////////////////////////
javac -cp .;%BINDIR%;./bin -d %BINDIR% ./src/Vue/*.java ./src/Modele/*.java ./src/Controle/*.java

@echo ///////////////////////////////////////////////////////
@echo // EXECUTION de l'app
@echo ///////////////////////////////////////////////////////


 java -cp ./bin;.;./tools/junit.jar Vue.OPTI
 cd %SPRINTDIR%

goto fin

:doc

@echo ///////////////////////////////////////////////////////
@echo // COMPILATION des documentations
@echo ///////////////////////////////////////////////////////
java -jar %PLANTUMLDIR%plantuml.jar -Tpng -o images %SRCDOCDIR%diag0.puml

%PYTHONEXE%python %ASCIIDOCDIR%asciidoc.py -a source-hightlighte=pygments -o %DOCDIR%DocUt.html %SRCDOCDIR%docU.txt
%PYTHONEXE%python %ASCIIDOCDIR%asciidoc.py -a source-hightlighte=pygments -o %DOCDIR%DocTe.html %SRCDOCDIR%docT.txt


goto fin

:test

@echo ///////////////////////////////////////////////////////
@echo // COMPILATION des tests
@echo ///////////////////////////////////////////////////////
 javac -cp .;bin\junit.jar -d %BINDIR% %SRCDIR%CsvManager.java
 javac -cp .;bin\junit.jar -d %BINDIR% %SRCDIR%CsvManagerTest.java

@echo ///////////////////////////////////////////////////////
@echo // EXECUTION des tests
@echo ///////////////////////////////////////////////////////

 java -cp ./bin;.;./tools/junit.jar CsvManagerTest
 cd %SPRINTDIR%

 goto fin

:fin
@echo ///////////////////////////////////////////////////////
@echo // Fin du .bat
@echo ///////////////////////////////////////////////////////