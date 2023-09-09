package com.raflisalam.fakeneflix.common.exception

class MediaTypeNotRecognizedException(message: String) : Exception(message)

class ApiException(message: String): Exception(message)
class EmptyListException(message: String) : Exception(message)