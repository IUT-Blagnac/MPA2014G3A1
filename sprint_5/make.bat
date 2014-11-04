set ASCIIDOCDIR=./tools/asciidoc-8.6.9/
set PLANTUMLDIR=./tools/
set SPRINTDIR=%~dp0
set BINDIR=./bin/
set SRCDOCDIR=./srcdoc/
set DOCDIR=./doc/
set PYTHONEXE=C:\Python27\

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
javac -cp .;%BINDIR%;./bin -d %BINDIR% ./src/MakeOPTIweb.java

@echo ///////////////////////////////////////////////////////
@echo // EXECUTION de l'app
@echo ///////////////////////////////////////////////////////

 java -cp ./bin;.;./tools/junit.jar MakeOPTIweb
 cd %SPRINTDIR%

goto fin

:doc

@echo ///////////////////////////////////////////////////////
@echo // COMPILATION des documentations
@echo ///////////////////////////////////////////////////////
java -jar %PLANTUMLDIR%plantuml.jar -Tpng -o images %SRCDOCDIR%diag0.puml

%PYTHONEXE%python %ASCIIDOCDIR%asciidoc.py -a source-hightlighte=pygments -o %DOCDIR%docTechnique.html %SRCDOCDIR%docTechnique.txt
%PYTHONEXE%python %ASCIIDOCDIR%asciidoc.py -a source-hightlighte=pygments -o %DOCDIR%docUtilisateur.html %SRCDOCDIR%docUtilisateur.txt
%PYTHONEXE%python %ASCIIDOCDIR%asciidoc.py -a source-hightlighte=pygments -o %DOCDIR%présentationFinale.html %SRCDOCDIR%présentationFinale.txt
goto fin

:test

@echo ///////////////////////////////////////////////////////
@echo // EXECUTION des tests
@echo ///////////////////////////////////////////////////////
casperjs.bat test casperAccueil.js
casperjs.bat test casperCredits.js
casperjs.bat test casperEtudiants.js
casperjs.bat test casperIntervenants.js
casperjs.bat test casperProjets.js
casperjs.bat test casperSujets.js
cd %SPRINTDIR%

 goto fin

:fin
@echo ///////////////////////////////////////////////////////
@echo // Fin du .bat
@echo ///////////////////////////////////////////////////////