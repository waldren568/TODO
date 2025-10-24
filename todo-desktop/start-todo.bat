@echo off
mode con cols=42 lines=3
title Loading...
cd /d "C:\Users\Utente\Downloads\TODO List (1)\TODO-List"
:: Spring Boot completamente nascosto
start /b "" cmd /c "mvnw spring-boot:run >nul 2>&1"

cd /d "C:\Users\Utente\Downloads\TODO List (1)\TODO-List\todo-desktop"
:: React completamente nascosto
start /b "" cmd /c "npm start >nul 2>&1"

setlocal enabledelayedexpansion

set /a seconds=0
set /a minutes=0
set /a hours=0

:loop
cls
echo Please, wait for the end of the queue: !hours!h !minutes!m !seconds!s
timeout /t 1 /nobreak >nul
set /a seconds+=1

if !seconds! geq 60 (
    set /a seconds=0
    set /a minutes+=1
)
if !minutes! geq 60 (
    set /a minutes=0
    set /a hours+=1
)

goto loop

taskkill /f /im java.exe >nul 2>&1
exit