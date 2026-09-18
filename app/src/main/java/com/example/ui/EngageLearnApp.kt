package com.example.ui

import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AutoStories
import androidx.compose.material.icons.filled.BarChart
import androidx.compose.material.icons.filled.Forum
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Psychology
import androidx.compose.material.icons.outlined.AutoStories
import androidx.compose.material.icons.outlined.BarChart
import androidx.compose.material.icons.outlined.Forum
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Psychology
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.EngageDataRepository
import com.example.ui.components.AppTopBar
import com.example.ui.components.NotificationsDialog
import com.example.ui.screens.CommunityScreen
import com.example.ui.screens.HomeScreen
import com.example.ui.screens.LoginScreen
import com.example.ui.screens.MicroLessonScreen
import com.example.ui.screens.ProfileScreen
import com.example.ui.screens.ProgressScreen
import com.example.ui.screens.QuizCentreScreen
import com.example.ui.screens.QuizScreen
import com.example.ui.screens.SplashScreen
import com.example.ui.screens.SubjectDetailScreen
import com.example.ui.screens.SubjectsScreen
import com.example.ui.screens.TeacherHodPortalScreen
import com.example.ui.screens.TestingResearchScreen
import com.example.ui.theme.MyApplicationTheme

sealed class Screen {
    data object Splash : Screen()
    data object Login : Screen()
    data object Home : Screen()
    data object Subjects : Screen()
    data class SubjectDetail(val subjectId: String) : Screen()
    data class MicroLesson(val subjectId: String, val moduleId: String) : Screen()
    data class Quiz(val subjectId: String) : Screen()
    data object QuizCentre : Screen()
    data object Progress : Screen()
    data object Community : Screen()
    data object Profile : Screen()
    data object FacultyPortal : Screen()
    data object TestingSuite : Screen()
}

enum class NavDestination(
    val label: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    val testTag: String
) {
    HOME("Home", Icons.Filled.Home, Icons.Outlined.Home, "nav_item_home"),
    SUBJECTS("Subjects", Icons.Filled.AutoStories, Icons.Outlined.AutoStories, "nav_item_subjects"),
    QUIZ("Quiz", Icons.Filled.Psychology, Icons.Outlined.Psychology, "nav_item_quiz"),
    PROGRESS("Progress", Icons.Filled.BarChart, Icons.Outlined.BarChart, "nav_item_progress"),
    COMMUNITY("Community", Icons.Filled.Forum, Icons.Outlined.Forum, "nav_item_community"),
    PROFILE("Profile", Icons.Filled.Person, Icons.Outlined.Person, "nav_item_profile")
}

@Composable
fun EngageLearnApp() {
    var isDarkTheme by remember { mutableStateOf(false) }
    var currentScreen by remember { mutableStateOf<Screen>(Screen.Splash) }
    var showNotificationsDialog by remember { mutableStateOf(false) }

    val user by EngageDataRepository.currentUser.collectAsState()
    val subjects by EngageDataRepository.subjects.collectAsState()
    val quizAttempts by EngageDataRepository.quizAttempts.collectAsState()
    val badges by EngageDataRepository.badges.collectAsState()
    val communityPosts by EngageDataRepository.communityPosts.collectAsState()
    val notifications by EngageDataRepository.notifications.collectAsState()
    val analyticsLogs by EngageDataRepository.analyticsLogs.collectAsState()
    val assignments by EngageDataRepository.teacherAssignments.collectAsState()

    // Handle back button logically
    BackHandler(enabled = currentScreen !is Screen.Splash && currentScreen !is Screen.Login && currentScreen !is Screen.Home) {
        currentScreen = when (currentScreen) {
            is Screen.SubjectDetail -> Screen.Subjects
            is Screen.MicroLesson -> {
                val subId = (currentScreen as Screen.MicroLesson).subjectId
                Screen.SubjectDetail(subId)
            }
            is Screen.Quiz -> Screen.QuizCentre
            is Screen.FacultyPortal, is Screen.TestingSuite -> Screen.Profile
            else -> Screen.Home
        }
    }

    MyApplicationTheme(darkTheme = isDarkTheme) {
        when (currentScreen) {
            is Screen.Splash -> {
                SplashScreen(
                    onContinue = { currentScreen = Screen.Login }
                )
            }
            is Screen.Login -> {
                LoginScreen(
                    onLoginSuccess = { role ->
                        currentScreen = if (role == "Teacher" || role == "HOD") Screen.FacultyPortal else Screen.Home
                    }
                )
            }
            else -> {
            Scaffold(
                topBar = {
                    AppTopBar(
                        user = user,
                        isDarkTheme = isDarkTheme,
                        notificationCount = notifications.size,
                        onToggleTheme = { isDarkTheme = !isDarkTheme },
                        onOpenNotifications = { showNotificationsDialog = true },
                        onProfileClick = { currentScreen = Screen.Profile }
                    )
                },
                bottomBar = {
                    val currentNav = when (currentScreen) {
                        is Screen.Home -> NavDestination.HOME
                        is Screen.Subjects, is Screen.SubjectDetail, is Screen.MicroLesson -> NavDestination.SUBJECTS
                        is Screen.Quiz, is Screen.QuizCentre -> NavDestination.QUIZ
                        is Screen.Progress -> NavDestination.PROGRESS
                        is Screen.Community -> NavDestination.COMMUNITY
                        is Screen.Profile, is Screen.FacultyPortal, is Screen.TestingSuite -> NavDestination.PROFILE
                        else -> null
                    }

                    NavigationBar(
                        containerColor = MaterialTheme.colorScheme.surface,
                        tonalElevation = 3.dp,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        NavDestination.entries.forEach { item ->
                            val selected = currentNav == item
                            NavigationBarItem(
                                selected = selected,
                                onClick = {
                                    currentScreen = when (item) {
                                        NavDestination.HOME -> Screen.Home
                                        NavDestination.SUBJECTS -> Screen.Subjects
                                        NavDestination.QUIZ -> Screen.QuizCentre
                                        NavDestination.PROGRESS -> Screen.Progress
                                        NavDestination.COMMUNITY -> Screen.Community
                                        NavDestination.PROFILE -> Screen.Profile
                                    }
                                },
                                icon = {
                                    Icon(
                                        imageVector = if (selected) item.selectedIcon else item.unselectedIcon,
                                        contentDescription = item.label,
                                        modifier = Modifier.size(22.dp)
                                    )
                                },
                                label = {
                                    Text(
                                        text = item.label,
                                        fontSize = 11.sp,
                                        fontWeight = if (selected) FontWeight.Bold else FontWeight.Medium
                                    )
                                },
                                colors = NavigationBarItemDefaults.colors(
                                    selectedIconColor = MaterialTheme.colorScheme.primary,
                                    selectedTextColor = MaterialTheme.colorScheme.primary,
                                    indicatorColor = MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.6f)
                                ),
                                modifier = Modifier.testTag(item.testTag)
                            )
                        }
                    }
                }
            ) { innerPadding ->
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding)
                ) {
                    AnimatedContent(
                        targetState = currentScreen,
                        transitionSpec = { fadeIn() togetherWith fadeOut() },
                        label = "screen_transition"
                    ) { screen ->
                        when (screen) {
                            is Screen.Splash, is Screen.Login -> {
                                // Handled in outer branches
                            }

                            is Screen.Home -> {
                                HomeScreen(
                                    user = user,
                                    subjects = subjects,
                                    quizAttempts = quizAttempts,
                                    onContinueLearning = {
                                        // Pick first subject with incomplete module, or default to first
                                        val subWithIncomplete = subjects.firstOrNull { it.completedCount < it.totalModules } ?: subjects.first()
                                        val firstIncompleteModule = subWithIncomplete.modules.firstOrNull { !it.isCompleted } ?: subWithIncomplete.modules.first()
                                        currentScreen = Screen.MicroLesson(subWithIncomplete.id, firstIncompleteModule.id)
                                    },
                                    onSubjectClick = { subId ->
                                        currentScreen = Screen.SubjectDetail(subId)
                                    },
                                    onViewAllSubjects = {
                                        currentScreen = Screen.Subjects
                                    },
                                    onQuizClick = {
                                        currentScreen = Screen.QuizCentre
                                    }
                                )
                            }

                            is Screen.Subjects -> {
                                SubjectsScreen(
                                    subjects = subjects,
                                    onSubjectClick = { subId ->
                                        currentScreen = Screen.SubjectDetail(subId)
                                    }
                                )
                            }

                            is Screen.SubjectDetail -> {
                                val subject = subjects.firstOrNull { it.id == screen.subjectId } ?: subjects.first()
                                SubjectDetailScreen(
                                    subject = subject,
                                    onBack = { currentScreen = Screen.Subjects },
                                    onStartLesson = { moduleId ->
                                        currentScreen = Screen.MicroLesson(subject.id, moduleId)
                                    },
                                    onStartQuiz = { subId ->
                                        currentScreen = Screen.Quiz(subId)
                                    }
                                )
                            }

                            is Screen.MicroLesson -> {
                                val subject = subjects.firstOrNull { it.id == screen.subjectId } ?: subjects.first()
                                val module = subject.modules.firstOrNull { it.id == screen.moduleId } ?: subject.modules.first()
                                MicroLessonScreen(
                                    subject = subject,
                                    module = module,
                                    onBack = { currentScreen = Screen.SubjectDetail(subject.id) },
                                    onMarkComplete = {
                                        EngageDataRepository.markModuleCompleted(subject.id, module.id)
                                    },
                                    onTakeQuiz = {
                                        currentScreen = Screen.Quiz(subject.id)
                                    }
                                )
                            }

                            is Screen.Quiz -> {
                                val subject = subjects.firstOrNull { it.id == screen.subjectId } ?: subjects.first()
                                val questions = EngageDataRepository.getQuizQuestionsForSubject(subject.id)
                                QuizScreen(
                                    subject = subject,
                                    questions = questions,
                                    onBack = { currentScreen = Screen.QuizCentre },
                                    onViewProgress = { currentScreen = Screen.Progress }
                                )
                            }

                            is Screen.QuizCentre -> {
                                QuizCentreScreen(
                                    subjects = subjects,
                                    quizAttempts = quizAttempts,
                                    onStartQuiz = { subId ->
                                        currentScreen = Screen.Quiz(subId)
                                    }
                                )
                            }

                            is Screen.Progress -> {
                                ProgressScreen(
                                    user = user,
                                    subjects = subjects,
                                    quizAttempts = quizAttempts,
                                    badges = badges
                                )
                            }

                            is Screen.Community -> {
                                CommunityScreen(
                                    user = user,
                                    posts = communityPosts
                                )
                            }

                            is Screen.Profile -> {
                                ProfileScreen(
                                    user = user,
                                    subjects = subjects,
                                    isDarkTheme = isDarkTheme,
                                    onToggleTheme = { isDarkTheme = !isDarkTheme },
                                    onOpenFacultyPortal = { currentScreen = Screen.FacultyPortal },
                                    onOpenTestingSuite = { currentScreen = Screen.TestingSuite },
                                    onSignOut = {
                                        currentScreen = Screen.Login
                                    }
                                )
                            }

                            is Screen.FacultyPortal -> {
                                TeacherHodPortalScreen(
                                    currentUser = user,
                                    subjects = subjects,
                                    assignments = assignments,
                                    onBack = { currentScreen = Screen.Profile }
                                )
                            }

                            is Screen.TestingSuite -> {
                                TestingResearchScreen(
                                    analyticsLogs = analyticsLogs,
                                    onBack = { currentScreen = Screen.Profile }
                                )
                            }
                        }
                    }
                }
            }
        }
    }

    // Global Notifications Dialog
        if (showNotificationsDialog) {
            NotificationsDialog(
                notifications = notifications,
                onDismiss = { showNotificationsDialog = false },
                onClearAll = {
                    EngageDataRepository.clearNotifications()
                    showNotificationsDialog = false
                }
            )
        }
    }
}
