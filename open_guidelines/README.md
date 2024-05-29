# How To View A Prototype of the App

### Install Node.js
Install [Node.js](https://nodejs.org/en) here and run through the setup. To check if Node.js is installed, run: 
```bash
node -v
```
If you get a version number like `v20.11.1`, it is installed

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

### Running the App
Go into Android Studio and start up an emulator (Pixel 4 API 30 will work fine). Once the emulator is running, run 

```bash
npm start
```

When prompted, press S to switch to Expo Go mode and press A to run on android