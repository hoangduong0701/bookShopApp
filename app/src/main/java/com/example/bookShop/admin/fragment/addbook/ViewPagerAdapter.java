package com.example.bookShop.admin.fragment.addbook;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class ViewPagerAdapter extends FragmentStateAdapter {
    public ViewPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                return new AddBooksFragment();
            case 1:
                return new KindBookFragment();
            default:
                return new AddBooksFragment();
        }
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
