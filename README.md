# ShiftSix

Environment setup:
1) Download and install Android Studio
2) Download Code from this page as a ZIP file, or using Git directly.
NOTE: directly importing from Git through Android Studio will cause incorrect behavior.
	2a. Extract from ZIP to file.
3) In Android Studio press "Import Project (Grade, Eclipse ADT, etc)"
4) Select the ShiftSix folder within downloaded files (NOT ShiftSix-main if downloading as a ZIP).
5) If there are still issues press File -> Synce Project with Gradle Files

Emulator setup:
1) Open the AVD Manager in top right:
![AVD Manager](https://i.imgur.com/Y52XtZP.png)
2) Create a Virtual Device (personally recommend Pixel 3a) and hit Next.
	2a. Ensure the device has Play Store support, indicated by a Play button in this column.
3) Download API Level 29 (Release Name Q) and select it/hit Next when finished.
4) Press Finish.

Finally, launch the Emulator and App by selecting this API 29 device from the AVD dropdown menu, and pressing the Play button (or just launch the emulator, and start the app from the emulated phone as normal).