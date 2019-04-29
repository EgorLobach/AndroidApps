package com.egorlobach.firebasechat.fragments;

import com.egorlobach.firebasechat.notifications.MyResponse;
import com.egorlobach.firebasechat.notifications.Sender;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface APIService {
    @Headers(
            {
                    "Content-Type:application/json",
                    "Authorization:key=AAAAxlBecLU:APA91bEyFPRsSvTAJkm3h8pskwn8R9oq2llsD6FVPHD5tvZcsIBH1Sp0r_fVGU2ki0L6QYa0ZLRqQtcny25Tl9-voNc4M4J49Bs6Nw8QVIeHIoE2yRDPqG5Qh_r7ai4X36pc4MbyJw-d"
            }
    )

    @POST("fcm/send")
    Call<MyResponse> sendNotification(@Body Sender body);
}
