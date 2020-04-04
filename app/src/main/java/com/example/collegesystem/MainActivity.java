package com.example.collegesystem;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

public class MainActivity extends AppCompatActivity {
    Add add_frag;
    Delete delete_frag;
    Modify modify_frag;
    Search search_frag;
    ViewAll viewAll_frag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        add_frag = new Add();
        delete_frag = new Delete();
        modify_frag = new Modify();
        search_frag = new Search();
        viewAll_frag = new ViewAll();
        BottomNavigationView nav = findViewById(R.id.nav1);
        final FrameLayout content = findViewById(R.id.content_layout);


        nav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.add12:
                        change_Fragment(add_frag);
                        Animation developmentv = AnimationUtils.loadAnimation(MainActivity.this, R.anim.custom_animation);
                        content.startAnimation(developmentv);
                        return true;
                    case R.id.delete11:
                        //add development code
                        change_Fragment(delete_frag);
                        Animation developmentv1 = AnimationUtils.loadAnimation(MainActivity.this,R.anim.custom_animation);
                        content.startAnimation(developmentv1);
                        return true;
                    case R.id.update11:
                        //add development code
                        change_Fragment(modify_frag);
                        Animation developmentv34 = AnimationUtils.loadAnimation(MainActivity.this,R.anim.custom_animation);
                        content.startAnimation(developmentv34);
                        return true;
                    case R.id.view1:
                        //add development code
                        change_Fragment(search_frag);
                        Animation developmentv2 = AnimationUtils.loadAnimation(MainActivity.this,R.anim.custom_animation);
                        content.startAnimation(developmentv2);
                        return true;
                    case R.id.viewAll1:
                        //add development code
                        change_Fragment(viewAll_frag);
                        Animation developmentv4 = AnimationUtils.loadAnimation(MainActivity.this,R.anim.custom_animation);
                        content.startAnimation(developmentv4);
                        return true;


                    default:
                        return false;
                }
            }
        });
    }

    private void change_Fragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction3 = getSupportFragmentManager().beginTransaction();
        fragmentTransaction3.replace(R.id.content_layout, fragment);
        fragmentTransaction3.commit();
    }

}
