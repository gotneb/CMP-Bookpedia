package com.gotneb.bookpedia

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform