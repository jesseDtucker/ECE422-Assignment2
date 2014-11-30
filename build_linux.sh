clear
./build_java.sh
javah -jni -d ./src/com/jetucker/ -cp bin/ com.jetucker.Encryptor
g++ -I"$JAVA_HOME/include/" -I"$JAVA_HOME/include/linux/" -std=c++0x -fPIC -Wall -shared ./src/com/jetucker/encryptor.cpp -o ./lib/libcom_jetucker_Encryptor.so
