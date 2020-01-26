package ca.logaritm.dezel.core

import ca.logaritm.dezel.core.external.JavaScriptValueExternal

/**
 * @class JavaScriptValue
 * @since 0.1.0
 */
open class JavaScriptValue(context: JavaScriptContext) {

	//--------------------------------------------------------------------------
	// Static
	//--------------------------------------------------------------------------

	companion object {

		/**
		 * @method createNull
		 * @since 0.1.0
		 * @hidden
		 */
		internal fun createNull(context: JavaScriptContext): JavaScriptValue {
			return create(context, JavaScriptValueExternal.createNull(context.handle))
		}

		/**
		 * @method createUndefined
		 * @since 0.1.0
		 * @hidden
		 */
		internal fun createUndefined(context: JavaScriptContext): JavaScriptValue {
			return create(context, JavaScriptValueExternal.createUndefined(context.handle))
		}

		/**
		 * @method createString
		 * @since 0.1.0
		 * @hidden
		 */
		internal fun createString(context: JavaScriptContext, value: String): JavaScriptValue {
			return create(context, JavaScriptValueExternal.createString(context.handle, value))
		}

		/**
		 * @method createNumber
		 * @since 0.1.0
		 * @hidden
		 */
		internal fun createNumber(context: JavaScriptContext, value: Double): JavaScriptValue {
			return create(context, JavaScriptValueExternal.createNumber(context.handle, value))
		}

		/**
		 * @method createBoolean
		 * @since 0.1.0
		 * @hidden
		 */
		internal fun createBoolean(context: JavaScriptContext, value: Boolean): JavaScriptValue {
			return create(context, JavaScriptValueExternal.createBoolean(context.handle, value))
		}

		/**
		 * @method createSymbol
		 * @since 0.1.0
		 * @hidden
		 */
		internal fun createSymbol(context: JavaScriptContext, value: String): JavaScriptValue {
			return create(context, JavaScriptValueExternal.createSymbol(context.handle, value))
		}

		/**
		 * @method createEmptyObject
		 * @since 0.1.0
		 * @hidden
		 */
		internal fun createEmptyObject(context: JavaScriptContext): JavaScriptValue {
			return create(context, JavaScriptValueExternal.createEmtpyObject(context.handle))
		}

		/**
		 * @method createEmptyArray
		 * @since 0.1.0
		 * @hidden
		 */
		internal fun createEmptyArray(context: JavaScriptContext): JavaScriptValue {
			return create(context, JavaScriptValueExternal.createEmptyArray(context.handle))
		}

		/**
		 * @method createFunction
		 * @since 0.1.0
		 * @hidden
		 */
		internal fun createFunction(context: JavaScriptContext, handler: JavaScriptFunctionHandler): JavaScriptValue {
			return create(context, JavaScriptValueExternal.createFunction(context.handle, JavaScriptFunctionWrapper(context, handler), null, context))
		}

		/**
		 * @method createFunction
		 * @since 0.1.0
		 * @hidden
		 */
		internal fun createFunction(context: JavaScriptContext, handler: JavaScriptFunctionHandler, name: String): JavaScriptValue {
			return create(context, JavaScriptValueExternal.createFunction(context.handle, JavaScriptFunctionWrapper(context, handler), name, context))
		}

		/**
		 * @method createObject
		 * @since 0.1.0
		 * @hidden
		 */
		internal fun createObject(context: JavaScriptContext, template: Class<*>): JavaScriptValue {
			return JavaScriptObjectBuilder.build(context, template)
		}

		/**
		 * @method createClass
		 * @since 0.1.0
		 * @hidden
		 */
		internal fun createClass(context: JavaScriptContext, template: Class<*>): JavaScriptValue {
			return JavaScriptClassBuilder.build(context, template)
		}

		/**
		 * @method create
		 * @since 0.1.0
		 * @hidden
		 */
		internal fun create(context: JavaScriptContext, handle: Long, bridge: Boolean = false, protect: Boolean = true): JavaScriptValue {
			return JavaScriptValue(context, handle, bridge, protect)
		}
	}

	//--------------------------------------------------------------------------
	// Properties
	//--------------------------------------------------------------------------

	/**
	 * The value's context.
	 * @property context
	 * @since 0.1.0
	 */
	public var context: JavaScriptContext
		private set

	/**
	 * The value's handle.
	 * @property handle
	 * @since 0.1.0
	 */
	public var handle: Long = 0L
		private set

	/**
	 * @property protected
	 * @since 0.1.0
	 * @hidden
	 */
	private var protected: Int = 0

	//--------------------------------------------------------------------------
	// Properties - Types
	//--------------------------------------------------------------------------

	/**
	 * Whether the value is undefined.
	 * @property isUndefined
	 * @since 0.1.0
	 */
	public val isUndefined: Boolean
		get() = JavaScriptValueExternal.getType(this.context.handle, this.handle) == kJavaScriptValueTypeUndefined

	/**
	 * Whether the value is null.
	 * @property isNull
	 * @since 0.1.0
	 */
	public val isNull: Boolean
		get() = JavaScriptValueExternal.getType(this.context.handle, this.handle) == kJavaScriptValueTypeNull

	/**
	 * Whether the value is a string.
	 * @property isString
	 * @since 0.1.0
	 */
	public val isString: Boolean
		get() = JavaScriptValueExternal.getType(this.context.handle, this.handle) == kJavaScriptValueTypeString

	/**
	 * Whether the value is a number.
	 * @property isNumber
	 * @since 0.1.0
	 */
	public val isNumber: Boolean
		get() = JavaScriptValueExternal.getType(this.context.handle, this.handle) == kJavaScriptValueTypeNumber

	/**
	 * Whether the value is a boolean.
	 * @property isBoolean
	 * @since 0.1.0
	 */
	public val isBoolean: Boolean
		get() = JavaScriptValueExternal.getType(this.context.handle, this.handle) == kJavaScriptValueTypeBoolean

	/**
	 * Whether the value is an object.
	 * @property isObject
	 * @since 0.1.0
	 */
	public val isObject: Boolean
		get() = JavaScriptValueExternal.getType(this.context.handle, this.handle) == kJavaScriptValueTypeObject

	/**
	 * Whether the value is an array.
	 * @property isArray
	 * @since 0.1.0
	 */
	public val isArray: Boolean
		get() = JavaScriptValueExternal.getType(this.context.handle, this.handle) == kJavaScriptValueTypeArray

	/**
	 * Whether the value is a function.
	 * @property isFunction
	 * @since 0.1.0
	 */
	public val isFunction: Boolean
		get() = JavaScriptValueExternal.getType(this.context.handle, this.handle) == kJavaScriptValueTypeFunction

	/**
	 * Returns the value as a string.
	 * @property string
	 * @since 0.1.0
	 */
	public val string: String by lazy {
		JavaScriptValueExternal.toString(context.handle, this.handle)
	}

	/**
	 * Returns the value as a number.
	 * @property number
	 * @since 0.1.0
	 */
	public val number: Double by lazy {
		JavaScriptValueExternal.toNumber(context.handle, this.handle)
	}

	/**
	 * Returns the value as a boolean.
	 * @property boolean
	 * @since 0.1.0
	 */
	public val boolean: Boolean by lazy {
		JavaScriptValueExternal.toBoolean(context.handle, this.handle)
	}

	//--------------------------------------------------------------------------
	// Methods
	//--------------------------------------------------------------------------

	/**
	 * @constructor
	 * @since 0.1.0
	 * @hidden
	 */
	init {
		this.context = context
	}

	/**
	 * @constructor
	 * @since 0.1.0
	 * @hidden
	 */
	internal constructor(context: JavaScriptContext, handle: Long, bridge: Boolean = false, protect: Boolean = true) : this(context) {
		this.reset(handle, bridge, protect)
	}

	/**
	 * Protects the value.
	 * @method protect
	 * @since 0.1.0
	 */
	open fun protect() {

		this.protected += 1

		if (this.protected == 1) {
			JavaScriptValueExternal.protect(this.context.handle, this.handle)
			JavaScriptValueReference.protect(this)
		}

		this.onProtectValue()
	}

	/**
	 * Unprotects the value.
	 * @method unprotect
	 * @since 0.1.0
	 */
	open fun unprotect() {

		if (this.handle == 0L) {
			return
		}

		if (this.protected == 1) {
			JavaScriptValueExternal.unprotect(this.context.handle, this.handle)
			JavaScriptValueReference.unprotect(this)
		}

		if (this.protected > 0) {
			this.protected -= 1
		}

		this.onUnprotectValue()
	}

	/**
	 * Disposes the value.
	 * @method dispose
	 * @since 0.1.0
	 */
	open fun dispose() {

		if (this.handle == 0L) {
			return
		}

		this.unprotect()

		this.handle = 0L
	}

	/**
	 * Casts the value as a specific type.
	 * @method cast
	 * @since 0.1.0
	 */
	open fun <T> cast(type: Class<T>): T? {

		try {

			val value = JavaScriptValueExternal.getAssociatedObject(this.context.handle, this.handle)
			if (value == null) {
				return null
			}

			return type.cast(value)

		} catch (e: ClassCastException) {
			return null
		}
	}

	/**
	 * Calls this value as a function.
	 * @method call
	 * @since 0.1.0
	 */
	open fun call() {
		JavaScriptValueExternal.call(this.context.handle, this.handle, 0, null, 0, null)
	}

	/**
	 * Calls this value as a function.
	 * @method call
	 * @since 0.1.0
	 */
	open fun call(arguments: JavaScriptArguments?, target: JavaScriptValue?, result: JavaScriptValue? = null) {
		JavaScriptValueExternal.call(this.context.handle, this.handle, toJs(target, this.context), toArgv(arguments, this.context), toArgc(arguments), result)
	}

	/**
	 * Calls a method from this value.
	 * @method callMethod
	 * @since 0.1.0
	 */
	open fun callMethod(method: String) {
		JavaScriptValueExternal.callMethod(this.context.handle, this.handle, method, null, 0, null)
	}

	/**
	 * Calls a method from this value.
	 * @method callMethod
	 * @since 0.1.0
	 */
	open fun callMethod(method: String, arguments: JavaScriptArguments?, result: JavaScriptValue? = null) {
		JavaScriptValueExternal.callMethod(this.context.handle, this.handle, method, toArgv(arguments, context), toArgc(arguments), result)
	}

	/**
	 * Calls this value as a constructor.
	 * @method construct
	 * @since 0.1.0
	 */
	open fun construct() {
		JavaScriptValueExternal.construct(this.context.handle, this.handle, null, 0, null)
	}

	/**
	 * Calls this value as a constructor.
	 * @method construct
	 * @since 0.1.0
	 */
	open fun construct(arguments: JavaScriptArguments?, result: JavaScriptValue? = null) {
		JavaScriptValueExternal.construct(this.context.handle, this.handle, toArgv(arguments, context), toArgc(arguments), result)
	}

	/**
	 * Defines a property on the value.
	 * @method defineProperty
	 * @since 0.1.0
	 */
	open fun defineProperty(property: String, value: JavaScriptValue?, getter: JavaScriptGetterHandler? = null, setter: JavaScriptSetterHandler? = null, writable: Boolean = true, enumerable: Boolean = true, configurable: Boolean = true) {

		if (value != null) {
			JavaScriptValueExternal.defineProperty(this.context.handle, this.handle, property, toJs(value, this.context), null, null, writable, enumerable, configurable, this.context)
			return
		}

		var get: JavaScriptGetterWrapper? = null
		var set: JavaScriptSetterWrapper? = null

		if (getter != null) get = JavaScriptGetterWrapper(this.context, getter)
		if (setter != null) set = JavaScriptSetterWrapper(this.context, setter)

		JavaScriptValueExternal.defineProperty(this.context.handle, this.handle, property, 0, get, set, writable, enumerable, configurable, this.context)
	}

	/**
	 * Assigns the value of a property of the value.
	 * @method property
	 * @since 0.1.0
	 */
	open fun property(name: String, property: JavaScriptProperty) {
		JavaScriptValueExternal.setProperty(this.context.handle, this.handle, name, toJs(property, this.context))
	}

	/**
	 * Assigns the value of a property of the value.
	 * @method property
	 * @since 0.1.0
	 */
	open fun property(name: String, value: JavaScriptValue?) {
		JavaScriptValueExternal.setProperty(this.context.handle, this.handle, name, toJs(value, this.context))
	}

	/**
	 * Assigns the value of a property of the value.
	 * @method property
	 * @since 0.1.0
	 */
	open fun property(name: String, string: String) {
		JavaScriptValueExternal.setProperty(this.context.handle, this.handle, name, string)
	}

	/**
	 * Assigns the value of a property of the value.
	 * @method property
	 * @since 0.1.0
	 */
	open fun property(name: String, number: Double) {
		JavaScriptValueExternal.setProperty(this.context.handle, this.handle, name, number)
	}

	/**
	 * Assigns the value of a property of the value.
	 * @method property
	 * @since 0.1.0
	 */
	open fun property(name: String, number: Float) {
		JavaScriptValueExternal.setProperty(this.context.handle, this.handle, name, number.toDouble())
	}

	/**
	 * Assigns the value of a property of the value.
	 * @method property
	 * @since 0.1.0
	 */
	open fun property(name: String, number: Int) {
		JavaScriptValueExternal.setProperty(this.context.handle, this.handle, name, number.toDouble())
	}

	/**
	 * Assigns the value of a property of the value.
	 * @method property
	 * @since 0.1.0
	 */
	open fun property(name: String, boolean: Boolean) {
		JavaScriptValueExternal.setProperty(this.context.handle, handle, name, boolean)
	}

	/**
	 * Returns the value of a property from this value.
	 * @method property
	 * @since 0.1.0
	 */
	open fun property(name: String): JavaScriptValue {
		return create(this.context, JavaScriptValueExternal.getProperty(this.context.handle, this.handle, name), bridge = true)
	}

	/**
	 * Assigns the value of an indexed property from this value.
	 * @method property
	 * @since 0.1.0
	 */
	open fun property(index: Int, value: JavaScriptValue) {
		JavaScriptValueExternal.setPropertyAtIndex(this.context.handle, this.handle, index, value.handle)
	}

	/**
	 * Assigns the value of an indexed property from this value.
	 * @method property
	 * @since 0.1.0
	 */
	open fun property(index: Int, string: String) {
		JavaScriptValueExternal.setPropertyAtIndex(this.context.handle, this.handle, index, string)
	}

	/**
	 * Assigns the value of an indexed property from this value.
	 * @method property
	 * @since 0.1.0
	 */
	open fun property(index: Int, number: Double) {
		JavaScriptValueExternal.setPropertyAtIndex(this.context.handle, this.handle, index, number)
	}

	/**
	 * Assigns the value of an indexed property from this value.
	 * @method property
	 * @since 0.1.0
	 */
	open fun property(index: Int, number: Float) {
		JavaScriptValueExternal.setPropertyAtIndex(this.context.handle, this.handle, index, number.toDouble())
	}

	/**
	 * Assigns the value of an indexed property from this value.
	 * @method property
	 * @since 0.1.0
	 */
	open fun property(index: Int, number: Int) {
		JavaScriptValueExternal.setPropertyAtIndex(this.context.handle, this.handle, index, number.toDouble())
	}

	/**
	 * Assigns the value of an indexed property from this value.
	 * @method property
	 * @since 0.1.0
	 */
	open fun property(index: Int, boolean: Boolean) {
		JavaScriptValueExternal.setPropertyAtIndex(this.context.handle, this.handle, index, boolean)
	}

	/**
	 * Returns the value of a property from this value using a symbol.
	 * @method property
	 * @since 0.1.0
	 */
	open fun property(index: Int): JavaScriptValue {
		return create(context, JavaScriptValueExternal.getPropertyAtIndex(this.context.handle, this.handle, index), bridge = true)
	}

	/**
	 * Assigns the value of a property from this value using a symbol.
	 * @method property
	 * @since 0.1.0
	 */
	open fun property(symbol: JavaScriptValue, value: JavaScriptValue?) {
		JavaScriptValueExternal.setPropertyWithSymbol(this.context.handle, this.handle, toJs(symbol, this.context), toJs(value, this.context))
	}

	/**
	 * Returns the value of a property from this value using a symbol.
	 * @method property
	 * @since 0.1.0
	 */
	open fun property(symbol: JavaScriptValue): JavaScriptValue {
		return create(this.context, JavaScriptValueExternal.getPropertyWithSymbol(this.context.handle, this.handle, toJs(symbol, this.context)), bridge = true)
	}

	/**
	 * Executes the callback on each indexed values from this value.
	 * @method forEach
	 * @since 0.1.0
	 */
	open fun forEach(handler: JavaScriptForEachHandler) {
		JavaScriptValueExternal.forEach(this.context.handle, this.handle, JavaScriptValueForEachWrapper(this.context, handler))
	}

	/**
	 * Executes the callback on each properties from this value.
	 * @method forOwn
	 * @since 0.1.0
	 */
	open fun forOwn(handler: JavaScriptForOwnHandler) {
		JavaScriptValueExternal.forOwn(this.context.handle, this.handle, JavaScriptValueForOwnWrapper(this.context, handler))
	}

	/**
	 * Assigns the prototype of this value.
	 * @method prototype
	 * @since 0.1.0
	 */
	open fun prototype(prototype: JavaScriptValue) {
		JavaScriptValueExternal.setPrototype(this.context.handle, this.handle, toJs(prototype, this.context))
	}

	/**
	 * Returns the prototype of this value.
	 * @method prototype
	 * @since 0.1.0
	 */
	open fun prototype(): JavaScriptValue {
		return create(this.context, JavaScriptValueExternal.getPrototype(this.context.handle, this.handle))
	}

	/**
	 * Whether the value is equal to another value.
	 * @method equals
	 * @since 0.1.0
	 */
	open fun equals(value: JavaScriptValue): Boolean {
		return JavaScriptValueExternal.equals(this.context.handle, this.handle, toJs(value, this.context))
	}

	/**
	 * Whether the value is equal to a string.
	 * @method equals
	 * @since 0.1.0
	 */
	open fun equals(string: String): Boolean {
		return JavaScriptValueExternal.equals(this.context.handle, this.handle, string)
	}

	/**
	 * Whether the value is equal to a number.
	 * @method equals
	 * @since 0.1.0
	 */
	open fun equals(number: Double): Boolean {
		return JavaScriptValueExternal.equals(this.context.handle, this.handle, number)
	}

	/**
	 * Whether the value is equal to a boolean.
	 * @method equals
	 * @since 0.1.0
	 */
	open fun equals(boolean: Boolean): Boolean {
		return JavaScriptValueExternal.equals(this.context.handle, this.handle, boolean)
	}

	/**
	 * Called when the value is protected.
	 * @method didProtectValue
	 * @since 0.1.0
	 */
	open fun onProtectValue() {

	}

	/**
	 * Called when the value is unprotected.
	 * @method didUnprotectValue
	 * @since 0.1.0
	 */
	open fun onUnprotectValue() {

	}

	/**
	 * Called when the value is reset.
	 * @method didReset
	 * @since 0.1.0
	 */
	open fun onResetValue() {

	}

	//--------------------------------------------------------------------------
	// Internal API
	//--------------------------------------------------------------------------

	/**
	 * @method toHandle
	 * @since 0.1.0
	 * @hidden
	 */
	open fun toHandle(context: JavaScriptContext): Long {
		return this.handle
	}

	//--------------------------------------------------------------------------
	// Private API
	//--------------------------------------------------------------------------

	/**
	 * @method reset
	 * @since 0.1.0
	 * @hidden
	 */
	public fun reset(handle: Long, bridge: Boolean = false, protect: Boolean = true) {

		this.unprotect()

		var handle = handle

		if (bridge) {

			/*
			 * Some of the values specified here are wrappers around a native
			 * object. These objects use a symbol to store their native object
			 * and in most cases this is the value that we actually want.
			 */

			if (JavaScriptValueExternal.isObject(this.context.handle, handle)) {
				val native = JavaScriptValueExternal.getPropertyWithSymbol(this.context.handle, handle, this.context.native.handle)
				if (native != 0L && JavaScriptValueExternal.isObject(this.context.handle, native)) {
					handle = native
				}
			}
		}

		this.handle = handle

		if (protect) {
			this.protect()
		}

		this.onResetValue()
	}
}
