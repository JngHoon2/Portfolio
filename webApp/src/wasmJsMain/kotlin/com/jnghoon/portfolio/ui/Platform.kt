package com.jnghoon.portfolio.ui

import kotlinx.browser.window

/** 브라우저 새 탭에서 외부 URL 을 연다. */
fun openUrl(url: String) {
    window.open(url, "_blank")
}
