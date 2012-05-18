Proguard Demo
============

Proguard Demo for the Cincy Android Dev Group

### Useful Links

* <http://proguard.sourceforge.net/>
* <http://proguard.sourceforge.net/#manual/examples.html>
* <https://code.google.com/p/dex2jar/>
* <http://java.decompiler.free.fr/?q=jdgui>

### Why?

* Security
* Protect IP
* Reduce APK Size
* Optimization
* Remove Logging

### Syntax
* <http://proguard.sourceforge.net/#manual/usage.html>
* \# is used for comments
* Always use fully-qualified class names

### Reflection
* Be very specific
* Don't assume proguard will catch it

### First Steps

1. Create proguard.cfg
2. Add `proguard.config=proguard.cfg` to project.properties
3. To obfuscate in debug builds, edit your -debug-obfuscation-check target to look like this:

```
<target name="-debug-obfuscation-check">
        <!-- yes, we want to obfuscate in debug too!!!! -->
        <condition property="proguard.enabled" value="true" else="false">
            <and>
                <isset property="proguard.config" />
            </and>
        </condition>
        <if condition="${proguard.enabled}">
            <then>
                <!-- Secondary dx input (jar files) is empty since all the
                     jar files will be in the obfuscated jar -->
                <path id="out.dex.jar.input.ref" />
            </then>
        </if>
    </target>
```

### Initial Template

```
#Remove all the injar/outjar/libraryjar junk, the android ant script takes care of this

-dontpreverify
-repackageclasses ''
-allowaccessmodification
-optimizations !code/simplification/arithmetic
-keepattributes *Annotation*

-keep public class * extends android.app.Activity
-keep public class * extends android.app.Application
-keep public class * extends android.app.Service
-keep public class * extends android.content.BroadcastReceiver
-keep public class * extends android.content.ContentProvider

-keep public class * extends android.view.View {
    public <init>(android.content.Context);
    public <init>(android.content.Context, android.util.AttributeSet);
    public <init>(android.content.Context, android.util.AttributeSet, int);
    public void set*(...);
}

-keepclasseswithmembers class * {
    public <init>(android.content.Context, android.util.AttributeSet);
}

-keepclasseswithmembers class * {
    public <init>(android.content.Context, android.util.AttributeSet, int);
}

-keepclassmembers class * implements android.os.Parcelable {
    static android.os.Parcelable$Creator CREATOR;
}

-keepclassmembers class **.R$* {
    public static <fields>;
}
```
### Fragments

```
-keep public class * extends android.support.v4.app.Fragment
-keep public class * extends android.app.Fragment
```
### 3rd Party Libraries
```
-keep class android.** {*;}
-keep class com.millennialmedia.android.** {*;)
-keep class com.google.ads.** {*;}
```
### Removing Logging
```
-assumenosideeffects class android.util.Log {
    public static *** e(...);
    public static *** w(...);
    public static *** wtf(...);
    public static *** d(...);
    public static *** v(...);
}
```
### Serializables
```
-keepnames class * implements java.io.Serializable

-keepclassmembers class * implements java.io.Serializable {
    static final long serialVersionUID;
    private static final java.io.ObjectStreamField[] serialPersistentFields;
    !static !transient <fields>;
    !private <fields>;
    !private <methods>;
    private void writeObject(java.io.ObjectOutputStream);
    private void readObject(java.io.ObjectInputStream);
    java.lang.Object writeReplace();
    java.lang.Object readResolve();
}
```

### Click Methods

```
-keepclassmembers class * {

public void *ButtonClicked(android.view.View);

}
```

### Native Methods
```
-keepclasseswithmembernames class * {
    native <methods>;
}
```

### Deobfuscating Stack Traces
* Save mapping.txt from any build released
* retrace.sh/bat is in sdk.dir/tools/proguard/bin
* No free plans, great product though. <http://www.hockeyapp.net/>
* Free plans, but very limited.  Open source projects have more robust free plans. <http://www.bugsense.com/>

