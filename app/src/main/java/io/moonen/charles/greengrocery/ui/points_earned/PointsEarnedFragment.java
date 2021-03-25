package io.moonen.charles.greengrocery.ui.points_earned;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import io.moonen.charles.greengrocery.MainActivity;
import io.moonen.charles.greengrocery.R;
import io.moonen.charles.greengrocery.ReceiptContentManagement.Receipt;

//POINTS EARNED FRAGMENT
public class PointsEarnedFragment extends Fragment {

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_points_earned, container, false);

        return root;
    }

    @Override
    public void onViewCreated(View v, Bundle savedInstanceState){
        super.onViewCreated(v, savedInstanceState);
        //get receipt data
        MainActivity activity = (MainActivity) getActivity();
        Receipt receipt = activity.getReceiptData();
        String pts = receipt.getOverallPoints();

        //set points value
        TextView pointsEarned = v.findViewById(R.id.pointsEarned);
        pointsEarned.setText("You Earned " + pts + " points!");
    }
}
