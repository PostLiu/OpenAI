package com.postliu.openai.exceptions

import okio.IOException

class DataResultException(val code: Int, override val message: String) : IOException(message)