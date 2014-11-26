#include <cstring>
#include <iostream>

#include "com_jetucker_Encryptor.h"

const auto STEP_SIZE = 2 * sizeof(long);

using namespace std;

void encrypt (long *v, long *k)
{
	/* TEA encryption algorithm */
	unsigned long y = v[0], z=v[1], sum = 0;
	unsigned long delta = 0x9e3779b9, n=32;

	while (n-- > 0){
		sum += delta;
		y += (z<<4) + k[0] ^ z + sum ^ (z>>5) + k[1];
		z += (y<<4) + k[2] ^ y + sum ^ (y>>5) + k[3];
	}

	v[0] = y;
	v[1] = z;
}

void decrypt (long *v, long *k)
{
	/* TEA decryption routine */
	unsigned long n=32, sum, y=v[0], z=v[1];
	unsigned long delta=0x9e3779b9l;

	sum = delta<<5;
	while (n-- > 0){
		z -= (y<<4) + k[2] ^ y + sum ^ (y>>5) + k[3];
		y -= (z<<4) + k[0] ^ z + sum ^ (z>>5) + k[1];
		sum -= delta;
	}
	v[0] = y;
	v[1] = z;
}

template<void (*Func)(long*,long*)>
jbyteArray ModifyArray(JNIEnv* env, jobject self, jbyteArray arr, jlong key)
{
	return arr;

	jboolean isCopy = false;
	auto javaSize = env->GetArrayLength(arr);
	auto javaBytes = env->GetByteArrayElements(arr,&isCopy);

	auto bytes = reinterpret_cast<char*>(javaBytes);
	auto size = javaSize;

	// must pad bytes out to be a multiple of 2 * sizeof(long)
	if(size % STEP_SIZE != 0)
	{
		auto paddedSize = size + STEP_SIZE - (size - 1) % STEP_SIZE - 1;
		bytes = new char[paddedSize];
		memset(bytes, 0, paddedSize);
		memcpy(bytes, javaBytes, javaSize);
		
		
		cout << "resizing to : " << paddedSize << " from : " << size << endl;
		
		size = paddedSize;
	}
	
	cout << "beginning modification ... " << size << endl;

	char* curr = bytes;
	while(curr < bytes + size)
	{
		Func(reinterpret_cast<long*>(curr), reinterpret_cast<long*>(&key));
		curr += STEP_SIZE;
	}

	auto result = env->NewByteArray(size);
	env->SetByteArrayRegion(result, 0, size, reinterpret_cast<jbyte*>(bytes));

	return result;
}

JNIEXPORT jbyteArray JNICALL Java_com_jetucker_Encryptor_Encrypt(JNIEnv* env, jobject self, jbyteArray arr, jlong key)
{
	return ModifyArray<encrypt>(env, self, arr, key);
}

JNIEXPORT jbyteArray JNICALL Java_com_jetucker_Encryptor_Decrypt(JNIEnv* env, jobject self, jbyteArray arr, jlong key)
{
	return ModifyArray<decrypt>(env, self, arr, key);
}