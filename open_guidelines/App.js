import React, { useRef, useState, useEffect } from 'react';
import { BackHandler } from 'react-native';
import { SafeAreaProvider, SafeAreaView } from 'react-native-safe-area-context';
import { WebView } from 'react-native-webview';
import TopBar from './TopBar';
import assetManifest from './assetManifest.js';
import { ensureAssetsCopied } from './copyAssests';

export default function App() {
  const webViewRef = useRef(null);
  const [pageStack, setPageStack] = useState(['title.html']);
  const [assetsRoot, setAssetsRoot] = useState(null);

  const currentPage = pageStack[pageStack.length - 1];

  useEffect(() => {
    ensureAssetsCopied().then(setAssetsRoot);
  }, []);

  // Handle Android hardware back button
  useEffect(() => {
    const onBackPress = () => {
      if (pageStack.length > 1) {
        goBack();
        return true; // handled — don't exit the app
      }
      return false; // on root page, let default back behavior happen
    };

    const subscription = BackHandler.addEventListener('hardwareBackPress', onBackPress);
    return () => subscription.remove();
  }, [pageStack]);

  const navigateTo = (page) => {
    setPageStack((prevStack) => {
      // avoid pushing a duplicate if it's already the current page
      if (prevStack[prevStack.length - 1] === page) return prevStack;
      return [...prevStack, page];
    });
  };

  const goBack = () => {
    setPageStack((prevStack) => {
      if (prevStack.length <= 1) return prevStack;
      return prevStack.slice(0, -1);
    });
  };

  const handleNavigation = (request) => {
    const url = request.url;
    console.log('Navigation requested:', url);
    const match = Object.keys(assetManifest).find((page) => url.endsWith(page));
    console.log('Matched page:', match);
    if (match) {
      navigateTo(match);
      return false;
    }
    return false;
  };

  if (!assetsRoot) {
    return null;
  }

  return (
    <SafeAreaProvider>
      <SafeAreaView style={{ flex: 1 }}>
        {currentPage !== 'title.html' && (
          <TopBar
            onNavigate={navigateTo}
            onBack={goBack}
            canGoBack={pageStack.length > 1}
          />
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