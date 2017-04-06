package com.bagicode.www.bagipukesmasvideo;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.util.ArrayMap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 * A simple {@link Fragment} subclass.
 */
public class laporanFragment extends Fragment implements AdapterView.OnItemClickListener {


    private CostumListLaporAdapter adapter_off;
    private List<model_lapor> model_homeList = new ArrayList<model_lapor>();
    private ListView listView;
    private ProgressDialog pDialog;

    private JSONObject Laporan;
    private ArrayList wow;
    private SessionManager session;

    public laporanFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_laporan, container, false);

        adapter_off = new CostumListLaporAdapter(getActivity(), model_homeList );
        listView = (ListView) rootView.findViewById(R.id.list_home);
        listView.setAdapter(adapter_off);
        listView.setOnItemClickListener(laporanFragment.this);
        listView.setClickable(true);

        session = new SessionManager(getActivity().getApplicationContext());

//        getLaporan("1,2,3,4,5,6,7");
        wow = new ArrayList();
        for (int a=0; a<=7; a++){
            wow.add(a);
        }

        getLaporan(String.valueOf(wow));
        session.setDataPage(String.valueOf(wow));
        session.setPage(String.valueOf(8));

        Button btnNext = (Button) rootView.findViewById(R.id.next);
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String halaman = session.getPage();
                Integer pageAwal = Integer.parseInt(halaman)-1;
                Integer pageAkhir = Integer.parseInt(halaman)+7;

                wow.clear();
                for (int a=pageAwal; a<=pageAkhir; a++){
                    wow.add(a);
                }

                getLaporan(String.valueOf(wow));
                session.setDataPage(String.valueOf(wow));
                session.setPage(String.valueOf(pageAkhir));

            }
        });

        Button btnback = (Button) rootView.findViewById(R.id.back);
        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String halaman = session.getPage();
                Integer pageAkhir = Integer.parseInt(halaman)-7;
                Integer pageAwal = pageAkhir-7;

                session.setPage(String.valueOf(pageAkhir));

                wow.clear();
                for (int a=pageAkhir; a>=pageAwal; a--){
                    wow.add(a);
                }

                if (pageAwal<0){
                    getLaporan("1,2,3,4,5,6,7");
                    session.setDataPage("1,2,3,4,5,6,7");
                    session.setPage("8");
                } else {
                    getLaporan(String.valueOf(wow));
                    session.setDataPage(String.valueOf(wow));
                    session.setPage(String.valueOf(pageAkhir));
                }


            }
        });

        return rootView;
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

    }

    public void getLaporan(final String status){

        String tag_string_req = "req_lapor";

        pDialog = new ProgressDialog(getActivity());
        pDialog.setMessage("Loading...");
        pDialog.show();

        StringRequest strReq = new StringRequest(Request.Method.GET,
                AppConfig.Host+status+AppConfig.pukesmas , new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {

                hidePDialog();

                try {
                    JSONObject jsonObj = new JSONObject(response);

                    model_homeList.clear();
                    listView.setAdapter(adapter_off);
                    // Getting JSON Array node
                    JSONArray contacts = jsonObj.getJSONArray("features");

                    // looping through All Contacts
                    for (int i = 0; i < contacts.length(); i++) {
                        JSONObject c = contacts.getJSONObject(i);
                        String type = c.getString("type");

                        // Phone node is JSON Object
                        JSONObject properties = c.getJSONObject("properties");
                        String id = properties.getString("id");
                        String nama_Puskesmas = properties.getString("nama_Puskesmas");
                        String email = properties.getString("email");
                        String kepala_puskesmas = properties.getString("kepala_puskesmas");

                        model_lapor judulModel = new model_lapor();
                        judulModel.set_judul(nama_Puskesmas);
                        judulModel.set_by(email);
                        judulModel.set_tgl(kepala_puskesmas);
                        model_homeList.add(judulModel);

                    }
                } catch (JSONException e) {
                    // JSON error
                    e.printStackTrace();
                    Toast.makeText(getActivity(), "Json error: " + e.getMessage(), Toast.LENGTH_LONG).show();
                }

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                //Log.e(TAG, "Login Error: " + error.getMessage());
                Toast.makeText(getActivity(),
                        "ErrorResponse "+error.getMessage(), Toast.LENGTH_LONG).show();
                hidePDialog();
            }
        })
        {

            @Override
            public Map<String, String> getHeaders() {
                Map<String, String> mHeaders = new ArrayMap<String, String>();
                mHeaders.put("Authorization", "NmSOR4L7FAN+qFPU32y7h7quDx5+7jb+SibgPOfJCR97XsDcuVPs118JpZwXx7Gn");
                return mHeaders;
            }

        };

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(strReq, tag_string_req);

    }

    private void hidePDialog() {
        if (pDialog != null) {
            pDialog.dismiss();
            pDialog = null;
        }
    }
}
