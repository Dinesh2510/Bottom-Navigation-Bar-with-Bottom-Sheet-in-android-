package com.demo.demo;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Handler;
import android.support.design.bottomappbar.BottomAppBar;
import android.support.design.widget.BottomSheetDialogFragment;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import static com.demo.demo.R.drawable.ic_add_white_24dp;


public class MainActivity extends AppCompatActivity {
        FloatingActionButton floatingActionButton;
        BottomAppBar bottomAppBar;
    boolean doubleBackToExitPressedOnce = false;

    final Context context = this;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
            floatingActionButton = findViewById(R.id.fab_bottom_appbar);
            bottomAppBar = findViewById(R.id.bottom_App_bar);
            bottomAppBar.replaceMenu(R.menu.popup_menu_main);
            bottomAppBar.setNavigationOnClickListener(

                    new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            BottomSheetDialogFragment bottomSheetDialogFragment = BottomSheetNavigationFragment.newInstance();
                            bottomSheetDialogFragment.show(getSupportFragmentManager(), "Bottom Sheet Dialog Fragment");
                            Toast.makeText(context, "Swipe Up For More!", Toast.LENGTH_SHORT).show();
                        }
                    });


            floatingActionButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Snackbar.make(view, "Fab is clicked", Snackbar.LENGTH_LONG)
                            .setAction("UNDO", null)
                            .show();
                }
            });
        }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }

        this.doubleBackToExitPressedOnce = true;
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        View dialogLayout = inflater.inflate(R.layout.exit, null);
        builder.setPositiveButton("Exit", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {finish();}});
        builder.setNegativeButton("Cancel",null);
        builder.setNeutralButton("Rate Us!",new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {Intent intent = new Intent(android.content.Intent.ACTION_VIEW);

                intent.setData(Uri.parse("https://play.google.com/store/apps/details?id=com.google.android.youtube"));

                startActivity(intent);;}});
        builder.setView(dialogLayout);
        builder.show();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce=false;
            }
        }, 2000);
    }
    //and this to handle actions
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.swich) {
            if (bottomAppBar.getFabAlignmentMode() == BottomAppBar.FAB_ALIGNMENT_MODE_END) {
                bottomAppBar.setFabAlignmentMode(BottomAppBar.FAB_ALIGNMENT_MODE_CENTER);
            } else {
                bottomAppBar.setFabAlignmentMode(BottomAppBar.FAB_ALIGNMENT_MODE_END);
            }            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    }