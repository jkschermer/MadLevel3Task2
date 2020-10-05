# MadLevel3Task2

# LEVEL 3 questions

**Which stages of an activity lifecycle exists?**

- onCreate()
- onStart()
- onResume()
- onPause()
- onStop()
- onDestroy()

**Which are the two kind of intents, and what is the difference?**

- Explicit intents
- Implicit intents

In an explicit intent it will specify which application will be used, by giving the name of the class or the package name of the app. On the other hand with an implicit intent there is no specific component declared. Besides that in an implicit intent the action is handled by another apps component, where with explicit intent the component inside of the application is used.

**What is the difference between Parcelables and Serializables?**

Parcelables are faster than Serializables and writing code in parcelables is a little bit complex compared to serialization. Serializable is a standard Java interface and it makes use of the approach reflection. This creates a lot of temporary objects and cause quite a bit of garbage collection. In the parcelable process they are more explicit about the serialization process, that&#39;s why it is faster.

**What is the purpose of the analyzer?**

It provides insight into the composition of your APK after the build process completes. By using the analyzer you can reduce the time you spend on debugging issues within your app and helps reduce your APK size.
