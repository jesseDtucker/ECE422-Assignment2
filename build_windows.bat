cls
call "./build_java.bat"
javah -jni -d ./src/com/jetucker/ -cp bin/ com.jetucker.Encryptor
call "C:\Program Files (x86)\Microsoft Visual Studio 12.0\VC\vcvarsall.bat" amd64
"C:\Program Files (x86)\Microsoft Visual Studio 12.0\VC\bin\amd64\cl.exe" /I"C:\Program Files (x86)\Microsoft Visual Studio 12.0\VC\include" /I"C:/Program Files/Java/jdk1.7.0_71/include/" /I"C:/Program Files/Java/jdk1.7.0_71/include/win32/" /LD /D_WINDLL .\src\com\jetucker\encryptor.cpp /link /DLL /OUT:lib/libcom_jetucker_Encryptor.dll