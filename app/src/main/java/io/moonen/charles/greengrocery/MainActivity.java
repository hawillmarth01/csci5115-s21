package io.moonen.charles.greengrocery;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import io.moonen.charles.greengrocery.ReceiptContentManagement.CSVFile;
import io.moonen.charles.greengrocery.ReceiptContentManagement.DataAdapter;
import io.moonen.charles.greengrocery.ReceiptContentManagement.Product;
import io.moonen.charles.greengrocery.ReceiptContentManagement.Receipt;
import io.moonen.charles.greengrocery.ui.receipt_scorecard.ReceiptScorecardFragment;

public class MainActivity extends AppCompatActivity {
    Receipt receipt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
//        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
//                R.id.navigation_rscan, R.id.navigation_garden, R.id.navigation_pscan)
//                .build();
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController);
        NavigationUI.setupWithNavController(navView, navController);
        //get example receipt data from csv file
        InputStream inputStream = getResources().openRawResource(R.raw.fake_database);
        CSVFile csvFile = new CSVFile(inputStream);
        List rowList = csvFile.readFile();

        //create list of products for example receipt
        List receiptProducts = new ArrayList<>();
        DataAdapter adapter;
        int numRows = rowList.size();
        for (int i = 1; i < numRows; i++){  //skip first row
            adapter = new DataAdapter((String[]) rowList.get(i));
            Product currProduct = adapter.createProduct();
            receiptProducts.add(currProduct);
        }

        //create example receipt
        receipt = new Receipt(receiptProducts);

        //test
        //String testing = ((Product) receiptProducts.get(1)).getName();

//        //ADDED BUTTON FOR TESTING
//        Button test = findViewById(R.id.test);
//        test.setOnClickListener(new View.OnClickListener(){
//            @Override
//            public void onClick(View v){
//                //test
//                //Toast.makeText(getApplicationContext(), testing, Toast.LENGTH_LONG).show();
//
//                ReceiptScorecardFragment frag = new ReceiptScorecardFragment();
//                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
//                transaction.replace(R.id.fragment_container,frag);
//                transaction.commit();
//            }
//        });
    }
    //returns example receipt
    public Receipt getReceiptData(){
        return receipt;
    }
}
