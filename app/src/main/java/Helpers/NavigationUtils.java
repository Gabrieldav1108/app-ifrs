package Helpers;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.View;

import com.example.app_ifrs.InfoScreen;
import com.example.app_ifrs.MainActivity;

public class NavigationUtils {
    public static void openActivity(Context context, Class<?> destination) {
        Intent intent = new Intent(context, destination);
        context.startActivity(intent);

    }

    public static void openUrl(Context context, String url) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        context.startActivity(intent);
    }

}
