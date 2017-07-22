[![Build Status](https://travis-ci.org/sargunv/gson-kotlin.svg?branch=master)](https://travis-ci.org/sargunv/gson-kotlin)
[![Download](https://api.bintray.com/packages/sargunster/maven/gson-kotlin/images/download.svg) ](https://bintray.com/sargunster/maven/gson-kotlin/_latestVersion)

# Gson Kotlin Extension

This is an extension to Gson to add Kotlin specific features.

## Features

Current:
 - Enforce nullability on Kotlin properties during deserialization

Todo:
 - Convenience extension functions
 - Suggestions for additional features are welcome!

## Usage

```kotlin
val gson = GsonBuilder()
        .registerTypeAdapterFactory(GsonKotlinAdapterFactory())
        .create()
```

## Download

This library is available at my Bintray repository `https://dl.bintray.com/sargunster/maven`. It'll soon be on JCenter.

```groovy
compile("me.sargunvohra.lib:gson-kotlin:0.1.0")
```
