# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile

-keep public class * implements com.bumptech.glide.module.GlideModule
-keep public class * extends com.bumptech.glide.module.AppGlideModule
-keep public enum com.bumptech.glide.load.resource.bitmap.ImageHeaderParser$** {
  **[] $VALUES;
  public *;
}

# Platform calls Class.forName on types which do not exist on Android to determine platform.
-dontnote retrofit2.Platform
# Platform used when running on Java 8 VMs. Will not be used at runtime.
-dontwarn retrofit2.Platform$Java8
# Retain generic type information for use by reflection by converters and adapters.
-keepattributes Signature
# Retain declared checked exceptions for use by a Proxy instance.
-keepattributes Exceptions

-keepattributes *Annotation*
-keep class eu.inmite.android.lib.validations.form.annotations.** { *; }
-keep class * implements eu.inmite.android.lib.validations.form.iface.ICondition
-keep class * implements eu.inmite.android.lib.validations.form.iface.IValidator
-keep class * implements eu.inmite.android.lib.validations.form.iface.IFieldAdapter
-keepclassmembers class ** {
	@eu.inmite.android.lib.validations.form.annotations.** *;
}

-dontwarn com.google.errorprone.annotations.*

-dontwarn okhttp3.**
-dontwarn okio.**
-dontwarn javax.annotation.**
# A resource is loaded with a relative path so the package of this class must be preserved.
-keepnames class okhttp3.internal.publicsuffix.PublicSuffixDatabase
-dontwarn pl.charmas.android.reactivelocation2.**
-dontwarn com.google.firebase.database.**

-keep class com.android.vending.billing.**
-dontwarn durdinapps.rxfirebase2.**