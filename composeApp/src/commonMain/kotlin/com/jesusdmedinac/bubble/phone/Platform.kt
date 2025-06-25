package com.jesusdmedinac.bubble.phone

sealed class Platform {
    data object Android : Platform()
    data object iOS : Platform()
}

expect fun getPlatform(): Platform