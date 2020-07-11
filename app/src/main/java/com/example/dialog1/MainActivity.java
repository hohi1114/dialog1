package com.example.dialog1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView listview = null ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ArrayList<Customer> items = new ArrayList<>();

        //final ArrayList<ListViewItem> items = new ArrayList<>();
        final ListViewAdapter adapter;

        // Adapter 생성
        adapter = new ListViewAdapter() ;

        // 리스트뷰 참조 및 Adapter달기
        listview = (ListView) findViewById(R.id.listview1);
        listview.setAdapter(adapter);

        AssetManager assetManager = getAssets();
        try {
            InputStream is= assetManager.open("data.json");
            InputStreamReader isr= new InputStreamReader(is);
            BufferedReader reader= new BufferedReader(isr);

            StringBuffer buffer= new StringBuffer();
            String line= reader.readLine();
            while (line!=null){
                buffer.append(line+"\n");
                line=reader.readLine();
            }

            String jsonData= buffer.toString();
            JSONArray jsonArray= new JSONArray(jsonData);

            String s="";

            for(int i=0; i<jsonArray.length();i++){
                JSONObject jo=jsonArray.getJSONObject(i);

                String name= jo.getString("name");
                String msg= jo.getString("msg");
                String gender = jo.getString("gender");
                adapter.addItem(ContextCompat.getDrawable(this, R.drawable.anonymous), name, msg);
                items.add(new Customer(name, msg, gender));
                //JSONObject flag=jo.getJSONObject("flag");
                //int aa= flag.getInt("aa");
                //int bb= flag.getInt("bb");

                //s += name+" : "+msg+"==>"+aa+","+bb+"\n";
            }
            //tv.setText(s);

        } catch (IOException e) {e.printStackTrace();} catch (JSONException e) {e.printStackTrace(); }

        EditText editTextFilter = (EditText)findViewById(R.id.editTextFilter) ;
        editTextFilter.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable edit) {
                String filterText = edit.toString() ;
                if (filterText.length() > 0) {
                    listview.setFilterText(filterText) ;
                } else {
                    listview.clearTextFilter() ;
                }
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
        });

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Intent intent = new Intent(getApplicationContext(), CustomersClicked.class);
                intent.putExtra("name", items.get(position).getName());
                intent.putExtra("gender", items.get(position).getGender());
                intent.putExtra("phone", items.get(position).getPhone());
                startActivity(intent);
            }
        });

        ///////////////////////전화번호 추가 기능/////////////////////////////
//        final ArrayList<String> addList = new ArrayList<String>();
//        final ArrayAdapter adapter1 = new ArrayAdapter(this, android.R.layout.simple_list_item_single_choice);

        final ListView LV = (ListView) findViewById(R.id.listview1);
        LV.setAdapter(adapter);

        Button addButton = (Button)findViewById(R.id.addPhone);
        addButton.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View v){
                int count;
                count = adapter.getCount();

                adapter.addItem(ContextCompat.getDrawable(getApplicationContext(), R.drawable.anonymous), "신성진", "010-9238-7609");
                items.add(new Customer("신성진", "010-9238-7609", "남자"));
                //addList.add("LIST"+Integer.toString(count+1));

                adapter.notifyDataSetChanged();
                //Toast.makeText(getApplicationContext(), "와우", Toast.LENGTH_SHORT).show();
            }
        });
    }
}