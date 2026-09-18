# EngageLearn 🎓
> **Interactive Academic Learning & Engagement Platform**
> Micro-Learning • Active Recall • Gamified Quizzes • Faculty Oversight • Usability Research

[![Android](https://img.shields.io/badge/Platform-Android-3DDC84?logo=android&logoColor=white)](https://developer.android.com/)
[![Kotlin](https://img.shields.io/badge/Language-Kotlin-7F52FF?logo=kotlin&logoColor=white)](https://kotlinlang.org/)
[![Jetpack Compose](https://img.shields.io/badge/UI-Jetpack%20Compose%20(M3)-4285F4?logo=jetpackcompose&logoColor=white)](https://developer.android.com/jetpack/compose)
[![Material 3](https://img.shields.io/badge/Design-Material%203-green)](https://m3.material.io/)
[![Gradle](https://img.shields.io/badge/Build-Gradle%20KTS-02303A?logo=gradle&logoColor=white)](https://gradle.org/)

---

## 📌 Overview

**EngageLearn** is a modern, native Android application engineered to enhance academic engagement, retention, and student motivation through evidence-based pedagogical frameworks. Designed specifically for higher education and professional business studies, EngageLearn blends **micro-learning**, **interactive active recall**, **gamified assessment loops**, and **faculty supervision** in a high-contrast Material 3 interface.

---

## ✨ Key Capabilities & Feature Modules

### 1. 🚀 Splash & Guided Onboarding
- Branded launch sequence displaying the official EngageLearn emblem, academic badge, and smooth animated scale/fade transitions.
- Quick-start action automatically routing users into role selection and login.

### 2. 🔐 Multi-Role Authentication
- Seamless role-based access for three primary educational stakeholders:
  - **Student**: Personalized learning dashboard, progress bars, active recall, quiz attempts, and community feed.
  - **Teacher / Faculty**: Real-time cohort analytics, weak-subject detection, and assignment issuance.
  - **Head of Department (HOD)**: Departmental curriculum oversight and assignment approval workflow.
- Preset one-tap demo accounts for instant evaluation alongside custom credential validation.

### 3. 📚 Micro-Learning Engine
- Lessons partitioned into digestible micro-units designed to prevent cognitive fatigue.
- Each module features:
  - **Conceptual Overviews**: Concise, structured foundational theory.
  - **Bullet-Point Takeaways**: Scannable, actionable knowledge points.
  - **Real-World Business Examples**: Pragmatic enterprise case studies (e.g., Apple, Netflix, Walmart, industrial IoT).
  - **Quick Insights**: Memorable industry maxims highlighting strategic rules of thumb.
  - **Interactive Active Recall**: Flip-to-reveal self-assessment cards that reinforce memory consolidation before module sign-off.

### 4. 🎯 Quiz Centre & Adaptive Assessments
- Multiple-choice assessment engine tailored to each academic subject.
- Instant score computation, detailed rationale explanations for every option, historical attempt logs, and bonus XP rewards.

### 5. 🏆 Gamification & Progress Tracking
- **Points (XP) & Levels**: Earn XP for every completed lesson, active recall review, and quiz passed.
- **Milestone Badges**: Unlockable achievements (*First Step*, *Quiz Master*, *Streak Warrior*, *Subject Champion*).
- **Streak Tracker**: Visual daily learning streak counters to reinforce habitual study routines.
- **Activity Audit Log**: Live, timestamped ledger of every educational milestone achieved.

### 6. 👩‍🏫 Faculty & HOD Oversight Portal
- **Cohort Analytics**: Deep-dive student performance metrics, including module completion percentages, quiz averages, and engagement status flags (*Active*, *Needs Attention*, *Excellent*).
- **Weak Subject Early-Warning**: Highlights topics where students need supplementary guidance.
- **Curriculum Assignment Workflow**: Teachers can draft and dispatch targeted coursework, routed through HOD validation for academic alignment.

### 7. 💬 Academic Community Hub
- Subject-filtered collaborative discussion forum.
- Enables peer-to-peer questioning, knowledge sharing, and peer encouragement with like interactions and author role badges.

### 8. 🧪 Empirical Research & Usability Module
- Built-in pedagogical testing laboratory tracking Likert-scale metrics before and after study sessions:
  - **Attention**
  - **Motivation**
  - **Interaction**
  - **Satisfaction**
- Facilitates empirical academic studies, pilot validation, and usability telemetry.

---

## 📖 Complete Academic Curriculum

The platform includes **7 foundational subject domains** spanning **21 structured micro-units**:

| # | Subject Domain | Icon | Key Modules & Topics |
| :-: | :--- | :--: | :--- |
| **01** | **Strategic Management** | ♟️ | Strategy Hierarchies (Corporate, Business, Functional) • PESTLE & VRIO Analysis • Porter's Generic Strategies & Execution Hurdles |
| **02** | **Logistics & SCM** | 🚚 | Triple Flows (Goods, Info, Funds) • Bullwhip Mitigation • EOQ Inventory Modeling • Cross-Docking & Last-Mile Delivery |
| **03** | **Design Thinking & Innovation** | 💡 | Empathy Mapping (Says, Thinks, Does, Feels) • "How Might We" Problem Framing • SCAMPER Protocol • Rapid Low-Fidelity Prototyping |
| **04** | **AI for Business** | 🤖 | Supervised vs. Unsupervised Learning • Enterprise Predictive Maintenance & Churn Forecasting • Responsible AI Governance & Explainability (XAI) |
| **05** | **Consumer Behaviour** | 🛍️ | 5-Stage Consumer Decision Process • Heuristics & Framing Effects • Loss Aversion • Reference Groups & Social Proof |
| **06** | **Human Resource Management** | 👥 | Strategic HRM & Employer Value Proposition (EVP) • STAR Structured Behavioral Interviews • Continuous 360° Feedback & OKRs |
| **07** | **Change Management & OD** | 🔄 | The Kübler-Ross Change Curve • Psychology of Employee Resistance • Lewin’s 3-Step Model • Kotter’s 8-Step Change Framework |

---

## 🛠️ Architecture & Tech Stack

```
com.example/
├── MainActivity.kt               # Single-activity container with edge-to-edge support
├── data/
│   ├── Models.kt                 # Type-safe immutable domain data classes
│   └── EngageDataRepository.kt   # Reactive StateFlow repository & state engine
└── ui/
    ├── EngageLearnApp.kt         # Master Compose navigation & top-level layout
    ├── components/
    │   ├── AppTopBar.kt          # Material 3 dynamic top bar with branding
    │   └── NotificationsDialog.kt# System notification modal
    ├── screens/
    │   ├── SplashScreen.kt       # Dynamic branded launch screen
    │   ├── LoginScreen.kt        # Multi-role authentication portal
    │   ├── HomeScreen.kt         # Student daily dashboard & quick resume
    │   ├── SubjectsScreen.kt     # Curriculum course directory
    │   ├── SubjectDetailScreen.kt# Module listing & progress tracker
    │   ├── MicroLessonScreen.kt  # Micro-reading & active recall view
    │   ├── QuizCentreScreen.kt   # Subject quiz catalog & test history
    │   ├── QuizScreen.kt         # Interactive multiple-choice quiz engine
    │   ├── ProgressScreen.kt     # Gamification badges & activity log
    │   ├── CommunityScreen.kt    # Peer discussion forum
    │   ├── TeacherHodPortalScreen.kt # Faculty student analytics & assignments
    │   ├── TestingResearchScreen.kt  # Usability & engagement metric evaluation
    │   └── ProfileScreen.kt      # Account settings & theme preferences
    └── theme/
        ├── Color.kt              # Accessible high-contrast educational palette
        ├── Theme.kt              # Material 3 light/dark color schemes
        └── Type.kt               # Scalable typography definitions
```

### Core Technologies
- **UI Toolkit**: [Jetpack Compose](https://developer.android.com/jetpack/compose) with Material Design 3 (M3).
- **Language**: Kotlin 2.0+ leveraging Coroutines and asynchronous StateFlows.
- **State Architecture**: MVVM with a centralized reactive repository pattern.
- **Theming & System Styling**: Complete Light and Dark mode with `enableEdgeToEdge()` and dynamic window insets.
- **CI/CD**: Pre-configured GitHub Actions workflows for automated Gradle test verification and APK artifact compilation.

---

## 🚀 Getting Started

### Prerequisites
- **Android Studio**: Jellyfish (2023.3.1) or Koala (2024.1.1)+
- **JDK**: Java 17 or higher
- **Android SDK**: Compile SDK 34, Min SDK 24, Target SDK 34

### Building & Running Locally

1. **Clone the repository:**
   ```bash
   git clone https://github.com/<your-username>/EngageLearn.git
   cd EngageLearn
   ```

2. **Open in Android Studio:**
   - Select **Open an Existing Project** and navigate to the project directory.
   - Allow Gradle to sync dependencies.

3. **Build via Command Line:**
   ```bash
   # Assemble Debug APK
   ./gradlew assembleDebug

   # Run local unit tests
   ./gradlew testDebugUnitTest
   ```

4. **Output APK Location:**
   After building, the debug APK is generated at:
   ```
   app/build/outputs/apk/debug/app-debug.apk
   ```

---

## 📤 Pushing to GitHub

### Option A: Using AI Studio Direct Push (Recommended)
1. In the Google AI Studio top-right settings menu, click **Push to GitHub** or **Export**.
2. Authenticate with your GitHub account and select your target repository name.
3. AI Studio will automatically push all source files, Gradle build files, and documentation to your GitHub repository.

### Option B: Using Git Command Line
```bash
# Initialize git if not already present
git init
git branch -M main

# Add files and commit
git add .
git commit -m "feat: complete EngageLearn academic micro-learning platform with README documentation"

# Add your GitHub remote and push
git remote add origin https://github.com/<your-username>/<your-repo-name>.git
git push -u origin main
```

---

## 📄 License

This project is created for educational and learning purposes under the **MIT License**.
Feel free to adapt, extend, and deploy it for your academic institution or learning organization.
