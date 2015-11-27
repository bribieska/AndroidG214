package mx.bribiesca.com.sedatu.util;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentManager;

/**
 * Created by Bribiesca on 23/11/15.
 */
public class AppPageAdapter extends FragmentPagerAdapter{
    public AppPageAdapter(FragmentManager fm){
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        Fragment f = new AppFragment();
        Bundle args = new Bundle();
        args.putInt("current_page", position);
        f.setArguments(args);
        return f;
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position){
            case 0: return "Rammstein";
            case 1: return "Miss May I";
            case 2: return "Like Moths";
            default: return "";
        }
    }
}
