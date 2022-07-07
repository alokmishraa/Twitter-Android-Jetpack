package com.alok.twitter.model.service

interface LogService {
    fun logNonFatalCrash(throwable: Throwable)
}
