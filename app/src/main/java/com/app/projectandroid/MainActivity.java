package com.app.projectandroid;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.SearchView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;
import com.app.projectandroid.adapter.TabAdapter;
import com.app.projectandroid.data.CompanyData;
import com.app.projectandroid.data.TabData;
import com.app.projectandroid.fragment.DateFragment;
import com.app.projectandroid.fragment.SettingsActivity;
import com.app.projectandroid.recycler.CompanyRecyclerAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private TabLayout tab;
    private ViewPager pager;
    private FloatingActionButton fab;
    SharedPreferences sp;
    SharedPreferences.Editor speditor;
    static String query1;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            default:
                break;
            case R.id.logout:
                speditor.putString("token", null);
                speditor.apply();
                startActivity(new Intent(MainActivity.this, LoginActivity.class));
                finish();
                return true;

        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        setSupportActionBar(toolbar);

        sp = getSharedPreferences("shared", MODE_PRIVATE);
        speditor = sp.edit();

        ArrayList<TabData> data = new ArrayList<>();
        data.add(new TabData("All Company", new CompanyFragment()));
        data.add(new TabData("Reservations", new DateFragment()));
        data.add(new TabData("Setting", new SettingsActivity.SettingsFragment()));

        final TabAdapter adapter = new TabAdapter(getSupportFragmentManager(), data);
        pager.setAdapter(adapter);
        tab.setupWithViewPager(pager);


        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), AddReservationActivity.class);
                startActivity(intent);
            }
        });
    }

    private void initView() {
        toolbar = findViewById(R.id.toolbar);
        //search = findViewById(R.id.search);
        tab = findViewById(R.id.tab);
        pager = findViewById(R.id.pager);
        fab = findViewById(R.id.fab);
    }

    public static class CompanyFragment extends Fragment {
        private RecyclerView rv;


        private ArrayList<CompanyData> data;
        private CompanyRecyclerAdapter adapter;
        private FirebaseDatabase database;
        private DatabaseReference myRef;
        String s;

        public static CompanyFragment getInstance(String query) {
            CompanyFragment c = new CompanyFragment();
            Bundle bundle = new Bundle();
            bundle.putString("q", query);
            c.setArguments(bundle);
            return c;
        }

        @Override
        public void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setHasOptionsMenu(true);
            if (getArguments() != null) {
                s = getArguments().getString("q");
            }
        }

        public CompanyFragment() {
            // Required empty public constructor
        }


        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View v = inflater.inflate(R.layout.fragment_company, container, false);
            rv = v.findViewById(R.id.company_rv);

            data = new ArrayList<>();


            database = FirebaseDatabase.getInstance();
            myRef = database.getReference("MyData").child("company");
            adapter = new CompanyRecyclerAdapter(data);

            myRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    data.clear();
                    adapter.notifyDataSetChanged();
                    for (DataSnapshot d : dataSnapshot.getChildren()) {
                        CompanyData allData = d.getValue(CompanyData.class);
                        data.add(allData);
                        rv.setAdapter(adapter);
                        rv.setLayoutManager(new LinearLayoutManager(getActivity()));
                        rv.setHasFixedSize(true);
                        adapter.notifyDataSetChanged();
                    }
                }

                @Override
                public void onCancelled(DatabaseError error) {
                    Log.e("Error", error.getMessage());
                    Toast.makeText(getActivity(), "Failed to read value.", Toast.LENGTH_SHORT).show();
                }
            });


            //  adapter.filter(query);
            return v;
        }

        @Override
        public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
            super.onCreateOptionsMenu(menu, inflater);
            /*getActivity().getMenuInflater().inflate(R.menu.example_menu, menu);
            MenuItem menuItem = menu.findItem(R.id.app_bar_search);
            SearchView searchView = (SearchView) menuItem.getActionView();*/
            MenuInflater aa = getActivity().getMenuInflater();
            aa.inflate(R.menu.example_menu, menu);

            MenuItem searchItem = menu.findItem(R.id.app_bar_search);
            SearchView searchView = (SearchView) searchItem.getActionView();

            searchView.setImeOptions(EditorInfo.IME_ACTION_DONE);
            searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

                @Override
                public boolean onQueryTextSubmit(String query) {
                    return false;
                }

                @Override
                public boolean onQueryTextChange(String query) {
                    adapter.getFilter().filter(query);
                    return false;
                }

            });
        }
    }
}
