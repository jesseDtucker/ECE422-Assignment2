/* DO NOT EDIT THIS FILE - it is machine generated */
#include <jni.h>
/* Header for class com_jetucker_Encryptor */

#ifndef _Included_com_jetucker_Encryptor
#define _Included_com_jetucker_Encryptor
#ifdef __cplusplus
extern "C" {
#endif
/*
 * Class:     com_jetucker_Encryptor
 * Method:    GetKeySize
 * Signature: ()I
 */
JNIEXPORT jint JNICALL Java_com_jetucker_Encryptor_GetKeySize
  (JNIEnv *, jclass);

/*
 * Class:     com_jetucker_Encryptor
 * Method:    Encrypt
 * Signature: ([B[B)[B
 */
JNIEXPORT jbyteArray JNICALL Java_com_jetucker_Encryptor_Encrypt
  (JNIEnv *, jobject, jbyteArray, jbyteArray);

/*
 * Class:     com_jetucker_Encryptor
 * Method:    Decrypt
 * Signature: ([B[B)[B
 */
JNIEXPORT jbyteArray JNICALL Java_com_jetucker_Encryptor_Decrypt
  (JNIEnv *, jobject, jbyteArray, jbyteArray);

#ifdef __cplusplus
}
#endif
#endif
