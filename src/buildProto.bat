setlocal
cls
cd %~dp0
protoc.exe --java_out=./ com/jetucker/*.proto
endlocal