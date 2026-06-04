# Open Guidelines

## 🌍 Making Clinical Guidelines Accessible to Healthcare Workers in Malawi

**Open Guidelines** is an initiative dedicated to providing free, unrestricted access to clinical guidelines used by healthcare professionals in hospitals worldwide. We believe that life-saving medical knowledge should be available to every healthcare worker, regardless of their location or resources.

### 📱 Project Overview

This critical app provides access to **neonatal and newborn care guidelines** developed in partnership with:

- **Malawi's Ministry of Health**
- **NEST 360°** (a collaboration between Rice University and Malawi College of Medicine)

The application supports healthcare workers in managing **neonatal morbidity and mortality** and properly utilizing **essential newborn care technologies**—directly impacting maternal, newborn, and child health outcomes across Malawi.


The original application has become **outdated and is no longer available** on the Apple App Store or Google Play Store due to device incompatibilities. Despite this setback, **clinicians in Malawi continue to request access** to this vital resource, highlighting the urgent need for a modern, compatible solution.





# Application Setup

## macOS
1. Install `node.js` and node package manager

Install `node.js` through and verify:
```
brew install node
node -v
npm -v
```

2. Download XCode from the app store (may require a software update)

3. Navigate to the settings to install the iOS component
```
Xcode -> Settings -> Components
```

4. 
Open and run the simulator 
```
open -a Simulator
```

#### Common Errors
If you get `error: tool 'xcodebuild' requires Xcode but active developer directory is a command line tool instance:

```
sudo xcode-select -s /Applications/Xcode.app/Contents/Developer
xcode-select -p
```

## Windows

### Emulator Installation

#### 0. Download and install [Android Studio](https://developer.android.com/studio)

#### 1. Open Android Studio

Launch Android Studio on your computer.

#### 2. Access the Device Manager

- **Method 1:** Click **Tools** → **Device Manager** in the top menu
- **Method 2:** Click the **Device Manager** icon in the toolbar (usually on the right side)

#### 3. Create a Virtual Device (AVD)

1. Click **"Create device"** or the **"+"** button in the Device Manager
2. **Select Hardware:**
   - Choose a device definition (e.g., **Pixel 5**, **Pixel 6**, **Nexus 5X**)
   - Click **Next**
3. **Select System Image:**
   - Choose an Android version (API level)
   - Download the system image if it shows a **Download** link
   - Recommended: Choose an **x86** or **x86_64** image for better performance
   - Click **Next**
4. **Verify Configuration:**
   - Review your settings (memory, internal storage, etc.)
   - Optionally enable **Secondary SD Card** if needed
   - Click **Finish**

#### 4. Launch the Emulator

1. In **Device Manager**, find your newly created virtual device
2. Click the **Play button** (▶️) next to your device name
3. Wait for the emulator to boot (this may take 1-3 minutes on first launch)

#### 5. Verify Emulator is Running

- The emulator window should open showing the Android home screen
- Your device should appear in the list of available devices when running your app

## Troubleshooting Tips

| Issue | Solution |
|-------|----------|
| Emulator is slow | Enable **Hyper-V** (Windows), **HAXM**, or **Android Host CPU Driver** for hardware acceleration |
| Blue screen on Windows | Enable **Virtualization** in BIOS/UEFI settings |
| Emulator won't start | Check that **Virtualization Technology** is enabled in your computer's BIOS |
| Low memory warnings | Increase RAM allocation in AVD settings (Edit → Show Advanced Settings) |



#### 6. Install Node.js
Install [Node.js](https://nodejs.org/en) here and run through the setup. To check if Node.js is installed, run: 
```bash
node -v
```
If you get a version number like `v20.11.1`, it is installed.

### Install Node Package Manager (npm)
Node Package Manager should be installed with Node.js. You can check by running 
```bash
npm -v
```

Additionally, you may need to install the dependecies of the app which are
``` bash
npm install -g create-react-native-app
npm install react-native-webview
```

(you can likely bypass this by run `npm install`)

### Running the App
Go into Android Studio and start up an emulator (Pixel 4 API 30 will work fine). Once the emulator is running, run 

```bash
npx expo start --clear
```

Press `A` to run on android

### Emulator Tips

The Android emulator is a virtual machine with its own network stack.
`localhost` inside the emulator points to itself, not your Windows machine.

| Address | Resolves To |
|---|---|
| `localhost` / `127.0.0.1` | The emulator itself |
| `10.0.2.2` | Your Windows host machine |

---

####  Step 1 — Set the Packager Hostname

In your terminal before starting Expo:
```bash
set REACT_NATIVE_PACKAGER_HOSTNAME=10.0.2.2
```

Or set it permanently in `app.json`:
```json
{
  "expo": {
    "hostUri": "10.0.2.2:8081"
  }
}
```

---

#### Step 2 — Forward the Port via ADB

Make sure the emulator is fully booted (Android home screen visible), then run:
```bash
adb reverse tcp:8081 tcp:8081
```

This should return immediately with just:
```
8081
```

If it hangs, the emulator isn't ready yet. Verify with:
```bash
adb devices
```

You should see `device` (not `offline`):
```
List of devices attached
emulator-5554   device
```

If the list is empty, restart the ADB server:
```bash
adb kill-server
adb start-server
adb devices
```

---

#### Step 3 — Start Expo

```bash
npx expo start
```

---

## Full Command Sequence

```bash
adb reverse tcp:8081 tcp:8081 && set REACT_NATIVE_PACKAGER_HOSTNAME=10.0.2.2 && npx expo start
```

---

## Notes
- Run `adb reverse` again any time the emulator restarts
- If `adb` is not recognized, find it at:
  `C:\Users\<you>\AppData\Local\Android\Sdk\platform-tools\adb.exe`

### RAG Offline Model

Start the virtual environment
```
source venv/bin/activate
```
