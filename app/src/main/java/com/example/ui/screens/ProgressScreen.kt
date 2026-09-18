package com.example.ui.screens

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AssignmentTurnedIn
import androidx.compose.material.icons.filled.BarChart
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.EmojiEvents
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Psychology
import androidx.compose.material.icons.filled.Stars
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.BadgeItem
import com.example.data.QuizAttempt
import com.example.data.Subject
import com.example.data.User
import com.example.ui.theme.EngageSuccess

@Composable
fun ProgressScreen(
    user: User,
    subjects: List<Subject>,
    quizAttempts: List<QuizAttempt>,
    badges: List<BadgeItem>,
    modifier: Modifier = Modifier
) {
    val completedModules = subjects.sumOf { it.completedCount }
    val totalModules = subjects.sumOf { it.totalModules }.coerceAtLeast(1)
    val overallProgress = ((completedModules.toFloat() / totalModules) * 100).toInt()

    val averageScore = if (quizAttempts.isEmpty()) 0 else (quizAttempts.sumOf { it.percentage } / quizAttempts.size)
    val unlockedBadgesCount = badges.count { it.isUnlocked }

    // Map subject codes for the performance bar chart
    val subjectAbbrs = mapOf(
        "sub_sm" to "SM",
        "sub_scm" to "SCM",
        "sub_dt" to "DT",
        "sub_ai" to "AI",
        "sub_mkt" to "MKT",
        "sub_hrm" to "HRM",
        "sub_cm" to "CM"
    )

    // Calculate score for each subject from actual quiz attempts
    val subjectScores = subjects.map { sub ->
        val latestAttempt = quizAttempts.firstOrNull { it.subjectId == sub.id }
        val score = latestAttempt?.percentage ?: 0
        val label = subjectAbbrs[sub.id] ?: sub.title.take(3).uppercase()
        Pair(label, score)
    }

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item {
            Spacer(modifier = Modifier.height(4.dp))
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    imageVector = Icons.Default.BarChart,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.size(28.dp)
                )
                Spacer(modifier = Modifier.width(10.dp))
                Column {
                    Text(
                        text = "Progress & Analytics",
                        style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Bold),
                        color = MaterialTheme.colorScheme.onBackground
                    )
                    Text(
                        text = "Real-time academic performance & engagement tracking",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
        }

        // Overall Progress Hero Card
        item {
            Card(
                shape = RoundedCornerShape(20.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .testTag("progress_overall_card")
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column {
                            Text(
                                text = "Overall Progress",
                                style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                                color = MaterialTheme.colorScheme.onSurface
                            )
                            Spacer(modifier = Modifier.height(2.dp))
                            Text(
                                text = "$completedModules / $totalModules modules completed",
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }

                        Text(
                            text = "$overallProgress%",
                            style = MaterialTheme.typography.headlineMedium.copy(
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.primary
                            )
                        )
                    }

                    Spacer(modifier = Modifier.height(14.dp))

                    LinearProgressIndicator(
                        progress = { overallProgress / 100f },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(10.dp)
                            .clip(RoundedCornerShape(5.dp)),
                        color = MaterialTheme.colorScheme.primary,
                        trackColor = MaterialTheme.colorScheme.surfaceVariant
                    )
                }
            }
        }

        // Statistics Cards (Average Quiz Score, Engagement Points, Quiz Attempts, Badges)
        item {
            Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    MetricProgressBox(
                        title = "Average Quiz Score",
                        value = "$averageScore%",
                        icon = Icons.Default.Psychology,
                        iconTint = Color(0xFF8B5CF6),
                        modifier = Modifier.weight(1f)
                    )
                    MetricProgressBox(
                        title = "Engagement Points",
                        value = "${user.points}",
                        icon = Icons.Default.Stars,
                        iconTint = Color(0xFFF59E0B),
                        modifier = Modifier.weight(1f)
                    )
                }

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    MetricProgressBox(
                        title = "Quiz Attempts",
                        value = "${quizAttempts.size}",
                        icon = Icons.Default.AssignmentTurnedIn,
                        iconTint = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.weight(1f)
                    )
                    MetricProgressBox(
                        title = "Badges",
                        value = "$unlockedBadgesCount / ${badges.size}",
                        icon = Icons.Default.EmojiEvents,
                        iconTint = EngageSuccess,
                        modifier = Modifier.weight(1f)
                    )
                }
            }
        }

        // Chart 1 — Quiz Performance (Real dynamic canvas bar chart)
        item {
            Card(
                shape = RoundedCornerShape(20.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .testTag("chart_quiz_performance")
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column {
                            Text(
                                text = "Chart 1 — Quiz Performance",
                                style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                                color = MaterialTheme.colorScheme.onSurface
                            )
                            Text(
                                text = "Latest recorded score per academic subject (%)",
                                style = MaterialTheme.typography.labelSmall,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }

                        Surface(
                            shape = RoundedCornerShape(6.dp),
                            color = MaterialTheme.colorScheme.primaryContainer
                        ) {
                            Text(
                                text = "DYNAMIC DATA",
                                style = MaterialTheme.typography.labelSmall.copy(
                                    fontWeight = FontWeight.Bold,
                                    color = MaterialTheme.colorScheme.primary
                                ),
                                modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(18.dp))

                    val primaryColor = MaterialTheme.colorScheme.primary
                    val gridColor = MaterialTheme.colorScheme.outline.copy(alpha = 0.25f)
                    val labelColor = MaterialTheme.colorScheme.onSurfaceVariant.toArgb()

                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(180.dp)
                    ) {
                        Canvas(modifier = Modifier.fillMaxSize()) {
                            val leftPadding = 80f
                            val bottomPadding = 60f
                            val chartWidth = size.width - leftPadding - 20f
                            val chartHeight = size.height - bottomPadding - 20f

                            // Draw Y-axis grid lines (0, 20, 40, 60, 80, 100)
                            val yLevels = listOf(0, 20, 40, 60, 80, 100)
                            yLevels.forEach { level ->
                                val y = 20f + chartHeight * (1f - (level / 100f))
                                drawLine(
                                    color = gridColor,
                                    start = Offset(leftPadding, y),
                                    end = Offset(size.width - 20f, y),
                                    strokeWidth = 1.dp.toPx()
                                )

                                drawContext.canvas.nativeCanvas.drawText(
                                    "$level",
                                    leftPadding - 20f,
                                    y + 10f,
                                    android.graphics.Paint().apply {
                                        color = labelColor
                                        textSize = 26f
                                        textAlign = android.graphics.Paint.Align.RIGHT
                                    }
                                )
                            }

                            // Draw bars for each subject
                            val barCount = subjectScores.size.coerceAtLeast(1)
                            val slotWidth = chartWidth / barCount
                            val barWidth = (slotWidth * 0.55f).coerceAtMost(36.dp.toPx())

                            subjectScores.forEachIndexed { i, (label, score) ->
                                val xCenter = leftPadding + i * slotWidth + slotWidth / 2f
                                val barLeft = xCenter - barWidth / 2f
                                val barHeight = (score / 100f) * chartHeight
                                val barTop = 20f + chartHeight - barHeight

                                if (score > 0) {
                                    drawRoundRect(
                                        color = primaryColor,
                                        topLeft = Offset(barLeft, barTop),
                                        size = Size(barWidth, barHeight),
                                        cornerRadius = CornerRadius(6.dp.toPx(), 6.dp.toPx())
                                    )

                                    // Draw score number on top of bar
                                    drawContext.canvas.nativeCanvas.drawText(
                                        "$score%",
                                        xCenter,
                                        barTop - 8f,
                                        android.graphics.Paint().apply {
                                            color = labelColor
                                            textSize = 24f
                                            isFakeBoldText = true
                                            textAlign = android.graphics.Paint.Align.CENTER
                                        }
                                    )
                                } else {
                                    // Empty state dash
                                    drawRoundRect(
                                        color = primaryColor.copy(alpha = 0.2f),
                                        topLeft = Offset(barLeft, 20f + chartHeight - 4.dp.toPx()),
                                        size = Size(barWidth, 4.dp.toPx()),
                                        cornerRadius = CornerRadius(2.dp.toPx(), 2.dp.toPx())
                                    )
                                }

                                // Draw X-axis label
                                drawContext.canvas.nativeCanvas.drawText(
                                    label,
                                    xCenter,
                                    size.height - 12f,
                                    android.graphics.Paint().apply {
                                        color = labelColor
                                        textSize = 24f
                                        isFakeBoldText = true
                                        textAlign = android.graphics.Paint.Align.CENTER
                                    }
                                )
                            }
                        }
                    }
                }
            }
        }

        // Chart 2 — Subject Completion (Real progress per subject)
        item {
            Card(
                shape = RoundedCornerShape(20.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .testTag("chart_subject_completion")
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp)
                ) {
                    Text(
                        text = "Chart 2 — Subject Completion",
                        style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    Text(
                        text = "Curriculum modules completed per subject area",
                        style = MaterialTheme.typography.labelSmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    subjects.forEach { subject ->
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 5.dp)
                        ) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    Text(text = subject.icon, fontSize = 16.sp)
                                    Spacer(modifier = Modifier.width(6.dp))
                                    Text(
                                        text = subject.title,
                                        style = MaterialTheme.typography.bodySmall.copy(fontWeight = FontWeight.Medium),
                                        color = MaterialTheme.colorScheme.onSurface
                                    )
                                }
                                Text(
                                    text = "${subject.progressPercentage}% (${subject.completedCount}/${subject.totalModules})",
                                    style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Bold),
                                    color = if (subject.progressPercentage == 100) EngageSuccess else MaterialTheme.colorScheme.primary
                                )
                            }
                            Spacer(modifier = Modifier.height(4.dp))
                            LinearProgressIndicator(
                                progress = { subject.progressPercentage / 100f },
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(6.dp)
                                    .clip(RoundedCornerShape(3.dp)),
                                color = if (subject.progressPercentage == 100) EngageSuccess else MaterialTheme.colorScheme.primary,
                                trackColor = MaterialTheme.colorScheme.surfaceVariant
                            )
                        }
                    }
                }
            }
        }

        // Section: Achievement System (Badges)
        item {
            Card(
                shape = RoundedCornerShape(20.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .testTag("achievements_card")
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Achievement Badges",
                            style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                            color = MaterialTheme.colorScheme.onSurface
                        )
                        Text(
                            text = "$unlockedBadgesCount / ${badges.size} Unlocked",
                            style = MaterialTheme.typography.labelMedium.copy(
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.primary
                            )
                        )
                    }

                    Spacer(modifier = Modifier.height(14.dp))

                    Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                        badges.forEach { badge ->
                            BadgeRowItem(badge)
                        }
                    }
                }
            }
        }

        item {
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

@Composable
private fun MetricProgressBox(
    title: String,
    value: String,
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    iconTint: Color,
    modifier: Modifier = Modifier
) {
    Card(
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp),
        modifier = modifier
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(14.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                Box(
                    modifier = Modifier
                        .size(28.dp)
                        .clip(CircleShape)
                        .background(iconTint.copy(alpha = 0.12f)),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = icon,
                        contentDescription = null,
                        tint = iconTint,
                        modifier = Modifier.size(15.dp)
                    )
                }
            }
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = value,
                style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Bold),
                color = MaterialTheme.colorScheme.onSurface
            )
        }
    }
}

@Composable
private fun BadgeRowItem(badge: BadgeItem) {
    val isUnlocked = badge.isUnlocked
    Surface(
        shape = RoundedCornerShape(14.dp),
        color = if (isUnlocked) MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.35f) else MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.35f),
        border = if (isUnlocked) androidx.compose.foundation.BorderStroke(1.dp, MaterialTheme.colorScheme.primary.copy(alpha = 0.2f)) else null,
        modifier = Modifier
            .fillMaxWidth()
            .alpha(if (isUnlocked) 1f else 0.55f)
            .testTag("badge_${badge.id}")
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(42.dp)
                    .clip(CircleShape)
                    .background(if (isUnlocked) Color(0xFFFEF3C7) else MaterialTheme.colorScheme.surfaceVariant),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = badge.icon,
                    fontSize = 20.sp
                )
            }

            Spacer(modifier = Modifier.width(12.dp))

            Column(modifier = Modifier.weight(1f)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = badge.title,
                        style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold),
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    if (isUnlocked) {
                        Icon(
                            imageVector = Icons.Default.CheckCircle,
                            contentDescription = "Unlocked",
                            tint = EngageSuccess,
                            modifier = Modifier.size(14.dp)
                        )
                    } else {
                        Icon(
                            imageVector = Icons.Default.Lock,
                            contentDescription = "Locked",
                            tint = MaterialTheme.colorScheme.onSurfaceVariant,
                            modifier = Modifier.size(14.dp)
                        )
                    }
                }
                Spacer(modifier = Modifier.height(2.dp))
                Text(
                    text = badge.description,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }

            Surface(
                shape = RoundedCornerShape(6.dp),
                color = if (isUnlocked) EngageSuccess.copy(alpha = 0.12f) else MaterialTheme.colorScheme.surfaceVariant
            ) {
                Text(
                    text = if (isUnlocked) "UNLOCKED" else "LOCKED",
                    style = MaterialTheme.typography.labelSmall.copy(
                        fontWeight = FontWeight.Bold,
                        color = if (isUnlocked) EngageSuccess else MaterialTheme.colorScheme.onSurfaceVariant
                    ),
                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 3.dp)
                )
            }
        }
    }
}
