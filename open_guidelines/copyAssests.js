import * as FileSystem from 'expo-file-system/legacy';
import { Asset } from 'expo-asset';
import assetManifest from './assetManifest.js';

const DEST_ROOT = FileSystem.documentDirectory + 'assets/';

export async function ensureAssetsCopied(onProgress) {
  const entries = Object.entries(assetManifest);
  let done = 0;

  for (const [relativePath, moduleId] of entries) {
    const destPath = DEST_ROOT + relativePath;
    const destDir = destPath.substring(0, destPath.lastIndexOf('/'));

    await FileSystem.makeDirectoryAsync(destDir, { intermediates: true }).catch(() => {});

    const info = await FileSystem.getInfoAsync(destPath);
    if (info.exists) {
      done++;
      onProgress?.(done, entries.length);
      continue;
    }

    const asset = Asset.fromModule(moduleId);
    await asset.downloadAsync();
    await FileSystem.copyAsync({ from: asset.localUri, to: destPath });

    done++;
    onProgress?.(done, entries.length);
  }

  return DEST_ROOT;
}