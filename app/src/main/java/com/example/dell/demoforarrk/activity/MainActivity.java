package com.example.dell.demoforarrk.activity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.dell.demoforarrk.R;
import com.example.dell.demoforarrk.adapter.RecyclerAdapter;
import com.example.dell.demoforarrk.model.StarWarDetailResponseModel;
import com.example.dell.demoforarrk.presenter.StarApiPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.example.dell.demoforarrk.constants.Constants.SEPERATOR;

public class MainActivity extends AppCompatActivity implements StarApiPresenter.StarWarInterface {


    @BindView(R.id.recycler)
    RecyclerView mRecyclerView;
    @BindView(R.id.button_retry)
    Button buttonRetry;
    @BindView(R.id.progressbar)
    ProgressBar mProgressBar;

    private StarApiPresenter starApiPresenter;
    private RecyclerAdapter mRecyclerAdapter;
    private ProgressDialog progressDialog;
    private int pageNumber = 1;
    private String nextUrl;
    private boolean mIsLoading;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        starApiPresenter = new StarApiPresenter();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(linearLayoutManager);

        mRecyclerAdapter = new RecyclerAdapter(this);
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage(getString(R.string.fetching_msg));
        linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

        mRecyclerView.setAdapter(mRecyclerAdapter);

        starApiPresenter.getStarWarCharacterDetails(this, pageNumber);
        progressDialog.show();

        buttonRetry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonRetry.setVisibility(View.GONE);
                progressDialog.show();
                starApiPresenter.getStarWarCharacterDetails(MainActivity.this, pageNumber);
            }
        });

        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (mIsLoading)
                    return;
                LinearLayoutManager mLayoutManager = ((LinearLayoutManager)mRecyclerView.getLayoutManager());
                int visibleItemCount = mLayoutManager.getChildCount();
                int totalItemCount = mLayoutManager.getItemCount();
                int pastVisibleItems = mLayoutManager.findFirstVisibleItemPosition();
                if (pastVisibleItems + visibleItemCount >= totalItemCount) {
                    if (nextUrl != null) {
                        mIsLoading = true;
                        if (mProgressBar != null)
                            mProgressBar.setVisibility(View.VISIBLE);
                        starApiPresenter.getStarWarCharacterDetails(MainActivity.this, pageNumber);
                    } else {
                        mIsLoading = false;
                        Toast.makeText(MainActivity.this, getString(R.string.list_end_msg), Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }


    @Override
    public void onSuccessStarWarDetails(StarWarDetailResponseModel starWarDetailResponseModel) {
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
        if (buttonRetry != null && buttonRetry.getVisibility() == View.VISIBLE) {
            buttonRetry.setVisibility(View.GONE);
        }
        if (mProgressBar != null)
            mProgressBar.setVisibility(View.GONE);
        mIsLoading = false;
        try {
            if (starWarDetailResponseModel.getNext() != null) {
                nextUrl = starWarDetailResponseModel.getNext();
                pageNumber = Integer.parseInt(nextUrl.split(SEPERATOR)[1]);
            } else {
                nextUrl = null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        mRecyclerAdapter.notify(starWarDetailResponseModel.getResults());
    }

    @Override
    public void onErrorStarWarDetails() {
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
        if (buttonRetry != null)
              buttonRetry.setVisibility(View.VISIBLE);
        if (mProgressBar != null)
            mProgressBar.setVisibility(View.GONE);
        mIsLoading = false;
        Toast.makeText(this, getString(R.string.error_msg), Toast.LENGTH_SHORT).show();
    }
}
