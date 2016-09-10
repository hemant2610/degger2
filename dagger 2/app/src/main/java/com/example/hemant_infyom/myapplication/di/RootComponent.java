package com.example.hemant_infyom.myapplication.di;

/**
 * Created by Linux on 8/22/2016.
 */


import com.example.hemant_infyom.myapplication.MainActivity;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {
        ServiceModule.class
})
public interface RootComponent {

    void inject(MainActivity mainActivity);
}
