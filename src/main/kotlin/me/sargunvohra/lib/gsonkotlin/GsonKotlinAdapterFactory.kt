package me.sargunvohra.lib.gsonkotlin

import com.google.gson.Gson
import com.google.gson.JsonParseException
import com.google.gson.TypeAdapter
import com.google.gson.TypeAdapterFactory
import com.google.gson.reflect.TypeToken
import com.google.gson.stream.JsonReader
import com.google.gson.stream.JsonWriter
import kotlin.reflect.KClass
import kotlin.reflect.declaredMemberProperties
import kotlin.reflect.jvm.isAccessible

class GsonKotlinAdapterFactory(
        val checkNulls: Boolean = true
) : TypeAdapterFactory {

    private val Class<*>.isKotlinClass: Boolean
        get() = declaredAnnotations.find { it.annotationClass.java.name == "kotlin.Metadata" } != null

    override fun <T : Any> create(gson: Gson, type: TypeToken<T>): TypeAdapter<T>? {
        if (!type.rawType.isKotlinClass)
            return null

        val kClass = (type.rawType as Class<*>).kotlin

        @Suppress("UNCHECKED_CAST")
        return Adapter(this, gson, type, kClass as KClass<T>, checkNulls)
    }

    class Adapter<T : Any>(
            factory: GsonKotlinAdapterFactory,
            gson: Gson,
            type: TypeToken<T>,
            private val kClass: KClass<T>,
            private val checkNulls: Boolean
    ) : TypeAdapter<T>() {

        val delegate: TypeAdapter<T> = gson.getDelegateAdapter(factory, type)

        override fun write(out: JsonWriter, value: T) {
            delegate.write(out, value)
        }

        override fun read(input: JsonReader): T {
            return delegate.read(input).apply {
                if (checkNulls) doNullCheck(this)
            }
        }

        private fun doNullCheck(value: T) {
            kClass.declaredMemberProperties.forEach { prop ->
                prop.isAccessible = true
                if (!prop.returnType.isMarkedNullable && prop(value) == null)
                    throw JsonParseException(
                            "Field: '${prop.name}' in Class '${prop.returnType.java.typeName}' is not marked nullable but found null value"
                    )
            }
        }
    }
}