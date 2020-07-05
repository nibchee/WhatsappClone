package com.example.nirbhay.whatsappclone;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.parse.FindCallback;
import com.parse.LogOutCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.List;

public class WhatsappUsersActivity extends AppCompatActivity implements View.OnClickListener
{
    private ListView listview;
    private ArrayList<String> waUsers;
    private ArrayAdapter arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_whatsapp_users);

        listview = findViewById(R.id.listView);
        waUsers = new ArrayList<>();
        arrayAdapter = new ArrayAdapter(WhatsappUsersActivity.this, android.R.layout.simple_list_item_1, waUsers);


        try {
            ParseQuery<ParseUser> parseQuery = ParseUser.getQuery();
            parseQuery.whereNotEqualTo("username", ParseUser.getCurrentUser().getUsername());

            parseQuery.findInBackground(new FindCallback<ParseUser>() {
                @Override
                public void done(List<ParseUser> users, ParseException e) {
                    if (e == null) {
                        if (users.size() > 0) {
                            for (ParseUser user : users) {
                                waUsers.add(user.getUsername());
                            }
                            listview.setAdapter(arrayAdapter);
                        }


                    }
                }

            });
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }




    public boolean onCreateOptionsMenu(Menu menu)
    {

        getMenuInflater().inflate(R.menu.my_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(@NonNull MenuItem item)
    {
        switch(item.getItemId()) {
            case R.id.logoutUserItem:
                ParseUser.getCurrentUser().logOutInBackground(new LogOutCallback() {
                    @Override
                    public void done(ParseException e) {
                        Intent intent = new Intent(WhatsappUsersActivity.this, SignUp.class);
                        startActivity(intent);
                        finish();

                    }
                });
                break;

        }
        return  super.onOptionsItemSelected(item);

    }

    @Override
    public void onClick(View v) {

    }
}