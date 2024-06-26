import React from 'react';
import { StyleSheet, View, Platform, Text } from 'react-native';
import {WebView} from 'react-native-webview'
import TopBar  from './TopBar.js';
import {NavigationContainer} from '@react-navigation/native';
import {createNativeStackNavigator} from '@react-navigation/native-stack';
import Home from './Home.js'

const Stack = createNativeStackNavigator();

const App = () => {
  const source = {
    link : require('./assets/demo.html')
  }
  const htmlFilePath = require('./assets/demo.html');
  const uri = htmlFilePath

  return (
    <NavigationContainer>
      <Stack.Navigator initialRouteName="Main" screenOptions={{ headerShown: false }}>
        <Stack.Screen name="Main" component={Home}/>
      
      </Stack.Navigator>
    </NavigationContainer>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: '#fff',
  },
});

export default App;
