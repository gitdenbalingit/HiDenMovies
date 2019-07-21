package com.hiden.challenge.presentation.common.arch

import io.reactivex.Scheduler

interface PostExecutionThread {

    val scheduler: Scheduler
}