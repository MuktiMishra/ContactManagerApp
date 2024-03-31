package com.example.contactmanagerapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.contactmanagerapp.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    //Data Source
    private ContactDatabase contactDatabase;
//    private ArrayList<Contacts> contacts = new ArrayList<>();

    private ArrayList<Contacts> contactsArrayList= new ArrayList<>();

    //Adapter
    private MyAdapter myAdapter;

    //Binding
    private ActivityMainBinding mainBinding;
    private MainActivityClickHandler handlers;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);


        handlers  = new MainActivityClickHandler(this);

        mainBinding.setClickHandler(handlers);


        //Recycler View
        RecyclerView recyclerView = mainBinding.recyclerView;
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);


        //Database
        contactDatabase =  ContactDatabase.getInstance(this);

        //ViewModel
        MyViewModel viewModel = new ViewModelProvider(this).get(MyViewModel.class);

        //Inserting a new contact for testing
        Contacts c1 = new Contacts("Jack", "Jack145@gmail.com");
        viewModel.addNewContact(c1);

        //Loading the Data from ROOM DB
        viewModel.getAllContacts().observe(this, new Observer<List<Contacts>>() {
            @Override
            public void onChanged(List<Contacts> contacts) {

                contactsArrayList.clear();
                for (Contacts c: contacts){
                    Log.v("TAGY", c.getName());
                    contactsArrayList.add(c);
                }

                myAdapter.notifyDataSetChanged();
            }
        });

        //Adapters
        myAdapter = new MyAdapter(contactsArrayList);

        //linking the RecyclerView with the Adapter
        recyclerView.setAdapter((myAdapter));
    }
}