package com.zeowls.a700apptask.Fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zeowls.a700apptask.Adapters.HomeAdapter;
import com.zeowls.a700apptask.DataModel.Result;
import com.zeowls.a700apptask.MyApplication;
import com.zeowls.a700apptask.R;
import com.zeowls.a700apptask.ServiceHandler.NetworkService;
import com.zeowls.a700apptask.UIHelper.PresenterIntegrator;
import com.zeowls.a700apptask.UIHelper.PresenterLayer;

import retrofit2.Call;

public class StoreFragment extends Fragment {

    private Call<Result> CategoryCaller;
    private boolean rxCallInWorks = false;
    private static final String EXTRA_RX = "EXTRA_RX";

    View view;

    public StoreFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_store, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        NetworkService service = ((MyApplication) getActivity().getApplication()).getNetworkService();
        PresenterIntegrator presenter = new PresenterLayer(service);
        if (savedInstanceState != null) {
            rxCallInWorks = savedInstanceState.getBoolean(EXTRA_RX);
        } else {
            rxCallInWorks = true;
            presenter.loadStoreData(this);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean(EXTRA_RX, rxCallInWorks);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (CategoryCaller != null) {
            CategoryCaller.cancel();
            CategoryCaller = null;
        }
    }

    public void showRxInProcess() {
        //ToDo Add Loading Function
    }

    public void showRxResults(Result response) {
        initView(response);
    }

    private void initView(Result response) {
        RecyclerView categoryRecycler = view.findViewById(R.id.category_recycler);
        categoryRecycler.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false) {
            @Override
            public boolean canScrollHorizontally() {
                return false;
            }

            @Override
            public boolean canScrollVertically() {
                return false;
            }
        });
        HomeAdapter homeAdapter = new HomeAdapter(getActivity());
        homeAdapter.setData(response.getItems());
        categoryRecycler.setAdapter(homeAdapter);
    }

    public void showRxFailure() {
        //ToDo Add Error Message On Error
    }

}
