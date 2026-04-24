package com.example.whatsappchatbot.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;


@Service
public class ChatbotService {

	private static final Logger logger = LoggerFactory.getLogger(ChatbotService.class);
	private static final String MEDICAL_DISCLAIMER = 
		"⚠️ MEDICAL DISCLAIMER: This is an AI assistant providing general health information. " +
		"For personalized medical advice, please consult a qualified oncologist.";

	public String getReply(String sender, String message) {
		LocalDateTime timestamp = LocalDateTime.now();
		logger.info("[{}] Message from [{}]: {}", timestamp, sender, message);

		if (message == null || message.isBlank()) {
			logger.warn("[{}] Invalid/empty message received from [{}]", timestamp, sender);
			return "Please enter a valid question. Type 'help' for available options.";
		}

		String trimmedMessage = message.trim().toLowerCase();
		String response = generateResponse(trimmedMessage);
		logger.info("[{}] Response to [{}]: {}", timestamp, sender, response);
		
		return response;
	}


	private String generateResponse(String query) {
		// Check for specific cancer types and symptoms
		if (containsKeyword(query, new String[]{"breast cancer", "breast", "mammogram"})) {
			return handleBreastCancer();
		}
		if (containsKeyword(query, new String[]{"lung cancer", "lungs", "respiratory"})) {
			return handleLungCancer();
		}
		if (containsKeyword(query, new String[]{"cervical", "cervix", "pap smear"})) {
			return handleCervicalCancer();
		}
		if (containsKeyword(query, new String[]{"colon", "colorectal", "bowel"})) {
			return handleColorectalCancer();
		}
		if (containsKeyword(query, new String[]{"symptoms", "sign", "symptom"})) {
			return handleSymptoms();
		}
		if (containsKeyword(query, new String[]{"prevention", "prevent", "lifestyle"})) {
			return handlePrevention();
		}
		if (containsKeyword(query, new String[]{"treatment", "therapy", "chemotherapy", "radiation"})) {
			return handleTreatment();
		}
		if (containsKeyword(query, new String[]{"emotional", "support", "counseling", "mental health"})) {
			return handleEmotionalSupport();
		}
		if (containsKeyword(query, new String[]{"screening", "test", "checkup", "screening test"})) {
			return handleScreening();
		}
		if (containsKeyword(query, new String[]{"cost", "fee", "financial", "expense"})) {
			return handleFinancialSupport();
		}
		if (containsKeyword(query, new String[]{"stage", "grade", "prognosis"})) {
			return handleStaging();
		}
		if (containsKeyword(query, new String[]{"diet", "nutrition", "food", "vitamin"})) {
			return handleNutrition();
		}
		if (containsKeyword(query, new String[]{"hello", "hi", "greetings", "hey"})) {
			return handleGreeting();
		}
		if (containsKeyword(query, new String[]{"help", "menu", "option", "what can"})) {
			return handleHelp();
		}
		if (containsKeyword(query, new String[]{"bye", "goodbye", "thank", "thanks", "exit"})) {
			return handleFarewell();
		}

		// Default helpful response
		return "I'm here to help with cancer health information. " +
			"Please ask about: symptoms, prevention, screening, treatments, emotional support, or financial assistance. " +
			"Type 'help' for more options. " + MEDICAL_DISCLAIMER;
	}

	private String handleGreeting() {
		return "👋 Welcome to Jarurat Care Foundation Cancer Health Consultant! " +
			"I'm here to provide general health information and support. " +
			"How can I help you today? Type 'help' to see all available topics.";
	}

	private String handleBreastCancer() {
		return "🎀 BREAST CANCER INFORMATION\n\n" +
			"Early Detection:\n" +
			"• Self-examination: Monthly breast self-check (BSE)\n" +
			"• Mammography: Women 40+ should have annual mammograms\n" +
			"• Clinical exam: Regular check-ups with doctors\n\n" +
			"Common Warning Signs:\n" +
			"• Lumps or thickening in breast tissue\n" +
			"• Nipple discharge or retraction\n" +
			"• Skin changes or dimpling\n" +
			"• Persistent breast pain\n\n" +
			"Risk Factors:\n" +
			"• Age, family history, hormonal factors\n" +
			"• Obesity, alcohol consumption, late menopause\n\n" +
			"For personalized advice, consult an oncologist. " + MEDICAL_DISCLAIMER;
	}

	private String handleLungCancer() {
		return "🫁 LUNG CANCER INFORMATION\n\n" +
			"Risk Factors:\n" +
			"• Smoking (primary risk factor)\n" +
			"• Secondhand smoke exposure\n" +
			"• Radon gas exposure\n" +
			"• Occupational hazards\n" +
			"• Family history\n\n" +
			"Warning Signs:\n" +
			"• Persistent cough (>3 weeks)\n" +
			"• Coughing up blood\n" +
			"• Shortness of breath\n" +
			"• Chest pain\n" +
			"• Hoarseness\n\n" +
			"Prevention:\n" +
			"• No smoking/avoid secondhand smoke\n" +
			"• Test home for radon\n" +
			"• Screening for high-risk individuals\n\n" +
			"Contact our specialists for free screening consultation. " + MEDICAL_DISCLAIMER;
	}

	private String handleCervicalCancer() {
		return "💗 CERVICAL CANCER INFORMATION\n\n" +
			"Prevention Through Screening:\n" +
			"• HPV Vaccination: Ages 9-45 (preventive)\n" +
			"• Pap Smear: Every 3 years for women 21-65\n" +
			"• HPV Test: Co-testing recommended\n\n" +
			"Early Detection:\n" +
			"• 95%+ cure rate when detected early\n" +
			"• Regular screening saves lives\n\n" +
			"Warning Signs:\n" +
			"• Unusual vaginal bleeding\n" +
			"• Pelvic pain\n" +
			"• Abnormal discharge\n" +
			"• Pain during intercourse\n\n" +
			"IMPORTANT: Most cases are preventable with screening!\n" +
			"Call us to schedule your screening. " + MEDICAL_DISCLAIMER;
	}

	private String handleColorectalCancer() {
		return "🔍 COLORECTAL CANCER INFORMATION\n\n" +
			"Screening Guidelines:\n" +
			"• Colonoscopy: Every 10 years (age 45+)\n" +
			"• FOBT: Fecal occult blood test annually\n" +
			"• FIT: Fecal immunochemical test annually\n\n" +
			"Risk Factors:\n" +
			"• Age 45+\n" +
			"• Family history\n" +
			"• Inflammatory bowel disease\n" +
			"• Fatty/low-fiber diet\n" +
			"• Sedentary lifestyle\n\n" +
			"Warning Signs:\n" +
			"• Blood in stool\n" +
			"• Change in bowel habits\n" +
			"• Persistent abdominal pain\n" +
			"• Unexplained weight loss\n\n" +
			"Early detection through screening improves survival. " + MEDICAL_DISCLAIMER;
	}

	private String handleSymptoms() {
		return "🔔 GENERAL CANCER SYMPTOMS TO MONITOR\n\n" +
			"Seek medical attention if you experience:\n\n" +
			"🔴 URGENT WARNING SIGNS:\n" +
			"• Unexplained weight loss (>5kg/month)\n" +
			"• Persistent fatigue\n" +
			"• Fever lasting >2 weeks\n" +
			"• Persistent pain\n" +
			"• Unusual bleeding/discharge\n" +
			"• Lumps/swelling anywhere\n" +
			"• Skin changes or moles\n\n" +
			"⏰ Report Early: Many cancers are curable when caught early.\n\n" +
			"DON'T PANIC: These symptoms may indicate non-cancerous conditions.\n" +
			"Consult a doctor for proper diagnosis. " + MEDICAL_DISCLAIMER;
	}

	private String handlePrevention() {
		return "🛡️ CANCER PREVENTION GUIDELINES\n\n" +
			"LIFESTYLE MODIFICATIONS:\n\n" +
			"1️⃣ QUIT SMOKING\n" +
			"   • #1 preventable cancer cause\n\n" +
			"2️⃣ MAINTAIN HEALTHY WEIGHT\n" +
			"   • BMI 18.5-24.9\n" +
			"   • Regular exercise (30+ min/day)\n\n" +
			"3️⃣ BALANCED DIET\n" +
			"   • Fruits, vegetables, whole grains\n" +
			"   • Limit red/processed meat\n" +
			"   • Reduce alcohol consumption\n\n" +
			"4️⃣ SUN PROTECTION\n" +
			"   • Use SPF 30+ sunscreen\n" +
			"   • Avoid peak sun hours\n\n" +
			"5️⃣ VACCINATION\n" +
			"   • HPV vaccine (cervical cancer)\n" +
			"   • Hepatitis B vaccine\n\n" +
			"6️⃣ REGULAR SCREENING\n" +
			"   • Age-appropriate screening tests\n\n" +
			"Prevention is easier than treatment! " + MEDICAL_DISCLAIMER;
	}

	private String handleTreatment() {
		return "💊 CANCER TREATMENT OPTIONS\n\n" +
			"Common Treatment Modalities:\n\n" +
			"🔬 SURGERY\n" +
			"• Tumor removal\n" +
			"• Often first-line treatment\n\n" +
			"💉 CHEMOTHERAPY\n" +
			"• Systemic drug therapy\n" +
			"• Targets rapidly dividing cells\n\n" +
			"☢️ RADIATION THERAPY\n" +
			"• High-energy beams\n" +
			"• Precise tumor targeting\n\n" +
			"🎯 TARGETED THERAPY\n" +
			"• Specific protein targeting\n" +
			"• Fewer side effects\n\n" +
			"🧬 IMMUNOTHERAPY\n" +
			"• Boosts immune system\n" +
			"• Latest advancement\n\n" +
			"COMBINATION THERAPY:\n" +
			"• Multi-modal approaches often used\n" +
			"• Depends on cancer type/stage\n\n" +
			"Treatment plan varies by individual. Consult oncologist. " + MEDICAL_DISCLAIMER;
	}

	private String handleEmotionalSupport() {
		return "💝 EMOTIONAL & MENTAL HEALTH SUPPORT\n\n" +
			"Cancer diagnosis can be emotionally overwhelming:\n\n" +
			"✨ COUNSELING SERVICES\n" +
			"• Professional psychologists available\n" +
			"• Individual & group counseling\n" +
			"• Completely confidential\n\n" +
			"👥 SUPPORT GROUPS\n" +
			"• Connect with survivors\n" +
			"• Share experiences\n" +
			"• Weekly meetings\n\n" +
			"💬 MINDFULNESS & WELLNESS\n" +
			"• Yoga & meditation classes\n" +
			"• Stress management programs\n" +
			"• Art & music therapy\n\n" +
			"👨‍👩‍👧‍👦 FAMILY SUPPORT\n" +
			"• Caregiver counseling\n" +
			"• Family therapy sessions\n\n" +
			"📞 CRISIS SUPPORT 24/7\n" +
			"• Call: +91-XXXX-XXXX\n" +
			"• Emergency emotional support\n\n" +
			"You're not alone. We're here for you. " + MEDICAL_DISCLAIMER;
	}

	private String handleScreening() {
		return "🔬 CANCER SCREENING PROGRAMS\n\n" +
			"Age-Appropriate Screening:\n\n" +
			"👩 WOMEN:\n" +
			"• Breast: Mammography 40+ annually\n" +
			"• Cervix: Pap smear 21-65 every 3 years\n" +
			"• Colon: Colonoscopy 45+ every 10 years\n\n" +
			"👨 MEN:\n" +
			"• Prostate: PSA test 50+ (discuss with doctor)\n" +
			"• Colon: Colonoscopy 45+ every 10 years\n\n" +
			"🏥 OUR FREE SCREENING:\n" +
			"• Cancer risk assessment\n" +
			"• Preliminary screening\n" +
			"• No-cost consultation\n" +
			"• Referral to specialists if needed\n\n" +
			"📅 EARLY DETECTION = BETTER OUTCOMES\n" +
			"• 90%+ survival when detected early\n" +
			"• Book your screening today\n\n" +
			"Call us to schedule. " + MEDICAL_DISCLAIMER;
	}

	private String handleFinancialSupport() {
		return "💰 FINANCIAL & ASSISTANCE PROGRAMS\n\n" +
			"Jarurat Care Foundation offers:\n\n" +
			"🆓 FREE SERVICES:\n" +
			"• Screening camps\n" +
			"• Initial consultation\n" +
			"• Counseling\n\n" +
			"💳 PAYMENT OPTIONS:\n" +
			"• Flexible payment plans\n" +
			"• Installment schemes available\n" +
			"• No-interest financing (eligible patients)\n\n" +
			"🏛️ GOVERNMENT SCHEMES:\n" +
			"• Ayushman Bharat\n" +
			"• State health schemes\n" +
			"• PMJAY eligibility check\n\n" +
			"❤️ SPECIAL ASSISTANCE:\n" +
			"• Below poverty line (BPL) support\n" +
			"• Emergency financial aid\n" +
			"• Medicine cost assistance\n\n" +
			"📋 REQUIRED DOCUMENTS:\n" +
			"• ID proof, income proof\n" +
			"• Medical prescription\n" +
			"• Aadhar/PAN\n\n" +
			"Contact our finance team for personalized assistance. " + MEDICAL_DISCLAIMER;
	}

	private String handleStaging() {
		return "📊 CANCER STAGING & GRADING\n\n" +
			"STAGING (TNM System):\n\n" +
			"Stage 0: Carcinoma in situ (localized)\n" +
			"Stage 1: Small tumor, limited spread\n" +
			"Stage 2: Larger tumor, possible lymph node involvement\n" +
			"Stage 3: Advanced local/regional spread\n" +
			"Stage 4: Metastatic (spread to distant sites)\n\n" +
			"GRADING (Cell Differentiation):\n\n" +
			"Grade 1: Well-differentiated (slow growing)\n" +
			"Grade 2: Moderately differentiated\n" +
			"Grade 3: Poorly differentiated (fast growing)\n" +
			"Grade 4: Undifferentiated (most aggressive)\n\n" +
			"🎯 PROGNOSIS DEPENDS ON:\n" +
			"• Stage at diagnosis\n" +
			"• Cancer grade\n" +
			"• Patient's overall health\n" +
			"• Treatment response\n\n" +
			"Early-stage detection significantly improves outcomes!\n" +
			"Discuss your specific stage with your oncologist. " + MEDICAL_DISCLAIMER;
	}

	private String handleNutrition() {
		return "🥗 CANCER PREVENTION & NUTRITION\n\n" +
			"RECOMMENDED FOODS:\n" +
			"• Fruits (berries, citrus)\n" +
			"• Vegetables (green, cruciferous)\n" +
			"• Whole grains (brown rice, oats)\n" +
			"• Lean proteins (fish, chicken)\n" +
			"• Nuts & seeds (omega-3 fatty acids)\n\n" +
			"LIMIT CONSUMPTION:\n" +
			"• Red meat (beef, pork)\n" +
			"• Processed meats (sausage, bacon)\n" +
			"• Sugary beverages\n" +
			"• Alcohol (especially high consumption)\n" +
			"• Salt-cured foods\n\n" +
			"🥤 HYDRATION:\n" +
			"• 8-10 glasses water daily\n" +
			"• Green tea has antioxidants\n\n" +
			"⚖️ WEIGHT MANAGEMENT:\n" +
			"• Maintain healthy BMI\n" +
			"• Regular physical activity\n\n" +
			"Post-treatment: Consult nutritionist for personalized diet. " + MEDICAL_DISCLAIMER;
	}

	private String handleHelp() {
		return "📚 JARURAT CARE FOUNDATION - CANCER HEALTH CONSULTANT\n\n" +
			"Available Topics:\n\n" +
			"🎀 'Breast Cancer' - Info & screening\n" +
			"🫁 'Lung Cancer' - Signs & prevention\n" +
			"💗 'Cervical Cancer' - Vaccination & screening\n" +
			"🔍 'Colorectal Cancer' - Early detection\n\n" +
			"🔔 'Symptoms' - Warning signs\n" +
			"🛡️ 'Prevention' - Lifestyle tips\n" +
			"💊 'Treatment' - Treatment options\n" +
			"💝 'Support' - Emotional counseling\n\n" +
			"🔬 'Screening' - Screening programs\n" +
			"💰 'Financial' - Assistance programs\n" +
			"📊 'Staging' - Understanding stages\n" +
			"🥗 'Nutrition' - Diet guidance\n\n" +
			"📞 DIRECT CONTACT:\n" +
			"Phone: +91-XXXX-XXXX\n" +
			"Email: health@jaruratcare.org\n\n" +
			"How can I assist you today?";
	}

	private String handleFarewell() {
		return "👋 Thank you for using Jarurat Care Foundation Cancer Health Consultant.\n\n" +
			"Remember:\n" +
			"✔️ Regular screening saves lives\n" +
			"✔️ Early detection improves outcomes\n" +
			"✔️ Healthy lifestyle is preventive\n" +
			"✔️ We're always here to help\n\n" +
			"📞 For urgent concerns, call our hotline: +91-XXXX-XXXX\n\n" +
			"Take care and stay healthy! ❤️";
	}


	private boolean containsKeyword(String query, String[] keywords) {
		for (String keyword : keywords) {
			if (query.contains(keyword.toLowerCase())) {
				return true;
			}
		}
		return false;
	}
}
