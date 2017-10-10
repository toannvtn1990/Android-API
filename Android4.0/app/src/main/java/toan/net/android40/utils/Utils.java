package toan.net.android40.utils;


import android.os.Build;
import android.os.StrictMode;

import toan.net.android40.MainActivity;
import toan.net.android40.contactprovider.ContactListActivity;

public class Utils {

    // Prevents instantiation
    private Utils() {
    }

    public static void enableStrictMode() {
        if (hasGingerbread()) {
            // enable all thread strict mode policies
            StrictMode.ThreadPolicy.Builder threadPolicyBuilder =
                    new StrictMode.ThreadPolicy.Builder()
                            .detectAll().penaltyLog();

            // enable all VM strict mode policies
            StrictMode.VmPolicy.Builder vmPolicyBuilder = new StrictMode.VmPolicy.Builder()
                    .detectAll()
                    .penaltyLog();

            // Honeycomb introduced some additional strict mode futures
            if (hasHoneycomb()) {
                // Flash screen when thread policies is violated
                threadPolicyBuilder.penaltyFlashScreen();
                // For each activity class, set an instance limit of 1. Any more instances and
                // there could be a memory leak.
                vmPolicyBuilder
                        .setClassInstanceLimit(ContactListActivity.class, 1)
                        .setClassInstanceLimit(MainActivity.class, 1);

            }

            // Use builders to enable strict mode policies
            StrictMode.setThreadPolicy(threadPolicyBuilder.build());
            StrictMode.setVmPolicy(vmPolicyBuilder.build());
        }
    }

    /**
     * Uses static final constants to detect if the device's platform is Gingerbread or later.
     */
    public static boolean hasGingerbread() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.GINGERBREAD;
    }

    /**
     * Uses static final constant to detect if the device's platform is honeycomb or later
     *
     * @return
     */
    public static boolean hasHoneycomb() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB;
    }

    /**
     * Uses the final static constant to detect if the device's platform is honeycombMR1 or later.
     *
     * @return
     */
    public static boolean hasHoneycombMR1() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR1;
    }

    /**
     * Uses the final static constant to detect if the device's platform is ice cream sandwich or later.
     *
     * @return
     */
    public static boolean hasIceCreamSandwich() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH;
    }

}
