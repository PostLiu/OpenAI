package com.postliu.openai.api

import com.postliu.openai.base.DataResult
import com.postliu.openai.model.network.*
import retrofit2.http.*

interface ApiService {

    // 商品列表
    @FormUrlEncoded
    @POST("/api/Goods/goods_list")
    suspend fun getHomeGoods(@FieldMap params: Map<String, @JvmSuppressWildcards Any>): DataResult<NetworkPageGoodsData>

    // 首页轮博图,新品
    @FormUrlEncoded
    @POST("/api/Index/getIndexAdList")
    suspend fun getHomeConfig(@FieldMap params: Map<String, @JvmSuppressWildcards Any>): DataResult<NetworkHomeConfigData>

    // 首页分页分类
    @GET("/api/Goodsclass/index")
    suspend fun getHomePagerSort(): DataResult<NetworkGoodsSortData>

    // 登录
    @FormUrlEncoded
    @POST("/api/Login/index")
    suspend fun login(@FieldMap params: Map<String, @JvmSuppressWildcards Any>): DataResult<NetworkLoginData>

    // 获取验证码
    @FormUrlEncoded
    @POST("/api/Connect/get_sms_captcha")
    suspend fun getSmsCode(@FieldMap params: Map<String, @JvmSuppressWildcards Any>): DataResult<Any>

    // 注册账号
    @FormUrlEncoded
    @POST("/api/Connect/sms_register")
    suspend fun register(@FieldMap params: Map<String, @JvmSuppressWildcards Any>): DataResult<Any>

    // 重置登录密码
    @FormUrlEncoded
    @POST("/api/Connect/find_password")
    suspend fun resetPassword(@FieldMap params: Map<String, @JvmSuppressWildcards Any>): DataResult<Any>

    // 店铺分类
    @GET("/api/Businessindex/index")
    suspend fun storeSortList(@QueryMap params: Map<String, @JvmSuppressWildcards Any>): DataResult<NetworkStoreSortParentData>

    // 店铺列表
    @FormUrlEncoded
    @POST("/api/Businessindex/store_list")
    suspend fun storeList(@FieldMap params: Map<String, @JvmSuppressWildcards Any>): DataResult<NetworkStorePageData>

    // 用户资料
    @FormUrlEncoded
    @POST("/api/Member/index")
    suspend fun userProfile(@FieldMap params: Map<String, @JvmSuppressWildcards Any>): DataResult<NetworkUserProfileData>
}