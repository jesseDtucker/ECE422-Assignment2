setlocal
cls
cd %~dp0
protoc.exe --java_out=./src/ src/com/jetucker/*.proto
endlocal