@echo off
setlocal enabledelayedexpansion

set BUILD_GATEWAY=true
set BUILD_JAVA_CHALLENGE=true
set CONTAINER_TOOL=podman

for %%i in (%*) do (
    if "%%i"=="gateway" set BUILD_GATEWAY=false
    if "%%i"=="java-challenge" set BUILD_JAVA_CHALLENGE=false
    if "%%i"=="docker" set CONTAINER_TOOL=docker
)

if "%BUILD_GATEWAY%"=="true" (
    echo Construyendo la imagen del Gateway con %CONTAINER_TOOL%...
    cd gateway
    %CONTAINER_TOOL% build -f Dockerfile -t gateway .
    if errorlevel 1 (
        echo Error al construir la imagen del Gateway.
        exit /b 1
    )
    cd ..
) else (
    echo Saltando la construcción de la imagen del Gateway.
)

if "%BUILD_JAVA_CHALLENGE%"=="true" (
    echo Construyendo la imagen de Java Challenge con %CONTAINER_TOOL%...
    %CONTAINER_TOOL% build -f Dockerfile -t java-challenge .
    if errorlevel 1 (
        echo Error al construir la imagen de Java Challenge.
        exit /b 1
    )
) else (
    echo Saltando la construcción de la imagen de Java Challenge.
)

if "%CONTAINER_TOOL%"=="podman" (
    echo Levantando el entorno con podman-compose...
    podman compose up
) else (
    echo Levantando el entorno con docker-compose...
    docker-compose up
)