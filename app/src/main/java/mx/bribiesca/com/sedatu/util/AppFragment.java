package mx.bribiesca.com.sedatu.util;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import mx.bribiesca.com.sedatu.R;

/**
 * Created by Bribiesca on 23/11/15.
 */
public class AppFragment extends Fragment {
    int currentPage;
    int[] imgs = {R.drawable.rammstein_logo, R.drawable.coca_logo, R.drawable.pepsi_logo};

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle data = getArguments();
        currentPage = data.getInt("current_page");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_content, container, false);
        ImageView image = (ImageView)v.findViewById(R.id.img);
        image.setImageResource(imgs[currentPage]);
        return v;
    }
}
