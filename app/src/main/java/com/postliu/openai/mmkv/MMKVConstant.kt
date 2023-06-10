package com.postliu.openai.mmkv

import com.dylanc.mmkv.MMKVOwner
import com.dylanc.mmkv.mmkvBool
import com.dylanc.mmkv.mmkvString

/**
 * 保存数据到mmkv/读取mmkv中的值
 */
object MMKVConstant : MMKVOwner {

    /**
     * token字段 登录用户访问需要token才能调用的接口
     */
    var token by mmkvString()

    /**
     * 是否登录 记录用户是否登录了账号的状态
     *
     * true已登录 false未登录
     */
    var isLogin by mmkvBool(false)

    /**
     * 是否同意，用于处理首次安装弹出用户政策，隐私协议弹窗
     *
     * true 已同意 false 未同意
     */
    var isAgree by mmkvBool(false)
}