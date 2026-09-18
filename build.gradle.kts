// Ensure debug.keystore exists before any subproject signing configuration evaluates
val debugKeystore = file("${rootDir}/debug.keystore")
if (!debugKeystore.exists()) {
  val b64Keystore = file("${rootDir}/debug.keystore.base64")
  if (b64Keystore.exists()) {
    try {
      val cleanedBase64 = b64Keystore.readText().replace("\\s".toRegex(), "")
      val decodedBytes = java.util.Base64.getDecoder().decode(cleanedBase64)
      debugKeystore.writeBytes(decodedBytes)
    } catch (e: Exception) {
      project.logger.warn("Could not decode debug.keystore.base64: ${e.message}")
    }
  }
  if (!debugKeystore.exists()) {
    try {
      ProcessBuilder(
        "keytool", "-genkey", "-v",
        "-keystore", debugKeystore.absolutePath,
        "-storepass", "android",
        "-alias", "androiddebugkey",
        "-keypass", "android",
        "-keyalg", "RSA",
        "-keysize", "2048",
        "-validity", "10000",
        "-dname", "CN=Android Debug,O=Android,C=US"
      ).inheritIO().start().waitFor()
    } catch (e: Exception) {
      project.logger.warn("Could not generate debug.keystore with keytool: ${e.message}")
    }
  }
}

// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
  alias(libs.plugins.android.application) apply false
  alias(libs.plugins.kotlin.compose) apply false
  alias(libs.plugins.google.devtools.ksp) apply false
  alias(libs.plugins.roborazzi) apply false
  alias(libs.plugins.secrets) apply false
  alias(libs.plugins.google.services) apply false
}
