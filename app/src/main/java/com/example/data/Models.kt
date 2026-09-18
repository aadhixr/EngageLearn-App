package com.example.data

data class User(
    val username: String = "Ans",
    val name: String = "Ans",
    val role: String = "Student", // "Student", "Teacher", "HOD"
    val points: Int = 95
)

data class LessonSection(
    val heading: String,
    val content: String,
    val bulletPoints: List<String> = emptyList(),
    val example: String? = null
)

data class LessonModule(
    val id: String,
    val subjectId: String,
    val moduleNumber: Int,
    val title: String,
    val overview: String,
    val sections: List<LessonSection>,
    val quickInsight: String,
    val activeRecallPrompt: String,
    val activeRecallAnswer: String,
    val isCompleted: Boolean = false
)

data class Subject(
    val id: String,
    val code: String,
    val title: String,
    val icon: String,
    val description: String,
    val modules: List<LessonModule> = emptyList()
) {
    val completedCount: Int
        get() = modules.count { it.isCompleted }
    val totalModules: Int
        get() = modules.size
    val progressPercentage: Int
        get() = if (totalModules == 0) 0 else (completedCount * 100) / totalModules
}

data class QuizQuestion(
    val id: String,
    val subjectId: String,
    val question: String,
    val options: List<String>,
    val correctAnswer: Int,
    val explanation: String = ""
)

data class QuizAttempt(
    val id: String,
    val subjectId: String,
    val subjectTitle: String,
    val score: Int,
    val total: Int = 3,
    val percentage: Int,
    val date: String,
    val timestamp: Long = System.currentTimeMillis()
)

data class BadgeItem(
    val id: String,
    val title: String,
    val description: String,
    val icon: String,
    val isUnlocked: Boolean = false,
    val requirement: String = ""
)

data class CommunityPost(
    val id: String,
    val authorName: String,
    val authorRole: String,
    val content: String,
    val timeAgo: String,
    val likes: Int = 0,
    val hasLiked: Boolean = false,
    val subjectTag: String = "Strategic Management"
) {
    val likesCount: Int get() = likes
    val isLiked: Boolean get() = hasLiked
}

data class NotificationItem(
    val id: String,
    val title: String,
    val message: String,
    val timeAgo: String,
    val iconType: String = "info" // "quiz", "badge", "lesson", "community"
)

data class AnalyticsLog(
    val id: String,
    val eventType: String,
    val description: String,
    val timestamp: String,
    val pointsAwarded: Int = 0
) {
    val action: String get() = eventType
    val details: String get() = description
}

data class UsabilityTestingSession(
    val participantId: String = "P01",
    val preAttention: Int = 2,
    val preMotivation: Int = 2,
    val preInteraction: Int = 2,
    val preSatisfaction: Int = 3,
    val taskLessonCompleted: Boolean = true,
    val taskQuizAttempted: Boolean = true,
    val taskQuizScore: String = "3/3",
    val taskViewedProgress: Boolean = true,
    val postAttention: Int = 4,
    val postMotivation: Int = 5,
    val postInteraction: Int = 4,
    val postSatisfaction: Int = 5,
    val notes: String = "Observed high active recall participation and immediate quiz validation."
)

data class TeacherAssignment(
    val id: String,
    val subjectId: String,
    val subjectTitle: String,
    val title: String,
    val description: String,
    val targetStudent: String = "Ans",
    val isHodApproved: Boolean = true,
    val isCompleted: Boolean = false,
    val dueDate: String = "Tomorrow"
) {
    val subjectName: String get() = subjectTitle
    val assignedToStudent: String get() = targetStudent
    val assignedByTeacher: String get() = "Prof. Anderson"
}

data class StudentSubjectProgress(
    val subjectId: String,
    val subjectTitle: String,
    val subjectIcon: String,
    val completedModules: Int,
    val totalModules: Int,
    val percentage: Int
)

data class StudentPerformance(
    val studentId: String,
    val name: String,
    val email: String,
    val points: Int,
    val quizAverage: Int,
    val totalQuizzesTaken: Int,
    val subjectProgress: List<StudentSubjectProgress>,
    val weakSubject: String,
    val engagementStatus: String // "Excellent", "Active", "Needs Attention"
) {
    val totalModulesCompleted: Int get() = subjectProgress.sumOf { it.completedModules }
    val totalModules: Int get() = subjectProgress.sumOf { it.totalModules }
    val overallPercentage: Int get() = if (totalModules == 0) 0 else (totalModulesCompleted * 100) / totalModules
}

