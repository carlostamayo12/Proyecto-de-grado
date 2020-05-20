package com.example.jeromotosapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.jeromotosapp.Adapter.PageAdapter;
import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;

public class TabActivity extends AppCompatActivity {

  TabLayout tabLayout;
  ViewPager viewPager;
  PagerAdapter pagerAdapter;

  private String motoId;
  private String tipoMotoId;
  Bundle datos;

  TabItem tab1, tab2, tab3;

  @Override
  protected void onRestart() {
    super.onRestart();
    Intent intent = new Intent(TabActivity.this, MainActivity.class);
    startActivity(intent);
  }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_tab);

    tabLayout = findViewById(R.id.tablayout);
    viewPager = findViewById(R.id.viewpager);

    tab1 = findViewById(R.id.tab0);
    tab2 = findViewById(R.id.tab1);
    tab3 = findViewById(R.id.tab2);

    Bundle datos = this.getIntent().getExtras();
    String motoId = datos.getString("motoId");
    String tipoMotoId = datos.getString("tipoMotoId");

    //motoId = datos.getString("motoId");

    //tipoMotoId = datos.getString("tipoMotoId");

    motoId = "1";
    tipoMotoId = "1";
    final PageAdapter pageAdapter = new PageAdapter(getSupportFragmentManager(), tabLayout.getTabCount(), motoId, tipoMotoId);
    viewPager.setAdapter(pageAdapter);

    tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
      @Override
      public void onTabSelected(TabLayout.Tab tab) {
        viewPager.setCurrentItem(tab.getPosition());

        if(tab.getPosition() == 0){
          pageAdapter.notifyDataSetChanged();
        }
        if(tab.getPosition() == 1){
          pageAdapter.notifyDataSetChanged();
        }
        if(tab.getPosition() == 2){
          pageAdapter.notifyDataSetChanged();
        }
      }

      @Override
      public void onTabUnselected(TabLayout.Tab tab) {

      }

      @Override
      public void onTabReselected(TabLayout.Tab tab) {

      }
    });

    viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
  }
}
