package com.postliu.openai.router

import com.postliu.openai.BuildConfig

object RouterPath {

    private const val ip = BuildConfig.IP

    const val Main = "$ip/Main"

    const val Home = "$ip/Home"

    const val Partner = "$ip/Partner"

    const val Exchange = "$ip/Exchange"

    const val Store = "$ip/Store"

    const val User = "$ip/User"

    const val Login = "$ip/Login"

    const val Register = "$ip/Register"

    const val Forget = "$ip/Forget"

    const val PartnerArea = "$ip/PartnerArea"

}