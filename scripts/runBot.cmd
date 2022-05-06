@echo off
Rem starts the botdetector
echo "starts the botdetector"

set SERVER_PORT=%1%

if  x%SERVER_PORT% == x (
set SERVER_PORT=8080
) else (
set SERVER_PORT=9090
)

java -jar ../target/botdetector-0.0.1-SNAPSHOT.jar