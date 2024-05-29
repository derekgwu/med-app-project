import React from 'react';
import { StyleSheet, View, Platform, Text } from 'react-native';
import {WebView} from 'react-native-webview'
import {NavigationContainer} from '@react-navigation/native';
import {createNativeStackNavigator} from '@react-navigation/native-stack';
import TopBar  from './TopBar';

const Home = () => {
  const source = {
    link : require('./assets/demo.html')
  }
  const htmlFilePath = require('./assets/demo.html');
  const uri = htmlFilePath

  return (
    <View style={styles.container}>
      <TopBar/>
      <WebView
        source={source.link}
        style={{ flex: 1 }}
      />
    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: '#fff',
  },
});

export default Home;