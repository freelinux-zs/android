#include <jni.h>
/*
 * Class:     com_example_sample_MainActivity
 * Method:    showlog
 * Signature: ()Ljava/lang/String;
 */
JNIEXPORT jstring JNICALL Java_com_example_sample_MainActivity_showlog
  (JNIEnv *env, jobject thiz) {

	return (*env)->NewStringUTF(env, "hello c!!");
}
