package com.ibt.complaintmanagement.ui.fragment;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ibt.complaintmanagement.R;
import com.ibt.complaintmanagement.adapter.ComplaintListAdapter;
import com.ibt.complaintmanagement.modal.User;
import com.ibt.complaintmanagement.modal.complaint_list.ComplaintMainModal;
import com.ibt.complaintmanagement.pagination_listener.PaginationScrollListener;
import com.ibt.complaintmanagement.retrofit_provider.RetrofitService;
import com.ibt.complaintmanagement.retrofit_provider.WebResponse;
import com.ibt.complaintmanagement.utils.Alerts;
import com.ibt.complaintmanagement.utils.BaseFragment;
import com.ibt.complaintmanagement.utils.ConnectionDetector;
import com.ibt.complaintmanagement.utils.TimeUtil;

import retrofit2.Response;

public class HomeFragment extends BaseFragment implements View.OnClickListener {

    private View rootView;
    private ComplaintListAdapter adapter;

    private SwipeRefreshLayout swipeRefresh;
    private LinearLayoutManager linearLayoutManager;
    private static final int PAGE_START = 1;
    private int currentPage = PAGE_START;
    private static int TOTAL_PAGES;
    private boolean isLoading = false;
    private boolean isLastPage = false;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        rootView = inflater.inflate(R.layout.fragment_home, container, false);
        mContext = getActivity();
        retrofitRxClient = RetrofitService.getRxClient();
        retrofitApiClient = RetrofitService.getRetrofit();
        cd = new ConnectionDetector(mContext);
        init();
        return rootView;
    }

    private void init() {

        swipeRefresh = rootView.findViewById(R.id.swipeRefresh);

        RecyclerView recyclerViewComplaint = rootView.findViewById(R.id.recyclerViewComplaint);
        cd = new ConnectionDetector(mContext);
        retrofitApiClient = RetrofitService.getRetrofit();

        adapter = new ComplaintListAdapter(mContext, activity);
        recyclerViewComplaint.setHasFixedSize(true);
        linearLayoutManager = new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false);
        recyclerViewComplaint.setLayoutManager(linearLayoutManager);
        recyclerViewComplaint.setItemAnimator(new DefaultItemAnimator());
        recyclerViewComplaint.setAdapter(adapter);
        recyclerViewComplaint.addOnScrollListener(new PaginationScrollListener(linearLayoutManager) {
            @Override
            protected void loadMoreItems() {
                isLoading = true;
                currentPage += 1;
                loadNextPage();
            }

            @Override
            public int getTotalPageCount() {
                return TOTAL_PAGES;
            }

            @Override
            public boolean isLastPage() {
                return isLastPage;
            }

            @Override
            public boolean isLoading() {
                return isLoading;
            }
        });

        adapter.notifyDataSetChanged();

        timelineApi();
        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                adapter.getTripList().clear();
                adapter.notifyDataSetChanged();
                timelineApi();
                swipeRefresh.setRefreshing(false);
            }
        });
    }

    private void timelineApi() {
        currentPage = PAGE_START;
        if (cd.isNetworkAvailable()) {
            String strUserId = User.getUser().getId();
            String time = TimeUtil.getSecondDateTime();
            RetrofitService.getComplaintList(new Dialog(mContext), retrofitApiClient.getComplaint(strUserId,
                    "1", time, "0"), new WebResponse() {
                @Override
                public void onResponseSuccess(Response<?> result) {
                    ComplaintMainModal timelineModel = (ComplaintMainModal) result.body();
                    if (timelineModel != null) {
                        if (!timelineModel.getError()) {
                            TOTAL_PAGES = timelineModel.getPageCount();
                            adapter.addAll(timelineModel.getComplaint());
                            if (currentPage < TOTAL_PAGES) {
                                adapter.addLoadingFooter();
                                isLastPage = false;
                            } else if (currentPage == TOTAL_PAGES) {
                                isLastPage = true;
                            }
                        } else {
                            Alerts.show(mContext, result.message());
                        }
                    }
                    adapter.notifyDataSetChanged();
                }

                @Override
                public void onResponseFailed(String error) {
                    Alerts.show(mContext, error);
                }
            });
        } else {
            cd.show(mContext);
        }
    }

    private void loadNextPage() {
        if (cd.isNetworkAvailable()) {
            String strUserId = User.getUser().getId();
            String time = TimeUtil.getSecondDateTime();
            RetrofitService.getComplaintList(new Dialog(mContext), retrofitApiClient.getComplaint(strUserId,
                    currentPage + "", time, "0"), new WebResponse() {
                @Override
                public void onResponseSuccess(Response<?> result) {
                    ComplaintMainModal timelineModel = (ComplaintMainModal) result.body();
                    if (timelineModel != null) {
                        if (!timelineModel.getError()) {
                            TOTAL_PAGES = timelineModel.getPageCount();
                            adapter.removeLoadingFooter();
                            isLoading = false;
                            adapter.addAll(timelineModel.getComplaint());
                            if (currentPage != TOTAL_PAGES) adapter.addLoadingFooter();
                            else isLastPage = true;
                        } else {
                            Alerts.show(mContext, result.message());
                        }
                    }
                }

                @Override
                public void onResponseFailed(String error) {
                    Alerts.show(mContext, error);
                }
            });
        } else {
            cd.show(mContext);
        }
    }

    @Override
    public void onClick(View v) {

    }
}
