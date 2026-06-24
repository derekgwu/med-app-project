import React, { useRef, useState, useEffect } from 'react';
import { SafeAreaView } from 'react-native';
import { WebView } from 'react-native-webview';
import { Asset } from 'expo-asset';
import TopBar from './TopBar';


// Import all HTML files you need
const pages = {
  'A1_Neonataladmissionsheet.html': require('./assets/A1_Neonataladmissionsheet.html'),
  'A2_Paedicatricadmissionsheet.html': require('./assets/A2_Paedicatricadmissionsheet.html'),
  'A3_CCP.html': require('./assets/A3_CCP.html'),
  'A4_NeonatalReview.html': require('./assets/A4_NeonatalReview.html'),
  'A5_NeonatalAudit.html': require('./assets/A5_NeonatalAudit.html'),
  'A6_Neonatalreferral.html': require('./assets/A6_Neonatalreferral.html'),
  'acknowledgements.html': require('./assets/acknowledgements.html'),
  'acronyms.html': require('./assets/acronyms.html'),
  'Appendices.html': require('./assets/Appendices.html'),
  'bibl.html': require('./assets/bibl.html'),
  'C10_Hypothermia.html': require('./assets/C10_Hypothermia.html'),
  'C111416_Emergencies.html': require('./assets/C111416_Emergencies.html'),
  'C11_Hypoglycaemia.html': require('./assets/C11_Hypoglycaemia.html'),
  'C12_18fluids.html': require('./assets/C12_18fluids.html'),
  'C12_fluidmanagement.html': require('./assets/C12_fluidmanagement.html'),
  'C13_Jaundice.html': require('./assets/C13_Jaundice.html'),
  'C14_Shock.html': require('./assets/C14_Shock.html'),
  'C15_17Infections.html': require('./assets/C15_17Infections.html'),
  'C15_Sepsis.html': require('./assets/C15_Sepsis.html'),
  'C16_ComaConvulsions.html': require('./assets/C16_ComaConvulsions.html'),
  'C17_Meningitis.html': require('./assets/C17_Meningitis.html'),
  'C18_Diarrhoea.html': require('./assets/C18_Diarrhoea.html'),
  'C19_20Transport.html': require('./assets/C19_20Transport.html'),
  'C19_Referral.html': require('./assets/C19_Referral.html'),
  'C1_Triage.html': require('./assets/C1_Triage.html'),
  'C20_Discharge.html': require('./assets/C20_Discharge.html'),
  'C21_essential.html': require('./assets/C21_essential.html'),
  'C22_23Audit.html': require('./assets/C22_23Audit.html'),
  'C22_audit.html': require('./assets/C22_audit.html'),
  'C23_goodclinicalpractice.html': require('./assets/C23_goodclinicalpractice.html'),
  'C24_Infectionprevention.html': require('./assets/C24_Infectionprevention.html'),
  'C2_RoutineCare.html': require('./assets/C2_RoutineCare.html'),
  'C3_4_Resuscitation.html': require('./assets/C3_4_Resuscitation.html'),
  'C3_ResuscNewborn.html': require('./assets/C3_ResuscNewborn.html'),
  'C4_ResuscInfant.html': require('./assets/C4_ResuscInfant.html'),
  'C567_Breathing.html': require('./assets/C567_Breathing.html'),
  'C5_Breathing difficulties.html': require('./assets/C5_Breathing difficulties.html'),
  'C6_oxygen.html': require('./assets/C6_oxygen.html'),
  'C7_CPAP.html': require('./assets/C7_CPAP.html'),
  'C8_Asphyxia.html': require('./assets/C8_Asphyxia.html'),
  'C910_Preterm.html': require('./assets/C910_Preterm.html'),
  'C9_Preterm.html': require('./assets/C9_Preterm.html'),
  'contributors.html': require('./assets/contributors.html'),
  'demo.html': require('./assets/demo.html'),
  'disccoin.html': require('./assets/disccoin.html'),
  'E10_Syringepump.html': require('./assets/E10_Syringepump.html'),
  'E11_phototherapy.html': require('./assets/E11_phototherapy.html'),
  'E1_Suctionpump.html': require('./assets/E1_Suctionpump.html'),
  'E2_Pulse oximeter.html': require('./assets/E2_Pulse oximeter.html'),
  'E3_Oxygen concentrator.html': require('./assets/E3_Oxygen concentrator.html'),
  'E4_Oxygen cylinder.html': require('./assets/E4_Oxygen cylinder.html'),
  'E5_Oxygen splitter.html': require('./assets/E5_Oxygen splitter.html'),
  'E6_Oxygen therapy.html': require('./assets/E6_Oxygen therapy.html'),
  'E8_Radiant Warmer.html': require('./assets/E8_Radiant Warmer.html'),
  'E9_Glucometer.html': require('./assets/E9_Glucometer.html'),
  'Equipment.html': require('./assets/Equipment.html'),
  'foreword.html': require('./assets/foreword.html'),
  'Formulary.html': require('./assets/Formulary.html'),
  'introduction.html': require('./assets/introduction.html'),
  'The Background.html': require('./assets/The Background.html'),
  'title.html': require('./assets/title.html'),
  'triage.html': require('./assets/triage.html'),
  'Wall Charts.html': require('./assets/Wall Charts.html'),
  'WA_phototherapy.html': require('./assets/WA_phototherapy.html'),
  'WB_convulsions.html': require('./assets/WB_convulsions.html'),
  'WC_Maintenance Oral and IV fluids.html': require('./assets/WC_Maintenance Oral and IV fluids.html'),
  'WD_Antibiotic and Aminophylline doses.html': require('./assets/WD_Antibiotic and Aminophylline doses.html'),
  'WE_HIEgrading.html': require('./assets/WE_HIEgrading.html'),
  'WF_Hypoglycaemia.html': require('./assets/WF_Hypoglycaemia.html'),
  'WG_10perc_glc.html': require('./assets/WG_10perc_glc.html'),
  'WH_neoresusc.html': require('./assets/WH_neoresusc.html'),
  'WI_YIresusc.html': require('./assets/WI_YIresusc.html'),
  'WJ_TRY.html': require('./assets/WJ_TRY.html'),
  'WK_shock.html': require('./assets/WK_shock.html'),
};



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