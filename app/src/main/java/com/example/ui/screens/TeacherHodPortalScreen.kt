package com.example.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.AdminPanelSettings
import androidx.compose.material.icons.filled.Assessment
import androidx.compose.material.icons.filled.AutoStories
import androidx.compose.material.icons.filled.BarChart
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.School
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.EngageDataRepository
import com.example.data.StudentPerformance
import com.example.data.Subject
import com.example.data.TeacherAssignment
import com.example.data.User
import com.example.ui.theme.EngageSuccess

@Composable
fun TeacherHodPortalScreen(
    currentUser: User,
    subjects: List<Subject>,
    assignments: List<TeacherAssignment>,
    onBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    var selectedTab by remember {
        mutableStateOf(if (currentUser.role == "HOD") "HOD Approval" else "Classroom Performance")
    }

    val students = remember(subjects) { EngageDataRepository.getStudentRoster() }
    var selectedStudentId by remember { mutableStateOf(students.first().studentId) }
    val currentSelectedStudent = students.firstOrNull { it.studentId == selectedStudentId } ?: students.first()

    // Dialog state for "Add New Subject"
    var showAddSubjectDialog by remember { mutableStateOf(false) }
    var newSubTitle by remember { mutableStateOf("") }
    var newSubCode by remember { mutableStateOf("") }
    var newSubIcon by remember { mutableStateOf("📘") }
    var newSubDesc by remember { mutableStateOf("") }
    var newSubFirstModTitle by remember { mutableStateOf("") }
    var newSubInsight by remember { mutableStateOf("") }
    var newSubQuizQuestion by remember { mutableStateOf("") }

    // Dialog state for "Create Remedial Task"
    var showCreateTaskDialog by remember { mutableStateOf(false) }
    var newTaskTitle by remember { mutableStateOf("") }
    var newTaskDescription by remember { mutableStateOf("") }
    var newTaskSubject by remember { mutableStateOf(subjects.firstOrNull()?.title ?: "Strategic Management") }
    var newTaskStudent by remember { mutableStateOf("Ans") }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        // App Bar
        Surface(
            color = MaterialTheme.colorScheme.surface,
            tonalElevation = 2.dp,
            shadowElevation = 1.dp,
            modifier = Modifier.fillMaxWidth()
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 12.dp, vertical = 8.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    IconButton(
                        onClick = onBack,
                        modifier = Modifier.testTag("teacher_portal_back_button")
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back",
                            tint = MaterialTheme.colorScheme.onSurface
                        )
                    }
                    Spacer(modifier = Modifier.width(4.dp))
                    Column {
                        Text(
                            text = if (currentUser.role == "HOD") "HOD Executive Portal" else "Teacher Academic Portal",
                            style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                            color = MaterialTheme.colorScheme.onSurface
                        )
                        Text(
                            text = "Signed in as: ${currentUser.name} (${currentUser.role})",
                            style = MaterialTheme.typography.labelSmall,
                            color = MaterialTheme.colorScheme.primary
                        )
                    }
                }

                // Add Subject quick button for Teacher / HOD
                Button(
                    onClick = { showAddSubjectDialog = true },
                    shape = RoundedCornerShape(10.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary),
                    modifier = Modifier.testTag("btn_add_subject_top")
                ) {
                    Icon(Icons.Default.Add, contentDescription = null, modifier = Modifier.size(16.dp))
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = "Add Subject",
                        style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Bold)
                    )
                }
            }
        }

        // Navigation Tabs: [ Classroom Performance ] [ Individual Student Charts ] [ HOD Approval & Tasks ]
        LazyRow(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 10.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            item {
                FilterChip(
                    selected = selectedTab == "Classroom Performance",
                    onClick = { selectedTab = "Classroom Performance" },
                    label = { Text("Classroom Performance") },
                    leadingIcon = {
                        Icon(Icons.Default.BarChart, contentDescription = null, modifier = Modifier.size(16.dp))
                    },
                    modifier = Modifier.testTag("tab_classroom_performance")
                )
            }
            item {
                FilterChip(
                    selected = selectedTab == "Student Charts",
                    onClick = { selectedTab = "Student Charts" },
                    label = { Text("Individual Student Charts") },
                    leadingIcon = {
                        Icon(Icons.Default.Person, contentDescription = null, modifier = Modifier.size(16.dp))
                    },
                    modifier = Modifier.testTag("tab_student_charts")
                )
            }
            item {
                FilterChip(
                    selected = selectedTab == "HOD Approval",
                    onClick = { selectedTab = "HOD Approval" },
                    label = { Text("Tasks & HOD Approval (${assignments.count { !it.isHodApproved }})") },
                    leadingIcon = {
                        Icon(Icons.Default.AdminPanelSettings, contentDescription = null, modifier = Modifier.size(16.dp))
                    },
                    modifier = Modifier.testTag("tab_hod_approval")
                )
            }
        }

        // Main Tab Content
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            when (selectedTab) {
                // TAB 1: CLASSROOM PERFORMANCE
                "Classroom Performance" -> {
                    item {
                        Card(
                            shape = RoundedCornerShape(18.dp),
                            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                            elevation = CardDefaults.cardElevation(defaultElevation = 1.dp),
                            modifier = Modifier.fillMaxWidth().testTag("classroom_overview_card")
                        ) {
                            Column(modifier = Modifier.padding(18.dp)) {
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Column {
                                        Text(
                                            text = "Cohort Performance Overview",
                                            style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                                            color = MaterialTheme.colorScheme.onSurface
                                        )
                                        Text(
                                            text = "5 Enrolled Students · ${subjects.size} Active Curriculum Subjects",
                                            style = MaterialTheme.typography.bodySmall,
                                            color = MaterialTheme.colorScheme.onSurfaceVariant
                                        )
                                    }

                                    Surface(
                                        shape = RoundedCornerShape(8.dp),
                                        color = MaterialTheme.colorScheme.primaryContainer
                                    ) {
                                        Text(
                                            text = "Term 2026",
                                            style = MaterialTheme.typography.labelSmall.copy(
                                                fontWeight = FontWeight.Bold,
                                                color = MaterialTheme.colorScheme.primary
                                            ),
                                            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                                        )
                                    }
                                }

                                Spacer(modifier = Modifier.height(16.dp))

                                // Top KPIs
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                                ) {
                                    val avgClassScore = students.map { it.quizAverage }.average().toInt()
                                    val avgCompletion = students.map { it.overallPercentage }.average().toInt()

                                    Surface(
                                        shape = RoundedCornerShape(12.dp),
                                        color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.6f),
                                        modifier = Modifier.weight(1f)
                                    ) {
                                        Column(modifier = Modifier.padding(12.dp)) {
                                            Text("Avg Completion", style = MaterialTheme.typography.labelSmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
                                            Spacer(modifier = Modifier.height(2.dp))
                                            Text("$avgCompletion%", style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold), color = MaterialTheme.colorScheme.primary)
                                        }
                                    }

                                    Surface(
                                        shape = RoundedCornerShape(12.dp),
                                        color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.6f),
                                        modifier = Modifier.weight(1f)
                                    ) {
                                        Column(modifier = Modifier.padding(12.dp)) {
                                            Text("Quiz Average", style = MaterialTheme.typography.labelSmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
                                            Spacer(modifier = Modifier.height(2.dp))
                                            Text("$avgClassScore%", style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold), color = EngageSuccess)
                                        }
                                    }

                                    Surface(
                                        shape = RoundedCornerShape(12.dp),
                                        color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.6f),
                                        modifier = Modifier.weight(1f)
                                    ) {
                                        Column(modifier = Modifier.padding(12.dp)) {
                                            Text("Total Points", style = MaterialTheme.typography.labelSmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
                                            Spacer(modifier = Modifier.height(2.dp))
                                            Text("${students.sumOf { it.points }}", style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold), color = Color(0xFFD97706))
                                        }
                                    }
                                }
                            }
                        }
                    }

                    // Class-wide Subject Mastery Chart
                    item {
                        Card(
                            shape = RoundedCornerShape(18.dp),
                            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                            elevation = CardDefaults.cardElevation(defaultElevation = 1.dp),
                            modifier = Modifier.fillMaxWidth().testTag("classroom_subjects_chart_card")
                        ) {
                            Column(modifier = Modifier.padding(18.dp)) {
                                Text(
                                    text = "Classroom Subject Progress Breakdown",
                                    style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                                    color = MaterialTheme.colorScheme.onSurface
                                )
                                Text(
                                    text = "Average module completion rate across all enrolled students",
                                    style = MaterialTheme.typography.bodySmall,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant
                                )

                                Spacer(modifier = Modifier.height(16.dp))

                                subjects.forEach { subject ->
                                    val avgSubjectPct = (students.map { s ->
                                        s.subjectProgress.firstOrNull { it.subjectId == subject.id }?.percentage ?: 0
                                    }.average()).toInt()

                                    Column(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(vertical = 6.dp)
                                    ) {
                                        Row(
                                            modifier = Modifier.fillMaxWidth(),
                                            horizontalArrangement = Arrangement.SpaceBetween,
                                            verticalAlignment = Alignment.CenterVertically
                                        ) {
                                            Text(
                                                text = "${subject.icon}  ${subject.title}",
                                                style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.SemiBold),
                                                color = MaterialTheme.colorScheme.onSurface
                                            )
                                            Text(
                                                text = "$avgSubjectPct% class avg",
                                                style = MaterialTheme.typography.labelMedium.copy(
                                                    fontWeight = FontWeight.Bold,
                                                    color = if (avgSubjectPct >= 65) EngageSuccess else if (avgSubjectPct >= 40) Color(0xFFD97706) else MaterialTheme.colorScheme.error
                                                )
                                            )
                                        }

                                        Spacer(modifier = Modifier.height(6.dp))

                                        LinearProgressIndicator(
                                            progress = { (avgSubjectPct / 100f).coerceIn(0f, 1f) },
                                            modifier = Modifier
                                                .fillMaxWidth()
                                                .height(8.dp)
                                                .clip(RoundedCornerShape(4.dp)),
                                            color = if (avgSubjectPct >= 65) EngageSuccess else if (avgSubjectPct >= 40) MaterialTheme.colorScheme.primary else Color(0xFFE53935),
                                            trackColor = MaterialTheme.colorScheme.surfaceVariant
                                        )
                                    }
                                }
                            }
                        }
                    }

                    // Enrolled Students Roster Summary
                    item {
                        Text(
                            text = "Student Cohort Roster",
                            style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                            color = MaterialTheme.colorScheme.onBackground
                        )
                    }

                    items(students) { student ->
                        Card(
                            shape = RoundedCornerShape(14.dp),
                            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                            elevation = CardDefaults.cardElevation(defaultElevation = 1.dp),
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable {
                                    selectedStudentId = student.studentId
                                    selectedTab = "Student Charts"
                                }
                                .testTag("roster_student_${student.studentId}")
                        ) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(14.dp),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    Box(
                                        modifier = Modifier
                                            .size(42.dp)
                                            .clip(CircleShape)
                                            .background(MaterialTheme.colorScheme.primaryContainer),
                                        contentAlignment = Alignment.Center
                                    ) {
                                        Text(
                                            text = student.name.take(1).uppercase(),
                                            fontWeight = FontWeight.Bold,
                                            color = MaterialTheme.colorScheme.primary
                                        )
                                    }

                                    Spacer(modifier = Modifier.width(12.dp))

                                    Column {
                                        Text(
                                            text = student.name,
                                            style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold),
                                            color = MaterialTheme.colorScheme.onSurface
                                        )
                                        Text(
                                            text = "ID: ${student.studentId} · ${student.points} pts",
                                            style = MaterialTheme.typography.bodySmall,
                                            color = MaterialTheme.colorScheme.onSurfaceVariant
                                        )
                                    }
                                }

                                Column(horizontalAlignment = Alignment.End) {
                                    Surface(
                                        shape = RoundedCornerShape(6.dp),
                                        color = if (student.engagementStatus == "Excellent") EngageSuccess.copy(alpha = 0.15f)
                                        else if (student.engagementStatus == "Active") MaterialTheme.colorScheme.primaryContainer
                                        else Color(0xFFFEE2E2)
                                    ) {
                                        Text(
                                            text = student.engagementStatus.uppercase(),
                                            style = MaterialTheme.typography.labelSmall.copy(
                                                fontWeight = FontWeight.Bold,
                                                color = if (student.engagementStatus == "Excellent") EngageSuccess
                                                else if (student.engagementStatus == "Active") MaterialTheme.colorScheme.primary
                                                else Color(0xFFDC2626)
                                            ),
                                            modifier = Modifier.padding(horizontal = 8.dp, vertical = 3.dp)
                                        )
                                    }

                                    Spacer(modifier = Modifier.height(4.dp))

                                    Text(
                                        text = "${student.overallPercentage}% Complete · View Chart ›",
                                        style = MaterialTheme.typography.labelSmall.copy(
                                            fontWeight = FontWeight.Medium,
                                            color = MaterialTheme.colorScheme.primary
                                        )
                                    )
                                }
                            }
                        }
                    }
                }

                // TAB 2: INDIVIDUAL STUDENT CHARTS
                "Student Charts" -> {
                    item {
                        Column {
                            Text(
                                text = "Select Student to View Subject Chart:",
                                style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Bold),
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )

                            Spacer(modifier = Modifier.height(8.dp))

                            LazyRow(
                                horizontalArrangement = Arrangement.spacedBy(8.dp),
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                items(students) { s ->
                                    val isSelected = s.studentId == currentSelectedStudent.studentId
                                    FilterChip(
                                        selected = isSelected,
                                        onClick = { selectedStudentId = s.studentId },
                                        label = { Text(s.name) },
                                        colors = FilterChipDefaults.filterChipColors(
                                            selectedContainerColor = MaterialTheme.colorScheme.primary,
                                            selectedLabelColor = Color.White
                                        ),
                                        modifier = Modifier.testTag("filter_chip_student_${s.studentId}")
                                    )
                                }
                            }
                        }
                    }

                    // Selected Student Profile & Summary Card
                    item {
                        Card(
                            shape = RoundedCornerShape(18.dp),
                            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                            elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
                            modifier = Modifier.fillMaxWidth().testTag("student_detail_card")
                        ) {
                            Column(modifier = Modifier.padding(18.dp)) {
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Row(verticalAlignment = Alignment.CenterVertically) {
                                        Box(
                                            modifier = Modifier
                                                .size(50.dp)
                                                .clip(CircleShape)
                                                .background(MaterialTheme.colorScheme.primary),
                                            contentAlignment = Alignment.Center
                                        ) {
                                            Text(
                                                text = currentSelectedStudent.name.take(1).uppercase(),
                                                style = MaterialTheme.typography.titleLarge.copy(
                                                    fontWeight = FontWeight.Bold,
                                                    color = Color.White
                                                )
                                            )
                                        }

                                        Spacer(modifier = Modifier.width(12.dp))

                                        Column {
                                            Text(
                                                text = currentSelectedStudent.name,
                                                style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                                                color = MaterialTheme.colorScheme.onSurface
                                            )
                                            Text(
                                                text = "${currentSelectedStudent.email} · ID: ${currentSelectedStudent.studentId}",
                                                style = MaterialTheme.typography.bodySmall,
                                                color = MaterialTheme.colorScheme.onSurfaceVariant
                                            )
                                        }
                                    }

                                    Surface(
                                        shape = RoundedCornerShape(8.dp),
                                        color = if (currentSelectedStudent.engagementStatus == "Excellent") EngageSuccess.copy(alpha = 0.15f)
                                        else if (currentSelectedStudent.engagementStatus == "Active") MaterialTheme.colorScheme.primaryContainer
                                        else Color(0xFFFEE2E2)
                                    ) {
                                        Text(
                                            text = currentSelectedStudent.engagementStatus.uppercase(),
                                            style = MaterialTheme.typography.labelSmall.copy(
                                                fontWeight = FontWeight.Bold,
                                                color = if (currentSelectedStudent.engagementStatus == "Excellent") EngageSuccess
                                                else if (currentSelectedStudent.engagementStatus == "Active") MaterialTheme.colorScheme.primary
                                                else Color(0xFFDC2626)
                                            ),
                                            modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp)
                                        )
                                    }
                                }

                                Spacer(modifier = Modifier.height(16.dp))

                                // Quick Student Stats
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.SpaceEvenly
                                ) {
                                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                        Text("${currentSelectedStudent.points}", style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold), color = MaterialTheme.colorScheme.primary)
                                        Text("Points", style = MaterialTheme.typography.labelSmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
                                    }

                                    Box(modifier = Modifier.height(28.dp).width(1.dp).background(MaterialTheme.colorScheme.outline.copy(alpha = 0.2f)))

                                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                        Text("${currentSelectedStudent.totalModulesCompleted}/${currentSelectedStudent.totalModules}", style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold), color = MaterialTheme.colorScheme.onSurface)
                                        Text("Modules", style = MaterialTheme.typography.labelSmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
                                    }

                                    Box(modifier = Modifier.height(28.dp).width(1.dp).background(MaterialTheme.colorScheme.outline.copy(alpha = 0.2f)))

                                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                        Text("${currentSelectedStudent.quizAverage}%", style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold), color = EngageSuccess)
                                        Text("Quiz Avg", style = MaterialTheme.typography.labelSmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
                                    }
                                }

                                Spacer(modifier = Modifier.height(14.dp))

                                // Remedial warning / weakness callout
                                Surface(
                                    shape = RoundedCornerShape(10.dp),
                                    color = Color(0xFFFEF3C7),
                                    modifier = Modifier.fillMaxWidth()
                                ) {
                                    Row(
                                        modifier = Modifier.padding(10.dp),
                                        verticalAlignment = Alignment.CenterVertically,
                                        horizontalArrangement = Arrangement.SpaceBetween
                                    ) {
                                        Row(
                                            modifier = Modifier.weight(1f),
                                            verticalAlignment = Alignment.CenterVertically
                                        ) {
                                            Icon(Icons.Default.Warning, contentDescription = null, tint = Color(0xFFB45309), modifier = Modifier.size(18.dp))
                                            Spacer(modifier = Modifier.width(8.dp))
                                            Text(
                                                text = "Attention Area: ${currentSelectedStudent.weakSubject}",
                                                style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.SemiBold, color = Color(0xFF92400E))
                                            )
                                        }

                                        TextButton(
                                            onClick = {
                                                newTaskStudent = currentSelectedStudent.name
                                                showCreateTaskDialog = true
                                            }
                                        ) {
                                            Text("Assign Task", style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Bold, color = Color(0xFFB45309)))
                                        }
                                    }
                                }
                            }
                        }
                    }

                    // Student's Detailed Subject Progress Chart (Identical structure to student progress charts)
                    item {
                        Card(
                            shape = RoundedCornerShape(18.dp),
                            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                            elevation = CardDefaults.cardElevation(defaultElevation = 1.dp),
                            modifier = Modifier.fillMaxWidth().testTag("student_subject_progress_chart")
                        ) {
                            Column(modifier = Modifier.padding(18.dp)) {
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Text(
                                        text = "${currentSelectedStudent.name}'s Subject Progress Chart",
                                        style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                                        color = MaterialTheme.colorScheme.onSurface
                                    )
                                    Text(
                                        text = "${currentSelectedStudent.overallPercentage}% Overall",
                                        style = MaterialTheme.typography.titleSmall.copy(
                                            fontWeight = FontWeight.Bold,
                                            color = MaterialTheme.colorScheme.primary
                                        )
                                    )
                                }

                                Text(
                                    text = "Visual breakdown of completion rate per enrolled academic subject",
                                    style = MaterialTheme.typography.bodySmall,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant
                                )

                                Spacer(modifier = Modifier.height(16.dp))

                                currentSelectedStudent.subjectProgress.forEach { subProgress ->
                                    Column(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(vertical = 6.dp)
                                    ) {
                                        Row(
                                            modifier = Modifier.fillMaxWidth(),
                                            horizontalArrangement = Arrangement.SpaceBetween,
                                            verticalAlignment = Alignment.CenterVertically
                                        ) {
                                            Text(
                                                text = "${subProgress.subjectIcon}  ${subProgress.subjectTitle}",
                                                style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Medium),
                                                color = MaterialTheme.colorScheme.onSurface
                                            )
                                            Text(
                                                text = "${subProgress.completedModules}/${subProgress.totalModules} (${subProgress.percentage}%)",
                                                style = MaterialTheme.typography.labelMedium.copy(
                                                    fontWeight = FontWeight.Bold,
                                                    color = if (subProgress.percentage >= 70) EngageSuccess
                                                    else if (subProgress.percentage > 0) MaterialTheme.colorScheme.primary
                                                    else MaterialTheme.colorScheme.onSurfaceVariant
                                                )
                                            )
                                        }

                                        Spacer(modifier = Modifier.height(6.dp))

                                        LinearProgressIndicator(
                                            progress = { (subProgress.percentage / 100f).coerceIn(0f, 1f) },
                                            modifier = Modifier
                                                .fillMaxWidth()
                                                .height(10.dp)
                                                .clip(RoundedCornerShape(5.dp)),
                                            color = if (subProgress.percentage >= 70) EngageSuccess
                                            else if (subProgress.percentage > 0) MaterialTheme.colorScheme.primary
                                            else MaterialTheme.colorScheme.outlineVariant,
                                            trackColor = MaterialTheme.colorScheme.surfaceVariant
                                        )
                                    }
                                }
                            }
                        }
                    }
                }

                // TAB 3: HOD APPROVAL & REMEDIAL TASKS
                "HOD Approval" -> {
                    item {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Column {
                                Text(
                                    text = "Intervention Tasks & HOD Approval",
                                    style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                                    color = MaterialTheme.colorScheme.onBackground
                                )
                                Text(
                                    text = "Curriculum oversight & remediation approval",
                                    style = MaterialTheme.typography.bodySmall,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant
                                )
                            }

                            Button(
                                onClick = { showCreateTaskDialog = true },
                                shape = RoundedCornerShape(8.dp),
                                modifier = Modifier.testTag("create_new_task_btn")
                            ) {
                                Icon(Icons.Default.Add, contentDescription = null, modifier = Modifier.size(16.dp))
                                Spacer(modifier = Modifier.width(4.dp))
                                Text("New Task", style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Bold))
                            }
                        }
                    }

                    if (assignments.isEmpty()) {
                        item {
                            Card(
                                shape = RoundedCornerShape(14.dp),
                                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                Box(
                                    modifier = Modifier.fillMaxWidth().padding(32.dp),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Text(
                                        text = "No remedial assignments pending.",
                                        style = MaterialTheme.typography.bodyMedium,
                                        color = MaterialTheme.colorScheme.onSurfaceVariant
                                    )
                                }
                            }
                        }
                    } else {
                        items(assignments) { task ->
                            Card(
                                shape = RoundedCornerShape(16.dp),
                                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                                elevation = CardDefaults.cardElevation(defaultElevation = 1.dp),
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .testTag("assignment_card_${task.id}")
                            ) {
                                Column(modifier = Modifier.padding(16.dp)) {
                                    Row(
                                        modifier = Modifier.fillMaxWidth(),
                                        horizontalArrangement = Arrangement.SpaceBetween,
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        Text(
                                            text = task.title,
                                            style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                                            color = MaterialTheme.colorScheme.onSurface,
                                            modifier = Modifier.weight(1f)
                                        )

                                        Surface(
                                            shape = RoundedCornerShape(6.dp),
                                            color = if (task.isHodApproved) EngageSuccess.copy(alpha = 0.12f) else Color(0xFFFEF3C7)
                                        ) {
                                            Text(
                                                text = if (task.isHodApproved) "HOD APPROVED" else "PENDING HOD APPROVAL",
                                                style = MaterialTheme.typography.labelSmall.copy(
                                                    fontWeight = FontWeight.Bold,
                                                    color = if (task.isHodApproved) EngageSuccess else Color(0xFFB45309)
                                                ),
                                                modifier = Modifier.padding(horizontal = 8.dp, vertical = 3.dp)
                                            )
                                        }
                                    }

                                    Spacer(modifier = Modifier.height(6.dp))

                                    Text(
                                        text = "Subject: ${task.subjectName} · Student: ${task.assignedToStudent} · Due: ${task.dueDate}",
                                        style = MaterialTheme.typography.labelSmall,
                                        color = MaterialTheme.colorScheme.onSurfaceVariant
                                    )

                                    Spacer(modifier = Modifier.height(8.dp))

                                    Text(
                                        text = task.description,
                                        style = MaterialTheme.typography.bodyMedium,
                                        color = MaterialTheme.colorScheme.onSurface
                                    )

                                    Spacer(modifier = Modifier.height(12.dp))

                                    // HOD Approval Button
                                    if (!task.isHodApproved) {
                                        Button(
                                            onClick = { EngageDataRepository.approveAssignment(task.id) },
                                            shape = RoundedCornerShape(8.dp),
                                            colors = ButtonDefaults.buttonColors(containerColor = EngageSuccess),
                                            modifier = Modifier
                                                .fillMaxWidth()
                                                .testTag("approve_task_${task.id}")
                                        ) {
                                            Icon(Icons.Default.Check, contentDescription = null, modifier = Modifier.size(16.dp))
                                            Spacer(modifier = Modifier.width(6.dp))
                                            Text("Approve Task as HOD", style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Bold))
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }

            item {
                Spacer(modifier = Modifier.height(20.dp))
            }
        }
    }

    // DIALOG: ADD NEW SUBJECT (Reflected instantly on Student Portal!)
    if (showAddSubjectDialog) {
        AlertDialog(
            onDismissRequest = { showAddSubjectDialog = false },
            title = {
                Text(
                    text = "Add New Curriculum Subject",
                    style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold)
                )
            },
            text = {
                Column(
                    modifier = Modifier.verticalScroll(rememberScrollState()),
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    Text(
                        text = "Once published, this subject will immediately appear on the Student Portal, complete with its initial module and quiz!",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.primary
                    )

                    OutlinedTextField(
                        value = newSubTitle,
                        onValueChange = { newSubTitle = it },
                        label = { Text("Subject Title *") },
                        placeholder = { Text("e.g. Financial Analytics & Risk") },
                        singleLine = true,
                        modifier = Modifier.fillMaxWidth().testTag("input_new_subject_title")
                    )

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        OutlinedTextField(
                            value = newSubCode,
                            onValueChange = { newSubCode = it },
                            label = { Text("Code") },
                            placeholder = { Text("08") },
                            singleLine = true,
                            modifier = Modifier.weight(1f).testTag("input_new_subject_code")
                        )

                        OutlinedTextField(
                            value = newSubIcon,
                            onValueChange = { newSubIcon = it },
                            label = { Text("Icon") },
                            placeholder = { Text("📊") },
                            singleLine = true,
                            modifier = Modifier.weight(1f).testTag("input_new_subject_icon")
                        )
                    }

                    OutlinedTextField(
                        value = newSubDesc,
                        onValueChange = { newSubDesc = it },
                        label = { Text("Subject Description") },
                        placeholder = { Text("Corporate finance, valuation, risk hedging and capital structure.") },
                        modifier = Modifier.fillMaxWidth().testTag("input_new_subject_desc")
                    )

                    HorizontalDivider(modifier = Modifier.padding(vertical = 4.dp))

                    Text(
                        text = "Initial Micro-Learning Module",
                        style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Bold),
                        color = MaterialTheme.colorScheme.onSurface
                    )

                    OutlinedTextField(
                        value = newSubFirstModTitle,
                        onValueChange = { newSubFirstModTitle = it },
                        label = { Text("Module 01 Title") },
                        placeholder = { Text("Foundations & Cash Flow Dynamics") },
                        singleLine = true,
                        modifier = Modifier.fillMaxWidth()
                    )

                    OutlinedTextField(
                        value = newSubInsight,
                        onValueChange = { newSubInsight = it },
                        label = { Text("Key Insight Callout") },
                        placeholder = { Text("Cash flow is the lifeblood of sustainable strategy.") },
                        modifier = Modifier.fillMaxWidth()
                    )

                    OutlinedTextField(
                        value = newSubQuizQuestion,
                        onValueChange = { newSubQuizQuestion = it },
                        label = { Text("First Interactive Quiz Question") },
                        placeholder = { Text("Which metric measures company liquidity ratio?") },
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            },
            confirmButton = {
                Button(
                    onClick = {
                        if (newSubTitle.isNotBlank()) {
                            EngageDataRepository.addNewSubject(
                                title = newSubTitle.trim(),
                                code = newSubCode.trim(),
                                description = newSubDesc.trim(),
                                icon = newSubIcon.trim().ifBlank { "📘" },
                                firstModuleTitle = newSubFirstModTitle.trim(),
                                firstModuleInsight = newSubInsight.trim(),
                                firstQuizQuestion = newSubQuizQuestion.trim()
                            )
                            newSubTitle = ""
                            newSubCode = ""
                            newSubDesc = ""
                            newSubFirstModTitle = ""
                            newSubInsight = ""
                            newSubQuizQuestion = ""
                            showAddSubjectDialog = false
                        }
                    },
                    enabled = newSubTitle.isNotBlank(),
                    modifier = Modifier.testTag("btn_confirm_add_subject")
                ) {
                    Text("Publish to Students")
                }
            },
            dismissButton = {
                TextButton(onClick = { showAddSubjectDialog = false }) {
                    Text("Cancel")
                }
            }
        )
    }

    // DIALOG: CREATE REMEDIAL TASK
    if (showCreateTaskDialog) {
        AlertDialog(
            onDismissRequest = { showCreateTaskDialog = false },
            title = {
                Text(
                    text = "Assign Intervention Task",
                    style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold)
                )
            },
            text = {
                Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                    OutlinedTextField(
                        value = newTaskStudent,
                        onValueChange = { newTaskStudent = it },
                        label = { Text("Target Student") },
                        modifier = Modifier.fillMaxWidth()
                    )

                    OutlinedTextField(
                        value = newTaskSubject,
                        onValueChange = { newTaskSubject = it },
                        label = { Text("Subject") },
                        modifier = Modifier.fillMaxWidth()
                    )

                    OutlinedTextField(
                        value = newTaskTitle,
                        onValueChange = { newTaskTitle = it },
                        label = { Text("Task Title *") },
                        placeholder = { Text("e.g. Active Recall on EOQ Formulation") },
                        modifier = Modifier.fillMaxWidth()
                    )

                    OutlinedTextField(
                        value = newTaskDescription,
                        onValueChange = { newTaskDescription = it },
                        label = { Text("Remedial Guidance") },
                        placeholder = { Text("Review Module 02 sections and retake quiz.") },
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            },
            confirmButton = {
                Button(
                    onClick = {
                        if (newTaskTitle.isNotBlank()) {
                            EngageDataRepository.createTeacherAssignment(
                                title = newTaskTitle,
                                description = newTaskDescription,
                                subjectName = newTaskSubject,
                                studentName = newTaskStudent
                            )
                            newTaskTitle = ""
                            newTaskDescription = ""
                            showCreateTaskDialog = false
                        }
                    },
                    enabled = newTaskTitle.isNotBlank()
                ) {
                    Text("Submit for HOD Approval")
                }
            },
            dismissButton = {
                TextButton(onClick = { showCreateTaskDialog = false }) {
                    Text("Cancel")
                }
            }
        )
    }
}
