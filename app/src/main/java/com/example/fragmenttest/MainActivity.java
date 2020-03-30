package com.example.fragmenttest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.fragmenttest.ui.main.FragmentThree;
import com.example.fragmenttest.ui.main.FragmentTwo;
import com.example.fragmenttest.ui.main.MainFragment;

public class MainActivity extends AppCompatActivity {

    private FragmentTransaction fragmentTransaction;
    private FragmentManager fragmentManager;
    private Button button;
    private Button button2;
    private Button button3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        button = (Button) findViewById(R.id.buttonFrag);
        button2 = (Button) findViewById(R.id.buttonFrag2);
        button3 = (Button) findViewById(R.id.buttonFrag3);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragmentManager = getSupportFragmentManager();
                addFragment();
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragmentManager = getSupportFragmentManager();
                fragmentManager.popBackStack();
            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment fragment = fragmentManager.findFragmentById(R.id.fragmentContainer);
                if(fragment != null){
                    fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.remove(fragment);
                    fragmentTransaction.commit();
                }
            }
        });
/*        if (savedInstanceState == null) {
         getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, MainFragment.newInstance())
                    .commitNow();

        }*/
    }

    @Override
    public void onBackPressed() {
        Fragment fragment = fragmentManager.findFragmentById(R.id.fragmentContainer);
        if(fragment != null){
            fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.remove(fragment);
            fragmentTransaction.commit();
        }
        else{
            super.onBackPressed();
        }
    }

    public void addFragment(){

/*        Fragment fragment;
        switch (fragmentManager.getBackStackEntryCount()){
            case 0: fragment = MainFragment.newInstance();
            break;
            case 1: fragment = FragmentTwo.newInstance();
            break;
            case 2: fragment = FragmentThree.newInstance();
            break;
            default: fragment = MainFragment.newInstance();
        }*/

        Fragment fragment = fragmentManager.findFragmentById(R.id.fragmentContainer);

        if(fragment instanceof MainFragment){
            fragment = FragmentTwo.newInstance();
        }
        else if(fragment instanceof FragmentTwo){
            fragment = FragmentThree.newInstance();
        }
        else if(fragment instanceof FragmentThree){
            fragment = MainFragment.newInstance();
        }
        else{
            fragment = MainFragment.newInstance();
        }

        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragmentContainer, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();



/*        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.fragmentContainer, MainFragment.newInstance());
        fragmentTransaction.addToBackStack("fragmentStack1");
        fragmentTransaction.commit();*/
    }
}
