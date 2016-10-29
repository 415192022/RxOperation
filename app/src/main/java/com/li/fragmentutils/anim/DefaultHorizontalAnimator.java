package com.li.fragmentutils.anim;


import rxop.li.com.rxoperation.R;

public class DefaultHorizontalAnimator extends FragmentAnimator {

    public DefaultHorizontalAnimator() {
        enter = R.anim.h_fragment_enter;
        exit = R.anim.h_fragment_exit;
        popEnter = R.anim.h_fragment_pop_enter;
        popExit = R.anim.h_fragment_pop_exit;
    }
}
