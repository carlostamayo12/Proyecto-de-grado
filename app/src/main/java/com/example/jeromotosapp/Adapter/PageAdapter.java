package com.example.jeromotosapp.Adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.jeromotosapp.Fragments.MotoFragment;
import com.example.jeromotosapp.Fragments.OrdenFragment;

import com.example.jeromotosapp.Fragments.TablaFragment;

public class PageAdapter extends FragmentPagerAdapter {

  int numeroTabs;
  String motoId;
  String tipoMotoId;

  public PageAdapter(@NonNull FragmentManager fm, int behavior, String motoId, String tipoMotoId) {
    super(fm, behavior);
    this.numeroTabs = behavior;
    this.motoId = motoId;
    this.tipoMotoId = motoId;
  }

  @NonNull
  @Override
  public Fragment getItem(int position){

    switch (position){
      case 0:
        return new MotoFragment(motoId, tipoMotoId);
      case 1:
        return new TablaFragment(motoId, tipoMotoId);
      case 2:
        return new OrdenFragment(motoId, tipoMotoId);
      default: return null;
    }


  }

  @Override
  public int getCount(){
    return numeroTabs;
  }
}
