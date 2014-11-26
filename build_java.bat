call ".\buildProto.bat"
rm -r ./bin
mkdir .\bin\
javac -sourcepath .\src\com\jetucker -d .\bin\ -classpath .\;.\bin\;.\lib\protobuf.jar .\src\com\jetucker\*.java