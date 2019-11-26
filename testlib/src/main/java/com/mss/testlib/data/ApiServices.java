package com.mss.testlib.data;

import com.mss.testlib.data.model.CheckOtp;
import com.mss.testlib.data.model.OtpGeneration;
import com.mss.testlib.data.model.Token;

import io.reactivex.Single;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiServices {

    @POST("/WebApiMobileGateway/api/MobileGatewayApi/OtpGeneration")
    Single<OtpGeneration> generateOtp(@Query("MSISDN") String msisdn,
                                      @Query("IMEI") String imei,
                                      @Query("idSession") String idSession,
                                      @Query("TypeOfUser") String typeOfUser,
                                      @Query("Service") String service,
                                      @Query("OperationClient") String operationClient);

    @POST("/WebApiMobileGateway/api/MobileGatewayApi/CheckOtp")
    Single<CheckOtp> checkOtp(@Query("MSISDN") String msisdn,
                              @Query("IMEI") String imei,
                              @Query("idSession") String idSession,
                              @Query("TypeOfUser") String typeOfUser,
                              @Query("Service") String service,
                              @Query("OTP") String otp,
                              @Query("Token") String token);

    @POST("/WebApiMobileGateway/api/MobileGatewayApi/ConnectOwin")
    Single<Token> token(@Query("User") String username,
                        @Query("Pwd") String password);
}
