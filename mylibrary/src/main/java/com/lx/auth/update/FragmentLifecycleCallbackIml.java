package com.lx.auth.update;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

public class FragmentLifecycleCallbackIml extends FragmentManager.FragmentLifecycleCallbacks {
    @Override
    public void onFragmentStarted(@NonNull FragmentManager fm, @NonNull Fragment f) {
        super.onFragmentStarted(fm, f);

    }
}
