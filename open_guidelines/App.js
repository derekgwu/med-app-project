import React from 'react';
import { StyleSheet, View, Platform, Text } from 'react-native';
import {WebView} from 'react-native-webview'

export default function App() {
  const htmlFilePath = require('./assets/demo.html');
  const uri = htmlFilePath

  return (
    <View style={styles.container}>
      <Text>Here</Text>
      <WebView
        source={require('./assets/demo.html')}
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
