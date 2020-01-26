package ca.logaritm.dezel.core

import ca.logaritm.dezel.BuildConfig
import ca.logaritm.dezel.core.external.JavaScriptContextExternal

/**
 * @class JavaScriptContext
 * @since 0.1.0
 */
open class JavaScriptContext {

	//--------------------------------------------------------------------------
	// Static
	//--------------------------------------------------------------------------

	companion object {
		init {
			System.loadLibrary("jsc")
			System.loadLibrary("dezel")
		}
	}

	//--------------------------------------------------------------------------
	// Constants
	//--------------------------------------------------------------------------

	/**
	 * The null value.
	 * @const Null
	 * @since 0.1.0
	 */
	public val Null: JavaScriptValue by lazy {
		this.createNull()
	}

	/**
	 * The undefined value.
	 * @const Undefined
	 * @since 0.1.0
	 */
	public val Undefined: JavaScriptValue by lazy {
		this.createUndefined()
	}

	/**
	 * The true value.
	 * @const True
	 * @since 0.1.0
	 */
	public val True: JavaScriptValue by lazy {
		this.createBoolean(true)
	}

	/**
	 * The false value.
	 * @const False
	 * @since 0.1.0
	 */
	public val False: JavaScriptValue by lazy {
		this.createBoolean(false)
	}

	/**
	 * @const native
	 * @since 0.1.0
	 * @hidden
	 */
	public val native: JavaScriptValue by lazy {
		this.createSymbol("native")
	}

	//--------------------------------------------------------------------------
	// Properties
	//--------------------------------------------------------------------------

	/**
	 * The context's handle.
	 * @property handle
	 * @since 0.1.0
	 */
	public var handle: Long = 0
		private set

	/**
	 * The context's global object.
	 * @property global
	 * @since 0.1.0
	 */
	public var global: JavaScriptValue
		private set

	/**
	 * The context's bridged classes.
	 * @property classes
	 * @since 0.1.0
	 */
	public var classes: MutableMap<String, JavaScriptValue> = mutableMapOf()
		internal set

	/**
	 * The context's bridged objects.
	 * @property objects
	 * @since 0.1.0
	 */
	public var objects: MutableMap<String, JavaScriptValue> = mutableMapOf()
		internal set

	//--------------------------------------------------------------------------
	// Methods
	//--------------------------------------------------------------------------

	/**
	 * Initializes the context.
	 * @constructor
	 * @since 0.1.0
	 */
	init {

		this.handle = JavaScriptContextExternal.create(BuildConfig.APPLICATION_ID)

		this.global = JavaScriptValue.create(this, JavaScriptContextExternal.getGlobalObject(this.handle))
		this.global.defineProperty("global", value = this.global, getter = null, setter = null, writable = false, enumerable = false, configurable = false)
		this.global.defineProperty("window", value = this.global, getter = null, setter = null, writable = false, enumerable = false, configurable = false)

		/**
		 * Creates a native symbol that will be used to store native object
		 * on standard object.
		 */

		this.global.defineProperty("\$native", value = this.native, getter = null, setter = null, writable = false, enumerable = false, configurable = false)

		JavaScriptContextReference.register(this)
	}

	/**
	 * Registers multiple bridged context classes.
	 * @method registerClasses
	 * @since 0.1.0
	 */
	open fun registerClasses(classes: Map<String, Class<*>>) {
		classes.forEach {
			this.registerClass(it.key, it.value)
		}
	}

	/**
	 * Registers multiple bridged context objects.
	 * @method registerObjects
	 * @since 0.1.0
	 */
	open fun registerObjects(objects: Map<String, Class<*>>) {
		objects.forEach {
			this.registerObject(it.key, it.value)
		}
	}

	/**
	 * Registers a bridged context class.
	 * @method registerClass
	 * @since 0.1.0
	 */
	open fun registerClass(uid: String, type: Class<*>) {
		this.classes[uid] = this.createClass(type)
	}

	/**
	 * Register a bridged context object.
	 * @method registerObject
	 * @since 0.1.0
	 */
	open fun registerObject(uid: String, type: Class<*>) {
		this.objects[uid] = this.createObject(type)
	}

	/**
	 * Disposes the context.
	 * @method dispose
	 * @since 0.1.0
	 */
	open fun dispose() {

		this.objects.clear()
		this.classes.clear()
		this.global.dispose()

		this.garbageCollect()

		JavaScriptContextExternal.delete(this.handle)
	}

	/**
	 * Creates a null JavaScript value in the context.
	 * @method createNull
	 * @since 0.1.0
	 */
	open fun createNull(): JavaScriptValue {
		return JavaScriptValue.createNull(this)
	}

	/**
	 * Creates an undefined JavaScript value in the context.
	 * @method createUndefined
	 * @since 0.1.0
	 */
	open fun createUndefined(): JavaScriptValue {
		return JavaScriptValue.createUndefined(this)
	}

	/**
	 * Creates a string JavaScript value in the context.
	 * @method createString
	 * @since 0.1.0
	 */
	open fun createString(value: String): JavaScriptValue {
		return JavaScriptValue.createString(this, value)
	}

	/**
	 * Creates a number JavaScript value in the context.
	 * @method createNumber
	 * @since 0.1.0
	 */
	open fun createNumber(value: Double): JavaScriptValue {
		return JavaScriptValue.createNumber(this, value)
	}

	/**
	 * Creates a number JavaScript value in the context.
	 * @method createNumber
	 * @since 0.1.0
	 */
	open fun createNumber(value: Float): JavaScriptValue {
		return JavaScriptValue.createNumber(this, value.toDouble())
	}

	/**
	 * Creates a number JavaScript value in the context.
	 * @method createNumber
	 * @since 0.1.0
	 */
	open fun createNumber(value: Int): JavaScriptValue {
		return JavaScriptValue.createNumber(this, value.toDouble())
	}

	/**
	 * Creates a boolean JavaScript value in the context.
	 * @method createBoolean
	 * @since 0.1.0
	 */
	open fun createBoolean(value: Boolean): JavaScriptValue {
		return JavaScriptValue.createBoolean(this, value)
	}

	/**
	 * Creates a symbol JavaScript value in the context.
	 * @method createSymbol
	 * @since 0.1.0
	 */
	open fun createSymbol(value: String): JavaScriptValue {
		return JavaScriptValue.createSymbol(this, value)
	}

	/**
	 * Creates an empty object in the context.
	 * @method createEmptyObject
	 * @since 0.1.0
	 */
	open fun createEmptyObject(): JavaScriptValue {
		return JavaScriptValue.createEmptyObject(this)
	}

	/**
	 * Creates an empty array in the context.
	 * @method createEmptyArray
	 * @since 0.1.0
	 */
	open fun createEmptyArray(): JavaScriptValue {
		return JavaScriptValue.createEmptyArray(this)
	}

	/**
	 * Creates a function in the context.
	 * @method createFunction
	 * @since 0.1.0
	 */
	open fun createFunction(value: JavaScriptFunctionHandler): JavaScriptValue {
		return JavaScriptValue.createFunction(this, value)
	}

	/**
	 * Creates a function in the context.
	 * @method createFunction
	 * @since 0.1.0
	 */
	open fun createFunction(name: String, value: JavaScriptFunctionHandler): JavaScriptValue {
		return JavaScriptValue.createFunction(this, value, name)
	}

	/**
	 * Creates a bridged object in the context.
	 * @method createObject
	 * @since 0.1.0
	 */
	open fun createObject(template: Class<*>): JavaScriptValue {
		return JavaScriptValue.createObject(this, template)
	}

	/**
	 * Creates a bridged class in the context.
	 * @method createClass
	 * @since 0.1.0
	 */
	open fun createClass(template: Class<*>): JavaScriptValue {
		return JavaScriptValue.createClass(this, template)
	}

	/**
	 * Creates a return value.
	 * @method createReturnValue
	 * @since 0.1.0
	 */
	open fun createReturnValue(): JavaScriptValue {
		return JavaScriptValue.createUndefined(this)
	}

	/**
	 * Evaluates JavaScript code in the context.
	 * @method evaluate
	 * @since 0.1.0
	 */
	open fun evaluate(source: String) {
		JavaScriptContextExternal.evaluate(this.handle, source)
	}

	/**
	 * Evaluates JavaScript code in the context.
	 * @method evaluate
	 * @since 0.1.0
	 */
	open fun evaluate(source: String, url: String) {
		JavaScriptContextExternal.evaluate(this.handle, source, url)
	}

	/**
	 * Assigns a custom attribute on the context.
	 * @method attribute
	 * @since 0.1.0
	 */
	open fun attribute(key: Any, value: Any?) {
		val hash = key.hashCode()
		JavaScriptContextExternal.delAttribute(this.handle, hash)
		JavaScriptContextExternal.setAttribute(this.handle, hash, value)
	}

	/**
	 * Returns a custom attribute from the context.
	 * @method attribute
	 * @since 0.1.0
	 */
	open fun attribute(key: Any): Any? {
		return JavaScriptContextExternal.getAttribute(this.handle, key.hashCode())
	}

	/**
	 * Assigns the context's error handler.
	 * @method handleError
	 * @since 0.1.0
	 */
	open fun handleError(callback: JavaScriptExceptionHandler) {
		JavaScriptContextExternal.setExceptionCallback(this.handle, JavaScriptExceptionWrapper(this, callback), this)
	}

	/**
	 * Perform a garbage collection on this context.
	 * @method garbageCollect
	 * @since 0.1.0
	 */
	open fun garbageCollect() {
		JavaScriptContextExternal.garbageCollect(this.handle)
	}
}
