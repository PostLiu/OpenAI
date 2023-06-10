package com.postliu.openai.exceptions

import okio.IOException

class TokenExpiredException(val code: Int, override val message: String) : IOException(message)