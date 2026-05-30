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

When prompted, press S to switch to Expo Go mode and press A to run on android

### RAG Offline Model

Start the virtual environment
```
source venv/bin/activate
```
