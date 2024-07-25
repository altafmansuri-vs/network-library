package com.pub.secure.network;

import com.pub.secure.model.LanguageModel;
import com.pub.secure.model.SettingModel;
import com.pub.secure.model.VideoTamplateModel;
import com.pub.secure.responses.FrameResponse;
import com.pub.secure.responses.HomeResponse;
import com.pub.secure.responses.PaytmResponse;
import com.pub.secure.responses.SimpleResponse;
import com.pub.secure.responses.StripeResponse;
import com.pub.secure.responses.UserResponse;
import com.pub.secure.responses.WhatsappOtpResponse;

import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiService {

    @GET("{API_KEY}/settings")
    Call<List<SettingModel>> getSettings(@Path("API_KEY") String apikey);

    @GET("{API_KEY}/language")
    Call<List<LanguageModel>> getLanguages(@Path("API_KEY") String apikey);

    @GET("{API_KEY}/profile")
    Call<UserResponse> geUserProfile(@Path("API_KEY") String apikey,
                                     @Query("user_id") String user_id,
                                     @Query("business_id") String business_id);

    @GET("{API_KEY}/userposts")
    Call<UserResponse> getUserPosts(@Path("API_KEY") String apikey,
                                    @Query("user_id") String user_id);

    @FormUrlEncoded
    @POST("{API_KEY}/login")
    Call<UserResponse> login(@Path("API_KEY") String apikey,
                             @Field("social") String social,
                             @Field("social_id") String social_id,
                             @Field("auth_token") String auth_token,
                             @Field("email") String email,
                             @Field("number") String number,
                             @Field("profile_pic") String profile_pic,
                             @Field("name") String name,
                             @Field("device_token") String device_token);

    @Multipart
    @POST("{API_KEY}/updateProPic")
    Call<UserResponse> updateProfilePic(@Path("API_KEY") String apikey,
                                        @Part("user_id") RequestBody user_id,
                                        @Part MultipartBody.Part image);

    @Multipart
    @POST("{API_KEY}/offlineSuscription")
    Call<UserResponse> offlineSuscription(@Path("API_KEY") String apikey,
                                          @Part("user_id") RequestBody user_id,
                                          @Part("type") RequestBody type,
                                          @Part("subscription_id") RequestBody sid,
                                          @Part("promocode") RequestBody promocode,
                                          @Part("amount") RequestBody amount,
                                          @Part MultipartBody.Part image);

    @Multipart
    @POST("{API_KEY}/uploadspost")
    Call<UserResponse> uploadUserPost(@Path("API_KEY") String apikey,
                                      @Part("user_id") RequestBody user_id,
                                      @Part MultipartBody.Part file);

    @FormUrlEncoded
    @POST("{API_KEY}/updateUserProfile")
    Call<UserResponse> updateProfile(@Path("API_KEY") String apikey,
                                     @Field("user_id") String user_id,
                                     @Field("name") String name,
                                     @Field("refer_code") String refer_code);

    @FormUrlEncoded
    @POST("{API_KEY}/addcontact")
    Call<UserResponse> addContact(@Path("API_KEY") String apikey,
                                  @Field("user_id") String user_id,
                                  @Field("number") String email,
                                  @Field("message") String number);

    @FormUrlEncoded
    @POST("{API_KEY}/addinquiry")
    Call<UserResponse> addInquiry(@Path("API_KEY") String apikey,
                                  @Field("user_id") String user_id,
                                  @Field("service_id") String service_id,
                                  @Field("number") String email,
                                  @Field("message") String number);

    @FormUrlEncoded
    @POST("{API_KEY}/paytmPayment")
    Call<PaytmResponse> createPaytmPayment(@Path("API_KEY") String apikey,
                                           @Field("order_id") String order_id,
                                           @Field("cust_id") String cust_id,
                                           @Field("amount") String amount);

    @FormUrlEncoded
    @POST("{API_KEY}/cashfreePayment")
    Call<SimpleResponse> createCashfreePayment(@Path("API_KEY") String apikey,
                                               @Field("order_id") String order_id,
                                               @Field("amount") String amount);

    @FormUrlEncoded
    @POST("{API_KEY}/verifyPaytmPayment")
    Call<SimpleResponse> verifyPaytmPayment(@Path("API_KEY") String apikey,
                                            @Field("order_id") String order_id);

    @FormUrlEncoded
    @POST("{API_KEY}/updateUserSubscription")
    Call<UserResponse> updateSubscription(@Path("API_KEY") String apikey,
                                          @Field("user_id") String user_id,
                                          @Field("type") String type,
                                          @Field("subscription_id") String subscription_id,
                                          @Field("transaction_id") String transaction_id,
                                          @Field("promocode") String promocode,
                                          @Field("amount") String amount);

    @GET("{API_KEY}/userbusinessdetail")
    Call<UserResponse> getBusinessDetail(@Path("API_KEY") String apikey,
                                         @Query("user_id") String user_id,
                                         @Query("id") String id);

    @GET("{API_KEY}/userbusiness")
    Call<UserResponse> getUserBusiness(@Path("API_KEY") String apikey,
                                       @Query("user_id") String user_id,
                                       @Query("type") String type);

    @FormUrlEncoded
    @POST("{API_KEY}/cheakReferCode")
    Call<UserResponse> cheakReferCode(@Path("API_KEY") String apikey,
                                      @Field("code") String code);

    @FormUrlEncoded
    @POST("{API_KEY}/usercategory")
    Call<UserResponse> getUserCategory(@Path("API_KEY") String apikey,
                                       @Field("category_id") String category_id);

    @GET("{API_KEY}/businesspoliticalcategory")
    Call<HomeResponse> getBusinessPoliticalCategory(@Path("API_KEY") String apikey,
                                                    @Query("search") String search,
                                                    @Query("type") String type);

    @GET("{API_KEY}/dailyPosts")
    Call<HomeResponse> getDailyPosts(@Path("API_KEY") String apikey,
                                     @Query("search") String ratio,
                                     @Query("language") String language,
                                     @Query("type") String type,
                                     @Query("page") int pageCount);

    @Multipart
    @POST("{API_KEY}/adduserbusiness")
    Call<UserResponse> addBusiness(@Path("API_KEY") String apikey,
                                   @Part MultipartBody.Part image,
                                   @Part("id") RequestBody id,
                                   @Part("user_id") RequestBody user_id,
                                   @Part("company") RequestBody company,
                                   @Part("name") RequestBody name,
                                   @Part("number") RequestBody number,
                                   @Part("email") RequestBody email,
                                   @Part("website") RequestBody website,
                                   @Part("address") RequestBody address,
                                   @Part("whatsapp") RequestBody whatsapp,
                                   @Part("facebook") RequestBody facebook,
                                   @Part("twitter") RequestBody twitter,
                                   @Part("youtube") RequestBody youtube,
                                   @Part("instagram") RequestBody instagram,
                                   @Part("about") RequestBody about,
                                   @Part("type") RequestBody type,
                                   @Part("designation") RequestBody designation,
                                   @Part("category_id") RequestBody category_id,
                                   @Part("automation") RequestBody automation,
                                   @Part("time") RequestBody tzime,
                                   @Part("auto_frame_type") RequestBody auto_frame_type,
                                   @Part("auto_frame_id") RequestBody auto_frame_id,
                                   @Part("auto_festival") RequestBody auto_festival,
                                   @Part("auto_business") RequestBody auto_business,
                                   @Part("auto_language") RequestBody auto_language);

    @FormUrlEncoded
    @POST("{API_KEY}/deletebusiness")
    Call<SimpleResponse> deleteUserBusiness(@Path("API_KEY") String apikey,
                                            @Field("id") String id);

    @FormUrlEncoded
    @POST("{API_KEY}/createuserbusinesscard")
    Call<SimpleResponse> createBusinessCard(@Path("API_KEY") String apikey,
                                            @Field("card_id") String user_id,
                                            @Field("business_id") String business_id);

    @GET("{API_KEY}/homedata")
    Call<HomeResponse> getHomeData(@Path("API_KEY") String apikey,
                                   @Query("language") String language,
                                   @Query("user_id") String user_id,
                                   @Query("category") String category,
                                   @Query("page") int page);

    @GET("{API_KEY}/mybusinessdata")
    Call<HomeResponse> getMyBusinessData(@Path("API_KEY") String apikey,
                                         @Query("language") String language,
                                         @Query("user_id") String user_id,
                                         @Query("category") String category,
                                         @Query("page") int page);

    @GET("{API_KEY}/bradingdata")
    Call<HomeResponse> getBrandingData(@Path("API_KEY") String apikey,
                                       @Query("language") String language,
                                       @Query("user_id") String user_id,
                                       @Query("category") String category,
                                       @Query("page") int page);

    @GET("{API_KEY}/customdata")
    Call<HomeResponse> getCustomData(@Path("API_KEY") String apikey,
                                     @Query("language") String language,
                                     @Query("user_id") String user_id,
                                     @Query("category") String category,
                                     @Query("page") int page);

    @GET("{API_KEY}/businesscards")
    Call<HomeResponse> getBusinessCards(@Path("API_KEY") String apikey);

    @GET("{API_KEY}/ourservices")
    Call<HomeResponse> getServices(@Path("API_KEY") String apikey);

    @GET("{API_KEY}/invitationcategories")
    Call<HomeResponse> getInvitationCategories(
            @Path("API_KEY") String apikey,
            @Query("search") String search);

    @GET("{API_KEY}/invitationcardsbycateid")
    Call<HomeResponse> getInvitationCardsByCatId(
            @Path("API_KEY") String apikey,
            @Query("category") String id);

    @GET("{API_KEY}/subscriptions")
    Call<HomeResponse> getSubscriptions(@Path("API_KEY") String apikey);

    @GET("{API_KEY}/premiumpostsbycategory/{TYPE}")
    Call<HomeResponse> getPremiumPostByCategory(@Path("API_KEY") String apikey,
                                                @Path("TYPE") String type);

    @GET("{API_KEY}/updatepostviews")
    Call<SimpleResponse> updatePostViews(@Path("API_KEY") String apikey,
                                         @Query("id") String id,
                                         @Query("type") String type);

    @GET("{API_KEY}/cheakPromo")
    Call<HomeResponse> cheakPromo(@Path("API_KEY") String apikey,
                                  @Query("code") String code);

    @GET("{API_KEY}/categoriesbypage")
    Call<HomeResponse> getCategoriesByPage(@Path("API_KEY") String apikey,
                                           @Query("page") int page,
                                           @Query("type") String type,
                                           @Query("search") String search);

    @GET("{API_KEY}/videotamplatecategoriesbypage")
    Call<HomeResponse> getVideoTamplateCategoriesByPage(@Path("API_KEY") String apikey,
                                                        @Query("page") int page,
                                                        @Query("type") String type,
                                                        @Query("search") String search);

    @GET("{API_KEY}/postsbypage")
    Call<HomeResponse> getPostByPage(@Path("API_KEY") String apikey,
                                     @Query("page") int page,
                                     @Query("type") String type,
                                     @Query("language") String language,
                                     @Query("post_type") String post_type,
                                     @Query("item_id") String item_id,
                                     @Query("subcategory") String subcategory,
                                     @Query("postid") String postid,
                                     @Query("search") String search,
                                     @Query("user_id") String uid);

    @GET("{API_KEY}/frames")
    Call<FrameResponse> getFrames(@Path("API_KEY") String apikey,
                                  @Query("user_id") String user_id,
                                  @Query("business_id") String business_id,
                                  @Query("category_id") String category_id,
                                  @Query("animated") boolean animated);

    @GET("{API_KEY}/userframes")
    Call<FrameResponse> getUserFrames(@Path("API_KEY") String apikey,
                                      @Query("user_id") String user_id,
                                      @Query("business_id") String business_id);

    @GET("{API_KEY}/automationframes")
    Call<UserResponse> getAutomationFrames(@Path("API_KEY") String apikey,
                                           @Query("user_id") String user_id,
                                           @Query("type") String type,
                                           @Query("category") String category,
                                           @Query("business_id") String bussiness_id);

    @GET("{API_KEY}/userinvitelist")
    Call<UserResponse> getInvitedUser(@Path("API_KEY") String apikey,
                                      @Query("user_id") String user_id);

    @GET("{API_KEY}/withdrawlist")
    Call<UserResponse> getWithdrawRequest(@Path("API_KEY") String apikey,
                                          @Query("user_id") String user_id);

    @GET("{API_KEY}/transactionlist")
    Call<UserResponse> getTransactionRequest(@Path("API_KEY") String apikey,
                                             @Query("user_id") String user_id);

    @FormUrlEncoded
    @POST("{API_KEY}/withdrawrequest")
    Call<UserResponse> withdrawRequest(@Path("API_KEY") String apikey,
                                       @Field("user_id") String user_id,
                                       @Field("balance") int balance,
                                       @Field("upi") String upi);

    @GET("{API_KEY}/stickercategory")
    Call<FrameResponse> getStickersByCategory(@Path("API_KEY") String apikey,
                                              @Query("search") String search);

    @GET("{API_KEY}/logoscategory")
    Call<FrameResponse> getLogosByCategory(@Path("API_KEY") String apikey);

    @GET("{API_KEY}/musiccategory")
    Call<FrameResponse> getMusicByCategory(@Path("API_KEY") String apikey,
                                           @Query("search") String search);

    @GET("{API_KEY}/videoTamplates")
    Call<List<VideoTamplateModel>> getVideoTamplates(@Path("API_KEY") String apikey,
                                                     @Query("category") String id);

    @FormUrlEncoded
    @POST("{API_KEY}/whatsappotp")
    Call<WhatsappOtpResponse> createWhatsappOtp(@Path("API_KEY") String apiKey,
                                                @Field("number") String number);

    @FormUrlEncoded
    @POST("{API_KEY}/updatedevicetoken")
    Call<SimpleResponse> updateDeviceToken(@Path("API_KEY") String apiKey,
                                           @Field("user_id") String number,
                                           @Field("token") String token);

    @FormUrlEncoded
    @POST("{API_KEY}/createStripePayment")
    Call<StripeResponse> createStripePayment(@Path("API_KEY") String apiKey,
                                             @Field("user_id") String user_id,
                                             @Field("order_id") String orderID,
                                             @Field("amount") String amount);

    @GET("{API_KEY}/searchSuggestion")
    Call<List<String>> getSearchSuggestion(@Path("API_KEY") String apikey,
                                           @Query("search") String search);

    @GET("{API_KEY}/backgrounds")
    Call<FrameResponse> getBackgrounds(@Path("API_KEY") String apikey,
                                       @Query("search") String search);

    @GET("{API_KEY}/updatepostcount")
    Call<SimpleResponse> updatePostCount(@Path("API_KEY") String apikey,
                                         @Query("user_id") String id);

    @GET("{API_KEY}/searchdata")
    Call<HomeResponse> getSearchData(@Path("API_KEY") String apikey,
                                     @Query("search") String search);

    @FormUrlEncoded
    @POST("{API_KEY}/deleteuseraccount")
    Call<SimpleResponse> deleteUserAccount(@Path("API_KEY") String apikey,
                                           @Field("user_id") String user_id);

    @GET("{API_KEY}/grouplist")
    Call<HomeResponse> getGroups(@Path("API_KEY") String apikey,
                                 @Query("user_id") String user_id,
                                 @Query("search") String search);

    @GET("{API_KEY}/grouppost")
    Call<HomeResponse> getGroupsPosts(@Path("API_KEY") String apikey,
                                      @Query("language") String language,
                                      @Query("user_id") String uid,
                                      @Query("group_id") String gid,
                                      @Query("page") int pagecount);

    @GET("{API_KEY}/groupdetails")
    Call<HomeResponse> getGroupsDetails(@Path("API_KEY") String apiKey,
                                        @Query("user_id") String uid,
                                        @Query("group_id") String gid);

    @FormUrlEncoded
    @POST("{API_KEY}/updategroupuser")
    Call<SimpleResponse> updateGroupUser(@Path("API_KEY") String apiKey,
                                         @Field("user_id") String user_id,
                                         @Field("action") String action);

    @GET("{API_KEY}/searchusers")
    Call<HomeResponse> getSearchUsers(@Path("API_KEY") String apiKey,
                                      @Query("user_id") String user_id,
                                      @Query("group_id") String group_id,
                                      @Query("search") String search);

    @FormUrlEncoded
    @POST("{API_KEY}/addusergroup")
    Call<SimpleResponse> addUserGroup(@Path("API_KEY") String apiKey,
                                      @Field("group_id") String group_id,
                                      @Field("user_id") String user_id);

    @FormUrlEncoded
    @POST("{API_KEY}/deleteexitgroup")
    Call<SimpleResponse> deleteExiteGroup(@Path("API_KEY") String apiKey,
                                          @Field("group_id") String group_id,
                                          @Field("user_id") String user_id,
                                          @Field("action") String action);

    @Multipart
    @POST("{API_KEY}/creategroup")
    Call<SimpleResponse> createGroup(@Path("API_KEY") String apiKey,
                                     @Part("user_id") RequestBody userid,
                                     @Part("name") RequestBody name,
                                     @Part("description") RequestBody description,
                                     @Part("privacy") RequestBody privacy,
                                     @Part MultipartBody.Part image);

    @FormUrlEncoded
    @POST("{API_KEY}/deleteuserframe")
    Call<SimpleResponse> deleteUserFrame(@Path("API_KEY") String apiKey,
                                         @Field("frame_id") String frame_id);

    @GET("{API_KEY}/getproductsdata")
    Call<UserResponse> getProductData(@Path("API_KEY") String apiKey,
                                      @Query("user_id") String user_id,
                                      @Query("business_id") String businessID);

    @GET("{API_KEY}/inquiries")
    Call<UserResponse> getInquiriesData(@Path("API_KEY") String apiKey,
                                        @Query("user_id") String user_id,
                                        @Query("business_id") String businessID);

    @FormUrlEncoded
    @POST("{API_KEY}/updatedefaultbusiness")
    Call<SimpleResponse> updateDefaultBusiness(@Path("API_KEY") String apiKey,
                                               @Field("user_id") String uid,
                                               @Field("business_id") String id);
}
