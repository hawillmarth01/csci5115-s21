package io.moonen.charles.greengrocery.ui.dashboard;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import io.moonen.charles.greengrocery.R;
import io.moonen.charles.greengrocery.ui.gardenessentials.EssentialsFragment;
import io.moonen.charles.greengrocery.ui.gardenessentials.PlantRow;

// Garden
public class DashboardFragment extends Fragment implements View.OnClickListener {

    private DashboardViewModel dashboardViewModel;
    private ConstraintLayout constraintLayout;
    private ArrayList<PlantRow> plants =  new ArrayList<>(Arrays.asList(
            new PlantRow("Evergreen Tree", 100, R.drawable.evergreen3, "Plant"),
            new PlantRow("Maple Tree", 50, R.drawable.maple3, "Plant"),
            new PlantRow("Berry Bush", 20, R.drawable.berrybush3, "Plant"),
            new PlantRow("Tulip", 10, R.drawable.tulip3, "Plant")
    ));

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        dashboardViewModel =
                new ViewModelProvider(this).get(DashboardViewModel.class);
        View root = inflater.inflate(R.layout.fragment_dashboard, container, false);

        Button growYourGarden = root.findViewById(R.id.growyourgarden);
        growYourGarden.setOnClickListener(this);

        constraintLayout = root.findViewById(R.id.dashboard);

        return root;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.growyourgarden:
                Bundle args = new Bundle();
                args.putParcelableArrayList("plants", plants);

                EssentialsFragment essentials = new EssentialsFragment();
                essentials.setArguments(args);

                FragmentTransaction fragmentTransaction = getActivity()
                        .getSupportFragmentManager()
                        .beginTransaction();
                fragmentTransaction.replace(R.id.dashboard, essentials);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
        }
    }
}