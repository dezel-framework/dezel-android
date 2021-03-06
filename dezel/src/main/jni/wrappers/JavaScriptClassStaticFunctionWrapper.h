#ifndef JavaScriptClassStaticFunctionWrapper_h
#define JavaScriptClassStaticFunctionWrapper_h

#include <JavaScriptValue.h>
#include <JavaScriptContext.h>
#include "jni_init.h"
#include "jni_module_core.h"

/**
 * @struct JavaScriptClassStaticFunctionWrapper
 * @since 0.1.0
 */
struct JavaScriptClassStaticFunctionWrapper {
	JNIEnv* env;
	jobject ctx;
	jclass cls;
	jmethodID callback;
	JSObjectRef function;
};

/**
 * @typedef JavaScriptClassStaticFunctionWrapperRef
 * @since 0.1.0
 */
typedef struct JavaScriptClassStaticFunctionWrapper* JavaScriptClassStaticFunctionWrapperRef;

/**
 * @function JavaScriptClassStaticFunctionWrapperCreate
 * @since 0.1.0
 */
JavaScriptClassStaticFunctionWrapperRef JavaScriptClassStaticFunctionWrapperCreate(JNIEnv* env, JSContextRef context, const char* name, const char* fqmn, jclass cls, jobject ctx);

#endif
