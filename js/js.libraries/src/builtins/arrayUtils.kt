/*
 * Copyright 2010-2016 JetBrains s.r.o.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

// a package is omitted to get declarations directly under the module

@PublishedApi
external internal fun <T> Array(size: Int): Array<T>

@JsName("newArray")
@PublishedApi
internal fun <T> newArray(size: Int, initValue: T) = fillArrayVal(Array<T>(size), initValue)

@JsName("newArrayF")
@PublishedApi
internal inline fun <T> arrayWithFun(size: Int, init: (Int) -> T) = fillArrayFun(Array<T>(size), init)

@JsName("fillArray")
@PublishedApi
internal inline fun <T> fillArrayFun(array: Array<T>, init: (Int) -> T): Array<T> {
    for (i in 0..array.size - 1) {
        array[i] = init(i)
    }
    return array
}

@JsName("booleanArray")
@PublishedApi
internal fun booleanArray(size: Int, init: dynamic): Array<Boolean> {
    val result: dynamic = Array<Boolean>(size)
    result.`$type$` = "BooleanArray"
    return when (init) {
        null, true -> fillArrayVal(result, false)
        false -> result
        else -> fillArrayFun<Boolean>(result, init)
    }
}

@JsName("booleanArrayF")
@PublishedApi
internal inline fun booleanArrayWithFun(size: Int, init: (Int) -> Boolean): Array<Boolean> = fillArrayFun(booleanArray(size, false), init)

@JsName("charArray")
@Suppress("UNUSED_PARAMETER")
@PublishedApi
internal fun charArray(size: Int, init: dynamic): Array<Char> {
    val result = js("new Uint16Array(size)")
    result.`$type$` = "CharArray"
    return when (init) {
        null, true, false -> result // For consistency
        else -> fillArrayFun<Char>(result, init)
    }
}

@JsName("charArrayF")
@PublishedApi
internal inline fun charArrayWithFun(size: Int, init: (Int) -> Char): Array<Char> = fillArrayFun(charArray(size, null), init)

@JsName("longArray")
@PublishedApi
internal fun longArray(size: Int, init: dynamic): Array<Long> {
    val result: dynamic = Array<Long>(size)
    result.`$type$` = "LongArray"
    return when (init) {
        null, true -> fillArrayVal(result, 0L)
        false -> result
        else -> fillArrayFun<Long>(result, init)
    }
}

@JsName("longArrayF")
@PublishedApi
internal inline fun longArrayWithFun(size: Int, init: (Int) -> Long): Array<Long> = fillArrayFun(longArray(size, false), init)

private fun <T> fillArrayVal(array: Array<T>, initValue: T): Array<T> {
    for (i in 0..array.size - 1) {
        array[i] = initValue
    }
    return array
}