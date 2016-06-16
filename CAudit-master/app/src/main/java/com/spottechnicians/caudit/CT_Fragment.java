package com.spottechnicians.caudit;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.spottechnicians.caudit.DatabaseHandler.DbHelper;
import com.spottechnicians.caudit.ModuleCT.CT_Questions;
import com.spottechnicians.caudit.adapters.AtmList;
import com.spottechnicians.caudit.models.Atm;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class CT_Fragment extends Fragment {
    ListView listViewCT;
    AtmList atmListAdapter;
    List<Atm> listOfAtms;
    EditText etSearchBar;
    public CT_Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View rootView=inflater.inflate(R.layout.fragment_ct_, container, false);
        listViewCT=(ListView)rootView.findViewById(R.id.listviewCT);
        etSearchBar=(EditText)rootView.findViewById(R.id.etSearchBarCT);
        listOfAtms=new ArrayList<>();




        listOfAtms=createDummyList();
        atmListAdapter=new AtmList(getContext(),listOfAtms);
        listViewCT.setAdapter(atmListAdapter);
        listViewCT.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String stringAtmId = ((TextView) view.findViewById(R.id.tvAtmId)).getText().toString();
                // Toast.makeText(getContext(),stringAtmId,Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(getContext(),CT_Questions.class);
                intent.putExtra("atmid", stringAtmId);
                startActivity(intent);
            }
        });
        etSearchBar.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                    atmListAdapter.getFilter().filter(etSearchBar.getText());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        return rootView;
    }

    public List<Atm> createDummyList()
    {
        List<Atm> listOfAtms1=new ArrayList<>();
        Atm atmObject;
        atmObject=new Atm();
        String atmid="ab89797";
        atmObject.setAtmId(atmid);
        atmObject.setBankName("yes");
        atmObject.setCustomerName("deibold");
        atmObject.setAddress("sion west sies");
        atmObject.setState("maharasthra");
        atmObject.setCity("mumbai");
        Log.d("listdata",atmid);
        listOfAtms1.add(atmObject);
        atmObject=new Atm();
        String atmid2="udg89797";
        atmObject.setAtmId(atmid2);
        atmObject.setBankName("icici");
        atmObject.setCustomerName("hitachi");
        atmObject.setAddress("matunga east");
        atmObject.setState("maharasthra");
        atmObject.setCity("pune");
        Log.d("listdata",atmid);
        listOfAtms1.add(atmObject);
        return listOfAtms1;
    }

    public List<Atm> getAtmsTypeCT()
    {
       List<Atm> listOfAtms1=new DbHelper(getContext()).fetchAtmsByType("ct/hk");
        return listOfAtms1;
    }

}
