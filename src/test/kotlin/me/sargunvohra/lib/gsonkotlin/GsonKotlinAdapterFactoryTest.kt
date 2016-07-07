package me.sargunvohra.lib.gsonkotlin

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.JsonParseException
import org.junit.Test
import kotlin.test.assertEquals

class GsonKotlinAdapterFactoryTest {

    val withNull = KFoo("hello", null)

    val withoutNull = KFoo("hello", "world")

    val withNullJson = """{"x":"hello","y":null}"""

    val withoutNullJson = """{"x":"hello","y":"goodbye"}"""

    val allNullJson ="""{"x":null,"y":null}"""

    val gson = Gson()

    val kson = GsonBuilder().apply {
        registerTypeAdapterFactory(GsonKotlinAdapterFactory())
    }.create()!!

    @Test
    fun toJsonWithNull() {
        val g = gson.toJson(withNull)
        val k = kson.toJson(withNull)
        println("$g\n$k\n")
        assertEquals(g, k)
    }

    @Test
    fun toJsonWithoutNull() {
        val g = gson.toJson(withoutNull)
        val k = kson.toJson(withoutNull)
        println("$g\n$k\n")
        assertEquals(g, k)
    }

    @Test
    fun fromJsonWithNull() {
        val g = gson.fromJson(withNullJson, KFoo::class.java)
        val k = kson.fromJson(withNullJson, KFoo::class.java)
        println("$g\n$k\n")
        assertEquals(g, k)
    }

    @Test
    fun fromJsonWithoutNull() {
        val g = gson.fromJson(withoutNullJson, KFoo::class.java)
        val k = kson.fromJson(withoutNullJson, KFoo::class.java)
        println("$g\n$k\n")
        assertEquals(g, k)
    }

    @Test(expected = JsonParseException::class)
    fun nullCheckGood() {
        kson.fromJson(allNullJson, KFoo::class.java)
    }

    @Test
    fun nullCheckBadJava() {
        kson.fromJson(allNullJson, JFoo::class.java)
        // j.x is happily null!
    }

    @Test
    fun nullCheckBadKotlin() {
        gson.fromJson(allNullJson, KFoo::class.java)
        // result.x is happily null!
    }
}