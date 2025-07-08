package com.java.myapplication;

import android.app.DatePickerDialog;
import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class BillAdapter extends RecyclerView.Adapter<BillAdapter.ViewHolder> {

    private List<Bill> billList;
    private Context context;
    private SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());

    public BillAdapter(List<Bill> billList) {
        this.billList = billList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_bill, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Bill currentBill = billList.get(position);
        holder.editTextBillNo.setText(currentBill.getBillNo());
        holder.textViewBillDate.setText(currentBill.getDate());
        holder.editTextIssuedBy.setText(currentBill.getIssuedBy());
        holder.editTextExpensesDetails.setText(currentBill.getExpensesDetails());
        holder.editTextAmount.setText(String.valueOf(currentBill.getAmount()));

        holder.buttonPickBillDate.setOnClickListener(v -> showDatePickerDialog(holder, position));
    }

    private void showDatePickerDialog(ViewHolder holder, int position) {
        Calendar calendar = Calendar.getInstance();
        DatePickerDialog datePickerDialog = new DatePickerDialog(context,
                (view, year, monthOfYear, dayOfMonth) -> {
                    calendar.set(Calendar.YEAR, year);
                    calendar.set(Calendar.MONTH, monthOfYear);
                    calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                    billList.get(position).setDate(dateFormatter.format(calendar.getTime()));
                    notifyItemChanged(position); // Update the displayed date in the TextView
                },
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();
    }

    @Override
    public int getItemCount() {
        return billList.size();
    }

    public void addBill() {
        billList.add(new Bill());
        notifyItemInserted(billList.size() - 1);
    }

    public List<Bill> getBillList() {
        for (int i = 0; i < billList.size(); i++) {
            ViewHolder holder = (ViewHolder) recyclerView.findViewHolderForAdapterPosition(i);
            if (holder != null) {
                Bill bill = billList.get(i);
                bill.setBillNo(holder.editTextBillNo.getText().toString().trim());
                bill.setDate(holder.textViewBillDate.getText().toString().trim());
                bill.setIssuedBy(holder.editTextIssuedBy.getText().toString().trim());
                bill.setExpensesDetails(holder.editTextExpensesDetails.getText().toString().trim());
                String amountStr = holder.editTextAmount.getText().toString().trim();
                bill.setAmount(amountStr.isEmpty() ? 0 : Double.parseDouble(amountStr));
            }
        }
        return billList;
    }

    private RecyclerView recyclerView;

    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        this.recyclerView = recyclerView;
    }

    public static class Bill implements Parcelable {
        private String billNo = "";
        private String date = "";
        private String issuedBy = "";
        private String expensesDetails = "";
        private double amount = 0.0;

        public Bill() {
            Calendar calendar = Calendar.getInstance();
            this.date = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(calendar.getTime());
        }

        protected Bill(Parcel in) {
            billNo = in.readString();
            date = in.readString();
            issuedBy = in.readString();
            expensesDetails = in.readString();
            amount = in.readDouble();
        }

        public static final Creator<Bill> CREATOR = new Creator<Bill>() {
            @Override
            public Bill createFromParcel(Parcel in) {
                return new Bill(in);
            }

            @Override
            public Bill[] newArray(int size) {
                return new Bill[size];
            }
        };

        public String getBillNo() {
            return billNo;
        }

        public void setBillNo(String billNo) {
            this.billNo = billNo;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public String getIssuedBy() {
            return issuedBy;
        }

        public void setIssuedBy(String issuedBy) {
            this.issuedBy = issuedBy;
        }

        public String getExpensesDetails() {
            return expensesDetails;
        }

        public void setExpensesDetails(String expensesDetails) {
            this.expensesDetails = expensesDetails;
        }

        public double getAmount() {
            return amount;
        }

        public void setAmount(double amount) {
            this.amount = amount;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(billNo);
            dest.writeString(date);
            dest.writeString(issuedBy);
            dest.writeString(expensesDetails);
            dest.writeDouble(amount);
        }
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        EditText editTextBillNo;
        TextView textViewBillDate;
        Button buttonPickBillDate;
        EditText editTextIssuedBy;
        EditText editTextExpensesDetails;
        EditText editTextAmount;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            editTextBillNo = itemView.findViewById(R.id.editTextBillNo);
            textViewBillDate = itemView.findViewById(R.id.textViewBillDate);
            buttonPickBillDate = itemView.findViewById(R.id.buttonPickBillDate);
            editTextIssuedBy = itemView.findViewById(R.id.editTextIssuedBy);
            editTextExpensesDetails = itemView.findViewById(R.id.editTextExpensesDetails);
            editTextAmount = itemView.findViewById(R.id.editTextAmount);
        }
    }
}