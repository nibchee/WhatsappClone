package com.example.nirbhay.whatsappclone;

import android.app.Application;

import com.parse.Parse;

public class App extends Application
{
    public void onCreate()
    {
        super.onCreate();
        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("NKFdj7SOCiciJPJlGjlRwDxNS5farHMzngLR0p7q")
                // if defined
                .clientKey("O1QGoYsShKRmIKNdF7VJeDtF03BrvwqMRUKi4nyV")
                .server("https://parseapi.back4app.com/")
                .build()
        );
    }
}
