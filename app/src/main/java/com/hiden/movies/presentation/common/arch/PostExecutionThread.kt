package com.hiden.movies.presentation.common.arch

import io.reactivex.Scheduler

interface PostExecutionThread {

    val scheduler: Scheduler
}