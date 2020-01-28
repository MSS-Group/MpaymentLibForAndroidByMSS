package com.mss.testlib.data;

import com.mss.testlib.data.model.Activation;
import com.mss.testlib.data.model.Authentification;
import com.mss.testlib.data.model.CheckOtp;
import com.mss.testlib.data.model.OtpGeneration;
import com.mss.testlib.data.model.PreActivation;
import com.mss.testlib.data.model.Token;
import com.mss.testlib.data.model.TransfertProInput;
import com.mss.testlib.data.model.TransfertProOutput;

import io.reactivex.Single;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiServices {

    @POST("/WebApiMobileGateway/api/MobileGatewayApi/PreActivation")
    Single<PreActivation> preActivation(
            @Query("MSISDN") String msisdn,
            @Query("IMEI") String imei,
            @Query("IdSession") String idSession,
            @Query("TypeOfUser") String type);

    @POST("/WebApiMobileGateway/api/MobileGatewayApi/Activation")
    Single<Activation> activation(@Query("MSISDN") String msisdn,
                                  @Query("IMEI") String imei,
                                  @Query("IdSession") String idSession,
                                  @Query("TypeOfUser") String type,
                                  @Query("OTP") String OTP,
                                  @Query("Token") String token);


    @POST("/WebApiMobileGateway/api/MobileGatewayApi/AuthentificationWithHachage")
    Single<Authentification> authentificationWithHachage(@Query("MSISDN") String msisdn,
                                                         @Query("IMEI") String imei,
                                                         @Query("IdSession") String idSession,
                                                         @Query("TypeOfUser") String type,
                                                         @Query("Pwd") String pwd,
                                                         @Query("regId") String regId,
                                                         @Query("HachMobile") String HachMobile,
                                                         @Query("Token") String token);

    @POST("/WebApiMobileGateway/PostTransfertPro")
    Single<TransfertProOutput> transfertPro(@Body TransfertProInput transfertProInput);


    @POST("/WebApiMobileGateway/api/MobileGatewayApi/ConnectOwin")
    Single<Token> token(@Query("User") String username,
                        @Query("Pwd") String password);
}
