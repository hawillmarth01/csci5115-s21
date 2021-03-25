package io.moonen.charles.greengrocery.ui.gardenessentials;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import io.moonen.charles.greengrocery.R;

public class EssentialsFragment extends Fragment implements View.OnClickListener, PurchaseDialog.PurchaseDialogListener {

    private EssentialsViewModel essentialsViewModel;
    private ArrayList<PlantRow> plants;
    private CustomAdapter adapter;
    private RecyclerView recyclerView;

    private int tempPoints = 100;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        Bundle mArgs = getArguments();
        plants = mArgs.getParcelableArrayList("plants");

        essentialsViewModel = new ViewModelProvider(this).get(EssentialsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_essentials, container, false);

        recyclerView = (RecyclerView) root.findViewById(R.id.essentials);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(root.getContext()));

        Fragment fragment = this;

        adapter = new CustomAdapter(root.getContext(), plants);
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(new CustomAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                String plantName = plants.get(position).getTitle();
                boolean purchaseable = tempPoints >= plants.get(position).getPrice();
                boolean water = plants.get(position).isPurchased();

                Bundle args = new Bundle();
                args.putString("name", plantName);
                args.putInt("position", position);
                args.putBoolean("purchaseable", purchaseable);
                args.putBoolean("water", water);

                PurchaseDialog dialog = new PurchaseDialog();

                dialog.setArguments(args);
                dialog.setTargetFragment(fragment,0);
                dialog.show(getFragmentManager(), "purchase dialog");
            }
        });

        TextView pointsView = root.findViewById(R.id.points);
        pointsView.setText(String.valueOf(tempPoints));

        return root;
    }


    @Override
    public void onClick(View view) {
    }

    @Override
    public void sendPurchase(boolean purchase, int position) {

        PlantRow plant = plants.get(position);

        System.out.println(plant.isPurchased());

        if (plant.isPurchased()) {
            plant.waterPlant();
        }
        else {
            plant.purchase();
        }
        tempPoints -= plant.getPrice();
        getFragmentManager().beginTransaction().detach(this).attach(this).commit();

    }




}
