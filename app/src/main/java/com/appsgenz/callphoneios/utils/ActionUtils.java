package com.appsgenz.callphoneios.utils;

import android.content.ActivityNotFoundException;
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.provider.ContactsContract;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.core.net.MailTo;
import androidx.fragment.app.Fragment;

import com.appsgenz.callphoneios.R;
import com.appsgenz.callphoneios.item.ItemContact;
import com.google.ads.mediation.facebook.FacebookMediationAdapter;

public class ActionUtils {
    public static void openOtherApps(Context context) {
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.setData(Uri.parse("https://play.google.com/store/apps/developer").buildUpon().appendQueryParameter(FacebookMediationAdapter.KEY_ID, MyConst.ID_DEV).build());
        try {
            context.startActivity(intent);
        } catch (ActivityNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void openFacebook(Context context) {
        try {
            if (context.getPackageManager().getApplicationInfo("com.facebook.katana", 0).enabled) {
                context.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("fb://page/111335474750038")));
                return;
            }
        } catch (Exception unused) {
        }
        openLink(context, MyConst.LINK_FACE);
    }

    public static void openInstagram(Context context) {
        Uri parse = Uri.parse("https://instagram.com/_u/remi_studio_app");
        try {
            Intent launchIntentForPackage = context.getPackageManager().getLaunchIntentForPackage("com.instagram.android");
            if (launchIntentForPackage != null) {
                launchIntentForPackage.setAction("android.intent.action.VIEW");
                launchIntentForPackage.setData(parse);
                context.startActivity(launchIntentForPackage);
                return;
            }
        } catch (Exception unused) {
        }
        openLink(context, "https://instagram.com/remi_studio_app");
    }

    public static void openLink(Context context, String str) {
        try {
            Intent intent = new Intent("android.intent.action.VIEW");
            intent.setData(Uri.parse(str.replace("HTTPS", "https")));
            context.startActivity(intent);
        } catch (ActivityNotFoundException unused) {
            Toast.makeText(context, (int) R.string.no_browser, Toast.LENGTH_SHORT).show();
        }
    }

    public static void feedback(Context context) {
        String[] strArr = {MyConst.EMAIL};
        Intent intent = new Intent("android.intent.action.SENDTO");
        intent.setData(Uri.parse(MailTo.MAILTO_SCHEME));
        intent.putExtra("android.intent.extra.EMAIL", strArr);
        intent.putExtra("android.intent.extra.SUBJECT", MyConst.TITLE_EMAIL);
        try {
            context.startActivity(intent);
        } catch (ActivityNotFoundException unused) {
            Toast.makeText(context, (int) R.string.no_email, Toast.LENGTH_SHORT).show();
        }
    }

    public static void shareApp(Context context) {
        shareText(context, "https://play.google.com/store/apps/details?id=" + context.getPackageName());
    }

    public static void shareText(Context context, String str) {
        Intent intent = new Intent("android.intent.action.SEND");
        intent.setType("text/plain");
        intent.putExtra("android.intent.extra.SUBJECT", context.getString(R.string.app_name));
        intent.putExtra("android.intent.extra.TEXT", str);
        context.startActivity(Intent.createChooser(intent, context.getString(R.string.app_name)));
    }

    public static void ratePkg(Context context, String str) {
        Intent intent = new Intent("android.intent.action.VIEW", Uri.parse("market://details?id=" + str));
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        try {
            context.startActivity(intent);
        } catch (ActivityNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void hideKeyboard(Context context, View view) {
        InputMethodManager inputMethodManager = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        if (inputMethodManager == null) {
            return;
        }
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 2);
    }

    public static void showKeyboard(Context context, View view) {
        InputMethodManager inputMethodManager = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        if (inputMethodManager == null) {
            return;
        }
        inputMethodManager.showSoftInput(view, 1);
    }

    public static void actionCall(Context context, String str) {
        try {
            Intent intent = new Intent("android.intent.action.VIEW");
            intent.setData(Uri.withAppendedPath(ContactsContract.Contacts.CONTENT_URI, String.valueOf(str)));
            context.startActivity(intent);
        } catch (ActivityNotFoundException unused) {
            Toast.makeText(context, (int) R.string.error, Toast.LENGTH_SHORT).show();
        }
    }

    public static void onNewContact(ActivityResultLauncher<Intent> activityResultLauncher, String str) {
        Intent intent = new Intent("android.intent.action.INSERT");
        intent.setType("vnd.android.cursor.dir/contact");
        intent.putExtra("phone", str);
        intent.putExtra("finishActivityOnSaveCompleted", true);
        activityResultLauncher.launch(intent);
    }

    public static void onNewContact(Fragment fragment, String str) {
        Intent intent = new Intent("android.intent.action.INSERT");
        intent.setType("vnd.android.cursor.dir/contact");
        intent.putExtra("phone", str);
        intent.putExtra("finishActivityOnSaveCompleted", true);
        fragment.startActivityForResult(intent, 1);
    }

    public static void onEditContact(Context context, ActivityResultLauncher<Intent> activityResultLauncher, ItemContact itemContact) {
        if (itemContact.getId() == null || itemContact.getId().isEmpty()) {
            if (itemContact.getArrPhone().size() > 0) {
                onNewContact(activityResultLauncher, itemContact.getArrPhone().get(0).getNumber());
                return;
            } else {
                Toast.makeText(context, (int) R.string.error, Toast.LENGTH_SHORT).show();
                return;
            }
        }
        Intent intent = new Intent("android.intent.action.EDIT");
        intent.setData(ContentUris.withAppendedId(ContactsContract.Contacts.CONTENT_URI, Long.parseLong(itemContact.getId())));
        intent.putExtra("finishActivityOnSaveCompleted", true);
        activityResultLauncher.launch(intent);
    }
}
