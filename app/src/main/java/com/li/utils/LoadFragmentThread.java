package com.li.utils;

import com.li.fragmentutils.SupportFragment;

/**
 * Created by Mingwei Li on 2017/1/17 0017.
 */

public class LoadFragmentThread implements Runnable {
    private static LoadFragmentThread loadFragmentThread;
    private SupportFragment thisSupportFragment;
    private SupportFragment targetFragment;
    private FragmentStartMode fragmentStartMode;
    private int res;

    private LoadFragmentThread(SupportFragment thisSupportFragment, SupportFragment targetFragment, FragmentStartMode fragmentStartMode) {
        this.thisSupportFragment = thisSupportFragment;
        this.targetFragment = targetFragment;
        this.fragmentStartMode = fragmentStartMode;
    }

    private LoadFragmentThread(SupportFragment thisSupportFragment, SupportFragment targetFragment, int res, FragmentStartMode fragmentStartMode) {
        this.thisSupportFragment = thisSupportFragment;
        this.targetFragment = targetFragment;
        this.fragmentStartMode = fragmentStartMode;
        this.res = res;
    }

    public static LoadFragmentThread newInstance(SupportFragment thisSupportFragment, SupportFragment targetFragment, FragmentStartMode fragmentStartMode) {
        loadFragmentThread = new LoadFragmentThread(thisSupportFragment, targetFragment, fragmentStartMode);
        return loadFragmentThread;
    }

    public static LoadFragmentThread newInstance(SupportFragment thisSupportFragment, SupportFragment targetFragment, int res, FragmentStartMode fragmentStartMode) {
        loadFragmentThread = new LoadFragmentThread(thisSupportFragment, targetFragment, res, fragmentStartMode);
        return loadFragmentThread;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        switch (fragmentStartMode) {
            case START:
                thisSupportFragment.start(targetFragment);
                break;
            case SHOW_HIDE:
                thisSupportFragment.showHideFragment(targetFragment, thisSupportFragment);
                break;
            case REPLAPSE:
                thisSupportFragment.replaceFragment(targetFragment, true);
                break;
            case LOAD_ROOT:
                thisSupportFragment.loadRootFragment(res, targetFragment);
                break;
            case LOAD_MULTIPLE:
                break;
            case POP:
                thisSupportFragment.pop();
                break;
        }
    }

    public enum FragmentStartMode {
        START, SHOW_HIDE, REPLAPSE, LOAD_ROOT, LOAD_MULTIPLE, POP
    }
}
