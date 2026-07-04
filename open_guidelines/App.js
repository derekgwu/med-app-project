import React, { useRef, useState, useEffect } from 'react';
import { SafeAreaProvider, SafeAreaView } from 'react-native-safe-area-context';
import { WebView } from 'react-native-webview';
import TopBar from './TopBar';
import pages from './pagesMap.js';
import { ensureAssetsCopied } from './copyAssests';

export default function App() {
  const webViewRef = useRef(null);
  const [currentPage, setCurrentPage] = useState('title.html');
  const [assetsRoot, setAssetsRoot] = useState(null);

  useEffect(() => {
    ensureAssetsCopied().then(setAssetsRoot);
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

  if (!assetsRoot) {
    return null; // or a loading spinner
  }

  return (
    <SafeAreaProvider>
      <SafeAreaView style={{ flex: 1 }}>
        {currentPage !== 'title.html' && (
          <TopBar onNavigate={setCurrentPage} />
        )}
        <WebView
          ref={webViewRef}
          source={{ uri: assetsRoot + currentPage }}
          originWhitelist={['*']}
          onShouldStartLoadWithRequest={handleNavigation}
          allowFileAccess={true}
          allowFileAccessFromFileURLs={true}
          allowUniversalAccessFromFileURLs={true}
          javaScriptEnabled={true}
        />
      </SafeAreaView>
    </SafeAreaProvider>
  );
}