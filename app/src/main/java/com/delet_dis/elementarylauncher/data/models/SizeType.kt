package com.delet_dis.elementarylauncher.data.models

enum class SizeType(
    val scaleCoefficient: Float,
    val firstGuidelinePercentage: Float,
    val secondGuidelinePercentage: Float
) {
    SMALL(1.1f, 0.2f, 0.705f),
    SMALL_MEDIUM(1.2f, 0.17f, 0.710f),
    MEDIUM(1.3f, 0.14f, 0.715f),
    MEDIUM_LARGE(1.4f, 0.11f, 0.720f),
    LARGE(1.5f, 0.08f, 0.715f)
}
