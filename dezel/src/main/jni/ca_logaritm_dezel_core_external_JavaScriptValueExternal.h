/* DO NOT EDIT THIS FILE - it is machine generated */
#include <jni.h>
/* Header for class ca_logaritm_dezel_core_external_JavaScriptValueExternal */

#ifndef _Included_ca_logaritm_dezel_core_external_JavaScriptValueExternal
#define _Included_ca_logaritm_dezel_core_external_JavaScriptValueExternal
#ifdef __cplusplus
extern "C" {
#endif
/*
 * Class:     ca_logaritm_dezel_core_external_JavaScriptValueExternal
 * Method:    createNull
 * Signature: (J)J
 */
JNIEXPORT jlong JNICALL Java_ca_logaritm_dezel_core_external_JavaScriptValueExternal_createNull
  (JNIEnv *, jclass, jlong);

/*
 * Class:     ca_logaritm_dezel_core_external_JavaScriptValueExternal
 * Method:    createUndefined
 * Signature: (J)J
 */
JNIEXPORT jlong JNICALL Java_ca_logaritm_dezel_core_external_JavaScriptValueExternal_createUndefined
  (JNIEnv *, jclass, jlong);

/*
 * Class:     ca_logaritm_dezel_core_external_JavaScriptValueExternal
 * Method:    createString
 * Signature: (JLjava/lang/String;)J
 */
JNIEXPORT jlong JNICALL Java_ca_logaritm_dezel_core_external_JavaScriptValueExternal_createString
  (JNIEnv *, jclass, jlong, jstring);

/*
 * Class:     ca_logaritm_dezel_core_external_JavaScriptValueExternal
 * Method:    createNumber
 * Signature: (JD)J
 */
JNIEXPORT jlong JNICALL Java_ca_logaritm_dezel_core_external_JavaScriptValueExternal_createNumber
  (JNIEnv *, jclass, jlong, jdouble);

/*
 * Class:     ca_logaritm_dezel_core_external_JavaScriptValueExternal
 * Method:    createBoolean
 * Signature: (JZ)J
 */
JNIEXPORT jlong JNICALL Java_ca_logaritm_dezel_core_external_JavaScriptValueExternal_createBoolean
  (JNIEnv *, jclass, jlong, jboolean);

/*
 * Class:     ca_logaritm_dezel_core_external_JavaScriptValueExternal
 * Method:    createSymbol
 * Signature: (JLjava/lang/String;)J
 */
JNIEXPORT jlong JNICALL Java_ca_logaritm_dezel_core_external_JavaScriptValueExternal_createSymbol
  (JNIEnv *, jclass, jlong, jstring);

/*
 * Class:     ca_logaritm_dezel_core_external_JavaScriptValueExternal
 * Method:    createEmtpyObject
 * Signature: (J)J
 */
JNIEXPORT jlong JNICALL Java_ca_logaritm_dezel_core_external_JavaScriptValueExternal_createEmtpyObject
  (JNIEnv *, jclass, jlong);

/*
 * Class:     ca_logaritm_dezel_core_external_JavaScriptValueExternal
 * Method:    createEmptyArray
 * Signature: (J)J
 */
JNIEXPORT jlong JNICALL Java_ca_logaritm_dezel_core_external_JavaScriptValueExternal_createEmptyArray
  (JNIEnv *, jclass, jlong);

/*
 * Class:     ca_logaritm_dezel_core_external_JavaScriptValueExternal
 * Method:    createFunction
 * Signature: (JLjava/lang/Object;Ljava/lang/String;Ljava/lang/Object;)J
 */
JNIEXPORT jlong JNICALL Java_ca_logaritm_dezel_core_external_JavaScriptValueExternal_createFunction
  (JNIEnv *, jclass, jlong, jobject, jstring, jobject);

/*
 * Class:     ca_logaritm_dezel_core_external_JavaScriptValueExternal
 * Method:    protect
 * Signature: (JJ)V
 */
JNIEXPORT void JNICALL Java_ca_logaritm_dezel_core_external_JavaScriptValueExternal_protect
  (JNIEnv *, jclass, jlong, jlong);

/*
 * Class:     ca_logaritm_dezel_core_external_JavaScriptValueExternal
 * Method:    unprotect
 * Signature: (JJ)V
 */
JNIEXPORT void JNICALL Java_ca_logaritm_dezel_core_external_JavaScriptValueExternal_unprotect
  (JNIEnv *, jclass, jlong, jlong);

/*
 * Class:     ca_logaritm_dezel_core_external_JavaScriptValueExternal
 * Method:    call
 * Signature: (JJJ[JILjava/lang/Object;)V
 */
JNIEXPORT void JNICALL Java_ca_logaritm_dezel_core_external_JavaScriptValueExternal_call
  (JNIEnv *, jclass, jlong, jlong, jlong, jlongArray, jint, jobject);

/*
 * Class:     ca_logaritm_dezel_core_external_JavaScriptValueExternal
 * Method:    callMethod
 * Signature: (JJLjava/lang/String;[JILjava/lang/Object;)V
 */
JNIEXPORT void JNICALL Java_ca_logaritm_dezel_core_external_JavaScriptValueExternal_callMethod
  (JNIEnv *, jclass, jlong, jlong, jstring, jlongArray, jint, jobject);

/*
 * Class:     ca_logaritm_dezel_core_external_JavaScriptValueExternal
 * Method:    construct
 * Signature: (JJ[JILjava/lang/Object;)V
 */
JNIEXPORT void JNICALL Java_ca_logaritm_dezel_core_external_JavaScriptValueExternal_construct
  (JNIEnv *, jclass, jlong, jlong, jlongArray, jint, jobject);

/*
 * Class:     ca_logaritm_dezel_core_external_JavaScriptValueExternal
 * Method:    defineProperty
 * Signature: (JJLjava/lang/String;JLjava/lang/Object;Ljava/lang/Object;ZZZLjava/lang/Object;)V
 */
JNIEXPORT void JNICALL Java_ca_logaritm_dezel_core_external_JavaScriptValueExternal_defineProperty
  (JNIEnv *, jclass, jlong, jlong, jstring, jlong, jobject, jobject, jboolean, jboolean, jboolean, jobject);

/*
 * Class:     ca_logaritm_dezel_core_external_JavaScriptValueExternal
 * Method:    setProperty
 * Signature: (JJLjava/lang/String;J)V
 */
JNIEXPORT void JNICALL Java_ca_logaritm_dezel_core_external_JavaScriptValueExternal_setProperty__JJLjava_lang_String_2J
  (JNIEnv *, jclass, jlong, jlong, jstring, jlong);

/*
 * Class:     ca_logaritm_dezel_core_external_JavaScriptValueExternal
 * Method:    setProperty
 * Signature: (JJLjava/lang/String;Ljava/lang/String;)V
 */
JNIEXPORT void JNICALL Java_ca_logaritm_dezel_core_external_JavaScriptValueExternal_setProperty__JJLjava_lang_String_2Ljava_lang_String_2
  (JNIEnv *, jclass, jlong, jlong, jstring, jstring);

/*
 * Class:     ca_logaritm_dezel_core_external_JavaScriptValueExternal
 * Method:    setProperty
 * Signature: (JJLjava/lang/String;D)V
 */
JNIEXPORT void JNICALL Java_ca_logaritm_dezel_core_external_JavaScriptValueExternal_setProperty__JJLjava_lang_String_2D
  (JNIEnv *, jclass, jlong, jlong, jstring, jdouble);

/*
 * Class:     ca_logaritm_dezel_core_external_JavaScriptValueExternal
 * Method:    setProperty
 * Signature: (JJLjava/lang/String;Z)V
 */
JNIEXPORT void JNICALL Java_ca_logaritm_dezel_core_external_JavaScriptValueExternal_setProperty__JJLjava_lang_String_2Z
  (JNIEnv *, jclass, jlong, jlong, jstring, jboolean);

/*
 * Class:     ca_logaritm_dezel_core_external_JavaScriptValueExternal
 * Method:    getProperty
 * Signature: (JJLjava/lang/String;)J
 */
JNIEXPORT jlong JNICALL Java_ca_logaritm_dezel_core_external_JavaScriptValueExternal_getProperty
  (JNIEnv *, jclass, jlong, jlong, jstring);

/*
 * Class:     ca_logaritm_dezel_core_external_JavaScriptValueExternal
 * Method:    setPropertyAtIndex
 * Signature: (JJIJ)V
 */
JNIEXPORT void JNICALL Java_ca_logaritm_dezel_core_external_JavaScriptValueExternal_setPropertyAtIndex__JJIJ
  (JNIEnv *, jclass, jlong, jlong, jint, jlong);

/*
 * Class:     ca_logaritm_dezel_core_external_JavaScriptValueExternal
 * Method:    setPropertyAtIndex
 * Signature: (JJILjava/lang/String;)V
 */
JNIEXPORT void JNICALL Java_ca_logaritm_dezel_core_external_JavaScriptValueExternal_setPropertyAtIndex__JJILjava_lang_String_2
  (JNIEnv *, jclass, jlong, jlong, jint, jstring);

/*
 * Class:     ca_logaritm_dezel_core_external_JavaScriptValueExternal
 * Method:    setPropertyAtIndex
 * Signature: (JJID)V
 */
JNIEXPORT void JNICALL Java_ca_logaritm_dezel_core_external_JavaScriptValueExternal_setPropertyAtIndex__JJID
  (JNIEnv *, jclass, jlong, jlong, jint, jdouble);

/*
 * Class:     ca_logaritm_dezel_core_external_JavaScriptValueExternal
 * Method:    setPropertyAtIndex
 * Signature: (JJIZ)V
 */
JNIEXPORT void JNICALL Java_ca_logaritm_dezel_core_external_JavaScriptValueExternal_setPropertyAtIndex__JJIZ
  (JNIEnv *, jclass, jlong, jlong, jint, jboolean);

/*
 * Class:     ca_logaritm_dezel_core_external_JavaScriptValueExternal
 * Method:    getPropertyAtIndex
 * Signature: (JJI)J
 */
JNIEXPORT jlong JNICALL Java_ca_logaritm_dezel_core_external_JavaScriptValueExternal_getPropertyAtIndex
  (JNIEnv *, jclass, jlong, jlong, jint);

/*
 * Class:     ca_logaritm_dezel_core_external_JavaScriptValueExternal
 * Method:    setPropertyWithSymbol
 * Signature: (JJJJ)V
 */
JNIEXPORT void JNICALL Java_ca_logaritm_dezel_core_external_JavaScriptValueExternal_setPropertyWithSymbol
  (JNIEnv *, jclass, jlong, jlong, jlong, jlong);

/*
 * Class:     ca_logaritm_dezel_core_external_JavaScriptValueExternal
 * Method:    getPropertyWithSymbol
 * Signature: (JJJ)J
 */
JNIEXPORT jlong JNICALL Java_ca_logaritm_dezel_core_external_JavaScriptValueExternal_getPropertyWithSymbol
  (JNIEnv *, jclass, jlong, jlong, jlong);

/*
 * Class:     ca_logaritm_dezel_core_external_JavaScriptValueExternal
 * Method:    forEach
 * Signature: (JJLjava/lang/Object;)V
 */
JNIEXPORT void JNICALL Java_ca_logaritm_dezel_core_external_JavaScriptValueExternal_forEach
  (JNIEnv *, jclass, jlong, jlong, jobject);

/*
 * Class:     ca_logaritm_dezel_core_external_JavaScriptValueExternal
 * Method:    forOwn
 * Signature: (JJLjava/lang/Object;)V
 */
JNIEXPORT void JNICALL Java_ca_logaritm_dezel_core_external_JavaScriptValueExternal_forOwn
  (JNIEnv *, jclass, jlong, jlong, jobject);

/*
 * Class:     ca_logaritm_dezel_core_external_JavaScriptValueExternal
 * Method:    setPrototype
 * Signature: (JJJ)V
 */
JNIEXPORT void JNICALL Java_ca_logaritm_dezel_core_external_JavaScriptValueExternal_setPrototype
  (JNIEnv *, jclass, jlong, jlong, jlong);

/*
 * Class:     ca_logaritm_dezel_core_external_JavaScriptValueExternal
 * Method:    getPrototype
 * Signature: (JJ)J
 */
JNIEXPORT jlong JNICALL Java_ca_logaritm_dezel_core_external_JavaScriptValueExternal_getPrototype
  (JNIEnv *, jclass, jlong, jlong);

/*
 * Class:     ca_logaritm_dezel_core_external_JavaScriptValueExternal
 * Method:    setAttribute
 * Signature: (JJILjava/lang/Object;)V
 */
JNIEXPORT void JNICALL Java_ca_logaritm_dezel_core_external_JavaScriptValueExternal_setAttribute__JJILjava_lang_Object_2
  (JNIEnv *, jclass, jlong, jlong, jint, jobject);

/*
 * Class:     ca_logaritm_dezel_core_external_JavaScriptValueExternal
 * Method:    setAttribute
 * Signature: (JILjava/lang/Object;)V
 */
JNIEXPORT void JNICALL Java_ca_logaritm_dezel_core_external_JavaScriptValueExternal_setAttribute__JILjava_lang_Object_2
  (JNIEnv *, jclass, jlong, jint, jobject);

/*
 * Class:     ca_logaritm_dezel_core_external_JavaScriptValueExternal
 * Method:    getAttribute
 * Signature: (JJI)Ljava/lang/Object;
 */
JNIEXPORT jobject JNICALL Java_ca_logaritm_dezel_core_external_JavaScriptValueExternal_getAttribute__JJI
  (JNIEnv *, jclass, jlong, jlong, jint);

/*
 * Class:     ca_logaritm_dezel_core_external_JavaScriptValueExternal
 * Method:    getAttribute
 * Signature: (JI)Ljava/lang/Object;
 */
JNIEXPORT jobject JNICALL Java_ca_logaritm_dezel_core_external_JavaScriptValueExternal_getAttribute__JI
  (JNIEnv *, jclass, jlong, jint);

/*
 * Class:     ca_logaritm_dezel_core_external_JavaScriptValueExternal
 * Method:    delAttribute
 * Signature: (JJI)V
 */
JNIEXPORT void JNICALL Java_ca_logaritm_dezel_core_external_JavaScriptValueExternal_delAttribute__JJI
  (JNIEnv *, jclass, jlong, jlong, jint);

/*
 * Class:     ca_logaritm_dezel_core_external_JavaScriptValueExternal
 * Method:    delAttribute
 * Signature: (JI)V
 */
JNIEXPORT void JNICALL Java_ca_logaritm_dezel_core_external_JavaScriptValueExternal_delAttribute__JI
  (JNIEnv *, jclass, jlong, jint);

/*
 * Class:     ca_logaritm_dezel_core_external_JavaScriptValueExternal
 * Method:    setAssociatedObject
 * Signature: (JJLjava/lang/Object;)V
 */
JNIEXPORT void JNICALL Java_ca_logaritm_dezel_core_external_JavaScriptValueExternal_setAssociatedObject
  (JNIEnv *, jclass, jlong, jlong, jobject);

/*
 * Class:     ca_logaritm_dezel_core_external_JavaScriptValueExternal
 * Method:    getAssociatedObject
 * Signature: (JJ)Ljava/lang/Object;
 */
JNIEXPORT jobject JNICALL Java_ca_logaritm_dezel_core_external_JavaScriptValueExternal_getAssociatedObject__JJ
  (JNIEnv *, jclass, jlong, jlong);

/*
 * Class:     ca_logaritm_dezel_core_external_JavaScriptValueExternal
 * Method:    getAssociatedObject
 * Signature: (J)Ljava/lang/Object;
 */
JNIEXPORT jobject JNICALL Java_ca_logaritm_dezel_core_external_JavaScriptValueExternal_getAssociatedObject__J
  (JNIEnv *, jclass, jlong);

/*
 * Class:     ca_logaritm_dezel_core_external_JavaScriptValueExternal
 * Method:    delAssociatedObject
 * Signature: (JJ)V
 */
JNIEXPORT void JNICALL Java_ca_logaritm_dezel_core_external_JavaScriptValueExternal_delAssociatedObject
  (JNIEnv *, jclass, jlong, jlong);

/*
 * Class:     ca_logaritm_dezel_core_external_JavaScriptValueExternal
 * Method:    setFinalizeHandler
 * Signature: (JJLjava/lang/Object;Ljava/lang/Object;)V
 */
JNIEXPORT void JNICALL Java_ca_logaritm_dezel_core_external_JavaScriptValueExternal_setFinalizeHandler
  (JNIEnv *, jclass, jlong, jlong, jobject, jobject);

/*
 * Class:     ca_logaritm_dezel_core_external_JavaScriptValueExternal
 * Method:    equals
 * Signature: (JJJ)Z
 */
JNIEXPORT jboolean JNICALL Java_ca_logaritm_dezel_core_external_JavaScriptValueExternal_equals__JJJ
  (JNIEnv *, jclass, jlong, jlong, jlong);

/*
 * Class:     ca_logaritm_dezel_core_external_JavaScriptValueExternal
 * Method:    equals
 * Signature: (JJLjava/lang/String;)Z
 */
JNIEXPORT jboolean JNICALL Java_ca_logaritm_dezel_core_external_JavaScriptValueExternal_equals__JJLjava_lang_String_2
  (JNIEnv *, jclass, jlong, jlong, jstring);

/*
 * Class:     ca_logaritm_dezel_core_external_JavaScriptValueExternal
 * Method:    equals
 * Signature: (JJD)Z
 */
JNIEXPORT jboolean JNICALL Java_ca_logaritm_dezel_core_external_JavaScriptValueExternal_equals__JJD
  (JNIEnv *, jclass, jlong, jlong, jdouble);

/*
 * Class:     ca_logaritm_dezel_core_external_JavaScriptValueExternal
 * Method:    equals
 * Signature: (JJZ)Z
 */
JNIEXPORT jboolean JNICALL Java_ca_logaritm_dezel_core_external_JavaScriptValueExternal_equals__JJZ
  (JNIEnv *, jclass, jlong, jlong, jboolean);

/*
 * Class:     ca_logaritm_dezel_core_external_JavaScriptValueExternal
 * Method:    isString
 * Signature: (JJ)Z
 */
JNIEXPORT jboolean JNICALL Java_ca_logaritm_dezel_core_external_JavaScriptValueExternal_isString
  (JNIEnv *, jclass, jlong, jlong);

/*
 * Class:     ca_logaritm_dezel_core_external_JavaScriptValueExternal
 * Method:    isNumber
 * Signature: (JJ)Z
 */
JNIEXPORT jboolean JNICALL Java_ca_logaritm_dezel_core_external_JavaScriptValueExternal_isNumber
  (JNIEnv *, jclass, jlong, jlong);

/*
 * Class:     ca_logaritm_dezel_core_external_JavaScriptValueExternal
 * Method:    isBoolean
 * Signature: (JJ)Z
 */
JNIEXPORT jboolean JNICALL Java_ca_logaritm_dezel_core_external_JavaScriptValueExternal_isBoolean
  (JNIEnv *, jclass, jlong, jlong);

/*
 * Class:     ca_logaritm_dezel_core_external_JavaScriptValueExternal
 * Method:    isFunction
 * Signature: (JJ)Z
 */
JNIEXPORT jboolean JNICALL Java_ca_logaritm_dezel_core_external_JavaScriptValueExternal_isFunction
  (JNIEnv *, jclass, jlong, jlong);

/*
 * Class:     ca_logaritm_dezel_core_external_JavaScriptValueExternal
 * Method:    isObject
 * Signature: (JJ)Z
 */
JNIEXPORT jboolean JNICALL Java_ca_logaritm_dezel_core_external_JavaScriptValueExternal_isObject
  (JNIEnv *, jclass, jlong, jlong);

/*
 * Class:     ca_logaritm_dezel_core_external_JavaScriptValueExternal
 * Method:    isArray
 * Signature: (JJ)Z
 */
JNIEXPORT jboolean JNICALL Java_ca_logaritm_dezel_core_external_JavaScriptValueExternal_isArray
  (JNIEnv *, jclass, jlong, jlong);

/*
 * Class:     ca_logaritm_dezel_core_external_JavaScriptValueExternal
 * Method:    isUndefined
 * Signature: (JJ)Z
 */
JNIEXPORT jboolean JNICALL Java_ca_logaritm_dezel_core_external_JavaScriptValueExternal_isUndefined
  (JNIEnv *, jclass, jlong, jlong);

/*
 * Class:     ca_logaritm_dezel_core_external_JavaScriptValueExternal
 * Method:    isNull
 * Signature: (JJ)Z
 */
JNIEXPORT jboolean JNICALL Java_ca_logaritm_dezel_core_external_JavaScriptValueExternal_isNull
  (JNIEnv *, jclass, jlong, jlong);

/*
 * Class:     ca_logaritm_dezel_core_external_JavaScriptValueExternal
 * Method:    getType
 * Signature: (JJ)I
 */
JNIEXPORT jint JNICALL Java_ca_logaritm_dezel_core_external_JavaScriptValueExternal_getType
  (JNIEnv *, jclass, jlong, jlong);

/*
 * Class:     ca_logaritm_dezel_core_external_JavaScriptValueExternal
 * Method:    toString
 * Signature: (JJ)Ljava/lang/String;
 */
JNIEXPORT jstring JNICALL Java_ca_logaritm_dezel_core_external_JavaScriptValueExternal_toString
  (JNIEnv *, jclass, jlong, jlong);

/*
 * Class:     ca_logaritm_dezel_core_external_JavaScriptValueExternal
 * Method:    toNumber
 * Signature: (JJ)D
 */
JNIEXPORT jdouble JNICALL Java_ca_logaritm_dezel_core_external_JavaScriptValueExternal_toNumber
  (JNIEnv *, jclass, jlong, jlong);

/*
 * Class:     ca_logaritm_dezel_core_external_JavaScriptValueExternal
 * Method:    toBoolean
 * Signature: (JJ)Z
 */
JNIEXPORT jboolean JNICALL Java_ca_logaritm_dezel_core_external_JavaScriptValueExternal_toBoolean
  (JNIEnv *, jclass, jlong, jlong);

#ifdef __cplusplus
}
#endif
#endif
