@echo off
setlocal

rem Where is the Java Runtime Environment (JRE)?
rem We use the JRE *inside* the JDK, because it has the Hotspot server VM.
set JRE_HOME=c:\pro\jdk\jre

rem Where is the Mines JTK? (Where is your build.xml?)
set MINES_JTK_HOME=c:\dhale\box\jtk\trunk

rem Where will Java look for classes? 
rem Add other jars to this list as necessary.
set CLASSPATH=^
%MINES_JTK_HOME%\build\jar\edu_mines_jtk.jar;^
%MINES_JTK_HOME%\jar\gluegen-rt.jar;^
%MINES_JTK_HOME%\jar\jogl.jar;^
%MINES_JTK_HOME%\jar\junit.jar;^
.

rem Where are the relevant native (non-Java) code libraries?
set JAVA_LIBRARY_PATH=^
%MINES_JTK_HOME%\lib\windows\x86

rem Run a server VM with assertions enabled and a 1GB max Java heap.
rem Modify these flags and properties as necessary for your system.
java -server -ea -Xmx1000m ^
-Djava.library.path=%JAVA_LIBRARY_PATH% ^
-Djava.util.logging.config.file=c:\dhale\etc\java_logging_config ^
%*

endlocal
