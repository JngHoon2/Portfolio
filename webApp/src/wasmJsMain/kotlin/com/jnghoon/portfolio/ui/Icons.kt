package com.jnghoon.portfolio.ui

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.scale
import androidx.compose.ui.graphics.vector.PathParser
import androidx.compose.ui.unit.dp

/** 16x16 viewBox 기준 SVG path("d") 를 그대로 재사용해 아이콘을 그린다. */
private fun svgIconPath(pathData: String) = PathParser().parsePathString(pathData).toPath()

@Composable
private fun SvgIcon(pathData: String, tint: Color, modifier: Modifier = Modifier) {
    val path = remember(pathData) { svgIconPath(pathData) }
    Canvas(modifier = modifier.size(15.dp)) {
        val scaleFactor = size.width / 16f
        scale(scaleFactor, scaleFactor, pivot = Offset.Zero) {
            drawPath(path, color = tint)
        }
    }
}

private const val GITHUB_PATH =
    "M8 0C3.58 0 0 3.58 0 8c0 3.54 2.29 6.53 5.47 7.59.4.07.55-.17.55-.38 0-.19-.01-.82-.01-1.49" +
        "-2.01.37-2.53-.49-2.69-.94-.09-.23-.48-.94-.82-1.13-.28-.15-.68-.52-.01-.53.63-.01 1.08.58 1.23.82" +
        ".72 1.21 1.87.87 2.33.66.07-.52.28-.87.51-1.07-1.78-.2-3.64-.89-3.64-3.95 0-.87.31-1.59.82-2.15" +
        "-.08-.2-.36-1.02.08-2.12 0 0 .67-.21 2.2.82.64-.18 1.32-.27 2-.27s1.36.09 2 .27c1.53-1.04 2.2-.82 2.2-.82" +
        ".44 1.1.16 1.92.08 2.12.51.56.82 1.27.82 2.15 0 3.07-1.87 3.75-3.65 3.95.29.25.54.73.54 1.48" +
        " 0 1.07-.01 1.93-.01 2.2 0 .21.15.46.55.38A8.01 8.01 0 0 0 16 8c0-4.42-3.58-8-8-8Z"

private const val MAIL_PATH =
    "M1.75 2h12.5c.97 0 1.75.78 1.75 1.75v8.5A1.75 1.75 0 0 1 14.25 14H1.75A1.75 1.75 0 0 1 0 12.25v-8.5" +
        "C0 2.78.78 2 1.75 2ZM1.5 12.25c0 .14.11.25.25.25h12.5a.25.25 0 0 0 .25-.25V5.81L8.38 9.4a.75.75 0 0 1-.76 0" +
        "L1.5 5.81v6.44Zm13-8.5v-.35a.25.25 0 0 0-.25-.25H1.75a.25.25 0 0 0-.25.25v.35L8 7.88l6.5-4.13Z"

private const val LINK_PATH =
    "M7.775 3.275a.75.75 0 0 0 1.06 1.06l1.25-1.25a2 2 0 1 1 2.83 2.83l-2.5 2.5a2 2 0 0 1-2.83 0" +
        " .75.75 0 0 0-1.06 1.06 3.5 3.5 0 0 0 4.95 0l2.5-2.5a3.5 3.5 0 0 0-4.95-4.95l-1.25 1.25Zm-4.69 9.64" +
        "a2 2 0 0 1 0-2.83l2.5-2.5a2 2 0 0 1 2.83 0 .75.75 0 0 0 1.06-1.06 3.5 3.5 0 0 0-4.95 0l-2.5 2.5" +
        "a3.5 3.5 0 0 0 4.95 4.95l1.25-1.25a.75.75 0 0 0-1.06-1.06l-1.25 1.25a2 2 0 0 1-2.83 0Z"

private const val PHONE_PATH =
    "M3.654 1.328a.678.678 0 0 0-1.015-.063L1.605 2.3c-.483.484-.661 1.169-.45 1.77a17.57 17.57 0 0 0 4.168 6.608" +
        " 17.57 17.57 0 0 0 6.608 4.168c.601.211 1.286.033 1.77-.45l1.034-1.034a.678.678 0 0 0-.063-1.015" +
        "l-2.307-1.794a.678.678 0 0 0-.58-.122l-2.19.547a1.745 1.745 0 0 1-1.657-.459L5.482 8.062" +
        "a1.745 1.745 0 0 1-.46-1.657l.548-2.19a.678.678 0 0 0-.122-.58L3.654 1.328Z"

@Composable
fun GithubIcon(tint: Color, modifier: Modifier = Modifier) = SvgIcon(GITHUB_PATH, tint, modifier)

@Composable
fun MailIcon(tint: Color, modifier: Modifier = Modifier) = SvgIcon(MAIL_PATH, tint, modifier)

@Composable
fun LinkIcon(tint: Color, modifier: Modifier = Modifier) = SvgIcon(LINK_PATH, tint, modifier)

@Composable
fun PhoneIcon(tint: Color, modifier: Modifier = Modifier) = SvgIcon(PHONE_PATH, tint, modifier)
