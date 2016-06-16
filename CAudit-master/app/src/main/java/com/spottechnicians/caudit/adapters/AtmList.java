package com.spottechnicians.caudit.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import com.spottechnicians.caudit.R;
import com.spottechnicians.caudit.models.Atm;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ganesh on 6/2/2016.
 */
public class AtmList extends BaseAdapter implements Filterable{
    Context context;
    List<Atm> listOfAtms;
    List<Atm> listOfFilteredAtms;
    ValueFilter valueFilter;

  //  private  static LayoutInflater inflater=null;

     public AtmList(Context context, List<Atm> listOfAtms)
     {
         this.context=context;
         this.listOfAtms=listOfAtms;
         this.listOfFilteredAtms=listOfAtms;


     }
    @Override
    public int getCount() {
        return listOfAtms.size();
    }

    @Override
    public Object getItem(int position) {

        return position;
    }

    @Override
    public long getItemId(int position) {

        return listOfAtms.indexOf(getItem(position));
    }



    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        View rootView=convertView;
        if(rootView==null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            rootView = inflater.inflate(R.layout.atm_list_item, null);
            viewHolder = new ViewHolder();
            viewHolder.atmID = (TextView) rootView.findViewById(R.id.tvAtmId);
            viewHolder.bankName = (TextView) rootView.findViewById(R.id.tvBankName);
            viewHolder.cutomerName = (TextView) rootView.findViewById(R.id.tvCustomerName);
            viewHolder.address = (TextView) rootView.findViewById(R.id.tvAddress);
            viewHolder.city = (TextView) rootView.findViewById(R.id.tvCity);
            viewHolder.state = (TextView) rootView.findViewById(R.id.tvState);
            viewHolder.date = (TextView) rootView.findViewById(R.id.tvAuditedDate);
            viewHolder.days = (TextView) rootView.findViewById(R.id.tvAuditedDays);
            Log.d("listdata2", listOfAtms.get(position).getAtmId());

            rootView.setTag(viewHolder);
        }

            viewHolder=(ViewHolder)rootView.getTag();


            viewHolder.atmID.setText(listOfAtms.get(position).getAtmId());
            viewHolder.bankName.setText(listOfAtms.get(position).getBankName());
            viewHolder.cutomerName.setText(listOfAtms.get(position).getCustomerName());
            viewHolder.address.setText(listOfAtms.get(position).getAddress());
            viewHolder.city.setText(listOfAtms.get(position).getCity());
            viewHolder.state.setText(listOfAtms.get(position).getState());
            viewHolder.date.setText("may-06-2016");
            viewHolder.days.setText("5days");
            return  rootView;



    }




    static class ViewHolder
    {
        TextView atmID,bankName,cutomerName,address,city,state,date,days;

    }


    @Override
    public Filter getFilter() {
        if(valueFilter==null)
        {
            valueFilter=new ValueFilter();
        }
        return valueFilter;
    }


    class ValueFilter extends Filter{

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults results=new FilterResults();
            if(constraint!=null && constraint.length()>0)
            {
                List<Atm> filterList=new ArrayList<Atm>();
                for(int i=0;i<listOfFilteredAtms.size();i++)
                {
                    if((listOfFilteredAtms.get(i).getAtmId().toLowerCase().contains(constraint.toString().toLowerCase()))||
                            (listOfFilteredAtms.get(i).getAddress().toLowerCase().contains(constraint.toString().toLowerCase()))||
                            (listOfFilteredAtms.get(i).getState().toLowerCase().contains(constraint.toString().toLowerCase()))
                            ||(listOfFilteredAtms.get(i).getCity().toLowerCase().contains(constraint.toString().toLowerCase()))
                            ||(listOfFilteredAtms.get(i).getBankName().toLowerCase().contains(constraint.toString().toLowerCase()))
                            ||(listOfFilteredAtms.get(i).getCustomerName().toLowerCase().contains(constraint.toString().toLowerCase())))
                    {
                        Atm atmObject=new Atm();
                        atmObject.setAtmId(listOfFilteredAtms.get(i).getAtmId());
                        atmObject.setBankName(listOfFilteredAtms.get(i).getBankName());
                        atmObject.setCustomerName(listOfFilteredAtms.get(i).getCustomerName());
                        atmObject.setAddress(listOfFilteredAtms.get(i).getAddress());
                        atmObject.setState(listOfFilteredAtms.get(i).getState());
                        atmObject.setCity(listOfFilteredAtms.get(i).getCity());
                        filterList.add(atmObject);

                    }
                }
                results.count=filterList.size();
                results.values=filterList;

            }
            else
            {
                results.count=listOfFilteredAtms.size();
                results.values=listOfFilteredAtms;
            }
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            listOfAtms=(List<Atm>)results.values;
            notifyDataSetChanged();
        }
    }






}
