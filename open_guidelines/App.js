import React, { useRef, useState, useEffect } from 'react';
import { SafeAreaView } from 'react-native-safe-area-context';
import { WebView } from 'react-native-webview';
import { Asset } from 'expo-asset';
import TopBar from './TopBar';


import pages from './pagesMap.js';


export default function App() {
  const webViewRef = useRef(null);
  const [currentPage, setCurrentPage] = useState('title.html');
  const [resolvedUris, setResolvedUris] = useState({});

  useEffect(() => {
    const resolveAssets = async () => {
      const uris = {};
      for (const [name, module] of Object.entries(pages)) {
        const asset = Asset.fromModule(module);
        await asset.downloadAsync();
        uris[name] = { uri: asset.localUri };
      }
      setResolvedUris(uris);
    };
    resolveAssets();
  }, []);

  const handleNavigation = (request) => {
    const url = request.url;
    const match = Object.keys(pages).find(page => url.endsWith(page));
    if (match) {
      setCurrentPage(match);
      return false;
    }
    return false;
  };

  if (!resolvedUris['title.html']) {
    return null; // or a loading spinner
  }

  return (
    <SafeAreaView style={{ flex: 1 }}>
      {currentPage !== 'title.html' && (
        <TopBar onNavigate={setCurrentPage} />
      )}
      <WebView
        ref={webViewRef}
        source={resolvedUris[currentPage]}
        originWhitelist={['*']}
        onShouldStartLoadWithRequest={handleNavigation}
        allowFileAccess={true}
        allowFileAccessFromFileURLs={true}
        allowUniversalAccessFromFileURLs={true}
        javaScriptEnabled={true}
      />
    </SafeAreaView>
  );
}