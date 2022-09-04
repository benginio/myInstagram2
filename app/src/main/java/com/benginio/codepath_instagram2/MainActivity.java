package com.benginio.codepath_instagram2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.benginio.codepath_instagram2.fragments.ComposeFragment;
import com.benginio.codepath_instagram2.fragments.PostFragment;
import com.benginio.codepath_instagram2.fragments.ProfilFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.parse.ParseUser;

public class MainActivity extends AppCompatActivity {
    public static final String TAG="MainActivity";
    private BottomNavigationView bottomNavigation;
    final FragmentManager fragmentManager = getSupportFragmentManager();


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.item_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==R.id.compose){
            //compose icon has been selected
            Toast.makeText(this, "Logout", Toast.LENGTH_SHORT).show();
            ParseUser.logOut();
            ParseUser currentUser = ParseUser.getCurrentUser(); // this will now be null
            //Navigate to the compose activity
            Intent intent=new Intent(this, LoginActivity.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar myToolbar=(Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setTitle("");
        getSupportActionBar().setLogo(R.drawable.logins);


        bottomNavigation=findViewById(R.id.bottomNavigation);
        bottomNavigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                Fragment fragment;
                switch (menuItem.getItemId()) {
                    case R.id.action_post:
                        fragment=new PostFragment();
                        break;
                    case R.id.action_home:
                    fragment=new ComposeFragment();
                        break;
                    case R.id.action_profile:
                    default:
                        fragment=new ProfilFragment();
                        break;
                }
                fragmentManager.beginTransaction().replace(R.id.flContainer, fragment).commit();
                return true;
            }
        });
        // Set default selection
       bottomNavigation.setSelectedItemId(R.id.action_post);
    }



}