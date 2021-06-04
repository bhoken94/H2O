package it.alessandra.h2o.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import it.alessandra.h2o.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link OnBoardingFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class OnBoardingFragment extends Fragment {

    private static final String BACKGROUND_COLOR = "background_color";
    private static final String PAGE = "page";

    private int backgroundColor;
    private int page;

    public static OnBoardingFragment newInstance(int page) {
        OnBoardingFragment fragment = new OnBoardingFragment();
        Bundle args = new Bundle();
        args.putInt(PAGE, page);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        backgroundColor = getArguments().getInt(BACKGROUND_COLOR);
        page = getArguments().getInt(PAGE);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Select a layout according to the current page
        int layoutResId;
        switch (page) {
            case 0:
                layoutResId = R.layout.fragment_on_boarding_01;
                break;
            case 1:
                layoutResId = R.layout.fragment_on_boarding_02;
                break;
            case 2:
                layoutResId = R.layout.fragment_on_boarding_03;
                break;
            default:
                layoutResId = R.layout.fragment_on_boarding_01;
                break;
        }

        // Inflate layout resource
        View view = getActivity().getLayoutInflater().inflate(layoutResId, container, false);

        // Set the current page index as the View's tag (used for PageTransformer)
        view.setTag(page);

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Set the background color of the root view to the color specified in newInstance()
        View background = view.findViewById(R.id.onboarding_fragment_bg);
        background.setBackgroundColor(backgroundColor);
    }
}