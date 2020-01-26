package winarwahyuw.winw.daftarbelanja;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyViewHolder extends RecyclerView.ViewHolder {
    View myview;

    public MyViewHolder(@NonNull View itemView) {
        super(itemView);
        myview=itemView;
    }

    public void setRevCode(String revCode){
        TextView mRevCode = myview.findViewById(R.id.revCode);
        mRevCode.setText(revCode);
    }

    public void setName(String name){
        TextView mName = myview.findViewById(R.id.name);
        mName.setText(name);
    }

    public void setAddress(String address){
        TextView mAddress = myview.findViewById(R.id.address);
        mAddress.setText(address);
    }

    public void setRentStart(String rentStart){
        TextView mType = myview.findViewById(R.id.rent_start);
        mType.setText(rentStart);
    }

    public void setRentEnd(String rentEnd){
        TextView mNote = myview.findViewById(R.id.rent_end);
        mNote.setText(rentEnd);
    }

    public void setCar(String car){
        TextView mCar = myview.findViewById(R.id.car);
        mCar.setText(car);
    }

    public void setCost(String cost){
        TextView mDate = myview.findViewById(R.id.cost);
        mDate.setText(cost);
    }

//    public void setRen(int amount){
//        TextView mAmount = myview.findViewById(R.id.amount);
//        String stam = String.valueOf(amount);
//        mAmount.setText(stam);
//    }
}
