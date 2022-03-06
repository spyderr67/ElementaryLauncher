package com.delet_dis.elementarylauncher.data.models

enum class SizeType(
    val scaleCoefficient: Float,
    val firstGuidelinePercentage: Float,
    val secondGuidelinePercentage: Float
) {
    SMALL(0.7f, 0.2f, 0.705f),
    SMALL_MEDIUM(0.8f, 0.17f, 0.710f),
    MEDIUM(0.9f, 0.14f, 0.715f),
    MEDIUM_LARGE(1.0f, 0.11f, 0.720f),
    LARGE(1.1f, 0.08f, 0.715f)
}
