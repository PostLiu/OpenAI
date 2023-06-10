package com.postliu.openai.icons

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Brush.Companion.linearGradient
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType
import androidx.compose.ui.graphics.PathFillType.Companion.NonZero
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeCap.Companion.Butt
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.StrokeJoin.Companion.Miter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.ImageVector.Builder
import androidx.compose.ui.graphics.vector.group
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp
import com.postliu.openai.Icons

public val Icons.GoodsPointIcon: ImageVector
    get() {
        if (_goodsPointIcon != null) {
            return _goodsPointIcon!!
        }
        _goodsPointIcon = Builder(name = "GoodsPointIcon", defaultWidth = 16.0.dp, defaultHeight =
                16.0.dp, viewportWidth = 16.0f, viewportHeight = 16.0f).apply {
            group {
                path(fill = linearGradient(0.0f to Color(0xFFFFCB40), 1.0f to Color(0xFFFFA31A),
                        start = Offset(3.824f,1.312f), end = Offset(8.0f,16.0f)), stroke = null,
                        strokeLineWidth = 0.0f, strokeLineCap = Butt, strokeLineJoin = Miter,
                        strokeLineMiter = 4.0f, pathFillType = NonZero) {
                    moveTo(8.0f, 8.0f)
                    moveToRelative(-8.0f, 0.0f)
                    arcToRelative(8.0f, 8.0f, 0.0f, true, true, 16.0f, 0.0f)
                    arcToRelative(8.0f, 8.0f, 0.0f, true, true, -16.0f, 0.0f)
                }
                path(fill = SolidColor(Color(0xFFfc9835)), stroke = null, strokeLineWidth = 0.0f,
                        strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                        pathFillType = NonZero) {
                    moveTo(8.0f, 8.0f)
                    moveToRelative(-6.0f, 0.0f)
                    arcToRelative(6.0f, 6.0f, 0.0f, true, true, 12.0f, 0.0f)
                    arcToRelative(6.0f, 6.0f, 0.0f, true, true, -12.0f, 0.0f)
                }
                path(fill = linearGradient(0.0f to Color(0xFFFFF349), 1.0f to Color(0xFFF7C630),
                        start = Offset(7.998f,4.376f), end = Offset(7.998f,11.219f)), stroke = null,
                        strokeLineWidth = 0.0f, strokeLineCap = Butt, strokeLineJoin = Miter,
                        strokeLineMiter = 4.0f, pathFillType = NonZero) {
                    moveTo(7.831f, 4.472f)
                    arcToRelative(0.2f, 0.2f, 0.0f, false, true, 0.337f, 0.0f)
                    lineTo(9.302f, 6.245f)
                    arcToRelative(0.2f, 0.2f, 0.0f, false, false, 0.12f, 0.086f)
                    lineToRelative(2.057f, 0.515f)
                    arcToRelative(0.2f, 0.2f, 0.0f, false, true, 0.1f, 0.323f)
                    lineTo(10.229f, 8.773f)
                    arcToRelative(0.2f, 0.2f, 0.0f, false, false, -0.047f, 0.142f)
                    lineToRelative(0.139f, 2.086f)
                    arcToRelative(0.2f, 0.2f, 0.0f, false, true, -0.273f, 0.2f)
                    lineTo(8.073f, 10.423f)
                    arcToRelative(0.2f, 0.2f, 0.0f, false, false, -0.146f, 0.0f)
                    lineToRelative(-1.976f, 0.777f)
                    arcToRelative(0.2f, 0.2f, 0.0f, false, true, -0.273f, -0.2f)
                    lineToRelative(0.139f, -2.086f)
                    arcToRelative(0.2f, 0.2f, 0.0f, false, false, -0.047f, -0.142f)
                    lineTo(4.416f, 7.169f)
                    arcToRelative(0.2f, 0.2f, 0.0f, false, true, 0.1f, -0.323f)
                    lineToRelative(2.057f, -0.515f)
                    arcToRelative(0.2f, 0.2f, 0.0f, false, false, 0.12f, -0.086f)
                    close()
                }
            }
        }
        .build()
        return _goodsPointIcon!!
    }

private var _goodsPointIcon: ImageVector? = null
