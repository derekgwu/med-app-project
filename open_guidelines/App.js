import React from 'react';
import { StyleSheet, View, Platform, Text } from 'react-native';
import {WebView} from 'react-native-webview'
import {NavigationContainer} from '@react-navigation/native';
import {createNativeStackNavigator} from '@react-navigation/native-stack';
import TopBar from './TopBar';
import Home from './Home.js'

const Stack = createNativeStackNavigator();

const Demo = () => {
  return (
    <View style={styles.container}>
      <TopBar/>
      <WebView
        source={require('./assets/demo.html')}
        style={{ flex: 1 }}
      />
    </View>
  );
}

const App = () => {
  return (
    <NavigationContainer>
      <Stack.Navigator initialRouteName="Main" screenOptions={{ headerShown: false }}>
        <Stack.Screen name="Main" component={Home}/>
        <Stack.Screen name="Demo" component={Demo}/>
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