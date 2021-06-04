package it.alessandra.h2o.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import it.alessandra.h2o.fragments.OnBoardingFragment;

public class OnBoardingAdapter extends FragmentPagerAdapter {

    public OnBoardingAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return OnBoardingFragment.newInstance(position);
            case 1:
                return OnBoardingFragment.newInstance(position);
            case 2:
                return OnBoardingFragment.newInstance(position);
            default:
                return OnBoardingFragment.newInstance(position);
        }
    }

    @Override
    public int getCount() {
        return 3;
    }
}
