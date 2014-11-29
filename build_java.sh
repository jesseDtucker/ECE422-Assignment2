rm -r ./bin
mkdir bin
javac -sourcepath ./src/com/jetucker -d ./bin/ -classpath "./:./bin/:./lib/protobuf.jar:.\lib\quick-json-1.0.2.3.jar" ./src/com/jetucker/*.java
