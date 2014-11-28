#include <cstring>

#include "com_jetucker_Encryptor.h"

const auto STEP_SIZE = 4 * sizeof(long);

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

void ThrowException(JNIEnv* env, char* msg)
{
	env->ThrowNew(env->FindClass("java/lang/Exception"), msg);
}

template<void (*Func)(long*,long*)>
jbyteArray ModifyArray(JNIEnv* env, jobject self, jbyteArray arr, jbyteArray key)
{
	auto keySize = env->GetArrayLength(key);
	if(keySize != STEP_SIZE)
	{
		ThrowException(env, "Key was the wrong size! Must be a 16 byte key!");
		return NULL;
	}
	auto arrSize = env->GetArrayLength(arr);

	auto paddedSize = arrSize;
	if(paddedSize % STEP_SIZE != 0)
	{
		paddedSize = arrSize + STEP_SIZE - (arrSize - 1) % STEP_SIZE - 1;
	}

	jbyte* keyBuffer = new jbyte[STEP_SIZE];
	jbyte* resultBuffer = new jbyte[paddedSize];
	memset(keyBuffer, 0, STEP_SIZE);
	memset(resultBuffer, 0, paddedSize);

	env->GetByteArrayRegion(arr, 0, arrSize, resultBuffer);
	env->GetByteArrayRegion(key, 0, STEP_SIZE, keyBuffer);

	auto curr = resultBuffer;

	curr = resultBuffer;
	while(curr < resultBuffer + paddedSize)
	{
		Func((long*)(curr), (long*)(keyBuffer));
		curr += STEP_SIZE;
	}

	auto result = env->NewByteArray(paddedSize);
	env->SetByteArrayRegion(result, 0, paddedSize, resultBuffer);

	return result;
}

JNIEXPORT jbyteArray JNICALL Java_com_jetucker_Encryptor_Encrypt(JNIEnv* env, jobject self, jbyteArray arr, jbyteArray key)
{
	return ModifyArray<encrypt>(env, self, arr, key);
}

JNIEXPORT jbyteArray JNICALL Java_com_jetucker_Encryptor_Decrypt(JNIEnv* env, jobject self, jbyteArray arr, jbyteArray key)
{
	return ModifyArray<decrypt>(env, self, arr, key);
}