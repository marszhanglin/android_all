Library Project including compatibility ActionBar.

This can be used by an Android project to provide
access to ActionBar on applications running on API 7+.

There is technically no source, but the src folder is necessary
to ensure that the build system works.  The content is actually
located in libs/android-support-v7-appcompat.jar.
The accompanying resources must also be included in the application.

CHANGELOG:

19.1.2
------

- Fixed an issue where certain Samsung phones which did not include the full JBMR2 APIs would crash.

19.1.1
------

- Fixed an issue which caused an Android Runtime Exception on devices with Android 4.0 or higher.
  See [the issue on google code](https://code.google.com/p/android/issues/detail?id=66120).