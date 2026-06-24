@echo off
echo Building Open Guidelines...
cd C:\Users\dchen\Desktop\og\open_guidelines\android
call gradlew.bat app:assembleRelease
if %errorlevel% neq 0 (
    echo Build FAILED!
    pause
    exit /b 1
)
echo Build successful! Installing...
adb install -r app\build\outputs\apk\release\app-release.apk
if %errorlevel% neq 0 (
    echo Install FAILED!
    pause
    exit /b 1
)
echo Done! App installed successfully.
pause