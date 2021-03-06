@echo off

rem
rem  All content copyright (c) 2003-2008 Terracotta, Inc.,
rem  except as may otherwise be noted in a separate copyright notice.
rem  All rights reserved.
rem

setlocal
set TC_INSTALL_DIR=%~d0%~p0
set TC_INSTALL_DIR="%TC_INSTALL_DIR:"=%"

if not defined JAVA_HOME set JAVA_HOME="%TC_INSTALL_DIR%\jre"
set JAVA_HOME="%JAVA_HOME:"=%"

%JAVA_HOME%\bin\jconsole %JAVA_OPTS% "-J-Djava.class.path=%TC_INSTALL_DIR%\lib\tc.jar;%JAVA_HOME%\lib\jconsole.jar;%JAVA_HOME%\lib\tools.jar"
endlocal
