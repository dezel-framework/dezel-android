#ifndef JavaScriptClassStaticGetterWrapper_h
#define JavaScriptClassStaticGetterWrapper_h

#include <JavaScriptValue.h>
#include <JavaScriptContext.h>
#include "jni_init.h"
#include "jni_module_core.h"

/**
 * @struct JavaScriptClassStaticGetterWrapper
 * @since 0.1.0
 */
struct JavaScriptClassStaticGetterWrapper {
	JNIEnv* env;
	jclass cls;
	jobject ctx;
	jmethodID callback;
	JSObjectRef function;
};

/**
 * @typedef JavaScriptClassStaticGetterWrapperRef
 * @since 0.1.0
 */
typedef struct JavaScriptClassStaticGetterWrapper* JavaScriptClassStaticGetterWrapperRef;

/**
 * @function JavaScriptClassStaticGetterWrapperCreate
 * @since 0.1.0
 */
JavaScriptClassStaticGetterWrapperRef JavaScriptClassStaticGetterWrapperCreate(JNIEnv* env, JSContextRef context, const char* name, const char* fqmn, jclass cls, jobject ctx);

#endif
