package fr.iut.appmob.fortuna.init;

import android.app.Activity;

import androidx.annotation.NonNull;
import androidx.biometric.BiometricManager;
import androidx.biometric.BiometricPrompt;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;

public class Authentication {
    private Activity activity;
    private BiometricManager manager;
    private BiometricPrompt prompt;
    private BiometricPrompt.PromptInfo info;

    public Authentication(Activity activity) {
        this.activity = activity;
        this.manager = BiometricManager.from(activity);
        this.prompt = new BiometricPrompt((FragmentActivity) activity, ContextCompat.getMainExecutor(activity), new BiometricPrompt.AuthenticationCallback() {
            @Override
            public void onAuthenticationSucceeded(@NonNull BiometricPrompt.AuthenticationResult result) {
                super.onAuthenticationSucceeded(result);
            }
        });

        this.info = new BiometricPrompt.PromptInfo.Builder()
                .setTitle("Authentication")
                .setNegativeButtonText("Ignore")
                .build();
    }

    public void start() {
        if(!isSuccess()) return;
        this.prompt.authenticate(this.info);
    }

    private boolean isSuccess() {
        return manager.canAuthenticate(BiometricManager.Authenticators.BIOMETRIC_STRONG)
                == BiometricManager.BIOMETRIC_SUCCESS;
    }

}
