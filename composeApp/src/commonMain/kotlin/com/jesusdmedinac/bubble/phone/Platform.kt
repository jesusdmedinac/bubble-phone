package com.jesusdmedinac.bubble.phone

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform