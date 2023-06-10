package com.postliu.openai.icons

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush.Companion.linearGradient
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType
import androidx.compose.ui.graphics.PathFillType.Companion.NonZero
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeCap.Companion.Butt
import androidx.compose.ui.graphics.StrokeCap.Companion.Round
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.StrokeJoin.Companion.Miter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.ImageVector.Builder
import androidx.compose.ui.graphics.vector.group
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp
import com.postliu.openai.Icons

public val Icons.StoreCartIcon: ImageVector
    get() {
        if (_storeCartIcon != null) {
            return _storeCartIcon!!
        }
        _storeCartIcon = Builder(name = "StoreCartIcon", defaultWidth = 36.0.dp, defaultHeight =
                36.0.dp, viewportWidth = 36.0f, viewportHeight = 36.0f).apply {
            group {
                path(fill = SolidColor(Color(0xFF000000)), stroke = null, fillAlpha = 0.76f,
                        strokeAlpha = 0.76f, strokeLineWidth = 0.0f, strokeLineCap = Butt,
                        strokeLineJoin = Miter, strokeLineMiter = 4.0f, pathFillType = NonZero) {
                    moveTo(18.0f, 18.0f)
                    moveToRelative(-17.0f, 0.0f)
                    arcToRelative(17.0f, 17.0f, 0.0f, true, true, 34.0f, 0.0f)
                    arcToRelative(17.0f, 17.0f, 0.0f, true, true, -34.0f, 0.0f)
                }
                path(fill = linearGradient(0.0f to Color(0xFFFF6D63), 1.0f to Color(0xFFF83427),
                        start = Offset(18.0f,3.0f), end = Offset(18.0f,33.0f)), stroke = null,
                        strokeLineWidth = 0.0f, strokeLineCap = Butt, strokeLineJoin = Miter,
                        strokeLineMiter = 4.0f, pathFillType = NonZero) {
                    moveTo(18.0f, 18.0f)
                    moveToRelative(-15.0f, 0.0f)
                    arcToRelative(15.0f, 15.0f, 0.0f, true, true, 30.0f, 0.0f)
                    arcToRelative(15.0f, 15.0f, 0.0f, true, true, -30.0f, 0.0f)
                }
                path(fill = SolidColor(Color(0xFF000000)), stroke = null, strokeLineWidth = 0.0f,
                        strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                        pathFillType = NonZero) {
                    moveTo(22.882f, 21.028f)
                    lineTo(13.854f, 21.028f)
                    lineTo(12.118f, 14.083f)
                    lineTo(24.618f, 14.083f)
                    close()
                }
                path(fill = SolidColor(Color(0x00000000)), stroke = SolidColor(Color(0xFF000000)),
                        strokeLineWidth = 1.5f, strokeLineCap = Round, strokeLineJoin =
                        StrokeJoin.Companion.Round, strokeLineMiter = 4.0f, pathFillType =
                        NonZero) {
                    moveTo(9.193f, 12.0f)
                    horizontalLineToRelative(2.4f)
                    lineToRelative(0.521f, 2.083f)
                    moveToRelative(0.0f, 0.0f)
                    lineToRelative(1.736f, 6.945f)
                    lineTo(22.882f, 21.028f)
                    lineToRelative(1.736f, -6.945f)
                    close()
                }
                path(fill = SolidColor(Color(0xFF000000)), stroke = null, strokeLineWidth = 0.0f,
                        strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                        pathFillType = NonZero) {
                    moveTo(14.214f, 23.939f)
                    moveToRelative(-1.356f, 0.0f)
                    arcToRelative(1.356f, 1.356f, 0.0f, true, true, 2.712f, 0.0f)
                    arcToRelative(1.356f, 1.356f, 0.0f, true, true, -2.712f, 0.0f)
                }
                path(fill = SolidColor(Color(0xFF000000)), stroke = null, strokeLineWidth = 0.0f,
                        strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                        pathFillType = NonZero) {
                    moveTo(22.761f, 23.939f)
                    moveToRelative(-1.356f, 0.0f)
                    arcToRelative(1.356f, 1.356f, 0.0f, true, true, 2.712f, 0.0f)
                    arcToRelative(1.356f, 1.356f, 0.0f, true, true, -2.712f, 0.0f)
                }
            }
        }
        .build()
        return _storeCartIcon!!
    }

private var _storeCartIcon: ImageVector? = null
