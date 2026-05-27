import React, { useRef } from 'react';
import { SafeAreaView, Platform } from 'react-native';
import { WebView } from 'react-native-webview';
import TopBar from './TopBar';


// Import all HTML files you need
const pages = {
  'title.html': require('./assets/title.html'),
  'demo.html': require('./assets/demo.html'),
};

export default function App() {
  const webViewRef = useRef(null);
  const [currentPage, setCurrentPage] = React.useState('title.html');

  // Intercept navigation requests
  const handleNavigation = (request) => {
    const url = request.url;

    // Check if it's a local HTML file link
    const match = Object.keys(pages).find(page => url.endsWith(page));
    if (match) {
      setCurrentPage(match);
      return false; // Block WebView from handling it
    }

    return true; // Allow external URLs
  };

  return (
    <SafeAreaView style={{ flex: 1 }}>
      {currentPage !== 'title.html' && (
        <TopBar onNavigate={setCurrentPage} />
      )}
      <WebView
        ref={webViewRef}
        source={pages[currentPage]}
        originWhitelist={['*']}
        onShouldStartLoadWithRequest={handleNavigation}
        allowFileAccess={true}
        javaScriptEnabled={true}
      />
    </SafeAreaView>
  );
}