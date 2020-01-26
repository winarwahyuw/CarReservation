package winarwahyuw.winw.daftarbelanja;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;


import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


import winarwahyuw.winw.daftarbelanja.model.Data;

public class HomeActivity extends AppCompatActivity {

    private FloatingActionButton fab_btn;

    private DatabaseReference mDatabase;
    private FirebaseAuth mAuth;

    private RecyclerView recyclerView;


    //Global Variable
//    private int amount;
    private String revCode;
    private String name;
    private String address;
    private String rent_start;
    private String rent_end;
    private String car;
    private String cost;
    private String post_key;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        getSupportActionBar().setTitle("Reservation List");

        mAuth = FirebaseAuth.getInstance();
        FirebaseUser mUser = mAuth.getCurrentUser();
        String uId = mUser.getUid();

        mDatabase = FirebaseDatabase.getInstance().getReference().child("Daftar Belanja").child(uId);

        mDatabase.keepSynced(true);

        fab_btn = findViewById(R.id.fab);
        fab_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                customDialog();
            }
        });

        recyclerView = findViewById(R.id.recycler_home);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setStackFromEnd(true);
        layoutManager.setReverseLayout(true);
        recyclerView.setHasFixedSize(true);
        recyclerView.setVisibility(View.VISIBLE);
        recyclerView.setLayoutManager(layoutManager);

        FirebaseRecyclerAdapter<Data, MyViewHolder> adapter = new FirebaseRecyclerAdapter<Data, MyViewHolder>
                (
                        Data.class,
                        R.layout.item_data,
                        MyViewHolder.class,
                        mDatabase
                )
        {
            @Override
            protected void populateViewHolder(MyViewHolder viewHolder, final Data model, final int position) {
                viewHolder.setRevCode(model.getRevCode());
                viewHolder.setName(model.getName());
                viewHolder.setAddress(model.getAddress());
                viewHolder.setRentStart(model.getRent_start());
                viewHolder.setRentEnd(model.getRent_end());
                viewHolder.setCar(model.getCar());
                viewHolder.setCost(model.getCost());

                viewHolder.myview.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        post_key    = getRef(position).getKey();
                        revCode     =model.getRevCode();
                        name        =model.getName();
                        address     =model.getAddress();
                        rent_start  =model.getRent_start();
                        rent_end    =model.getRent_end();
                        car         =model.getCar();
                        cost        =model.getCost();
                        updateData();
                    }
                });
            }
        };
        recyclerView.setAdapter(adapter);
    }

    private void customDialog(){
        AlertDialog.Builder mydialog = new AlertDialog.Builder(HomeActivity.this);

        LayoutInflater inflater = LayoutInflater.from(HomeActivity.this);
        View myview = inflater.inflate(R.layout.input_data, null);
        final AlertDialog dialog = mydialog.create();
        dialog.setView(myview);
        dialog.show();

        Button btnCancel=myview.findViewById(R.id.cancel);
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        final EditText revCode = myview.findViewById(R.id.edt_revCode);
        final EditText name=myview.findViewById(R.id.edt_name);
        final EditText address=myview.findViewById(R.id.edt_address);
        final EditText rent_start=myview.findViewById(R.id.edt_rent_start);
        final EditText rent_end=myview.findViewById(R.id.edt_rent_end);
        final EditText car=myview.findViewById(R.id.edt_car);
        final EditText cost=myview.findViewById(R.id.edt_cost);

        Button btnSave = myview.findViewById(R.id.btn_save);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String mRevCode     =revCode.getText().toString().trim();
                String mName        =name.getText().toString().trim();
                String mAddress     =address.getText().toString().trim();
                String mRentStart   =rent_start.getText().toString().trim();
                String mRentEnd     =rent_end.getText().toString().trim();
                String mCar         =car.getText().toString().trim();
                String mCost        =cost.getText().toString().trim();

                if(TextUtils.isEmpty(mRevCode)){
                    revCode.setError("Required Field...");
                    return;
                }

                if(TextUtils.isEmpty(mName)){
                    name.setError("Required Field...");
                    return;
                }

                if (TextUtils.isEmpty(mAddress)){
                    address.setError("Required Field...");
                    return;
                }
                if(TextUtils.isEmpty(mRentStart)){
                    rent_start.setError("Required Field...");
                    return;
                }

                if (TextUtils.isEmpty(mRentEnd)){
                    rent_end.setError("Required Field...");
                    return;

                }if(TextUtils.isEmpty(mCar)){
                    car.setError("Required Field...");
                    return;
                }

                if (TextUtils.isEmpty(mCost)){
                    cost.setError("Required Field...");
                    return;
                }

                String id = mDatabase.push().getKey();
                Data data = new Data(mRevCode, mName, mAddress, mRentStart, mRentEnd, mCar, mCost, id);
                mDatabase.child(id).setValue(data);

                Toast.makeText(getApplicationContext(), "Data Added", Toast.LENGTH_SHORT).show();

                dialog.dismiss();
            }
        });

        dialog.show();
    }

//    @Override
//    protected void onStart(){
//        super.onStart();
//
//
//    }

    public void updateData(){
        AlertDialog.Builder mydialog = new AlertDialog.Builder(HomeActivity.this);
        LayoutInflater inflater = LayoutInflater.from(HomeActivity.this);
        View mView = inflater.inflate(R.layout.update_input, null);

        final AlertDialog dialog = mydialog.create();
        dialog.setView(mView);

        final EditText edt_revCode  = mView.findViewById(R.id.edt_revCode_upd);
        final EditText edt_name     = mView.findViewById(R.id.edt_name_upd);
        final EditText edt_address  = mView.findViewById(R.id.edt_address_upd);
        final EditText edt_rentStart= mView.findViewById(R.id.edt_rent_start_upd);
        final EditText edt_rentEnd  = mView.findViewById(R.id.edt_rent_end_upd);
        final EditText edt_car      = mView.findViewById(R.id.edt_car_upd);
        final EditText edt_cost     = mView.findViewById(R.id.edt_cost_upd);

        edt_revCode.setText(revCode);

        edt_name.setText(name);

        edt_address.setText(address);

        edt_rentStart.setText(rent_start);

        edt_rentEnd.setText(rent_end);

        edt_car.setText(car);

        edt_cost.setText(cost);

        Button btnUpdate = mView.findViewById(R.id.btn_save_upd);
        Button btnDelete = mView.findViewById(R.id.btn_delete_upd);
        Button btnCancel = mView.findViewById(R.id.cancel);

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                revCode     = edt_revCode.getText().toString().trim();
                name        = edt_name.getText().toString().trim();
                address     = edt_address.getText().toString().trim();
                rent_start  = edt_rentStart.getText().toString().trim();
                rent_end    = edt_rentEnd.getText().toString().trim();
                car         = edt_car.getText().toString().trim();
                cost        = edt_cost.getText().toString().trim();

                Data data = new Data(revCode, name, address, rent_start, rent_end, car, cost, post_key);

                mDatabase.child(post_key).setValue(data);
                dialog.dismiss();

            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDatabase.child(post_key).removeValue();

                dialog.dismiss();
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.show();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_item, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.language:
                startActivity(new Intent(Settings.ACTION_LOCALE_SETTINGS));
                break;
            case R.id.about_creator:
                startActivity(new Intent(getApplicationContext(), AboutActivity.class));
                break;
            case R.id.log_out:
                mAuth.signOut();
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                break;

        }

        return super.onOptionsItemSelected(item);
    }


}
