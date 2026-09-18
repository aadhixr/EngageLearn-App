package com.example.data

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

object EngageDataRepository {

    private val dateFormat = SimpleDateFormat("MMM dd, yyyy · HH:mm", Locale.getDefault())

    // Initial 7 Subjects with 3 Modules each = 21 Modules
    private fun getInitialSubjects(): List<Subject> = listOf(
        Subject(
            id = "sub_sm",
            code = "01",
            title = "Strategic Management",
            icon = "♟️",
            description = "Strategy formulation, analysis, implementation and competitive advantage.",
            modules = listOf(
                LessonModule(
                    id = "sm_mod_1",
                    subjectId = "sub_sm",
                    moduleNumber = 1,
                    title = "Strategic Management Basics",
                    overview = "Meaning, levels of strategy and the strategic management process.",
                    sections = listOf(
                        LessonSection(
                            heading = "What is Strategy?",
                            content = "Strategy is an integrated and coordinated set of commitments and actions designed to exploit core competencies and gain a competitive advantage.",
                            bulletPoints = listOf(
                                "Corporate Level: Which businesses should we compete in?",
                                "Business Level: How do we compete effectively in each market?",
                                "Functional Level: How do operational departments support the business strategy?"
                            ),
                            example = "Example: Apple differentiates through premium design and integrated hardware-software ecosystems rather than competing on low price."
                        ),
                        LessonSection(
                            heading = "The Strategic Management Process",
                            content = "The strategic management process involves continuous scanning, strategy crafting, execution, and evaluation.",
                            bulletPoints = listOf(
                                "Strategic Intent: Vision, mission, and strategic objectives",
                                "Environmental Scanning: Internal strengths and external threats",
                                "Strategy Formulation: Developing competitive alternatives",
                                "Strategy Implementation: Allocating resources and aligning structures",
                                "Evaluation & Control: Measuring real-world results against benchmarks"
                            ),
                            example = "Example: Netflix pivoted from DVD mailing to video streaming and then original content production to stay ahead of market disruptions."
                        )
                    ),
                    quickInsight = "Strategy requires both deliberate choice and effective execution.",
                    activeRecallPrompt = "What are the three distinct hierarchical levels of strategy?",
                    activeRecallAnswer = "Corporate level, Business level, and Functional level.",
                    isCompleted = true
                ),
                LessonModule(
                    id = "sm_mod_2",
                    subjectId = "sub_sm",
                    moduleNumber = 2,
                    title = "Environmental & Internal Analysis",
                    overview = "Macro-environmental PESTLE analysis, Porter's Five Forces, and VRIO resource-based view.",
                    sections = listOf(
                        LessonSection(
                            heading = "External Macro Analysis (PESTLE)",
                            content = "Organizations exist within complex macro-environments governed by six broad external forces.",
                            bulletPoints = listOf(
                                "Political: Government policies, tax regulations, and trade tariffs",
                                "Economic: Inflation, interest rates, exchange rates, and disposable income",
                                "Social: Demographic shifts, lifestyle trends, and consumer cultural preferences",
                                "Technological: R&D pace, automation, and digital platforms",
                                "Legal: Employment laws, safety standards, and intellectual property rights",
                                "Environmental: Climate policies, sustainability criteria, and carbon footprints"
                            ),
                            example = "Example: Electric vehicle manufacturers must continually evaluate PESTLE factors such as government subsidies and battery recycling laws."
                        ),
                        LessonSection(
                            heading = "Internal Core Competency (VRIO)",
                            content = "A resource provides sustained competitive advantage only when it satisfies all four criteria: Value, Rarity, Inimitability, and Organization.",
                            bulletPoints = listOf(
                                "Value: Neutralizes threats or exploits market opportunities",
                                "Rarity: Controlled by only a limited number of competitors",
                                "Inimitability: Costly or difficult for rivals to replicate",
                                "Organization: Company is structured to exploit the resource potential"
                            )
                        )
                    ),
                    quickInsight = "External analysis reveals what you might do; internal analysis determines what you can do.",
                    activeRecallPrompt = "What four tests must a capability pass under VRIO for sustainable advantage?",
                    activeRecallAnswer = "Value, Rarity, Inimitability, and Organizational alignment.",
                    isCompleted = true
                ),
                LessonModule(
                    id = "sm_mod_3",
                    subjectId = "sub_sm",
                    moduleNumber = 3,
                    title = "Strategy Formulation & Implementation",
                    overview = "Porter's Generic Strategies, balance scorecards, and strategy execution hurdles.",
                    sections = listOf(
                        LessonSection(
                            heading = "Generic Competitive Strategies",
                            content = "Michael Porter identified three fundamental positional routes to outperforming industry rivals.",
                            bulletPoints = listOf(
                                "Cost Leadership: Becoming the lowest-cost producer in the industry (e.g., Walmart)",
                                "Differentiation: Offering unique attributes valued by customers for a price premium",
                                "Focus / Niche: Concentrating narrowly on a specific customer segment, geographic area, or product line"
                            )
                        ),
                        LessonSection(
                            heading = "Bridging the Execution Gap",
                            content = "Over 70% of strategic failures stem from poor execution rather than faulty strategic logic. Alignment of culture, reward systems, and communication is vital.",
                            bulletPoints = listOf(
                                "Clear KPI cascade across every functional team",
                                "Agile feedback loops to pivot upon market shifts"
                            )
                        )
                    ),
                    quickInsight = "Culture eats strategy for breakfast. Execution bridges the vision to reality.",
                    activeRecallPrompt = "What are Porter's three generic competitive strategies?",
                    activeRecallAnswer = "Cost Leadership, Differentiation, and Focus.",
                    isCompleted = false
                )
            )
        ),
        Subject(
            id = "sub_scm",
            code = "02",
            title = "Logistics and Supply Chain Management",
            icon = "🚚",
            description = "End-to-end supply chain integration, inventory optimization, and distribution networks.",
            modules = listOf(
                LessonModule(
                    id = "scm_mod_1",
                    subjectId = "sub_scm",
                    moduleNumber = 1,
                    title = "SCM Fundamentals",
                    overview = "Supply chain definitions, flows of goods, information, funds, and the bullwhip effect.",
                    sections = listOf(
                        LessonSection(
                            heading = "The Triple Flow of Supply Chains",
                            content = "Modern supply chains manage three synchronized flows across suppliers, manufacturers, distributors, and end consumers.",
                            bulletPoints = listOf(
                                "Material Flow: Physical progression of raw materials into finished goods",
                                "Information Flow: Demand forecasts, order confirmations, and tracking data",
                                "Financial Flow: Credit terms, payment schedules, and invoices"
                            )
                        ),
                        LessonSection(
                            heading = "The Bullwhip Effect",
                            content = "Small fluctuations in retail customer demand amplify exponentially as orders travel upstream toward raw material suppliers.",
                            example = "Remedy: Implement Point-of-Sale (POS) data sharing and vendor-managed inventory (VMI) across tiers."
                        )
                    ),
                    quickInsight = "True supply chain competition is supply chain vs. supply chain, not company vs. company.",
                    activeRecallPrompt = "What three primary streams flow through an integrated supply chain?",
                    activeRecallAnswer = "Materials/Products, Information, and Finances/Funds.",
                    isCompleted = true
                ),
                LessonModule(
                    id = "scm_mod_2",
                    subjectId = "sub_scm",
                    moduleNumber = 2,
                    title = "Inventory & Warehousing",
                    overview = "Economic Order Quantity (EOQ), Just-in-Time (JIT), cross-docking, and warehouse management systems.",
                    sections = listOf(
                        LessonSection(
                            heading = "Balancing Holding and Ordering Costs",
                            content = "Inventory management seeks to balance carrying costs with stockout risks and ordering expenses.",
                            bulletPoints = listOf(
                                "EOQ Model: Mathematical optimum batch order size that minimizes total inventory costs",
                                "Safety Stock: Buffer buffer against lead time uncertainty and sudden demand surges",
                                "Cross-Docking: Direct transfer of incoming freight directly to outbound trailers with minimal storage"
                            )
                        )
                    ),
                    quickInsight = "Inventory is often described as money sitting on shelves; lean flow unlocks operating cash.",
                    activeRecallPrompt = "What trade-off does the Economic Order Quantity (EOQ) formula balance?",
                    activeRecallAnswer = "Holding (carrying) costs versus ordering (setup) costs.",
                    isCompleted = true
                ),
                LessonModule(
                    id = "scm_mod_3",
                    subjectId = "sub_scm",
                    moduleNumber = 3,
                    title = "Transportation & Distribution",
                    overview = "Intermodal transport, last-mile logistics, route optimization, and carbon footprint reduction.",
                    sections = listOf(
                        LessonSection(
                            heading = "Multimodal Logistics Networks",
                            content = "Selecting optimal transport modes requires evaluating speed, reliability, capacity, and cost per ton-kilometer.",
                            bulletPoints = listOf(
                                "Rail & Water: High volume, energy efficient, longer lead times",
                                "Road: High door-to-door flexibility and agile scheduling",
                                "Air: Maximum speed, high cost, ideal for high-value perishable goods",
                                "Last-Mile Delivery: The costliest 28% to 53% of the total shipping journey"
                            )
                        )
                    ),
                    quickInsight = "The last mile is the most complex and expensive link in the logistics chain.",
                    activeRecallPrompt = "Which transportation mode provides the fastest transit time for high-value items?",
                    activeRecallAnswer = "Air freight.",
                    isCompleted = false
                )
            )
        ),
        Subject(
            id = "sub_dt",
            code = "03",
            title = "Design Thinking and Innovation",
            icon = "💡",
            description = "Human-centered problem solving, empathy mapping, rapid prototyping, and iterative testing.",
            modules = listOf(
                LessonModule(
                    id = "dt_mod_1",
                    subjectId = "sub_dt",
                    moduleNumber = 1,
                    title = "Empathise & Define",
                    overview = "User immersion, observation techniques, journey mapping, and Point-of-View (POV) statements.",
                    sections = listOf(
                        LessonSection(
                            heading = "The Empathy Foundation",
                            content = "Design thinking starts by immersing yourself in the user's emotional reality rather than assuming their pain points.",
                            bulletPoints = listOf(
                                "Empathy Interviews: Open-ended 'tell me about the last time' inquiries",
                                "Observation: Watching non-verbal struggles in real work contexts",
                                "Empathy Map: Segregating observations into Says, Thinks, Does, and Feels"
                            )
                        ),
                        LessonSection(
                            heading = "Framing the Problem Statement",
                            content = "Transform research findings into actionable 'How Might We' (HMW) challenge questions that invite creative ideas.",
                            example = "Formula: [User] needs [Need] because [Surprising Insight]."
                        )
                    ),
                    quickInsight = "Fall in love with the problem, not with your first solution.",
                    activeRecallPrompt = "What are the four quadrants of a standard Empathy Map?",
                    activeRecallAnswer = "Says, Thinks, Does, and Feels.",
                    isCompleted = true
                ),
                LessonModule(
                    id = "dt_mod_2",
                    subjectId = "sub_dt",
                    moduleNumber = 2,
                    title = "Ideate & Develop",
                    overview = "Divergent vs convergent thinking, brainstorming protocols, SCAMPER, and dot voting.",
                    sections = listOf(
                        LessonSection(
                            heading = "Divergent Exploration",
                            content = "In ideation, quantity breeds quality. Defer judgment and welcome unconventional combinations before converging.",
                            bulletPoints = listOf(
                                "Go for Volume: The best way to have a good idea is to have many ideas",
                                "Build on Others' Ideas: Saying 'Yes, and...' rather than 'No, but...'",
                                "SCAMPER: Substitute, Combine, Adapt, Modify, Put to other use, Eliminate, Reverse"
                            )
                        )
                    ),
                    quickInsight = "Defer judgment during ideation to allow fragile novel ideas to mature.",
                    activeRecallPrompt = "What principle should teams apply when building upon a teammate's concept?",
                    activeRecallAnswer = "'Yes, and...' to extend the creative possibility.",
                    isCompleted = true
                ),
                LessonModule(
                    id = "dt_mod_3",
                    subjectId = "sub_dt",
                    moduleNumber = 3,
                    title = "Prototype, Test & Iterate",
                    overview = "Low-fidelity paper prototypes, Wizard of Oz tests, user feedback grids, and pivoting.",
                    sections = listOf(
                        LessonSection(
                            heading = "Low-Fidelity Prototyping",
                            content = "Build to think, not just to validate. A prototype should only be as detailed as necessary to answer the current question.",
                            bulletPoints = listOf(
                                "Paper Wireframes: Cheap, disposable, and frictionless to throw away",
                                "Storyboarding: Contextualizing how the solution fits user daily routines",
                                "Feedback Capture Grid: Likes, Criticisms, Questions, and New Ideas"
                            )
                        )
                    ),
                    quickInsight = "If a picture is worth a thousand words, a prototype is worth a thousand meetings.",
                    activeRecallPrompt = "Why are low-fidelity prototypes preferred in early testing cycles?",
                    activeRecallAnswer = "They are fast, inexpensive, and encourage open feedback without defensive attachment.",
                    isCompleted = false
                )
            )
        ),
        Subject(
            id = "sub_ai",
            code = "04",
            title = "AI for Business",
            icon = "🤖",
            description = "Machine learning fundamentals, operational applications, and responsible AI governance.",
            modules = listOf(
                LessonModule(
                    id = "ai_mod_1",
                    subjectId = "sub_ai",
                    moduleNumber = 1,
                    title = "AI Foundations for Business",
                    overview = "Supervised vs unsupervised learning, neural networks, foundation models, and business value creation.",
                    sections = listOf(
                        LessonSection(
                            heading = "Core Paradigms of AI",
                            content = "Business AI centers around statistical pattern discovery that converts raw enterprise data into predictive insights.",
                            bulletPoints = listOf(
                                "Supervised Learning: Trained on labeled input-output pairs for classification and regression",
                                "Unsupervised Learning: Discovers hidden clusters and customer segmentation without prior labels",
                                "Generative AI: Models trained on vast tokens to synthesize novel text, code, images, and simulations"
                            )
                        )
                    ),
                    quickInsight = "AI does not replace managers, but managers who use AI will replace those who do not.",
                    activeRecallPrompt = "Which learning paradigm uses labeled historical targets to forecast outcomes?",
                    activeRecallAnswer = "Supervised learning.",
                    isCompleted = true
                ),
                LessonModule(
                    id = "ai_mod_2",
                    subjectId = "sub_ai",
                    moduleNumber = 2,
                    title = "AI Applications",
                    overview = "Predictive maintenance, churn forecasting, automated conversational agents, and dynamic pricing.",
                    sections = listOf(
                        LessonSection(
                            heading = "Enterprise AI Implementations",
                            content = "High-ROI business AI targets high-frequency, repetitive decisions with quantifiable benchmarks.",
                            bulletPoints = listOf(
                                "Predictive Maintenance: Industrial IoT sensors predicting machine degradation before failure",
                                "Customer Churn Prediction: Early-warning scoring to trigger retention workflows",
                                "Dynamic Pricing: Real-time supply-demand pricing algorithms used in hospitality and rideshare"
                            )
                        )
                    ),
                    quickInsight = "Focus AI pilots where data is abundant, decisions are frequent, and errors are low-cost.",
                    activeRecallPrompt = "Name a widespread application of AI in customer retention.",
                    activeRecallAnswer = "Customer churn prediction scoring.",
                    isCompleted = false
                ),
                LessonModule(
                    id = "ai_mod_3",
                    subjectId = "sub_ai",
                    moduleNumber = 3,
                    title = "Responsible AI",
                    overview = "Algorithmic bias, data privacy, explainability (XAI), and corporate AI governance.",
                    sections = listOf(
                        LessonSection(
                            heading = "Ethical & Transparent Governance",
                            content = "Deploying AI requires rigorous oversight to prevent algorithmic bias, protect user privacy, and ensure regulatory compliance.",
                            bulletPoints = listOf(
                                "Fairness & Bias Audits: Testing model outcomes across sensitive demographic attributes",
                                "Explainability: Ensuring decisions in credit, hiring, and healthcare can be understood by humans",
                                "Privacy & Security: Safeguarding sensitive customer proprietary data from model leakage"
                            )
                        )
                    ),
                    quickInsight = "Trust is the currency of enterprise AI adoption; explainability builds customer trust.",
                    activeRecallPrompt = "Why is Explainable AI (XAI) crucial in regulated industries like banking and healthcare?",
                    activeRecallAnswer = "To allow humans to audit, verify, and explain automated decisions to customers and regulators.",
                    isCompleted = false
                )
            )
        ),
        Subject(
            id = "sub_mkt",
            code = "05",
            title = "Marketing – Consumer Behaviour",
            icon = "🛍️",
            description = "Decision-making journeys, psychological motivators, and social influences on purchasing habits.",
            modules = listOf(
                LessonModule(
                    id = "mkt_mod_1",
                    subjectId = "sub_mkt",
                    moduleNumber = 1,
                    title = "Consumer Behaviour Foundations",
                    overview = "The 5-stage consumer decision journey, cognitive biases, and high vs low involvement purchases.",
                    sections = listOf(
                        LessonSection(
                            heading = "The Decision Process Model",
                            content = "Consumers navigate five predictable cognitive gates when fulfilling needs.",
                            bulletPoints = listOf(
                                "1. Problem Recognition: Discrepancy between actual state and desired state",
                                "2. Information Search: Internal memory recall and external peer research",
                                "3. Alternative Evaluation: Comparing brands across salient decision criteria",
                                "4. Purchase Decision: Final channel selection and transactional execution",
                                "5. Post-Purchase Evaluation: Cognitive dissonance or loyalty advocacy"
                            )
                        )
                    ),
                    quickInsight = "The customer journey does not end at checkout; post-purchase sentiment drives retention.",
                    activeRecallPrompt = "What is the initial trigger in the classical 5-stage consumer decision process?",
                    activeRecallAnswer = "Problem or need recognition.",
                    isCompleted = true
                ),
                LessonModule(
                    id = "mkt_mod_2",
                    subjectId = "sub_mkt",
                    moduleNumber = 2,
                    title = "Psychological Influences",
                    overview = "Maslow's hierarchy of needs, selective perception, behavioral conditioning, and heuristics.",
                    sections = listOf(
                        LessonSection(
                            heading = "Perception and Motivational Drivers",
                            content = "Consumers do not react to raw reality; they react to their perceptual interpretation of marketing stimuli.",
                            bulletPoints = listOf(
                                "Selective Attention: Consumers filter out 95% of commercial impressions",
                                "Framing Effect: How price or benefits are phrased influences choice (e.g., 90% fat-free vs 10% fat)",
                                "Loss Aversion: The psychological pain of losing is twice as potent as the pleasure of gaining"
                            )
                        )
                    ),
                    quickInsight = "Perception is reality in the mind of the consumer.",
                    activeRecallPrompt = "According to behavioral economics, is loss aversion stronger or weaker than gain pleasure?",
                    activeRecallAnswer = "Substantially stronger (typically estimated at twice as potent).",
                    isCompleted = false
                ),
                LessonModule(
                    id = "mkt_mod_3",
                    subjectId = "sub_mkt",
                    moduleNumber = 3,
                    title = "Social & Cultural Influences",
                    overview = "Reference groups, opinion leaders, social proof, and cultural subcultures.",
                    sections = listOf(
                        LessonSection(
                            heading = "Social Proof and Reference Groups",
                            content = "Purchasing behavior is heavily mediated by aspiration groups, peer benchmarks, and micro-influencers.",
                            bulletPoints = listOf(
                                "Aspirational Groups: Audiences a consumer desires to belong to",
                                "Dissociative Groups: Audiences a consumer actively strives to avoid being associated with",
                                "Social Proof: Validating decisions through authentic user reviews and peer adoption"
                            )
                        )
                    ),
                    quickInsight = "People buy not just for what things do, but for what they symbolize to their peer group.",
                    activeRecallPrompt = "What do we call reference groups that consumers actively avoid being associated with?",
                    activeRecallAnswer = "Dissociative reference groups.",
                    isCompleted = false
                )
            )
        ),
        Subject(
            id = "sub_hrm",
            code = "06",
            title = "Human Resource Management",
            icon = "👥",
            description = "Talent acquisition, workforce development, performance appraisals, and employee engagement.",
            modules = listOf(
                LessonModule(
                    id = "hrm_mod_1",
                    subjectId = "sub_hrm",
                    moduleNumber = 1,
                    title = "HRM Fundamentals",
                    overview = "Evolution of personnel management to strategic HR, employer branding, and organizational culture.",
                    sections = listOf(
                        LessonSection(
                            heading = "Strategic Human Resource Management",
                            content = "Strategic HRM aligns people practices directly with long-term corporate competitive objectives.",
                            bulletPoints = listOf(
                                "Human Capital as an Asset: Viewing employees as investments rather than overhead expenses",
                                "HR Business Partner Model: HR leaders co-designing unit strategies with operational executives",
                                "Employer Value Proposition (EVP): The complete package of culture, mission, and rewards"
                            )
                        )
                    ),
                    quickInsight = "Employees don't leave companies; they leave unsupportive cultures and bad managers.",
                    activeRecallPrompt = "What does the abbreviation EVP stand for in strategic HR management?",
                    activeRecallAnswer = "Employer Value Proposition.",
                    isCompleted = false
                ),
                LessonModule(
                    id = "hrm_mod_2",
                    subjectId = "sub_hrm",
                    moduleNumber = 2,
                    title = "Recruitment & Selection",
                    overview = "Competency-based interviewing, behavioral assessment, structured scoring, and onboarding.",
                    sections = listOf(
                        LessonSection(
                            heading = "Structured Talent Acquisition",
                            content = "Unstructured interviews predict job performance with under 15% accuracy; structured behavioral interviewing dramatically raises predictive validity.",
                            bulletPoints = listOf(
                                "STAR Method: Situation, Task, Action, Result",
                                "Work Sample Tests: Hands-on realistic job previews and problem tasks",
                                "Structured Scoring Rubrics: Eliminating halo and confirmation biases"
                            )
                        )
                    ),
                    quickInsight = "Hire for attitude and cultural adaptability; train for specialized skills.",
                    activeRecallPrompt = "What four elements make up the STAR behavioral interviewing structure?",
                    activeRecallAnswer = "Situation, Task, Action, and Result.",
                    isCompleted = false
                ),
                LessonModule(
                    id = "hrm_mod_3",
                    subjectId = "sub_hrm",
                    moduleNumber = 3,
                    title = "Performance & Development",
                    overview = "Continuous feedback, 360-degree appraisals, OKRs, and employee skill development.",
                    sections = listOf(
                        LessonSection(
                            heading = "Modern Continuous Feedback",
                            content = "Annual performance reviews are being replaced by agile weekly check-ins, continuous coaching, and transparent OKRs.",
                            bulletPoints = listOf(
                                "Objectives & Key Results (OKRs): Ambitious, measurable quarterly milestones",
                                "360-Degree Feedback: Multi-source input from peers, subordinates, and supervisors",
                                "Skill Upskilling: Proactive learning pathways to maintain organizational agility"
                            )
                        )
                    ),
                    quickInsight = "Feedback is the breakfast of champions; timely feedback corrects trajectory before it's too late.",
                    activeRecallPrompt = "What key shift has replaced traditional annual performance evaluations?",
                    activeRecallAnswer = "Frequent, continuous check-ins and real-time coaching.",
                    isCompleted = false
                )
            )
        ),
        Subject(
            id = "sub_cm",
            code = "07",
            title = "Change Management and Organisational Development",
            icon = "🔄",
            description = "Managing resistance, Lewin's change model, Kotter's 8-step framework, and institutional agility.",
            modules = listOf(
                LessonModule(
                    id = "cm_mod_1",
                    subjectId = "sub_cm",
                    moduleNumber = 1,
                    title = "Understanding Organisational Change",
                    overview = "Forces driving change, employee resistance psychology, and the change curve.",
                    sections = listOf(
                        LessonSection(
                            heading = "The Nature of Resistance",
                            content = "People do not resist change; they resist the uncertainty, perceived loss of control, and incompetence that transition threatens.",
                            bulletPoints = listOf(
                                "Drivers: Disruptive technologies, competitor moves, regulatory shifts, and economic crises",
                                "The Kübler-Ross Change Curve: Shock -> Denial -> Frustration -> Experimentation -> Integration"
                            )
                        )
                    ),
                    quickInsight = "Change is an emotional transition, not just a technical process.",
                    activeRecallPrompt = "Why do employees typically resist organizational transformations?",
                    activeRecallAnswer = "Fear of uncertainty, loss of autonomy, and fear of personal inadequacy.",
                    isCompleted = false
                ),
                LessonModule(
                    id = "cm_mod_2",
                    subjectId = "sub_cm",
                    moduleNumber = 2,
                    title = "Change Models & Implementation",
                    overview = "Lewin's 3-stage model (Unfreeze, Change, Refreeze) and Kotter's 8-Step Leading Change framework.",
                    sections = listOf(
                        LessonSection(
                            heading = "Lewin & Kotter Frameworks",
                            content = "Structured models provide a navigational roadmap for steering organizations through turmoil.",
                            bulletPoints = listOf(
                                "Lewin's Three Phases: 1. Unfreeze (break old habits), 2. Change (transition), 3. Refreeze (anchor new norms)",
                                "Kotter's 8 Steps: Establish urgency, create guiding coalition, formulate vision, communicate vision, empower broad-based action, generate short-term wins, consolidate gains, anchor in culture"
                            )
                        )
                    ),
                    quickInsight = "Generating visible short-term wins builds momentum and silences skeptics.",
                    activeRecallPrompt = "What are the three stages of Kurt Lewin's classical change model?",
                    activeRecallAnswer = "Unfreeze, Change (Transition), and Refreeze.",
                    isCompleted = false
                ),
                LessonModule(
                    id = "cm_mod_3",
                    subjectId = "sub_cm",
                    moduleNumber = 3,
                    title = "Organisational Development",
                    overview = "Action research, team interventions, culture transformation, and organizational learning capacity.",
                    sections = listOf(
                        LessonSection(
                            heading = "Sustaining Agile Culture",
                            content = "Organizational Development (OD) is a systematic, data-driven approach to improving organizational health and problem-solving vitality.",
                            bulletPoints = listOf(
                                "Action Research: Diagnostic cycle of data collection, diagnosis, feedback, planning, action, and evaluation",
                                "Psychological Safety: Fostering an environment where taking interpersonal risks is embraced"
                            )
                        )
                    ),
                    quickInsight = "A learning organization turns everyday operational missteps into collective competitive wisdom.",
                    activeRecallPrompt = "What cyclical approach forms the methodological backbone of Organizational Development?",
                    activeRecallAnswer = "Action research (data collection, diagnosis, action, evaluation).",
                    isCompleted = false
                )
            )
        )
    )

    // Initial Quizzes for all 7 Subjects (3 questions each with 4 options and 1 correct answer)
    private fun getInitialQuizzes(): Map<String, List<QuizQuestion>> = mapOf(
        "sub_sm" to listOf(
            QuizQuestion(
                id = "sm_q1",
                subjectId = "sub_sm",
                question = "Which framework examines political, economic, social, technological, environmental and legal factors?",
                options = listOf("PESTLE", "FIFO", "PERT", "BPR"),
                correctAnswer = 0,
                explanation = "PESTLE analyzes macro-environmental external forces shaping strategic opportunities and threats."
            ),
            QuizQuestion(
                id = "sm_q2",
                subjectId = "sub_sm",
                question = "According to Michael Porter, what strategy focuses on achieving the lowest cost in the industry?",
                options = listOf("Differentiation", "Cost Leadership", "Focus Strategy", "Diversification"),
                correctAnswer = 1,
                explanation = "Cost Leadership aims to be the low-cost producer across a broad industry scope."
            ),
            QuizQuestion(
                id = "sm_q3",
                subjectId = "sub_sm",
                question = "Under the VRIO model, a resource that is valuable, rare, and costly to imitate provides:",
                options = listOf("Competitive parity", "Temporary disadvantage", "Sustained competitive advantage", "Operational deadweight"),
                correctAnswer = 2,
                explanation = "When supported by organizational alignment, VRIO yields sustained competitive advantage."
            )
        ),
        "sub_scm" to listOf(
            QuizQuestion(
                id = "scm_q1",
                subjectId = "sub_scm",
                question = "What phenomenon describes how small shifts in retail demand amplify into massive swings upstream?",
                options = listOf("Hawthorne Effect", "Bullwhip Effect", "Pareto Principle", "Halo Effect"),
                correctAnswer = 1,
                explanation = "The Bullwhip Effect occurs when demand forecast variance cascades through supply chain tiers."
            ),
            QuizQuestion(
                id = "scm_q2",
                subjectId = "sub_scm",
                question = "The Economic Order Quantity (EOQ) formula calculates the order size that minimizes:",
                options = listOf("Only shipping expenses", "Total holding and ordering costs", "Raw material cost per unit", "Employee overtime payroll"),
                correctAnswer = 1,
                explanation = "EOQ balances inventory carrying costs against fixed order placement costs."
            ),
            QuizQuestion(
                id = "scm_q3",
                subjectId = "sub_scm",
                question = "What is the logistics practice of transferring goods directly from inbound to outbound transport without warehousing?",
                options = listOf("Cross-docking", "Deadheading", "Drop-shipping", "Reverse buffering"),
                correctAnswer = 0,
                explanation = "Cross-docking eliminates staging and holding by transferring immediately across the dock."
            )
        ),
        "sub_dt" to listOf(
            QuizQuestion(
                id = "dt_q1",
                subjectId = "sub_dt",
                question = "Which phase of Design Thinking focuses on understanding user needs through immersion and observation?",
                options = listOf("Prototype", "Empathise", "Test", "Ideate"),
                correctAnswer = 1,
                explanation = "Empathise involves user observation, interviews, and journey mapping to uncover core pain points."
            ),
            QuizQuestion(
                id = "dt_q2",
                subjectId = "sub_dt",
                question = "In brainstorm ideation sessions, what rule helps maximize creative exploration?",
                options = listOf("Immediately critique impractical ideas", "Defer judgment and build on others' ideas", "Limit brainstorming to 3 suggestions", "Vote only on executive proposals"),
                correctAnswer = 1,
                explanation = "Deferring judgment and using 'Yes, and...' allows unconventional ideas to flourish."
            ),
            QuizQuestion(
                id = "dt_q3",
                subjectId = "sub_dt",
                question = "What is the primary purpose of a rapid, low-fidelity paper prototype?",
                options = listOf("Final production release", "Patent filing specification", "Quick, inexpensive learning and user testing", "Replacing user documentation"),
                correctAnswer = 2,
                explanation = "Low-fidelity prototypes validate hypotheses quickly without heavy sunk cost."
            )
        ),
        "sub_ai" to listOf(
            QuizQuestion(
                id = "ai_q1",
                subjectId = "sub_ai",
                question = "Which machine learning approach relies on training algorithms using labeled historical data?",
                options = listOf("Supervised Learning", "Unsupervised Clustering", "Reinforcement Latency", "Random Heuristics"),
                correctAnswer = 0,
                explanation = "Supervised learning maps known input features to explicit target labels."
            ),
            QuizQuestion(
                id = "ai_q2",
                subjectId = "sub_ai",
                question = "Which AI application helps telecommunications and SaaS companies retain subscribers before they cancel?",
                options = listOf("Inventory depreciation", "Customer Churn Prediction", "Payroll tax auditing", "Server thermal cooling"),
                correctAnswer = 1,
                explanation = "Churn prediction models detect early disengagement signals to trigger proactive retention."
            ),
            QuizQuestion(
                id = "ai_q3",
                subjectId = "sub_ai",
                question = "Why is Explainable AI (XAI) vital for high-stakes business deployment?",
                options = listOf("It makes models run twice as fast", "It enables humans to audit and understand decision rationale", "It eliminates the need for computing GPUs", "It automatically generates social media ads"),
                correctAnswer = 1,
                explanation = "XAI ensures decisions are transparent, trustworthy, and compliant with audit standards."
            )
        ),
        "sub_mkt" to listOf(
            QuizQuestion(
                id = "mkt_q1",
                subjectId = "sub_mkt",
                question = "What is the first formal stage in the classical 5-stage consumer decision process?",
                options = listOf("Alternative Evaluation", "Problem or Need Recognition", "Purchase Decision", "Post-purchase Review"),
                correctAnswer = 1,
                explanation = "The customer decision begins when a difference between perceived actual and desired state is recognized."
            ),
            QuizQuestion(
                id = "mkt_q2",
                subjectId = "sub_mkt",
                question = "Which psychological heuristic asserts that losses feel roughly twice as painful as equivalent gains?",
                options = listOf("Loss Aversion", "Anchoring Bias", "Confirmation Bias", "Bandwagon Bias"),
                correctAnswer = 0,
                explanation = "Kahneman and Tversky's loss aversion demonstrated that pain of loss outstrips joy of gain."
            ),
            QuizQuestion(
                id = "mkt_q3",
                subjectId = "sub_mkt",
                question = "Consumer groups that individuals actively avoid associating with or being identified as are called:",
                options = listOf("Aspirational groups", "Dissociative groups", "Primary groups", "Informal tribes"),
                correctAnswer = 1,
                explanation = "Dissociative groups influence purchase behavior by motivating consumers to shun their perceived tastes."
            )
        ),
        "sub_hrm" to listOf(
            QuizQuestion(
                id = "hrm_q1",
                subjectId = "sub_hrm",
                question = "What does the STAR technique stand for in structured behavioral interviewing?",
                options = listOf("Skills, Talent, Attitude, Resilience", "Situation, Task, Action, Result", "Strategy, Timing, Assessment, Reward", "Selection, Testing, Approval, Retention"),
                correctAnswer = 1,
                explanation = "STAR prompts candidates for concrete past Situations, Tasks, Actions, and Results."
            ),
            QuizQuestion(
                id = "hrm_q2",
                subjectId = "sub_hrm",
                question = "What performance framework establishes ambitious, measurable objectives paired with numerical key results?",
                options = listOf("OKRs", "SWOT", "PEST", "BCG Matrix"),
                correctAnswer = 0,
                explanation = "Objectives and Key Results (OKRs) drive quarterly team focus and measurable execution."
            ),
            QuizQuestion(
                id = "hrm_q3",
                subjectId = "sub_hrm",
                question = "The collection of workplace culture, career mobility, and total rewards offered to attract talent is called:",
                options = listOf("B2B Proposition", "Employer Value Proposition (EVP)", "Labor Overhead Rate", "Fringe Quota"),
                correctAnswer = 1,
                explanation = "The EVP articulates the unique workplace benefits and experience that attracts and retains talent."
            )
        ),
        "sub_cm" to listOf(
            QuizQuestion(
                id = "cm_q1",
                subjectId = "sub_cm",
                question = "In Kurt Lewin's change management model, what is the initial phase designed to overcome inertia?",
                options = listOf("Refreeze", "Unfreeze", "Execute", "Transition"),
                correctAnswer = 1,
                explanation = "Unfreezing involves preparing the organization to accept that change is necessary and dismantling existing mindsets."
            ),
            QuizQuestion(
                id = "cm_q2",
                subjectId = "sub_cm",
                question = "Which step in Kotter's 8-Stage Change Model is crucial for sustaining early employee momentum?",
                options = listOf("Delaying communication until full deployment", "Generating and celebrating short-term wins", "Ignoring resistance from middle managers", "Rotating leaders every six weeks"),
                correctAnswer = 1,
                explanation = "Celebrating tangible short-term wins provides proof of concept and validates team effort."
            ),
            QuizQuestion(
                id = "cm_q3",
                subjectId = "sub_cm",
                question = "What psychological factor is essential for team members to openly discuss failures and suggest bold changes without fear?",
                options = listOf("Hierarchical compliance", "Psychological safety", "Strict surveillance", "Zero-tolerance penalty"),
                correctAnswer = 1,
                explanation = "Psychological safety allows teams to experiment, admit mistakes, and drive continuous improvement."
            )
        )
    )

    // Initial State values conforming to prompt:
    // Subjects: 7, Lessons Completed: 8 / 21, Quiz Attempts: 5, Engagement Points: 95
    private val _user = MutableStateFlow(
        User(
            username = "Ans",
            name = "Ans",
            role = "Student",
            points = 95
        )
    )
    val user: StateFlow<User> = _user.asStateFlow()

    private val _subjects = MutableStateFlow<List<Subject>>(getInitialSubjects())
    val subjects: StateFlow<List<Subject>> = _subjects.asStateFlow()

    private val _quizzes = MutableStateFlow<Map<String, List<QuizQuestion>>>(getInitialQuizzes())
    val quizzes: StateFlow<Map<String, List<QuizQuestion>>> = _quizzes.asStateFlow()

    // 5 Quiz attempts already recorded for demo data
    private val _quizAttempts = MutableStateFlow<List<QuizAttempt>>(
        listOf(
            QuizAttempt(
                id = "att_1",
                subjectId = "sub_sm",
                subjectTitle = "Strategic Management",
                score = 3,
                total = 3,
                percentage = 100,
                date = "Yesterday"
            ),
            QuizAttempt(
                id = "att_2",
                subjectId = "sub_scm",
                subjectTitle = "Logistics and SCM",
                score = 2,
                total = 3,
                percentage = 67,
                date = "2 days ago"
            ),
            QuizAttempt(
                id = "att_3",
                subjectId = "sub_dt",
                subjectTitle = "Design Thinking",
                score = 3,
                total = 3,
                percentage = 100,
                date = "3 days ago"
            ),
            QuizAttempt(
                id = "att_4",
                subjectId = "sub_ai",
                subjectTitle = "AI for Business",
                score = 2,
                total = 3,
                percentage = 67,
                date = "4 days ago"
            ),
            QuizAttempt(
                id = "att_5",
                subjectId = "sub_mkt",
                subjectTitle = "Marketing – Consumer Behaviour",
                score = 2,
                total = 3,
                percentage = 67,
                date = "5 days ago"
            )
        )
    )
    val quizAttempts: StateFlow<List<QuizAttempt>> = _quizAttempts.asStateFlow()

    // Badges: 6 defined badges
    private val _badges = MutableStateFlow<List<BadgeItem>>(
        listOf(
            BadgeItem(
                id = "b_first_step",
                title = "First Step",
                description = "Complete first lesson.",
                icon = "🌱",
                isUnlocked = true,
                requirement = "Complete 1 lesson"
            ),
            BadgeItem(
                id = "b_quiz_explorer",
                title = "Quiz Explorer",
                description = "Complete first quiz.",
                icon = "🧠",
                isUnlocked = true,
                requirement = "Attempt 1 quiz"
            ),
            BadgeItem(
                id = "b_high_scorer",
                title = "High Scorer",
                description = "Score 80% or above on a quiz.",
                icon = "🏆",
                isUnlocked = true,
                requirement = "Score >= 80% on any quiz"
            ),
            BadgeItem(
                id = "b_active_learner",
                title = "Active Learner",
                description = "Complete five modules.",
                icon = "🔥",
                isUnlocked = true,
                requirement = "Complete 5 modules"
            ),
            BadgeItem(
                id = "b_course_finisher",
                title = "Course Finisher",
                description = "Complete all modules.",
                icon = "🎓",
                isUnlocked = false,
                requirement = "Complete all 21 modules"
            ),
            BadgeItem(
                id = "b_engage_champion",
                title = "Engage Champion",
                description = "Reach 100 engagement points.",
                icon = "⭐",
                isUnlocked = false, // 95 points currently, unlocks at 100!
                requirement = "Earn 100+ engagement points"
            )
        )
    )
    val badges: StateFlow<List<BadgeItem>> = _badges.asStateFlow()

    // Community Posts
    private val _communityPosts = MutableStateFlow<List<CommunityPost>>(
        listOf(
            CommunityPost(
                id = "post_1",
                authorName = "Ans",
                authorRole = "Student",
                content = "Today, I understood the difference between strategy formulation and implementation.",
                timeAgo = "2 min ago",
                likes = 6,
                hasLiked = true
            ),
            CommunityPost(
                id = "post_2",
                authorName = "Priya Sharma",
                authorRole = "Student",
                content = "The Bullwhip Effect simulation in SCM was eye-opening! POS data sharing cuts down stockout costs drastically.",
                timeAgo = "1 hour ago",
                likes = 12,
                hasLiked = false
            ),
            CommunityPost(
                id = "post_3",
                authorName = "Prof. Roberts",
                authorRole = "Faculty Advisor",
                content = "Reminder for Design Thinking: Keep your low-fidelity prototypes rough. Don't fall in love with your first iteration!",
                timeAgo = "3 hours ago",
                likes = 24,
                hasLiked = false
            )
        )
    )
    val communityPosts: StateFlow<List<CommunityPost>> = _communityPosts.asStateFlow()

    // Notifications
    private val _notifications = MutableStateFlow<List<NotificationItem>>(
        listOf(
            NotificationItem(
                id = "notif_1",
                title = "Quiz completed",
                message = "Strategic Management — 80%",
                timeAgo = "10m ago",
                iconType = "quiz"
            ),
            NotificationItem(
                id = "notif_2",
                title = "New achievement",
                message = "Quiz Explorer unlocked",
                timeAgo = "2h ago",
                iconType = "badge"
            ),
            NotificationItem(
                id = "notif_3",
                title = "Continue learning",
                message = "Your next module is ready",
                timeAgo = "5h ago",
                iconType = "lesson"
            )
        )
    )
    val notifications: StateFlow<List<NotificationItem>> = _notifications.asStateFlow()

    // Analytics Activity Log (Tracking for Design Thinking project research)
    private val _analyticsLogs = MutableStateFlow<List<AnalyticsLog>>(
        listOf(
            AnalyticsLog("al_1", "Login", "User Ans authenticated successfully", "Today · 08:30"),
            AnalyticsLog("al_2", "Lesson completed", "Completed Strategic Management Basics", "Today · 08:42", 10),
            AnalyticsLog("al_3", "Quiz completed", "Scored 100% in Strategic Management Quiz", "Today · 08:50", 15),
            AnalyticsLog("al_4", "Discussion post", "Shared learning insight in Community", "Today · 09:05", 5)
        )
    )
    val analyticsLogs: StateFlow<List<AnalyticsLog>> = _analyticsLogs.asStateFlow()

    // Teacher Assignments (supporting teacher and HOD monitoring view)
    private val _teacherAssignments = MutableStateFlow<List<TeacherAssignment>>(
        listOf(
            TeacherAssignment(
                id = "asg_1",
                subjectId = "sub_scm",
                subjectTitle = "Logistics and SCM",
                title = "EOQ Case Exercise Review",
                description = "Targeted practice module on carrying cost vs ordering costs.",
                targetStudent = "Ans",
                isHodApproved = true,
                isCompleted = false,
                dueDate = "Tomorrow"
            ),
            TeacherAssignment(
                id = "asg_2",
                subjectId = "sub_ai",
                subjectTitle = "AI for Business",
                title = "Responsible AI Ethical Audit Checklist",
                description = "Review algorithmic bias mitigation strategies in financial scoring.",
                targetStudent = "Ans",
                isHodApproved = true,
                isCompleted = true,
                dueDate = "Completed"
            )
        )
    )
    val teacherAssignments: StateFlow<List<TeacherAssignment>> = _teacherAssignments.asStateFlow()

    // Research / Testing Session state (Section 28)
    private val _researchSession = MutableStateFlow(UsabilityTestingSession())
    val researchSession: StateFlow<UsabilityTestingSession> = _researchSession.asStateFlow()

    // Theme state
    private val _isDarkTheme = MutableStateFlow(false)
    val isDarkTheme: StateFlow<Boolean> = _isDarkTheme.asStateFlow()

    // Feedback message (Toast / Banner)
    private val _bannerMessage = MutableStateFlow<String?>(null)
    val bannerMessage: StateFlow<String?> = _bannerMessage.asStateFlow()

    fun clearBannerMessage() {
        _bannerMessage.value = null
    }

    fun setDarkTheme(isDark: Boolean) {
        _isDarkTheme.value = isDark
    }

    fun toggleTheme() {
        _isDarkTheme.update { !it }
    }

    // Role switcher (for demoing Student, Teacher, and HOD)
    fun switchUserRole(newRole: String) {
        _user.update { it.copy(role = newRole, name = if (newRole == "Student") "Ans" else if (newRole == "Teacher") "Prof. Anderson" else "Dr. V. Sharma (HOD)") }
        recordAnalytics("Role switch", "Switched active view to $newRole mode")
    }

    // Mark Module Complete
    fun completeLessonModule(moduleId: String) {
        var completedModuleTitle = ""
        var newlyCompleted = false

        _subjects.update { currentSubjects ->
            currentSubjects.map { subject ->
                val updatedModules = subject.modules.map { mod ->
                    if (mod.id == moduleId) {
                        if (!mod.isCompleted) {
                            newlyCompleted = true
                            completedModuleTitle = mod.title
                            mod.copy(isCompleted = true)
                        } else {
                            mod
                        }
                    } else {
                        mod
                    }
                }
                subject.copy(modules = updatedModules)
            }
        }

        if (newlyCompleted) {
            // 1. Add 10 points
            _user.update { it.copy(points = it.points + 10) }

            // 2. Add notification
            addNotification(
                title = "Lesson completed",
                message = "+10 points · $completedModuleTitle",
                iconType = "lesson"
            )

            // 3. Record analytics
            recordAnalytics(
                eventType = "Lesson completed",
                description = "Completed micro-lesson: $completedModuleTitle",
                points = 10
            )

            // 4. Banner toast
            _bannerMessage.value = "+10 points · Lesson completed"

            // 5. Evaluate badges
            checkBadges()
        }
    }

    // Submit Quiz Attempt
    fun submitQuizAttempt(
        subjectId: String,
        subjectTitle: String,
        score: Int,
        totalQuestions: Int = 3
    ): Int {
        val percentage = (score * 100) / totalQuestions
        val pointsEarned = score * 5
        val dateStr = dateFormat.format(Date())

        val attempt = QuizAttempt(
            id = "att_${System.currentTimeMillis()}",
            subjectId = subjectId,
            subjectTitle = subjectTitle,
            score = score,
            total = totalQuestions,
            percentage = percentage,
            date = "Just now"
        )

        // Save attempt
        _quizAttempts.update { listOf(attempt) + it }

        // Award points
        if (pointsEarned > 0) {
            _user.update { it.copy(points = it.points + pointsEarned) }
        }

        // Notification
        addNotification(
            title = "Quiz completed",
            message = "$subjectTitle — $percentage% (+$pointsEarned pts)",
            iconType = "quiz"
        )

        // Analytics
        recordAnalytics(
            eventType = "Quiz completed",
            description = "Completed $subjectTitle quiz ($score/$totalQuestions, $percentage%)",
            points = pointsEarned
        )

        _bannerMessage.value = "+$pointsEarned points · Quiz completed ($percentage%)"

        // Check badges
        checkBadges(latestScore = percentage)

        return pointsEarned
    }

    // Post to Community
    fun postToCommunity(content: String): Boolean {
        val trimmed = content.trim()
        if (trimmed.isEmpty()) return false

        val newPost = CommunityPost(
            id = "post_${System.currentTimeMillis()}",
            authorName = _user.value.name,
            authorRole = _user.value.role,
            content = trimmed,
            timeAgo = "Just now",
            likes = 1,
            hasLiked = true
        )

        _communityPosts.update { listOf(newPost) + it }

        // +5 points
        _user.update { it.copy(points = it.points + 5) }

        // Notification
        addNotification(
            title = "Community post",
            message = "+5 points · Shared insight with peers",
            iconType = "community"
        )

        recordAnalytics(
            eventType = "Discussion post",
            description = "Posted study tip to peer community",
            points = 5
        )

        _bannerMessage.value = "+5 points · Shared to Community"

        checkBadges()
        return true
    }

    fun toggleLikePost(postId: String) {
        _communityPosts.update { posts ->
            posts.map { post ->
                if (post.id == postId) {
                    val newLiked = !post.hasLiked
                    post.copy(
                        hasLiked = newLiked,
                        likes = if (newLiked) post.likes + 1 else (post.likes - 1).coerceAtLeast(0)
                    )
                } else post
            }
        }
    }

    // Add notification
    fun addNotification(title: String, message: String, iconType: String = "info") {
        val item = NotificationItem(
            id = "notif_${System.currentTimeMillis()}",
            title = title,
            message = message,
            timeAgo = "Just now",
            iconType = iconType
        )
        _notifications.update { listOf(item) + it }
    }

    fun clearNotifications() {
        _notifications.value = emptyList()
    }

    // Teacher & HOD actions
    val currentUser: StateFlow<User> = _user.asStateFlow()

    fun getQuizQuestionsForSubject(subjectId: String): List<QuizQuestion> =
        _quizzes.value[subjectId] ?: emptyList()

    fun markModuleCompleted(subjectId: String, moduleId: String) {
        completeLessonModule(moduleId)
    }

    fun addCommunityPost(authorName: String, subjectTag: String, content: String) {
        val trimmed = content.trim()
        if (trimmed.isEmpty()) return

        val newPost = CommunityPost(
            id = "post_${System.currentTimeMillis()}",
            authorName = if (authorName.isNotBlank()) authorName else _user.value.name,
            authorRole = _user.value.role,
            content = trimmed,
            timeAgo = "Just now",
            likes = 1,
            hasLiked = true,
            subjectTag = subjectTag
        )
        _communityPosts.update { listOf(newPost) + it }
        _user.update { it.copy(points = it.points + 5) }
        addNotification(
            title = "Community post",
            message = "+5 points · Shared insight in $subjectTag",
            iconType = "community"
        )
        recordAnalytics("Discussion post", "Posted to $subjectTag", points = 5)
        _bannerMessage.value = "+5 points · Shared to Community"
        checkBadges()
    }

    fun togglePostLike(postId: String) {
        toggleLikePost(postId)
    }

    fun createTeacherAssignment(
        title: String,
        description: String,
        subjectName: String,
        studentName: String
    ) {
        addTeacherAssignment(
            subjectId = "sub_sm",
            subjectTitle = subjectName,
            title = title,
            description = description,
            student = studentName
        )
    }

    fun approveAssignment(assignmentId: String) {
        approveAssignmentAsHod(assignmentId)
    }

    fun recordLog(action: String, details: String) {
        recordAnalytics(action, details)
    }

    fun addNewSubject(
        title: String,
        code: String,
        description: String,
        icon: String = "📘",
        firstModuleTitle: String = "Introduction and Core Concepts",
        firstModuleOverview: String = "Foundational theories and modern industry practices.",
        firstModuleInsight: String = "Consistent application of fundamental principles is key to mastery.",
        firstModuleRecallPrompt: String = "What is the primary objective of this subject?",
        firstModuleRecallAnswer: String = "To build analytical and practical competencies in $title.",
        firstQuizQuestion: String = "What is a core principle of $title?",
        firstQuizOptions: List<String> = listOf("Systematic analysis and evaluation", "Random guessing", "Ignoring feedback", "Avoiding data"),
        firstQuizAnswer: Int = 0,
        firstQuizExplanation: String = "Systematic analysis provides the basis for rigorous decision making."
    ): Subject {
        val newSubId = "sub_${System.currentTimeMillis()}"
        val newModId = "mod_${System.currentTimeMillis()}_1"

        val module = LessonModule(
            id = newModId,
            subjectId = newSubId,
            moduleNumber = 1,
            title = if (firstModuleTitle.isNotBlank()) firstModuleTitle else "Module 01: Core Concepts",
            overview = if (firstModuleOverview.isNotBlank()) firstModuleOverview else "Introduction to $title",
            sections = listOf(
                LessonSection(
                    heading = "1. Fundamental Frameworks",
                    content = "This module introduces the key pillars of $title. Mastering these concepts provides the analytical basis needed to navigate complex challenges.",
                    bulletPoints = listOf(
                        "Foundational terminology and definitions",
                        "Strategic impact on organizational outcomes",
                        "Practical application in current industry contexts"
                    ),
                    example = "Case in Point: Modern enterprises adopt structured methodologies to stay competitive in shifting market conditions."
                ),
                LessonSection(
                    heading = "2. Implementation Best Practices",
                    content = "Effective execution requires continuous monitoring, clear performance benchmarks, and proactive alignment across stakeholders.",
                    bulletPoints = listOf(
                        "Identifying key performance indicators",
                        "Minimizing operational bottlenecks",
                        "Leveraging real-time feedback loops"
                    )
                )
            ),
            quickInsight = if (firstModuleInsight.isNotBlank()) firstModuleInsight else "Clear strategy coupled with disciplined execution produces sustained performance.",
            activeRecallPrompt = if (firstModuleRecallPrompt.isNotBlank()) firstModuleRecallPrompt else "What is the key takeaway of this module?",
            activeRecallAnswer = if (firstModuleRecallAnswer.isNotBlank()) firstModuleRecallAnswer else "Analytical rigor and structured execution.",
            isCompleted = false
        )

        val newSubject = Subject(
            id = newSubId,
            code = if (code.isNotBlank()) code else (_subjects.value.size + 1).toString().padStart(2, '0'),
            title = title,
            icon = if (icon.isNotBlank()) icon else "📘",
            description = if (description.isNotBlank()) description else "Comprehensive curriculum module for $title.",
            modules = listOf(module)
        )

        val quizList = listOf(
            QuizQuestion(
                id = "q_${System.currentTimeMillis()}_1",
                subjectId = newSubId,
                question = if (firstQuizQuestion.isNotBlank()) firstQuizQuestion else "What is the central focus of $title?",
                options = firstQuizOptions,
                correctAnswer = firstQuizAnswer,
                explanation = firstQuizExplanation
            ),
            QuizQuestion(
                id = "q_${System.currentTimeMillis()}_2",
                subjectId = newSubId,
                question = "How do high-performing teams evaluate progress in $title?",
                options = listOf("Continuous KPI tracking and review", "Annual anecdotal check", "No formal review", "Guesswork"),
                correctAnswer = 0,
                explanation = "Regular tracking allows rapid course-correction and sustained improvement."
            ),
            QuizQuestion(
                id = "q_${System.currentTimeMillis()}_3",
                subjectId = newSubId,
                question = "Why is active student engagement critical for this subject?",
                options = listOf("Deeper retention and practical problem-solving", "Only to pass time", "It reduces course materials", "It avoids homework"),
                correctAnswer = 0,
                explanation = "Micro-learning combined with active recall yields higher retention and engagement."
            )
        )

        _subjects.update { it + newSubject }
        _quizzes.update { map ->
            map + (newSubId to quizList)
        }

        addNotification(
            title = "New Subject Available",
            message = "Faculty published: $title (${newSubject.code})",
            iconType = "lesson"
        )

        recordAnalytics("Subject Added", "Teacher added new subject: $title to student curriculum")
        _bannerMessage.value = "New subject '$title' added and published to students!"

        return newSubject
    }

    fun getStudentRoster(): List<StudentPerformance> {
        val currentSubjects = _subjects.value
        val ansSubjectProgress = currentSubjects.map { s ->
            StudentSubjectProgress(
                subjectId = s.id,
                subjectTitle = s.title,
                subjectIcon = s.icon,
                completedModules = s.completedCount,
                totalModules = s.totalModules,
                percentage = s.progressPercentage
            )
        }

        return listOf(
            StudentPerformance(
                studentId = "STD-101",
                name = "Ans",
                email = "ans@engagelearn.edu",
                points = _user.value.points,
                quizAverage = if (_quizAttempts.value.isEmpty()) 80 else (_quizAttempts.value.sumOf { it.percentage } / _quizAttempts.value.size),
                totalQuizzesTaken = _quizAttempts.value.size,
                subjectProgress = ansSubjectProgress,
                weakSubject = "Change Management (0% completed)",
                engagementStatus = if (_user.value.points >= 90) "Excellent" else "Active"
            ),
            StudentPerformance(
                studentId = "STD-102",
                name = "Priya Sharma",
                email = "priya.s@engagelearn.edu",
                points = 115,
                quizAverage = 88,
                totalQuizzesTaken = 6,
                subjectProgress = currentSubjects.mapIndexed { idx, s ->
                    val comp = when (idx % 3) {
                        0 -> s.totalModules
                        1 -> (s.totalModules - 1).coerceAtLeast(1)
                        else -> 1
                    }
                    StudentSubjectProgress(
                        subjectId = s.id,
                        subjectTitle = s.title,
                        subjectIcon = s.icon,
                        completedModules = comp,
                        totalModules = s.totalModules,
                        percentage = if (s.totalModules == 0) 0 else (comp * 100) / s.totalModules
                    )
                },
                weakSubject = "Marketing – Consumer Behaviour",
                engagementStatus = "Excellent"
            ),
            StudentPerformance(
                studentId = "STD-103",
                name = "Rahul Mehta",
                email = "rahul.m@engagelearn.edu",
                points = 70,
                quizAverage = 65,
                totalQuizzesTaken = 4,
                subjectProgress = currentSubjects.mapIndexed { idx, s ->
                    val comp = if (idx == 0 || idx == 2) 2 else 0
                    StudentSubjectProgress(
                        subjectId = s.id,
                        subjectTitle = s.title,
                        subjectIcon = s.icon,
                        completedModules = comp,
                        totalModules = s.totalModules,
                        percentage = if (s.totalModules == 0) 0 else (comp * 100) / s.totalModules
                    )
                },
                weakSubject = "Human Resource Management (0%)",
                engagementStatus = "Needs Attention"
            ),
            StudentPerformance(
                studentId = "STD-104",
                name = "Sneha Patel",
                email = "sneha.p@engagelearn.edu",
                points = 130,
                quizAverage = 92,
                totalQuizzesTaken = 7,
                subjectProgress = currentSubjects.mapIndexed { idx, s ->
                    val comp = if (idx < 5) s.totalModules else 1
                    StudentSubjectProgress(
                        subjectId = s.id,
                        subjectTitle = s.title,
                        subjectIcon = s.icon,
                        completedModules = comp,
                        totalModules = s.totalModules,
                        percentage = if (s.totalModules == 0) 0 else (comp * 100) / s.totalModules
                    )
                },
                weakSubject = "None (High Performer)",
                engagementStatus = "Excellent"
            ),
            StudentPerformance(
                studentId = "STD-105",
                name = "Vikram Rao",
                email = "vikram.r@engagelearn.edu",
                points = 45,
                quizAverage = 58,
                totalQuizzesTaken = 2,
                subjectProgress = currentSubjects.mapIndexed { idx, s ->
                    val comp = if (idx == 0) 1 else 0
                    StudentSubjectProgress(
                        subjectId = s.id,
                        subjectTitle = s.title,
                        subjectIcon = s.icon,
                        completedModules = comp,
                        totalModules = s.totalModules,
                        percentage = if (s.totalModules == 0) 0 else (comp * 100) / s.totalModules
                    )
                },
                weakSubject = "Logistics and SCM / AI for Business",
                engagementStatus = "Needs Attention"
            )
        )
    }

    fun addTeacherAssignment(subjectId: String, subjectTitle: String, title: String, description: String, student: String) {
        val newAsg = TeacherAssignment(
            id = "asg_${System.currentTimeMillis()}",
            subjectId = subjectId,
            subjectTitle = subjectTitle,
            title = title,
            description = description,
            targetStudent = student,
            isHodApproved = false, // HOD needs to approve
            isCompleted = false,
            dueDate = "Next class"
        )
        _teacherAssignments.update { listOf(newAsg) + it }
        recordAnalytics("Teacher Assignment", "Assigned $title to $student")
        _bannerMessage.value = "Activity assigned. Pending HOD review."
    }

    fun approveAssignmentAsHod(assignmentId: String) {
        _teacherAssignments.update { list ->
            list.map { if (it.id == assignmentId) it.copy(isHodApproved = true) else it }
        }
        _bannerMessage.value = "HOD approved assignment."
        addNotification("HOD Approved", "Task approved for student intervention", "info")
    }

    // Usability Testing session update
    fun updateResearchSession(session: UsabilityTestingSession) {
        _researchSession.value = session
        recordAnalytics("Research Test", "Updated participant ${session.participantId} usability metrics")
    }

    private fun checkBadges(latestScore: Int? = null) {
        val completedTotal = _subjects.value.sumOf { it.completedCount }
        val attemptsTotal = _quizAttempts.value.size
        val pointsTotal = _user.value.points

        _badges.update { currentBadges ->
            currentBadges.map { badge ->
                when (badge.id) {
                    "b_first_step" -> if (!badge.isUnlocked && completedTotal >= 1) {
                        badge.copy(isUnlocked = true)
                    } else badge

                    "b_quiz_explorer" -> if (!badge.isUnlocked && attemptsTotal >= 1) {
                        badge.copy(isUnlocked = true)
                    } else badge

                    "b_high_scorer" -> if (!badge.isUnlocked && ((latestScore != null && latestScore >= 80) || _quizAttempts.value.any { it.percentage >= 80 })) {
                        badge.copy(isUnlocked = true)
                    } else badge

                    "b_active_learner" -> if (!badge.isUnlocked && completedTotal >= 5) {
                        badge.copy(isUnlocked = true)
                    } else badge

                    "b_course_finisher" -> if (!badge.isUnlocked && completedTotal >= 21) {
                        badge.copy(isUnlocked = true)
                    } else badge

                    "b_engage_champion" -> if (!badge.isUnlocked && pointsTotal >= 100) {
                        addNotification("🎯 Achievement Unlocked", "Engage Champion (100+ points)!", "badge")
                        badge.copy(isUnlocked = true)
                    } else badge

                    else -> badge
                }
            }
        }
    }

    private fun recordAnalytics(eventType: String, description: String, points: Int = 0) {
        val log = AnalyticsLog(
            id = "al_${System.currentTimeMillis()}",
            eventType = eventType,
            description = description,
            timestamp = "Today · " + SimpleDateFormat("HH:mm", Locale.getDefault()).format(Date()),
            pointsAwarded = points
        )
        _analyticsLogs.update { listOf(log) + it }
    }
}
