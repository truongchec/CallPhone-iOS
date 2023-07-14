package com.appsgenz.callphoneios.utils;

import android.content.ContentUris;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.util.Log;
import android.webkit.MimeTypeMap;

import com.google.firebase.analytics.FirebaseAnalytics;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/* loaded from: classes.dex */
public class FileUltils {
    public static final String AUTHORITY = "YOUR_AUTHORITY.provider";
    private static final boolean DEBUG = false;
    public static final String DOCUMENTS_DIR = "documents";
    public static final String HIDDEN_PREFIX = ".";
    static final String TAG = "FileUtils";

    private static void logDir(File file) {
    }

    private FileUltils() {
    }

    public static String getExtension(String str) {
        if (str == null) {
            return null;
        }
        int lastIndexOf = str.lastIndexOf(HIDDEN_PREFIX);
        return lastIndexOf >= 0 ? str.substring(lastIndexOf) : "";
    }

    public static boolean isLocal(String str) {
        return (str == null || str.startsWith("http://") || str.startsWith("https://")) ? false : true;
    }

    public static boolean isMediaUri(Uri uri) {
        return "media".equalsIgnoreCase(uri.getAuthority());
    }

    public static Uri getUri(File file) {
        if (file != null) {
            return Uri.fromFile(file);
        }
        return null;
    }

    public static String getMimeType(File file) {
        String extension = getExtension(file.getName());
        return extension.length() > 0 ? MimeTypeMap.getSingleton().getMimeTypeFromExtension(extension.substring(1)) : "application/octet-stream";
    }

    public static String getMimeType(Context context, Uri uri) {
        return getMimeType(new File(getPath(context, uri)));
    }

    public static String getMimeType(Context context, String str) {
        String type = context.getContentResolver().getType(Uri.parse(str));
        return type == null ? "application/octet-stream" : type;
    }

    public static boolean isLocalStorageDocument(Uri uri) {
        return AUTHORITY.equals(uri.getAuthority());
    }

    public static boolean isExternalStorageDocument(Uri uri) {
        return "com.android.externalstorage.documents".equals(uri.getAuthority());
    }

    public static boolean isDownloadsDocument(Uri uri) {
        return "com.android.providers.downloads.documents".equals(uri.getAuthority());
    }

    public static boolean isMediaDocument(Uri uri) {
        return "com.android.providers.media.documents".equals(uri.getAuthority());
    }

    public static boolean isGooglePhotosUri(Uri uri) {
        return "com.google.android.apps.photos.content".equals(uri.getAuthority());
    }

    public static boolean isGoogleDriveUri(Uri uri) {
        return "com.google.android.apps.docs.storage.legacy".equals(uri.getAuthority()) || "com.google.android.apps.docs.storage".equals(uri.getAuthority());
    }

    /* JADX WARN: Code restructure failed: missing block: B:13:0x002b, code lost:
        if (r8 != null) goto L8;
     */
    /* JADX WARN: Code restructure failed: missing block: B:20:0x0035, code lost:
        if (r8 == null) goto L6;
     */
    /* JADX WARN: Code restructure failed: missing block: B:21:0x0037, code lost:
        r8.close();
     */
    /* JADX WARN: Code restructure failed: missing block: B:22:0x003a, code lost:
        return null;
     */
    /* JADX WARN: Removed duplicated region for block: B:26:0x003f  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static String getDataColumn(Context r8, Uri r9, String r10, String[] r11) {
        /*
            java.lang.String r0 = "_data"
            java.lang.String[] r3 = new java.lang.String[]{r0}
            r7 = 0
            android.content.ContentResolver r1 = r8.getContentResolver()     // Catch: java.lang.Throwable -> L2e java.lang.Exception -> L30
            r6 = 0
            r2 = r9
            r4 = r10
            r5 = r11
            android.database.Cursor r8 = r1.query(r2, r3, r4, r5, r6)     // Catch: java.lang.Throwable -> L2e java.lang.Exception -> L30
            if (r8 == 0) goto L2b
            boolean r9 = r8.moveToFirst()     // Catch: java.lang.Exception -> L29 java.lang.Throwable -> L3b
            if (r9 == 0) goto L2b
            int r9 = r8.getColumnIndexOrThrow(r0)     // Catch: java.lang.Exception -> L29 java.lang.Throwable -> L3b
            java.lang.String r9 = r8.getString(r9)     // Catch: java.lang.Exception -> L29 java.lang.Throwable -> L3b
            if (r8 == 0) goto L28
            r8.close()
        L28:
            return r9
        L29:
            r9 = move-exception
            goto L32
        L2b:
            if (r8 == 0) goto L3a
            goto L37
        L2e:
            r9 = move-exception
            goto L3d
        L30:
            r9 = move-exception
            r8 = r7
        L32:
            r9.printStackTrace()     // Catch: java.lang.Throwable -> L3b
            if (r8 == 0) goto L3a
        L37:
            r8.close()
        L3a:
            return r7
        L3b:
            r9 = move-exception
            r7 = r8
        L3d:
            if (r7 == 0) goto L42
            r7.close()
        L42:
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.appsgenz.callphoneios.utils.FileUltils.getDataColumn(android.content.Context, android.net.Uri, java.lang.String, java.lang.String[]):java.lang.String");
    }

    public static String getPath(Context context, Uri uri) {
        String localPath = getLocalPath(context, uri);
        return localPath != null ? localPath : uri.toString();
    }

    private static String getLocalPath(Context context, Uri uri) {
        String dataColumn = null;
        Uri uri2 = null;
        if ((Build.VERSION.SDK_INT >= 19) && DocumentsContract.isDocumentUri(context, uri)) {
            if (isLocalStorageDocument(uri)) {
                return DocumentsContract.getDocumentId(uri);
            }
            if (isExternalStorageDocument(uri)) {
                String[] split = DocumentsContract.getDocumentId(uri).split(":");
                String str = split[0];
                if ("primary".equalsIgnoreCase(str)) {
                    return Environment.getExternalStorageDirectory() + "/" + split[1];
                } else if ("home".equalsIgnoreCase(str)) {
                    return Environment.getExternalStorageDirectory() + "/documents/" + split[1];
                }
            } else if (isDownloadsDocument(uri)) {
                String documentId = DocumentsContract.getDocumentId(uri);
                if (documentId != null && documentId.startsWith("raw:")) {
                    return documentId.substring(4);
                }
                String[] strArr = {"content://downloads/public_downloads", "content://downloads/my_downloads"};
                for (int i = 0; i < 2; i++) {
                    try {
                        dataColumn = getDataColumn(context, ContentUris.withAppendedId(Uri.parse(strArr[i]), Long.valueOf(documentId).longValue()), null, null);
                    } catch (Exception unused) {
                    }
                    if (dataColumn != null) {
                        return dataColumn;
                    }
                }
                File generateFileName = generateFileName(getFileName(context, uri), getDocumentCacheDir(context));
                if (generateFileName != null) {
                    String absolutePath = generateFileName.getAbsolutePath();
                    saveFileFromUri(context, uri, absolutePath);
                    return absolutePath;
                }
                return null;
            } else if (isMediaDocument(uri)) {
                String[] split2 = DocumentsContract.getDocumentId(uri).split(":");
                String str2 = split2[0];
                if ("image".equals(str2)) {
                    uri2 = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
                } else if ("video".equals(str2)) {
                    uri2 = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
                } else if ("audio".equals(str2)) {
                    uri2 = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
                }
                return getDataColumn(context, uri2, "_id=?", new String[]{split2[1]});
            } else if (isGoogleDriveUri(uri)) {
                return getGoogleDriveFilePath(uri, context);
            }
        } else if (FirebaseAnalytics.Param.CONTENT.equalsIgnoreCase(uri.getScheme())) {
            if (isGooglePhotosUri(uri)) {
                return uri.getLastPathSegment();
            }
            if (isGoogleDriveUri(uri)) {
                return getGoogleDriveFilePath(uri, context);
            }
            return getDataColumn(context, uri, null, null);
        } else if ("file".equalsIgnoreCase(uri.getScheme())) {
            return uri.getPath();
        }
        return null;
    }

    public static File getFile(Context context, Uri uri) {
        String path;
        if (uri == null || (path = getPath(context, uri)) == null || !isLocal(path)) {
            return null;
        }
        return new File(path);
    }

    public static File getDownloadsDir() {
        return Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
    }

    public static File getDocumentCacheDir(Context context) {
        File file = new File(context.getCacheDir(), DOCUMENTS_DIR);
        if (!file.exists()) {
            file.mkdirs();
        }
        logDir(context.getCacheDir());
        logDir(file);
        return file;
    }

    public static File generateFileName(String str, File file) {
        String str2;
        if (str == null) {
            return null;
        }
        File file2 = new File(file, str);
        if (file2.exists()) {
            int lastIndexOf = str.lastIndexOf(46);
            int i = 0;
            if (lastIndexOf > 0) {
                String substring = str.substring(0, lastIndexOf);
                str2 = str.substring(lastIndexOf);
                str = substring;
            } else {
                str2 = "";
            }
            while (file2.exists()) {
                i++;
                file2 = new File(file, str + '(' + i + ')' + str2);
            }
        }
        try {
            if (file2.createNewFile()) {
                logDir(file);
                return file2;
            }
            return null;
        } catch (IOException e) {
            Log.w(TAG, e);
            return null;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:44:0x005f A[Catch: IOException -> 0x005b, TRY_LEAVE, TryCatch #7 {IOException -> 0x005b, blocks: (B:40:0x0057, B:44:0x005f), top: B:51:0x0057 }] */
    /* JADX WARN: Removed duplicated region for block: B:51:0x0057 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static void saveFileFromUri(Context r3, Uri r4, String r5) {
        /*
            r0 = 0
            android.content.ContentResolver r3 = r3.getContentResolver()     // Catch: java.lang.Throwable -> L3a java.io.IOException -> L3d
            java.io.InputStream r3 = r3.openInputStream(r4)     // Catch: java.lang.Throwable -> L3a java.io.IOException -> L3d
            java.io.BufferedOutputStream r4 = new java.io.BufferedOutputStream     // Catch: java.lang.Throwable -> L32 java.io.IOException -> L36
            java.io.FileOutputStream r1 = new java.io.FileOutputStream     // Catch: java.lang.Throwable -> L32 java.io.IOException -> L36
            r2 = 0
            r1.<init>(r5, r2)     // Catch: java.lang.Throwable -> L32 java.io.IOException -> L36
            r4.<init>(r1)     // Catch: java.lang.Throwable -> L32 java.io.IOException -> L36
            r5 = 1024(0x400, float:1.435E-42)
            byte[] r5 = new byte[r5]     // Catch: java.lang.Throwable -> L2e java.io.IOException -> L30
            r3.read(r5)     // Catch: java.lang.Throwable -> L2e java.io.IOException -> L30
        L1b:
            r4.write(r5)     // Catch: java.lang.Throwable -> L2e java.io.IOException -> L30
            int r0 = r3.read(r5)     // Catch: java.lang.Throwable -> L2e java.io.IOException -> L30
            r1 = -1
            if (r0 != r1) goto L1b
            if (r3 == 0) goto L2a
            r3.close()     // Catch: java.io.IOException -> L48
        L2a:
            r4.close()     // Catch: java.io.IOException -> L48
            goto L53
        L2e:
            r5 = move-exception
            goto L34
        L30:
            r5 = move-exception
            goto L38
        L32:
            r5 = move-exception
            r4 = r0
        L34:
            r0 = r3
            goto L55
        L36:
            r5 = move-exception
            r4 = r0
        L38:
            r0 = r3
            goto L3f
        L3a:
            r5 = move-exception
            r4 = r0
            goto L55
        L3d:
            r5 = move-exception
            r4 = r0
        L3f:
            r5.printStackTrace()     // Catch: java.lang.Throwable -> L54
            if (r0 == 0) goto L4a
            r0.close()     // Catch: java.io.IOException -> L48
            goto L4a
        L48:
            r3 = move-exception
            goto L50
        L4a:
            if (r4 == 0) goto L53
            r4.close()     // Catch: java.io.IOException -> L48
            goto L53
        L50:
            r3.printStackTrace()
        L53:
            return
        L54:
            r5 = move-exception
        L55:
            if (r0 == 0) goto L5d
            r0.close()     // Catch: java.io.IOException -> L5b
            goto L5d
        L5b:
            r3 = move-exception
            goto L63
        L5d:
            if (r4 == 0) goto L66
            r4.close()     // Catch: java.io.IOException -> L5b
            goto L66
        L63:
            r3.printStackTrace()
        L66:
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.appsgenz.callphoneios.utils.FileUltils.saveFileFromUri(android.content.Context, android.net.Uri, java.lang.String):void");
    }


    /*public static byte[] readBytesFromFile(String str) {
        FileInputStream fileInputStream = null;
        IOException e;
        FileInputStream fileInputStream2 = null;
        try {
            try {
                try {
                    try {
                        File file = new File(str);
                        str = new byte[(int) file.length()];
                        try {
                            fileInputStream = new FileInputStream(file);
                            try {
                                fileInputStream.read(str);
                                fileInputStream.close();
                                str = str;
                            } catch (IOException e2) {
                                e = e2;
                                e.printStackTrace();
                                if (fileInputStream != null) {
                                    fileInputStream.close();
                                    str = str;
                                }
                                return str.getBytes();
                            }
                        } catch (IOException e3) {
                            fileInputStream = null;
                            e = e3;
                        }
                    } catch (IOException e4) {
                        fileInputStream = null;
                        e = e4;
                        str = null;
                    }
                } catch (Throwable th) {
                    th = th;
                    if (fileInputStream2 != null) {
                        try {
                            fileInputStream2.close();
                        } catch (IOException e5) {
                            e5.printStackTrace();
                        }
                    }
                    throw th;
                }
            } catch (IOException e6) {
                e6.printStackTrace();
            }
            return str;
        } catch (Throwable th2) {
            fileInputStream2 = fileInputStream;
        }
    }*/

    public static File createTempImageFile(Context context, String str) throws IOException {
        return File.createTempFile(str, ".jpg", new File(context.getCacheDir(), DOCUMENTS_DIR));
    }

    public static String getFileName(Context context, Uri uri) {
        if (context.getContentResolver().getType(uri) == null && context != null) {
            String path = getPath(context, uri);
            if (path == null) {
                return getName(uri.toString());
            }
            return new File(path).getName();
        }
        Cursor query = context.getContentResolver().query(uri, null, null, null, null);
        if (query != null) {
            int columnIndex = query.getColumnIndex("_display_name");
            query.moveToFirst();
            String string = query.getString(columnIndex);
            query.close();
            return string;
        }
        return null;
    }

    public static String getName(String str) {
        if (str == null) {
            return null;
        }
        return str.substring(str.lastIndexOf(47) + 1);
    }

    private static String getGoogleDriveFilePath(Uri uri, Context context) {
        Cursor query = context.getContentResolver().query(uri, null, null, null, null);
        int columnIndex = query.getColumnIndex("_display_name");
        int columnIndex2 = query.getColumnIndex("_size");
        query.moveToFirst();
        String string = query.getString(columnIndex);
        Long.toString(query.getLong(columnIndex2));
        File file = new File(context.getCacheDir(), string);
        try {
            InputStream openInputStream = context.getContentResolver().openInputStream(uri);
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            byte[] bArr = new byte[Math.min(openInputStream.available(), 1048576)];
            while (true) {
                int read = openInputStream.read(bArr);
                if (read == -1) {
                    break;
                }
                fileOutputStream.write(bArr, 0, read);
            }
            openInputStream.close();
            fileOutputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return file.getPath();
    }
}
